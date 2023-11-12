package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    private Order order;
    @Before
    public void setup() {
        order = new Order();
    }


    @Test
    public void testDefaultConstructor() {
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
        assertFalse(order.equals(new Object()));
    }
    @Test
    public void testSetGetId() {
        order.setId(1);
        assertEquals(1, order.getId());
    }


    @Test
    public void testSetCustomer() {
        order.setCustomer(1);
        assertEquals(1, order.getCustomer());
    }


    @Test
    public void testSetPrice() {
        order.setPrice(1);
        assertEquals(1, order.getPrice());
    }


    @Test
    public void testSetQuantity() {
        order.setQuantity(1);
        assertEquals(1, order.getQuantity());
    }

}
