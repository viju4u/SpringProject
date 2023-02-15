package com.ShoppingApp.OrderService.service;

import com.ShoppingApp.OrderService.dto.InventoryResponse;
import com.ShoppingApp.OrderService.dto.OrderLineItemsDto;
import com.ShoppingApp.OrderService.dto.OrderRequest;
import com.ShoppingApp.OrderService.model.Order;
import com.ShoppingApp.OrderService.model.OrderLineItems;
import com.ShoppingApp.OrderService.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {


    private final OrderServiceRepository orderServiceRepository;
    private final WebClient webClient;

    public OrderService(OrderServiceRepository orderServiceRepository, WebClient webClient) {
        this.orderServiceRepository = orderServiceRepository;
        this.webClient = webClient;
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order newOrder = new Order();
        newOrder.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.
                            getOrderLineItemsDtoList().
                            stream().
                            map(this::mappedToOrderLineItems).
                            toList();
        newOrder.setOrderLineItemsList(orderLineItemsList);

        //Call Inventory service to check whether item is in stock or not

        // By Default web client is making asynchronous call
        // We use block method for making it synchronous

        List<String> skuCodeList=newOrder.
                getOrderLineItemsList().
                stream().
                map(orderLineItems -> orderLineItems.getSkuCode()).
                toList();

         InventoryResponse[] inventoryResponses = webClient
                .get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder ->uriBuilder.queryParam("skuCode",skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean result=Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if (result) {
            orderServiceRepository.save(newOrder);
        } else {
            throw new IllegalArgumentException("Product is out of stock");
        }

    }

    private OrderLineItems mappedToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
