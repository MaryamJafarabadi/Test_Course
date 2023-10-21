package model;

import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {

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
}
