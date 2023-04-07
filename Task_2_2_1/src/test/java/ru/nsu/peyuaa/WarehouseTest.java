package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WarehouseTest {

    @Test
    public void testAddOrder() {
        Warehouse warehouse = new Warehouse(2);
        Order order = new Order(new Pizza());
        warehouse.addOrder(order);
        assertFalse(warehouse.isEmpty());
        assertFalse(warehouse.isFull());
        assertEquals(1, warehouse.pickUpPizzas(1).size());
    }

    @Test
    public void testIsFull() {
        Warehouse warehouse = new Warehouse(2);
        assertFalse(warehouse.isFull());
        Order order1 = new Order(new Pizza());
        Order order2 = new Order(new Pizza());
        warehouse.addOrder(order1);
        assertFalse(warehouse.isFull());
        warehouse.addOrder(order2);
        assertTrue(warehouse.isFull());
    }

    @Test
    public void testIsEmpty() {
        Warehouse warehouse = new Warehouse(1);
        assertTrue(warehouse.isEmpty());
        Order order = new Order(new Pizza());
        warehouse.addOrder(order);
        assertFalse(warehouse.isEmpty());
        warehouse.pickUpPizzas(1);
        assertTrue(warehouse.isEmpty());
    }
}
