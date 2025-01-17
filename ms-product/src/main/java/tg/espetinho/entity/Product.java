package tg.espetinho.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Entity
@Table(name = "produto")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "codigo")
   private Long productCode;
   @Column(name = "nome")
   private String name;
   @Column(name = "descricao")
   private String description;
   @Column(name = "preco_unidade")
   private Double price;
   @Column(name = "quantidade")
   private Integer quantity ;
   @Column(name = "url_image")
   private String urlImage;
   @Column(name = "ativo")
   private Boolean active;

   @Autowired
   public Product() {
   }


   public Product(String name, String description, Double price, Integer quantity, String urlImage, Boolean active) {
      this.name = name;
      this.description = description;
      this.price = price;
      this.quantity = quantity;
      this.urlImage = urlImage;
      this.active = active;
   }

   public Product(Long productCode, String name, String description,
                  Double price, Integer quantity, String urlImage, Boolean active) {
      this.productCode = productCode;
      this.name = name;
      this.description = description;
      this.price = price;
      this.quantity = quantity;
      this.urlImage = urlImage;
      this.active = active;
   }

   public Long getProductCode() {
      return productCode;
   }

   public void setProductCode(Long productCode) {
      this.productCode = productCode;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   public Boolean getActive() {
      return active;
   }

   public void setActive(Boolean active) {
      this.active = active;
   }

   public String getUrlImage() {
      return urlImage;
   }

   public void setUrlImage(String urlImage) {
      this.urlImage = urlImage;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Product product = (Product) o;
      return Objects.equals(productCode, product.productCode);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(productCode);
   }
}
