package ar.edu.unrn.seminario.modelo;

import java.util.List;

public class Edificio {
	
	/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre; 
	private List<Aula> listaAulas;
	
	
	
	/*-------------------------------- MÉTODOS --------------------------------*/
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	public List<Aula> getListaAulas() {
		return listaAulas;
	}
	
	public void setListaAulas(List<Aula> listaAulas) {
		this.listaAulas = listaAulas;
	}
	
	
	
	/*----------------------------- CONSTRUCTORES -----------------------------*/
	
	public Edificio (String unNombre) {
		this.nombre= unNombre;
	
	
}
}
