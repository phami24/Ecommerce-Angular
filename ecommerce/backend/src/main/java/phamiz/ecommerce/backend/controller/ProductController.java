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
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws ProductException {
        Product product = productService.findProductById(id);
        if (product == null) {
            logger.error("Can not found product!");
        }
        logger.info("Product found with id : " + id);
        ProductDTO productDTO = productService.toDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductDTO>> findProductByFilter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) List<String> color,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) String sort,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) throws ProductException {
        Page<ProductDTO> response = productService.getAllProduct(
                category, color, minPrice, maxPrice,
                sort, pageNumber, pageSize);
        System.out.println(response);
        logger.info("Filter products success !");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
