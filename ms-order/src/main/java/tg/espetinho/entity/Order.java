package tg.espetinho.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "pedido")
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codOrder;

    @Column(name = "cpf_cliente")
    private String customerCPF;

    @Column(name = "data_pedido")
    private LocalDateTime orderDate;

    @Column(name = "valor")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento")
    private PaymentStatus paymentStatus;

    public Order(String customerCPF,
                 PaymentType paymentType, PaymentStatus paymentStatus) {
        this.customerCPF = customerCPF;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
    }

    public Order(Long id, String cpf, PaymentType paymentType, PaymentStatus paymentStatus) {
        this.codOrder = id;
        this.customerCPF = cpf;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(codOrder, order.codOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codOrder);
    }
}
