package phamiz.ecommerce.backend.service;

import org.springframework.data.domain.Page;
import phamiz.ecommerce.backend.dto.Product.CreateProductRequest;
import phamiz.ecommerce.backend.dto.Product.ProductDTO;
import phamiz.ecommerce.backend.exception.ProductException;
import phamiz.ecommerce.backend.model.Product;

import java.util.List;

public interface IProductService {
    ProductDTO toDTO(Product product);
    public List<ProductDTO> findAllProduct();
    public Product createProduct(CreateProductRequest request);
    public String deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product req) throws  ProductException;
    public Product findProductById(Long id) throws ProductException;
    public Page<ProductDTO> getAllProduct(String category, List<String> colors,
                                       Integer minPrice, Integer maxPrice, String sort,
                                       Integer pageNumber, Integer pageSize) throws ProductException;
    public List<ProductDTO> getNewProduct();
    List<ProductDTO> getRandomProduct();
}
