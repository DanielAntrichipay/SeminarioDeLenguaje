package ar.edu.unrn.seminario.modelo;

public class Materia {
	/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre;
	private String codigoCarrera;
	private Carrera unaCarrera;
	
	/*-------------------------------- MÉTODOS --------------------------------*/
	public String getNombre() {
		return this.nombre;
	}
	
	public Carrera getCarrera (){
		return this.unaCarrera;
	}
	
	public String getCodigoCarrera () {
		return this.codigoCarrera;
	}
	
	public String setNombre () {
		return this.nombre;
	}

	public boolean equals(String codigoCarrera) {
		return this.nombre.contentEquals(codigoCarrera);
	}
	/*----------------------------- CONSTRUCTORES -----------------------------*/

	public Materia(String nombre, String codigoCarrera, Carrera unaCarrera) {
		this.nombre = nombre;
		this.codigoCarrera = codigoCarrera;
		this.unaCarrera = unaCarrera;
	}
	
	
}
