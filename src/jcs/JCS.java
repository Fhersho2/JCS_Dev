package jcs;

import DAL.Conexion;
import java.sql.*;
import Views.Home;


public class JCS {

    public static void main(String[] args)  {
        // TODO code application logic here
//        Conexion conn = new Conexion();
//        try {
//            if (conn.Open() != null)
//            {
//                JOptionPane.showMessageDialog(null, "Conectado Correctamente");
//            }else{
//                JOptionPane.showMessageDialog(null,"Mensaje", "Error al conectar al servidor", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(JCS.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Home vista = new Home();
        vista.setVisible(true);
    }

}
