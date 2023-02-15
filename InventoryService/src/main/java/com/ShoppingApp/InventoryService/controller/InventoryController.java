package com.ShoppingApp.InventoryService.controller;

import com.ShoppingApp.InventoryService.dto.InventoryResponse;
import com.ShoppingApp.InventoryService.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService=inventoryService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isProductAvailable(@RequestParam List<String> skuCodeList) {
        return inventoryService.isProductAvailable(skuCodeList);
    }
}
