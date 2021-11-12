package jcs;
import DAL.Conexion;
import Views.Home;
import Views.Login;
import java.sql.SQLException;

public class JCS {

    public static void main(String[] args) throws SQLException  {
        // TODO code application logic here
//        Conexion conn = new Conexion();

        
        
//        try {
//            if (conn.Open() != null)
//            {
//                System.out.println("Conectado Correctamente");
//            }else{
//                System.out.println("Error al conectar al servidor");
//            }
           

            
//        } catch (ClassNotFoundException ex) {
//            System.out.println(ex);
//        }

        Login vista = new Login();
        vista.setVisible(true);

    }

}
