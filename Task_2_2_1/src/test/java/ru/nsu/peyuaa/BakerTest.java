package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BakerTest {
    @Test
    public void testMakePizza() {
        Warehouse warehouse = new Warehouse(10);
        Pizzeria pizzeria = new Pizzeria(warehouse);
        Baker baker = new Baker(pizzeria, 500);
        Order order = new Order(new Pizza());
        baker.makePizza(order);
        assertEquals(OrderState.COOKED, order.getState());
    }

}
