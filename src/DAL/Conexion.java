package DAL;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    private Connection cnx = null;

    public Connection Open() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://72.249.55.114:3306/ceuarkos_JCS?noAccessToProcedureBodies=true";
        String username = "ceuarkos_jcs";
        String password = "4r34s1st3m4s";
//        String url = "jdbc:mysql://localhost:3306/ceuarkos?noAccessToProcedureBodies=true";
//        String username = "root";
//        String password = "2240467";
        if (cnx == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cnx = DriverManager.getConnection(url, username, password);
                System.out.println("Conexion Abierta");
            } catch (SQLException ex) {
                //throw new SQLException(ex);
                JOptionPane.showMessageDialog(null, "No hay conexiones disponibles");
                
            } catch (ClassNotFoundException ex) {
                throw new ClassNotFoundException(ex.getMessage());
            }
        }
        return cnx;
    }
    
    public void cerrar() throws SQLException{
        if(cnx != null) {
            cnx.close();
            System.out.println("Conexion cerrada");
        }
    }
}
