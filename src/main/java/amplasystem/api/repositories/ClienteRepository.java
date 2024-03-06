package amplasystem.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import amplasystem.api.models.Cliente;

public interface ClienteRepository extends JpaRepository<Integer, Cliente> {

}
