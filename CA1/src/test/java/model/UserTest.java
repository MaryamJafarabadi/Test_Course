package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.CommodityIsNotInBuyList;
import exceptions.InsufficientCredit;
import exceptions.InvalidCreditRange;

public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "password123", "user@example.com", "2000-01-01", "123 Main St.");
        user.setCredit(100.0f);
    }

    @Test
    void testAddCreditValid() {
        assertDoesNotThrow(() -> user.addCredit(50.0f));
        assertEquals(150.0f, user.getCredit());
    }

    @Test
    void testAddCreditInvalid() {
        assertThrows(InvalidCreditRange.class, () -> user.addCredit(-50.0f));
        assertEquals(100.0f, user.getCredit());
    }

    @Test
    void testWithdrawCreditValid() {
        assertDoesNotThrow(() -> user.withdrawCredit(50.0f));
        assertEquals(50.0f, user.getCredit());
    }

    @Test
    void testWithdrawCreditInsufficient() {
        assertThrows(InsufficientCredit.class, () -> user.withdrawCredit(150.0f));
        assertEquals(100.0f, user.getCredit());
    }

    @Test
    void testAddBuyItem() {
        Commodity commodity = new Commodity();
        commodity.setId("1");
        user.addBuyItem(commodity);

        assertTrue(user.getBuyList().containsKey("1"));
        assertEquals(1, user.getBuyList().get("1"));
    }

    @Test
    void testAddBuyExistingItem() {
        Commodity commodity1 = new Commodity();
        Commodity commodity2 = new Commodity();
        commodity1.setId("1");
        commodity2.setId("1");
        user.addBuyItem(commodity1);
        user.addBuyItem(commodity2);
        assertTrue(user.getBuyList().containsKey("1"));
        assertEquals(2, user.getBuyList().get("1") );
    }

    @Test
    void testAddPurchasedItem() {
        user.addPurchasedItem("2", 3);

        assertTrue(user.getPurchasedList().containsKey("2"));
        assertEquals(3, user.getPurchasedList().get("2"));
    }

    @Test
    void testAddPurchasedExistingItem() {
        user.addPurchasedItem("2", 3);
        user.addPurchasedItem("2", 1);
        assertTrue(user.getPurchasedList().containsKey("2"));
        assertEquals(4, user.getPurchasedList().get("2"));
    }

    @Test
    void testRemoveItemFromBuyListValidWhichBecomeEmpty() {
        Commodity commodity = new Commodity();
        commodity.setId("3");
        user.addBuyItem(commodity);

        assertDoesNotThrow(() -> user.removeItemFromBuyList(commodity));
        assertFalse(user.getBuyList().containsKey("3"));
    }

    @Test
    void testRemoveItemFromBuyListValidWhichBecomeDecremented() {
        Commodity commodity = new Commodity();
        commodity.setId("3");
        user.addBuyItem(commodity);
        user.addBuyItem(commodity);

        assertDoesNotThrow(() -> user.removeItemFromBuyList(commodity));
        assertEquals(1, user.getBuyList().get("3") );
        //dif begirim???
    }
    @Test
    void testRemoveItemFromBuyListInvalid() {
        Commodity commodity = new Commodity();
        commodity.setId("4");

        assertThrows(CommodityIsNotInBuyList.class, () -> user.removeItemFromBuyList(commodity));
        assertFalse(user.getBuyList().containsKey("4"));
    }
}

