package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.NotInStock;

public class CommodityTest {
    private Commodity commodity;

    @BeforeEach
    void setUp() {
        commodity = new Commodity();
        commodity.setInStock(10);
        commodity.setRating(3.0f);
    }

    @Test
    void testUpdateInStockValid() {
        assertDoesNotThrow(() -> commodity.updateInStock(5));
        assertEquals(15, commodity.getInStock());
    }

    @Test
    void testUpdateInStockInvalid() {
        assertThrows(NotInStock.class, () -> commodity.updateInStock(-15));
        assertEquals(10, commodity.getInStock());
    }

    @Test
    void testRatingInitialization() {
        assertEquals(3, commodity.getRating());
    }

    @Test
    void testAddRate() {
        commodity.addRate("User1", 4);
        assertEquals(4, commodity.getUserRate().get("User1"));
        assertEquals(3.5f, commodity.getRating()); // Initial rating was 3.0, so new rating is (3.0 + 4) / 2
    }
}

