package ar.edu.unrn.seminario.modelo;

import java.util.ArrayList;
import java.util.List;

public class Edificio {
	
	/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre;
	private String direccion;
	
	
	private List<Aula> listaAulas;	
	
	
	/*-------------------------------- MÉTODOS --------------------------------*/
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	public List<Aula> obtenerListaAulas() {
		return listaAulas;
	}
	
	
	public boolean existeAulaEnLista (Aula unAula) {
		boolean existe= false;
		for (Aula a: listaAulas) {
			if (a.getNumeroAula()==unAula.getNumeroAula()) {
				existe= true;
			}
		}
		return existe;
	}
	
	public boolean existeAulaEnLista (int unNumeroAula) {
		boolean existe= false;
		for (Aula a: listaAulas) {
			if (a.getNumeroAula()==unNumeroAula) {
				existe= true;
			}
		}
		return existe;
	}
	
	public void agregarAula (Aula unAula) {		
		if (! existeAulaEnLista(unAula)) {
			listaAulas.add (unAula);
		}
		
	}
	
	public void quitarAula (Aula unAula) {
		
		if (existeAulaEnLista (unAula)) {
			listaAulas.remove(unAula);
		}
	}
	
	
	public Aula obtenerAulaEspecifica (int unNumeroAula) {
		Aula unAula= null;
		for (Aula a: listaAulas) {
			if (a.getNumeroAula () == unNumeroAula) {
				unAula = a;
			}
		}
		
		return unAula;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Aula> getListaAulas() {
		return listaAulas;
	}

	public void setListaAulas(List<Aula> listaAulas) {
		this.listaAulas = listaAulas;
	}
	
	
	/*----------------------------- CONSTRUCTORES -----------------------------*/
	
	


	public Edificio () {
		
	}
	
	public Edificio (String unNombre, String unaDireccion) {
		super();
		this.nombre= unNombre;
		this.direccion= unaDireccion;
		listaAulas = new ArrayList <Aula>();
		
	
	
}
}
