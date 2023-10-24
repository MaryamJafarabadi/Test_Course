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
    void testAddScoreSuccessfuly() {
        assertDoesNotThrow(() -> commodity.addRate("User1", 4));
        assertEquals(4, commodity.getUserRate().get("User1"));
    }
    @Test
    void testAddRateValid() {
        assertDoesNotThrow(() -> commodity.addRate("User1", 4));
        assertEquals(3.5f, commodity.getRating());
        // Initial rating was 3.0, so new rating is (3.0 + 4) / 2
    }
    @Test
    void testAddRateValid_line1() {
        assertDoesNotThrow(() -> commodity.addRate("User1", 1));
        assertEquals((3+1)/2f, commodity.getRating());
    }
    @Test
    void testAddRateValid_line10() {
        assertDoesNotThrow(() -> commodity.addRate("User1", 10));
        assertEquals((3+10)/2f, commodity.getRating());
    }
    @Test
    void testAddRateOutOfRange_LessThan1() {
        assertThrows(IllegalArgumentException.class, () -> commodity.addRate("user1", 0));
        assertEquals(0.0f, commodity.getRating());
    }

    @Test
    void testAddRateOutOfRange_MoreThan10() {
        assertThrows(IllegalArgumentException.class, () -> commodity.addRate("user1", 11));
        assertEquals(0.0f, commodity.getRating());
    }

    @Test
    void testAddRateOutOfRangeNegative() {
        assertThrows(IllegalArgumentException.class, () -> commodity.addRate("user1", -1));
        assertEquals(0.0f, commodity.getRating());
    }

    @Test
    void testAddRateOutOfRangeAfterAddingOneValidScore() { //because I want to rating be changed.
        assertDoesNotThrow(() -> commodity.addRate("User1", 4));
        assertThrows(IllegalArgumentException.class, () -> commodity.addRate("user1", -1));
        assertEquals(3.5f, commodity.getRating());
    }

}

