package tg.espetinho.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.espetinho.service.ProductService;
import tg.espetinho.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        List<ProductResponseDTO> productList = productService.findAllProducts();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping(value = "/search/id")
    public ResponseEntity<ProductResponseDTO> findProductById(@RequestBody FindByIdRequestDTO requestDTO) {
        ProductResponseDTO productResponseDTO = productService.findProductById(requestDTO);
        return ResponseEntity.ok().body(productResponseDTO);
    }

    @GetMapping(value = "/search/name")
    public ResponseEntity<ProductResponseDTO> findProductByName(@RequestBody FindByNameRequestDTO requestDTO) {
        ProductResponseDTO productResponseDTO = productService.findProductByName(requestDTO);
        return ResponseEntity.ok().body(productResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateRequestDTO requestDTO) {
        ProductResponseDTO productResponseDTO = productService.createProduct(requestDTO);
        return ResponseEntity.ok().body(productResponseDTO);
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody UpdateRequestDTO requestDTO) {
        productService.updateProduct(requestDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "code-product")
    public ResponseEntity<Void> deleteProduct(@RequestBody DeleteRequestDTO requestDTO) {
        productService.deleteProduct(requestDTO);
        return ResponseEntity.noContent().build();
    }
}