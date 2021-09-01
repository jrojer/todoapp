package todoapp;

import java.util.List;
import spark.Request;
import spark.Response;
import spark.route.HttpMethod;

public class DeleteNote extends BaseController {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.delete;
    }

    @Override
    public String getUri() {
        return UriConf.getNotesUri(":id");
    }

    @Override
    public Object handle(Request req, Response resp) {
        int id = Integer.valueOf(req.params(":id"));
        try {
            registry.getNoteService().deleteNote(id);
        } catch (RepoException e) {
            return ResponseTool.serverError(resp);
        }
        return ResponseTool.noContent(resp);
    }
}