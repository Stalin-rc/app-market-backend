package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.markat.backend.entities.Product;
import pe.com.markat.backend.entities.Stock;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select s.product from Stock s where s.store.id=?1")
    List<Product> findAllProductsByStoreIdJPA(Long idStore);
}