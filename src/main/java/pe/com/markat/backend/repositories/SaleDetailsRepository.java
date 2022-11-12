package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.markat.backend.entities.Sale;
import pe.com.markat.backend.entities.SaleDetails;

import java.util.List;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long> {
    @Query("select s from SaleDetails s where s.sale.id=?1")
    List<SaleDetails> findAllSalesDetailsBySaleIdJPA(Long id);


}