package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Contact;
import pe.com.markat.backend.entities.Product;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.ContactRepository;
import pe.com.markat.backend.repositories.ProductRepository;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class ProductController {
    @Autowired
    private ProductRepository repo;
    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> products=repo.findAll();
        for (Product product:products){
            product.setSaleDetails(null);
            product.setSupplier(null);
            product.setStocks(null);
        }
        return repo.findAll();
    }
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id){
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Product with id="+id));
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return repo.save(product);
    }
    @PutMapping("/products")
    public Product editProduct(@RequestBody Product product){
        return repo.save(product);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
