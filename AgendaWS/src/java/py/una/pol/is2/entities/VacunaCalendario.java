/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.is2.entities;

import py.una.pol.is2.entities.Hijo;
import py.una.pol.is2.entities.Vacuna;

/**
 *
 * @author mjose
 */
public class VacunaCalendario {

    /*
    	vacunas_calendario_id SERIAL UNIQUE,
	hijo_id INTEGER,
	vacuna_id INTEGER,
	fecha_aplicacion DATE,
	aplicada BOOLEAN NOT NULL DEFAULT FALSE
     */
    private Integer vacunaCalendarioId;
    private Hijo hijo;
    private Vacuna vacuna;
    private String fechaAplicacion;
    private boolean aplicada;

    public Integer getVacunaCalendarioId() {
        return vacunaCalendarioId;
    }

    public void setVacunaCalendarioId(Integer vacunaCalendarioId) {
        this.vacunaCalendarioId = vacunaCalendarioId;
    }

    public Hijo getHijo() {
        return hijo;
    }

    public void setHijo(Hijo hijo) {
        this.hijo = hijo;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public boolean isAplicada() {
        return aplicada;
    }

    public void setAplicada(boolean aplicada) {
        this.aplicada = aplicada;
    }

}
