package model;

import java.util.Date;

public class Comment {
    Date time;
    String message;
    Long userId;

    public Date getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public Comment(String message) {
        this.message = message;
        time = new Date();
    }
}
