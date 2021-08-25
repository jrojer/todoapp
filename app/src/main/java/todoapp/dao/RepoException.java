package todoapp;

public class RepoException extends Exception {
    String msg;
    public RepoException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}