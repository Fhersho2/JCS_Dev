


//gg
package BAL;
import DAL.Conexion;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author natsu
 */
public class BalPeriodos {
    
    public int IdPeriodo;
    public int Año;
    public String Periodo;
    public Date FechaInicio;
    public Date FechaFin;

    public int getIdPeriodo() {
        return IdPeriodo;
    }

    public void setIdPeriodo(int IdPeriodo) {
        this.IdPeriodo = IdPeriodo;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int Año) {
        this.Año = Año;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String Periodo) {
        this.Periodo = Periodo;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }
    
    
    public ArrayList<BalPeriodos> listarPeriodos(){
        Conexion conn = new Conexion();
        ResultSet rs;
        ArrayList<BalPeriodos> periodos = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call listarPeriodos()}");
            procedure.execute();
            rs = procedure.getResultSet();
            while(rs.next()){
                BalPeriodos periodo = new BalPeriodos();
                periodo.IdPeriodo = rs.getInt("IDPeriodo");
                periodo.Año = Integer.parseInt(rs.getString("Year"));
                periodo.Periodo = (rs.getString("Rango"));
                periodo.FechaInicio = rs.getDate("FechaInicial");
                periodo.FechaFin = rs.getDate("FechaFin");
                periodos.add(periodo);
            }
            procedure.close();
            conn.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return periodos;
    }
    
    public void agregarPeriodo(BalPeriodos periodo) {
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call agregarPeriodo(?, ?, ?, ?)}");
            procedure.setInt(1, Integer.parseInt(periodo.getPeriodo()));
            procedure.setInt(2, periodo.getAño());
            procedure.setDate(3, periodo.getFechaInicio());
            procedure.setDate(4, periodo.getFechaFin());
            procedure.executeQuery();
            procedure.close();
            conn.cerrar();
            JOptionPane.showMessageDialog(null, "Periodo agregado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarPeriodo(BalPeriodos periodo) {
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call modificarPeriodo(?,?, ?, ?, ?)}");
            procedure.setInt(1, periodo.getIdPeriodo());
            procedure.setInt(2, Integer.parseInt(periodo.getPeriodo()));
            procedure.setInt(3, periodo.getAño());
            procedure.setDate(4, periodo.getFechaInicio());
            procedure.setDate(5, periodo.getFechaFin());
            procedure.executeQuery();
            procedure.close();
            conn.cerrar();
            JOptionPane.showMessageDialog(null, "Periodo modificado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarPeriodo(int ID) {
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call eliminarPeriodo(?)}");
            procedure.setInt(1, ID);
            procedure.executeQuery();
            procedure.close();
            conn.cerrar();
            JOptionPane.showMessageDialog(null, "Periodo eliminado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<BalPeriodos> consultarPeriodo(Date fecha)  {
        Conexion conn = new Conexion();
        ResultSet rs;
        ArrayList<BalPeriodos> periodos = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call seleccionarPeriodo(?)}");
            procedure.setDate(1, fecha);
            procedure.execute();
            rs = procedure.getResultSet();
            while(rs.next()){
                BalPeriodos periodo = new BalPeriodos();
                periodo.IdPeriodo = rs.getInt("IDPeriodo");
                periodo.Periodo = (rs.getString("Rango"));
                periodos.add(periodo);
            }
            procedure.close();
            conn.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return periodos;
    }
}