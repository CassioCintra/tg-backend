package tg.espetinho.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tg.espetinho.entity.Customer;
import tg.espetinho.web.dto.*;
import tg.espetinho.web.exception.EntityNotFoundException;
import tg.espetinho.web.exception.EntityAlreadyExistsException;
import tg.espetinho.repository.CustomerRepository;
import tg.espetinho.web.dto.mapper.CustomerMapper;

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
    public FindResponseDTO findCustomerById(String cpf) {
        return customerRepository.findById(cpf)
                .map(customerMapper::toFindResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Customer not found with the CPF: %s", cpf))
                );
    }

    @Transactional
    public CreateResponseDTO createCustomer(CreateRequestDTO customerRequestDTO) {
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
    public void updateCustomer(String cpf, UpdateRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.toEntity(customerRequestDTO);
        customer.setCpf(cpf);
        customerRepository.findById(customer.getCpf())
                .ifPresentOrElse(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setTelephone(customer.getTelephone());
                    customerRepository.save(existingCustomer);
                }, () -> {
                    throw new EntityNotFoundException(
                            String.format("Customer not found with CPF: %s", customerRequestDTO.cpf())
                    );
                });
    }

    @Transactional
    public void deleteCustomer(DeleteRequestDTO dto) {
        customerRepository
                .findById(dto.cpf())
                .ifPresentOrElse(
                        customer -> customerRepository.deleteById(dto.cpf()),
                        () -> { throw new EntityNotFoundException(
                                String.format("Customer with CPF %s not found", dto.cpf()));
                        }
                );
    }
}
