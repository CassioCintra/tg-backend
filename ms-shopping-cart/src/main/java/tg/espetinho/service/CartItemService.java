package tg.espetinho.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tg.espetinho.web.dto.*;
import tg.espetinho.web.dto.mapper.CartItemMapper;
import tg.espetinho.model.CartItem;
import tg.espetinho.repository.CartItemRepository;

import tg.espetinho.web.exception.EntityNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository repository;
    private final CartItemMapper mapper;

    @Transactional
    public List<CartResponseDTO> findAllItems() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public List<CartResponseDTO> findItemsByOrder(FindRequestDTO requestDTO) {
        return repository.findItemsByOrder(requestDTO.id())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public CartResponseDTO findOrderItemById(FindRequestDTO requestDTO) {
        return repository.findById(requestDTO.id())
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Item not found with the Id: %s", requestDTO.id())));
    }

    @Transactional
    public CartResponseDTO createOrderItem(CreateRequestDTO requestDTO) {
        CartItem cartItem = mapper.toEntity(requestDTO);
        repository.save(cartItem);
        return mapper.toResponse(cartItem);
    }

    @Transactional
    public void updateOrderItem(UpdateRequestDTO requestDTO) {
        repository.findById(requestDTO.id())
                .ifPresentOrElse(item -> mapper.toEntity(item, requestDTO),
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Entity not found with ID: %s", requestDTO.id()));
                        }
                );
    }

    @Transactional
    public void deleteItem(DeleteRequestDTO requestDTO) {
        repository.findById(requestDTO.id())
                .ifPresentOrElse(item -> repository.deleteById(requestDTO.id()),
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Item not found with ID: %s", requestDTO.id())
                            );
                        });
    }

    @Transactional
    public void deleteItemByOrder(DeleteRequestDTO requestDTO){
        List<CartItem> items = repository.findItemsByOrder(requestDTO.id());

        if (items.isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("Item not found in Order with ID: %s", requestDTO.id())
            );
        }
        repository.deleteAllByOrderId(requestDTO.id());
    }
}
