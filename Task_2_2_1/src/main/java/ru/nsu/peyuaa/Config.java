package ru.nsu.peyuaa;

/**
 * A `Config` represents the configuration settings for a pizzeria.
 */
public class Config {

    /**
     * The number of `Baker`s to employ.
     */
    public int bakersNum;

    /**
     * The time it takes for a `Baker` to cook a pizza, in milliseconds.
     */
    public int bakersTimeToCook;

    /**
     * The number of `Courier`s to employ.
     */
    public int couriersNum;

    /**
     * The maximum number of pizzas that a `Courier` can deliver at once.
     */
    public int couriersMaxVolume;

    /**
     * The number of `Customer`s to simulate.
     */
    public int customersNum;

    /**
     * The maximum number of orders that the `Warehouse` can hold.
     */
    public int warehouseSize;

    /**
     * The time it takes for a `Courier` to deliver a pizza, in milliseconds.
     */
    public int courierDeliveryTime;

    /**
     * Constructs a new `Config` object with the specified settings.
     *
     * @param bakersNum the number of `Baker`s to employ
     * @param bakersTimeToCook the time it takes for a `Baker` to cook a pizza, in milliseconds
     * @param couriersNum the number of `Courier`s to employ
     * @param couriersMaxVolume the maximum number of pizzas that a `Courier` can deliver at once
     * @param customersNum the number of `Customer`s to simulate
     * @param warehouseSize the maximum number of orders that the `Warehouse` can hold
     */
    public Config(int bakersNum, int bakersTimeToCook, int couriersNum,
                    int couriersMaxVolume, int customersNum, int warehouseSize,
                        int courierDeliveryTime) {
        this.bakersNum = bakersNum;
        this.bakersTimeToCook = bakersTimeToCook;
        this.couriersNum = couriersNum;
        this.couriersMaxVolume = couriersMaxVolume;
        this.customersNum = customersNum;
        this.warehouseSize = warehouseSize;
        this.courierDeliveryTime = courierDeliveryTime;
    }
}
