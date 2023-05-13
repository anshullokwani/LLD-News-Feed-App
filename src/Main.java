import model.Comment;
import model.Post;
import service.FeedService;
import service.PlatformService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true) {
            UserService userService = UserService.getInstance();
            PlatformService platformService = PlatformService.getInstance();
            FeedService feedService = FeedService.getInstance();
            System.out.println("Please enter command followed by arguments");
            Scanner scanner = new Scanner(System.in);
            String in = scanner.nextLine();
            if(in.equals("signup")) {
                in = scanner.nextLine();
                String username = scanner.nextLine();
                platformService.signup(username);
            }
            else if(in.equals("login")) {
                in = scanner.nextLine();
                String username = scanner.nextLine();
                platformService.login(username);
            }
            else if(in.equals("post")) {
                in = scanner.nextLine();
                String caption = scanner.nextLine();
                userService.post(caption);
            }
            else if(in.equals("follow")) {
                in = scanner.nextLine();
                String username = scanner.nextLine();
                userService.follow(username);
            }
            else if(in.equals("reply")) {
                in = scanner.nextLine();
                in = scanner.nextLine();
                Long id = Long.parseLong(in);
                in = scanner.nextLine();
                String reply = scanner.nextLine();
                userService.reply(id, reply);
            } else if(in.equals("upvote")) {
                in = scanner.nextLine();
                in = scanner.nextLine();
                Long id = Long.parseLong(in);
                userService.upVote(id);
            } else if(in.equals("downvote")) {
                in = scanner.nextLine();
                in = scanner.nextLine();
                Long id = Long.parseLong(in);
                userService.downVote(id);
            } else if (in.equals("shownewsfeed")) {
                List<Post> feed = feedService.showNewsFeed();
                for(Post post: feed) {
                    System.out.println(post.getId());
                    System.out.println("(" + post.getUpVotes().size() + " upvotes, " + post.getDownVotes().size() + " downvotes)");
                    System.out.println(userService.getUsernameFromUserId(post.getAuthorId()));
                    System.out.println(post.getCaption());
                    System.out.println(post.getTime());
                    System.out.println("******** COMMENTS START ********");
                    for(Comment comment: post.getComments()) {
                        System.out.println(userService.getUsernameFromUserId(comment.getUserId()));
                        System.out.println(comment.getMessage());
                        System.out.println(comment.getTime());
                    }
                    System.out.println("******** COMMENTS END ********");
                }
            } else if(in.equals("exit")) {
                break;
            }
            else {
                System.out.println("Please select a valid command, or type exit to exit the NewsFeedApp");
            }
        }
    }
}
