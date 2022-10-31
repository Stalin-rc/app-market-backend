package pe.com.markat.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table (name= "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storeName;
    private String noRuc;
    private String noPhone;
    private String ownerName;
    private String ownerLastName;
    private String ownerPhoto;
    private String ownerDni;
    private String email;
    private String password;
    private String city;
    @OneToMany(mappedBy = "store")
    private List<Stock> stocks;
    @OneToMany(mappedBy = "store")
    private List<Sale> sales;

}
