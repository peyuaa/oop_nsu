/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Semaphore;

/**
 * This class represents the entry point of the pizza delivery simulation program.
 * It reads the configuration file and creates the necessary objects to run the simulation.
 * It starts the threads for the customers and the pizzeria,
 * which in turn start the threads for the couriers, bakers, and warehouse.
 */
public class Main {
    private static final String CONFIG = "config.json";

    /**
     * The main method of the application.
     * Parses the configuration file,
     * creates and starts the necessary threads, starts the simulation.
     *
     * @param args The command-line arguments, which are not used in this application.
     * @throws IOException If an I/O error occurs while reading the configuration file.
     */
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                Main.class.getClassLoader().getResourceAsStream(CONFIG))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }

        Gson g = new Gson();
        Config config = g.fromJson(sb.toString(), Config.class);

        List<Baker> bakers = new ArrayList<>();
        List<Courier> couriers = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        Warehouse warehouse = new Warehouse(config.warehouseSize);
        Pizzeria pizzeria = new Pizzeria(warehouse);

        for (int i = 0; i < config.bakersNum; i++) {
            bakers.add(new Baker(pizzeria, config.bakersTimeToCook));
        }
        for (int i = 0; i < config.couriersNum; i++) {
            couriers.add(new Courier(config.couriersMaxVolume, config.courierDeliveryTime,
                    warehouse));
        }

        pizzeria.setCouriers(couriers);
        pizzeria.setBakers(bakers);

        for (int i = 0; i < config.customersNum; i++) {
            customers.add(new Customer(pizzeria));
        }

        for (Customer customer : customers) {
            customer.start();
        }

        pizzeria.start();

        Semaphore stopSemaphore = new Semaphore(0);

        Thread stopThread = new Thread(() -> {
            System.out.println("Press ENTER to stop the Pizzeria...");
            try {
                System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stopSemaphore.release();
        });

        stopThread.start();

        try {
            stopSemaphore.acquire();
        } catch (InterruptedException e) {
            System.out.println("Received interrupt signal, stopping Pizzeria...");
        }

        System.out.println("Received STOP command. "
                + "Interrupting threads and shutting down Pizzeria...");

        pizzeria.stop();
        for (Customer customer : customers) {
            customer.interrupt();
        }
    }
}
