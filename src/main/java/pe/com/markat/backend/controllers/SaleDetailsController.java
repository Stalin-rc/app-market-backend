package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.*;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.SaleDetailsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class SaleDetailsController {
    @Autowired
    private SaleDetailsRepository repo;
    @GetMapping("/details")
    public ResponseEntity<List<SaleDetails>> getSaleDetails(){

        List<SaleDetails> saleDetails=repo.findAll();

        if (saleDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (SaleDetails saleDetail:saleDetails){

            saleDetail.setSale(null);
            saleDetail.setProduct(null);
        }
        return new ResponseEntity<List<SaleDetails>>(saleDetails,HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Optional<SaleDetails>> getSaleDetails(@PathVariable Long id){

        Optional <SaleDetails> saleDetail= Optional.ofNullable(repo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Not found SaleDetails with id: " + id)));;

        saleDetail.get().setSale(null);
        saleDetail.get().setProduct(null);

        return new ResponseEntity<Optional<SaleDetails>>(saleDetail,HttpStatus.OK);
    }

    @PostMapping("/details")
    public ResponseEntity<List<SaleDetails>> addSaleDetails(@RequestBody List<SaleDetails> saleDetails){
        List<SaleDetails> saleDetail1=repo.saveAll(saleDetails);
        for (SaleDetails sale:saleDetail1){
            sale.setSale(null);
            sale.setProduct(null);
        }
        return new ResponseEntity<>(saleDetail1,HttpStatus.OK);
    }

    @PutMapping("/details")
    public ResponseEntity<SaleDetails> editStock(@RequestBody SaleDetails saleDetails){

        SaleDetails saleDetail1=repo.save(saleDetails);
        saleDetails.setSale(null);
        saleDetails.setProduct(null);

        return new ResponseEntity<SaleDetails>(saleDetail1,HttpStatus.OK);
    }


    @DeleteMapping("/details/{id}")
    public ResponseEntity<HttpStatus> deleteSaleDetails(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
