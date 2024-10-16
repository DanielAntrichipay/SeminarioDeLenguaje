package ar.edu.unrn.seminario.api;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;

import ar.edu.unrn.seminario.modelo.Edificio;
import ar.edu.unrn.seminario.dto.EdificioDTO;
import ar.edu.unrn.seminario.modelo.Recurso;
import ar.edu.unrn.seminario.dto.RecursoDTO;
import ar.edu.unrn.seminario.modelo.Aula;
import ar.edu.unrn.seminario.dto.AulaDTO;

//Implementa la fachada, se llama memory api porque almaceno en array list, viven en la memoria.
public class MemoryApi implements IApi {
	
	private List<Rol> roles = new ArrayList();
	private List<Usuario> usuarios = new ArrayList<>();
	
	private List<Aula> aulas = new ArrayList<>();
	private List<Edificio> edificios = new ArrayList<>();

	public MemoryApi() {

		// Inicializa valores, acá los roles, puede no hacer nada
		//Simula tener los roles precargados
		this.roles.add(new Rol(1, "ADMIN"));
		this.roles.add(new Rol(2, "ESTUDIANTE"));
		this.roles.add(new Rol(3, "INVITADO"));
		inicializarUsuarios();
	}
	
	// Inicializa usuarios
	private void inicializarUsuarios() {
		registrarUsuario("admin", "1234", "admin@unrn.edu.ar", "Admin", 1);
		registrarUsuario("ldifabio", "4", "ldifabio@unrn.edu.ar", "Lucas", 2);
		registrarUsuario("bjgorosito", "1234", "bjgorosito@unrn.edu.ar", "Bruno", 3);

	}

