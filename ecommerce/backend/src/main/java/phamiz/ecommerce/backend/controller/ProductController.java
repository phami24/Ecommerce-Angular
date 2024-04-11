package phamiz.ecommerce.backend.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phamiz.ecommerce.backend.dto.Product.ProductDTO;
import phamiz.ecommerce.backend.exception.ProductException;
import phamiz.ecommerce.backend.model.Product;
import phamiz.ecommerce.backend.service.IProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<ProductDTO> listProduct = productService.findAllProduct();
        logger.info(("Get all product !!!"));
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductException {
        Product product = productService.findProductById(id);
        if (product == null) {
            logger.error("Can not found product!");
        }
        logger.info("Product found with id : " + id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/filter")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(
            @RequestParam String category, @RequestParam List<String> color,
            @RequestParam List<String> size, @RequestParam Integer minPrice,
            @RequestParam Integer maxPrice, @RequestParam String sort,
            @RequestParam String stock, @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        Page<Product> response = productService.getAllProduct(
                category, color, size, minPrice, maxPrice,
                sort, stock, pageNumber, pageSize);
        logger.info("Filter products success !");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
