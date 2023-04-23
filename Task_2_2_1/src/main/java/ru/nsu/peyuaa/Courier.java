package ru.nsu.peyuaa;

import java.util.ArrayList;
import java.util.List;

/**
 * A `Courier` represents an employee of a pizzeria
 * who is responsible for delivering pizzas to customers.
 */
public class Courier extends Thread {

    /**
     * The number of `Courier`s created so far.
     */
    private static int courierCount = 0;

    /**
     * The maximum number of pizzas that this `Courier` can deliver at once.
     */
    private final int maxVolume;

    /**
     * The `Warehouse` from which this `Courier` picks up pizzas.
     */
    private final Warehouse warehouse;

    /**
     * The list of orders that this `Courier` is currently delivering.
     */
    private final List<Order> orders;

    /**
     * The time it takes for this `Courier` to deliver a pizza, in milliseconds.
     */
    private final int deliveryTime;

    /**
     * Constructs a new `Courier` object with the specified settings.
     *
     * @param maxVolume the maximum number of pizzas that this `Courier` can deliver at once
     * @param warehouse the `Warehouse` from which this `Courier` picks up pizzas
     */
    public Courier(int maxVolume, int deliveryTime, Warehouse warehouse) {
        this.maxVolume = maxVolume;
        this.warehouse = warehouse;
        this.deliveryTime = deliveryTime;
        this.orders = new ArrayList<>();
    }

    /**
     * Adds the specified `Order` to this `Courier`'s list of orders to deliver.
     * Sets the state of the `Order` to `DELIVERING`.
     *
     * @param order the `Order` to add
     */
    public void addOrder(Order order) {
        order.setState(OrderState.DELIVERING);
        orders.add(order);
    }

    /**
     * Runs this `Courier`. Loops indefinitely,
     * picking up pizzas from the `Warehouse` and delivering them to customers.
     * Once an order has been delivered, sets the state of the `Order` to `DELIVERED`.
     * Sleeps for 5 seconds between deliveries.
     */
    @Override
    public void run() {
        System.out.println("Courier " + courierCount++ + " is ready");
        while (!isInterrupted()) {
            List<Order> orders = warehouse.pickUpPizzas(maxVolume);
            for (Order order : orders) {
                addOrder(order);
                try {
                    Thread.sleep(deliveryTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                order.setState(OrderState.DELIVERED);
            }
            orders.clear();
        }
    }
}

