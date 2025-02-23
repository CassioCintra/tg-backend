package tg.espetinho.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tg.espetinho.data.TestData;
import tg.espetinho.repository.CustomerRepository;
import tg.espetinho.web.dto.CreateResponseDTO;
import tg.espetinho.web.dto.FindResponseDTO;
import tg.espetinho.web.dto.mapper.CustomerMapper;
import tg.espetinho.web.exception.EntityAlreadyExistsException;
import tg.espetinho.web.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
   void findAllCustomers() {
        when(customerRepository.findAll()).thenReturn(List.of(TestData.CUSTOMER));
        when(customerMapper.toFindResponseDTO(TestData.CUSTOMER)).thenReturn(TestData.FIND_RESPONSE_DTO);

        List<FindResponseDTO> result = customerService.findAllCustomers();

        assertEquals(1, result.size());
        assertEquals(TestData.FIND_RESPONSE_DTO, result.getFirst());
   }

    @Test
    void findCustomerById_shouldReturnCustomer_whenFound() {
        when(customerRepository.findById(TestData.CUSTOMER.getCpf()))
                .thenReturn(Optional.of(TestData.CUSTOMER));
        when(customerMapper.toFindResponseDTO(TestData.CUSTOMER))
                .thenReturn(TestData.FIND_RESPONSE_DTO);

        FindResponseDTO result = customerService.findCustomerById(TestData.FIND_REQUEST_DTO);

        assertEquals(TestData.FIND_RESPONSE_DTO, result);
    }

//    @Test
//    void findCustomerById_shouldThrowException_whenCpfIsNull() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> customerService.findCustomerById(null)
//        );
//
//        assertEquals("CPF cannot be null", exception.getMessage());
//    }

    @Test
    void findCustomerById_shouldThrowException_whenCpfIsEmpty() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.findCustomerById(TestData.FIND_EMPTY_REQUEST_DTO)
        );

        assertEquals("CPF cannot be empty or blank.", exception.getMessage());
    }

    @Test
    void findCustomerById_shouldThrowException_whenNotFound() {
        when(customerRepository.findById(TestData.CUSTOMER.getCpf()))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> customerService.findCustomerById(TestData.FIND_REQUEST_DTO)
        );

        assertEquals("Customer not found with the CPF: 12345678901", exception.getMessage());
    }

    @Test
    void createCustomer_shouldSaveCustomer_whenNotExists() {
        when(customerMapper.toEntity(TestData.CREATE_REQUEST_DTO))
                .thenReturn(TestData.CUSTOMER);
        when(customerRepository.findById(TestData.CUSTOMER.getCpf()))
                .thenReturn(Optional.empty());
        when(customerMapper.toResponseDTO(TestData.CUSTOMER))
                .thenReturn(TestData.CREATE_RESPONSE_DTO);

        CreateResponseDTO result = customerService.createCustomer(TestData.CREATE_REQUEST_DTO);

        assertEquals(TestData.CREATE_RESPONSE_DTO, result);
        verify(customerRepository, times(1)).save(TestData.CUSTOMER);
    }

    @Test
    void createCustomer_shouldThrowException_whenAlreadyExists() {
        when(customerMapper.toEntity(TestData.CREATE_REQUEST_DTO))
                .thenReturn(TestData.CUSTOMER);
        when(customerRepository.findById(TestData.CUSTOMER.getCpf()))
                .thenReturn(Optional.of(TestData.CUSTOMER));

        EntityAlreadyExistsException exception = assertThrows(
                EntityAlreadyExistsException.class,
                () -> customerService.createCustomer(TestData.CREATE_REQUEST_DTO)
        );

        assertEquals("Customer John Doe already exists.", exception.getMessage());
    }

    @Test
    void updateCustomer_shouldUpdateCustomer_whenFound() {
        when(customerMapper.toEntity(TestData.UPDATE_REQUEST_DTO)).thenReturn(TestData.CUSTOMER);
        when(customerRepository.findById(TestData.CUSTOMER.getCpf())).thenReturn(Optional.of(TestData.CUSTOMER));

        customerService.updateCustomer(TestData.UPDATE_REQUEST_DTO);

        verify(customerRepository, times(1)).save(TestData.CUSTOMER);
    }

    @Test
    void updateCustomer_shouldThrowException_whenNotFound() {
        when(customerRepository.findById(TestData.CUSTOMER.getCpf()))
                .thenReturn(Optional.empty());
        when(customerMapper.toEntity(TestData.UPDATE_REQUEST_DTO))
                .thenReturn(TestData.CUSTOMER);

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> customerService.updateCustomer(TestData.UPDATE_REQUEST_DTO)
        );

        assertEquals("Customer not found with CPF: 123456789", exception.getMessage());
    }

    @Test
    void deleteCustomer_shouldDeleteCustomer_whenFound() {
        when(customerRepository.findById(TestData.CUSTOMER.getCpf()))
                .thenReturn(Optional.of(TestData.CUSTOMER));

        customerService.deleteCustomer(TestData.DELETE_REQUEST_DTO);

        verify(customerRepository, times(1)).deleteById(TestData.CUSTOMER.getCpf());
    }

    @Test
    void deleteCustomer_shouldThrowException_whenNotFound() {
        when(customerRepository.findById(TestData.CUSTOMER.getCpf())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> customerService.deleteCustomer(TestData.DELETE_REQUEST_DTO)
        );

        assertEquals("Customer with CPF 12345678901 not found", exception.getMessage());
    }
}