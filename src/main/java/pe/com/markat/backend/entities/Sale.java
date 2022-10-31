package pe.com.markat.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    private Date dateSale;
    private Double totalPrice;
    private String sellType;
    private String noVoucher;
    @OneToMany(mappedBy = "sale")
    private List<SaleDetails> saleDetails;
}
