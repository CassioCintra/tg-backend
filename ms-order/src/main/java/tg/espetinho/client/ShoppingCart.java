package tg.espetinho.client;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import tg.espetinho.client.dto.DeleteItemsRequestDTO;
import tg.espetinho.client.dto.OrderItemDTO;
import tg.espetinho.client.dto.UpdateItemDTO;

import java.util.List;

@FeignClient(name = "shopping-cart", url = "http://localhost:8083/v1/shopping-cart")
public interface ShoppingCart {
    @GetMapping("search/order")
    List<OrderItemDTO> getItemsByOrder(Long id);

    @PostMapping
    OrderItemDTO createItem();

    @PutMapping
    OrderItemDTO updateItem(UpdateItemDTO updateItemDTO);

    @DeleteMapping
    OrderItemDTO deleteItem(Long id);

    @DeleteMapping("delete/order")
    DeleteItemsRequestDTO deleteItemsByOrderId(Long orderId);
}
