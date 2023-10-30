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
    public void likeCommentSuccess_checkStatusOfResponse() throws Exception {
        Comment comment = mock(Comment.class);
        when(baloot.getCommentById(anyInt())).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.likeComment("1", input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void likeCommentSuccess_checkValueOfResponse() throws Exception {
        Comment comment = mock(Comment.class);
        when(baloot.getCommentById(anyInt())).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.likeComment("1", input);

        assertEquals(200, response.getStatusCodeValue());

    }
    @Test
    public void likeCommentSuccess_checkBodyOfResponse() throws Exception {
        Comment comment = mock(Comment.class);
        when(baloot.getCommentById(anyInt())).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.likeComment("1", input);

        assertEquals("The comment was successfully liked!", response.getBody());

    }


    @Test
    public void likeCommentNotExist_checkStatusOfResponse() throws Exception {
        when(baloot.getCommentById(anyInt())).thenThrow(new exceptions.NotExistentComment());

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.likeComment("1", input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
    @Test
    public void likeCommentNotExist_checkValueOfResponse() throws Exception {
        when(baloot.getCommentById(anyInt())).thenThrow(new exceptions.NotExistentComment());

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.likeComment("1", input);

        assertEquals(404, response.getStatusCodeValue());

    }
    @Test
    public void likeCommentNotExist_checkBodyOfResponse() throws Exception {
        when(baloot.getCommentById(anyInt())).thenThrow(new exceptions.NotExistentComment());

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.likeComment("1", input);

        assertEquals("Comment does not exist.", response.getBody());

    }
    //dislikeComment
    @Test
    public void dislikeCommentSuccess_checkStatusOfResponse() throws Exception {
        Comment comment = mock(Comment.class);
        when(baloot.getCommentById(anyInt())).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.dislikeComment("1", input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void dislikeCommentSuccess_checkValueOfResponse() throws Exception {
        Comment comment = mock(Comment.class);
        when(baloot.getCommentById(anyInt())).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.dislikeComment("1", input);

        assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    public void dislikeCommentSuccess_checkBodyOfResponse() throws Exception {
        Comment comment = mock(Comment.class);
        when(baloot.getCommentById(anyInt())).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.dislikeComment("1", input);

        assertEquals("The comment was successfully disliked!", response.getBody());
    }
    @Test
    public void dislikeCommentNotExist_checkStatusOfResponse() throws Exception {
        when(baloot.getCommentById(anyInt())).thenThrow(new exceptions.NotExistentComment());

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.dislikeComment("1", input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void dislikeCommentNotExist_checkValueOfResponse() throws Exception {
        when(baloot.getCommentById(anyInt())).thenThrow(new exceptions.NotExistentComment());

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.dislikeComment("1", input);

        assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    public void dislikeCommentNotExist_checkBodyOfResponse() throws Exception {
        when(baloot.getCommentById(anyInt())).thenThrow(new exceptions.NotExistentComment());

        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");

        ResponseEntity<String> response = commentController.dislikeComment("1", input);

        assertEquals("Comment does not exist.", response.getBody());
    }
}
