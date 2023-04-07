package ru.nsu.peyuaa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    public void testNewOrderCreatedWithQueuedState() {
        Pizza pizza = new Pizza();
        Order order = new Order(pizza);
        Assertions.assertEquals(OrderState.QUEUED, order.getState());
    }

    @Test
    public void testNewOrderCreatedWithUniqueId() {
        Pizza pizza1 = new Pizza();
        Order order1 = new Order(pizza1);

        Pizza pizza2 = new Pizza();
        Order order2 = new Order(pizza2);

        Assertions.assertNotEquals(order1.getOrderId(), order2.getOrderId());
    }

    @Test
    public void testGenerateOrderIdMethodGeneratesUniqueIds() {
        Order order1 = new Order(new Pizza());
        Order order2 = new Order(new Pizza());
        Assertions.assertEquals(order1.getOrderId() + 1, order2.getOrderId());
    }

    @Test
    public void testOrderStateCanBeChanged() {
        Order order = new Order(new Pizza());
        order.setState(OrderState.COOKING);
        Assertions.assertEquals(OrderState.COOKING, order.getState());
    }
}

