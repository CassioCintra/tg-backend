package tg.espetinho.web.mapper;

import org.springframework.stereotype.Component;
import tg.espetinho.entity.Order;
import tg.espetinho.web.dto.CreateRequestDTO;
import tg.espetinho.client.dto.OrderItemDTO;
import tg.espetinho.web.dto.OrderResponseDTO;
import tg.espetinho.web.dto.UpdateRequestDTO;

import java.util.List;

@Component
public class OrderMapper {
    public OrderResponseDTO toResponseDTO(Order order, List<OrderItemDTO> items) {
        return new OrderResponseDTO(
                order.getCodOrder(),
                order.getAmount(),
                order.getOrderDate(),
                order.getPaymentType(),
                order.getPaymentStatus(),
                items
        );
    }

    public Order toEntity(UpdateRequestDTO request){
        return new Order(
                request.id(),
                request.customerCPF(),
                request.paymentType(),
                request.paymentStatus()
        );
    }

    public void toEntity(Order order, UpdateRequestDTO orderDTO){
        order.setCodOrder(orderDTO.id());
        order.setCustomerCPF(orderDTO.customerCPF());
        order.setPaymentType(orderDTO.paymentType());
        order.setPaymentStatus(orderDTO.paymentStatus());
    }

    public Order toEntity(CreateRequestDTO request) {
        return new Order(
                request.customerCPF(),
                request.paymentType(),
                request.paymentStatus()
        );
    }

}
