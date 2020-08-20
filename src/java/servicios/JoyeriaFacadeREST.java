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
import modelo.Joyeria;

/**
 *
 * @author USUARIO
 */
@Stateless
@Path("modelo.joyeria")
public class JoyeriaFacadeREST extends AbstractFacade<Joyeria> {

    @PersistenceContext(unitName = "joyeriasPU")
    private EntityManager em;

    public JoyeriaFacadeREST() {
        super(Joyeria.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Joyeria entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Joyeria entity) {
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
    public Joyeria find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    
    //crear
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("idjoyeria") int idjoyeria, @FormParam("nombre") String nombre, 
            @FormParam("ubicacion") String ubicacion , @FormParam("direccion") String direccion,
            @FormParam("encargado") String encargado,@FormParam("dim_local") String dim_local,
            @FormParam("ciudad") String ciudad){
        
        Joyeria ob = new Joyeria(idjoyeria, nombre, ubicacion, direccion, encargado, dim_local, ciudad);
        super.create(ob);
        return "El usuario se creo con exito";   
    }

    
    //leer 
    @POST
    @Path("leerubicacion")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> leer (@FormParam("ubicacion") String ubicacion){
        TypedQuery consulta = getEntityManager().createQuery("SELECT j FROM Joyeria j WHERE j.ubicacion = :ubicacion" , Joyeria.class);
        consulta.setParameter("ubicacion", ubicacion);
        return consulta.getResultList();   
    }

    
    //editar
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("idjoyeria") int idjoyeria, @FormParam("nombre") String nombre, 
            @FormParam("ubicacion") String ubicacion , @FormParam("direccion") String direccion,
            @FormParam("encargado") String encargado,@FormParam("dim_local") String dim_local,
            @FormParam("ciudad") String ciudad){
        Joyeria ob = super.find(idjoyeria);
        ob.setNombre(nombre);
        ob.setUbicacion(ubicacion);
        ob.setDireccion(direccion);
        ob.setEncargado(encargado);
        ob.setDimLocal(dim_local);
        ob.setCiudad(ciudad);
        super.edit(ob);
        return "El usuario se dito con exito";
    }
    
    
    //eliminar 
    @POST
    @Path("eliminar")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String eliminar (@FormParam("idjoyeria") int idjoyeria){
        Joyeria ob = super.find(idjoyeria);
        super.remove(ob);
        return "La joyeria se elimino con exito";
    }
    
    
    //Un recurso web que me permita obtener las joyerías de una ciudad 
    @POST
    @Path("leerciudad")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> leerciudad (@FormParam("ciudad") String ciudad){
        TypedQuery consulta = getEntityManager().createQuery("SELECT j FROM Joyeria j WHERE j.ciudad = :ciudad" , Joyeria.class);
        consulta.setParameter("ciudad", ciudad);
        return consulta.getResultList();   
    }
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
