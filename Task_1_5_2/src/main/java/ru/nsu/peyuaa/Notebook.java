/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Notebook {
    private static class Note {
        @Override
        public String toString() {
            return "Note{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", created=" + created +
                    '}';
        }

        @JsonProperty("title")
        private final String title;
        @JsonProperty("content")
        private final String content;
        @JsonProperty("created")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        private final Date created;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Note(@JsonProperty("title") String title, @JsonProperty("content") String content) {
            this.created = new Date();
            this.title = title;
            this.content = content;
        }
    }

    private final static String add = "-add";
    private final static String remove = "-rm";
    private final static String show = "-show";
    private final static String fileName = "notebook.json";
    private final static String inputDateFormat = "dd.MM.yyyy HH:mm";
    private final static int keyWordsStartIndex = 3;
    private final static int addNumberOfArguments = 3;
    private final static int removeNumberOfArguments = 2;
    private final static int showAllNumberOfArguments = 1;
    private final static int showMinimalNumberOfArguments = 4;
    private final static int minimalNumberOfArguments = 1;
    private final static ObjectMapper objectMapper = new ObjectMapper();


    private static class NoteTitleComparator implements Comparator<Note> {
        @Override
        public int compare(Note o1, Note o2) {
            return o1.title.equals(o2.title) ? 0 : 1;
        }
    }

    @JsonProperty("notes")
    private final List<Note> notes = new ArrayList<>();

    /**
     * Finds and returns index of first note created after the date.
     * If there is a note with time creation equals to that date, returns index of that note.
     * If there is several notes with time creation equals to the date,
     * it returns first encountered note during binary search.
     * Otherwise, returns -1.
     *
     * @param date find after or equals to that date
     * @return index
     */
    private int indexOfNoteAfterDate(Date date) {
        int left = 0;
        int right = notes.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Note midNote = notes.get(mid);
            if (midNote.created.equals(date)) {
                return mid;
            }
            if (midNote.created.after(date)) {
                if (mid == 0) {
                    return mid;
                } else if (notes.get(mid - 1).created.before(date)) {
                    return mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private void addNote(String title, String content) throws IOException {
        Note note = new Note(title, content);
        int indexToAdd = indexOfNoteAfterDate(note.created);
        if (indexToAdd != -1) {
            notes.add(indexToAdd, note);
        } else {
            notes.add(note);
        }
        serialize();
    }

    private void deleteNote(String title) throws IOException {
        int noteIndex = Collections
                .binarySearch(notes, new Note(title,""), new NoteTitleComparator());
        if (noteIndex >= 0) {
            notes.remove(noteIndex);
        }
        serialize();
    }

    private void printNotes() {
        notes.forEach(System.out::println);
    }

    private boolean isNoteContainsKeywords(Note note, String ...keywords) {
        return Arrays.stream(keywords).anyMatch(note.content::contains);
    }

    private void printNotes(Date from, Date to, String ...keywords) {
        int index = indexOfNoteAfterDate(from);
        if (index != -1) {
            while (index < notes.size()
                    && (notes.get(index).created.before(to) || notes.get(index).created.equals(to))) {
                if (isNoteContainsKeywords(notes.get(index), keywords)) {
                    System.out.println(notes.get(index));
                }
                index++;
            }
        }
    }

    private void showNotes(String[] args) throws ParseException {
        if (args.length == 1) {
            printNotes();
        } else {
            SimpleDateFormat format = new SimpleDateFormat(inputDateFormat);
            Date from = format.parse(args[1]);
            Date to = format.parse(args[2]);
            printNotes(from, to, Arrays.copyOfRange(args, keyWordsStartIndex, args.length));
        }
    }

    private void serialize() throws IOException {
        objectMapper.writeValue(new File(fileName), this);
    }

    private static Notebook getNotebook() {
        Notebook notebook;
        try {
            notebook = objectMapper.readValue(new File(fileName), Notebook.class);
        } catch (Exception e) {
            notebook = new Notebook();
        }
        return notebook;
    }

    private static boolean isAddValid(String[] args) {
        return args.length == addNumberOfArguments;
    }

    private static boolean isRemoveValid(String[] args) {
        return args.length == removeNumberOfArguments;
    }

    private static boolean isDateValid(String date) {
        SimpleDateFormat format = new SimpleDateFormat(inputDateFormat);
        Date parsedDate;
        try {
            parsedDate = format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return date.equals(format.format(parsedDate));
    }

    private static boolean isShowValid(String[] args) {
        if (args.length == showAllNumberOfArguments) {
            return true;
        }

        if (args.length < showMinimalNumberOfArguments) {
            return false;
        }
        return isDateValid(args[1]) && isDateValid(args[2]);
    }

    private static boolean isInputValid(String[] args) {
        if (args.length < minimalNumberOfArguments) {
            return false;
        }

        return switch (args[0]) {
            case add -> isAddValid(args);
            case remove -> isRemoveValid(args);
            case show -> isShowValid(args);
            default -> false;
        };
    }

    private void processInput(String[] args) throws IOException, ParseException {
        switch (args[0]) {
            case add -> addNote(args[1], args[2]);
            case remove -> deleteNote(args[1]);
            case show -> showNotes(args);
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        Notebook notebook = getNotebook();
        if (isInputValid(args)) {
            notebook.processInput(args);
        } else {
            System.err.println("Incorrect input");
        }
    }
}
