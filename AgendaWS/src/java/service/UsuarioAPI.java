package service;

import entities.Usuarios;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.persistence.NoResultException;

import py.una.pol.is2.model.UsuarioManager;
import py.una.pol.is2.model.HijoManager;
import py.una.pol.is2.entities.Usuario;
import py.una.pol.is2.entities.Hijo;

@javax.ejb.Stateless
@Path("usuarios")
public class UsuarioAPI {

    @Context
    private Request httpRequest;

    @Context
    private Response httpResponse;

    /**
     * Listar a todos los usuarios
     *
     * @return JSON. Representación json de la lista de todos los usuarios
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> findAll() {
        UsuarioManager um = new UsuarioManager();

        List<Usuario> usuarioLista = new ArrayList<Usuario>();
        usuarioLista = um.getAll();

        return usuarioLista;
    }

    /**
     * Obtener usuario según ID en base de datos
     *
     * @return JSON. Representación json de un usuario
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario findById(@PathParam("id") Integer id) {

        UsuarioManager um = new UsuarioManager();
        Usuario usuario = um.getById(id);
        return usuario;
    }
    
    /**
     * Obtener usuario según ID en base de datos
     *
     * @return JSON. Representación json de un usuario
     */
    @GET
    @Path("obtener_por_correo")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario findByCorreo(
            @QueryParam("correo") String correo) {

        UsuarioManager um = new UsuarioManager();
        Usuario usuario = um.getByCorreo(correo);
        return usuario;
    }

    @GET
    @Path("validarUsuario/{correo}")
    //@Produces(MediaType.APPLICATION_JSON)
    public Boolean validarUsuario(@PathParam("correo") String correo) {

        UsuarioManager um = new UsuarioManager();

        Usuario usuario = um.getByCorreo(correo);

        Boolean esValido = false;

        if (usuario != null) {
            esValido = true;
        }

        return esValido;

    }

    /**
     * Listar a todos los hijos según según ID del padre
     *
     * @return JSON. Representación json de la lista de todos los hijos
     */
    @GET
    @Path("obtener_hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hijo> getHijos(
            @QueryParam("padre_id") Integer padreId) {

        UsuarioManager um = new UsuarioManager();

        List<Hijo> hijoLista = new ArrayList<Hijo>();
        hijoLista = um.getHijos(padreId);

        return hijoLista;
    }

    /**
     * Registrar a un hijo
     *
     * @param nombre String para el nombre
     * @param apellido String para el apellido
     * @param fechaNacimiento String para la fecha de nacimiento. Debe tener el
     * formato yyyy-mm-dd
     * @param padreId Integer del id del padre
     * @return booleano true si se inserta correctamente, de lo contrario false.
     */
    @POST
    @Path("crear_hijo")
    public boolean crearHijo(
            @FormParam("nombre") String nombre,
            @FormParam("apellido") String apellido,
            @FormParam("fecha_nacimiento") String fechaNacimiento,
            @FormParam("padre_id") Integer padreId) {

        HijoManager hm = new HijoManager();
        Hijo nuevoHijo = new Hijo(nombre, apellido, fechaNacimiento, padreId);
        return hm.insertar(nuevoHijo);
    }

    
    @POST
    @Path("editar")
    public boolean editarUsuario(
            @FormParam("nombre") String nombre,
            @FormParam("correo") String correo,
            @FormParam("usuario_id") Integer usuarioId) {

        UsuarioManager um = new UsuarioManager();
        Usuario usuario = new Usuario(nombre, correo);
        usuario.setId(usuarioId);
        return um.editar(usuario);
    }

}
