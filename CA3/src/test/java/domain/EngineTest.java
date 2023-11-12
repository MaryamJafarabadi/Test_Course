package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineTest {
    private Engine engine;

    @Before
    public void setUp() {
        engine = new Engine();
    }

    @Test
    public void testGetAverageOrderQuantityByCustomerWithNoOrderHistory() {
        assertEquals(0, engine.getAverageOrderQuantityByCustomer(1));
    }
    @Test
    public void testGetAverageOrderQuantityByCustomerSameCustomer() {
        Order order = new Order();
        Order order2 = new Order();
        order.setCustomer(1);
        order.setQuantity(3);
        order2.setCustomer(1);
        order2.setQuantity(2);
        engine.orderHistory.add(order);
        engine.orderHistory.add(order2);
        assertEquals(2, engine.getAverageOrderQuantityByCustomer(1));
    }
    @Test
    public void testGetAverageOrderQuantityByCustomerDifferentCustomer() {
        Order order = new Order();
        Order order2 = new Order();
        order.setCustomer(1);
        order.setQuantity(3);
        order2.setCustomer(2);
        order2.setQuantity(2);
        engine.orderHistory.add(order);
        engine.orderHistory.add(order2);
        assertEquals(3, engine.getAverageOrderQuantityByCustomer(1));
    }


}
