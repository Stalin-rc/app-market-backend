package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.markat.backend.entities.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long> {
}