package model;

import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CommentTest {
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment(1, "user@example.com", "User1", 2, "This is a comment.");
    }
    @Test
    public void testGetCurrentDate() {
        // Get the current date using the getCurrentDate method
        String currentDate = comment.getCurrentDate();

        // Create a SimpleDateFormat with the expected date format
        SimpleDateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the current date with the expected format
        String expectedDate = expectedDateFormat.format(new Date());

        // Assert that the current date matches the expected date
        assertEquals(expectedDate, currentDate);
    }


    /*@ParameterizedTest
    @CsvSource({
            "User2, like, 1, 0, Like count should increase by 1",
            "User3, dislike, 0, 1, Dislike count should increase by 1"
    })
    public void testAddUserVote(String voter, String vote, int expectedLike, int expectedDislike, String assertionMessage) {
        // Add a vote
        comment.addUserVote(voter, vote);

        // Perform a single assertion for this case
        assertEquals(expectedLike, comment.getLike(), assertionMessage);
        assertEquals(expectedDislike, comment.getDislike(), assertionMessage);
    }*/

    @Test
    void testAddUserVoteLike() {
        comment.addUserVote("User2", "like");
        assertEquals(1, comment.getLike());
        assertEquals(0, comment.getDislike());
    }

    @Test
    void testAddUserVoteDislike() {
        comment.addUserVote("User3", "dislike");
        assertEquals(0, comment.getLike());
        assertEquals(1, comment.getDislike());
    }

    @Test
    void testAddMultipleVotes() {
        comment.addUserVote("User2", "like");
        comment.addUserVote("User3", "dislike");
        comment.addUserVote("User4", "like");
        assertEquals(2, comment.getLike());
        assertEquals(1, comment.getDislike());
    }
}
