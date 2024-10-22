package ar.edu.unrn.seminario.api;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;

import ar.edu.unrn.seminario.modelo.Edificio;
import ar.edu.unrn.seminario.dto.EdificioDTO;
import ar.edu.unrn.seminario.modelo.Recurso;
import ar.edu.unrn.seminario.dto.RecursoDTO;
import ar.edu.unrn.seminario.modelo.Aula;
import ar.edu.unrn.seminario.dto.AulaDTO;

public class MemoryApi implements IApi {
	
	/*------------------------------------------------------ ATRIBUTOS ------------------------------------------------------*/
	
	private List<Rol> roles = new ArrayList();
	private List<Usuario> usuarios = new ArrayList<>();
	
	private List<Aula> aulas = new ArrayList<>();
	private List<Edificio> edificios = new ArrayList<>();
	
	/*------------------------------------------------------ CONSTRUCTORES ------------------------------------------------------*/

	public MemoryApi() {
		
		try {
		this.roles.add(new Rol(1, "ADMIN"));
			this.roles.add(new Rol(2, "ESTUDIANTE"));
			this.roles.add(new Rol(3, "INVITADO"));
			inicializarUsuarios();
			List <String> nombresDeRecursos = new ArrayList();
			nombresDeRecursos.add("Ruecurso_1");
			nombresDeRecursos.add("Ruecurso_2");
			List <String> descripcionDeRecursos = new ArrayList();
			descripcionDeRecursos.add("un Recurso");
			cargarAula (nombresDeRecursos, descripcionDeRecursos, "Edificio_Chiquito", 9, 30);
			cargarEdificio("Edificio_Chiquito", "Direccion");
		} catch (DataEmptyException execepcion){
			System.out.println ("Al estar harcodeado no deberia saltar esta excepción, pero de todas formas hay que capturarla");
		}
	}
	
	/*------------------------------------------------------ MÉTODOS ------------------------------------------------------*/
	
	//-- ABM USUARIOS ----------------
	
	private void inicializarUsuarios() {
		registrarUsuario("admin", "1234", "admin@unrn.edu.ar", "Admin", 1);
		registrarUsuario("ldifabio", "4", "ldifabio@unrn.edu.ar", "Lucas", 2);
		registrarUsuario("bjgorosito", "1234", "bjgorosito@unrn.edu.ar", "Bruno", 3);

	}
	
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
	
	public void modificarUsuario(String username, String nombre, String email, Integer rol) {
	    Usuario user = this.buscarUsuario(username);
	    if (user != null) {
	        user.setNombre(nombre);
	        user.setEmail(email);
	        Rol nuevoRol = buscarRol(rol);
	        user.setRol(nuevoRol);
	    }
	}


	@Override
	public void darDeBajaUsuario(String username) {
	    Usuario usuario = buscarUsuario(username); 
	    if (usuario != null) {
	        usuarios.remove(usuario);  //lo elimina de la lista...
	        System.out.println("Usuario " + username + " se ha dado de baja.");
	    } else {
	        System.out.println("Usuario no encontrado: " + username);
	    }
	}
	
	/*-- ABM AULA ----------------*/
	
	//EDIFICIO:
	
	@Override
	public void cargarEdificio (String nombreEdificio, String direccion) throws DataEmptyException {
		
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
	@Override
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
	/*
	
	@Override
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
	
	// AULA:
	
	@Override
	public void cargarAula (List <String> nombresDeRecursos, List <String> descripcionDeRecursos, String nombreEdificio, int numeroDeAula, int capacidadDeAula){
	// Empezar de nuevo
		for (Edificio unEdificio : this.edificios){
			if (unEdificio.getNombre() == nombreEdificio && !unEdificio.existeAulaEnLista(numeroDeAula)){
				Aula nuevaAula = new Aula (numeroDeAula, construirRecursos(nombresDeRecursos, descripcionDeRecursos), unEdificio, capacidadDeAula);
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
	@Override
	public List<AulaDTO> obtenerTodasLasAulasDTO(){
		return construirAulaDTO(this.aulas);
	}
 
	/*------------------------------------------------------ MÉTODOS INTERNOS ------------------------------------------------------*/
	
	private AulaDTO construirAulaDTO (Aula unAula) {
		AulaDTO unAulaDTO = new AulaDTO(unAula.getNumeroAula(), construirEdificioDTO(unAula.getEdificio()), unAula.getCapacidad(), construirRecursoDTO (unAula.getListaRecursos()));
		return unAulaDTO;
		//return new AulaDTO(unAula.getNumeroAula(), construirEdificioDTO(unAula.getEdificio()), unAula.getCapacidad(), construirRecursosDTO (unAula.getListaRecursos()));
		//SE PUEDE??
	}
	private List<AulaDTO> construirAulaDTO (List <Aula> listaDeAulas) {
		
		List<AulaDTO> unaListaDeAulasDTO = new ArrayList();
		for (Aula unAula : listaDeAulas) {
			unaListaDeAulasDTO.add(construirAulaDTO (unAula));
		}
		return unaListaDeAulasDTO;
	}
	
	
	
	private EdificioDTO construirEdificioDTO (Edificio unEdificio){
		EdificioDTO unEdificioDTO = new EdificioDTO (unEdificio.getNombre(), unEdificio.getDireccion(), construirAulaDTO(unEdificio.getListaAulas()));
		return unEdificioDTO;
		// return new EdificioDTO (unEdificio.getNombre(), unEdificio.getDireccion(), construirAulasDTO(unEdificio.getListaAulas())) - SE PUEDE??
	}
	private List<EdificioDTO> contruirEdficioDTO(List<Edificio> ListaDeEdificios){
		
		List<EdificioDTO> listaEdificiosDTO = new ArrayList();
		for (Edificio unEdificio : ListaDeEdificios) {
			listaEdificiosDTO.add(construirEdificioDTO (unEdificio));
		}
		return listaEdificiosDTO;
	}
	
	
	
	private RecursoDTO construirRecursoDTO (Recurso unRecurso) {
		RecursoDTO unRecursoDTO = new RecursoDTO (unRecurso.obtenerNombre(), unRecurso.obtenerDescripcion());
		return unRecursoDTO;
		//return new RecursoDTO (unRecurso.obtenerNombre(), unRecurso.obtenerDescripcion()); //SE PUEDE??
	}
	private List<RecursoDTO> construirRecursoDTO (List <Recurso> listaDeRecurso) {
		
		List<RecursoDTO> unaListaDeRecursosDTO = new ArrayList();
		for (Recurso unRecurso : listaDeRecurso) {
			unaListaDeRecursosDTO.add(construirRecursoDTO (unRecurso));
		}
		return unaListaDeRecursosDTO;
	}

	
	
	private List<Recurso> construirRecursos (List <String> nombresDeRecursos, List <String> descripcionDeRecursos){
		List<Recurso> listaDeRecursos = new ArrayList();
		for (String unNombreRecurso : nombresDeRecursos) {
			for (String unaDescripcionDeRecurso : descripcionDeRecursos) {
				Recurso unRecurso = new Recurso(unNombreRecurso, unaDescripcionDeRecurso);
				listaDeRecursos.add(unRecurso);
			}
		}
		return listaDeRecursos;
	}
}
	


