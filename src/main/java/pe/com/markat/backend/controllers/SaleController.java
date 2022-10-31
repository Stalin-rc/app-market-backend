package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Contact;
import pe.com.markat.backend.entities.Sale;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.SaleRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class SaleController {
    @Autowired
    private SaleRepository repo;
    @GetMapping("/sales")
    public List<Sale> getSales(){
        List<Sale> sales=repo.findAll();
        for (Sale sale:sales){
            sale.setSaleDetails(null);
            sale.setStore(null);
            sale.getClient().setSales(null);
        }
        return sales;
    }
    @GetMapping("/sales/{id}")
    public Sale getSale(@PathVariable Long id){
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Sale with id="+id));
    }
    @PostMapping("/sale")
    public Sale addSale(@RequestBody Sale sale){
        Sale sale1=repo.save(sale);
        sale1.setSaleDetails(null);
        sale1.setClient(null);
        sale1.setSaleDetails(null);
        sale1.setStore(null);
        return sale1;
    }
    @PutMapping("/sale")
    public Sale editSale(@RequestBody Sale sale){
        return repo.save(sale);
    }
    @DeleteMapping("/sales/{id}")
    public ResponseEntity<HttpStatus> deleteSale(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
