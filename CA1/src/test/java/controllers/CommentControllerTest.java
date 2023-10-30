package controllers;

import model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import service.Baloot;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;

public class CommentControllerTest {

    @InjectMocks
    CommentController commentController;

    @Mock
    Baloot baloot;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    //likeComment
    @Test
    public void likeCommentSuccess() throws Exception {
        Comment comment = mock(Comment.class);
        when(baloot.getCommentById(anyInt())).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.likeComment("1", input);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void likeCommentNotExist() throws Exception {
        when(baloot.getCommentById(anyInt())).thenThrow(new exceptions.NotExistentComment());

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.likeComment("1", input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    //dislikeComment
    @Test
    public void dislikeCommentSuccess() throws Exception {
        Comment comment = mock(Comment.class);
        when(baloot.getCommentById(anyInt())).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.dislikeComment("1", input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void dislikeCommentNotExist() throws Exception {
        when(baloot.getCommentById(anyInt())).thenThrow(new exceptions.NotExistentComment());

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.dislikeComment("1", input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
