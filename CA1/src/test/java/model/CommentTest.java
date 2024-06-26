package model;

import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class CommentTest {
    private Comment comment;
    int PreCountLikes;
    int PreCountDislike;
    @BeforeEach
    public void setUp() {
        comment = new Comment(1, "userGmail@gmail.com", "User1", 2, "This is a comment=)");
        PreCountLikes = comment.getLike();
        PreCountDislike = comment.getDislike();
    }
    @Test
    public void testGetCurrentDate() {
        String currentDate = comment.getCurrentDate();
        SimpleDateFormat expectedDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expectedDate = expectedDateFormat.format(new Date());
        assertEquals(expectedDate, currentDate);
    }

    @Test
    void testAddUserVoteLike() {
        comment.addUserVote("User2", "like");
        assertEquals(PreCountLikes + 1, comment.getLike());
        assertEquals(PreCountDislike, comment.getDislike());
    }

    @Test
    void testAddUserVoteDislike() {
        comment.addUserVote("User3", "dislike");
        assertEquals(PreCountLikes, comment.getLike());
        assertEquals(PreCountDislike + 1, comment.getDislike());
    }

    @Test
    void testAddMultipleVotesByDifferentUsers() {
        comment.addUserVote("User2", "like");
        comment.addUserVote("User3", "dislike");
        comment.addUserVote("User4", "like");
        assertEquals(PreCountLikes + 2, comment.getLike());
        assertEquals(PreCountDislike + 1, comment.getDislike());
    }

    @Test
    void testChangeUserVoteLikeToDislike() {
        comment.addUserVote("user1", "like");
        comment.addUserVote("user1", "dislike");
        assertEquals(PreCountLikes, comment.getLike());
        assertEquals(PreCountDislike + 1, comment.getDislike());
    }

    @Test
    void testChangeUserVoteDislikeToLike() {
        comment.addUserVote("user1", "dislike");
        comment.addUserVote("user1", "like");
        assertEquals(PreCountLikes + 1, comment.getLike());
        assertEquals(PreCountDislike, comment.getDislike());
    }
    @Test
    void testChangeUserVoteAfterSomeAddingVotesByOthers() {
        comment.addUserVote("user1", "like");
        comment.addUserVote("user2", "dislike");
        comment.addUserVote("user3", "dislike");
        comment.addUserVote("user1", "dislike");
        assertEquals(PreCountLikes, comment.getLike());
        assertEquals(PreCountDislike + 3, comment.getDislike());
    }
    @Test
    void testOneUserAddMultipleSameLikeVote() {
        comment.addUserVote("user1", "like");
        comment.addUserVote("user1", "like");
        assertEquals(PreCountLikes + 1, comment.getLike());
        assertEquals(PreCountDislike, comment.getDislike());
    }

    @Test
    void testOneUserAddMultipleSameDislikeVote() {
        comment.addUserVote("user1", "dislike");
        comment.addUserVote("user1", "dislike");
        assertEquals(PreCountLikes, comment.getLike());
        assertEquals(PreCountDislike + 1, comment.getDislike());
    }

    @Test
    void testOneUserAddMultipleSameVotesAndFinallyChangeHisVote() {
        comment.addUserVote("user1", "like");
        comment.addUserVote("user1", "like");
        comment.addUserVote("user1", "like");
        comment.addUserVote("user1", "dislike");
        assertEquals(PreCountLikes, comment.getLike());
        assertEquals(PreCountDislike + 1, comment.getDislike());
    }


    @Test
    void testAddVoteInvalid() {
        assertThrows(IllegalArgumentException.class, () -> comment.addUserVote("user1", "likee"));
        assertEquals(PreCountDislike, comment.getDislike());
        assertEquals(PreCountLikes, comment.getLike());
    }
}
