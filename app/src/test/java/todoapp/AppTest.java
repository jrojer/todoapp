package todoapp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    NoteService service;
    DataRepository repo;
    static NoteDto note1 = new NoteDto("sample text", "2007-12-03T10:15:30");
    static NoteDto note2 = new NoteDto("another text", "2012-12-03T10:15:30");
    static NoteDto note3 = new NoteDto("1234567 1234", "2012-10-03T10:11:33");
    static NoteDto noteBadDate = new NoteDto("1234567 1234", "2012-8880-03T10:11:33");

    @BeforeEach
    public void fixture() {
        repo = new InMemoryDataRepo();
        service = new NoteServiceImpl(repo);
    }

    @Test
    void canAddNote() throws DtoException {
        int id = service.addNote(note1);
        Note note = repo.getNote(id);
        assertEquals(note1, new NoteDto(note));
    }

    @Test
    void canGetAllNotes() throws DtoException {
        service.addNote(note1);
        service.addNote(note2);
        service.addNote(note3);
        assertTrue(service.getNotes().equals(List.of(note1, note2, note3)));
    }

    @Test
    void serializeConsistent() {
        String json = JsonTool.serialize(note1);
        NoteDto sameNote = JsonTool.deserialize(json, NoteDto.class);
        assertEquals(note1, sameNote);
    }

    @Test
    void canGetNoteById() throws DtoException {
        int id1 = service.addNote(note1);
        NoteDto note1_ = service.getNote(id1);
        assertEquals(note1, note1_);
    }

    @Test
    void canDeleteNote() throws DtoException {
        int id1 = service.addNote(note1);
        service.deleteNote(id1);
        assertThrows(NoSuchElementException.class, () -> { service.getNote(id1); });
    }

    @Test
    void canUpdateText() throws DtoException {
        int id1 = service.addNote(note1);
        NoteDto dto = new NoteDto("new text", null);
        service.updateNote(id1, dto);
        NoteDto noteDto = service.getNote(id1);
        assertEquals(noteDto.text, dto.text);
    }
}
