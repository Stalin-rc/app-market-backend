package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.markat.backend.entities.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("select s from Stock s where s.store.id=?1 order by s.noUnits desc")
    List<Stock> findAllStocksByStoreIdJPA(Long id);

    @Query("SELECT s FROM Stock s WHERE s.store.id =?1 AND s.noUnits < 20 order by s.noUnits asc")
    List<Stock> findStocksByStoreIdJPA(Long id);

    @Query("SELECT SUM(c.noUnits) FROM Stock c WHERE c.store.id=?1")
    Double findSumStockByStoreIdJPA(Long id);

}