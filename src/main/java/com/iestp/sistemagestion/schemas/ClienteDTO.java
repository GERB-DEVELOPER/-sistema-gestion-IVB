package com.iestp.sistemagestion.schemas;
import com.iestp.sistemagestion.modelo.Cliente;

public class ClienteDTO {

	    private Long id;
	    private String nombre;
	    private String ruc;
	    private String email;
	    
	    public static ClienteDTO fromEntity(Cliente cliente) {
	        ClienteDTO dto = new ClienteDTO();
	        dto.id = cliente.getId();
	        dto.nombre = cliente.getNombre();
	        dto.ruc = cliente.getRuc();
	        dto.email = cliente.getEmail();
	        return dto;
	    }
	    public Long getId() { return id; }
	    public String getNombre() { return nombre; }
	    public String getRuc() { return ruc; }
	    public String getEmail() { return email; }
}
