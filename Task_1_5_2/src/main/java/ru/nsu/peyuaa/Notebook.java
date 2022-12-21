/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import java.util.*;

public class Notebook {
    private class Note {
        @Override
        public String toString() {
            return "Note{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", created=" + created +
                    '}';
        }

        private String title;
        private String content;
        private Date created;

        public Note(String title, String content) {
            this.created = new Date();
            this.title = title;
            this.content = content;
        }
    }

    private class NoteTitleComparator implements Comparator<Note> {
        @Override
        public int compare(Note o1, Note o2) {
            return o1.title.equals(o2.title) ? 0 : 1;
        }
    }

    private List<Note> notes = new ArrayList<>();

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
        boolean isAdded = false;

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).created.after(note.created)) {
                notes.add(i, note);
                isAdded = true;
            }
        }

        if (!isAdded) {
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
        for (Note note : notes) {
            System.out.println(note);
        }
    }
}
