/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import BAL.BalPagos;
import BAL.BalReportes;
import DAL.Conexion;
import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Terckoer
 */
public class Reportes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Reportes
     */
    
    ArrayList<BalReportes> modelo ;
    
    public Reportes() {
        initComponents();
        this.setTitle("Reportes");
        dcFechaInicio.setEnabled(false);
        dcFechaInicio.getCalendarButton().setEnabled(true);
        
        dcFechaFin.setEnabled(false);
        dcFechaFin.getCalendarButton().setEnabled(true);
        dcFechaInicio.setDate(new Date());
        dcFechaFin.setDate(new Date());
    }

    
    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public void reporte( java.sql.Date fecha, java.sql.Date fechaFin){
        BalReportes reporte = new BalReportes();
        modelo = reporte.reportes(fecha, fechaFin);
        
        model.setNumRows(0);
        model.setColumnCount(0);
        model.addColumn("ID Alumno");
        model.addColumn("Nombre Alumno");
        model.addColumn("Referencia de pago");
        model.addColumn("Tipo de pago");
        model.addColumn("Servicio");
        model.addColumn("Descuento");
        model.addColumn("Pago total");
        model.addColumn("Fecha");
        model.addColumn("Nombre de usuario");
        model.addColumn("Estatus");

        

        tblReportes.getTableHeader().setReorderingAllowed(false);
       
        try {
            int cantidad = modelo.size();
            model.setNumRows(cantidad);
            int count =0;
            for(int i=0;i<modelo.size();i++){
                
                    model.setValueAt(modelo.get(i).pago.IDAlumno, count, 0);
                    model.setValueAt(modelo.get(i).alumno.Nombre, count, 1);
                    model.setValueAt(modelo.get(i).pago.Referencia_P, count, 2);
                    model.setValueAt(modelo.get(i).pago.TipoPago, count, 3);
                    model.setValueAt(modelo.get(i).pago.NombreServicio, count, 4);
                    model.setValueAt(modelo.get(i).pago.Descuento, count, 5);
                    model.setValueAt(modelo.get(i).pago.PagoTotal, count, 6);
                    model.setValueAt(modelo.get(i).pago.Fecha, count, 7);
                    model.setValueAt(modelo.get(i).usuario.fullname, count, 8);
                    model.setValueAt(modelo.get(i).pago.Estatus, count, 9);


                    count++;
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        tblReportes.setModel(model);
        
        
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReportes = new javax.swing.JTable();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcFechaFin = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(42, 157, 143));

        tblReportes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblReportes);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REPORTES DE PAGOS");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha inicial");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha final");

        btnBuscar.setBackground(new java.awt.Color(0, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(0, 204, 51));
        btnBuscar1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setText("Reporte diario");
        btnBuscar1.setBorderPainted(false);
        btnBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(btnBuscar1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dcFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dcFechaFin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Date dateI =dcFechaInicio.getDate();
        long d = dateI.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        
        Date dateIFin =dcFechaFin.getDate();
        long dFin = dateIFin.getTime();
        java.sql.Date fechaFin = new java.sql.Date(dFin);
        System.out.println(fecha + " ----> "+fechaFin);
        reporte(fecha, fechaFin);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
//        

          
//            try {
//                Conexion con = new Conexion();
//                String path = "src/Reportes/report1.jasper";
//                
//                JasperReport masterReport = (JasperReport) JRLoader.loadObjectFromFile(path);
//
//                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, con.Open());
//                JasperViewer jviewer = new JasperViewer(jasperPrint, false);
//                jviewer.setTitle("Ejemplo de reporte");
//                jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//                jviewer.setVisible(true);
//            } catch (Exception j) {
//                System.out.println("Mensaje de Error:" + j.getMessage());
//            }
//         
            
            
            try {
            Conexion con = new Conexion();
            Date fecha = dcFechaInicio.getDate();
            long date = fecha.getTime();
            java.sql.Date fechaI = new java.sql.Date(date);

            Date fecha2 = dcFechaFin.getDate();
            long date2 = fecha2.getTime();
            java.sql.Date fechaF = new java.sql.Date(date2);

            Map parametros = new HashMap();
            parametros.put("fechaInicio", fechaI);
            parametros.put("fechaFin", fechaF);

            //String path = "Reportes/report1.jasper";
            JasperReport masterReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/report1.jasper"));

            //JasperReport masterReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametros, con.Open());
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            
            //TESTING
//            String pdfPath = "C:\\Users\\Terckoer\\Desktop\\jasper\\test.pdf";
//            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
//            
            //TESTING
            
            jviewer.setTitle("Ejemplo de reporte");
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.getMessage());
        }
 
    }//GEN-LAST:event_btnBuscar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private com.toedter.calendar.JDateChooser dcFechaFin;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReportes;
    // End of variables declaration//GEN-END:variables
}
