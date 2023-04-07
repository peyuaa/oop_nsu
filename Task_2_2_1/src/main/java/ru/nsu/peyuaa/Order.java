package ru.nsu.peyuaa;

public class Order {
    private final Pizza pizza;
    private OrderState state;
    private final int orderId;

    private static int lastOrderId = 0;

    public Order(Pizza pizza) {
        this.state = OrderState.QUEUED;
        this.pizza = pizza;
        this.orderId = generateOrderId();
    }

    private static synchronized int generateOrderId() {
        return ++lastOrderId;
    }

    public void setState(OrderState state) {
        this.state = state;
        System.out.printf("[%d], [%s]\n", orderId, this.state);
    }
}

