
package BAL;

import DAL.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class BalServicios {
    
    Conexion conn = new Conexion();
    
    public int IDServicio;
    public String NombreServicio;
    public String CostoServicio;

    public int getIDServicio() {
        return IDServicio;
    }

    public void setIDServicio(int IDServicio) {
        this.IDServicio = IDServicio;
    }

    public String getNombreServicio() {
        return NombreServicio;
    }

    public void setNombreServicio(String NombreServicio) {
        this.NombreServicio = NombreServicio;
    }

    public String getCostoServicio() {
        return CostoServicio;
    }

    public void setCostoServicio(String CostoServicio) {
        this.CostoServicio = CostoServicio;
    }
    
    public ArrayList<BalServicios> listarServicios() {
        ResultSet rs;
        ArrayList<BalServicios> servicios = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call listarServicios()}");
            procedure.execute();
            rs = procedure.getResultSet();
            while(rs.next()){
                BalServicios servicio = new BalServicios();
                servicio.IDServicio = Integer.parseInt(rs.getString("referencia"));
                servicio.NombreServicio = rs.getString("Nombre");
                servicio.CostoServicio = rs.getString("costo");
                servicios.add(servicio);
            }
            procedure.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servicios;
    }
    
    public void agregarServicio(BalServicios servicio){
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call jcsdb.agregarServicio(?, ?)}");
            procedure.setString(1, servicio.getNombreServicio());
            procedure.setString(2, servicio.getCostoServicio());
            procedure.execute();
            procedure.close();
            JOptionPane.showMessageDialog(null, "Servicio agregado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarServicio(BalServicios servicio){
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call jcsdb.modificarServicio(?, ?, ?)}");
            procedure.setInt(1, servicio.getIDServicio());
            procedure.setString(2, servicio.getNombreServicio());
            procedure.setString(3, servicio.getCostoServicio());
            procedure.executeUpdate();
            procedure.close();
            JOptionPane.showMessageDialog(null, "Servicio Modificado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarServicio(int ID){
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call eliminarServicio(?)}");
            procedure.setInt(1, ID);
            procedure.execute();
            procedure.close();
            JOptionPane.showMessageDialog(null, "Servicio Eliminado con exito");
        } catch (SQLException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
