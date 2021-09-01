package todoapp;

import static todoapp.generated.Tables.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.*;

/*
    A real database.
*/
public class JooqDataRepo implements DataRepository {
    private static DbConnection db = new DbConnection();

    public JooqDataRepo() {
        // this.db = new DbConnection();
    }

    public int addNote(String text, LocalDateTime datetime) {
        int id;
        try (Connection conn = db.getConnection()) {
            DSLContext ctx = DSL.using(conn, SQLDialect.POSTGRES);
            Record record = ctx.insertInto(NOTE, NOTE.TEXT, NOTE.DATETIME, NOTE.DONE)
                                .values(text, datetime, false)
                                .returningResult(NOTE.ID)
                                .fetchOne();
            id = record.getValue(NOTE.ID);
        } catch (SQLException | DataAccessException e) {
            throw new RepoException(e.toString());
        };
        return id;
    }

    public List<Note> getAllNotes() throws RepoException {
        Result<Record> result;
        // TODO consider try(DSLContext ctx = db.getContext()) {
        try (Connection conn = db.getConnection()) {
            DSLContext ctx = DSL.using(conn, SQLDialect.POSTGRES);
            result = ctx.select().from(NOTE).fetch();
        } catch (SQLException e) {
            throw new RepoException(e.toString());
        }
        if (result == null) {
            throw new RepoException("ctx.select returned null");
        }
        List<Note> notes = new ArrayList<>();
        for (Record r : result) {
            Integer id = r.get("id", Integer.class);
            String text = r.get("text", String.class);
            LocalDateTime dt = r.get("datetime", LocalDateTime.class);
            notes.add(new Note(id, text, dt));
        }
        return notes;
    }

    public Note getNote(int id) throws NoSuchElementException, RepoException {
        Result<Record> result;
        try (Connection conn = db.getConnection()) {
            DSLContext ctx = DSL.using(conn, SQLDialect.POSTGRES);
            result = ctx.select().from(NOTE).where(DSL.condition("id=" + id)).fetch();
        } catch (SQLException | DataAccessException e) {
            throw new RepoException(e.toString());
        }
        if (result == null) {
            throw new RepoException("ctx.select returned null");
        }
        if (result.size() == 0) {
            throw new NoSuchElementException();
        }
        Record r = result.get(0);
        String text = r.get("text", String.class);
        LocalDateTime dt = r.get("datetime", LocalDateTime.class);
        return new Note(id, text, dt);
    }

    public void deleteNote(int id) throws RepoException {
        try (Connection conn = db.getConnection()) {
            DSLContext ctx = DSL.using(conn, SQLDialect.POSTGRES);
            ctx.delete(NOTE).where(NOTE.ID.eq(id)).execute();
        } catch (SQLException | DataAccessException e) {
            throw new RepoException(e.toString());
        }
    }

    public void updateNoteText(int id, String text) throws NoSuchElementException, RepoException {
        int numRecordsUpdated;
        try (Connection conn = db.getConnection()) {
            DSLContext ctx = DSL.using(conn, SQLDialect.POSTGRES);
            numRecordsUpdated =
                ctx.update(NOTE).set(NOTE.TEXT, text).where(NOTE.ID.eq(id)).execute();
        } catch (SQLException | DataAccessException e) {
            throw new RepoException(e.toString());
        };
        if (numRecordsUpdated == 0) {
            throw new NoSuchElementException();
        }
    }

    public void updateNoteDatetime(int id, LocalDateTime datetime) throws NoSuchElementException {
        int numRecordsUpdated;
        try (Connection conn = db.getConnection()) {
            DSLContext ctx = DSL.using(conn, SQLDialect.POSTGRES);
            numRecordsUpdated =
                ctx.update(NOTE).set(NOTE.DATETIME, datetime).where(NOTE.ID.eq(id)).execute();
        } catch (SQLException | DataAccessException e) {
            throw new RepoException(e.toString());
        };
        if (numRecordsUpdated == 0) {
            throw new NoSuchElementException();
        }
    }

    public void updateNoteDone(int id, boolean val) throws NoSuchElementException {
        int numRecordsUpdated;
        try (Connection conn = db.getConnection()) {
            DSLContext ctx = DSL.using(conn, SQLDialect.POSTGRES);
            numRecordsUpdated =
                ctx.update(NOTE).set(NOTE.DONE, val).where(NOTE.ID.eq(id)).execute();
        } catch (SQLException | DataAccessException e) {
            throw new RepoException(e.toString());
        };
        if (numRecordsUpdated == 0) {
            throw new NoSuchElementException();
        }
    }
}