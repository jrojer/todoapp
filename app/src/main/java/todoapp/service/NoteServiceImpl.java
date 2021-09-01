package todoapp;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class NoteServiceImpl implements NoteService {
    private DataRepository repo;

    public NoteServiceImpl(DataRepository repo) {
        this.repo = repo;
    }

    public List<NoteDto> getNotes() throws RepoException {
        List<Note> notes = repo.getAllNotes();
        List<NoteDto> dtos = new ArrayList<>();
        for (Note note : notes) {
            dtos.add(new NoteDto(note));
        }
        return dtos;
    }

    static LocalDateTime parseDatetime(String s) throws DtoException, RepoException {
        try {
            LocalDateTime dt = LocalDateTime.parse(s);
            return dt;
        } catch (DateTimeException e) {
            throw new DtoException(e.toString());
        }
    }

    public int addNote(NoteDto dto) throws DtoException, RepoException {
        return repo.addNote(dto.text, parseDatetime(dto.datetime));
    }

    public void deleteNote(int id) throws RepoException {
        repo.deleteNote(id);
    }

    public NoteDto getNote(int id) throws NoSuchElementException, RepoException {
        Note note = repo.getNote(id);
        return new NoteDto(note);
    }

    public void updateNote(int id, NoteDto dto)
        throws DtoException, NoSuchElementException, RepoException {
        if (dto.text != null) {
            repo.updateNoteText(id, dto.text);
        }
        if (dto.datetime != null) {
            repo.updateNoteDatetime(id, parseDatetime(dto.datetime));
        }
        if (dto.done != null) {
            repo.updateNoteDone(id, dto.done);
        }
    }
}