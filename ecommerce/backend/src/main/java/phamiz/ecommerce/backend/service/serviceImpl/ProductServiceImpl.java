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

    @Override
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
    public Page<ProductDTO> getAllProduct(String category, List<String> colors,
                                       Integer minPrice, Integer maxPrice,
                                       String sort, Integer pageNumber, Integer pageSize) throws ProductException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, sort);
        if (products.isEmpty()) {
            logger.error("Can not found any product");
            throw new ProductException("Can not found any product");
        }
        List<Product> listFilterProduct = new ArrayList<>();
        if (!colors.isEmpty()) {
            for (Product product : products) {
                for (ProductColor color : product.getProductColors()) {
                    if (colors.contains(color.getColor_name().toString())) {
                        listFilterProduct.add(product);
                        break;
                    }
                }
            }
        } else {
            listFilterProduct.addAll(products);
        }
        if (pageNumber < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid pageNumber or pageSize");
        }
        List<ProductDTO> listProductRespone = new ArrayList<>();
        for (Product product : listFilterProduct) {
            System.out.println(product.getProduct_name());
            listProductRespone.add(toDTO(product));
        }
        int startIndex = pageable.getPageNumber() * pageable.getPageSize();
        int endIndex = startIndex + pageable.getPageSize();
        endIndex = Math.min(endIndex, listProductRespone.size());
        List<ProductDTO> pageContent = listProductRespone.subList(startIndex, endIndex);
        Page<ProductDTO> pageResult  = new PageImpl<>(pageContent, pageable, listProductRespone.size());
        return pageResult ;
    }

    @Override
    public List<ProductDTO> getNewProduct() {
        List<Product> products = productRepository.findTop6ByOrderByCreatedAtDesc();
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
    public List<ProductDTO> getRandomProduct() {
        List<Product> products = productRepository.findRandom6Products();
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
}