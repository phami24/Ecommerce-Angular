package phamiz.ecommerce.backend.service;

import phamiz.ecommerce.backend.dto.Rating.RatingRequest;
import phamiz.ecommerce.backend.exception.ProductException;
import phamiz.ecommerce.backend.model.Rating;
import phamiz.ecommerce.backend.model.User;

import java.util.List;

public interface IRatingService {
    public Rating createRating(RatingRequest request, User user) throws ProductException;
    public List<Rating> getProductsRating(Long productId);
}
