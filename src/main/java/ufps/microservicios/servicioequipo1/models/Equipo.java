package ufps.microservicios.servicioequipo1.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="equipos")

public class Equipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String ciudad;
	
	private int club;
	
	private String telefono;
	
	
	@ManyToOne
	private Categoria categoria;
	
	private String escudo;
	
	private String direccion;
	
	private String nombre;

	public Equipo() {}
	
	public int getId() {
		return id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public int getClub() {
		return club;
	}

	public String getTelefono() {
		return telefono;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getEscudo() {
		return escudo;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setClub(int club) {
		this.club = club;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setEscudo(String escudo) {
		this.escudo = escudo;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
