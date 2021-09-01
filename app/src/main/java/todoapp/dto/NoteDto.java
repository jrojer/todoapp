package todoapp;

/*
    Internal representation of data transfered via API endpoints.
    https://stackoverflow.com/questions/61303236/how-to-use-dtos-in-the-controller-service-and-repository-pattern
*/
public class NoteDto {
    public final String text;
    public final String datetime;
    public final Boolean done;

    public NoteDto(String text, String datetime) {
        this.text = text;
        this.datetime = datetime;
        this.done = false;
    }

    public NoteDto(Note note) {
        this.text = note.getText();
        this.datetime = note.getDatetime().toString();
        this.done = note.isDone();
    }

    public boolean equals(Object obj) {
        if (obj.getClass() != NoteDto.class) {
            return false;
        }
        NoteDto o = (NoteDto) obj;
        return o.text.equals(text) && o.datetime.equals(datetime);
    }

    @Override
    public String toString(){
        return "<" + text + " | " + datetime + " | " + done + ">";
    }
}
