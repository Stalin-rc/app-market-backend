package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Supplier;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.SupplierRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class SupplierController {
    @Autowired
    private SupplierRepository repo;
    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers(){
        return repo.findAll();
    }
    @GetMapping("/suppliers/{id}")
    public Supplier getSupplier(@PathVariable Long id){
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Supplier with id="+id));
    }
    @PostMapping("/suppliers")
    public Supplier addSupplier(@RequestBody Supplier supplier){
        return repo.save(supplier);
    }
    @PutMapping("/suppliers")
    public Supplier editSupplier(@RequestBody Supplier supplier){
        return repo.save(supplier);
    }
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<HttpStatus> deleteSupplier(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
