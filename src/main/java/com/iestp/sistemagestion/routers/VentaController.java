package com.iestp.sistemagestion.routers;

import com.iestp.sistemagestion.modelo.Venta;
import com.iestp.sistemagestion.schemas.VentaDTO;
import com.iestp.sistemagestion.servicio.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/ventas")
public class VentaController {
	
	 private final VentaService ventaService;

	    public VentaController(VentaService ventaService) {
	        this.ventaService = ventaService;
	    }

	    @PostMapping
	    public ResponseEntity<VentaDTO> registrar(@RequestParam Long clienteId,
	                                            @RequestParam Long productoId,
	                                            @RequestParam Integer cantidad) {
	        Venta creada = ventaService.registrarVenta(clienteId, productoId, cantidad);
	        return ResponseEntity.status(HttpStatus.CREATED).body(VentaDTO.fromEntity(creada));
	    }

	    @GetMapping("/cliente/{clienteId}")
	    public List<VentaDTO> listarPorCliente(@PathVariable Long clienteId) {
	        return ventaService.listarPorCliente(clienteId)
	        		.stream()
	        		.map(VentaDTO::fromEntity)
	        		.collect(Collectors.toList());
	    }
	    
	    @GetMapping
	    public List<VentaDTO> listarTodas() {
	    	return ventaService.listarTodas()
	    		.stream()
	    		.map(VentaDTO::fromEntity)
	            .collect(Collectors.toList());
	    		
	    }
	    
	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<String> manejarError(RuntimeException ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	    
	    
	    
	    
	    
}
