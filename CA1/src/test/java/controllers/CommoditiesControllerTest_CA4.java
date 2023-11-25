package controllers;

import  application.BalootApplication;
import  database.Database;
import database.DataParser;

import exceptions.NotExistentCommodity;
import exceptions.NotExistentUser;
import org.junit.jupiter.api.*;

import  java.util.Map;
import  java.util.HashMap;
import  java.util.ArrayList;

import model.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import  org.springframework.boot.test.context.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import  org.springframework.http.*;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BalootApplication.class)
public class CommoditiesControllerTest_CA4 {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private  ObjectMapper objectMapper;

    String commodity_ID;
    private void emptyCommodities() {
        Database.getInstance().setCommodities((new ArrayList<>()));
    }
    @BeforeEach
    public  void setup() throws Exception{
        emptyCommodities();
        DataParser dataParser =  new DataParser(Database.getInstance());
        dataParser.getCommoditiesList();
    }

    // GetCommodities

    @Test
    public void testGetCommodities_empty_checkStatus() throws Exception {
        emptyCommodities();
        mvc.perform(get("/commodities"))
              .andExpect(status().isOk());
    }
    @Test
    public void testGetCommodities_empty_checkContent() throws Exception {
        emptyCommodities();
       mvc.perform(get("/commodities"))
               .andExpect(content().string("[]"));
               //.andDo(print());
    }

    @Test
     public void testGetCommodities_notEmpty_checkStatus() throws Exception{

    }
    @Test
     public void testGetCommodities_notEmpty_checkContent() throws Exception{

    }

    // GetCommodity:
    @Test
    public void testGetCommodity_checkStatus() throws NotExistentCommodity {

    }

    @Test
    public void testGetCommodity_checkContent() throws NotExistentCommodity {

    }

    @Test
    public void testGetCommodityNotFound_checkStatus() throws NotExistentCommodity {

    }

    @Test
    public void testGetCommodityNotFound_checkContent() throws NotExistentCommodity {

    }

    @Test
    public void testRateCommodity_checkStatus() throws NotExistentCommodity {

    }

    @Test
    public void testRateCommodity_checkContent() throws NotExistentCommodity {

    }



    @Test
    void testRateCommodityNotExistentCommodity_checkStatus() throws NotExistentCommodity {

    }

    @Test
    void testRateCommodityNotExistentCommodity_checkContent() throws NotExistentCommodity {

    }



    @ParameterizedTest
    @CsvSource({
            "F102M8, 11",
            "F102M8, -2"
    })
    void testRateCommodityIncorrectRange_checkStatus(String username, String rate) throws NumberFormatException,NotExistentCommodity {

    }

    @ParameterizedTest
    @CsvSource({
            "F102M8, 11",
            "F102M8, -2"
    })
    void testRateCommodityIncorrectRange_checkContent(String username, String rate) throws NumberFormatException,NotExistentCommodity {

    }



    @Test
    public void testAddCommodityComment_checkStatus() throws NotExistentUser {

    }


    @Test
    public void testAddCommodityComment_checkContent() throws NotExistentUser {

    }


    @Test
    public void testAddCommodityCommentUserNotFound_checkStatus() throws NotExistentUser{

    }

    @Test
    public void testAddCommodityCommentUserNotFound_checkContent() throws NotExistentUser{

    }


    @Test
    public void testGetCommodityComment_checkStatus() {

    }
    @Test
    public void testGetCommodityComment_checkContent() {

    }

    @ParameterizedTest
    @ValueSource(strings = { "name", "category", "provider" })
    public void testSearchCommodities_checkStatus(String searchOption) {

    }
    @ParameterizedTest
    @ValueSource(strings = { "name", "category", "provider" })
    public void testSearchCommodities_checkContent(String searchOption) {
    }

    @Test
    public void testSearchCommoditiesByOtherOptions_checkStatus() {

    }
    @Test
    public void testSearchCommoditiesByOtherOptions_checkContent() {

    }

    @Test
    public void testGetSuggestedCommodities_checkStatus() throws NotExistentCommodity {

    }
    @Test
    public void testGetSuggestedCommodities_checkContent() throws NotExistentCommodity {

    }

    @Test
    public void testGetSuggestedCommoditiesWithNotExistedCommodity_checkStatus() throws NotExistentCommodity {

    }
    @Test
    public void testGetSuggestedCommoditiesWithNotExistedCommodity_checkContent() throws NotExistentCommodity {

    }

}
