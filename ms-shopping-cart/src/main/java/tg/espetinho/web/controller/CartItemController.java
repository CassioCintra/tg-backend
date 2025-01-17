package tg.espetinho.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.espetinho.service.CartItemService;
import tg.espetinho.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/v1/shopping-cart")
@RequiredArgsConstructor
public class CartItemController {
   private final CartItemService itemService;

   @GetMapping
   public ResponseEntity<List<CartResponseDTO>> getAllOrderItems(){
      List<CartResponseDTO> responseDTO = itemService.findAllItems();
      return ResponseEntity.ok().body(responseDTO);
   }

   @GetMapping(value = "/search/id")
   public ResponseEntity<CartResponseDTO> getOrderItemById(@RequestBody FindRequestDTO requestDTO){
      CartResponseDTO responseDTO = itemService.findOrderItemById(requestDTO);
      return ResponseEntity.ok().body(responseDTO);
   }

   @GetMapping(value = "/search/order")
   public ResponseEntity<List<CartResponseDTO>> getItemsByOrder(@RequestBody FindRequestDTO requestDTO){
      List<CartResponseDTO> responseDTOS = itemService.findItemsByOrder(requestDTO);
      return ResponseEntity.ok().body(responseDTOS);
   }

   @PostMapping
   public ResponseEntity<CartResponseDTO> createOrderItem(
                                                @RequestBody CreateRequestDTO itemRequestDTO){
      CartResponseDTO responseDTO = itemService.createOrderItem(itemRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
   }

   @PutMapping
   public ResponseEntity<Void> updateOrderItem(@RequestBody UpdateRequestDTO requestDTO){
      itemService.updateOrderItem(requestDTO);
      return ResponseEntity.noContent().build();
   }

   @DeleteMapping
   public ResponseEntity<Void> deleteOrderItem(@RequestBody DeleteRequestDTO requestDTO){
      itemService.deleteItem(requestDTO);
      return ResponseEntity.noContent().build();
   }

   @DeleteMapping(value = "/delete/order")
   public ResponseEntity<Void> deleteItemByOrderId(@RequestBody DeleteRequestDTO requestDTO){
      itemService.deleteItemByOrder(requestDTO);
      return ResponseEntity.noContent().build();
   }
}
