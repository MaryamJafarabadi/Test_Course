package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testDefaultConstructor() {
        Order order = new Order();
        assertEquals(0, order.getId());
        assertEquals(0, order.getCustomer());
        assertEquals(0, order.getPrice());
        assertEquals(0, order.getQuantity());
    }

    @Test
    public void testEqualsSameOrder() {
        Order order1 = new Order();
        order1.setId(1);
        Order order2 = new Order();
        order2.setId(1);
        assertTrue(order1.equals(order2));
    }
    @Test
    public void testEqualsDiffOrder() {
        Order order1 = new Order();
        order1.setId(1);
        Order order2 = new Order();
        order2.setId(2);
        assertFalse(order1.equals(order2));
    }

    @Test
    public void testEqualsNotOrderObject() {
        Order order = new Order();
        assertFalse(order.equals(new Object()));
    }
}
