/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.is2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.una.pol.is2.entities.Usuario;
import py.una.pol.is2.util.DBUtils;

import java.util.List;
import java.util.ArrayList;

import py.una.pol.is2.entities.VacunaCalendario;
import py.una.pol.is2.entities.Vacuna;

public class VacunaCalendarioManager {

    public List<VacunaCalendario> getByHijoId(int id) {
        List<VacunaCalendario> vacunas = new ArrayList<>();

        Usuario retValue = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sqlString = "SELECT cal.vacunas_calendario_id, vac.vacuna_id, "
                + "cal.fecha_aplicacion, cal.aplicada, vac.nombre "
                + "FROM hijos h INNER JOIN vacuna_calendario cal "
                + "ON h.hijo_id = cal.hijo_id INNER JOIN vacuna_tipo vac "
                + "ON cal.vacuna_id = vac.vacuna_id WHERE h.hijo_id = ?";

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sqlString);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                // datos generales
                VacunaCalendario vacunaCal = new VacunaCalendario();
                vacunaCal.setVacunaCalendarioId(rs.getInt("vacunas_calendario_id"));
                vacunaCal.setFechaAplicacion(rs.getString("fecha_aplicacion"));
                vacunaCal.setAplicada(rs.getBoolean("aplicada"));

                // datos de vacuna
                Vacuna vacuna = new Vacuna();
                vacuna.setVacunaId(rs.getInt("vacuna_id"));
                vacuna.setNombre(rs.getString("nombre"));

                vacunaCal.setVacuna(vacuna);

                vacunas.add(vacunaCal);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VacunaCalendarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return vacunas;
    }

}
