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

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor

public class HomeController {

    private final IProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(phamiz.ecommerce.backend.controller.ProductController.class);

    @GetMapping("/get-new")
    public ResponseEntity<List<ProductDTO>> getNewProduct() {
        List<ProductDTO> listProduct = productService.getNewProduct();
        logger.info(("Get new product !!!"));
        return ResponseEntity.ok(listProduct);
    }
    @GetMapping("/get-random")
    public ResponseEntity<List<ProductDTO>> getRandomProduct() {
        List<ProductDTO> listProduct = productService.getRandomProduct();
        logger.info(("Get random product !!!"));
        return ResponseEntity.ok(listProduct);
    }
}


