package py.una.pol.is2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtils {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* credenciales de conexión a la DB */
        // TODO hacer que esto sea configurable en algún XML
        String username = "postgres";
        String password = "admin";
        Integer portNum = 5432;
        String dbName = "agenda_pediatrica_db";

        String connectionURL = String.format("jdbc:postgresql://localhost:%s/%s", portNum, dbName);
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static java.sql.Date toSqlDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date date = formatter.parse(dateStr);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            return sqlDate;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

}
