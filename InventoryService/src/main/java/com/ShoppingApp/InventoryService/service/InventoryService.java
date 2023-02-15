package com.ShoppingApp.InventoryService.service;

import com.ShoppingApp.InventoryService.dto.InventoryResponse;
import com.ShoppingApp.InventoryService.model.Inventory;
import com.ShoppingApp.InventoryService.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isProductAvailable(List<String> skuCodeList) {
        return inventoryRepository.findBySkuCodeIn(skuCodeList).stream()
                .map(this::buildInventoryResponse).toList();
    }

    private InventoryResponse buildInventoryResponse(Inventory inventory) {
        InventoryResponse inventoryResponse=new InventoryResponse();
        inventoryResponse.setSkuCode(inventory.getSkuCode());
        if(inventory.getQuantity()>0) {
            inventoryResponse.setInStock(true);
        } else {
            inventoryResponse.setInStock(false);
        }

        return inventoryResponse;
    }

}
