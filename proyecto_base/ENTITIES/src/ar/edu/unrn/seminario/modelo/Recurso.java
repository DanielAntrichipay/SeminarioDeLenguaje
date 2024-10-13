package ar.edu.unrn.seminario.modelo;

public class Recurso {
    
    /*------------------------------- ATRIBUTOS -------------------------------*/
	
	private String nombre; //modifique recurso para que ahora tenga un nombre
    private String descripcion;
    

    /*-------------------------------- MÉTODOS --------------------------------*/
    
    public String obtenerNombre() {  
        return nombre;
    
    }
    
    public void modificarNombre(String nombre) {
        this.nombre = nombre;
   
    }
    
    public String obtenerDescripcion() {
        return descripcion;
     
    }

    public void modificarDescripcion(String descripcion) {
        this.descripcion = descripcion; 
        
    }
    
    /*----------------------------- CONSTRUCTOR -----------------------------*/
    
    public Recurso(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        
    }
}
