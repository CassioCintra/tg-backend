package tg.espetinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tg.espetinho.model.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
   @Query(value = "SELECT * FROM pedido_produto oi " +
           "WHERE oi.cod_pedido = ?1", nativeQuery = true)
   List<CartItem> findItemsByOrder(Long orderId);

   void deleteAllByOrderId(Long OrderId);

}
