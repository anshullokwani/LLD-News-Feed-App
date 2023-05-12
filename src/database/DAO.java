package database;

import model.Comment;
import model.Post;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO {
    private static DAO dao;
    public static DAO getInstance() {
        if(dao == null) {
            dao = new DAO();
        }
        return dao;
    }

    // keeping track of logged in user
    User currentUser;

    // data structures needed for in-memory store
    Map<Long, User> userIdToUserMap = new HashMap<>();
    Map<String, Long> userNameToUserIdMap = new HashMap<>();
    Map<Long, Post> postIdToPostMap = new HashMap<>();


    public void login(String userName) throws Exception{
        if(!userNameToUserIdMap.containsKey(userName)) {
            System.out.println("User does not exist, please sign up!!!");
            return;
        }
        Long userId = userNameToUserIdMap.get(userName);
        User user = userIdToUserMap.get(userId);
        currentUser = user;
    }

    public void signup(User user) throws Exception{
        if(userNameToUserIdMap.containsKey(user.getName())) {
            System.out.println("User already Exists, please login!!!");
            return;
        }

        userNameToUserIdMap.put(user.getName(), user.getId());
        userIdToUserMap.put(user.getId(), user);
    }

    public void post(Post post) throws Exception{
        if (isCurrentUserNull()) return;
        post.setAuthorId(currentUser.getId());
        postIdToPostMap.put(post.getId(), post);
    }

    public void follow(String userName) throws Exception{
        if (isCurrentUserNull()) return;
        Long userIdToFollow = userNameToUserIdMap.get(userName);
        if(userIdToFollow == null) {
            System.out.println("The user you want to follow does not exist, " +
                    "please try again with valid username!!!");
            return;
        }
        currentUser.getFollowing().add(userIdToFollow);
    }

    public void upVote(Long id) throws Exception{
        if (isCurrentUserNull()) return;
        Post post = postIdToPostMap.get(id);
        if(post == null) {
            System.out.println("post with the specified Id does not exist, " +
                    "please try again with correct post Id!!!");
            return;
        }
        Long userId = currentUser.getId();
        post.getUpVotes().add(userId);
    }

    public void downVote(Long id) throws Exception{
        if (isCurrentUserNull()) return;
        Post post = postIdToPostMap.get(id);
        if(post == null) {
            System.out.println("post with the specified Id does not exist, " +
                    "please try again with correct post Id!!!");
            return;
        }
        Long userId = currentUser.getId();
        post.getDownVotes().add(userId);
    }

    public void reply(Long id, String reply) throws Exception{
        if (isCurrentUserNull()) return;
        Post post = postIdToPostMap.get(id);
        if(post == null) {
            System.out.println("post with the specified Id does not exist, " +
                    "please try again with correct post Id!!!");
            return;
        }
        post.getComments().add(new Comment(reply));
    }

    public List<Post> getAllPosts() {
        if (isCurrentUserNull()) return null;
        return (List<Post>) postIdToPostMap.values();
    }

    private boolean isCurrentUserNull() {
        if(currentUser == null) {
            System.out.println("No user logged in, please login!!!");
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getUsernameFromUserId(Long id) {
        User user = userIdToUserMap.get(id);
        return user.getName();
    }
}
