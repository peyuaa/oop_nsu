/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

        public Note(String title, String content) {
            this.created = new Date();
            this.title = title;
            this.content = content;
        }
    }

    private final static String add = "-add";
    private final static String remove = "-rm";
    private final static String show = "-show";
    private final static String whitespace = " ";
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
                if (mid - 1 >= 0 && notes.get(mid - 1).created.before(date)) {
                    return mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private void addNote(String title, String content) {
        Note note = new Note(title, content);
        int indexToAdd = indexOfNoteAfterDate(note.created);
        if (indexToAdd != -1) {
            notes.add(indexToAdd, note);
        } else {
            notes.add(note);
        }
    }

    private void deleteNote(String title) {
        int noteIndex = Collections
                .binarySearch(notes, new Note(title,""), new NoteTitleComparator());
        if (noteIndex >= 0) {
            notes.remove(noteIndex);
        }
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
            }
        }
    }

    private void serialize() throws IOException {
        objectMapper.writeValue(new File("notebook.json"), this);
    }
}
