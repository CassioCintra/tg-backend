package tg.espetinho.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tg.espetinho.entity.Product;
import tg.espetinho.repository.ProductRepository;
import tg.espetinho.web.dto.*;
import tg.espetinho.web.dto.mapper.ProductMapper;
import tg.espetinho.web.exception.EntityNotFoundException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductResponseDTO createProduct(CreateRequestDTO requestDTO) {
        Product product = productMapper.toEntity(requestDTO);
        productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Transactional
    public List<ProductResponseDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public ProductResponseDTO findProductById(FindByIdRequestDTO requestDTO) {
        return productRepository.findById(requestDTO.id())
                .map(productMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Product not found with the ID: %s", requestDTO.id()))
                );
    }

    @Transactional
    public ProductResponseDTO findProductByName(FindByNameRequestDTO requestDTO) {
        return productRepository.findProductByName(requestDTO.name())
                .map(productMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Product not found with the NAME: %s", requestDTO.name())
                ));
    }

    @Transactional
    public void updateProduct(UpdateRequestDTO requestDTO) {
        productRepository.findById(requestDTO.id())
                .ifPresentOrElse(product -> productMapper.toEntity(product, requestDTO),
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Product not found with ID: %s", requestDTO.id()));
                        }
                );
    }

    @Transactional
    public void deleteProduct(DeleteRequestDTO requestDTO) {
        productRepository.findById(requestDTO.id())
                .ifPresentOrElse(product -> productRepository.deleteById(requestDTO.id()),
                        () -> {
                            throw new EntityNotFoundException(
                                    String.format("Product not found with ID: %s", requestDTO.id()));
                        }
                );
    }
}
