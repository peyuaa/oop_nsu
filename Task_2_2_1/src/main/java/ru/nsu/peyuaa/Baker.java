package ru.nsu.peyuaa;

/**
 * A `Baker` represents an employee of a `Pizzeria` who cooks pizzas.
 * Each `Baker` is run as a separate thread.
 */
public class Baker extends Thread {

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
        this.pizzeria = pizzeria;
        this.timeToCook = timeToCook;
    }

    /**
     * Cooks a pizza for the specified `Order`.
     *
     * @param order the `Order` for which to cook a pizza
     */
    void makePizza(Order order) {
        order.setState(OrderState.COOKING);
        try {
            Thread.sleep(timeToCook);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        order.setState(OrderState.COOKED);
    }

    /**
     * Starts the `Baker` thread and has them cook pizzas for orders until interrupted.
     */
    public void run() {
        System.out.println("Baker is ready");
        while (true) {
            Order order = pizzeria.getOrder();
            if (order == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                makePizza(order);
                pizzeria.getWarehouse().addOrder(order);
            }
        }
    }
}

