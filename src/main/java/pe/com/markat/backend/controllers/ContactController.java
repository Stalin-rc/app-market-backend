package pe.com.markat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.markat.backend.entities.Contact;
import pe.com.markat.backend.repositories.ClientRepository;
import pe.com.markat.backend.repositories.ContactRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/api")
public class ContactController {
    @Autowired
    private ContactRepository repo;
    @GetMapping("/contacts")
    public List<Contact> getContacts(){
        return repo.findAll();
    }
    @GetMapping("/contacts/{id}")
    public Contact getContact(@PathVariable Long id){
        return repo.findById(id).get();
    }
    @PostMapping("/contacts")
    public Contact addContact(@RequestBody Contact contact){
        return repo.save(contact);
    }
    @PutMapping("/contacts")
    public Contact editContact(@RequestBody Contact contact){
        return repo.save(contact);
    }
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable Long id){
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
