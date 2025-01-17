package tg.espetinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tg.espetinho.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerCPF(String cpf);
}
