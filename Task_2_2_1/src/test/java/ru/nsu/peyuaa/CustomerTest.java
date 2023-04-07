package ru.nsu.peyuaa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

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

    // very stupid test
    @Test
    public void testRun() throws InterruptedException {
        // Create a mock Pizzeria
        Pizzeria pizzeria = mock(Pizzeria.class);

        // Create a new Customer with the mock Pizzeria
        Customer customer = new Customer(pizzeria);

        // Call the run method of the Customer
        customer.start();

        Thread.sleep(1000);

        // Verify that the makeAnOrder method
        // was called on the mock Pizzeria with a new Pizza object
        verify(pizzeria).makeAnOrder(any(Pizza.class));
    }
}
