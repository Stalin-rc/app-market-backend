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
    private String firstName;
    private String lastName;
    private Integer dni;
    private String jobName;
    private String clientAddress;
    private Integer noPhone;
    private Boolean morosidad;
    private String photo;
    private Double credit;
    private Date payDate;


    @OneToMany(mappedBy = "client")
    List<Sale> sales;
}
