/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    Conexion conn = new Conexion();
    public int IdPeriodo;

    public int getIdPeriodo() {
        return IdPeriodo;
    }

    public void setIdPeriodo(int IdPeriodo) {
        this.IdPeriodo = IdPeriodo;
    }
    public int Año;
    public String Periodo;
    public Date FechaInicio;
    public Date FechaFin;

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
    
    public ArrayList<BalPeriodos> listarPeriodos() {
        ResultSet rs;
        ArrayList<BalPeriodos> periodos = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call listarPeriodos()}");
            procedure.execute();
            rs = procedure.getResultSet();
            while(rs.next()){
                BalPeriodos periodo = new BalPeriodos();
                periodo.IdPeriodo = rs.getInt("IDPeriodo");
                periodo.Año = Integer.parseInt(rs.getString("Año"));
                periodo.Periodo = (rs.getString("Rango"));
                periodo.FechaInicio = rs.getDate("FechaInicial");
                periodo.FechaFin = rs.getDate("FechaFin");
                periodos.add(periodo);
            }
            procedure.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return periodos;
    }
}
