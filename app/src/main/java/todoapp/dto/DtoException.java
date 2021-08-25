package todoapp;

import java.lang.Exception;

public class DtoException extends Exception {
    String msg;

    public DtoException(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return msg;
    }
}