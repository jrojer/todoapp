package todoapp;

public class RepoException extends RuntimeException {
    String msg;
    public RepoException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}