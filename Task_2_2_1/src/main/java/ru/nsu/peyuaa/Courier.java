package ru.nsu.peyuaa;

import java.util.ArrayList;
import java.util.List;

public class Courier extends Thread {
    private static int courierCount = 0;
    private int maxVolume;
    private Warehouse warehouse;
    private List<Order> orders;

    public Courier(int maxVolume, Warehouse warehouse) {
        this.maxVolume = maxVolume;
        this.warehouse = warehouse;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        order.setCourier(this);
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
                }
            } else {
                List<Order> orders = warehouse.pickUpPizzas(maxVolume);
                for (Order order : orders) {
                    addOrder(order);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    order.setState(OrderState.DELIVERED);
                }
                orders.clear();
            }
        }
    }
}

