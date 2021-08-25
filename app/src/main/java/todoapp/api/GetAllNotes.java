package todoapp;

import java.util.List;
import spark.Request;
import spark.Response;
import spark.route.HttpMethod;

public class GetAllNotes extends BaseController {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.get;
    }

    @Override
    public String getUri() {
        return UriConf.getNotesUri();
    }

    @Override
    public Object handle(Request req, Response resp) {
        List<NoteDto> notes = registry.getNoteService().getNotes();
        return ResponseTool.ok(resp, JsonTool.serialize(notes));
    }
}