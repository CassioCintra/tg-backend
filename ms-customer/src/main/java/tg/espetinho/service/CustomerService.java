package tg.espetinho.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import tg.espetinho.entity.Customer;
import tg.espetinho.web.dto.*;
import tg.espetinho.web.exception.EntityNotFoundException;
import tg.espetinho.web.exception.EntityAlreadyExistsException;
import tg.espetinho.repository.CustomerRepository;
import tg.espetinho.web.dto.mapper.CustomerMapper;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public List<FindResponseDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toFindResponseDTO)
                .toList();
    }

    @Transactional
    public FindResponseDTO findCustomerById(FindRequestDTO requestDTO) {
        validateDto(requestDTO);
        return customerRepository.findById(requestDTO.cpf())
                .map(customerMapper::toFindResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Customer not found with the CPF: %s", requestDTO.cpf()))
                );
    }

    @Transactional
    public CreateResponseDTO createCustomer(CreateRequestDTO customerRequestDTO) {
        validateDto(customerRequestDTO);
        Customer customer = customerMapper.toEntity(customerRequestDTO);
        customerRepository.findById(customer.getCpf())
                .ifPresent(c -> {
                    throw new EntityAlreadyExistsException(
                            String.format("Customer %s already exists.", c.getName())
                    );
                });
        customerRepository.save(customer);
        return customerMapper.toResponseDTO(customer);
    }

    @Transactional
    public void updateCustomer(UpdateRequestDTO requestDTO) {
        validateDto(requestDTO);
        Customer customer = customerMapper.toEntity(requestDTO);
        customerRepository.findById(customer.getCpf())
                .ifPresentOrElse(existingCustomer -> {
                        existingCustomer.setName(customer.getName());
                        existingCustomer.setTelephone(customer.getTelephone());
                        customerRepository.save(existingCustomer);
                    },
                    () -> {
                        throw new EntityNotFoundException(
                                String.format("Customer not found with CPF: %s", requestDTO.cpf())
                        );
                    }
                );
    }

    @Transactional
    public void deleteCustomer(DeleteRequestDTO dto) {
        validateDto(dto);
        customerRepository
                .findById(dto.cpf())
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
            field.setAccessible(true);
            try {
                Object value = field.get(dto);
                validateField(field, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field: " + field.getName(), e);
            }
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
