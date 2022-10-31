package pe.com.markat.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.markat.backend.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}