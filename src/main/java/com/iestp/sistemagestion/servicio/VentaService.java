package com.iestp.sistemagestion.servicio;

import com.iestp.sistemagestion.dao.ClienteRepository;
import com.iestp.sistemagestion.dao.ProductoRepository;
import com.iestp.sistemagestion.dao.VentaRepository;
import com.iestp.sistemagestion.modelo.Cliente;
import com.iestp.sistemagestion.modelo.Producto;
import com.iestp.sistemagestion.modelo.Venta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaService {
	 private final VentaRepository ventaRepository;
	 private final ClienteRepository clienteRepository;
	 private final ProductoRepository productoRepository;
	 
	 public VentaService(VentaRepository ventaRepository,
             ClienteRepository clienteRepository,
             ProductoRepository productoRepository) 
	 {
		this.ventaRepository = ventaRepository;
		this.clienteRepository = clienteRepository;
		this.productoRepository = productoRepository;
	}
	 
	 public List<Venta> listarPorCliente(Long clienteId) {
	        return ventaRepository.findByCliente_IdOrderByFechaDesc(clienteId);
	    }
	 
	 public List<Venta> listarTodas(){
		 return ventaRepository.findAll();
	 }
	 
	 @Transactional
	    public Venta registrarVenta(Long clienteId, Long productoId, Integer cantidad) {
	        if (cantidad == null || cantidad <= 0) {
	            throw new RuntimeException("La cantidad debe ser mayor a cero");
	        }

	        Cliente cliente = clienteRepository.findById(clienteId)
	                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + clienteId));

	        Producto producto = productoRepository.findById(productoId)
	                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + productoId));

	        Venta venta = new Venta(cliente, producto, cantidad);
	        // fecha y total NO se calculan aqui: el @PrePersist de Venta (Semana 4)
	        // los calcula automaticamente cuando se guarda
	        return ventaRepository.save(venta);
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
