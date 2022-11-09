package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.markat.backend.entities.Sale;
import pe.com.markat.backend.entities.Stock;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("select s from Sale s where s.store.id=?1 order by s.dateSale desc")
    List<Sale> findAllSalesByStoreIdJPA(Long id);

}