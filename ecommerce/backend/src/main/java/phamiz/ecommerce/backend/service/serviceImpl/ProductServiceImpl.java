package phamiz.ecommerce.backend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import phamiz.ecommerce.backend.dto.Product.CreateProductRequest;
import phamiz.ecommerce.backend.dto.Product.ProductDTO;
import phamiz.ecommerce.backend.dto.Product.ReviewDTO;
import phamiz.ecommerce.backend.exception.ProductException;
import phamiz.ecommerce.backend.model.*;
import phamiz.ecommerce.backend.repositories.ICategoryRepository;
import phamiz.ecommerce.backend.repositories.IProductRepository;
import phamiz.ecommerce.backend.service.IProductService;
import phamiz.ecommerce.backend.service.IUserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IUserService userService;
    private final ICategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProduct_name());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setPrice(product.getPrice());
        productDTO.setBrand(product.getBrand());
        productDTO.setCategory(product.getCategory());
        productDTO.setCreatedAt(product.getCreatedAt());

        double totalRating = 0.0;
        for (Rating rating : product.getRatings()) {
            totalRating += rating.getRating();
        }
        productDTO.setRating(totalRating);
        List<ReviewDTO> listReviewDTO = new ArrayList<>();
        for (Review review : product.getReviews()) {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setUserId(review.getUser().getId());
            reviewDTO.setComment(review.getReview());
            listReviewDTO.add(reviewDTO);
        }
        productDTO.setReviews(listReviewDTO);
        productDTO.setImages(product.getImages().stream()
                .map(ProductImage::getImageUrl)
                .collect(Collectors.toList()));
        Set<String> colorNames = product.getProductColors().stream()
                .map(ProductColor::getColor_name)
                .collect(Collectors.toSet());
        productDTO.setProductColors(colorNames);

        return productDTO;
    }

    @Override
    public List<ProductDTO> findAllProduct() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            logger.warn("No product was found!");
        }
        logger.info("Find success");
        List<ProductDTO> listProductRespone = new ArrayList<>();
        for (Product product : products) {

            listProductRespone.add(toDTO(product));
        }
        return listProductRespone;
    }

    @Override
    public Product createProduct(CreateProductRequest request) {
        Category firstLevel = categoryRepository.findByCategoryName(request.getFirstLevelCategory());
        if (firstLevel == null) {
            Category firstLevelCategory = new Category();
            firstLevelCategory.setCategory_name(request.getFirstLevelCategory());
            firstLevelCategory.setLevel(1);
            firstLevel = categoryRepository.save(firstLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(
                request.getFirstLevelCategory(), firstLevel.getCategory_name());
        if (secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setCategory_name(request.getSecondLevelCategory());
            secondLevelCategory.setParent_category(firstLevel);
            secondLevelCategory.setLevel(2);
            secondLevel = categoryRepository.save(secondLevelCategory);
        }


        Product product = new Product();
        product.setProductColors(request.getColors());
        product.setImages(request.getImages());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(secondLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        logger.info("Create success product : ", savedProduct);
        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepository.deleteById(product.getId());
        logger.info("Product deleted success!");
        return "Product deleted success!";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product = findProductById(productId);

        if (req.getQuantity() != 0) {
            product.setQuantity(req.getQuantity());
            logger.info("Product update success!");
        }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            logger.info("Product was found with id : ", id);
            return optionalProduct.get();
        }
        logger.error("Product not found with " + id);
        throw new ProductException("Product not found with " + id);
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes,
                                       Integer minPrice, Integer maxPrice,
                                       String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, sort);

        if (!colors.isEmpty()) {
            products = products.stream().filter(p -> colors.stream().anyMatch(c ->
                    c.equalsIgnoreCase(p.getProductColors().toString()))).collect(Collectors.toList());
        }

        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

        return filteredProducts;
    }
}