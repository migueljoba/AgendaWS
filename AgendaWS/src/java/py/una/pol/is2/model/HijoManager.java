package py.una.pol.is2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.una.pol.is2.entities.Usuario;
import py.una.pol.is2.entities.Hijo;
import py.una.pol.is2.util.DBUtils;

/**
 *
 * @author mjose
 */
public class HijoManager {

    public boolean insertar(Hijo hijo) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO hijos(nombre, apellido, fecha_nacimiento, padre_id) "
                    + "VALUES(?, ?, ?, ?)");
            pstmt.setString(1, hijo.getNombre());
            pstmt.setString(2, hijo.getApellido());

            java.sql.Date fechaNacimiento = DBUtils.toSqlDate(hijo.getFechaNacimiento());
            pstmt.setDate(3, fechaNacimiento);

            pstmt.setInt(4, hijo.getPadreId());

            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(HijoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }
    
}
