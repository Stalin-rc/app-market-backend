package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Client;
import pe.com.markat.backend.entities.Contact;
import pe.com.markat.backend.entities.Product;
import pe.com.markat.backend.entities.Stock;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.ContactRepository;
import pe.com.markat.backend.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class ProductController {
    @Autowired
    private ProductRepository repo;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){

        List<Product> products=repo.findAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Product product:products){
            product.setSaleDetails(null);
            product.getSupplier().setProducts(null);
            product.setStocks(null);
        }
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @GetMapping("/user/{idStore}/products")

    public ResponseEntity<List<Product>> getProducts(@PathVariable Long idStore){

        List<Product> products=repo.findAllProductsByStoreIdJPA(idStore);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Product product:products){
            product.setSaleDetails(null);
            product.getSupplier().setProducts(null);
            product.setStocks(null);
        }
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);

    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id){
        Optional<Product> product= Optional.ofNullable(repo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Not found Product with id: " + id)));;

        product.get().setSaleDetails(null);
        product.get().getSupplier().setProducts(null);
        product.get().setStocks(null);

        return new ResponseEntity<Optional<Product>>(product, HttpStatus.OK);

    }


    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product product1=repo.save(product);
        product1.setStocks(null);
        product1.setSupplier(null);
        product1.setSaleDetails(null);
        return new ResponseEntity<Product>(product1,HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<Product> editProduct(@RequestBody Product product){
        Product product1=repo.save(product);
        product1.setStocks(null);
        product1.setSupplier(null);
        product1.setSaleDetails(null);
        return new ResponseEntity<Product>(product1,HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
