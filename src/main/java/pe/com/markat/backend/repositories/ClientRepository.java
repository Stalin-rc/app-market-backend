package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.markat.backend.entities.Client;
import pe.com.markat.backend.entities.Product;
import pe.com.markat.backend.entities.Stock;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select s.client from Sale s where s.store.id=?1")
    List<Client> findAllClientsByStoreIdJPA(Long idStore);

}