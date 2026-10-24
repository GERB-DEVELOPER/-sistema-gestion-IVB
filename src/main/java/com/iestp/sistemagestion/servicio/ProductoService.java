package com.iestp.sistemagestion.servicio;

import com.iestp.sistemagestion.dao.ProductoRepository;
import com.iestp.sistemagestion.modelo.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoService {
	 private final ProductoRepository productoRepository;
	 
	 public ProductoService(ProductoRepository productoRepository) {
	        this.productoRepository = productoRepository;
	    }
	 
	 public List<Producto> listar() {
	        return productoRepository.findAllByOrderByNombreAsc();
	    }
	 
	 public Producto obtenerPorId(Long id) {
	        return productoRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + id));
	    }
	 
	 @Transactional
	    public Producto crear(Producto producto) {
	        // regla de negocio: no se acepta un precio negativo o en cero
	        if (producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
	            throw new RuntimeException("El precio debe ser mayor a cero");
	        }
	        return productoRepository.save(producto);
	    }
	 
	 @Transactional
	    public Producto actualizar(Long id, Producto datos) {
	        Producto existente = obtenerPorId(id);
	        if (datos.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
	            throw new RuntimeException("El precio debe ser mayor a cero");
	        }
	        existente.setNombre(datos.getNombre());
	        existente.setPrecio(datos.getPrecio());
	        return productoRepository.save(existente);
	    }
	 
	 @Transactional
	    public void eliminar(Long id) {
	        Producto existente = obtenerPorId(id);
	        productoRepository.delete(existente);
	    }
}
