package todoapp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*
    An interface for working with business logic.
    Implementation of this interface are very likely to change much. So we need DIP.
*/

interface NoteService {
    public List<NoteDto> getNotes() throws RepoException;
    public NoteDto getNote(int id) throws NoSuchElementException, RepoException;
    public int addNote(NoteDto dto) throws DtoException, RepoException;
    public void deleteNote(int id) throws RepoException;
    public void updateNote(int id, NoteDto dto)
        throws DtoException, NoSuchElementException, RepoException;
}