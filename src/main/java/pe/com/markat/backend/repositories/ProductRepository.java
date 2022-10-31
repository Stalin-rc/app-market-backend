package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.markat.backend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}