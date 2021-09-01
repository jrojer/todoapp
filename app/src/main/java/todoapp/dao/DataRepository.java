package todoapp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/*
    Class that abstracts database.
*/

public interface DataRepository {
    int addNote(String text, LocalDateTime datetime);
    List<Note> getAllNotes() throws RepoException;
    Note getNote(int id) throws NoSuchElementException, RepoException;
    void deleteNote(int id) throws RepoException;
    void updateNoteText(int id, String text) throws NoSuchElementException, RepoException;
    void updateNoteDatetime(int id, LocalDateTime datetime)
        throws NoSuchElementException, RepoException;
    void updateNoteDone(int id, boolean val) throws NoSuchElementException, RepoException;
}