package tg.espetinho.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tg.espetinho.entity.Delivery;
import tg.espetinho.repository.DeliveryRepository;
import tg.espetinho.web.dto.*;
import tg.espetinho.web.dto.mapper.DeliveryMapper;
import tg.espetinho.web.exception.EntityNotFoundException;

import java.util.List;
import java.security.SecureRandom;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    public DeliveryService(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
    }

    @Transactional
    public List<FindResponseDTO> findAllDeliveries() {
        return deliveryRepository.findAll()
                .stream()
                .map(deliveryMapper::toFindResponseDTO)
                .toList();
    }

    @Transactional
    public FindResponseDTO findDeliveryByOrder(FindByOrderIdDTO requestDTO) {
        return deliveryRepository
                .findDeliveryByOrder(requestDTO.orderId())
                .map(deliveryMapper::toFindResponseDTO)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Delivery not found for order ID: " + requestDTO.orderId())
                );
    }


    @Transactional
    public FindResponseDTO findDeliveryById(FindByIdRequestDTO requestDTO) {
        return deliveryRepository
                .findById(requestDTO.id())
                .map(deliveryMapper::toFindResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Item not found with the ID: %s", requestDTO.id())));
    }

    @Transactional
    public CreateResponseDTO createDelivery(CreateRequestDTO requestDTO) {
        Delivery delivery = deliveryMapper.toEntity(requestDTO);
        String securityCode = generateSecurityCode();
        delivery.setSecurityCode(securityCode);
        deliveryRepository.save(delivery);
        return deliveryMapper.toResponseDTO(delivery);
    }

    @Transactional
    public void updateDelivery(UpdateRequestDTO requestDTO) {
        deliveryRepository.findById(requestDTO.id())
                .ifPresentOrElse(delivery -> deliveryMapper.toEntity(delivery, requestDTO),
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Delivery not found with ID: %s", requestDTO.id()));
                        });
    }

    @Transactional
    public void deleteDeliveryById(DeleteRequestDTO requestDTO) {
        deliveryRepository.findById(requestDTO.id())
                .ifPresentOrElse(existingDelivery ->
                                deliveryRepository.deleteById(requestDTO.id()),
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Delivery not found with ID: %s", requestDTO.id()));
                        });
    }

    private String generateSecurityCode() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder securityCode = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = secureRandom.nextInt(10);
            securityCode.append(digit);
        }

        return securityCode.toString();
    }

}
