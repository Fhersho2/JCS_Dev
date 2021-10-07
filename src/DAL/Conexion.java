package DAL;

import java.sql.*;

public class Conexion {

    private static Connection cnx = null;

    public Connection Open() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/jcsdb";
        String username = "root";
        String password = "root";
        if (cnx == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cnx = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassNotFoundException(ex.getMessage());
            }
        }
        return cnx;
    }
    
    public void cerrar() throws SQLException{
        if(cnx != null) {
            cnx.close();
        }
    }
}
