package ru.nsu.peyuaa;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The Pizzeria class represents a pizzeria where orders can be made and fulfilled.
 * This class manages a set of bakers, couriers, and a warehouse where pizzas are baked,
 * stored, and picked up for delivery.
 */
public class Pizzeria {
    private List<Courier> couriers;
    private List<Baker> bakers;
    private final Warehouse warehouse;
    private final BlockingQueue<Order> orders;

    /**
     * Constructs a new Pizzeria instance with the specified warehouse.
     *
     * @param warehouse the warehouse to be used by this pizzeria
     */
    public Pizzeria(Warehouse warehouse) {
        this.warehouse = warehouse;
        orders = new LinkedBlockingQueue<>();
        this.couriers = new ArrayList<>();
        this.bakers = new ArrayList<>();
    }

    /**
     * Sets the list of bakers to be used by this pizzeria.
     *
     * @param bakers the list of bakers
     */
    public void setBakers(List<Baker> bakers) {
        this.bakers = bakers;
    }

    /**
     * Sets the list of couriers to be used by this pizzeria.
     *
     * @param couriers the list of couriers
     */
    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }

    /**
     * Makes an order for the specified pizza.
     *
     * @param pizza the pizza to be ordered
     */
    public void makeAnOrder(Pizza pizza) {
        Order order = new Order(pizza);
        order.setState(OrderState.QUEUED);
        try {
            orders.put(order);
        } catch (InterruptedException e) {
            System.out.println("Pizzeria stopped accepting orders");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Gets the next order from the queue.
     *
     * @return the next order, or null if the queue is empty
     */
    public Order getOrder() throws InterruptedException {
        return orders.take();
    }

    /**
     * Gets the warehouse used by this pizzeria.
     *
     * @return the warehouse
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Starts the pizzeria operation by initializing
     * and starting the bakers, couriers, and warehouse threads.
     * This method should be called after setting the bakers and couriers lists
     * and starting the pizzeria thread.
     * Once the pizzeria is open,
     * it will continuously listen for new orders from the customers and add them to
     * the orders queue until the program is terminated.
     */
    public void start() {
        System.out.println("Pizzeria is open");
        for (Baker baker : bakers) {
            baker.start();
        }
        for (Courier courier : couriers) {
            courier.start();
        }
    }

    /**
     * Stops the pizzeria operation by interrupting the threads of all bakers and couriers.
     */
    public void stop() {
        for (Baker baker : bakers) {
            baker.interrupt();
        }
        for (Courier courier : couriers) {
            courier.interrupt();
        }
    }
}
