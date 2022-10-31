package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.markat.backend.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}