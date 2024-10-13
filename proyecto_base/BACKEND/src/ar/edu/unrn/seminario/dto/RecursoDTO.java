package ar.edu.unrn.seminario.dto;

public class RecursoDTO{
  
	/*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre;
    private String descripcion;
    
    
    /*-------------------------------- MÉTODOS --------------------------------*/
    
    public String obtenerNombre() {
    	return nombre;
    
    }

    public String obtenerDescripcion() {
        return descripcion;
        
    }
    
    public void setNombre (String nombre) {
    	this.nombre= nombre;
    	
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; 
        
    }
    
    /*----------------------------- CONSTRUCTOR -----------------------------*/
    
    public RecursoDTO(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        
    }
}