package tg.espetinho.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tg.espetinho.entity.Customer;
import tg.espetinho.repository.CustomerRepository;
import tg.espetinho.service.CustomerService;
import tg.espetinho.web.dto.*;
import tg.espetinho.web.dto.mapper.CustomerMapper;
import tg.espetinho.web.exception.EntityAlreadyExistsException;
import tg.espetinho.web.exception.EntityNotFoundException;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public List<FindResponseDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toFindResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public FindResponseDTO findCustomerById(FindRequestDTO requestDTO) {
        validateDto(requestDTO);
        return customerRepository.findById(requestDTO.cpf())
                .map(customerMapper::toFindResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Customer not found with the CPF: %s", requestDTO.cpf()))
                );
    }

    @Override
    @Transactional
    public CreateResponseDTO createCustomer(CreateRequestDTO customerRequestDTO) {
        validateDto(customerRequestDTO);
        customerRepository.findById(customerRequestDTO.cpf())
                .ifPresent(customer -> {
                    throw new EntityAlreadyExistsException(
                            String.format("Customer %s already exists.", customer.getName())
                    );
                });
        Customer customer = customerMapper.toEntity(customerRequestDTO);
        customerRepository.save(customer);
        return customerMapper.toResponseDTO(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(UpdateRequestDTO requestDTO) {
        validateDto(requestDTO);
        customerRepository.findById(requestDTO.cpf())
                .ifPresentOrElse(existingCustomer -> customerMapper.toEntity(existingCustomer, requestDTO),
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Customer not found with CPF: %s", requestDTO.cpf())
                            );
                        }
                );
    }

    @Override
    @Transactional
    public void deleteCustomer(DeleteRequestDTO dto) {
        validateDto(dto);
        customerRepository.findById(dto.cpf())
                .ifPresentOrElse(
                        customer -> customerRepository.deleteById(dto.cpf()),
                        () -> { throw new EntityNotFoundException(
                                String.format("Customer with CPF %s not found", dto.cpf()));
                        }
                );
    }

    private void validateDto(Object dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO cannot be null.");
        }

        for (Field field : dto.getClass().getDeclaredFields()) {
            extractFieldValue(dto, field);
        }
    }

    private void extractFieldValue(Object dto, Field field){
        try {
            field.setAccessible(true);
            Object value = field.get(dto);
            validateField(field, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing field: " + field.getName(), e);
        }
    }

    private void validateField(Field field, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(field.getName().toUpperCase() + " cannot be null.");
        } else if (value instanceof String string && string.isBlank()) {
            throw new IllegalArgumentException(field.getName().toUpperCase() + " cannot be empty or blank.");
        }
    }
}
