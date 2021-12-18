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
 * @author Terckoer
 */
public class BalReportes {
        public Date FechaInicio;
        public Date FechaFin;
        public BalPagos pago = new BalPagos();
        public BalAlumnos alumno = new BalAlumnos();
        public BalUsuarios usuario = new BalUsuarios();
        

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
    
    
    public ArrayList<BalReportes> reportes(Date fechaInicio, Date fechaFin)  {
        Conexion conn = new Conexion();
        ResultSet rs;
        ArrayList<BalReportes> periodos = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call reportes(?,?)}");
            procedure.setDate(1, fechaInicio);
            procedure.setDate(2, fechaFin);

            procedure.execute();
            rs = procedure.getResultSet();
            while(rs.next()){
                BalReportes periodo = new BalReportes();
                
                periodo.usuario.fullname =rs.getString("Nombre");
                periodo.alumno.Nombre = rs.getString("Nombre");
                periodo.pago.IDAlumno = rs.getInt("IDAlumno");
                periodo.pago.Referencia_P = rs.getString("Referencia_P");
                periodo.pago.TipoPago = rs.getString("TipoPago");
                periodo.pago.NombreServicio = rs.getString("NombreServicio");
                periodo.pago.Descuento = rs.getString("Descuento");
                periodo.pago.PagoTotal = rs.getString("PagoTotal");
                periodo.pago.Fecha = rs.getDate("Fecha");
                periodo.pago.Estatus = rs.getString("Estatus");

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
