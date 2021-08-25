package todoapp;

public class UriConf {
    public static String getNotesUri(){
        return getNotesUri("");
    }

    public static String getNotesUri(String s){
        return "/notes/" + s;
    }

    public static String getNotesUri(int id){
        return getNotesUri("" + id);
    }
}