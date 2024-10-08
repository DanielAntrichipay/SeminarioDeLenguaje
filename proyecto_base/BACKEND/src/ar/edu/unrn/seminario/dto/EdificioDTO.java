package ar.edu.unrn.seminario.dto;

import java.util.List;

import ar.edu.unrn.seminario.modelo.Aula;

public class EdificioDTO {
	
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
	

	public boolean existeAulaEnLista (Aula unAula) {
		boolean existe= false;
		for (Aula a: listaAulas) {
			if (a.getNumeroAula()==unAula.getNumeroAula()) {
				existe= true;
			}
		}
		return existe;
	}
	
	// Queremos que edificioDTO tenga agregar o quitar aulas?????
	
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

	public EdificioDTO(String unNombre) {
		super();
		this.nombre=unNombre;
	}

}
