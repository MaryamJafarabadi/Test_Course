package controllers;

import exceptions.IncorrectPassword;
import exceptions.NotExistentUser;
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

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    //login
    @Test
    public void ResponseEntity_testLoginSuccessfully() {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("password", "123456789");

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void ResponseEntity_testLoginWithNonExistentUser() throws NotExistentUser, IncorrectPassword {
        Map<String, String> input = new HashMap<>();
        input.put("username", "notF102M8");
        input.put("password", "123456789");

        doThrow(NotExistentUser.class).when(baloot).login(input.get("username"), input.get(("password")));

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(HttpStatus.NOT_FOUND , response.getStatusCode());
    }

    @Test
    public void ResponseEntity_testLoginWithIncorrectPassword() throws NotExistentUser, IncorrectPassword {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("password", "123123123");

        doThrow(IncorrectPassword.class).when(baloot).login(input.get("username"), input.get(("password")));

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    //signup:
    @Test
    void testSignupSuccess()  {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M");
        input.put("password", "123456789");
        input.put("address", "Kargar Shomali");
        input.put("birthDate", "01/12/2001");
        input.put("email", "F102M.8@email.com");

        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void testSignupUsernameAlreadyTaken() throws UsernameAlreadyTaken {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M");
        input.put("password", "123456789");
        input.put("address", "Kargar Shomali");
        input.put("birthDate", "01/12/2001");
        input.put("email", "F102M.8@email.com");

        doThrow(UsernameAlreadyTaken.class).when(baloot).addUser(any(User.class));

        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
