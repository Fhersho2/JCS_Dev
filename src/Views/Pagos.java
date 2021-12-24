/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import BAL.BalAlumnos;
import BAL.BalPagos;
import BAL.BalServicios;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Terckoer
 */
public class Pagos extends javax.swing.JInternalFrame {

    /** 
     * Creates new form Pagos
     */
    String admin;
    String periodo;
    String TipoAdmin;
    ArrayList<BalServicios> modelo;
    ArrayList<BalAlumnos> modeloAlumnos;

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
    
     public void setTipoAdmin(String TipoAdmin) {
        this.TipoAdmin = TipoAdmin;
    }

    public Pagos() {
        initComponents();
        this.setTitle("Pagos");
        txtReferencia.setEditable(false);
        cboMeses.setEnabled(false);
        //txtSaldo.setEditable(false);
        txtFecha.setDate(new Date());
        txtFecha.setEnabled(false);
        txtPrecio.setEditable(false);
        txtConDescuento.setEditable(false);
        txtDescuento.setText("0");
        txtDigitos.setEnabled(false);
        txtBanco.setEnabled(false);
        tblAlumnos.setAutoCreateRowSorter(true);
    }
    
    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }
        
    };
    






//ordeno la tabla tomando en cuenta la columna cero
    
    
    
    public void cargarServicios() {
        BalServicios control = new BalServicios();
        modelo = control.listarServicios();
        cboServicios.removeAllItems();
        try {
            Iterator<BalServicios> itrUsuarios = modelo.iterator();
            while (itrUsuarios.hasNext()) {
                BalServicios servicio = itrUsuarios.next();
                cboServicios.addItem(servicio.NombreServicio);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cargarAlumnos(){
        BalAlumnos alumnos = new BalAlumnos();
           
        model.setNumRows(0);
        model.setColumnCount(0);
        model.addColumn("NoControl");
        model.addColumn("Nombre");
        model.addColumn("Semestre");
        model.addColumn("Estatus");
        tblAlumnos.getTableHeader().setReorderingAllowed(false);
        ArrayList<BalAlumnos> modelo1;
        modelo1 = alumnos.listarAlumnos();
        try {
            int cantidad = modelo1.size();
            model.setNumRows(cantidad);
            int x = 0;
            Iterator<BalAlumnos> itrAlumnos = modelo1.iterator();
            while (itrAlumnos.hasNext()) {
                BalAlumnos alumno = itrAlumnos.next();
                model.setValueAt(alumno.NoControl, x, 0);
                model.setValueAt(alumno.Nombre, x, 1);
                model.setValueAt(alumno.Semestre, x, 2);
                model.setValueAt(alumno.Estatus, x, 3);
                x++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        tblAlumnos.setModel(model);
    }
    

    public void generarCodigo() {
        BalPagos control = new BalPagos();
        txtReferencia.setText(control.generarCodigo());
    }

    public void pagoDetalleContado() {
        BalPagos control = new BalPagos();
        control.setReferencia_P(txtReferencia.getText());
        for (int x = 0; x < modelo.size(); x++) {
            if(cboServicios.getSelectedItem().toString().equals(modelo.get(x).NombreServicio)){
                control.setReferencia_S(modelo.get(x).IDServicio + "");
            }
        }
        control.setNota(txtNota.getText());
        control.setNombreServicio(cboServicios.getSelectedItem().toString());
        control.setMensualidad("No Aplica");
        Date dateI = new Date();
        long d = dateI.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        control.setFechaVencimiento(fecha);
        control.setPagoMensualidad("No aplica");
        control.setDescuento("0");
        control.setPagoTotal(txtPrecio.getText());
        control.setEstatus("Pagado");
        if(chkTipoDePago.isSelected()){
            control.setTipoDePago("Pago con tarjeta");
        }else{
            control.setTipoDePago("Pago en efectivo");
        }
        control.setBanco(txtBanco.getText());
        control.setDigitos(txtDigitos.getText());

        control.agregarPagoContadoDetalle(control);
    }

    public void pagoContado() {
        BalPagos control = new BalPagos();
        control.setReferencia_P(txtReferencia.getText());
        control.setIDUsuario(Integer.parseInt(admin));
        control.setIDAlumno(Integer.parseInt(txtNoControl.getText()));
        Date dateI = txtFecha.getDate();
        long d = dateI.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        control.setFecha(fecha);
        control.setIDPeriodo(Integer.parseInt(periodo));
        control.setCosto(txtPrecio.getText());
        control.setTipoPago("Contado");
        
        control.setEstatus("Pagado");
        control.agregarPagoContado(control);
        pagoDetalleContado();
    }

    
    
    public void pagoDetalleContadoDescuento() {
        BalPagos control = new BalPagos();
        control.setReferencia_P(txtReferencia.getText());
        for (int x = 0; x < modelo.size(); x++) {
            if(cboServicios.getSelectedItem().toString().equals(modelo.get(x).NombreServicio)){
                control.setReferencia_S(modelo.get(x).IDServicio + "");
            }
        }
        control.setNota(txtNota.getText());
        control.setNombreServicio(cboServicios.getSelectedItem().toString());
        control.setMensualidad("No Aplica");
        Date dateI = new Date();
        long d = dateI.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        control.setFechaVencimiento(fecha);
        control.setPagoMensualidad("No aplica");
        control.setDescuento(txtDescuento.getText());
        int descuento = Integer.parseInt(txtPrecio.getText())*Integer.parseInt(txtDescuento.getText())/100;
        control.setPagoTotal((Integer.parseInt(txtPrecio.getText())-descuento)+"");
        control.setEstatus("Pagado");
        if(chkTipoDePago.isSelected()){
            control.setTipoDePago("Pago con tarjeta");
        }else{
            control.setTipoDePago("Pago en efectivo");
        }
        control.setBanco(txtBanco.getText());
        control.setDigitos(txtDigitos.getText());

        System.out.println(Integer.parseInt(txtPrecio.getText())-descuento);
        control.agregarPagoContadoDetalle(control);
    }
    
    public void pagoContadoConDescuento() {
        BalPagos control = new BalPagos();
        control.setReferencia_P(txtReferencia.getText());
        control.setIDUsuario(Integer.parseInt(admin));
        control.setIDAlumno(Integer.parseInt(txtNoControl.getText()));
        Date dateI = txtFecha.getDate();
        long d = dateI.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        control.setFecha(fecha);
        control.setIDPeriodo(Integer.parseInt(periodo));
        control.setCosto(txtPrecio.getText());
        control.setTipoPago("Contado con descuento");
        control.setEstatus("Pagado");
        control.setDigitos(""+txtDigitos.getText());
        control.setBanco(""+txtBanco.getText());

        control.agregarPagoContado(control);
        pagoDetalleContadoDescuento();
    }
//    
//     public void pagoContadoConSaldo() {
//        BalPagos control = new BalPagos();
//        control.setReferencia_P(txtReferencia.getText());
//        control.setIDUsuario(Integer.parseInt(admin));
//        control.setIDAlumno(Integer.parseInt(txtNoControl.getText()));
//        Date dateI = txtFecha.getDate();
//        long d = dateI.getTime();
//        java.sql.Date fecha = new java.sql.Date(d);
//        control.setFecha(fecha);
//        control.setIDPeriodo(Integer.parseInt(periodo));
//        control.setCosto(txtPrecio.getText());
//        control.setTipoPago("Contado con saldo");
//        control.setEstatus("Pagado");
//        control.agregarPagoContado(control);
//        pagoDetalleContadoSaldo();
//    }
//     
//     public void pagoDetalleContadoSaldo() {
//        BalPagos control = new BalPagos();
//        BalAlumnos alumnos = new BalAlumnos();
//        control.setReferencia_P(txtReferencia.getText());
//        for (int x = 0; x < modelo.size(); x++) {
//            if(cboServicios.getSelectedItem().toString().equals(modelo.get(x).NombreServicio)){
//                control.setReferencia_S(modelo.get(x).IDServicio + "");
//            }
//        }
//        control.setNota(txtNota.getText());
//        control.setNombreServicio(cboServicios.getSelectedItem().toString());
//        control.setMensualidad("No Aplica");
//        Date dateI = new Date();
//        long d = dateI.getTime();
//        java.sql.Date fecha = new java.sql.Date(d);
//        control.setFechaVencimiento(fecha);
//        control.setPagoMensualidad("No aplica");
//        control.setDescuento(""+0);
//        int saldo = Integer.parseInt(txtSaldo.getText());
//        int precioServicio = Integer.parseInt(txtPrecio.getText());
////        if(precioServicio < saldo){
////            alumnos.asignarSaldo(Integer.parseInt(txtNoControl.getText()), (-1*precioServicio)+"");
////            control.setPagoTotal((precioServicio)+"");
////
////        }else{
////            alumnos.asignarSaldo(Integer.parseInt(txtNoControl.getText()), (-1*saldo)+"");
////            control.setPagoTotal((precioServicio-saldo)+"");
////        }
//        control.setEstatus("Pagado");
//        control.agregarPagoContadoDetalle(control);
//    }
     
//     
//     public void pagoContadoConSaldoAndDescuento() {
//        BalPagos control = new BalPagos();
//        control.setReferencia_P(txtReferencia.getText());
//        control.setIDUsuario(Integer.parseInt(admin));
//        control.setIDAlumno(Integer.parseInt(txtNoControl.getText()));
//        Date dateI = txtFecha.getDate();
//        long d = dateI.getTime();
//        java.sql.Date fecha = new java.sql.Date(d);
//        control.setFecha(fecha);
//        control.setIDPeriodo(Integer.parseInt(periodo));
//        control.setCosto(txtPrecio.getText());
//        control.setTipoPago("Contado con saldo y descuento");
//        control.setEstatus("Pagado");
//        control.agregarPagoContado(control);
//        pagoDetalleContadoSaldoAndDescuento();
//    }
    
     
//     public void pagoDetalleContadoSaldoAndDescuento() {
//        BalPagos control = new BalPagos();
//        BalAlumnos alumnos = new BalAlumnos();
//        control.setReferencia_P(txtReferencia.getText());
//        for (int x = 0; x < modelo.size(); x++) {
//            if(cboServicios.getSelectedItem().toString().equals(modelo.get(x).NombreServicio)){
//                control.setReferencia_S(modelo.get(x).IDServicio + "");
//            }
//        }
//        control.setNota(txtNota.getText());
//        control.setNombreServicio(cboServicios.getSelectedItem().toString());
//        control.setMensualidad("No Aplica");
//        Date dateI = new Date();
//        long d = dateI.getTime();
//        java.sql.Date fecha = new java.sql.Date(d);
//        control.setFechaVencimiento(fecha);
//        control.setPagoMensualidad("No aplica");
//        control.setDescuento(txtDescuento.getText());
//        int saldo = Integer.parseInt(txtSaldo.getText());
//        int precioServicio = Integer.parseInt(txtPrecio.getText());
//        
//        int descuento = (precioServicio*Integer.parseInt(txtDescuento.getText()))/100;
//        
//        
////        if((precioServicio-descuento) < saldo){
////            alumnos.asignarSaldo(Integer.parseInt(txtNoControl.getText()), (-1*(precioServicio-descuento))+"");
////            control.setPagoTotal((precioServicio-descuento)+"");
////           
////        }else{
////            alumnos.asignarSaldo(Integer.parseInt(txtNoControl.getText()), 0+"");
////            control.setPagoTotal(((precioServicio-descuento)-saldo)+"");
////        }
//        control.setEstatus("Pagado");
//        control.agregarPagoContadoDetalle(control);
//    }
     
     public void pagoMensualidad() {
        BalPagos control = new BalPagos();
        control.setReferencia_P(txtReferencia.getText());
        control.setIDUsuario(Integer.parseInt(admin));
        control.setIDAlumno(Integer.parseInt(txtNoControl.getText()));
        Date dateI = txtFecha.getDate();
        long d = dateI.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        control.setFecha(fecha);
        control.setIDPeriodo(Integer.parseInt(periodo));
        control.setCosto(txtPrecio.getText());
        control.setTipoPago("Mensualidad");
        control.setEstatus("Pendiente");
        control.agregarPagoContado(control);
        pagoDetalleMensualidad();
         System.out.println("ME EJECUTE N VECES");
    }
    
     /*
     RESTRICCION: 10 DE CADA MES -10
        < 10 MES DE HOY 29-30 -1 ---> 
     Suponiendo: Fecha = 19/11/2021 <--- Hoy hicimos el pago
     
     */
     
    
     
     public void pagoDetalleMensualidad() {
        BalPagos control = new BalPagos();
        BalAlumnos alumnos = new BalAlumnos();
        Date dateI = new Date();
        long d = dateI.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        String meses[] = (fecha+"").split("-");
        int dia = Integer.parseInt(meses[2]);
        SimpleDateFormat fe = new SimpleDateFormat("yyyy-MM-dd");
        
        int size = Integer.parseInt(cboMeses.getSelectedItem()+"");
        for(int i=0;i<size;i++){
            int year = Integer.parseInt(meses[0]);
            int mes = Integer.parseInt(meses[1]);
            String result ="";
            control.setReferencia_P(txtReferencia.getText());
            for (int x = 0; x < modelo.size(); x++) {
                if(cboServicios.getSelectedItem().toString().equals(modelo.get(x).NombreServicio)){
                    control.setReferencia_S(modelo.get(x).IDServicio + "");
                }
            }
            control.setNota(txtNota.getText());
            control.setNombreServicio(cboServicios.getSelectedItem().toString());
            control.setMensualidad((i+1)+"");
            
            if(dia>27){
                if(mes==11){
                    meses[0] = (year+1)+"";
                    meses[1] = ("01");
                }
                else if(mes ==12){
                    meses[0] = (year+1)+"";
                    meses[1] = "02";
                }else{
                    meses[1]= (mes+1)<10?("0"+(mes+1)):""+(mes+1);
                }
                dia = 10;
                result =  meses[0]+"-"+meses[1]+"-"+dia;
                
                try {
                                  
                    Date dateLong = fe.parse(result);
                    long variableLong = dateLong.getTime();
                    java.sql.Date resultFecha = new java.sql.Date(variableLong);
                    control.setFechaVencimiento(resultFecha);
            
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                }

            }else{
                 if(mes==12){
                    meses[0] = (year+1)+"";
                    meses[1] = "01";
                }else{
                    meses[1]=(mes+1)<10?("0"+(mes+1)):""+(mes+1);
                }
                dia = 10;
                result =  meses[0]+"-"+meses[1]+"-"+dia;
                
               try {
                                  
                    Date dateLong = fe.parse(result);
                    long variableLong = dateLong.getTime();
                    java.sql.Date resultFecha = new java.sql.Date(variableLong);
                    control.setFechaVencimiento(resultFecha);
            
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Something went wrong");
                }
                

            }
           
            
            control.setPagoMensualidad((Integer.parseInt(txtPrecio.getText())/size)+"");
            control.setDescuento(0+"");
            control.setPagoTotal((Integer.parseInt(txtPrecio.getText())/size)+"");
            control.setEstatus("Pendiente");
            control.agregarPagoContadoDetalle(control);
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
        jLabel2 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNoControl = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboServicios = new javax.swing.JComboBox<>();
        txtFecha = new com.toedter.calendar.JDateChooser();
        chkMensualidad = new javax.swing.JCheckBox();
        cboMeses = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnPagar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        txtDescuento = new javax.swing.JTextField();
        chkDescuento = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        lblDescuento = new javax.swing.JLabel();
        txtConDescuento = new javax.swing.JTextField();
        txtDigitos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        chkTipoDePago = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        txtBanco = new javax.swing.JTextField();
        chkPagoTrasnferencia = new javax.swing.JCheckBox();

        setClosable(true);
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                formComponentRemoved(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(42, 157, 143));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATOS DEL PAGO:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Referencia:");

        txtReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Clave Alumno:");

        txtNoControl.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNoControlCaretUpdate(evt);
            }
        });
        txtNoControl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNoControlKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoControlKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Servicio:");

        cboServicios.setFocusable(false);
        cboServicios.setRequestFocusEnabled(false);
        cboServicios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboServiciosItemStateChanged(evt);
            }
        });

        txtFecha.setDateFormatString("yyyy-MM-dd");

        chkMensualidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chkMensualidad.setForeground(new java.awt.Color(255, 255, 255));
        chkMensualidad.setText("Mensualidad");
        chkMensualidad.setFocusPainted(false);
        chkMensualidad.setOpaque(false);
        chkMensualidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMensualidadActionPerformed(evt);
            }
        });

        cboMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        cboMeses.setRequestFocusEnabled(false);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DATOS DEL SERVICIO:");

        btnPagar.setBackground(new java.awt.Color(0, 102, 204));
        btnPagar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(255, 255, 255));
        btnPagar.setText("Pagar");
        btnPagar.setBorderPainted(false);
        btnPagar.setContentAreaFilled(false);
        btnPagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPagar.setFocusPainted(false);
        btnPagar.setOpaque(true);
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAlumnos.setRowHeight(32);
        jScrollPane1.setViewportView(tblAlumnos);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Precio:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nota:");

        txtDescuento.setEditable(false);
        txtDescuento.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDescuentoCaretUpdate(evt);
            }
        });
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        chkDescuento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chkDescuento.setForeground(new java.awt.Color(255, 255, 255));
        chkDescuento.setText("Descuento");
        chkDescuento.setOpaque(false);
        chkDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDescuentoActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(232, 59, 45));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Limpiar");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblDescuento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblDescuento.setForeground(new java.awt.Color(255, 255, 255));
        lblDescuento.setText("Con descuento: ");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ultimos 4 digitos");

        chkTipoDePago.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chkTipoDePago.setForeground(new java.awt.Color(255, 255, 255));
        chkTipoDePago.setText("Pago con tarjeta");
        chkTipoDePago.setOpaque(false);
        chkTipoDePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTipoDePagoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Banco");

        txtBanco.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        chkPagoTrasnferencia.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chkPagoTrasnferencia.setForeground(new java.awt.Color(255, 255, 255));
        chkPagoTrasnferencia.setText("Transferencia");
        chkPagoTrasnferencia.setOpaque(false);
        chkPagoTrasnferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPagoTrasnferenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(cboServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel8)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel4))
                                        .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(160, 160, 160))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtConDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(41, 41, 41)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(chkMensualidad, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chkDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chkTipoDePago)
                                            .addComponent(chkPagoTrasnferencia))))))))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(75, 75, 75)
                        .addComponent(jLabel7)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(chkDescuento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkMensualidad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkTipoDePago)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkPagoTrasnferencia)
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtConDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPagar))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(139, 139, 139)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addComponent(lblDescuento)))))
                        .addGap(2, 2, 2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkMensualidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMensualidadActionPerformed
        // TODO add your handling code here:
        if (chkMensualidad.isSelected()) {
            if(chkDescuento.isSelected()){
                chkDescuento.doClick();
            }
            if(chkTipoDePago.isSelected()){
                chkTipoDePago.doClick();
            }
            cboMeses.setEnabled(true);
            chkTipoDePago.setEnabled(false);
            chkDescuento.setEnabled(false);

        } else {
            cboMeses.setEnabled(false);
            chkTipoDePago.setEnabled(true);
            chkDescuento.setEnabled(true);

        }
    }//GEN-LAST:event_chkMensualidadActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        generarCodigo();
        //System.out.println(TipoAdmin);
        if(!TipoAdmin.equals("Administrador")){
            chkPagoTrasnferencia.setVisible(false);
        }
        cargarServicios();
        cargarAlumnos();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        // TODO add your handling code here:
        //pagoContado();
        
        BalPagos pagos = new BalPagos();
        BalAlumnos alumno = new BalAlumnos();
        //pagos.validarReferencia(s);
        
        if(txtNoControl.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese los campos faltantes");
        }else if((txtDigitos.getText().length()<4 || txtBanco.getText().equals("")) && chkTipoDePago.isSelected()){
                        JOptionPane.showMessageDialog(null, "Ingrese los datos faltantes de la tarjeta");
        }else{
            if(!pagos.validarReferencia(txtReferencia.getText())){
                if(alumno.isNoControlValid(Integer.parseInt(txtNoControl.getText()))){
    //                if(chkAplicarSaldo.isSelected() && chkDescuento.isSelected()){
    //                pagoContadoConSaldoAndDescuento();
    //                generarCodigo();
    //                cargarAlumnos();
    //                }
                    if(chkDescuento.isSelected()){
                        pagoContadoConDescuento();
                        generarCodigo();
                    }
                    else if(chkMensualidad.isSelected()){
                        pagoMensualidad();
                        generarCodigo();

                    }
                    else{
                        pagoContado();
                        generarCodigo();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Numero de control no encontrado. Ingrese uno valido");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Referencia existente, generando nueva referencia intente otra vez");
                generarCodigo();
            }
        }
        
    }//GEN-LAST:event_btnPagarActionPerformed

    private void cboServiciosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboServiciosItemStateChanged
        // TODO add your handling code here:
        for (int x = 0; x < modelo.size(); x++) {
            if(cboServicios.getSelectedItem().toString().equals(modelo.get(x).NombreServicio)){
                txtPrecio.setText(modelo.get(x).CostoServicio + "");
                if(chkDescuento.isSelected()){
                    txtConDescuento.setText((Integer.parseInt(txtPrecio.getText())-((Integer.parseInt(txtPrecio.getText())*Integer.parseInt(txtDescuento.getText()))/100))+"");
                }else{
                    txtConDescuento.setText(txtPrecio.getText());
                }
            }
        }
    }//GEN-LAST:event_cboServiciosItemStateChanged

    private void chkDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDescuentoActionPerformed
        // TODO add your handling code here:
        if (chkDescuento.isSelected()) {
            txtDescuento.setEditable(true);
            txtConDescuento.setText((Integer.parseInt(txtPrecio.getText())-((Integer.parseInt(txtPrecio.getText())*Integer.parseInt(txtDescuento.getText()))/100))+"");
        } else {
            txtDescuento.setEditable(false);
            txtConDescuento.setText(txtPrecio.getText());
        }
    }//GEN-LAST:event_chkDescuentoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txtNoControl.setText("");
        txtDescuento.setText("");
        txtNota.setText("");
        //txtSaldo.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNoControlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoControlKeyPressed
        // TODO add your handling code here:
        if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V){
            evt.consume();
        }
    }//GEN-LAST:event_txtNoControlKeyPressed

    private void txtNoControlCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNoControlCaretUpdate
        // TODO add your handling code here:
