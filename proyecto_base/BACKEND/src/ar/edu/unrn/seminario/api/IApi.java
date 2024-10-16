package ar.edu.unrn.seminario.api;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Recurso;

//Esto es la fachada
public interface IApi {

	void registrarUsuario(String username, String password, String email, String nombre, Integer rol);

	UsuarioDTO obtenerUsuario(String username); //Transformar el dato que obtengo del usuario a un DTO. Cómo dato plano.

	void eliminarUsuario(String username); 

	List<RolDTO> obtenerRoles();

	List<RolDTO> obtenerRolesActivos();

	void guardarRol(Integer codigo, String descripcion, boolean estado); // crear el objeto de dominio �Rol�

	RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

	void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

	void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

	List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

	void activarUsuario(String username); // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void desactivarUsuario(String username); // recuperar el objeto Usuario, implementar el comportamiento de estado.
	
	//--- LOGICA DE EDIFICIO.
	
	void cargarEdificio (String nombreEdificio, String direccion);
	
	void bajaDeEdificio (String nombreEdificio);
	
	void actualizarEdificio (String nombreEdificio, String nuevoNombreEdificio, String nuevaDireccion);
	
	/*
	void actualizarEdificio (String nombreEdificio, String nuevoNombreEdificio);
	
	void actualizarEdificio (String nombreEdificio, String nuevaDireccion);
	*/
	//--- lOGICA DE AULA.
	
	void cargarAula (ArrayList <String> NombresDeRecursos, ArrayList <String> descripcionDeRecursos, String nombreEdificio, int numeroDeAula, int capacidadDeAula);
	
	void bajaDeAula (String nombreEdificio, int numeroDeAula);
	
	void modificarAula (String nombreEdificio, int numeroDeAula, int nuevoNumeroDeAula, ArrayList<Recurso> nuevosRecursos);
	
	void modificarAula (String nombreEdificio, int numeroDeAula, ArrayList<Recurso> nuevosRecursos);
	
	void modificarAula (String nombreEdificio, int numeroDeAula, int nuevoNumeroDeAula);
}
