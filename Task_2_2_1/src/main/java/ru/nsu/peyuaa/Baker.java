package ru.nsu.peyuaa;

/**
 * A `Baker` represents an employee of a `Pizzeria` who cooks pizzas.
 * Each `Baker` is run as a separate thread.
 */
public class Baker extends Thread {

    private static int bakersCount = 0;

    private final int bakerID;

    /**
     * The time it takes to cook a pizza, in milliseconds.
     */
    private final int timeToCook;

    /**
     * The `Pizzeria` where the `Baker` works.
     */
    private final Pizzeria pizzeria;

    /**
     * Constructs a new `Baker` object with the specified `Pizzeria`.
     *
     * @param pizzeria the `Pizzeria` where the `Baker` works
     */
    public Baker(Pizzeria pizzeria, int timeToCook) {
        this.bakerID = bakersCount++;
        this.pizzeria = pizzeria;
        this.timeToCook = timeToCook;
    }

    /**
     * Cooks a pizza for the specified `Order`.
     *
     * @param order the `Order` for which to cook a pizza
     */
    void makePizza(Order order) throws InterruptedException {
        order.setState(OrderState.COOKING);
        Thread.sleep(timeToCook);
        order.setState(OrderState.COOKED);
    }

    /**
     * Starts the `Baker` thread and has them cook pizzas for orders until interrupted.
     */
    public void run() {
        System.out.printf("Baker %d is ready\n", bakerID);
        while (!isInterrupted()) {
            try {
                Order order = pizzeria.getOrder();
                makePizza(order);
                pizzeria.getWarehouse().addOrder(order);
            } catch (InterruptedException e) {
                System.out.printf("Baker №%d stopped cooking the pizza\n", bakerID);
                interrupt();
            }
        }
    }
}

