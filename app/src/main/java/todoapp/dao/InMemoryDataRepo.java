package todoapp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/*
    A fake database for testing.
*/

public class InMemoryDataRepo implements DataRepository {
    private Map<Integer, Note> notes;
    private int autoincrementId;

    InMemoryDataRepo() {
        this.notes = new HashMap<>();
        this.autoincrementId = 1;
    }

    @Override
    public int addNote(String text, LocalDateTime datetime) {
        int id = autoincrementId;
        autoincrementId++;
        notes.put(id, new Note(id, text, datetime));
        return id;
    }

    @Override
    public List<Note> getAllNotes() {
        return notes.entrySet()
            .stream()
            .map(x -> x.getValue())
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public void deleteNote(int id) {
        notes.remove(id);
    }

    @Override
    public Note getNote(int id) throws NoSuchElementException {
        Note note = notes.get(id);
        if (note == null) {
            throw new NoSuchElementException("No such record Note " + id);
        }
        return note;
    }

    @Override
    public void updateNoteText(int id, String text) throws NoSuchElementException {
        Note note = notes.get(id);
        if (note == null) {
            throw new NoSuchElementException("No such record Note " + id);
        }
        note.setText(text);
        notes.put(id, note);
    }

    @Override
    public void updateNoteDatetime(int id, LocalDateTime datetime) throws NoSuchElementException {
        Note note = notes.get(id);
        if (note == null) {
            throw new NoSuchElementException("No such record Note " + id);
        }
        note.setDatetime(datetime);
        notes.put(id, note);
    }

    @Override
    public void updateNoteDone(int id, boolean val) throws NoSuchElementException {
        Note note = notes.get(id);
        if (note == null) {
            throw new NoSuchElementException("No such record Note " + id);
        }
        note.setDone(val);
        notes.put(id, note);
    }
}