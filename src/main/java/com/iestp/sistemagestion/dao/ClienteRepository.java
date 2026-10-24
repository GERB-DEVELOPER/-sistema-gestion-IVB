package com.iestp.sistemagestion.dao;
import com.iestp.sistemagestion.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository    extends JpaRepository<Cliente, Long>       {
	 Optional<Cliente> findByRuc(String ruc);
	 List<Cliente> findAllByOrderByNombreAsc();
}
