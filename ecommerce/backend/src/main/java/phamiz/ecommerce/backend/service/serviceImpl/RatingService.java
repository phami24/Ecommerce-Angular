package phamiz.ecommerce.backend.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import phamiz.ecommerce.backend.dto.Rating.RatingRequest;
import phamiz.ecommerce.backend.exception.ProductException;
import phamiz.ecommerce.backend.model.Product;
import phamiz.ecommerce.backend.model.Rating;
import phamiz.ecommerce.backend.model.User;
import phamiz.ecommerce.backend.repositories.IRatingRepository;
import phamiz.ecommerce.backend.service.IProductService;
import phamiz.ecommerce.backend.service.IRatingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService {

    private final IRatingRepository ratingRepository;
    private final IProductService productService;


    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setRating(request.getRating());
        rating.setUser(user);
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}