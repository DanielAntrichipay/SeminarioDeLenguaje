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
	
	public List<Aula> obtenerListaAulas() {
		return listaAulas;
	}
	
	// está bien el uso de === o tengo que usar equals?

	public boolean existeAulaEnLista (Aula unAula) {
		boolean existe= false;
		for (Aula a: listaAulas) {
			if (a.getNumeroAula()==unAula.getNumeroAula()) {
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
	
	
	/*----------------------------- CONSTRUCTORES -----------------------------*/
	
	
	public Edificio () {
		
	}
	
	public Edificio (String unNombre) {
		super();
		this.nombre= unNombre;
	
	
}
}
