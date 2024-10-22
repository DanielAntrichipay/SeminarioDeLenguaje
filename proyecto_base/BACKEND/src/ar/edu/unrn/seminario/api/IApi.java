package ar.edu.unrn.seminario.api;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.dto.AulaDTO;
import ar.edu.unrn.seminario.dto.EdificioDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.DataEmptyException;
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
	
	void modificarUsuario(String username, String nombre, String email, Integer integer); // PRUEBA ROCIO
	
	void darDeBajaUsuario(String username);  //PRUEBA ROCIO
	//--- LOGICA DE EDIFICIO.
	
	void cargarEdificio (String nombreEdificio, String direccion) throws DataEmptyException ;
	
	void bajaDeEdificio (String nombreEdificio);
	
	void actualizarEdificio (String nombreEdificio, String nuevoNombreEdificio, String nuevaDireccion);
	
	List<EdificioDTO> obtenerEdificiosDTO();
	
	/*
	void actualizarEdificio (String nombreEdificio, String nuevoNombreEdificio);
	
	void actualizarEdificio (String nombreEdificio, String nuevaDireccion);
	*/
	//--- lOGICA DE AULA.
	
	void cargarAula (List <String> nombresDeRecursos, List <String> descripcionDeRecursos, String nombreEdificio, int numeroDeAula, int capacidadDeAula);
	
	void bajaDeAula (String nombreEdificio, int numeroDeAula);
	
	void modificarAula (String nombreEdificio, int numeroDeAula, int nuevoNumeroDeAula, ArrayList<Recurso> nuevosRecursos);
	
	void modificarAula (String nombreEdificio, int numeroDeAula, ArrayList<Recurso> nuevosRecursos);
	
	void modificarAula (String nombreEdificio, int numeroDeAula, int nuevoNumeroDeAula);

	List<AulaDTO> obtenerTodasLasAulasDTO();
}