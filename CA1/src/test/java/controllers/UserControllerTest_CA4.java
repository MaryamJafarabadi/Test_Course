package controllers;

import application.BalootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.NotExistentUser;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.Baloot;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

//@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = BalootApplication.class)
class UserControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Baloot baloot;

    @Autowired
    private UserController userController;

    @BeforeEach
    public void setUp()
    {
        userController.setBaloot(baloot);
    }
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testGetUserSuccess_checkStatus() throws Exception {
        User user = new User("username", "password", "email", "birthDate", "address");
        when(baloot.getUserById(anyString())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", "username")
                    .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
    }
    @Test
    void testGetUserSuccess_checkContent() throws Exception {
        User user = new User("username", "password", "email", "birthDate", "address");
        when(baloot.getUserById(anyString())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", "username")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"username\":\"username\",\"password\":\"password\",\"email\":\"email\",\"birthDate\":\"birthDate\",\"address\":\"address\",\"credit\":0.0,\"commoditiesRates\":{},\"buyList\":{},\"purchasedList\":{}}"));

    }
    @Test
    void testGetUserSuccess_checkFields() throws Exception {
        User user = new User("username", "password", "email", "birthDate", "address");
        when(baloot.getUserById(anyString())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", "username")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(user.getPassword()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate").value(user.getBirthDate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(user.getAddress()));
    }

    @Test
    void testGetUserNotFound_checkStatus() throws Exception {
        String userId = "nonExistentUserId";
        when(baloot.getUserById(userId)).thenThrow(new NotExistentUser());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void testGetUserNotFound_checkContent() throws Exception {
        String userId = "nonExistentUserId";
        when(baloot.getUserById(userId)).thenThrow(new NotExistentUser());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(""));
    }

    @Test
    void testAddCreditSuccess_checkStatus() throws Exception {
        String userId = "testUserId";
        float creditToAdd = 100;

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", String.valueOf(creditToAdd));

        when(baloot.getUserById(anyString())).thenReturn(new User(userId, "password", "email", "birthDate", "address"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testAddCreditSuccess_checkContent() throws Exception {
        String userId = "testUserId";
        float creditToAdd = 100;

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", String.valueOf(creditToAdd));

        when(baloot.getUserById(anyString())).thenReturn(new User(userId, "password", "email", "birthDate", "address"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.content().string("credit added successfully!"));
    }
    @Test
    void testAddCreditInvalidCreditRange_checkStatus() throws Exception {
        String userId = "testUserId";
        float creditToAdd = -50.0f;

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", String.valueOf(creditToAdd));

        when(baloot.getUserById(anyString())).thenReturn(new User(userId, "password", "email", "birthDate", "address"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testAddCreditInvalidCreditRange_checkContent() throws Exception {
        String userId = "testUserId";
        float creditToAdd = -50.0f;

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", String.valueOf(creditToAdd));

        when(baloot.getUserById(anyString())).thenReturn(new User(userId, "password", "email", "birthDate", "address"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.content().string("Credit value must be a positive float"));
    }

    @Test
    void testAddCreditNotExistentUser_checkStatus() throws Exception {
        String userId = "nonExistentUserId";
        float creditToAdd = 100.0f;

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", String.valueOf(creditToAdd));

        when(baloot.getUserById(anyString())).thenThrow(new NotExistentUser());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void testAddCreditNotExistentUser_checkContent() throws Exception {
        String userId = "nonExistentUserId";
        float creditToAdd = 100.0f;

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", String.valueOf(creditToAdd));

        when(baloot.getUserById(anyString())).thenThrow(new NotExistentUser());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.content().string("User does not exist."));
    }


    @Test
    void testAddCreditInvalidJsonFormat_checkStatus() throws Exception {
        String userId = "testUserId";
        String invalidJson = "{invalidJson}";

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testAddCreditInvalidJsonFormat_checkContent() throws Exception {
        String userId = "testUserId";
        String invalidJson = "{invalidJson}";

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void testAddCreditInvalidCreditFormat_checkStatus() throws Exception {
        String userId = "testUserId";
        String invalidCredit = "invalidCredit";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", invalidCredit);

        when(baloot.getUserById(anyString())).thenReturn(new User(userId, "password", "email", "birthDate", "address"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testAddCreditInvalidCreditFormat_checkContent() throws Exception {
        String userId = "testUserId";
        String invalidCredit = "invalidCredit";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", invalidCredit);

        when(baloot.getUserById(anyString())).thenReturn(new User(userId, "password", "email", "birthDate", "address"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestBody)))
                .andExpect(MockMvcResultMatchers.content().string("Please enter a valid number for the credit amount."));
    }

}
