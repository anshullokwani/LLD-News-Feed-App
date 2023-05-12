package utils;

public class IdGenerator {
    private static long userId = 0;
    private static long postId = 0;

    public static long getUserId() {
        return ++userId;
    }

    public static long getPostId() {
        return ++postId;
    }
}
