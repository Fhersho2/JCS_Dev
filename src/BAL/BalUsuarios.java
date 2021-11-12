/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAL;

import DAL.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class BalUsuarios {

    public int adminID; //Variable Global
    public String admintype; //Variable Global
    public String username; //Variable Global
    public String fullname; //Variable Global
    public String pass; //Variable Global

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdmintype() {
        return admintype;
    }

    public void setAdmintype(String admintype) {
        this.admintype = admintype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean validarUsuario(BalUsuarios usuario) {
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call login(?, ?)}");
            procedure.setString(1, usuario.getUsername());
            procedure.setString(2, usuario.getPass());
            procedure.execute();
            final ResultSet rs = procedure.getResultSet();
            if (rs.next()) {
                setAdminID(rs.getInt("IDUsuario"));
                setUsername(rs.getString("Usuario"));
                setFullname(rs.getString("Nombre"));
                setAdmintype(rs.getString("TipoUsuario"));
                try {
                    conn.cerrar();
                } catch (SQLException ex) {
                    Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    public boolean validarUsuario(BalUsuarios usuario) {
//        ResultSet rs = null;
//        try {
//            PreparedStatement ps = conn.Open().prepareStatement("SELECT IDUsuario, Nombre, Usuario, TipoUsuario FROM tbl_usuarios WHERE Usuario = ? AND Password = ?");
//            ps.setString(1, usuario.getUsername());
//            ps.setString(2, usuario.getPass());
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return true;
//            }else{
//                return false;
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            return false;
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//    
    public ArrayList<BalUsuarios> listarUsuarios() {
        ResultSet rs;
        Conexion conn = new Conexion();
        ArrayList<BalUsuarios> usuarios = new ArrayList<>();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call listarUsuarios()}");
            procedure.execute();
            rs = procedure.getResultSet();
            while (rs.next()) {
                BalUsuarios user = new BalUsuarios();
                user.adminID = Integer.parseInt(rs.getString("IDUsuario"));
                user.fullname = rs.getString("Nombre");
                user.username = rs.getString("Usuario");
                user.admintype = rs.getString("TipoUsuario");
                usuarios.add(user);
//                setFullname(rs.getString("fullname"));
//                setAdmintype(rs.getString("admintype"));
            }
            procedure.close();
            conn.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public void agregarUsuario(BalUsuarios usuario) {
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call agregarUsuarios(?, ?, ?, ?)}");
            procedure.setString(1, usuario.getFullname());
            procedure.setString(2, usuario.getUsername());
            procedure.setString(3, usuario.getPass());
            procedure.setString(4, usuario.getAdmintype());
            procedure.executeQuery();
            procedure.close();
            conn.cerrar();
            JOptionPane.showMessageDialog(null, "Usuario agregado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarUsuario(BalUsuarios usuario) {
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call modificarUsuario(?, ?, ?, ?, ?)}");
            procedure.setInt(1, usuario.getAdminID());
            procedure.setString(2, usuario.getFullname());
            procedure.setString(3, usuario.getUsername());
            procedure.setString(4, usuario.getPass());
            procedure.setString(5, usuario.getAdmintype());
            procedure.executeUpdate();
            procedure.close();
            conn.cerrar();
            JOptionPane.showMessageDialog(null, "Usuario Modificado con exito");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarUsuario(int ID) {
        Conexion conn = new Conexion();
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call eliminarUsuario(?)}");
            procedure.setInt(1, ID);
            procedure.execute();
            procedure.close();
            conn.cerrar();
            JOptionPane.showMessageDialog(null, "Usuario Eliminado con exito");
        } catch (SQLException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
