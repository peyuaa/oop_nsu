package ru.nsu.peyuaa;

/**
 * An enumeration representing the different states an order can be in:
 * QUEUED, COOKING, COOKED, WAREHOUSE, DELIVERING, and DELIVERED.
 */
public enum OrderState {
    QUEUED, // The order is in the queue
    COOKING, // The order is being cooked
    COOKED, // The order has been cooked
    WAREHOUSE, // The order is in the warehouse
    DELIVERING, // The order is being delivered
    DELIVERED // The order has been delivered
}
