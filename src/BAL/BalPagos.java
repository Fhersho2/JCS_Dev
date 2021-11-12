/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAL;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import DAL.Conexion;
import java.sql.Date;


public class BalPagos {
    
    public String Referencia_P;
    public int IDUsuario;
    public int IDAlumno;
    public Date Fecha;
    public int IDPeriodo;
    public String Costo;
    public String TipoPago;
    public String Estatus;

    public String getReferencia_P() {
        return Referencia_P;
    }

    public void setReferencia_P(String Referencia_P) {
        this.Referencia_P = Referencia_P;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public int getIDAlumno() {
        return IDAlumno;
    }

    public void setIDAlumno(int IDAlumno) {
        this.IDAlumno = IDAlumno;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public int getIDPeriodo() {
        return IDPeriodo;
    }

    public void setIDPeriodo(int IDPeriodo) {
        this.IDPeriodo = IDPeriodo;
    }

    public String getCosto() {
        return Costo;
    }

    public void setCosto(String Costo) {
        this.Costo = Costo;
    }

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String TipoPago) {
        this.TipoPago = TipoPago;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }
    
    public void agregarPagoContado(BalPagos pago){
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call agregarPago(?,?,?,?,?,?,?,?)}");
            procedure.setString(1, pago.getReferencia_P());
            procedure.setInt(2, pago.getIDUsuario());
            procedure.setInt(3, pago.getIDAlumno());
            procedure.setDate(4, pago.getFecha());
            procedure.setInt(5, pago.getIDPeriodo());
            procedure.setString(6, pago.getCosto());
            procedure.setString(7, pago.getTipoPago());
            procedure.setString(8, pago.getEstatus());
            procedure.executeQuery();
            procedure.close();
            conn.cerrar();
            JOptionPane.showMessageDialog(null, "Pago agregado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String generarCodigo(){
        Conexion conn = new Conexion();
        ResultSet rs;
        String codigo = "";
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call contarFilas()}");
            procedure.execute();
            rs = procedure.getResultSet();
            while(rs.next()){
                codigo = rs.getString("Cantidad");
            }
            procedure.close();
            conn.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "RP000" +  (Integer.parseInt(codigo) + 1);
    } 
}
