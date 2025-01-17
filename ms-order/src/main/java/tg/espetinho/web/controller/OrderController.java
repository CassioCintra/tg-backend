package tg.espetinho.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.espetinho.service.OrderService;
import tg.espetinho.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/search/customer")
    public ResponseEntity<List<OrderResponseDTO>> findClientOrders(@RequestBody FindByClientDTO dto){
        List<OrderResponseDTO> orders = orderService.findAllOrdersByClient(dto);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAllOrders(){
        List<OrderResponseDTO> orders = orderService.findAllOrders();
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody CreateRequestDTO requestDTO){
        OrderResponseDTO order = orderService.createOrder(requestDTO);
        return ResponseEntity.ok().body(order);
    }

    @PutMapping
    public ResponseEntity<Void> updateOrder(@RequestBody UpdateRequestDTO requestDTO){
        orderService.updateOrder(requestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOrder(@RequestBody DeleteRequestDTO requestDTO){
        orderService.deleteOrder(requestDTO);
        return ResponseEntity.noContent().build();
    }


}
