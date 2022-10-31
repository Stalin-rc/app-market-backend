package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.SaleDetails;
import pe.com.markat.backend.repositories.SaleDetailsRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class SaleDatailsController {
    @Autowired
    private SaleDetailsRepository repo;
    @GetMapping("/details")
    public List<SaleDetails> getSaleDetails(){
        return repo.findAll();
    }
    @GetMapping("/details/{id}")
    public SaleDetails getSaleDetails(@PathVariable Long id){
        return repo.findById(id).get();
    }
    @PostMapping("/details")
    public List<SaleDetails> addSaleDetails(@RequestBody List<SaleDetails> saleDetails){
        List<SaleDetails> details=repo.saveAll(saleDetails);
        for(SaleDetails details1:details){
            details1.setSale(null);
            details1.setProduct(null);
        }
        return details;
    }
    @PutMapping("/details")
    public SaleDetails editCSaleDetails(@RequestBody SaleDetails saleDetails){
        return repo.save(saleDetails);
    }
    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteSaleDetails(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
