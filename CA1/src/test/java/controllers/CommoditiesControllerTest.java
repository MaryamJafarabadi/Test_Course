//check the test for testAddCommodityComment because in it we don't really addComment(a method of baloot)
//add test for search option not found!  //done
//parameterized the search options. //done
package controllers;

import model.Comment;
import model.Commodity;
import org.springframework.http.HttpStatus;
import service.Baloot;
import exceptions.NotExistentCommodity;
import exceptions.NotExistentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CommoditiesControllerTest {

    @InjectMocks
    private CommoditiesController commoditiesController;

    @Mock
    private Baloot baloot;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test ////////////get body!
    public void testGetCommodities_checkStatus() {
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(new Commodity());
        when(baloot.getCommodities()).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getCommodities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCommodities_checkValue() {
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(new Commodity());
        when(baloot.getCommodities()).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getCommodities();

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetCommodity_checkStatus() throws NotExistentCommodity {
        Commodity commodity = new Commodity();
        when(baloot.getCommodityById("1")).thenReturn(commodity);

        ResponseEntity<Commodity> response = commoditiesController.getCommodity("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCommodity_checkValue() throws NotExistentCommodity {
        Commodity commodity = new Commodity();
        when(baloot.getCommodityById("1")).thenReturn(commodity);

        ResponseEntity<Commodity> response = commoditiesController.getCommodity("1");

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetCommodity_checkBody() throws NotExistentCommodity {
        Commodity commodity = new Commodity();
        when(baloot.getCommodityById("1")).thenReturn(commodity);

        ResponseEntity<Commodity> response = commoditiesController.getCommodity("1");

        assertEquals(commodity, response.getBody());
    }

    @Test
    public void testGetCommodityNotFound_checkStatus() throws NotExistentCommodity {
        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<Commodity> response = commoditiesController.getCommodity("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetCommodityNotFound_checkValue() throws NotExistentCommodity {
        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<Commodity> response = commoditiesController.getCommodity("1");

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testGetCommodityNotFound_checkBody() throws NotExistentCommodity {
        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<Commodity> response = commoditiesController.getCommodity("1");

        assertEquals(null, response.getBody());/////////////////////////////////
    }

    @Test
    public void testRateCommodity_checkStatus() throws NotExistentCommodity {
        Map<String, String> input = new HashMap<>();
        input.put("rate", "5");
        input.put("username", "F102M8");

        Commodity commodity = new Commodity();
        when(baloot.getCommodityById("1")).thenReturn(commodity);

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRateCommodity_checkValue() throws NotExistentCommodity {
        Map<String, String> input = new HashMap<>();
        input.put("rate", "5");
        input.put("username", "F102M8");

        Commodity commodity = new Commodity();
        when(baloot.getCommodityById("1")).thenReturn(commodity);

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testRateCommodity_checkBody() throws NotExistentCommodity {
        Map<String, String> input = new HashMap<>();
        input.put("rate", "5");
        input.put("username", "F102M8");

        Commodity commodity = new Commodity();
        when(baloot.getCommodityById("1")).thenReturn(commodity);

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals("rate added successfully!", response.getBody());
    }

    @Test
    void testRateCommodityNotExistentCommodity_checkStatus() throws NotExistentCommodity {
        Map<String, String> input = new HashMap<>();
        input.put("rate", "5");
        input.put("username", "F102M8");

        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testRateCommodityNotExistentCommodity_checkValue() throws NotExistentCommodity {
        Map<String, String> input = new HashMap<>();
        input.put("rate", "5");
        input.put("username", "F102M8");

        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testRateCommodityNotExistentCommodity_checkBody() throws NotExistentCommodity {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("rate", "5");


        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals("Commodity does not exist.", response.getBody());
    }

    @Test
    void testRateCommodityIncorrectRange_checkStatus() throws NumberFormatException {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("rate", "1.5");

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testRateCommodityIncorrectRange_checkValue() throws NumberFormatException {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("rate", "1.5");

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testRateCommodityIncorrectRange_checkBody() throws NumberFormatException {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("rate", "1.5");

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals("For input string: \"1.5\"", response.getBody());///??????????????????
    }

    @Test
    public void testAddCommodityComment_checkStatus() throws NotExistentUser {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("comment", "Nice!");

        when(baloot.generateCommentId()).thenReturn(1);
        when(baloot.getUserById("F102M8")).thenReturn(new model.User());

        ResponseEntity<String> response = commoditiesController.addCommodityComment("1", input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddCommodityComment_checkValue() throws NotExistentUser {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("comment", "Nice!");

        when(baloot.generateCommentId()).thenReturn(1);
        when(baloot.getUserById("F102M8")).thenReturn(new model.User());

        ResponseEntity<String> response = commoditiesController.addCommodityComment("1", input);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testAddCommodityComment_checkBody() throws NotExistentUser {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("comment", "Nice!");

        when(baloot.generateCommentId()).thenReturn(1);
        when(baloot.getUserById("F102M8")).thenReturn(new model.User());

        ResponseEntity<String> response = commoditiesController.addCommodityComment("1", input);

        assertEquals("comment added successfully!", response.getBody());
    }

    @Test
    public void testAddCommodityCommentUserNotFound_checkStatus() throws NotExistentUser{
        Map<String, String> input = new HashMap<>();
        input.put("username", "NotExistedUser");
        input.put("comment", "Nice!");

        when(baloot.getUserById("NotExistedUser")).thenThrow(new NotExistentUser());
        ResponseEntity<String> response = commoditiesController.addCommodityComment("1", input);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddCommodityCommentUserNotFound_checkValue() throws NotExistentUser{
        Map<String, String> input = new HashMap<>();
        input.put("username", "NotExistedUser");
        input.put("comment", "Nice!");

        when(baloot.getUserById("NotExistedUser")).thenThrow(new NotExistentUser());
        ResponseEntity<String> response = commoditiesController.addCommodityComment("1", input);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testAddCommodityCommentUserNotFound_checkBody() throws NotExistentUser{
        Map<String, String> input = new HashMap<>();
        input.put("username", "NotExistedUser");
        input.put("comment", "Nice!");

        when(baloot.getUserById("NotExistedUser")).thenThrow(new NotExistentUser());
        ResponseEntity<String> response = commoditiesController.addCommodityComment("1", input);
        assertEquals("User does not exist.", response.getBody());
    }

    @Test //get body!
    public void testGetCommodityComment_checkStatus() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());

        when(baloot.getCommentsForCommodity(1)).thenReturn(comments);

        ResponseEntity<ArrayList<Comment>> response = commoditiesController.getCommodityComment("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCommodityComment_checkValue() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());

        when(baloot.getCommentsForCommodity(1)).thenReturn(comments);

        ResponseEntity<ArrayList<Comment>> response = commoditiesController.getCommodityComment("1");

        assertEquals(200, response.getStatusCodeValue());
    }

    @ParameterizedTest //get body!
    @ValueSource(strings = { "name", "category", "provider" })
    public void testSearchCommodities_checkStatus(String searchOption) {
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(new Commodity());

        Map<String, String> input = new HashMap<>();
        input.put("searchOption", searchOption);
        input.put("searchValue", "commodityValue");

        when(baloot.filterCommoditiesByName("commodityValue")).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @ParameterizedTest
    @ValueSource(strings = { "name", "category", "provider" })
    public void testSearchCommodities_checkValue(String searchOption) {
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(new Commodity());

        Map<String, String> input = new HashMap<>();
        input.put("searchOption", searchOption);
        input.put("searchValue", "commodityValue");

        when(baloot.filterCommoditiesByName("commodityValue")).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test //get body!
    public void testSearchCommoditiesByOtherOptions_checkStatus() {
        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "OtherOptions");
        input.put("searchValue", "commodityOtherOptions");

        when(baloot.filterCommoditiesByName("commodityOtherOptions")).thenReturn(new ArrayList<>());

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSearchCommoditiesByOtherOptions_checkValue() {
        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "OtherOptions");
        input.put("searchValue", "commodityOtherOptions");

        when(baloot.filterCommoditiesByName("commodityOtherOptions")).thenReturn(new ArrayList<>());

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test //get body!
    public void testGetSuggestedCommodities_checkStatus() throws NotExistentCommodity {
        Commodity commodity = new Commodity();
        ArrayList<Commodity> suggestedCommodities = new ArrayList<>();
        suggestedCommodities.add(new Commodity());

        when(baloot.getCommodityById("1")).thenReturn(commodity);
        when(baloot.suggestSimilarCommodities(commodity)).thenReturn(suggestedCommodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getSuggestedCommodities("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetSuggestedCommodities_checkValue() throws NotExistentCommodity {
        Commodity commodity = new Commodity();
        ArrayList<Commodity> suggestedCommodities = new ArrayList<>();
        suggestedCommodities.add(new Commodity());

        when(baloot.getCommodityById("1")).thenReturn(commodity);
        when(baloot.suggestSimilarCommodities(commodity)).thenReturn(suggestedCommodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getSuggestedCommodities("1");

        assertEquals(200, response.getStatusCodeValue());
    }


    @Test //get body!
    public void testGetSuggestedCommoditiesWithNotExistedCommodity_checkStatus() throws NotExistentCommodity {
        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getSuggestedCommodities("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetSuggestedCommoditiesWithNotExistedCommodity_checkValue() throws NotExistentCommodity {
        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getSuggestedCommodities("1");

        assertEquals(404, response.getStatusCodeValue());
    }

}
