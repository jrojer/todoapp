package todoapp;

import java.util.List;
import spark.Request;
import spark.Response;
import spark.route.HttpMethod;

public class AddNote extends BaseController {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.post;
    }

    @Override
    public String getUri() {
        return UriConf.getNotesUri();
    }

    @Override
    public Object handle(Request req, Response resp) {
        NoteDto newNote = JsonTool.deserialize(req.body(), NoteDto.class);
        int newResourseId;
        try {
            newResourseId = registry.getNoteService().addNote(newNote);
        } catch (DtoException e) {
            return ResponseTool.badRequest(resp);
        } catch (RepoException e){
            return ResponseTool.serverError(resp);
        }
        return ResponseTool.created(resp, UriConf.getNotesUri(newResourseId));
    }
}