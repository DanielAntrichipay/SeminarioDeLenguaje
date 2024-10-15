package ar.edu.unrn.seminario.dto;

import java.util.ArrayList;
import java.util.List;

public class EdificioDTO {
	
	/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre;
	private String direccion;
	private List<AulaDTO> listaAulas;

	/*-------------------------------- MÉTODOS --------------------------------*/
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public void setListaAulas(List<AulaDTO> listaAulas) {
		this.listaAulas = listaAulas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	public List<AulaDTO> obtenerListaAulas() {
		return listaAulas;
	}
	

	public boolean existeAulaEnLista (AulaDTO unAula) {
		boolean existe= false;
		for (AulaDTO a: listaAulas) {
			if (a.getNumeroAula()==unAula.getNumeroAula()) {
				existe= true;
			}
		}
		return existe;
	}
	
	public boolean existeAulaEnLista (int unNumeroAula) {
		boolean existe= false;
		for (AulaDTO a: listaAulas) {
			if (a.getNumeroAula()==unNumeroAula) {
				existe= true;
			}
		}
		return existe;
	}
	
	
	
	public void agregarAula (AulaDTO unAula) {		
		if (! existeAulaEnLista(unAula)) {
			listaAulas.add (unAula);
		}
		
	}
	
	public void quitarAula (AulaDTO unAula) {
		
		if (existeAulaEnLista (unAula)) {
			listaAulas.remove(unAula);
		}
	}
	
	
	public AulaDTO obtenerAulaEspecifica (int unNumeroAula) {
		AulaDTO unAula= null;
		for (AulaDTO a: listaAulas) {
			if (a.getNumeroAula () == unNumeroAula) {
				unAula = a;
			}
		}
		
		return unAula;
	}
	
	

	/*----------------------------- CONSTRUCTORES -----------------------------*/

	
	public EdificioDTO () {
		
	};
	
	public EdificioDTO(String unNombre, String unaDireccion) {
		this.nombre=unNombre;
		this.direccion= unaDireccion;
		listaAulas = new ArrayList <AulaDTO> ();
		
	}

}
