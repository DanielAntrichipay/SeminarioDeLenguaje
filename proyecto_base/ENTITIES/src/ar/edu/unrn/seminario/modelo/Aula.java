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
	
	public void setListaRecurso(List<Recurso>listaRecursos) { 
		this.listaRecursos = listaRecursos;
		
	}
	
	public void agregarRecurso(Recurso recurso) {
		//recorro la lista de recursos para saber si el que quiero agregar ya se encuentra o no 
		for (Recurso recurso : listaRecursos) {
			if (recurso.obtenerDescripcion().equals(recurso.obtenerDescripcion())) { //aca etsa bien el equals ?
				System.out.println("El recurso ingresado ya se encuentra en el aula");
			}
			
		}
		listaRecursos.add(new Recurso(recurso));
		System.out.println("El recurso fue agregado con exito");
	}
	
	public void quitarRecurso(Recurso recurso) {
		//Recurso recursoaeliminar; hace falta esto??
		for (Recurso recurso : listaRecursos) {
			if (recurso.obtenerDescripcion().equals(recurso)) {
				//recursoaeliminar = descripcion;
				listaRecursos.remove(recurso);// esta parte, deveria ir afuera del for??
	            System.out.println("Recurso eiminado");
	        } 
			else {
				System.out.println("El recurso no se encuentra en el aula.");
			}
			}
	}
	
	
	/*----------------------------- CONSTRUCTORES -----------------------------*/
	
	public Aula (int numeroAula,List<Recurso> listaRecursos, Edificio edificio,int capacidad) {
		this.numeroAula= numeroAula;
		this.listaRecursos = listaRecursos;
		this.edificio= edificio;
		this.capacidad=capacidad;
	
	
}
}
