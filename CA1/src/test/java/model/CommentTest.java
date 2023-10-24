package model;

import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment(1, "user@example.com", "User1", 2, "This is a comment.");
    }
    @Test
    public void testGetCurrentDate() {
        // Create a Comment object
        Comment comment = new Comment(1, "user@example.com", "User", 123, "This is a test comment");

        // Get the current date using the getCurrentDate method
        String currentDate = comment.getCurrentDate();

        // Create a SimpleDateFormat with the expected date format
        SimpleDateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the current date with the expected format
        String expectedDate = expectedDateFormat.format(new Date());

        // Assert that the current date matches the expected date
        assertEquals(expectedDate, currentDate);
    }


    @Test
    public void testAddUserVote() {
        // Initial state of like and dislike should be 0
        assertEquals(0, comment.getLike());
        assertEquals(0, comment.getDislike());

        // Add a "like" vote
        comment.addUserVote("User2", "like");
        assertEquals(1, comment.getLike()); // Like count should increase by 1
        assertEquals(0, comment.getDislike()); // Dislike count should remain 0

        // Add a "dislike" vote
        comment.addUserVote("User3", "dislike");
        assertEquals(1, comment.getLike()); // Like count should remain 1
        assertEquals(1, comment.getDislike()); // Dislike count should increase by 1

        // Add another "like" vote
        comment.addUserVote("User4", "like");
        assertEquals(2, comment.getLike()); // Like count should increase by 1
        assertEquals(1, comment.getDislike()); // Dislike count should remain 1
    }

}
