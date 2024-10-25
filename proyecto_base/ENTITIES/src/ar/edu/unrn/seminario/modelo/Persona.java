package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.modelo.Usuario;

public class Persona {
	/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre;
	private String apellido;	private String legajo;	private Usuario usuario;
	
	/*-------------------------------- MÉTODOS --------------------------------*/
	
	public String getNombre () {
		return this.nombre;
	}
	
	public String getApellido () {
		return this.apellido;
	}
	
	public String getLegajo () {
		return this.legajo;
	}
	
	public Usuario getUsuario () {
		return this.usuario;
	}
	
	public void setNombre (String unNombre) {
		this.nombre = unNombre;
	}
	
	public void setApellido (String unApellido) {
		this.apellido = unApellido;
	}

	/*----------------------------- CONSTRUCTORES -----------------------------*/
	
	public Persona (String unNombre, String unApellido, String unLegajo, Usuario unUsuario) {
		this.nombre = unNombre;
		this.apellido = unApellido;
		this.legajo = unLegajo;
		this.usuario = unUsuario;
	}
	
}
/*------------------------------- ATRIBUTOS -------------------------------*/
/*-------------------------------- MÉTODOS --------------------------------*/
/*----------------------------- CONSTRUCTORES -----------------------------*/