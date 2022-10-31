package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.markat.backend.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}