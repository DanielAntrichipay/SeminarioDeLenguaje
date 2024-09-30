package ar.edu.unrn.seminario.modelo;

public class Recurso {
    
    /*------------------------------- ATRIBUTOS -------------------------------*/
	

    private String descripcion;
    

    /*-------------------------------- MÉTODOS --------------------------------*/

    public String obtenerDescripcion() {
        return descripcion;
        
    }

    public void modificarDescripcion(String descripcion) {
        this.descripcion = descripcion; 
        
    }
    
    /*----------------------------- CONSTRUCTOR -----------------------------*/
    
    public Recurso(String descripcion) {
        this.descripcion = descripcion;
        
    }
}
