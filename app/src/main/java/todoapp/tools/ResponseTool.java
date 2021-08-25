package todoapp;

import spark.Response;
/*
    Methods for creating responses of different response codes.
    Logging can be added.
    Different headers can be set also.
*/
public class ResponseTool {
    public static String ok(Response response, String json) {
        response.status(200);
        response.header("Content-Type", "application/json");
        // consider "response.body(json);"
        return json;
    }

    public static String noContent(Response response) {
        response.status(204);
        return "";
    }

    public static String created(Response response, String location) {
        response.status(201);
        response.header("Location", location);
        return "";
    }

    public static String notFound(Response response) {
        response.status(404);
        return "";
    }

    public static String badRequest(Response response) {
        response.status(400);
        return "";
    }
}