package com.iestp.sistemagestion;

import com.iestp.sistemagestion.modelo.Cliente;
import com.iestp.sistemagestion.modelo.Producto;
import com.iestp.sistemagestion.modelo.Venta;

import com.iestp.sistemagestion.servicio.ClienteService;
import com.iestp.sistemagestion.servicio.ProductoService;
import com.iestp.sistemagestion.servicio.VentaService;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class DatosDePruebaRunner implements CommandLineRunner{
	
	 private final ClienteService clienteService;
	 private final ProductoService productoService;
	 private final VentaService ventaService;

	    public DatosDePruebaRunner(
	    		ClienteService clienteService,
                ProductoService productoService,
                VentaService ventaService) {
	        
	    	 this.clienteService = clienteService;
	         this.productoService = productoService;
	         this.ventaService = ventaService;
	    }
	    

	    @Override
	    public void run(String... args) {
	    	
	    	Cliente cliente = clienteService.crear(
	                new Cliente("Comercial JOssi SAC", "33388521478", "guvara@gmail.com", "999111222"));
	        System.out.println("Cliente creado con id: " + cliente.getId());
	    	
	        Producto producto = productoService.crear(new Producto("rap", new BigDecimal("200.00")));
	        System.out.println("Producto creado con id: " + producto.getId());
	        
	        
	        Venta venta = ventaService.registrarVenta(cliente.getId(), producto.getId(), 2);
	        System.out.println("Venta registrada con id: " + venta.getId() + " - total: " + venta.getTotal());
	        
	    }
}
