package tg.espetinho.web.dto.mapper;

import org.springframework.stereotype.Component;
import tg.espetinho.entity.Customer;
import tg.espetinho.web.dto.CreateRequestDTO;
import tg.espetinho.web.dto.CreateResponseDTO;
import tg.espetinho.web.dto.FindResponseDTO;
import tg.espetinho.web.dto.UpdateRequestDTO;

@Component
public class CustomerMapper {

   public Customer toEntity(CreateRequestDTO requestDTO) {
      return new Customer(
              requestDTO.cpf(),
              requestDTO.name(),
              requestDTO.telephone()
      );
   }

   public Customer toEntity(UpdateRequestDTO requestDTO){
      return new Customer(
              requestDTO.cpf(),
              requestDTO.name(),
              requestDTO.telephone()
      );
   }

   public void toEntity(Customer customer, UpdateRequestDTO requestDTO){
      customer.setCpf(requestDTO.cpf());
      customer.setName(requestDTO.name());
      customer.setTelephone(requestDTO.telephone());
   }

   public CreateResponseDTO toResponseDTO(Customer customer) {
      return new CreateResponseDTO(
              customer.getName(),
              customer.getTelephone()
      );
   }

   public FindResponseDTO toFindResponseDTO(Customer customer){
      return new FindResponseDTO(
              customer.getName(),
              customer.getTelephone()
      );
   }

}
