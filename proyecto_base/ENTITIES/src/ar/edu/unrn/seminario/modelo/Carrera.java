package ar.edu.unrn.seminario.modelo;

import java.util.List;

import ar.edu.unrn.seminario.exception.NotNullException;

public class Carrera {
	/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre;
	private List <Materia> listaDeMaterias;
	
	/*-------------------------------- MÉTODOS --------------------------------*/
	public String getNombre () {
		return this.nombre;
	}
	public List <Materia> getListaMaterias () {
		return this.listaDeMaterias;
	}
	
	public void setNombre (String unNombre) {
		this.nombre = unNombre;
	}
	
	public void setListaMaterias (List <Materia> unaListaDeMaterias) {
		this.listaDeMaterias = unaListaDeMaterias;
	}
	
	public void agregarMateria (Materia unaMateria) {
		this.listaDeMaterias.add(unaMateria);
	}
	
	public boolean existeMateria (String codigoCarrera) {
		
		boolean resultado = false;
		
		for (Materia unaMateria : this.listaDeMaterias) {
			if (unaMateria.equals(codigoCarrera));
				resultado = true;
		}
		
		return resultado;
	}
	
	public Materia obtenerMateria (String unCodigoDeMateria) throws NotNullException {
		Materia unaMateria = null;
		
		for (Materia materia : this.listaDeMaterias) {
			if (materia.equals(unCodigoDeMateria)) {
				unaMateria = materia;
			}
		}
		
		return unaMateria;
	}
	
	/*----------------------------- CONSTRUCTORES -----------------------------*/
	
}
