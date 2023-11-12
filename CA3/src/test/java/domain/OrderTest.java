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
    @Test
    public void testSetGetId() {
        Order order = new Order();
        order.setId(1);
        assertEquals(1, order.getId());
    }


    @Test
    public void testSetCustomer() {
        Order order = new Order();
        order.setCustomer(1);
        assertEquals(1, order.getCustomer());
    }


    @Test
    public void testSetPrice() {
        Order order = new Order();
        order.setPrice(1);
        assertEquals(1, order.getPrice());
    }


    @Test
    public void testSetQuantity() {
        Order order = new Order();
        order.setQuantity(1);
        assertEquals(1, order.getQuantity());
    }

}
