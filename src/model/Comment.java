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

    public Comment(Long userId, String message) {
        this.userId = userId;
        this.message = message;
        this.time = new Date();
    }
}
