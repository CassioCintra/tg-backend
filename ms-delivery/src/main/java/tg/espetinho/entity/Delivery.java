package tg.espetinho.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "entrega")
public class Delivery {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "codigo")
   private Long id;
   @Column(name = "cod_pedido")
   private Long orderId;
   @Column(name = "endereco")
   private String deliveryAddress;
   @Column(name = "data_entrega")
   private LocalDateTime deliveryDate;
   @Enumerated(EnumType.STRING)
   @Column(name = "status_entrega")
   private DeliveryStatus deliveryStatus;
   @Column(name = "codigo_seguranca")
   private String securityCode;

   public Delivery() {
   }

   public Delivery(Long orderId, String deliveryAddress, LocalDateTime deliveryDate, DeliveryStatus deliveryStatus) {
      this.orderId = orderId;
      this.deliveryAddress = deliveryAddress;
      this.deliveryDate = deliveryDate;
      this.deliveryStatus = deliveryStatus;
   }

   public Delivery(Long orderId, String deliveryAddress,
                   LocalDateTime deliveryDate, DeliveryStatus deliveryStatus,
                   String securityCode) {
      this.orderId = orderId;
      this.deliveryAddress = deliveryAddress;
      this.deliveryDate = deliveryDate;
      this.deliveryStatus = deliveryStatus;
      this.securityCode = securityCode;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getOrderId() {
      return orderId;
   }

   public void setOrderId(Long orderId) {
      this.orderId = orderId;
   }

   public String getDeliveryAddress() {
      return deliveryAddress;
   }

   public void setDeliveryAddress(String deliveryAddress) {
      this.deliveryAddress = deliveryAddress;
   }

   public LocalDateTime getDeliveryDate() {
      return deliveryDate;
   }

   public void setDeliveryDate(LocalDateTime deliveryDate) {
      this.deliveryDate = deliveryDate;
   }

   public DeliveryStatus getDeliveryStatus() {
      return deliveryStatus;
   }

   public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
      this.deliveryStatus = deliveryStatus;
   }

   public String getSecurityCode() {
      return securityCode;
   }

   public void setSecurityCode(String securityCode) {
      this.securityCode = securityCode;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Delivery delivery = (Delivery) o;
      return Objects.equals(id, delivery.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
