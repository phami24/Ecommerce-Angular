package phamiz.ecommerce.backend.service;

import org.springframework.data.domain.Page;
import phamiz.ecommerce.backend.dto.Product.CreateProductRequest;
import phamiz.ecommerce.backend.dto.Product.ProductDTO;
import phamiz.ecommerce.backend.exception.ProductException;
import phamiz.ecommerce.backend.model.Product;

import java.util.List;

public interface IProductService {
    public List<ProductDTO> findAllProduct();
    public Product createProduct(CreateProductRequest request);
    public String deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product req) throws  ProductException;
    public Product findProductById(Long id) throws ProductException;
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes,
                                       Integer minPrice, Integer maxPrice, String sort,
                                       String stock, Integer pageNumber, Integer pageSize);
}
