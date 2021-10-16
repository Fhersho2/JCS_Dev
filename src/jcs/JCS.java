package jcs;

import Views.Login;

public class JCS {

    public static void main(String[] args)  {
        // TODO code application logic here
// 
//        try {
//            if (conn.Open() != null)
//            {
//                System.out.println("Conectado Correctamente");
//            }else{
//                System.out.println("Error al conectar al servidor");
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            System.out.println(ex);
////            Logger.getLogger(JCS.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Login vista = new Login();
        vista.setVisible(true);
    }

}
