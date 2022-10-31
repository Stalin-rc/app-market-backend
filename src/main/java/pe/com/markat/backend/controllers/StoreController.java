package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Product;
import pe.com.markat.backend.entities.Store;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.StoreRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class StoreController {
    @Autowired
    private StoreRepository repo;
    @GetMapping("/stores")
    public ResponseEntity<List<Store>> getStores(){
        List<Store> stores=repo.findAll();
        if (stores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Store store:stores){
            store.setStocks(null);
            store.setSales(null);
        }
        return new ResponseEntity<List<Store>>(stores,HttpStatus.OK);
    }
    @GetMapping("/stores/{id}")
    public ResponseEntity<Optional<Store>> getStore(@PathVariable Long id){
        Optional<Store> store= Optional.ofNullable(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la la tienda con id: " + id)));;
        store.get().setSales(null);
        store.get().setStocks(null);
        return new ResponseEntity<Optional<Store>>(store,HttpStatus.OK);
    }
    @PostMapping("/stores")
    public ResponseEntity<Store> addStore(@RequestBody Store store){
        Store store1=repo.save(store);
        store1.setSales(null);
        store.setStocks(null);
        return new ResponseEntity<Store>(store1,HttpStatus.CREATED);
    }
    @PutMapping("/stores")
    public ResponseEntity<Store> editStore(@RequestBody Store store){
        Store store1=repo.save(store);
        store1.setStocks(null);
        store1.setSales(null);
        return new ResponseEntity<Store>(store1,HttpStatus.OK);
    }
    @DeleteMapping("/stores/{id}")
    public ResponseEntity<HttpStatus> deleteStore(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