		// Sobreescribe registrar usuario de la API, porque la estoy implementado.
	//le doy comportamiento.
	//Creamos un usuario lo cargamos a la lista.
	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {

		Rol role = this.buscarRol(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, role);
		this.usuarios.add(usuario);

	}

	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		List<UsuarioDTO> dtos = new ArrayList<>();
		for (Usuario u : this.usuarios) {
			dtos.add(new UsuarioDTO(u.getUsuario(), u.getContrasena(), u.getNombre(), u.getEmail(),
					u.getRol().getNombre(), u.isActivo(), u.obtenerEstado()));
		}
		return dtos;
	}

	@Override
	public UsuarioDTO obtenerUsuario(String username) {
		UsuarioDTO miUsuario= null;
		for (Usuario u: usuarios) {
			if (username.equals(u.getNombre())) {
				miUsuario= new UsuarioDTO(u.getUsuario(), u.getContrasena(), u.getNombre(), u.getEmail(),
						u.getRol().getNombre(), u.isActivo(), u.obtenerEstado());
			}
		}
		return miUsuario;
	}

	@Override
	public void eliminarUsuario(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RolDTO> obtenerRoles() {
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles) {
			dtos.add(new RolDTO(r.getCodigo(), r.getNombre()));
		}
		return dtos;
	}

	@Override
	public List<RolDTO> obtenerRolesActivos() {
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles) {
			if (r.isActivo())
				dtos.add(new RolDTO(r.getCodigo(), r.getNombre()));
		}
		return dtos;
	}

	@Override
	public void guardarRol(Integer codigo, String descripcion, boolean estado) {
		
		Rol rol = new Rol(codigo, descripcion);
		this.roles.add(rol);
	}

	@Override
	public RolDTO obtenerRolPorCodigo(Integer codigo) {
		RolDTO rol = null;
		for (Rol r: this.roles) {
			if (r.getCodigo().equals(codigo)) {
				rol= new RolDTO (r.getCodigo(), r.getNombre(), r.isActivo());
			}
		}
		return rol;
	}

	@Override
	public void activarRol(Integer codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void desactivarRol(Integer codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activarUsuario(String usuario) {
		Usuario user = this.buscarUsuario(usuario);
		user.activar();
	}

	@Override
	public void desactivarUsuario(String usuario) {
		Usuario user = this.buscarUsuario(usuario);
		user.desactivar();
	}

	private Rol buscarRol(Integer codigo) {
		for (Rol rol : roles) {
			if (rol.getCodigo().equals(codigo))
				return rol;
		}
		return null;
	}

	private Usuario buscarUsuario(String usuario) {
		for (Usuario user : usuarios) {
			if (user.getUsuario().equals(usuario))
				return user;
		}
		return null;
	}
	
	//--- AMB AULA
	@Override
	public void cargarEdificio (String nombreEdificio, String direccion){
		
		boolean elEdificioExiste= false;
		
		for (Edificio e : this.edificios){
			if (e.getNombre() == nombreEdificio){
				elEdificioExiste = true;
			}
		}
			
		if (!elEdificioExiste) {
			Edificio nuevoEdificio = new Edificio (nombreEdificio, direccion);
			this.edificios.add(nuevoEdificio);
		}	
	}
	
	@Override
	public void bajaDeEdificio (String nombreEdificio){
		if (!this.edificios.isEmpty()) {
			for (Edificio e : this.edificios){
				if (e.getNombre() == nombreEdificio){
					this.edificios.remove(e);
				}
			}
		}
	}
	
	public List<EdificioDTO> obtenerEdificiosDTO(){
		List<EdificioDTO> listaEdificiosDTO = new ArrayList<>();
		for (Edificio unEdificio : this.edificios) {
			listaEdificiosDTO.add(construirEdificioDTO(unEdificio));
		}
		return listaEdificiosDTO;
		
	}
	
	@Override
	public void actualizarEdificio (String nombreEdificio, String nuevoNombreEdificio, String nuevaDireccion){
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio){
				unEdificio.setNombre(nuevoNombreEdificio);
				unEdificio.setDireccion(nuevaDireccion);
			}
		}
	}
	/*@Override
	public void actualizarEdificio (String nombreEdificio, String nuevaDireccion){
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio){
				unEdificio.setDireccion(nuevaDireccion);
			}
		}
	}
	@Override
	public void actualizarEdificio (String nombreEdificio, String nuevoNombreEdificio){
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio){
				unEdificio.setNombre(nuevoNombreEdificio);
			}
		}
	}
	*/
	// --- AULA ---
	
	@Override
	public void cargarAula (ArrayList <String> NombresDeRecursos, ArrayList <String> descripcionDeRecursos, String nombreEdificio, int numeroDeAula, int capacidadDeAula){
	// Empezar de nuevo
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio && !unEdificio.existeAulaEnLista(numeroDeAula)){
				Aula nuevaAula = new Aula (numeroDeAula, recursos, unEdificio, capacidadDeAula);
				unEdificio.agregarAula(nuevaAula);	
				this.aulas.add(nuevaAula);			 
			}	
		}
	}

	@Override
	public void bajaDeAula (String nombreEdificio, int numeroDeAula){
		
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio && unEdificio.existeAulaEnLista(numeroDeAula)){
				Aula aulaADarDeBaja = unEdificio.obtenerAulaEspecifica(numeroDeAula);
				unEdificio.quitarAula(aulaADarDeBaja);
				this.aulas.remove(aulaADarDeBaja);
			}
		}
	}
	
	@Override
	public void modificarAula (String nombreEdificio, int numeroDeAula, int nuevoNumeroDeAula, ArrayList<Recurso> nuevosRecursos) {
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio && unEdificio.existeAulaEnLista(numeroDeAula)){
				Aula aulaParaModificar = unEdificio.obtenerAulaEspecifica(numeroDeAula);
				aulaParaModificar.setListaRecurso(nuevosRecursos);
				aulaParaModificar.setNumeroAula(nuevoNumeroDeAula);
			}
		}
	}

	@Override
	public void modificarAula (String nombreEdificio, int numeroDeAula, ArrayList<Recurso> nuevosRecursos) {
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio && unEdificio.existeAulaEnLista(numeroDeAula)){
				Aula aulaParaModificar = unEdificio.obtenerAulaEspecifica(numeroDeAula);
				aulaParaModificar.setListaRecurso(nuevosRecursos);
			}
		}
	}
	
	@Override
	public void modificarAula (String nombreEdificio, int numeroDeAula, int nuevoNumeroDeAula) {
		
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio && unEdificio.existeAulaEnLista(numeroDeAula)){
				Aula aulaParaModificar = unEdificio.obtenerAulaEspecifica(numeroDeAula);
				aulaParaModificar.setNumeroAula(nuevoNumeroDeAula);
		}
	}
}
//------------------------------------------------------------------------------------------------------------

	private List<AulaDTO> construirAulasDTO (List <Aula> listaDeAulas) {
		List<AulaDTO> unaListaDeAulasDTO = new ArrayList();
		
		for (Aula unAula : listaDeAulas) {
			AulaDTO unAulaDTO = new AulaDTO(unAula.getNumeroAula(), contruirEdificioDTO(unAula.getEdificio), unAula.getCapacidad, construirRecursosDTO (unAula.getListaRecursos()));			
			unaListaDeAulasDTO.add(unAulaDTO);
		}
		return unaListaDeAulasDTO;
	}
	
	private EdificioDTO construirEdificioDTO (Edificio unEdificio) {
		EdificioDTO unEdificioDTO = new EdificioDTO (unEdificio.getNombre(), unEdificio.getDireccion());
		unEdificioDTO.setListaAulas(construirAulasDTO(unEdificio.getListaAulas()));
		return unEdificioDTO;
	}
	
	private List<RecursoDTO> construirRecursosDTO (List <Recurso> listaDeRecurso) {
		List<RecursoDTO> unaListaDeRecursosDTO = new ArrayList();
		
		for (Recurso unRecurso : listaDeRecurso) {
			RecursoDTO unRecursoDTO = new RecursoDTO(unRecurso.obtenerNombre(), unRecurso.obtenerDescripcion());
			unaListaDeRecursosDTO.add(unRecursoDTO);
		}
		return unaListaDeRecursosDTO;
	}	
}
	