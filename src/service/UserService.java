package service;

import database.DAO;
import model.Post;
import utils.IdGenerator;

public class UserService {
    public static UserService userService;
    private static DAO dao;
    public static UserService getInstance() {
        if(userService == null) {
            userService = new UserService();
        }
        return userService;
    }
    UserService() {
        dao = DAO.getInstance();
    }

    public void post(String caption) {
        try {
            Long id = IdGenerator.getPostId();
            Post newPost = new Post(id, caption);
            dao.post(newPost);
        } catch (Exception e) {
            System.out.println("Post Failed!!!");
        }
    }

    public void follow(String username) {
        try {
            dao.follow(username);
        } catch (Exception e) {
            System.out.println("Follow Failed!!!");
        }
    }

    public void upVote(Long id) {
        try {
            dao.upVote(id);
        } catch (Exception e) {
            System.out.println("upVote failed!!!");
        }
    }

    public void downVote(Long id) {
        try {
            dao.downVote(id);
        } catch (Exception e) {
            System.out.println("downVote Failed!!!");
        }
    }

    public void reply(Long id, String reply) {
        try {
            dao.reply(id, reply);
        } catch (Exception e) {
            System.out.println("reply Failed!!!");
        }
    }

    public String getUsernameFromUserId(Long id) {
        return dao.getUsernameFromUserId(id);
    }

}
