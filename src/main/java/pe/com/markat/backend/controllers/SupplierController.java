package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.SaleDetails;
import pe.com.markat.backend.entities.Supplier;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.SupplierRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class SupplierController {
    @Autowired
    private SupplierRepository repo;
    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> getSuppliers(){

        List<Supplier> suppliers=repo.findAll();
        if (suppliers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Supplier proveedor:suppliers){
            proveedor.setProducts(null);
        }
        return new ResponseEntity<List<Supplier>>(suppliers,HttpStatus.OK);
    }


    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Optional<Supplier>> getSupplier(@PathVariable Long id){

        Optional <Supplier> supplier= Optional.ofNullable(repo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Not found Supplier with id: " + id)));;

        supplier.get().setProducts(null);

        return new ResponseEntity<Optional<Supplier>>(supplier,HttpStatus.OK);
    }

    @PostMapping("/suppliers")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier){
        Supplier supplier1=repo.save(supplier);
        supplier1.setProducts(null);
        return new ResponseEntity<Supplier>(supplier1,HttpStatus.CREATED);
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
