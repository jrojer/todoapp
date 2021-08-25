package todoapp;

import java.util.List;
import java.util.NoSuchElementException;
import spark.Request;
import spark.Response;
import spark.route.HttpMethod;

public class GetNoteById extends BaseController {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.get;
    }

    @Override
    public String getUri() {
        return UriConf.getNotesUri(":id");
    }

    @Override
    public Object handle(Request req, Response resp) {
        int id = Integer.valueOf(req.params(":id"));
        NoteDto note;
        try {
            note = registry.getNoteService().getNote(id);
        } catch (NoSuchElementException e) {
            return ResponseTool.notFound(resp);
        }
        return ResponseTool.ok(resp, JsonTool.serialize(note));
    }
}