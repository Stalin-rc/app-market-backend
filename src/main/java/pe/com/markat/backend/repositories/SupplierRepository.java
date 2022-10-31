package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.markat.backend.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}