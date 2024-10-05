package ar.edu.unrn.seminario.modelo;
import java.util.List;


public class Aula {

/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private int numeroAula; 
	private List<Recurso> listaRecursos;
	private Edificio edificio;
	private int capacidad;
	
	
	
	
	/*-------------------------------- MÉTODOS --------------------------------*/
	
	public int getNumeroAula() {
		return numeroAula;
	}
	
	public void setNumeroAula(int numeroAula) {
		this.numeroAula = numeroAula;
	} 
	
	public List<Recurso> getListaRecursos() {
		return listaRecursos;
	}
	
	public void setListaRecurso(List<Recurso> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}
	
	public void agregarRecurso(String descripcion) {
		//recorro la lista de recursos para saber si el que quiero agregar ya se encuentra o no 
		for (Recurso recurso : listaRecursos) {
			if (recurso.obtenerDescripcion().equals(descripcion)) { //aca etsa bien el equal?
				System.out.println("El recurso ingresado ya se encuentra en el aula");
			}
			
		}
		listaRecursos.add(new Recurso(descripcion));
		System.out.println("El recurso fue agregado con exito");
	}
	
	public void quitarRecurso(String descripcion) {
		//Recurso recursoaeliminar; hace falta esto??
		for (Recurso recurso : listaRecursos) {
			if (recurso.obtenerDescripcion().equals(descripcion)) {
				//recursoaeliminar = descripcion;
				listaRecursos.remove(descripcion);// esta parte, deveria ir afuera del for??
	            System.out.println("Recurso eiminado");
	        } 
			else {
				System.out.println("El recurso no se encuentra en el aula.");
			}
			}
	}
	
	
	/*----------------------------- CONSTRUCTORES -----------------------------*/
	
	public Aula (int numeroAula,Edificio edificio,int capacidad) {
		this.numeroAula= numeroAula;
		this.edificio= edificio;
		this.capacidad=capacidad;
	
	
}
}
