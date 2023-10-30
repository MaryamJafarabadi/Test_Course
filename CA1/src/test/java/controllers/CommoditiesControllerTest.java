//check the test for testAddCommodityComment because in it we don't really addComment(a method of baloot)
//add test for search option not found!  //done
//parameterized the search options.
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

public class CommoditiesControllerTest {

    @InjectMocks
    private CommoditiesController commoditiesController;

    @Mock
    private Baloot baloot;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCommodities() {
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(new Commodity());
        when(baloot.getCommodities()).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getCommodities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetCommodity() throws NotExistentCommodity {
        Commodity commodity = new Commodity();
        when(baloot.getCommodityById("1")).thenReturn(commodity);

        ResponseEntity<Commodity> response = commoditiesController.getCommodity("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
       // assertEquals(commodity, response.getBody());
    }

    @Test
    public void testGetCommodityNotFound() throws NotExistentCommodity {
        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<Commodity> response = commoditiesController.getCommodity("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRateCommodity() throws NotExistentCommodity {
        Map<String, String> input = new HashMap<>();
        input.put("rate", "5");
        input.put("username", "F102M8");

        Commodity commodity = new Commodity();
        when(baloot.getCommodityById("1")).thenReturn(commodity);

        ResponseEntity<String> response = commoditiesController.rateCommodity("1", input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals("rate added successfully!", response.getBody());
    }

    //add more tests for not successful paths................

    @Test
    public void testAddCommodityComment() throws NotExistentUser {
        Map<String, String> input = new HashMap<>();
        input.put("username", "F102M8");
        input.put("comment", "Nice!");

        when(baloot.generateCommentId()).thenReturn(1);
        when(baloot.getUserById("F102M8")).thenReturn(new model.User());

        ResponseEntity<String> response = commoditiesController.addCommodityComment("1", input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals("comment added successfully!", response.getBody());
    }

    @Test
    public void testAddCommodityCommentUserNotFound() throws NotExistentUser{
        Map<String, String> input = new HashMap<>();
        input.put("username", "NotExistedUser");
        input.put("comment", "Nice!");

        when(baloot.getUserById("NotExistedUser")).thenThrow(new NotExistentUser());
        ResponseEntity<String> response = commoditiesController.addCommodityComment("1", input);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetCommodityComment() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());

        when(baloot.getCommentsForCommodity(1)).thenReturn(comments);

        ResponseEntity<ArrayList<Comment>> response = commoditiesController.getCommodityComment("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testSearchCommoditiesByName() {
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(new Commodity());

        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "name");
        input.put("searchValue", "commodityName");

        when(baloot.filterCommoditiesByName("commodityName")).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testSearchCommoditiesByCategory() {
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(new Commodity());

        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "category");
        input.put("searchValue", "commodityCategory");

        when(baloot.filterCommoditiesByName("commodityCategory")).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSearchCommoditiesByProvider() {
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(new Commodity());

        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "provider");
        input.put("searchValue", "commodityProvider");

        when(baloot.filterCommoditiesByName("commodityProvider")).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSearchCommoditiesByOtherOptions() {
        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "OtherOptions");
        input.put("searchValue", "commodityOtherOptions");

        when(baloot.filterCommoditiesByName("commodityOtherOptions")).thenReturn(new ArrayList<>());

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // parameterized

    @Test
    public void testGetSuggestedCommodities() throws NotExistentCommodity {
        Commodity commodity = new Commodity();
        ArrayList<Commodity> suggestedCommodities = new ArrayList<>();
        suggestedCommodities.add(new Commodity());

        when(baloot.getCommodityById("1")).thenReturn(commodity);
        when(baloot.suggestSimilarCommodities(commodity)).thenReturn(suggestedCommodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getSuggestedCommodities("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


    @Test
    public void testGetSuggestedCommoditiesWithNotExistedCommodity() throws NotExistentCommodity {
        when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getSuggestedCommodities("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
