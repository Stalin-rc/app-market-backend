package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Stock;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.StockRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class StockController {
    @Autowired
    private StockRepository repo;
    @GetMapping("/stocks")
    public List<Stock> getStocks(){
        return repo.findAll();
    }
    @GetMapping("/stocks/{id}")
    public Stock getStock(@PathVariable Long id){
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Stock with id="+id));
    }
    @PostMapping("/stocks")
    public Stock addStock(@RequestBody Stock stock){
        return repo.save(stock);
    }
    @PutMapping("/stocks")
    public Stock editStock(@RequestBody Stock stock){
        return repo.save(stock);
    }
    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<HttpStatus> deleteStock(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
