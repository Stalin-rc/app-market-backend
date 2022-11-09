package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Stock;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.StockRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class StockController {
    @Autowired
    private StockRepository repo;
    @GetMapping("/user/{idStore}/stocks")

    public ResponseEntity<List<Stock>> getStocks(@PathVariable Long idStore){

        List<Stock> stocks=repo.findAllStocksByStoreIdJPA(idStore);

        if (stocks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Stock stock:stocks){
            stock.setStore(null);
            stock.getProduct().setStocks(null);
            stock.getProduct().setSaleDetails(null);
            stock.getProduct().getSupplier().setProducts(null);
        }
        return new ResponseEntity<List<Stock>>(stocks,HttpStatus.OK);
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Optional<Stock>> getStock(@PathVariable Long id){

        Optional <Stock> stock= Optional.ofNullable(repo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Not found Stock with id: " + id)));;
        stock.get().getProduct().getSupplier().setProducts(null);
        stock.get().getProduct().setStocks(null);
        stock.get().getProduct().setSaleDetails(null);
        stock.get().setStore(null);
        return new ResponseEntity<Optional<Stock>>(stock,HttpStatus.OK);
    }
    @PostMapping("/stocks")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock){
        Stock stock1=repo.save(stock);
            stock1.setProduct(null);
            stock1.setStore(null);
        return new ResponseEntity<Stock>(stock1,HttpStatus.CREATED);
    }
    @PutMapping("/stocks")
    public ResponseEntity<Stock> editStock(@RequestBody Stock stock){
        Stock stock1=repo.save(stock);
        stock1.setStore(null);
        stock1.setProduct(null);
        return new ResponseEntity<Stock>(stock1,HttpStatus.OK);
    }
    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<HttpStatus> deleteStock(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
