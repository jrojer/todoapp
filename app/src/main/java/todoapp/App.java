package todoapp;

public class App {
    public static void main(String[] args) {
        /*
            Start the controllers
         */
        new AddNote().start();
        new DeleteNote().start();
        new GetAllNotes().start();
        new GetNoteById().start();
        new UpdateNote().start();
    }
}