//        String valor = txtNoControl.getText();
//        if (valor.isEmpty()) {
//            txtNoControl.setText(null);
//        } else {
//           for(int i=0;i<modeloAlumnos.size();i++){
//               if((modeloAlumnos.get(i).NoControl+"").equals(valor)){
//                   txtSaldo.setText(modeloAlumnos.get(i).CodigoPostal);
//               }
//           }
//        }
    }//GEN-LAST:event_txtNoControlCaretUpdate

    private void formComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentRemoved
        // TODO add your handling code here:

    }//GEN-LAST:event_formComponentRemoved

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formInternalFrameActivated

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here
        
    }//GEN-LAST:event_formMouseEntered

    private void txtReferenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReferenciaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        int size = txtReferencia.getText().length();
        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean numero = key >= 48 && key <= 57;
         if (!(numero || mayusculas || minusculas) || size>=8)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtReferenciaKeyTyped

    private void txtNoControlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoControlKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        int size = txtNoControl.getText().length();
        boolean numero = key >= 48 && key <= 57;
         if (!numero || size>=8)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNoControlKeyTyped

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        int size = txtDescuento.getText().length();
        boolean numero = key >= 48 && key <= 57;
         if (!numero || size>=2)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyPressed
        // TODO add your handling code here:
        if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V){
            evt.consume();
        }
    }//GEN-LAST:event_txtDescuentoKeyPressed

    private void txtDescuentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescuentoCaretUpdate
        // TODO add your handling code here:
        if(!txtPrecio.getText().isEmpty() && !txtDescuento.getText().isEmpty()){
            txtConDescuento.setText(((Integer.parseInt(txtPrecio.getText())-((Integer.parseInt(txtPrecio.getText())*Integer.parseInt(txtDescuento.getText()))/100))+""));
        }else{
           txtConDescuento.setText(txtPrecio.getText());
        }
    }//GEN-LAST:event_txtDescuentoCaretUpdate

    private void chkTipoDePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTipoDePagoActionPerformed
        // TODO add your handling code here:
        if(chkTipoDePago.isSelected()){
            txtDigitos.setEnabled(true);
            txtBanco.setEnabled(true);
        }else{
            txtDigitos.setEnabled(false);
            txtBanco.setEnabled(false);
            txtBanco.setText("");
            txtDigitos.setText("");
        }

    }//GEN-LAST:event_chkTipoDePagoActionPerformed

    private void chkPagoTrasnferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPagoTrasnferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPagoTrasnferenciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPagar;
    private javax.swing.JComboBox<String> cboMeses;
    private javax.swing.JComboBox<String> cboServicios;
    private javax.swing.JCheckBox chkDescuento;
    private javax.swing.JCheckBox chkMensualidad;
    private javax.swing.JCheckBox chkPagoTrasnferencia;
    private javax.swing.JCheckBox chkTipoDePago;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtConDescuento;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDigitos;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtNoControl;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtReferencia;
    // End of variables declaration//GEN-END:variables

    

}
