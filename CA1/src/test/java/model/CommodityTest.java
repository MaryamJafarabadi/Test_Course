package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.NotInStock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


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


    @ParameterizedTest //Happy Scenario
    @CsvSource({
            "4, 3.5", //    (3.0 + 4) / 2   ** 3 is initial rate!
            "1, 2.0", //    (3+1)/2
            "10, 6.5" //    (3+10)/2
    })
    void testAddRateValid(int rate, float expectedRating) {
        assertDoesNotThrow(() -> commodity.addRate("User1", rate));
        assertEquals(expectedRating, commodity.getRating());
    }

    @ParameterizedTest //Sad Scenario
    @CsvSource({
            "0, 0.0",
            "11, 0.0",
            "-1, 0.0"
    })
    void testAddRateOutOfRange(int rate, float expectedRating) {
        assertThrows(NumberFormatException.class, () -> commodity.addRate("user1", rate));
        assertEquals(expectedRating, commodity.getRating());
    }

    @Test
    void testAddRateOutOfRangeAfterAddingOneValidScore() { //because I want to rating be changed.
        assertDoesNotThrow(() -> commodity.addRate("User1", 4));
        assertThrows(NumberFormatException.class, () -> commodity.addRate("user1", -1));
        assertEquals(3.5f, commodity.getRating());
    }

}

