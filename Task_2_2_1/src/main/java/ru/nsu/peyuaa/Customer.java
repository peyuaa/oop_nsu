package ru.nsu.peyuaa;

public class Customer extends Thread {

    private final Pizzeria pizzeria;

    public Customer(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public void makeOrder() {
        pizzeria.makeAnOrder(new Pizza());
    }

    public void run() {
        System.out.println("Customer started");
        makeOrder();
    }
}
