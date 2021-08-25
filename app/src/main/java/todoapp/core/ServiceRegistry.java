package todoapp;

/*
    A class that stores all the services.
    It allows a controller to access any service, without mentioning it explicitly in constructor.
*/
public class ServiceRegistry {
    private NoteService noteService;

    public ServiceRegistry() {
        this.noteService = new NoteServiceImpl(AppConf.repo);
    }

    public NoteService getNoteService() {
        return noteService;
    }
}
