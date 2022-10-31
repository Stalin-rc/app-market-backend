package pe.com.markat.backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sale_details")
public class SaleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double priceUnit;
    private Integer noUnits;
    private Double subtotalPrice;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
}
