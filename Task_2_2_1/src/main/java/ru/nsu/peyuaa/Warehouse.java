package ru.nsu.peyuaa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A warehouse class that stores pizza orders until they are picked up by couriers for delivery.
 * The warehouse has a maximum size limit and allows adding and removing orders.
 */
public class Warehouse extends Thread {
    private final int maxSize;
    private final List<Order> orders;

    /**
     * Creates a new instance of the Warehouse class with a specified maximum size.
     * @param maxSize The maximum number of orders that the warehouse can hold.
     */
    public Warehouse(int maxSize) {
        this.maxSize = maxSize;
        this.orders = new ArrayList<>();
    }

    /**
     * Checks if the warehouse is full.
     * @return true if the warehouse is full, false otherwise.
     */
    public boolean isFull() {
        return orders.size() >= maxSize;
    }

    /**
     * Checks if the warehouse is empty.
     * @return true if the warehouse is empty, false otherwise.
     */
    public boolean isEmpty() {
        return orders.isEmpty();
    }

    /**
     * Adds an order to the warehouse.
     * @param order The order to add.
     */
    public void addOrder(Order order) {
        while (isFull()) {
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException ignored) {
            }
        }
        orders.add(order);
        order.setState(OrderState.WAREHOUSE);
    }

    /**
     * Picks up a specified number of pizza orders from the warehouse and removes them.
     * @param count The number of orders to pick up.
     * @return A list of removed orders.
     */
    public synchronized List<Order> pickUpPizzas(int count) {
        List<Order> removedPizzas = new ArrayList<>();
        int volume = 0;
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext() && volume < count) {
            Order order = iterator.next();
            volume += 1;
            removedPizzas.add(order);
            iterator.remove();
        }
        return removedPizzas;
    }

    /**
     * Runs the warehouse process in a separate thread.
     * The warehouse checks if it is empty and waits for new orders to arrive.
     * When a new order is added, it waits until there is space in the warehouse to store it.
     */
    public void run() {
        while (true) {
            if (isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
