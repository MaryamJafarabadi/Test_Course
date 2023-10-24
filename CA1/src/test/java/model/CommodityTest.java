package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.NotInStock;
import exceptions.InvalidCreditRange;

public class CommodityTest {
    private Commodity commodity;
    int PreInStock;
    @BeforeEach
    void setUp() {
        commodity = new Commodity();
        commodity.setInStock(10);
        commodity.setInitRate(3.0f);
        PreInStock = commodity.getInStock();
    }

    @Test
    void testUpdateInStockValid() {
        assertDoesNotThrow(() -> commodity.updateInStock(5));
        assertEquals(PreInStock + 5, commodity.getInStock());
    }

    @Test
    void testUpdateInStockInvalid() {
        assertThrows(NotInStock.class, () -> commodity.updateInStock(-15));
        assertEquals(PreInStock, commodity.getInStock());
    }


    @Test
    void testAddRate() {
        commodity.addRate("User1", 4);
        assertEquals(4, commodity.getUserRate().get("User1"));
        assertEquals(3.5f, commodity.getRating()); // Initial rating was 3.0, so new rating is (3.0 + 4) / 2
    }

    @Test
    void testAddRateOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> commodity.addRate("user1", 0));
    }

    @Test
    void testAddRateOutOfRangeNegative() {
        assertThrows(IllegalArgumentException.class, () -> commodity.addRate("user1", -1));
    }
}

