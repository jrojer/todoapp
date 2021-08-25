package todoapp;

import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalDateTime;


/*
    Class that abstracts database.
*/

public interface DataRepository {
    int addNote(String text, LocalDateTime datetime);
    List<Note> getAllNotes();
    Note getNote(int id) throws NoSuchElementException;
    void deleteNote(int id);
    void updateNoteText(int id, String text) throws NoSuchElementException ;
    void updateNoteDatetime(int id, LocalDateTime datetime) throws NoSuchElementException ;
    void updateNoteDone(int id, boolean val) throws NoSuchElementException ;
}