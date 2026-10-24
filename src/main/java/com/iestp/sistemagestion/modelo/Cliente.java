package com.iestp.sistemagestion.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 150)
	private String nombre;
	
	@Column(nullable = false, unique = true, length = 20)
    private String ruc;
	
	@Column(nullable = false, length = 150)
	private String email;
	
	@Column(nullable = false, length = 20)
    private String telefono;
	
    public Cliente() { }  // constructor vacío: Hibernate lo necesita para crear objetos al leer filas

    public Cliente(String nombre, String ruc, String email, String telefono) {
        // constructor "de negocio": no recibe id, porque lo asigna la base de datos al guardar
        this.nombre = nombre;
        this.ruc = ruc;
        this.email = email;
        this.telefono = telefono;
    }
	
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
	
	
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
	
	
	
	
	
	
	
	
	
}
