package utils;

import model.Post;

import java.util.Comparator;
import java.util.Date;

public class FeedSortComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Post post1 = (Post) o1;
        Post post2 = (Post) o2;
        int score1 = post1.getUpVotes().size() - post1.getDownVotes().size();
        int score2 = post2.getUpVotes().size() - post2.getDownVotes().size();
        if(score1 == score2) {
            int commentsCount1 = post1.getComments().size();
            int commentsCount2 = post2.getComments().size();
            if(commentsCount1 == commentsCount2) {
                Date time1 = post1.getComments().size() > 0 ?
                        post1.getComments().get(post1.getComments().size() - 1).getTime() : post1.getTime();
                Date time2 = post2.getComments().size() > 0 ?
                        post2.getComments().get(post2.getComments().size() - 1).getTime() : post2.getTime();
                return time1.compareTo(time2);
            } else {
                return commentsCount1 > commentsCount2 ? 1 : -1;
            }
        } else {
            return score1 > score2 ? 1 : -1;
        }
    }
}
