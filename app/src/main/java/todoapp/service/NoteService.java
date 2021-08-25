package todoapp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;



/*
    An interface for working with business logic.
    Implementation of this interface are very likely to change much. So we need DIP.
*/

interface NoteService {
    public List<NoteDto> getNotes();
    public NoteDto getNote(int id) throws NoSuchElementException;
    public int addNote(NoteDto dto) throws DtoException;
    public void deleteNote(int id);
    public void updateNote(int id, NoteDto dto) throws DtoException, NoSuchElementException;
}