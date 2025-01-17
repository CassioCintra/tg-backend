package tg.espetinho.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Entity
@Table(name = "pedido_produto")
public class CartItem {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "codigo")
   private Long id;

   @Column(name = "cod_pedido")
   private Long orderId;

   @Column(name = "cod_produto")
   private Long productId;

   @Column(name = "quantidade")
   private Integer quantity;

   @Column(name = "preco_unidade")
   private Double unitPrice;

   @Column(name = "nome_produto")
   private String name;

   @Autowired
   public CartItem() {
   }

   public CartItem(Long orderId, Long productId, Integer quantity,
                   Double unitPrice, String name) {
      this.orderId = orderId;
      this.productId = productId;
      this.quantity = quantity;
      this.unitPrice = unitPrice;
      this.name = name;
   }

   public CartItem(Long id, Long orderId, Long productId, Integer quantity,
                   Double unitPrice, String name) {
      this.id = id;
      this.orderId = orderId;
      this.productId = productId;
      this.name = name;
      this.quantity = quantity;
      this.unitPrice = unitPrice;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getOrder() {
      return orderId;
   }

   public void setOrder(Long orderId) {
      this.orderId = orderId;
   }

   public Long getProduct() {
      return productId;
   }

   public void setProduct(Long productId) {
      this.productId = productId;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   public Double getUnitPrice() {
      return unitPrice;
   }

   public void setUnitPrice(Double unitPrice) {
      this.unitPrice = unitPrice;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CartItem cartItem = (CartItem) o;
      return Objects.equals(id, cartItem.id);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }
}
