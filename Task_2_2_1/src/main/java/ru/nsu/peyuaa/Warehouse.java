package ru.nsu.peyuaa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A warehouse class that stores pizza orders until they are picked up by couriers for delivery.
 * The warehouse has a maximum size limit and allows adding and removing orders.
 */
public class Warehouse {
    private final int maxSize;
    private final BlockingQueue<Order> orders;

    /**
     * Creates a new instance of the Warehouse class with a specified maximum size.
     *
     * @param maxSize The maximum number of orders that the warehouse can hold.
     */
    public Warehouse(int maxSize) {
        this.maxSize = maxSize;
        this.orders = new LinkedBlockingQueue<>();
    }

    /**
     * Checks if the warehouse is full.
     *
     * @return true if the warehouse is full, false otherwise.
     */
    public boolean isFull() {
        return orders.size() >= maxSize;
    }

    /**
     * Checks if the warehouse is empty.
     *
     * @return true if the warehouse is empty, false otherwise.
     */
    public boolean isEmpty() {
        return orders.isEmpty();
    }

    /**
     * Adds an order to the warehouse.
     *
     * @param order The order to add.
     */
    public synchronized void addOrder(Order order) {
        try {
            Thread.sleep(1000); // Wait for 1 second
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        orders.add(order);
        order.setState(OrderState.WAREHOUSE);
    }

    /**
     * Picks up a specified number of pizza orders from the warehouse and removes them.
     *
     * @param count The number of orders to pick up.
     * @return A list of removed orders.
     */
    public List<Order> pickUpPizzas(int count) {
        List<Order> removedPizzas = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Order order;
            if (i == 0) {
                try {
                    order = orders.take();
                    removedPizzas.add(order);
                    continue;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            order = orders.poll();
            if (order == null) {
                break;
            }
            removedPizzas.add(order);
        }
        return removedPizzas;
    }
}
