package ar.edu.unrn.seminario.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.exception.DataEmptyException;

public class Edificio {
	
	/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre;
	private String direccion;	
	private List<Aula> listaAulas= new ArrayList <Aula>();	
	
	
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
	
	
	@Override
	public boolean equals(Object obj) {
		
		
		if (this==obj) { // Verificar si son la misma instancia
			return true;
			
		}
		else if (obj==null || this.getClass()!= obj.getClass())  { // Verifica si el objeto con el que comparo es nulo o de clases distintas
			return false;			
		
		}
		
		// Hacer el downcasting
		
		Edificio other = (Edificio) obj;
		
		if (nombre == null) { //Si el nombre es nulo
			if (other.getNombre () != null) //Si en el otro objeto el nombre no es nulo no son iguales
				return false;
		} 
		
		else if (!nombre.equals(other.getNombre())) // Si los nombres son distintos
			return false;
		
		// Si son iguales, retorna true
		return true; 
		
	}
	/*----------------------------- CONSTRUCTORES -----------------------------*/
	
	
	public Edificio () {
		
	}
	
	public Edificio (String unNombre, String unaDireccion) throws DataEmptyException {
		super();
		this.nombre= unNombre;
		this.direccion= unaDireccion;
		
		if (nombre== "")  {
			throw new DataEmptyException ("Completar el campo de nombre por favor");
		}
		if (unaDireccion == "") {
			throw new DataEmptyException ("Completar el campo de dirección por favor");
		}
		
		
	
	
}
}
