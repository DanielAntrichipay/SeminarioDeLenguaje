package ar.edu.unrn.seminario.api;

import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;

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
	
	void cargarEdificio (String nombreEdificio);
	
	void bajaDeEdificio (String nombreEdificio);
	
	void actualizarEdificio (String nombreEdificio, String nuevoNombreEdificio);
	
	//--- lOGICA DE AULA.
	
	void cargarAula (String descripcionDeRecurso, String nombreEdificio, int numeroDeAula, int capacidadDeAula);
	
	void bajaDeAula (String nombreEdificio, int NumeroDeAula);
	
	void actualizarAula (String nombreEdificio, int NumeroDeAula, String nuevaDescripcionDeRecurso, int nuevoNumeroDeAula);
	
	void actualizarAula (String nombreEdificio, int NumeroDeAula, String nuevaDescripcionDeRecurso);
	
	void actualizarAula (String nombreEdificio, int NumeroDeAula, int nueevoNumeroDeAula);
	
}
