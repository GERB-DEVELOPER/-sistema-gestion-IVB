package com.iestp.sistemagestion.servicio;

import org.springframework.stereotype.Service;

import com.iestp.sistemagestion.dao.ClienteRepository;
import com.iestp.sistemagestion.excepciones.ClienteNoEncontradoException;
import com.iestp.sistemagestion.excepciones.RucDuplicadoException;
import com.iestp.sistemagestion.modelo.Cliente;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;




@Service
public class ClienteService {
	 private final ClienteRepository clienteRepository;
	 
	 public ClienteService(ClienteRepository clienteRepository) {
	        this.clienteRepository = clienteRepository;
	}
	 
	 public List<Cliente> listar() {
	        return clienteRepository.findAllByOrderByNombreAsc();
	    }
	 
	 public Cliente obtenerPorId(Long id) {
	        return clienteRepository.findById(id)
	                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con id " + id));
	    }
	 
	 @Transactional
	    public Cliente crear(Cliente cliente) {
		 
		   if(clienteRepository.findByRuc(cliente.getRuc()).isPresent()) {
			   throw new RucDuplicadoException("Ya existe un cliente con el RUC " + cliente.getRuc());
		   }
		 
	        
	        return clienteRepository.save(cliente);
	    }
	 
	 @Transactional
	    public Cliente actualizar(Long id, Cliente datos) {
	        Cliente existente = obtenerPorId(id); // reutiliza la búsqueda de arriba
	        existente.setNombre(datos.getNombre());
	        existente.setEmail(datos.getEmail());
	        existente.setTelefono(datos.getTelefono());
	        // el RUC no se actualiza aquí a propósito: es la identidad fiscal del cliente
	        return clienteRepository.save(existente);
	    }
	 
	 @Transactional
	    public void eliminar(Long id) {
	        Cliente existente = obtenerPorId(id); // si no existe, lanza la excepción antes de intentar borrar
	        clienteRepository.delete(existente);
	    }
	 
} //fin servicio










