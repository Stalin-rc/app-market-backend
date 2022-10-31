package pe.com.markat.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String brand;
    private String productDescription;
    private String type_name;
    private String img;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;
    @OneToMany(mappedBy = "product")
    private List<SaleDetails> saleDetails;
}
