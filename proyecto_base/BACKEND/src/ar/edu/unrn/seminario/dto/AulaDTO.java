package ar.edu.unrn.seminario.dto;


public class AulaDTO {
	private int numeroAula; 
	private  String nombreEdificio;
	private int capacidad;
	//que pasa con recursos, que es lo que me interesa saber ? la descripcion ?
	
	
	public int getNumeroAula() {
		return numeroAula;
	}
	public void setNumeroAula(int numeroAula) {
		this.numeroAula = numeroAula;
	}
	public String getNombreEdificio() {
		return nombreEdificio;
	}
	public void setNombreEdificio(String nombreEdificio) {
		this.nombreEdificio = nombreEdificio;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	


}
