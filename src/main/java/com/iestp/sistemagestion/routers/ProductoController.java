package com.iestp.sistemagestion.routers;
import com.iestp.sistemagestion.modelo.Producto;
import com.iestp.sistemagestion.servicio.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoService productoService;
	
	 public ProductoController(ProductoService productoService) {
	        this.productoService = productoService;
	    }
	 
	 @GetMapping
	    public List<Producto> listar() {
	        return productoService.listar();
	    }
	 
	 @GetMapping("/{id}")
	    public Producto obtenerPorId(@PathVariable Long id) {
	        return productoService.obtenerPorId(id);
	    }

	    @PostMapping
	    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
	        Producto creado = productoService.crear(producto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	    }

	    @PutMapping("/{id}")
	    public Producto actualizar(@PathVariable Long id, @RequestBody Producto datos) {
	        return productoService.actualizar(id, datos);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
	        productoService.eliminar(id);
	        return ResponseEntity.noContent().build();
	    }

	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<String> manejarError(RuntimeException ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
}
