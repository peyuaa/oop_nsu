package ru.nsu.peyuaa;

import java.util.List;

public class Order {
    private Pizza pizza;
    private Courier courier;
    private OrderState state;
    private int orderId;

    private static int lastOrderId = 0;

    public Order(Pizza pizza) {
        this.state = OrderState.QUEUED;
        this.pizza = pizza;
        this.orderId = generateOrderId();
    }

    private static synchronized int generateOrderId() {
        return ++lastOrderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setState(OrderState state) {
        this.state = state;
        System.out.printf("[%d], [%s]\n", orderId, state);
    }

    public OrderState getState() {
        return state;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}

