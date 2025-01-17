package tg.espetinho.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tg.espetinho.service.CustomerService;
import tg.espetinho.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<FindResponseDTO>> findAllCustomers() {
        List<FindResponseDTO> customerList = customerService.findAllCustomers();
        return ResponseEntity.ok().body(customerList);
    }

    @GetMapping(value= "/search", params = "cpf")
    public ResponseEntity<FindResponseDTO> findCustomerById(@RequestParam String cpf) {
        FindResponseDTO customerResponseDTO = customerService.findCustomerById(cpf);
        return ResponseEntity.ok().body(customerResponseDTO);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDTO> createCustomer(
                                                @RequestBody CreateRequestDTO customerRequestDTO) {
        CreateResponseDTO customerDTO = customerService.createCustomer(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
    }

    @PutMapping(params = "cpf")
    public ResponseEntity<Void> updateCustomer(@RequestParam String cpf,
                                               @RequestBody UpdateRequestDTO customerRequestDTO) {
        customerService.updateCustomer(cpf, customerRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "cpf")
    public ResponseEntity<Void> deleteCustomer(@RequestParam DeleteRequestDTO dto) {
        customerService.deleteCustomer(dto);
        return ResponseEntity.noContent().build();
    }
}
