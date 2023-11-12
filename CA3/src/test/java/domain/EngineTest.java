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

    @Test
    public void testGetQuantityPatternByPriceWithNoOrderHistory() {
        assertEquals(0, engine.getQuantityPatternByPrice(10));
    }
    @Test
    public void testGetQuantityPatternByPriceWithEmptyHistory() {
        Order order = new Order();
        engine.orderHistory.add(order);
        assertEquals(0, engine.getQuantityPatternByPrice(1));
    }

    @Test
    public void testGetQuantityPatternByPriceWithNoPatternAllDiffPrice() {
        Order order = new Order();
        order.setPrice(1);
        order.setId(1);
        Order order2 = new Order();
        order2.setPrice(1);
        order2.setId(2);
        engine.orderHistory.add(order);
        engine.orderHistory.add(order2);
        assertEquals(0, engine.getQuantityPatternByPrice(2));
    }
    @Test
    public void testGetQuantityPatternByPriceDiffChange() {
        Order order = new Order();
        order.setPrice(1);
        order.setId(1);
        order.setQuantity(1);
        Order order2 = new Order();
        order2.setPrice(1);
        order2.setId(2);
        order2.setQuantity(2);
        engine.orderHistory.add(order);
        engine.orderHistory.add(order2);
        assertEquals(1, engine.getQuantityPatternByPrice(1));
    }

    @Test
    public void testGetQuantityPatternByPriceInterruptOrder() {
        Order order = new Order();
        order.setPrice(1);
        order.setId(1);
        order.setQuantity(1);
        Order order2 = new Order();
        order2.setPrice(1);
        order2.setId(2);
        order2.setQuantity(2);
        Order order3 = new Order();
        order3.setPrice(1);
        order3.setId(3);
        order3.setQuantity(4);

        engine.orderHistory.add(order);
        engine.orderHistory.add(order2);
        engine.orderHistory.add(order3);

        assertEquals(0, engine.getQuantityPatternByPrice(1));
    }

    @Test
    public void testGetQuantityPatternByPrice() {
        Order order = new Order();
        order.setPrice(1);
        order.setId(1);
        order.setQuantity(1);
        Order order2 = new Order();
        order2.setPrice(1);
        order2.setId(2);
        order2.setQuantity(2);
        Order order3 = new Order();
        order3.setPrice(1);
        order3.setId(3);
        order3.setQuantity(3);

        engine.orderHistory.add(order);
        engine.orderHistory.add(order2);
        engine.orderHistory.add(order3);

        assertEquals(1, engine.getQuantityPatternByPrice(1));
    }

    /*@Test
    public void testGetQuantityPatternByPriceNoPattern() {
        Order order = new Order();
        order.setPrice(1);
        order.setId(1);
        order.setQuantity(1);
        Order order2 = new Order();
        order2.setPrice(1);
        order2.setId(2);
        order2.setQuantity(2);
        Order order3 = new Order();
        order3.setPrice(1);
        order3.setId(3);
        order3.setQuantity(2);

        engine.orderHistory.add(order);
        engine.orderHistory.add(order2);
        engine.orderHistory.add(order3);

        assertEquals(0, engine.getQuantityPatternByPrice(1));
    }*/

}
