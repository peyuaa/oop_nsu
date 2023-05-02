package ru.nsu.peyuaa;

/**
 * A `Customer` represents a customer who wants to order a pizza from a `Pizzeria`.
 * Each `Customer` is run as a separate thread.
 */
public class Customer extends Thread {

    private static int customerCount = 0;

    private final int customerId;

    /**
     * The `Pizzeria` from which the customer wants to order a pizza.
     */
    private final Pizzeria pizzeria;

    /**
     * Constructs a new `Customer` object with the specified `Pizzeria`.
     *
     * @param pizzeria the `Pizzeria` from which the customer wants to order a pizza
     */
    public Customer(Pizzeria pizzeria) {
        this.customerId = customerCount++;
        this.pizzeria = pizzeria;
    }

    /**
     * Tells the `Pizzeria` to make an order for a new `Pizza`.
     */
    public void makeOrder(Pizza pizza) {
        pizzeria.makeAnOrder(pizza);
    }

    /**
     * Starts the `Customer` thread and tells it to make an order.
     */
    public void run() {
        System.out.printf("Customer №%d started\n", customerId);
        makeOrder(new Pizza());
    }
}
