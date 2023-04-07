package ru.nsu.peyuaa;
public class Config {
    public int bakersNum;
    public int bakersTimeToCook;
    public int couriersNum;
    public int couriersMaxVolume;
    public int customersNum;
    public int warehouseSize;

    public Config(int bakersNum, int bakersTimeToCook, int couriersNum,
                    int couriersMaxVolume, int customersNum, int warehouseSize) {
        this.bakersNum = bakersNum;
        this.bakersTimeToCook = bakersTimeToCook;
        this.couriersNum = couriersNum;
        this.couriersMaxVolume = couriersMaxVolume;
        this.customersNum = customersNum;
        this.warehouseSize = warehouseSize;
    }
}
