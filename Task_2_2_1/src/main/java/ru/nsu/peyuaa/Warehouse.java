package ru.nsu.peyuaa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Warehouse extends Thread {
    private final int maxSize;
    private final List<Order> orders;

    public Warehouse(int maxSize) {
        this.maxSize = maxSize;
        this.orders = new ArrayList<>();
    }

    public boolean isFull() {
        return orders.size() >= maxSize;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public void addOrder(Order order) {
        while (isFull()) {
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException ignored) {
            }
        }
        orders.add(order);
        order.setState(OrderState.WAREHOUSE);
    }

    public synchronized List<Order> pickUpPizzas(int count) {
        List<Order> removedPizzas = new ArrayList<>();
        int volume = 0;
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext() && volume < count) {
            Order order = iterator.next();
            volume += 1;
            removedPizzas.add(order);
            iterator.remove();
        }
        return removedPizzas;
    }

    public void run() {
        while (true) {
            if (isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}

