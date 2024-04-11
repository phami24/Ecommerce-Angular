package phamiz.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phamiz.ecommerce.backend.model.Address;
@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
}
