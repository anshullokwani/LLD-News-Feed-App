package database;

import model.Comment;
import model.Post;
import model.User;
import utils.CommonUtils;

import java.util.ArrayList;
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
            CommonUtils.logger("User does not exist, please sign up!!!");
            return;
        }
        Long userId = userNameToUserIdMap.get(userName);
        User user = userIdToUserMap.get(userId);
        currentUser = user;
        CommonUtils.logger("Successfully logged in!!!");
    }

    public void signup(User user) throws Exception{
        if(userNameToUserIdMap.containsKey(user.getName())) {
            CommonUtils.logger("User already Exists, please login!!!");
            return;
        }

        userNameToUserIdMap.put(user.getName(), user.getId());
        userIdToUserMap.put(user.getId(), user);
        CommonUtils.logger("Successfully signed up!!!");
    }

    public void post(Post post) throws Exception{
        if (isCurrentUserNull()) return;
        post.setAuthorId(currentUser.getId());
        postIdToPostMap.put(post.getId(), post);
        CommonUtils.logger("Post successful");
    }

    public void follow(String userName) throws Exception{
        if (isCurrentUserNull()) return;
        Long userIdToFollow = userNameToUserIdMap.get(userName);
        if(userIdToFollow == null) {
            CommonUtils.logger("The user you want to follow does not exist, " +
                    "please try again with valid username!!!");
            return;
        }
        currentUser.getFollowing().add(userIdToFollow);
        CommonUtils.logger("Successfully followed!!!");
    }

    public void upVote(Long id) throws Exception{
        if (isCurrentUserNull()) return;
        Post post = postIdToPostMap.get(id);
        if(post == null) {
            CommonUtils.logger("post with the specified Id does not exist, " +
                    "please try again with correct post Id!!!");
            return;
        }
        Long userId = currentUser.getId();
        post.getUpVotes().add(userId);
        CommonUtils.logger("Successfully upvoted!!!");
    }

    public void downVote(Long id) throws Exception{
        if (isCurrentUserNull()) return;
        Post post = postIdToPostMap.get(id);
        if(post == null) {
            CommonUtils.logger("post with the specified Id does not exist, " +
                    "please try again with correct post Id!!!");
            return;
        }
        Long userId = currentUser.getId();
        post.getDownVotes().add(userId);
        CommonUtils.logger("Successfully downvoted");
    }

    public void reply(Long id, String reply) throws Exception{
        if (isCurrentUserNull()) return;
        Post post = postIdToPostMap.get(id);
        if(post == null) {
            CommonUtils.logger("post with the specified Id does not exist, " +
                    "please try again with correct post Id!!!");
            return;
        }
        post.getComments().add(new Comment(id, reply));
        CommonUtils.logger("Successfully replied!!!");
    }

    public List<Post> getAllPosts() {
        if (isCurrentUserNull()) return null;
        return postIdToPostMap.size() == 0 ? new ArrayList() : postIdToPostMap.values().stream().toList();
    }

    private boolean isCurrentUserNull() {
        if(currentUser == null) {
            CommonUtils.logger("No user logged in, please login!!!");
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
