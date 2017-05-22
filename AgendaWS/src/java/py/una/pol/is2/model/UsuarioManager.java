package py.una.pol.is2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;

import py.una.pol.is2.util.DBUtils;
import py.una.pol.is2.entities.Usuario;
import py.una.pol.is2.entities.Hijo;

public class UsuarioManager {

    public boolean insertar(Usuario usuario) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO usuarios(nombre, correo) "
                    + "VALUES(?, ?)");
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(4, usuario.getCorreo());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean editar(Usuario usuario) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sqlString = "UPDATE usuarios SET nombre = ?, correo = ? WHERE id = ? ";

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sqlString);
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setInt(3, usuario.getId());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean delete(Usuario usuario) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sqlString = "DELEETE FROM usuarios WHERE id_usuario = ?";

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sqlString);
            pstmt.setInt(1, usuario.getId());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public Usuario getByCorreo(String correo) {
        Usuario retValue = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sqlString = "SELECT * FROM usuarios WHERE correo = ?";

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sqlString);
            pstmt.setString(1, correo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Usuario();
                retValue.setNombre(rs.getString("nombre"));
                retValue.setId(rs.getInt("id"));
                retValue.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public Usuario getById(int id) {
        Usuario retValue = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sqlString = "SELECT * FROM usuarios WHERE id = ?";

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sqlString);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Usuario();
                retValue.setNombre(rs.getString("nombre"));
                retValue.setId(rs.getInt("id"));
                retValue.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public List<Usuario> getAll() {
        List<Usuario> retValue = new ArrayList<Usuario>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sqlString = "SELECT * FROM usuarios";
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sqlString);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setId(rs.getInt("id"));
                usuario.setCorreo(rs.getString("correo"));

                retValue.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public List<Hijo> getHijos(Integer padreId) {
        List<Hijo> retValue = new ArrayList<Hijo>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sqlString = "SELECT * FROM hijos WHERE padre_id = ?";

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sqlString);
            pstmt.setInt(1, padreId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Hijo hijo = new Hijo();
                hijo.setHijoId(rs.getInt("hijo_id"));
                hijo.setNombre(rs.getString("nombre"));
                hijo.setApellido(rs.getString("apellido"));
                hijo.setPadreId(rs.getInt("padre_id"));
                hijo.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                retValue.add(hijo);
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

}
