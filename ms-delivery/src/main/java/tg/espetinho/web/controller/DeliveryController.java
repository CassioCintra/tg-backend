package tg.espetinho.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.espetinho.service.DeliveryService;
import tg.espetinho.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<FindResponseDTO>> findAllDeliveries() {
        List<FindResponseDTO> deliveryList = deliveryService.findAllDeliveries();
        return ResponseEntity.ok().body(deliveryList);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<FindResponseDTO> findDeliveryById(
            @RequestBody FindByIdRequestDTO requestDTO) {
        FindResponseDTO deliveryDTO = deliveryService.findDeliveryById(requestDTO);
        return ResponseEntity.ok().body(deliveryDTO);
    }

    @GetMapping(value = "/search/order")
    public ResponseEntity<FindResponseDTO> findDeliveryByOrderId(
            @RequestBody FindByOrderIdDTO requestDTO) {
        FindResponseDTO responseDTO = deliveryService.findDeliveryByOrder(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDTO> createDelivery(
            @RequestBody CreateRequestDTO requestDTO) {
        CreateResponseDTO responseDTO = deliveryService.createDelivery(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(
            @Valid @RequestBody UpdateRequestDTO requestDTO) {
        deliveryService.updateDelivery(requestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(
            @RequestBody DeleteRequestDTO requestDTO) {
        deliveryService.deleteDeliveryById(requestDTO);
        return ResponseEntity.noContent().build();
    }
}