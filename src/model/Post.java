package model;

import java.sql.Timestamp;
import java.util.*;

public class Post {
    Long id;
    Date time;
    List<Comment> comments;
    String caption;
    Set<Long> upVotes;
    Set<Long> downVotes;
    Long authorId;

    public Date getTime() {
        return time;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getCaption() {
        return caption;
    }

    public Set<Long> getUpVotes() {
        return upVotes;
    }

    public Set<Long> getDownVotes() {
        return downVotes;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }


    public Post(Long id, String caption) {
        this.id = id;
        this.caption = caption;
        upVotes = new HashSet<>();
        downVotes = new HashSet<>();
        time = new Date();
        comments = new ArrayList<>();
    }

}
