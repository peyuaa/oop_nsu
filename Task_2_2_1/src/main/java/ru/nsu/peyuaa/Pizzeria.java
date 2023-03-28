package ru.nsu.peyuaa;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {
    private List<Courier> couriers;
    private List<Baker> bakers;
    private Warehouse warehouse;
    private BlockingQueue<Order> orders;

    public Pizzeria(Warehouse warehouse) {
        this.warehouse = warehouse;
        orders = new LinkedBlockingQueue<>();
    }

    public void setBakers(List<Baker> bakers) {
        this.bakers = bakers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }

    public void makeAnOrder(Pizza pizza) {
        Order order = new Order(pizza);
        order.setState(OrderState.QUEUED);
        while (!orders.offer(order)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Order getOrder() {
        return orders.poll();
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}
