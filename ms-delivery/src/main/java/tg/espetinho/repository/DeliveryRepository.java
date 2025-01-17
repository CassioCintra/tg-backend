package tg.espetinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tg.espetinho.entity.Delivery;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

   @Query(value = "SELECT * FROM entrega d " +
           "WHERE d.cod_pedido = ?",
           nativeQuery = true)
   Optional<Delivery> findDeliveryByOrder(Long orderId);

}
