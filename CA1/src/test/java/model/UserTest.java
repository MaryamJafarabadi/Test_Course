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

}

