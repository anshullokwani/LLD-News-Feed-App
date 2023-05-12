package service;

import database.DAO;
import model.Post;
import model.User;
import utils.IdGenerator;

import java.util.List;

public class PlatformService {
    private static PlatformService platformService;
    private static DAO dao;
    public static PlatformService getInstance() {
        if(platformService == null) {
            platformService = new PlatformService();
        }
        return platformService;
    }
    PlatformService() {
        dao = DAO.getInstance();
    }

    public List<Post> login(String userName) {
        try {
            dao.login(userName);
        } catch (Exception e) {
            System.out.println("Login failed!!!");
            return null;
        }
        FeedService feedService = FeedService.getInstance();
        return feedService.showNewsFeed();
    }

    public void signup(String userName) {
        try {
            Long id = IdGenerator.getUserId();
            User user = new User(id, userName);
            dao.signup(user);
        } catch (Exception e) {
            System.out.println("Signup Failed!!!");
        }
    }

}
