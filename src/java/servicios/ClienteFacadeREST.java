/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Cliente;
import static modelo.Cliente_.edad;
import modelo.Joyeria;

/**
 *
 * @author USUARIO
 */
@Stateless
@Path("modelo.cliente")
public class ClienteFacadeREST extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "joyeriasPU")
    private EntityManager em;

    public ClienteFacadeREST() {
        super(Cliente.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cliente entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cliente entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cliente find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    //CREAR
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("idcliente") int cliente, @FormParam("nombre") String nombre,
            @FormParam("direccion") String direccion, @FormParam("correo") String correo,
            @FormParam("telefono") String telefono, @FormParam("ciudad") String ciudad, @FormParam("edad") String edad,
            @FormParam("joyeria") String joyeria) {
        Cliente ob = new Cliente(cliente, nombre, direccion, correo, telefono, ciudad, edad, joyeria);
        super.create(ob);
        return "el usuario se creo con exito";

    }

    //leer
    @POST
    @Path("leerciudad")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> leer(@FormParam("ciudad") String ciudad) {
        TypedQuery consulta = getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.ciudad = :ciudad", Cliente.class);
        consulta.setParameter("ciudad", ciudad);
        return consulta.getResultList();

    }

    //editar
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("idcliente") int idcliente, @FormParam("nombre") String nombre,
            @FormParam("direccion") String direccion, @FormParam("correo") String correo,
            @FormParam("telefono") String telefono, @FormParam("ciudad") String ciudad, @FormParam("edad") String edad,
            @FormParam("joyeria") String joyeria) {

        Cliente ob = super.find(idcliente);
        ob.setNombre(nombre);
        ob.setDireccion(direccion);
        ob.setCorreo(correo);
        ob.setCiudad(ciudad);
        ob.setTelefono(telefono);
        ob.setEdad(edad);
        ob.setIdjoyeria(joyeria);
        super.edit(ob);

        return "El usuario se edito correctamente";
    }

    //eliminar
    @POST
    @Path("eliminar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String eliminar(@FormParam("idcliente") int idcliente) {
        Cliente ob = super.find(idcliente);
        super.remove(ob);
        return "Se elimino correctamente";
    }

    //Un recurso Web que permita obtener todos los clientes mayores de edad 
    @POST
    @Path("mayor")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> mayor(@FormParam("edad") String edad) {
       TypedQuery consulta = getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.edad >= :edad", Cliente.class);
        consulta.setParameter("edad", edad);
        return consulta.getResultList();
    }

    //Un recurso web que me permita obtener todos los clientes que pertenecen a una joyería 
    @POST
    @Path("clientesdeunajoyeria")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cliente> joyeria (@FormParam("idjoyeria") String idjoyeria ){
        TypedQuery consultas = getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.idjoyeria = :idjoyeria" , Cliente.class);
        consultas.setParameter("idjoyeria", idjoyeria);
        return consultas.getResultList();
        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
