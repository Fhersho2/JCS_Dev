/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAL;

import DAL.Conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BalUsuarios {

    Conexion conn = new Conexion();
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
        try {
            CallableStatement procedure = conn.Open().prepareCall("{call Login(?, ?)}");
            procedure.setString(1, usuario.getUsername());
            procedure.setString(2, usuario.getPass());
            procedure.execute();
            final ResultSet rs = procedure.getResultSet();
            if (rs.next()) {
                System.out.println(rs.getString("fullname"));
               
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BalUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
