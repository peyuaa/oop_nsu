package ru.nsu.peyuaa;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CustomerTest {

    @Test
    void testMakeOrder() {
        // create a mock Pizzeria object
        Pizzeria pizzeria = mock(Pizzeria.class);

        // create a new Customer object with the mock Pizzeria
        Customer customer = new Customer(pizzeria);

        Pizza pizza = new Pizza();

        // make an order
        customer.makeOrder(pizza);

        // verify that the Pizzeria's makeAnOrder method was called with a new Pizza object
        verify(pizzeria).makeAnOrder(pizza);
    }
}
