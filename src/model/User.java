package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    Long id;
    String name;
    List<Long> following;


    public Long getId() {
        return id;
    }

    public List<Long> getFollowing() {
        return following;
    }

    public String getName() {
        return name;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        following = new ArrayList<>();
    }
}
