package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.markat.backend.entities.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("select s from Stock s where s.store.id=?1 order by s.noUnits desc")
    List<Stock> findAllByStoreIdJPA(Long id);
}