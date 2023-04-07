package ru.nsu.peyuaa;

/**
 * Represents an order of a pizza. Each order has a unique ID, a state,
 * and a pizza object.
 */
public class Order {

    /**
     * The pizza associated with this order.
     */
    private final Pizza pizza;

    /**
     * The current state of this order.
     */
    private OrderState state;

    /**
     * The unique ID of this order.
     */
    private final int orderId;

    /**
     * The last generated order ID. Used to ensure that each order has a unique ID.
     */
    private static int lastOrderId = 0;

    /**
     * Creates a new Order object with the specified pizza.
     * The initial state of the order is set to QUEUED.
     *
     * @param pizza the pizza associated with this order
     */
    public Order(Pizza pizza) {
        this.state = OrderState.QUEUED;
        this.pizza = pizza;
        this.orderId = generateOrderId();
    }

    /**
     * Generates a unique ID for the order.
     * This method is synchronized to ensure thread-safety.
     *
     * @return a unique ID for the order
     */
    private static synchronized int generateOrderId() {
        return ++lastOrderId;
    }

    /**
     * Sets the current state of the order and prints a message
     * with the order ID and the new state.
     *
     * @param state the new state of the order
     */
    public void setState(OrderState state) {
        this.state = state;
        System.out.printf("[%d], [%s]\n", orderId, this.state);
    }
}

