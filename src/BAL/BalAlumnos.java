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
public class BalAlumnos {

    Conexion conn = new Conexion();
    public int NoControl;
    public String Nombre;
    public Date FechaNac;
    public String LugarNac;
    public String Correo;
    public String Semestre;
    public String Grupo;
    public String PeriodoF;
    public String PeriodoE;
    public String PadreTutor;
    public String Madre;
    public String Domicilio;
    public String Telefono;
    public String Emergencias;
    public String Estatus;
    public String Saldo;
    public String Usuario;
    public String Password;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getNoControl() {
        return NoControl;
    }

    public void setNoControl(int NoControl) {
        this.NoControl = NoControl;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Date getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(Date FechaNac) {
        this.FechaNac = FechaNac;
    }

    public String getLugarNac() {
        return LugarNac;
    }

    public void setLugarNac(String LugarNac) {
        this.LugarNac = LugarNac;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String Semestre) {
        this.Semestre = Semestre;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    public String getPeriodoF() {
        return PeriodoF;
    }

    public void setPeriodoF(String PeriodoF) {
        this.PeriodoF = PeriodoF;
    }

    public String getPeriodoE() {
        return PeriodoE;
    }

    public void setPeriodoE(String PeriodoE) {
        this.PeriodoE = PeriodoE;
    }

    public String getPadreTutor() {
        return PadreTutor;
    }

    public void setPadreTutor(String PadreTutor) {
        this.PadreTutor = PadreTutor;
    }

    public String getMadre() {
        return Madre;
    }

    public void setMadre(String Madre) {
        this.Madre = Madre;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmergencias() {
        return Emergencias;
    }

    public void setEmergencias(String Emergencias) {
        this.Emergencias = Emergencias;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    public String getSaldo() {
        return Saldo;
    }

    public void setSaldo(String Saldo) {
        this.Saldo = Saldo;
    }

    public ArrayList<BalAlumnos> listarAlumnos() {
        ResultSet rs;
        ArrayList<BalAlumnos> alumnos = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call listarAlumnos()}");
            procedure.execute();
            rs = procedure.getResultSet();
            while (rs.next()) {
                BalAlumnos alumno = new BalAlumnos();
                alumno.NoControl = rs.getInt("IDAlumno");
                alumno.Nombre = rs.getString("Nombre");
                alumno.Semestre = rs.getString("Semestre");
                alumno.Estatus = rs.getString("Estatus");
                alumnos.add(alumno);
            }
            procedure.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }

    public ArrayList<BalAlumnos> consultarAlumno(int ID) {
        ResultSet rs;
        ArrayList<BalAlumnos> alumnos = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call consultarAlumno(?)}");
            procedure.setInt(1, ID);
            procedure.execute();
            rs = procedure.getResultSet();
            while (rs.next()) {
                BalAlumnos alumno = new BalAlumnos();
                alumno.NoControl = rs.getInt("IDAlumno");
                alumno.Nombre = rs.getString("Nombre");
                alumno.FechaNac = rs.getDate("FechaNac");
                alumno.LugarNac = rs.getString("LugarNac");
                alumno.Correo = rs.getString("Correo");
                alumno.Semestre = rs.getString("Semestre");
                alumno.Grupo = rs.getString("Grupo");
                alumno.PeriodoF = rs.getString("PeriodoF");
                alumno.PeriodoE = rs.getString("PeriodoE");
                alumno.PadreTutor = rs.getString("PadreTutor");
                alumno.Madre = rs.getString("Madre");
                alumno.Domicilio = rs.getString("Domicilio");
                alumno.Telefono = rs.getString("Telefono");
                alumno.Emergencias = rs.getString("Emergencias");
                alumno.Estatus = rs.getString("Estatus");
                alumno.Saldo = rs.getString("Saldo");
                alumnos.add(alumno);
            }
            procedure.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }

    public void agregarAlumnos(BalAlumnos alumno) {
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call agregarAlumno(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            procedure.setInt(1, alumno.getNoControl());
            procedure.setString(2, alumno.getNombre());
            procedure.setDate(3, alumno.getFechaNac());
            procedure.setString(4, alumno.getLugarNac());
            procedure.setString(5, alumno.getCorreo());
            procedure.setString(6, alumno.getSemestre());
            procedure.setString(7, alumno.getGrupo());
            procedure.setString(8, alumno.getPeriodoF());
            procedure.setString(9, alumno.getPeriodoE());
            procedure.setString(10, alumno.getPadreTutor());
            procedure.setString(11, alumno.getMadre());
            procedure.setString(12, alumno.getDomicilio());
            procedure.setString(13, alumno.getTelefono());
            procedure.setString(14, alumno.getEmergencias());
            procedure.setString(15, alumno.getEstatus());
            procedure.setString(16, alumno.getSaldo());
            procedure.executeQuery();
            procedure.close();
            JOptionPane.showMessageDialog(null, "Alumno agregado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarAlumno(BalAlumnos alumno) {
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call modificarAlumno(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            procedure.setInt(1, alumno.getNoControl());
            procedure.setString(2, alumno.getNombre());
            procedure.setDate(3, alumno.getFechaNac());
            procedure.setString(4, alumno.getLugarNac());
            procedure.setString(5, alumno.getCorreo());
            procedure.setString(6, alumno.getSemestre());
            procedure.setString(7, alumno.getGrupo());
            procedure.setString(8, alumno.getPeriodoF());
            procedure.setString(9, alumno.getPeriodoE());
            procedure.setString(10, alumno.getPadreTutor());
            procedure.setString(11, alumno.getMadre());
            procedure.setString(12, alumno.getDomicilio());
            procedure.setString(13, alumno.getTelefono());
            procedure.setString(14, alumno.getEmergencias());
            procedure.setString(15, alumno.getEstatus());
            procedure.executeQuery();
            procedure.close();
            JOptionPane.showMessageDialog(null, "Alumno modificado  con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalPeriodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarAlumno(int ID) {
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call eliminarAlumno(?)}");
            procedure.setInt(1, ID);
            procedure.executeQuery();
            procedure.close();
            JOptionPane.showMessageDialog(null, "Alumno eliminado  con exito");
        } catch (SQLException ex) {
            Logger.getLogger(BalAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void asignarUsuario(int control, String User, String pass) {
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call validarUsuarioAlumno(?,?)}");
            procedure.setInt(1, control);
            procedure.setString(2, User);
            procedure.executeQuery();
            final ResultSet rs = procedure.getResultSet();
            int cont = 0;
            while(rs.next()){
                cont++;
            }
            System.out.println(cont);
            if (cont>0) {
                JOptionPane.showMessageDialog(null, "Este alumno ya cuenta con un usuario o tiene usuario repetido");
            } else {
                CallableStatement procedure1 = conn.Open().prepareCall("{call asignarUsuario(?,?,?)}");
                procedure1.setInt(1, control);
                procedure1.setString(2, User);
                procedure1.setString(3, pass);
                procedure1.executeQuery();
                JOptionPane.showMessageDialog(null, "Usuario asignado con exito");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void asignarSaldo(int control, String saldo){
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call asignarSaldo(?,?)}");
            procedure.setInt(1, control);
            procedure.setString(2, saldo);
            procedure.executeQuery();
            JOptionPane.showMessageDialog(null, "Saldo asignado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
