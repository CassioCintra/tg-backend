package tg.espetinho.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import tg.espetinho.data.TestData;
import tg.espetinho.web.dto.CreateResponseDTO;
import tg.espetinho.web.dto.FindResponseDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CustomerControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testFindAllCustomers() {
        webTestClient.get()
                .uri("/v1/customer")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(FindResponseDTO.class)
                .value(customers -> {
                    assertNotNull(customers, "Customer list should not be null");
                    assertFalse(customers.isEmpty(), "Customer list should not be empty");
                });
    }

    @Test
    public void testFindCustomerById() {
        webTestClient.post()
                .uri("/v1/customer/search")
                .bodyValue(TestData.FIND_REQUEST_DTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(FindResponseDTO.class)
                .value(response -> {
                    assertNotNull(response, "Response should not be null");
                    assertEquals(TestData.CUSTOMER.getName(), response.nome(), "Name should match");
                    assertEquals(TestData.CUSTOMER.getTelephone(), response.telephone(), "Telephone should match");
                });
    }

    @Test
    public void testCreateCustomer() {
          webTestClient.post()
                .uri("/v1/customer")
                .bodyValue(TestData.CREATE_REQUEST_DTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CreateResponseDTO.class)
                .value(response -> {
                    assertNotNull(response, "Response should not be null");
                    assertEquals(TestData.CUSTOMER.getTelephone(), response.telephone(), "CPF should match");
                });
    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}