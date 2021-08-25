package todoapp;

import com.google.gson.Gson;

/*
    Tools for working with json.
    Gson lives here.
*/

public class JsonTool {
    private static Gson gson = new Gson();
    public static String serialize(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T deserialize(String str, Class<T> clazz) {
        return gson.fromJson(str, clazz);
    }
}