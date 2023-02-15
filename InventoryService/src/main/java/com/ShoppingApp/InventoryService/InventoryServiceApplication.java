package com.ShoppingApp.InventoryService;

import com.ShoppingApp.InventoryService.model.Inventory;
import com.ShoppingApp.InventoryService.repository.InventoryRepository;
import com.ShoppingApp.InventoryService.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setSkuCode("iphone_13");
            inventory.setQuantity(100);

            Inventory inventory1 = new Inventory();
            inventory1.setSkuCode("iphone_13_red");
            inventory1.setQuantity(0);

            Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("iphone_13_blue");
            inventory2.setQuantity(50);

            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);

        };
    }
}
