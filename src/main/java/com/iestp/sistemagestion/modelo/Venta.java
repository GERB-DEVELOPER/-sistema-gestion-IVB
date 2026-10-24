package com.iestp.sistemagestion.modelo;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "ventas")
 public class Venta {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "cliente_id", nullable = false)
	    private Cliente cliente;

	    @ManyToOne
	    @JoinColumn(name = "producto_id", nullable = false)
	    private Producto producto;

	    @Column(nullable = false)
	    private Integer cantidad;

	    @Column(nullable = false)
	    private LocalDateTime fecha;

	    @Column(nullable = false, precision = 10, scale = 2)
	    private BigDecimal total;
	    
	    public Venta() { }

	    public Venta(Cliente cliente, Producto producto, Integer cantidad) {
	        this.cliente = cliente;
	        this.producto = producto;
	        this.cantidad = cantidad;
	    }
	    
	    @PrePersist
	    public void calcularAntesDeGuardar() {
	    	if (this.fecha == null) {
	    		 this.fecha = LocalDateTime.now();	
	    	}
	    	 if (this.total == null && this.producto != null && this.cantidad != null) {
	    		 this.total = this.producto.getPrecio().multiply(BigDecimal.valueOf(this.cantidad));
	    	 }
	    }
	    
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public Cliente getCliente() { return cliente; }
	    public void setCliente(Cliente cliente) { this.cliente = cliente; }

	    public Producto getProducto() { return producto; }
	    public void setProducto(Producto producto) { this.producto = producto; }

	    public Integer getCantidad() { return cantidad; }
	    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

	    public LocalDateTime getFecha() { return fecha; }
	    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

	    public BigDecimal getTotal() { return total; }
	    public void setTotal(BigDecimal total) { this.total = total; }
}




















