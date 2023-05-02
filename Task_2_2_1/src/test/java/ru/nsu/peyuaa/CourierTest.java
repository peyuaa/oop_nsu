package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class CourierTest {

    @Test
    public void testAddOrder() {
        Warehouse warehouse = new Warehouse(10);
        Courier courier = new Courier(3, 100, warehouse);
        Order order = new Order(new Pizza());
        courier.addOrder(order);
        assertEquals(OrderState.DELIVERING, order.getState());
    }

    @Test
    public void testRun() throws InterruptedException {
        Warehouse warehouse = new Warehouse(10);
        Courier courier = new Courier(3, 100, warehouse);
        Order order1 = new Order(new Pizza());
        Order order2 = new Order(new Pizza());
        warehouse.addOrder(order1);
        warehouse.addOrder(order2);

        Thread courierThread = new Thread(courier);
        courierThread.start();

        TimeUnit.SECONDS.sleep(2);

        assertEquals(OrderState.DELIVERED, order1.getState());
        assertEquals(OrderState.DELIVERED, order2.getState());

        courierThread.interrupt();
    }
}
