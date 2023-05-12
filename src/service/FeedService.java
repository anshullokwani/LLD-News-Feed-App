package service;

import database.DAO;
import model.Post;
import model.User;
import utils.FeedSortComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeedService {
    private static FeedService feedService;
    private static DAO dao;

    public static FeedService getInstance() {
        if(feedService == null) {
            feedService = new FeedService();
        }
        return feedService;
    }

    FeedService() {
        dao = DAO.getInstance();
    }

    public List<Post> showNewsFeed() {
        List<Post> allPosts = dao.getAllPosts();
        if(allPosts == null) return null;
        List<Post> sortedPosts = new ArrayList<>();
        List<Post> followedUserPosts = new ArrayList<>();
        List<Post> notFollowedUserPosts = new ArrayList<>();
        filterPosts(allPosts, followedUserPosts, notFollowedUserPosts);
        Collections.sort(followedUserPosts, new FeedSortComparator());
        Collections.sort(notFollowedUserPosts, new FeedSortComparator());
        sortedPosts.addAll(followedUserPosts);
        sortedPosts.addAll(notFollowedUserPosts);
        return allPosts;
    }

    private void filterPosts(List<Post> allPosts, List<Post> followedUserPosts,
                             List<Post> notFollowedUserPosts) {
        User currentUser = dao.getCurrentUser();
        List<Long> following = currentUser.getFollowing();
        for(Post post: allPosts) {
            if(following.contains(post.getAuthorId())) {
                followedUserPosts.add(post);
            } else {
                notFollowedUserPosts.add(post);
            }
        }
    }

}
