package ru.nsu.peyuaa;

public class Customer {

    private Pizzeria pizzeria;

    public Customer(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public void makeOrder() {
        pizzeria.makeAnOrder(new Pizza());
    }
}
