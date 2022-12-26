/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotebookTest {
    @Test
    void addNote() throws IOException, ParseException {
        try (
                PrintStream out = new PrintStream(new ByteArrayOutputStream());
                PrintStream err = new PrintStream(new ByteArrayOutputStream())
        ) {
            String fileName = "test.json";
            File file = new File(fileName);
            file.delete();

            GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
            calendar.clear();
            calendar.setTimeZone(TimeZone.getTimeZone("Asia/Novosibirsk"));
            calendar.set(2005, Calendar.APRIL, 13, 0, 0, 0);

            Notebook.DateTime mockedDateImpl = mock();
            when(mockedDateImpl.getDate()).thenReturn(calendar.getTime());

            String[] addNote = new String[]{"-add", "my note", "to be honest I hate notes"};
            Notebook.run(out, err, fileName, mockedDateImpl, addNote);

            String expected = "{\"notes\":[{\"title\":\"my note\",\"content\":\"to "
                    + "be honest I hate notes\",\"created\":1113325200000}]}";

            Assertions.assertEquals(expected, Files.readString(Paths.get(fileName)));

            file.delete();
        }
    }

    @Test
    void removeNote() throws IOException, ParseException {
        try (
                PrintStream out = new PrintStream(new ByteArrayOutputStream());
                PrintStream err = new PrintStream(new ByteArrayOutputStream())
        ) {
            String fileName = "test.json";
            File file = new File(fileName);
            file.delete();

            GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
            calendar.clear();
            calendar.setTimeZone(TimeZone.getTimeZone("Asia/Novosibirsk"));
            calendar.set(2005, Calendar.APRIL, 13, 0, 0, 0);

            Notebook.DateTime mockedDateImpl = mock();
            when(mockedDateImpl.getDate()).thenReturn(calendar.getTime());

            String[] addNote = new String[]{"-add", "my note", "to be honest I hate notes"};
            Notebook.run(out, err, fileName, mockedDateImpl, addNote);

            String[] removeNote = new String[]{"-rm", "my note"};
            Notebook.run(out, err, fileName, mockedDateImpl, removeNote);

            String expected = "{\"notes\":[]}";

            Assertions.assertEquals(expected, Files.readString(Paths.get(fileName)));

            file.delete();
        }
    }

    @Test
    void showAll() throws IOException, ParseException {
        try (
                ByteArrayOutputStream outByte = new ByteArrayOutputStream();
                PrintStream out = new PrintStream(outByte);
                PrintStream err = new PrintStream(new ByteArrayOutputStream())
        ) {
            String fileName = "test.json";
            File file = new File(fileName);
            file.delete();

            GregorianCalendar firstCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
            firstCalendar.clear();
            firstCalendar.setTimeZone(TimeZone.getTimeZone("Asia/Novosibirsk"));
            firstCalendar.set(2005, Calendar.APRIL, 13, 0, 0, 0);

            GregorianCalendar secondCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
            secondCalendar.clear();
            secondCalendar.setTimeZone(TimeZone.getTimeZone("Asia/Novosibirsk"));
            secondCalendar.set(2009, Calendar.FEBRUARY, 13, 3, 7, 3);

            Notebook.DateTime mockedDateImpl = mock();
            when(mockedDateImpl.getDate()).thenReturn(firstCalendar.getTime(), secondCalendar.getTime());

            String[] addFirstNote = new String[]{"-add", "my note", "to be honest I hate notes"};
            Notebook.run(out, err, fileName, mockedDateImpl, addFirstNote);

            String[] addSecondNote = new String[]{"-add", "my second note", "nvmd I love them"};
            Notebook.run(out, err, fileName, mockedDateImpl, addSecondNote);

            String[] showAll = new String[]{"-show"};
            Notebook.run(out, err, fileName, mockedDateImpl, showAll);

            String expected = "Note{title='my note', content='to be honest I hate notes',"
                    + " created=Wed Apr 13 00:00:00 NOVST 2005}\n" +
                    "Note{title='my second note', content='nvmd I love them', "
                    + "created=Fri Feb 13 03:07:03 NOVT 2009}\n";

            Assertions.assertEquals(expected, outByte.toString());

            file.delete();
        }
    }
}
