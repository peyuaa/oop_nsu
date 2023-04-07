package ru.nsu.peyuaa;

public class Baker extends Thread {
    private static final int timeToCook = 1000;
    private final Pizzeria pizzeria;

    public Baker(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    private void makePizza(Order order) {
        order.setState(OrderState.COOKING);
        try {
            Thread.sleep(timeToCook);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        order.setState(OrderState.COOKED);
    }

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

