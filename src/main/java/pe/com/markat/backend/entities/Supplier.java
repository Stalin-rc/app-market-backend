package pe.com.markat.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String supplierName;
    private String noPhone;
    private String address;
    @OneToMany(mappedBy = "supplier")
    List<Product> products;
}
