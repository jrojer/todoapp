package todoapp;

import java.util.List;
import java.util.NoSuchElementException;
import spark.Request;
import spark.Response;
import spark.route.HttpMethod;

public class UpdateNote extends BaseController {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.put;
    }

    @Override
    public String getUri() {
        return UriConf.getNotesUri(":id");
    }

    @Override
    public Object handle(Request req, Response resp) {
        int id = Integer.valueOf(req.params(":id"));
        NoteDto noteDto = JsonTool.deserialize(req.body(), NoteDto.class);
        try {
            registry.getNoteService().updateNote(id, noteDto);
        } catch (DtoException | NoSuchElementException e) {
            return ResponseTool.badRequest(resp);
        }
        return ResponseTool.noContent(resp);
    }
}