package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Store;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.StoreRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class StoreController {
    @Autowired
    private StoreRepository repo;
    @GetMapping("/stores")
    public List<Store> getStore(){
        return repo.findAll();
    }
    @GetMapping("/stores/{id}")
    public Store getStore(@PathVariable Long id){
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Store with id="+id));
    }
    @PostMapping("/stores")
    public Store addStore(@RequestBody Store store){
        return repo.save(store);
    }
    @PutMapping("/stores")
    public Store editStore(@RequestBody Store store){
        return repo.save(store);
    }
    @DeleteMapping("/stores/{id}")
    public ResponseEntity<HttpStatus> deleteStore(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
