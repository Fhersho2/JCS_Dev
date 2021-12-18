package Views;

import BAL.BalPeriodos;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JInternalFrame;
import BAL.BalUsuarios;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author natsu
 */
public class Home extends javax.swing.JFrame {

    Alumnos ViewAlumnos = new Alumnos();
    Reportes viewReportes = new Reportes();
    Servicios viewServicios = new Servicios();
    Periodos viewPeriodos = new Periodos();
    Pagos viewPagos = new Pagos();
    Usuarios viewUsuarios = new Usuarios();
    Mensualidades viewMensualidades = new Mensualidades();

    //Servicios ViewServices = new Servicios();
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        this.setTitle("Sistema Contable Preparatoria JCS");
        setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        
    }

    public Home(BalUsuarios usuario) {
        initComponents();
        this.setTitle("Sistema Contable Preparatoria JCS");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/icon.png")));
        setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        lblAdmin.setText(usuario.username);
        lblFullName.setText(usuario.fullname);
        lblTipoUsuario.setText(usuario.admintype);
        lblidAdmin.setText(usuario.adminID + "");
        periodoActual();
    }
    
    public void periodoActual(){
        //ObtenerFecha fecha = new ObtenerFecha();
        java.sql.Date fechaN = new java.sql.Date(new Date().getTime());
        System.out.println(fechaN);
        BalPeriodos control = new BalPeriodos();
        ArrayList<BalPeriodos> modelo;
        modelo = control.consultarPeriodo(fechaN);
        try {
            int x = 0;
            Iterator<BalPeriodos> itrPeriodos = modelo.iterator();
            while (itrPeriodos.hasNext()) {
                BalPeriodos periodo = itrPeriodos.next();
                lblPeriodo.setText(periodo.Periodo);
                lblidPeriodo.setText((periodo.IdPeriodo)+ "");
                x++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        InformationUser = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblAdmin = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        lblTipoUsuario = new javax.swing.JLabel();
        LogOut = new javax.swing.JButton();
        PaneNavBar = new javax.swing.JPanel();
        btn_Alumnos = new javax.swing.JButton();
        btn_Usuarios = new javax.swing.JButton();
        btn_Servicios = new javax.swing.JButton();
        btn_pagos = new javax.swing.JButton();
        btn_Mensualidades = new javax.swing.JButton();
        btn_reportes = new javax.swing.JButton();
        btn_periodos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        lblidAdmin = new javax.swing.JLabel();
        lblidPeriodo = new javax.swing.JLabel();
        escritorio = new javax.swing.JDesktopPane();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        InformationUser.setBackground(new java.awt.Color(38, 70, 83));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo:");

        lblAdmin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAdmin.setForeground(new java.awt.Color(255, 255, 255));

        lblFullName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFullName.setForeground(new java.awt.Color(255, 255, 255));

        lblTipoUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTipoUsuario.setForeground(new java.awt.Color(255, 255, 255));

        LogOut.setBackground(new java.awt.Color(42, 157, 143));
        LogOut.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        LogOut.setForeground(new java.awt.Color(255, 255, 255));
        LogOut.setText("Cerrar Sesion");
        LogOut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        LogOut.setBorderPainted(false);
        LogOut.setFocusPainted(false);
        LogOut.setRequestFocusEnabled(false);
        LogOut.setRolloverEnabled(false);
        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutActionPerformed(evt);
            }
        });

        PaneNavBar.setBackground(new java.awt.Color(255, 255, 255));

        btn_Alumnos.setBackground(new java.awt.Color(255, 255, 255));
        btn_Alumnos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Alumnos.setForeground(new java.awt.Color(38, 70, 83));
        btn_Alumnos.setText("Alumnos");
        btn_Alumnos.setBorderPainted(false);
        btn_Alumnos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Alumnos.setFocusPainted(false);
        btn_Alumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_AlumnosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_AlumnosMouseExited(evt);
            }
        });
        btn_Alumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AlumnosActionPerformed(evt);
            }
        });

        btn_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        btn_Usuarios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Usuarios.setForeground(new java.awt.Color(38, 70, 83));
        btn_Usuarios.setText("Usuarios");
        btn_Usuarios.setBorderPainted(false);
        btn_Usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Usuarios.setFocusPainted(false);
        btn_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_UsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_UsuariosMouseExited(evt);
            }
        });
        btn_Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UsuariosActionPerformed(evt);
            }
        });

        btn_Servicios.setBackground(new java.awt.Color(255, 255, 255));
        btn_Servicios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Servicios.setForeground(new java.awt.Color(38, 70, 83));
        btn_Servicios.setText("Servicios");
        btn_Servicios.setBorderPainted(false);
        btn_Servicios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Servicios.setFocusPainted(false);
        btn_Servicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ServiciosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ServiciosMouseExited(evt);
            }
        });
        btn_Servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ServiciosActionPerformed(evt);
            }
        });

        btn_pagos.setBackground(new java.awt.Color(255, 255, 255));
        btn_pagos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_pagos.setForeground(new java.awt.Color(38, 70, 83));
        btn_pagos.setText("Pagos");
        btn_pagos.setBorderPainted(false);
        btn_pagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pagos.setFocusPainted(false);
        btn_pagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_pagosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_pagosMouseExited(evt);
            }
        });
        btn_pagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagosActionPerformed(evt);
            }
        });

        btn_Mensualidades.setBackground(new java.awt.Color(255, 255, 255));
        btn_Mensualidades.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Mensualidades.setForeground(new java.awt.Color(38, 70, 83));
        btn_Mensualidades.setText("Mensualidades");
        btn_Mensualidades.setBorderPainted(false);
        btn_Mensualidades.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Mensualidades.setFocusPainted(false);
        btn_Mensualidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_MensualidadesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_MensualidadesMouseExited(evt);
            }
        });
        btn_Mensualidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MensualidadesActionPerformed(evt);
            }
        });

        btn_reportes.setBackground(new java.awt.Color(255, 255, 255));
        btn_reportes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_reportes.setForeground(new java.awt.Color(38, 70, 83));
        btn_reportes.setText("Reportes");
        btn_reportes.setBorderPainted(false);
        btn_reportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reportes.setFocusPainted(false);
        btn_reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reportesMouseExited(evt);
            }
        });
        btn_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reportesActionPerformed(evt);
            }
        });

        btn_periodos.setBackground(new java.awt.Color(255, 255, 255));
        btn_periodos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_periodos.setForeground(new java.awt.Color(38, 70, 83));
        btn_periodos.setText("Periodos");
        btn_periodos.setBorderPainted(false);
        btn_periodos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_periodos.setFocusPainted(false);
        btn_periodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_periodosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_periodosMouseExited(evt);
            }
        });
        btn_periodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_periodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PaneNavBarLayout = new javax.swing.GroupLayout(PaneNavBar);
        PaneNavBar.setLayout(PaneNavBarLayout);
        PaneNavBarLayout.setHorizontalGroup(
            PaneNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneNavBarLayout.createSequentialGroup()
                .addComponent(btn_Alumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_Usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_Servicios, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_pagos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_Mensualidades, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_periodos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btn_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PaneNavBarLayout.setVerticalGroup(
            PaneNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_Alumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_Usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_Servicios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_pagos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_Mensualidades, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_reportes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btn_periodos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Periodo:");

        lblPeriodo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPeriodo.setForeground(new java.awt.Color(255, 255, 255));

        lblidAdmin.setText("jLabel5");

        lblidPeriodo.setText("jLabel6");

        javax.swing.GroupLayout InformationUserLayout = new javax.swing.GroupLayout(InformationUser);
        InformationUser.setLayout(InformationUserLayout);
        InformationUserLayout.setHorizontalGroup(
            InformationUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformationUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblidAdmin)
                .addGap(18, 18, 18)
                .addComponent(lblidPeriodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LogOut)
                .addGap(12, 12, 12))
            .addComponent(PaneNavBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        InformationUserLayout.setVerticalGroup(
            InformationUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InformationUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InformationUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(InformationUserLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(InformationUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTipoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(InformationUserLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(InformationUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblidAdmin)
                            .addComponent(lblidPeriodo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaneNavBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        escritorio.setBackground(new java.awt.Color(42, 157, 143));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 913, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InformationUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(InformationUser, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(escritorio))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_periodosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_periodosMouseEntered
        // TODO add your handling code here:
        btn_periodos.setBackground(new java.awt.Color(38, 70, 83));
        btn_periodos.setForeground(new java.awt.Color(255, 255, 255));

    }//GEN-LAST:event_btn_periodosMouseEntered

    private void btn_periodosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_periodosMouseExited
        // TODO add your handling code here:
        btn_periodos.setBackground(new java.awt.Color(255, 255, 255));
        btn_periodos.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_periodosMouseExited

    private void btn_AlumnosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AlumnosMouseEntered
        // TODO add your handling code here:
        btn_Alumnos.setBackground(new java.awt.Color(38, 70, 83));
        btn_Alumnos.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_AlumnosMouseEntered

    private void btn_AlumnosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AlumnosMouseExited
        // TODO add your handling code here:
        btn_Alumnos.setBackground(new java.awt.Color(255, 255, 255));
        btn_Alumnos.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_AlumnosMouseExited

    private void btn_periodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_periodosActionPerformed
        // TODO add your handling code here:
        if (!lblTipoUsuario.getText().equals("Cajero")) {
            if (!viewPeriodos.isShowing()) {
                escritorio.add(viewPeriodos,0);
                viewPeriodos.show();
                escritorio.remove(ViewAlumnos);
                escritorio.remove(viewUsuarios);
                escritorio.remove(viewServicios);
                escritorio.remove(viewReportes);
                escritorio.remove(viewPagos);
                escritorio.remove(viewMensualidades);
                escritorio.repaint();
                try {
                    viewPeriodos.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "No tienes Acceso a esta vista");
        }
//        if (!viewPeriodos.isShowing()) {
//            escritorio.add(viewPeriodos);
//            viewPeriodos.show();
//            escritorio.remove(ViewAlumnos);
//            escritorio.remove(viewUsuarios);
//            escritorio.remove(viewServicios);
//            escritorio.remove(viewReportes);
//            escritorio.remove(viewPagos);
//            escritorio.remove(viewMensualidades);
//            escritorio.repaint();
//            try {
//                viewPeriodos.setMaximum(true);
//            } catch (PropertyVetoException ex) {
//                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_btn_periodosActionPerformed

    private void btn_UsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UsuariosMouseEntered
        // TODO add your handling code here:
        btn_Usuarios.setBackground(new java.awt.Color(38, 70, 83));
        btn_Usuarios.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_UsuariosMouseEntered

    private void btn_UsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UsuariosMouseExited
        // TODO add your handling code here:
        btn_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        btn_Usuarios.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_UsuariosMouseExited

    private void btn_UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UsuariosActionPerformed
        if (!lblTipoUsuario.getText().equals("Cajero")) {
            if (!viewUsuarios.isShowing()) {
                escritorio.add(viewUsuarios);
                viewUsuarios.show();
                escritorio.remove(ViewAlumnos);
                escritorio.remove(viewPeriodos);
                escritorio.remove(viewServicios);
                escritorio.remove(viewReportes);
                escritorio.remove(viewPagos);
                escritorio.remove(viewMensualidades);
                escritorio.repaint();
                try {
                    viewUsuarios.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tienes Acceso a esta vista");
        }
//        if (!viewUsuarios.isShowing()) {
//            escritorio.add(viewUsuarios);
//            viewUsuarios.show();
//            escritorio.remove(ViewAlumnos);
//            escritorio.remove(viewPeriodos);
//            escritorio.remove(viewServicios);
//            escritorio.remove(viewReportes);
//            escritorio.remove(viewPagos);
//            escritorio.remove(viewMensualidades);
//            escritorio.repaint();
//            try {
//                viewUsuarios.setMaximum(true);
//            } catch (PropertyVetoException ex) {
//                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_btn_UsuariosActionPerformed

    private void btn_ServiciosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ServiciosMouseEntered
        // TODO add your handling code here:
        btn_Servicios.setBackground(new java.awt.Color(38, 70, 83));
        btn_Servicios.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_ServiciosMouseEntered

    private void btn_ServiciosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ServiciosMouseExited
        // TODO add your handling code here:
        btn_Servicios.setBackground(new java.awt.Color(255, 255, 255));
        btn_Servicios.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_ServiciosMouseExited

    private void btn_ServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ServiciosActionPerformed
        if (!lblTipoUsuario.getText().equals("Cajero")) {
            if (!viewServicios.isShowing()) {
                escritorio.add(viewServicios);
                viewServicios.show();
                escritorio.remove(ViewAlumnos);
                escritorio.remove(viewUsuarios);
                escritorio.remove(viewPeriodos);
                escritorio.remove(viewReportes);
                escritorio.remove(viewPagos);
                escritorio.remove(viewMensualidades);
                escritorio.repaint();
                try {
                    viewServicios.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tienes Acceso a esta vista");
        }
//        if (!viewServicios.isShowing()) {
//            escritorio.add(viewServicios);
//            viewServicios.show();
//            escritorio.remove(ViewAlumnos);
//            escritorio.remove(viewUsuarios);
//            escritorio.remove(viewPeriodos);
//            escritorio.remove(viewReportes);
//            escritorio.remove(viewPagos);
//            escritorio.remove(viewMensualidades);
//            escritorio.repaint();
//            try {
//                viewServicios.setMaximum(true);
//            } catch (PropertyVetoException ex) {
//                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }//GEN-LAST:event_btn_ServiciosActionPerformed

    private void btn_pagosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pagosMouseEntered
        // TODO add your handling code here:
        btn_pagos.setBackground(new java.awt.Color(38, 70, 83));
        btn_pagos.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_pagosMouseEntered

    private void btn_pagosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pagosMouseExited
        // TODO add your handling code here:
        btn_pagos.setBackground(new java.awt.Color(255, 255, 255));
        btn_pagos.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_pagosMouseExited

    private void btn_pagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagosActionPerformed
        // TODO add your handling code here:
        viewPagos.setAdmin(lblidAdmin.getText());
        viewPagos.setPeriodo(lblidPeriodo.getText());
        if (!viewPagos.isShowing()) {
            escritorio.add(viewPagos);
            viewPagos.show();
            escritorio.remove(ViewAlumnos);
            escritorio.remove(viewUsuarios);
            escritorio.remove(viewServicios);
            escritorio.remove(viewReportes);
            escritorio.remove(viewPeriodos);
            escritorio.remove(viewMensualidades);
            escritorio.repaint();
            try {
                viewPagos.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_pagosActionPerformed

    private void btn_MensualidadesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_MensualidadesMouseEntered
        // TODO add your handling code here:
        btn_Mensualidades.setBackground(new java.awt.Color(38, 70, 83));
        btn_Mensualidades.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_MensualidadesMouseEntered

    private void btn_MensualidadesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_MensualidadesMouseExited
        // TODO add your handling code here:
        btn_Mensualidades.setBackground(new java.awt.Color(255, 255, 255));
        btn_Mensualidades.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_MensualidadesMouseExited

    private void btn_MensualidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MensualidadesActionPerformed
        // TODO add your handling code here:
        if (!viewMensualidades.isShowing()) {
            escritorio.add(viewMensualidades);
            viewMensualidades.show();
            escritorio.remove(ViewAlumnos);
            escritorio.remove(viewUsuarios);
            escritorio.remove(viewServicios);
            escritorio.remove(viewPeriodos);
            escritorio.remove(viewReportes);
            escritorio.remove(viewPagos);
            escritorio.repaint();
            try {
                viewMensualidades.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_MensualidadesActionPerformed

    private void btn_AlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AlumnosActionPerformed
        // TODO add your handling code here:
        if (!lblTipoUsuario.getText().equals("Cajero")) {
            if (!ViewAlumnos.isShowing()) {
                escritorio.add(ViewAlumnos);
                ViewAlumnos.show();
                escritorio.remove(viewPeriodos);
                escritorio.remove(viewUsuarios);
                escritorio.remove(viewServicios);
                escritorio.remove(viewReportes);
                escritorio.remove(viewPagos);
                escritorio.remove(viewMensualidades);
                try {
                    ViewAlumnos.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
//            Escritorio.remove(servicio);
//            Escritorio.remove(cita);
//            Escritorio.remove(gestion);
                escritorio.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tienes Acceso a esta vista");
        }
//        if (!ViewAlumnos.isShowing()) {
//            escritorio.add(ViewAlumnos);
//            ViewAlumnos.show();
//            escritorio.remove(viewPeriodos);
//            escritorio.remove(viewUsuarios);
//            escritorio.remove(viewServicios);
//            escritorio.remove(viewReportes);
//            escritorio.remove(viewPagos);
//            escritorio.remove(viewMensualidades);
//            try {
//                ViewAlumnos.setMaximum(true);
//            } catch (PropertyVetoException ex) {
//                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//            }
////            Escritorio.remove(servicio);
////            Escritorio.remove(cita);
////            Escritorio.remove(gestion);
//            escritorio.repaint();
//        }                      
    }//GEN-LAST:event_btn_AlumnosActionPerformed

    private void btn_reportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reportesMouseEntered
        // TODO add your handling code here:
        btn_reportes.setBackground(new java.awt.Color(38, 70, 83));
        btn_reportes.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btn_reportesMouseEntered

    private void btn_reportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reportesMouseExited
        // TODO add your handling code here:
        btn_reportes.setBackground(new java.awt.Color(255, 255, 255));
        btn_reportes.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_btn_reportesMouseExited

    private void btn_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportesActionPerformed
        // TODO add your handling code here:
        if (!viewReportes.isShowing()) {
            escritorio.add(viewReportes);
            viewReportes.show();
            escritorio.remove(ViewAlumnos);
            escritorio.remove(viewUsuarios);
            escritorio.remove(viewServicios);
            escritorio.remove(viewPeriodos);
            escritorio.remove(viewPagos);
            escritorio.remove(viewMensualidades);
            escritorio.repaint();
            try {
                viewReportes.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_reportesActionPerformed

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Login vista = new Login();
        vista.setVisible(true);
    }//GEN-LAST:event_LogOutActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel InformationUser;
    private javax.swing.JButton LogOut;
    private javax.swing.JPanel PaneNavBar;
    private javax.swing.JButton btn_Alumnos;
    private javax.swing.JButton btn_Mensualidades;
    private javax.swing.JButton btn_Servicios;
    private javax.swing.JButton btn_Usuarios;
    private javax.swing.JButton btn_pagos;
    private javax.swing.JButton btn_periodos;
    private javax.swing.JButton btn_reportes;
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblTipoUsuario;
    public javax.swing.JLabel lblidAdmin;
    public javax.swing.JLabel lblidPeriodo;
    // End of variables declaration//GEN-END:variables
}
