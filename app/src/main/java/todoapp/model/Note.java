package todoapp;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/*
    A class that represents database record Note. Entity.
*/

public class Note {
    private final int id;
    private String text;
    private LocalDateTime datetime;
    private Boolean done;

    public Note(int id, String text, CharSequence datetime) throws DateTimeException {
        this.id = id;
        this.text = text;
        this.datetime = LocalDateTime.parse(datetime);
        this.done = false;
    }

    public Note(int id, String text, LocalDateTime datetime) {
        this.id = id;
        this.text = text;
        this.datetime = datetime;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public Boolean isDone() {
        return done;
    }

    public void setText(String s) {
        text = s;
    }

    public void setDatetime(String dt) throws DateTimeException {
        datetime = LocalDateTime.parse(dt);
    }

    public void setDatetime(LocalDateTime dt) {
        datetime = dt;
    }

    public void setDone(boolean val) {
        done = val;
    }
}
