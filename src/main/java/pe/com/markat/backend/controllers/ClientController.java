package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Client;
import pe.com.markat.backend.exceptions.ResourceNotFoundException;
import pe.com.markat.backend.repositories.ClientRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class ClientController {
    @Autowired
    private ClientRepository repo;
    @GetMapping("/user/{idStore}/clients")
    public ResponseEntity<List<Client>> getClients(@PathVariable Long idStore){
        List<Client> clients=repo.findAllClientsByStoreIdJPA(idStore);

        if (clients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        for(Client client:clients){
            client.setSales(null);
        }

        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable Long id){
        Client client=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found Client with id="+id));
        client.setSales(null);
        return client;
    }
    @PostMapping("/clients")
    public Client addClient(@RequestBody Client client){
        Client client1=repo.save(client);
        client1.setSales(null);
        return client1;
    }
    @PutMapping("clients")
    public Client editClient(@RequestBody Client client){
        return repo.save(client);
    }
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
