package tg.espetinho.service;

import tg.espetinho.web.dto.*;

import java.util.List;

public interface CustomerService {
    List<FindResponseDTO> findAllCustomers();
    FindResponseDTO findCustomerById(FindRequestDTO requestDTO);
    CreateResponseDTO createCustomer(CreateRequestDTO customerRequestDTO);
    void updateCustomer(UpdateRequestDTO requestDTO);
    void deleteCustomer(DeleteRequestDTO dto);
}
