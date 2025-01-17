package tg.espetinho.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tg.espetinho.client.dto.UpdateItemDTO;
import tg.espetinho.client.mapper.CartMapper;
import tg.espetinho.entity.Order;
import tg.espetinho.client.ShoppingCart;
import tg.espetinho.web.dto.FindByClientDTO;
import tg.espetinho.client.dto.OrderItemDTO;
import tg.espetinho.repository.OrderRepository;
import tg.espetinho.web.dto.*;
import tg.espetinho.web.exception.EntityNotFoundException;
import tg.espetinho.web.mapper.OrderMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final ShoppingCart shoppingCart;
    private final CartMapper cartMapper;

    @Transactional
    public List<OrderResponseDTO> findAllOrdersByClient(FindByClientDTO findDTO) {
        List<Order> orders = repository.findByCustomerCPF(findDTO.cpf());

        return orders.stream()
                .map(order -> {
                    List<OrderItemDTO> items = shoppingCart.getItemsByOrder(order.getCodOrder());
                    return mapper.toResponseDTO(order, items);
                })
                .toList();

    }

    @Transactional
    public List<OrderResponseDTO> findAllOrders() {
        List<Order> orders = repository.findAll();

        return orders.stream()
                .map(order -> {
                    List<OrderItemDTO> items = shoppingCart.getItemsByOrder(order.getCodOrder());
                    return mapper.toResponseDTO(order, items);
                })
                .toList();
    }

    @Transactional
    public OrderResponseDTO createOrder(CreateRequestDTO requestDTO) {
        Order order = mapper.toEntity(requestDTO);
        order.setOrderDate(LocalDateTime.now());

        Double amount = calcAmount(requestDTO.items());
        order.setAmount(amount);

        repository.save(order);

        List<OrderItemDTO> items = mapItems(requestDTO);
        return mapper.toResponseDTO(order, items);
    }

    @Transactional
    public void updateOrder(UpdateRequestDTO requestDTO) {
        repository.findById(requestDTO.id())
                .ifPresentOrElse(order -> {
                            requestDTO.items()
                                    .forEach(item -> {
                                        UpdateItemDTO updateDTO = cartMapper.toUpdateItem(item);
                                        shoppingCart.updateItem(updateDTO);
                                    });
                            mapper.toEntity(order, requestDTO);
                        },
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Order not found with ID: %s", requestDTO.id())
                            );
                        }
                );
    }

    @Transactional
    public void deleteOrder(DeleteRequestDTO requestDTO) {
        repository.findById(requestDTO.id())
                .ifPresentOrElse(order -> {
                            shoppingCart.deleteItemsByOrderId(requestDTO.id());
                            repository.deleteById(order.getCodOrder());
                        },
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Order not found with ID: %s", requestDTO.id())
                            );
                        }
                );
    }

    private Double calcAmount(List<OrderItemDTO> itemsDTO) {
        if (itemsDTO.isEmpty()) {
            return 0.0;
        }

        double amount = itemsDTO.stream()
                .mapToDouble(OrderItemDTO::unitPrice)
                .sum();

        return Math.max(amount, 0.0);
    }

    private List<OrderItemDTO> mapItems(CreateRequestDTO requestDTO) {
        return requestDTO.items().isEmpty() ? new ArrayList<>() : requestDTO.items();
    }
}
