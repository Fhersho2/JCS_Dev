/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import BAL.BalPeriodos;
import BAL.ObtenerFecha;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Terckoer
 */
public class Periodos extends javax.swing.JInternalFrame {

    BalPeriodos control = new BalPeriodos();
    ObtenerFecha fecha = new ObtenerFecha();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form Periodos
     */
    public Periodos() {
        initComponents();
        this.setTitle("Periodos");
        //txtIdPeriodo.setVisible(false);

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
        model.addColumn("IDPeriodo");
        model.addColumn("Año");
        model.addColumn("Periodo");
        model.addColumn("Fecha Inicial");
        model.addColumn("Fecha Final");
        tblPeriodos.getTableHeader().setReorderingAllowed(false);
        ArrayList<BalPeriodos> modelo;
        modelo = control.listarPeriodos();
        try {
            int cantidad = modelo.size();
            model.setNumRows(cantidad);
            int x = 0;
            Iterator<BalPeriodos> itrPeriodos = modelo.iterator();
            while (itrPeriodos.hasNext()) {
                BalPeriodos periodo = itrPeriodos.next();
                model.setValueAt(periodo.IdPeriodo, x, 0);
                model.setValueAt(periodo.Año, x, 1);
                model.setValueAt(periodo.Periodo, x, 2);
                model.setValueAt(periodo.FechaInicio, x, 3);
                model.setValueAt(periodo.FechaFin, x, 4);
                x++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        tblPeriodos.setModel(model);
    }

    public void Actions() {
        if (!(txtAño.getYear() + "").equals("") && !(txtFechaInicio.getDate() + "").equals("") && !(txtFechaFin.getDate() + "").equals("") && !cboPeriodos.getSelectedItem().equals("Seleccionar...")) {
            if (cboAcciones.getSelectedItem().equals("Agregar")) {
                Date dateI = txtFechaInicio.getDate();
                long d = dateI.getTime();
                java.sql.Date fecha = new java.sql.Date(d);
                Date dateF = txtFechaFin.getDate();
                long dF = dateF.getTime();
                java.sql.Date fechaF = new java.sql.Date(dF);
                BalPeriodos periodo = new BalPeriodos();
                periodo.setAño(txtAño.getYear());
                periodo.setPeriodo(cboPeriodos.getSelectedIndex() + "");
                periodo.setFechaInicio(fecha);
                periodo.setFechaFin(fechaF);
                periodo.agregarPeriodo(periodo);
                actualizarDatos();
                //Limpiar();
            } else if (cboAcciones.getSelectedItem().equals("Modificar") && !txtIdPeriodo.getText().equals("")) {
                Date dateI = txtFechaInicio.getDate();
                long d = dateI.getTime();
                java.sql.Date fechaI = new java.sql.Date(d);
                Date dateF = txtFechaFin.getDate();
                long dF = dateF.getTime();
                java.sql.Date fechaF = new java.sql.Date(dF);
                BalPeriodos periodo = new BalPeriodos();
                periodo.setIdPeriodo(Integer.parseInt(txtIdPeriodo.getText()));
                periodo.setAño(Integer.parseInt(txtAño.getYear() + ""));
                periodo.setPeriodo(cboPeriodos.getSelectedIndex() + "");
                periodo.setFechaInicio(fechaI);
                periodo.setFechaFin(fechaF);
                periodo.modificarPeriodo(periodo);
                actualizarDatos();
                //Limpiar();
            } else if (cboAcciones.getSelectedItem().equals("Eliminar") && !txtIdPeriodo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Opcion Eliminar");
                BalPeriodos periodo = new BalPeriodos();
                periodo.eliminarPeriodo(Integer.parseInt(txtIdPeriodo.getText()));
                actualizarDatos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No campos vacios");
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
        txtAño = new com.toedter.calendar.JYearChooser();
        jLabel2 = new javax.swing.JLabel();
        cboPeriodos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtFechaFin = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeriodos = new javax.swing.JTable();
        btnEjecutar = new javax.swing.JButton();
        cboAcciones = new javax.swing.JComboBox<>();
        txtIdPeriodo = new javax.swing.JLabel();

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

        jLabel1.setText("Año:");

        jLabel2.setText("Periodo:");

        cboPeriodos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Enero-Agosto", "Agosto-Diciembre" }));

        jLabel3.setText("Fecha Inicio:");

        txtFechaInicio.setDateFormatString("d-MM-yyyy");

        jLabel4.setText("Fecha Fin:");

        txtFechaFin.setDateFormatString("d MM yyyy");

        tblPeriodos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPeriodos.setRowHeight(30);
        tblPeriodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeriodosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeriodos);

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        cboAcciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agregar", "Modificar", "Eliminar" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboAcciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEjecutar)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cboPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(txtIdPeriodo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdPeriodo)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2))
                    .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEjecutar)
                    .addComponent(cboAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
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

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        // TODO add your handling code here:
        String Fecha = "";
        Fecha = formato.format(fecha.getNTPDate());
        //Imprimimos la fecha en consola. 

        System.out.println(Fecha);
        Actions();
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void tblPeriodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeriodosMouseClicked
        // TODO add your handling code here:
        int fila = tblPeriodos.getSelectedRow();
        if (fila == -1) {
        } else {
            txtIdPeriodo.setText(tblPeriodos.getValueAt(fila, 0).toString());
            txtAño.setYear(Integer.parseInt(tblPeriodos.getValueAt(fila, 1).toString()));
            String tipo = (tblPeriodos.getValueAt(fila, 2).toString());
            //if(("Enero-Agosto").equals(tipo)){
            cboPeriodos.setSelectedItem(tipo);
            try {
                //}
                txtFechaInicio.setDate(formato.parse(tblPeriodos.getValueAt(fila, 3).toString()));
                txtFechaFin.setDate(formato.parse(tblPeriodos.getValueAt(fila, 4).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(Periodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblPeriodosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JComboBox<String> cboAcciones;
    private javax.swing.JComboBox<String> cboPeriodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPeriodos;
    private com.toedter.calendar.JYearChooser txtAño;
    private com.toedter.calendar.JDateChooser txtFechaFin;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    private javax.swing.JLabel txtIdPeriodo;
    // End of variables declaration//GEN-END:variables
}
