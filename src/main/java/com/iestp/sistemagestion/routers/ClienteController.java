package com.iestp.sistemagestion.routers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;
import com.iestp.sistemagestion.modelo.Cliente;
import com.iestp.sistemagestion.schemas.ClienteDTO;
import com.iestp.sistemagestion.servicio.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
 //debo crear una variable local que haga referencia al ClienteService
	 private final ClienteService clienteService;

	 //es metodo
	 public ClienteController(ClienteService clienteService) {
		 this.clienteService = clienteService;
	 }
	 
	 //listar todos los clientes
	 @GetMapping
	 	public List<ClienteDTO> listar(){
		 return clienteService.listar()
				 .stream()
				 .map(ClienteDTO::fromEntity)
				 .collect(Collectors.toList());
	 }
	 
	 //listar por ID
	 @GetMapping("/{id}")
	 public ClienteDTO obtenerPorId(@PathVariable Long id) {
		 return  ClienteDTO.fromEntity(clienteService.obtenerPorId(id));
	 }

	 //crear clientes
	 @PostMapping
	 public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){	 
		 Cliente creado = clienteService.crear(cliente);
		 return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	 }
	 
	 //actualizar clientes
	 @PutMapping("/{id}")
	 public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente datos) {
		 return clienteService.actualizar(id, datos);
	 }
	 
	 //eliminar datos
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
	        clienteService.eliminar(id);
	        return ResponseEntity.noContent().build();
	    }



} //fin de clase
