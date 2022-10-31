package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.markat.backend.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}