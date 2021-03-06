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
import java.util.ArrayList;

public class BalPagos {

    public int IDDetalle;
    public String Referencia_P;
    public String Referencia_S;
    public String Nota;
    public String NombreServicio;
    public String Mensualidad;
    public Date FechaVencimiento;
    public String PagoMensualidad;
    public String Descuento;
    public String PagoTotal;
    public String TipoDePago;

    public String getTipoDePago() {
        return TipoDePago;
    }

    public void setTipoDePago(String TipoDePago) {
        this.TipoDePago = TipoDePago;
    }

    public String getPagoTotal() {
        return PagoTotal;
    }

    public void setPagoTotal(String PagoTotal) {
        this.PagoTotal = PagoTotal;
    }

    public String getReferencia_S() {
        return Referencia_S;
    }

    public void setReferencia_S(String Referencia_S) {
        this.Referencia_S = Referencia_S;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String Nota) {
        this.Nota = Nota;
    }

    public String getNombreServicio() {
        return NombreServicio;
    }

    public void setNombreServicio(String NombreServicio) {
        this.NombreServicio = NombreServicio;
    }

    public String getMensualidad() {
        return Mensualidad;
    }

    public void setMensualidad(String Mensualidad) {
        this.Mensualidad = Mensualidad;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(Date FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public String getPagoMensualidad() {
        return PagoMensualidad;
    }

    public void setPagoMensualidad(String PagoMensualidad) {
        this.PagoMensualidad = PagoMensualidad;
    }

    public String getDescuento() {
        return Descuento;
    }

    public void setDescuento(String Descuento) {
        this.Descuento = Descuento;
    }

    public int getIDDetalle() {
        return IDDetalle;
    }

    public void setIDDetalle(int IDDetalle) {
        this.IDDetalle = IDDetalle;
    }
    public int IDUsuario;
    public int IDAlumno;
    public Date Fecha;
    public int IDPeriodo;
    public String Costo;
    public String TipoPago;
    public String Estatus;
    public String banco;
    public String digitos;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getDigitos() {
        return digitos;
    }

    public void setDigitos(String digitos) {
        this.digitos = digitos;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }
    public String Servicio;

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

    public boolean validarReferencia(String referencia){
        Conexion conn = new Conexion();

        try {
            CallableStatement procedure1 = conn.Open().prepareCall("{call validarReferencia(?)}");
            procedure1.setString(1, referencia);
            procedure1.executeQuery();
            final ResultSet rs = procedure1.getResultSet();
            int cont = 0;
            while (rs.next()) {
                cont++;
            }
            System.out.println(cont);
            procedure1.close();
            conn.cerrar();
            if (cont > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public void agregarPagoContado(BalPagos pago) {
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

    public void agregarPagoContadoDetalle(BalPagos pago) {
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call agregarPagoDetalle(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            procedure.setString(1, pago.getReferencia_P());
            procedure.setString(2, pago.getReferencia_S());
            procedure.setString(3, pago.getNota());
            procedure.setString(4, pago.getNombreServicio());
            procedure.setString(5, pago.getMensualidad());
            procedure.setDate(6, pago.getFechaVencimiento());
            procedure.setString(7, pago.getPagoMensualidad());
            procedure.setString(8, pago.getDescuento());
            procedure.setString(9, pago.getPagoTotal());
            procedure.setString(10, pago.getEstatus());
            procedure.setString(11, pago.getTipoDePago());
            procedure.setString(12, pago.getDigitos());
            procedure.setString(13, pago.getBanco());


            procedure.executeQuery();
            procedure.close();
            conn.cerrar();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String generarCodigo() {
        Conexion conn = new Conexion();
        ResultSet rs;
        String codigo = "";
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call contarFilas()}");
            procedure.execute();
            rs = procedure.getResultSet();
            while (rs.next()) {
                codigo = rs.getString("Cantidad");
            }
            procedure.close();
            conn.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "RP000" + (Integer.parseInt(codigo) + 1);
    }

    public ArrayList<BalPagos> listarPagos() {
        Conexion conn = new Conexion();
        ResultSet rs;
        ArrayList<BalPagos> pagos = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call cargarMensualidad()}");
            procedure.execute();
            rs = procedure.getResultSet();
            while (rs.next()) {
                BalPagos pago = new BalPagos();
                pago.IDDetalle = rs.getInt("IDDetalle");
                pago.Referencia_P = rs.getString("Referencia_P");
                pago.IDAlumno = rs.getInt("IDAlumno");
                pago.NombreServicio = rs.getString("NombreServicio");
                pago.Mensualidad = rs.getString("Mensualidad");
                pago.PagoMensualidad = rs.getString("PagoMensualidad");
                pago.FechaVencimiento = rs.getDate("FechaVencimiento");
                pago.Estatus = rs.getString("Estatus");
                pagos.add(pago);
            }
            procedure.close();
            conn.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pagos;
    }

    public void pagarMensualidad(BalPagos pago) {
        Conexion conn = new Conexion();
        Conexion conn2 = new Conexion();
        Conexion conn3 = new Conexion();

        try {
            CallableStatement procedure = conn.Open().prepareCall("{call pagoMensualidad(?,?,?,?,?,?,?)}");
            procedure.setInt(1, pago.getIDDetalle());
            procedure.setString(2, pago.getNota());
            procedure.setDate(3, pago.getFecha());
            procedure.setString(4, pago.getDescuento());
            procedure.setString(5, pago.getPagoMensualidad());
            procedure.setString(6, pago.getEstatus());
            procedure.setString(7, pago.getTipoDePago());
            


            procedure.executeUpdate();
            procedure.close();
            conn.cerrar();
//
            CallableStatement procedure2 = conn2.Open().prepareCall("{call validarPago(?)}");
            procedure2.setString(1, pago.getReferencia_P());
            procedure2.executeUpdate();

            final ResultSet rs = procedure2.getResultSet();
            int cont = 0;
            while (rs.next()) {
                cont++;
            }
            System.out.println(cont);
            procedure2.close();
            conn2.cerrar();
            if (cont > 0) {
                JOptionPane.showMessageDialog(null, "Pago realizado con exito");
            } else {
                CallableStatement procedure3 = conn3.Open().prepareCall("{call finalizarPago(?)}");
                procedure3.setString(1, pago.getReferencia_P());
                procedure3.executeQuery();
                procedure3.close();
                JOptionPane.showMessageDialog(null, "Pago realizado con exito, usted ya no debe nada");
                try {
                    conn3.cerrar();
                } catch (SQLException ex) {
                    Logger.getLogger(BalAlumnos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
