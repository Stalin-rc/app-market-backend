package pe.com.markat.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dni;
    private String firstName;
    private String lastName;
    private String clientAddress;
    private Integer noPhone;
    private String photo;

    @OneToMany(mappedBy = "client")
    List<Sale> sales;
}
