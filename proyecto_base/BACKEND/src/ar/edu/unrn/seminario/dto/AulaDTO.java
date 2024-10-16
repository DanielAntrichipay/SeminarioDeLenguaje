package ar.edu.unrn.seminario.dto;

import java.util.List;



public class AulaDTO {
	
	private int numeroAula; 
	//private  String nombreEdificio;
	private EdificioDTO edificio;
	private int capacidad;
	private List<RecursoDTO> listaRecurso;
	
	
	public List<RecursoDTO> getListaRecurso() {
		return listaRecurso;
	}
	public void setListaRecurso(List<RecursoDTO> listaRecurso) {
		this.listaRecurso = listaRecurso;
	}
	public int getNumeroAula() {
		return numeroAula;
	}
	public void setNumeroAula(int numeroAula) {
		this.numeroAula = numeroAula;
	}

	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public EdificioDTO getEdificio() {
		return edificio;
	}
	public void setEdificio(EdificioDTO edificio) {
		this.edificio = edificio;
	}
	
	public boolean existeRecurso(String nombre) { // aca va un string de nombre dell recurso o un recurso de tipo recurso?
		boolean existe= false;
		for (RecursoDTO r: listaRecurso) {
			if (r.obtenerNombre()==nombre) {
				existe= true;
			}
		}
		return existe;
	}
		
	
	public void agregarRecurso (RecursoDTO recurso) {		
		if (! existeRecurso(recurso.obtenerNombre())) {
			listaRecurso.add (recurso);
		}
		
	}
	
	public void eliminarRecurso (RecursoDTO recurso) {
		
		if (existeRecurso (recurso.obtenerNombre())) {
			listaRecurso.remove(recurso);
		}
	}
	
	
	public RecursoDTO obtenerRecurso (String nombreRecurso) {
		RecursoDTO unRecurso= null;
		for (RecursoDTO r: listaRecurso) {
			if (r.obtenerNombre () == nombreRecurso) {
				unRecurso = r;
			}
		}
		
		return unRecurso;
	}

	public AulaDTO(int numeroAula, EdificioDTO edificio, int capacidad, List<RecursoDTO> listaRecurso) {
		//super();
		this.numeroAula = numeroAula;
		this.edificio = edificio;
		this.capacidad = capacidad;
		this.listaRecurso = listaRecurso;
		
	}
	
	public AulaDTO(int numeroAula, EdificioDTO edificio, int capacidad) {
		//super();
		this.numeroAula = numeroAula;
		this.edificio = edificio;
		this.capacidad = capacidad;
	}


}
