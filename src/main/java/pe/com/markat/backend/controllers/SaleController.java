package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.*;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.SaleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class SaleController {
    @Autowired
    private SaleRepository repo;

   
    @GetMapping("/sales/total/{idStore}")
    public ResponseEntity <Double> GetTotalPrice(@PathVariable Long idStore){
        Double totalPrice = repo.findSumTotalPriceByStoreIdJPA(idStore);
        return new ResponseEntity<Double>(totalPrice,HttpStatus.OK);
    }

    @GetMapping("/user/{idStore}/sales")
    public ResponseEntity<List<Sale>> getSales(@PathVariable Long idStore){

        List<Sale> sales=repo.findAllSalesByStoreIdJPA(idStore);

        for (Sale sale:sales){
            sale.setSaleDetails(null);
           if(sale.getStore()!=null){
               sale.getStore().setSales(null);
               sale.getStore().setStocks(null);
           }
            sale.getClient().setSales(null);
        }
        return new ResponseEntity<List<Sale>>(sales,HttpStatus.OK);
    }
    @GetMapping("/sales/{id}")
    public ResponseEntity<Optional<Sale>>  getSale(@PathVariable Long id){
        Optional<Sale> sale= Optional.ofNullable(repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No se encontró la venta con id: " + id)));

        sale.get().getStore().setSales(null);
        sale.get().getStore().setStocks(null);
        sale.get().getClient().setSales(null);
        for (SaleDetails saleDetails:sale.get().getSaleDetails()){
            saleDetails.getProduct().setSupplier(null);
            saleDetails.getProduct().setSaleDetails(null);
            saleDetails.getProduct().setStocks(null);
            saleDetails.setSale(null);
        }

        sale.get().getClient().setSales(null);

        return new ResponseEntity<Optional<Sale>>(sale,HttpStatus.OK);
    }
    @PostMapping("/sales")
    public ResponseEntity<Sale> addSale(@RequestBody Sale sale){
        Sale sale1=repo.save(sale);
        sale1.getClient().setSales(null);
        sale1.setStore(null);
        sale1.setSaleDetails(null);
        return new ResponseEntity<Sale>(sale1,HttpStatus.CREATED);
    }

    @PutMapping("/sales")
    public ResponseEntity<Sale> editSale(@RequestBody Sale sale){
        Sale sale1=repo.save(sale);
        sale1.setClient(null);
        sale1.setStore(null);
        sale1.setSaleDetails(null);
        return new ResponseEntity<Sale>(sale1,HttpStatus.CREATED);
    }
    @DeleteMapping("/sales/{id}")
    public ResponseEntity<HttpStatus> deleteSale(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
