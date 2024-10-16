package ar.edu.unrn.seminario.modelo;
import ar.edu.unrn.seminario.modelo.Recurso;// importo el recurso pero , no se si lo hago mal , o hay otra cuestion detras que no me permite usaro je
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
	
	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	
	public List<Recurso> getListaRecursos() {
		return listaRecursos;
	}
	
	public void setListaRecurso(List<Recurso>listaRecursos) { 
		this.listaRecursos = listaRecursos;
		
	}
	
	public void agregarRecurso(Recurso recursos) {
		
		for (Recurso r : listaRecursos) {
			if (r.obtenerDescripcion().equals(r.obtenerDescripcion())) { 
				System.out.println("El recurso ingresado ya se encuentra en el aula");
			}
			
		}
		listaRecursos.add(recursos);
		
		System.out.println("El recurso fue agregado con exito");
	}
	
	public void quitarRecurso(Recurso recurso) {
		for (Recurso r : listaRecursos) {
			if (r.obtenerDescripcion().equals(recurso)) {
		
				listaRecursos.remove(recurso);
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
