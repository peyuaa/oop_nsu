package ru.nsu.peyuaa;

import java.util.ArrayList;
import java.util.List;

public class Courier extends Thread {
    private static int courierCount = 0;
    private final int maxVolume;
    private final Warehouse warehouse;
    private final List<Order> orders;

    public Courier(int maxVolume, Warehouse warehouse) {
        this.maxVolume = maxVolume;
        this.warehouse = warehouse;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        order.setState(OrderState.DELIVERING);
        orders.add(order);
    }

    @Override
    public void run() {
        System.out.println("Courier " + courierCount++ + " is ready");
        while (true) {
            if (warehouse.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                List<Order> orders = warehouse.pickUpPizzas(maxVolume);
                for (Order order : orders) {
                    addOrder(order);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    order.setState(OrderState.DELIVERED);
                }
                orders.clear();
            }
        }
    }
}

