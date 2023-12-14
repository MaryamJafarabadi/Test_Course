package controllers;


import application.BalootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Database;
import exceptions.NotExistentCommodity;
import exceptions.NotExistentUser;
import model.Comment;
import model.Commodity;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import service.Baloot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(classes = BalootApplication.class)
class CommoditiesControllerTest_CA4 {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Baloot baloot;

    private void emptyCommodities() {
        Database.getInstance().setCommodities((new ArrayList<>()));
    }
    @Autowired
    private CommoditiesController commoditiesController;
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void setUp()
    {
        commoditiesController.setBaloot(baloot);
    }

    @Test
    void testGetCommodity_checkStatus() throws Exception {
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setName("commodity_name");
        commodity.setProviderId("provider_id");
        commodity.setPrice(1);
        ArrayList<String> x = new ArrayList<String>();
        commodity.setCategories(x);
        commodity.setRating(1);
        commodity.setInStock(2);
        commodity.setImage("commodity_image");

        Mockito.when(baloot.getCommodityById("1")).thenReturn(commodity);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testGetCommodity_checkContent() throws Exception {
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setName("commodity_name");
        commodity.setProviderId("provider_id");
        commodity.setPrice(1);
        ArrayList<String> x = new ArrayList<String>();
        commodity.setCategories(x);
        commodity.setRating(1);
        commodity.setInStock(2);
        commodity.setImage("commodity_image");

        Mockito.when(baloot.getCommodityById("1")).thenReturn(commodity);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/1"))
                .andExpect(content().string("{\"id\":\"commodity_id\",\"name\":\"commodity_name\",\"providerId\":\"provider_id\",\"price\":1,\"categories\":[],\"rating\":1.0,\"inStock\":2,\"image\":\"commodity_image\",\"userRate\":{},\"initRate\":0.0}"));
    }
    @Test
    void testGetCommodity_checkFields() throws Exception {
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setName("commodity_name");
        commodity.setProviderId("provider_id");
        commodity.setPrice(1);
        ArrayList<String> x = new ArrayList<String>();
        commodity.setCategories(x);
        commodity.setRating(1);
        commodity.setInStock(2);
        commodity.setImage("commodity_image");

        Mockito.when(baloot.getCommodityById("1")).thenReturn(commodity);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(commodity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(commodity.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.providerId").value(commodity.getProviderId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(commodity.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categories").value(commodity.getCategories()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(commodity.getRating()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inStock").value(commodity.getInStock()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value(commodity.getImage()));
    }

    @Test
    public void testGetCommodityNotFound_checkStatus() throws Exception{
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");

        Mockito.when(baloot.getCommodityById("commodity_id")).thenThrow(new NotExistentCommodity());
        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/commodity_id"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void testGetCommodityNotFound_checkContent() throws Exception{
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");

        Mockito.when(baloot.getCommodityById("commodity_id")).thenThrow(new NotExistentCommodity());
        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/commodity_id"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    public void testGetCommodities_empty_checkStatus() throws Exception {
        ArrayList<Commodity> commodities = new ArrayList<Commodity>();

        when(baloot.getCommodities()).thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetCommodities_empty_checkContent() throws Exception {
        ArrayList<Commodity> commodities = new ArrayList<Commodity>();

        when(baloot.getCommodities()).thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetCommodities_notEmpty_checkStatus() throws Exception {
        ArrayList<Commodity> commodities = new ArrayList<Commodity>();
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setName("commodity_name");
        commodity.setProviderId("provider_id");
        commodity.setPrice(1);
        ArrayList x = new ArrayList();
        commodity.setCategories(x);
        commodity.setRating(1);
        commodity.setInStock(2);
        commodity.setImage("commodity_image");
        commodities.add(commodity);

        when(baloot.getCommodities()).thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetCommodities_notEmpty_checkContent() throws Exception {
        ArrayList<Commodity> commodities = new ArrayList<Commodity>();
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setName("commodity_name");
        commodity.setProviderId("provider_id");
        commodity.setPrice(1);
        ArrayList x = new ArrayList();
        commodity.setCategories(x);
        commodity.setRating(1);
        commodity.setInStock(2);
        commodity.setImage("commodity_image");
        commodities.add(commodity);

        when(baloot.getCommodities()).thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":\"commodity_id\",\"name\":\"commodity_name\",\"providerId\":\"provider_id\",\"price\":1,\"categories\":[],\"rating\":1.0,\"inStock\":2,\"image\":\"commodity_image\",\"userRate\":{},\"initRate\":0.0}]"));
    }

    @Test
    void testRateCommodity_checkStatus() throws Exception {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("username", "testUser");
        requestBody.put("rate", "5");

        Mockito.when(baloot.getCommodityById("1")).thenReturn(new Commodity(/* provide necessary details */));

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testRateCommodity_checkContent() throws Exception {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("username", "testUser");
        requestBody.put("rate", "5");

        Mockito.when(baloot.getCommodityById("1")).thenReturn(new Commodity(/* provide necessary details */));

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.content().string("rate added successfully!"));
    }

    @Test
    void testRateCommodityNotExistentCommodity_checkStatus() throws Exception {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("rate", "3");
        requestBody.put("username", "testUser");

        Mockito.when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void testRateCommodityNotExistentCommodity_checkContent() throws Exception {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("rate", "3");
        requestBody.put("username", "testUser");

        Mockito.when(baloot.getCommodityById("1")).thenThrow(new NotExistentCommodity());

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.content().string("Commodity does not exist."));
    }

    @Test
    void testRateCommodityInvalidRate_checkStatus() throws Exception {

        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("rate", "invalidRate");
        requestBody.put("username", "testUser");

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testRateCommodityInvalidRate_checkContent() throws Exception {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("rate", "invalidRate");
        requestBody.put("username", "testUser");

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("For input string: \"invalidRate\""));
    }

    @Test
    void testAddCommodityCommentSuccess_checkStatus() throws Exception {
        String commodityId = "1";
        String username = "testUser";
        String commentText = "test comment";

        Mockito.when(baloot.getUserById(username))
                .thenReturn(new User(username, "password", "email", "2001-12-01", "Test Address"));

        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("username", username);
        requestBody.put("comment", commentText);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/{id}/comment", commodityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testAddCommodityCommentSuccess_checkContent() throws Exception {
        String commodityId = "1";
        String username = "testUser";
        String commentText = "test comment";

        Mockito.when(baloot.getUserById(username))
                .thenReturn(new User(username, "password", "email", "2001-12-01", "Test Address"));

        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("username", username);
        requestBody.put("comment", commentText);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/{id}/comment", commodityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("comment added successfully!"));
    }

    @Test
    void testAddCommodityCommentUserNotFound_checkStatus() throws Exception {
        String commodityId = "1";
        String username = "nonexistentUser";
        String commentText = "test comment";

        Mockito.when(baloot.getUserById(username)).thenThrow(new NotExistentUser());

        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("username", username);
        requestBody.put("comment", commentText);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/{id}/comment", commodityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void testAddCommodityCommentUserNotFound_checkContent() throws Exception {
        String commodityId = "1";
        String username = "nonexistentUser";
        String commentText = "test comment";

        Mockito.when(baloot.getUserById(username)).thenThrow(new NotExistentUser());

        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("username", username);
        requestBody.put("comment", commentText);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/{id}/comment", commodityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(content().string("User does not exist."));
    }
    @Test
    void testGetCommodityComment_checkStatus() throws Exception {
        String commodityId = "1";
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Comment comment = new Comment(1, "email","username", 2, "text");
        comment.setDate("date");
        comments.add(comment);

        Mockito.when(baloot.getCommentsForCommodity(Integer.parseInt(commodityId)))
                .thenReturn(comments);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/{id}/comment", commodityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //because of date we can't test the content.
    }
    @Test
    void testGetCommodityComment_checkSize() throws Exception {
        String commodityId = "1";
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Comment comment = new Comment(1, "email","username", 2, "text");
        comment.setDate("date");
        comments.add(comment);

        Mockito.when(baloot.getCommentsForCommodity(Integer.parseInt(commodityId)))
                .thenReturn(comments);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/{id}/comment", commodityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
        //because of date we can't test the content.
    }
    @Test
    void testSearchCommoditiesByName_checkStatus() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "name");
        input.put("searchValue", "commodity_name");
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setName("commodity_name");
        commodity.setPrice(1);
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByName("commodity_name"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchCommoditiesByName_checkContent() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "name");
        input.put("searchValue", "commodity_name");
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setName("commodity_name");
        commodity.setPrice(1);
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByName("commodity_name"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":\"commodity_id\",\"name\":\"commodity_name\",\"providerId\":null,\"price\":1,\"categories\":[],\"rating\":0.0,\"inStock\":0,\"image\":null,\"userRate\":{},\"initRate\":0.0}]"));
    }

    @Test
    void testSearchCommoditiesByName_checkField() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "name");
        input.put("searchValue", "commodity_name");
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setName("commodity_name");
        commodity.setPrice(1);
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByName("commodity_name"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(commodity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(commodity.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(commodity.getPrice()));
    }

    @Test
    void testSearchCommoditiesByProvider_checkStatus() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "provider");
        input.put("searchValue", "commodity_provider");
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setProviderId("commodity_provider");
        commodity.setPrice(1);
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByProviderName("commodity_provider"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }
    @Test
    void testSearchCommoditiesByProvider_checkContent() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "provider");
        input.put("searchValue", "commodity_provider");
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setProviderId("commodity_provider");
        commodity.setPrice(1);
        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByProviderName("commodity_provider"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":\"commodity_id\",\"name\":null,\"providerId\":\"commodity_provider\",\"price\":1,\"categories\":[],\"rating\":0.0,\"inStock\":0,\"image\":null,\"userRate\":{},\"initRate\":0.0}]"));
    }
    @Test
    void testSearchCommoditiesByProvider_checkFields() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "provider");
        input.put("searchValue", "commodity_provider");

        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        commodity.setProviderId("commodity_provider");
        commodity.setPrice(1);

        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByProviderName("commodity_provider"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(commodity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].providerId").value(commodity.getProviderId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(commodity.getPrice()));
    }
    @Test
    void testSearchCommoditiesByCategory_checkStatus() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "category");
        input.put("searchValue", "commodity_category");

        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        ArrayList<String> categories = new ArrayList();
        categories.add("commodity_category");
        commodity.setCategories(categories);
        commodity.setPrice(1);

        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByCategory("commodity_category"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchCommoditiesByCategory_getContent() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "category");
        input.put("searchValue", "commodity_category");

        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        ArrayList<String> categories = new ArrayList();
        categories.add("commodity_category");
        commodity.setCategories(categories);
        commodity.setPrice(1);

        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByCategory("commodity_category"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":\"commodity_id\",\"name\":null,\"providerId\":null,\"price\":1,\"categories\":[\"commodity_category\"],\"rating\":0.0,\"inStock\":0,\"image\":null,\"userRate\":{},\"initRate\":0.0}]"));
    }
    @Test
    void testSearchCommoditiesByCategory_checkFields() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "category");
        input.put("searchValue", "commodity_category");
        Commodity commodity = new Commodity();
        commodity.setId("commodity_id");
        ArrayList<String> categories = new ArrayList();
        categories.add("commodity_category");
        commodity.setCategories(categories);
        commodity.setPrice(1);

        ArrayList<Commodity> commodities = new ArrayList<>();
        commodities.add(commodity);

        Mockito.when(baloot.filterCommoditiesByCategory("commodity_category"))
                .thenReturn(commodities);

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(commodity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].categories").value(commodity.getCategories()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(commodity.getPrice()));
    }

    @Test
    void testSearchCommoditiesInvalidSearchOption_checkStatus() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "invalidOption");
        input.put("searchValue", "value");

        when(baloot.filterCommoditiesByName("commodityOtherOptions")).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }
    @Test
    void testSearchCommoditiesInvalidSearchOption_checkSize() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "invalidOption");
        input.put("searchValue", "value");

        when(baloot.filterCommoditiesByName("commodityOtherOptions")).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
    }
    @Test
    void testSearchCommoditiesInvalidSearchOption_getContent() throws Exception {
        Map<String, String> input = new HashMap<String, String>();
        input.put("searchOption", "invalidOption");
        input.put("searchValue", "value");

        when(baloot.filterCommoditiesByName("commodityOtherOptions")).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetSuggestedCommodities_checkStatus() throws Exception {
        String commodityId = "1";
        Commodity commodity = new Commodity();
        commodity.setId(commodityId);
        ArrayList<Commodity> suggestedCommodities = new ArrayList<Commodity>();
        suggestedCommodities.add(commodity);

        Mockito.when(baloot.getCommodityById(commodityId)).thenReturn(commodity);
        Mockito.when(baloot.suggestSimilarCommodities(commodity)).thenReturn(suggestedCommodities);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/{id}/suggested", commodityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testGetSuggestedCommodities_checkResult() throws Exception {
        String commodityId = "1";
        Commodity commodity = new Commodity();
        commodity.setId(commodityId);
        ArrayList<Commodity> suggestedCommodities = new ArrayList<Commodity>();
        suggestedCommodities.add(commodity);

        Mockito.when(baloot.getCommodityById(commodityId)).thenReturn(commodity);
        Mockito.when(baloot.suggestSimilarCommodities(commodity)).thenReturn(suggestedCommodities);

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/{id}/suggested", commodityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(suggestedCommodities.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(commodityId));
    }
    @Test
    void testGetSuggestedCommoditiesNotFound_checkStatus() throws Exception {
        String nonExistentCommodityId = "nonExistentCommodityId";

        Mockito.when(baloot.getCommodityById(nonExistentCommodityId)).thenThrow(new NotExistentCommodity());

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/{id}/suggested", nonExistentCommodityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void testGetSuggestedCommoditiesNotFound_checkResult() throws Exception {
        String nonExistentCommodityId = "nonExistentCommodityId";

        Mockito.when(baloot.getCommodityById(nonExistentCommodityId)).thenThrow(new NotExistentCommodity());

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/{id}/suggested", nonExistentCommodityId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }
}