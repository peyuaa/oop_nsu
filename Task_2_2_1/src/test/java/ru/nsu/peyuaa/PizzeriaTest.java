package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PizzeriaTest {
    @Test
    void testMakeAnOrder() {
        Warehouse warehouse = new Warehouse(10);
        Pizzeria pizzeria = new Pizzeria(warehouse);

        // Make an order and check that it was added to the orders queue
        pizzeria.makeAnOrder(new Pizza());
        Order order = pizzeria.getOrder();
        assertNotNull(order);
        assertEquals(OrderState.QUEUED, order.getState());
        assertNull(pizzeria.getOrder());
    }

    @Test
    void testSetBakers() throws InterruptedException {
        Warehouse warehouse = new Warehouse(10);
        Pizzeria pizzeria = new Pizzeria(warehouse);

        // Set the list of bakers and check that they are all started
        List<Baker> bakers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            bakers.add(new Baker(pizzeria, 100));
        }
        pizzeria.setBakers(bakers);
        pizzeria.start();
        Thread.sleep(2000);
        for (Baker baker : bakers) {
            assertTrue(baker.isAlive());
        }
    }

    @Test
    void testSetCouriers() throws InterruptedException {
        Warehouse warehouse = new Warehouse(1);
        Pizzeria pizzeria = new Pizzeria(warehouse);

        // Set the list of couriers and check that they are all started
        List<Courier> couriers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            couriers.add(new Courier(1, 100, warehouse));
        }
        pizzeria.setCouriers(couriers);
        pizzeria.start();
        Thread.sleep(2000);
        for (Courier courier : couriers) {
            assertTrue(courier.isAlive());
        }
    }
}
