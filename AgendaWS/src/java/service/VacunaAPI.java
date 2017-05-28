/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import py.una.pol.is2.entities.Hijo;
import py.una.pol.is2.entities.VacunaCalendario;
import py.una.pol.is2.entities.Usuario;
import py.una.pol.is2.model.HijoManager;
import py.una.pol.is2.model.UsuarioManager;
import py.una.pol.is2.model.VacunaCalendarioManager;

@javax.ejb.Stateless
@Path("vacunas")
public class VacunaAPI {
      @Context
    private Request httpRequest;

    @Context
    private Response httpResponse;

    /**
     * Listar a todos los hijos según según ID del padre
     *
     * @return JSON. Representación json de la lista de todos los hijos
     */
    @GET
    @Path("listar_por_hijo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacunaCalendario> getHijos(
            @QueryParam("hijo_id") Integer hijoId) {

        UsuarioManager um = new UsuarioManager();
        VacunaCalendarioManager manager = new VacunaCalendarioManager();

        List<VacunaCalendario> vacunaLista = new ArrayList<>();
        vacunaLista = manager.getByHijoId(hijoId);

        return vacunaLista;
    }
}
