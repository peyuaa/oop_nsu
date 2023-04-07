package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ConfigTest {
    @Test
    public void testConstructor() {
        Config config = new Config(3, 5000, 2, 3, 10, 50, 8000);

        assertEquals(3, config.bakersNum);
        assertEquals(5000, config.bakersTimeToCook);
        assertEquals(2, config.couriersNum);
        assertEquals(3, config.couriersMaxVolume);
        assertEquals(10, config.customersNum);
        assertEquals(50, config.warehouseSize);
        assertEquals(8000, config.courierDeliveryTime);
    }
}
