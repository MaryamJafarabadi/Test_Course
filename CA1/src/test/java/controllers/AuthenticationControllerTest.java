package controllers;

import exceptions.IncorrectPassword;
import exceptions.NotExistentUser;
import exceptions.UsernameAlreadyTaken;
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
import exceptions.UsernameAlreadyTaken;
import model.User;


public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private Baloot baloot;

    Map<String, String> input;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);
        input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("password", "123456789");
    }

    //login
    @Test
    public void testLoginSuccessfully_checkStateOfResponse() {

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testLoginSuccessfully_checkValueOfResponse() {

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    public void testLoginSuccessfully_checkBodyOfResponse() {

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals("login successfully!", response.getBody());
    }

    @Test
    public void testLoginWithNonExistentUser_checkStatusOfResponse() throws NotExistentUser, IncorrectPassword {

        doThrow(new NotExistentUser()).when(baloot).login(input.get("username"), input.get(("password")));

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(HttpStatus.NOT_FOUND , response.getStatusCode());
    }
    @Test
    public void testLoginWithNonExistentUser_checkValuesOfResponse() throws NotExistentUser, IncorrectPassword {

        doThrow(new NotExistentUser()).when(baloot).login(input.get("username"), input.get(("password")));

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(404 , response.getStatusCodeValue());
    }
    @Test
    public void testLoginWithNonExistentUser_checkBodyOfResponse() throws NotExistentUser, IncorrectPassword {

        doThrow(new NotExistentUser()).when(baloot).login(input.get("username"), input.get(("password")));

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals("User does not exist." , response.getBody());
    }

    @Test
    public void testLoginWithIncorrectPassword_checkStatusOfResponse() throws NotExistentUser, IncorrectPassword {

        doThrow(new IncorrectPassword()).when(baloot).login(input.get("username"), input.get(("password")));

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
    @Test
    public void testLoginWithIncorrectPassword_checkValueOfResponse() throws NotExistentUser, IncorrectPassword {

        doThrow(new IncorrectPassword()).when(baloot).login(input.get("username"), input.get(("password")));

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(401, response.getStatusCodeValue());
    }
    @Test
    public void testLoginWithIncorrectPassword_checkBodyOfResponse() throws NotExistentUser, IncorrectPassword {

        doThrow(new IncorrectPassword()).when(baloot).login(input.get("username"), input.get(("password")));

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals("Incorrect password.", response.getBody());
    }
    //signup:
    @Test
    void testSignupSuccess_checkStatusOfResponse()  {
        input.put("address", "Kargar Shomali");
        input.put("birthDate", "01/12/2001");
        input.put("email", "F102M.8@email.com");

        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    void testSignupSuccess_checkValueOfResponse()  {
        input.put("address", "Kargar Shomali");
        input.put("birthDate", "01/12/2001");
        input.put("email", "F102M.8@email.com");

        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals(200, response.getStatusCodeValue());

    }
    @Test
    void testSignupSuccess_checkBodyOfResponse()  {
        input.put("address", "Kargar Shomali");
        input.put("birthDate", "01/12/2001");
        input.put("email", "F102M.8@email.com");

        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals("signup successfully!", response.getBody());

    }

    @Test
    void testSignupUsernameAlreadyTaken_checkStatusOfResponse() throws UsernameAlreadyTaken {
        input.put("address", "Kargar Shomali");
        input.put("birthDate", "01/12/2001");
        input.put("email", "F102M.8@email.com");

        doThrow(new UsernameAlreadyTaken()).when(baloot).addUser(any(User.class));

        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void testSignupUsernameAlreadyTaken_checkValueOfResponse() throws UsernameAlreadyTaken {
        input.put("address", "Kargar Shomali");
        input.put("birthDate", "01/12/2001");
        input.put("email", "F102M.8@email.com");

        doThrow(new UsernameAlreadyTaken()).when(baloot).addUser(any(User.class));

        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals(400, response.getStatusCodeValue());
    }
    @Test
    void testSignupUsernameAlreadyTaken_checkBodyOfResponse() throws UsernameAlreadyTaken {
        input.put("address", "Kargar Shomali");
        input.put("birthDate", "01/12/2001");
        input.put("email", "F102M.8@email.com");

        doThrow(new UsernameAlreadyTaken()).when(baloot).addUser(any(User.class));

        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals("The username is already taken.", response.getBody());
    }
}
