/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import javax.swing.JOptionPane;
import BAL.BalUsuarios;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Terckoer
 */
public class Usuarios extends javax.swing.JInternalFrame {
    
    BalUsuarios control = new BalUsuarios();

    /**
     * Creates new form Usuarios
     */
    public Usuarios() {
        initComponents();
        this.setTitle("Usuarios");
        
    }
    
    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public void actualizarDatos() {
        model.setNumRows(0);
        model.setColumnCount(0);
        model.addColumn("IdUsuario");
        model.addColumn("Nombre");
        model.addColumn("Usuario");
        model.addColumn("TipoUsuario");
        tblUsers.getTableHeader().setReorderingAllowed(false);
        ArrayList<BalUsuarios> modelo;
        modelo = control.listarUsuarios();
        try {
//            //dato.last();
            int cantidad = modelo.size();
            model.setNumRows(cantidad);
            int x = 0;
            Iterator<BalUsuarios> itrUsuarios = modelo.iterator();
            while (itrUsuarios.hasNext()) {
                BalUsuarios usuario = itrUsuarios.next();
                model.setValueAt(usuario.adminID, x, 0);
                model.setValueAt(usuario.fullname, x, 1);
                model.setValueAt(usuario.username, x, 2);
                model.setValueAt(usuario.admintype, x, 3);
                x++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        tblUsers.setModel(model);
    }
    
    public void Limpiar() {
        IDUsuario.setText("");
        txtNombre.setText("");
        txtUsuario.setText("");
        txtPass.setText("");
        cboTipoUsuario.setSelectedIndex(0);
        cboAcciones.setSelectedIndex(0);
    }
    
    public void Actions() {
        if (!txtNombre.getText().equals("") && !txtPass.getText().equals("") && !txtUsuario.getText().equals("") && !cboTipoUsuario.getSelectedItem().equals("Seleccionar...")) {
            if (cboAcciones.getSelectedItem().equals("Agregar") && IDUsuario.getText().equals("")) {
                BalUsuarios user = new BalUsuarios();
                user.setFullname(txtNombre.getText());
                user.setUsername(txtUsuario.getText());
                user.setPass(txtPass.getText());
                user.setAdmintype((String) cboTipoUsuario.getSelectedItem());
                user.agregarUsuario(user);
                actualizarDatos();
                Limpiar();
            } else if (cboAcciones.getSelectedItem().equals("Modificar") && !IDUsuario.getText().equals("")) {
                BalUsuarios user = new BalUsuarios();
                user.setAdminID(Integer.parseInt(IDUsuario.getText()));
                user.setFullname(txtNombre.getText());
                user.setUsername(txtUsuario.getText());
                user.setPass(txtPass.getText());
                user.setAdmintype((String) cboTipoUsuario.getSelectedItem());
                user.modificarUsuario(user);
                actualizarDatos();
                Limpiar();
            }
        } else if(cboAcciones.getSelectedItem().equals("Eliminar") && !IDUsuario.getText().equals("")){
            BalUsuarios user = new BalUsuarios();
            user.eliminarUsuario(Integer.parseInt(IDUsuario.getText()));
            actualizarDatos();
            Limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "No se permiten campos vacios");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboTipoUsuario = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboAcciones = new javax.swing.JComboBox<>();
        txtPass = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnExecute = new javax.swing.JButton();
        IDUsuario = new javax.swing.JTextField();
        btnClean = new javax.swing.JButton();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(42, 157, 143));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre de Usuario:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario: ");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tipo de Usuario:");

        cboTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Director", "Administrador", "Contador", "Cajero" }));
        cboTipoUsuario.setRequestFocusEnabled(false);
        cboTipoUsuario.setVerifyInputWhenFocusTarget(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Acciones:");

        cboAcciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agregar", "Modificar", "Eliminar" }));
        cboAcciones.setRequestFocusEnabled(false);

        tblUsers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblUsers.setRowHeight(25);
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsers);

        btnExecute.setBackground(new java.awt.Color(0, 153, 255));
        btnExecute.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnExecute.setForeground(new java.awt.Color(255, 255, 255));
        btnExecute.setText("Ejecutar");
        btnExecute.setBorderPainted(false);
        btnExecute.setContentAreaFilled(false);
        btnExecute.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExecute.setOpaque(true);
        btnExecute.setRequestFocusEnabled(false);
        btnExecute.setRolloverEnabled(false);
        btnExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecuteActionPerformed(evt);
            }
        });

        IDUsuario.setEnabled(false);

        btnClean.setBackground(new java.awt.Color(232, 59, 45));
        btnClean.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnClean.setForeground(new java.awt.Color(255, 255, 255));
        btnClean.setText("Limpiar");
        btnClean.setBorderPainted(false);
        btnClean.setContentAreaFilled(false);
        btnClean.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClean.setOpaque(true);
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnExecute, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IDUsuario)
                            .addComponent(cboTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre)
                            .addComponent(txtUsuario)
                            .addComponent(cboAcciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                        .addGap(119, 119, 119)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnClean, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(IDUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExecute, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClean, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        actualizarDatos();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecuteActionPerformed
        // TODO add your handling code here:
        Actions();
    }//GEN-LAST:event_btnExecuteActionPerformed

    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        // TODO add your handling code here:
        String tipo = null;
        int fila = tblUsers.getSelectedRow();
        if (fila == -1) {
        } else {
            IDUsuario.setText(tblUsers.getValueAt(fila, 0).toString());
            txtNombre.setText(tblUsers.getValueAt(fila, 1).toString());
            txtUsuario.setText(tblUsers.getValueAt(fila, 2).toString());
            txtPass.setText("");
            tipo = (tblUsers.getValueAt(fila, 3).toString());
        }
        if ("Director".equals(tipo)) {
            cboTipoUsuario.setSelectedIndex(1);
        }
        if ("Administrador".equals(tipo)) {
            cboTipoUsuario.setSelectedIndex(2);
        }
        if ("Contador".equals(tipo)) {
            cboTipoUsuario.setSelectedIndex(3);
        }
        if ("Cajero".equals(tipo)) {
            cboTipoUsuario.setSelectedIndex(4);
        }
    }//GEN-LAST:event_tblUsersMouseClicked

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_btnCleanActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        int size = txtNombre.getText().length();
        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
         if (!( mayusculas || minusculas) || size>=55)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
        if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDUsuario;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnExecute;
    private javax.swing.JComboBox<String> cboAcciones;
    private javax.swing.JComboBox<String> cboTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
