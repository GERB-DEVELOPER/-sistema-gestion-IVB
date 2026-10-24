package com.iestp.sistemagestion.dao;
import com.iestp.sistemagestion.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface VentaRepository extends JpaRepository<Venta, Long> {
	List<Venta> findByCliente_IdOrderByFechaDesc(Long clienteId);
	
}
