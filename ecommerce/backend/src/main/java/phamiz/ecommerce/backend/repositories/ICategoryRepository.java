package phamiz.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import phamiz.ecommerce.backend.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category c " +
            "WHERE c.category_name = :name ")
    public Category findByCategoryName(String name);
    @Query("SELECT c FROM Category c " +
            "WHERE c.category_name = :name " +
            "AND c.parent_category.category_name = :parentCategoryName")
    public Category findByNameAndParent(@Param("name") String name,
                                        @Param("parentCategoryName") String parentCategoryName);
}
