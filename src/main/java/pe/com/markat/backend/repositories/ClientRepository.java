package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.markat.backend.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}