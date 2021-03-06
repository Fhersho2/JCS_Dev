package Views;

import BAL.BalAlumnos;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author natsu-terckoer
 */
public class Alumnos extends javax.swing.JInternalFrame {

    Calendar Fecha_actual = new GregorianCalendar();
    BalAlumnos control = new BalAlumnos();

   
    
    /**
     * Creates new form Alumnos
     */
    public Alumnos() {

        initComponents();
        this.setTitle("Alumnos");
        
        txtFechaP.setEnabled(false);
        txtFechaP.setCalendar(Fecha_actual);
        txtPeriodoL.setCalendar(Fecha_actual);
        
        

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(this);
        try {
            this.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtFechaN.setEnabled(false);
        txtFechaN.getCalendarButton().setEnabled(true);
        txtPeriodoL.setEnabled(false);
        txtPeriodoL.getCalendarButton().setEnabled(true);
        
        
    }

    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }
    };

    public void Limpiar() {
        txtNoControl.setEditable(true);
        txtNoControl.setText("");
        txtNombreA.setText("");
        txtFechaN.setDate(null);
        txtLugarN.setText("");
        txtCorreo.setText("");
        cboSemestre.setSelectedIndex(0);
        txtFechaP.setCalendar(Fecha_actual);
        cboGrupo.setSelectedIndex(0);
        cboPeriodo.setSelectedIndex(0);
        txtPadre.setText("");
        txtMadre.setText("");
        txtDomicilio.setText("");
        txtTelefono.setText("");
        txtEmergencias.setText("");
        cboEstatus.setSelectedIndex(0);
        txtCodigoPostal.setText("");
        actualizarDatos();
    }

    public void actualizarDatos() {
        model.setNumRows(0);
        model.setColumnCount(0);
        model.addColumn("NoControl");
        model.addColumn("Nombre");
        model.addColumn("Semestre");
        model.addColumn("Estatus");
        tblAlumnos.getTableHeader().setReorderingAllowed(false);
        ArrayList<BalAlumnos> modelo;
        modelo = control.listarAlumnos();
        try {
            int cantidad = modelo.size();
            model.setNumRows(cantidad);
            int x = 0;
            Iterator<BalAlumnos> itrAlumnos = modelo.iterator();
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

    public void agregarAlumno() {
        if (txtNoControl.getText().equals("") || txtNombreA.getText().equals("") || (txtFechaN.getDate() + "").equals("") || txtLugarN.getText().equals("") || txtCorreo.getText().equals("") || cboSemestre.getSelectedItem().equals("...") || cboGrupo.getSelectedItem().equals("...") || cboPeriodo.getSelectedItem().equals("...") || (txtFechaP.getDate() + "").equals("") || txtPadre.getText().equals("") || txtMadre.getText().equals("") || txtDomicilio.getText().equals("") || txtTelefono.getText().equals("") || txtEmergencias.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campos Vacios");

        } else {
            Date dateI = txtFechaN.getDate();
            long d = dateI.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            Date dateP = txtFechaP.getDate();
            long F = dateP.getTime();
            java.sql.Date fechap = new java.sql.Date(F);
            BalAlumnos alumno = new BalAlumnos();
            alumno.setNoControl(Integer.parseInt(txtNoControl.getText()));
            alumno.setNombre(txtNombreA.getText());
            alumno.setFechaNac(fecha);
            alumno.setLugarNac(txtLugarN.getText());
            alumno.setCorreo(txtCorreo.getText());
            alumno.setSemestre(cboSemestre.getSelectedItem().toString());
            alumno.setGrupo(cboGrupo.getSelectedItem().toString());
            alumno.setPeriodoF(fechap.toString().substring(0, 4));
            alumno.setPeriodoE(cboPeriodo.getSelectedItem().toString());
            alumno.setPadreTutor(txtPadre.getText());
            alumno.setMadre(txtMadre.getText());
            alumno.setDomicilio(txtDomicilio.getText());
            alumno.setTelefono(txtTelefono.getText());
            alumno.setEmergencias(txtEmergencias.getText());
            alumno.setEstatus(cboEstatus.getSelectedItem().toString());
            alumno.setCodigoPostal(txtCodigoPostal.getText());
            
            alumno.agregarAlumnos(alumno);
            actualizarDatos();
        }
    }

    public void modificarAlumno() {
        if (txtNoControl.getText().equals("") || txtNombreA.getText().equals("") || (txtFechaN.getDate() + "").equals("") || txtLugarN.getText().equals("") || txtCorreo.getText().equals("") || cboSemestre.getSelectedItem().equals("...") || cboGrupo.getSelectedItem().equals("...") || cboPeriodo.getSelectedItem().equals("...") || (txtFechaP.getDate() + "").equals("") || txtPadre.getText().equals("") || txtMadre.getText().equals("") || txtDomicilio.getText().equals("") || txtTelefono.getText().equals("") || txtEmergencias.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campos Vacios");

        } else {
            Date dateI = txtFechaN.getDate();
            long d = dateI.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            Date dateP = txtFechaP.getDate();
            long F = dateP.getTime();
            java.sql.Date fechap = new java.sql.Date(F);
            BalAlumnos alumno = new BalAlumnos();
            alumno.setNoControl(Integer.parseInt(txtNoControl.getText()));
            alumno.setNombre(txtNombreA.getText());
            alumno.setFechaNac(fecha);
            alumno.setLugarNac(txtLugarN.getText());
            alumno.setCorreo(txtCorreo.getText());
            alumno.setSemestre(cboSemestre.getSelectedItem().toString());
            alumno.setGrupo(cboGrupo.getSelectedItem().toString());
            alumno.setPeriodoF(fechap.toString().substring(0, 4));
            alumno.setPeriodoE(cboPeriodo.getSelectedItem().toString());
            alumno.setPadreTutor(txtPadre.getText());
            alumno.setMadre(txtMadre.getText());
            alumno.setDomicilio(txtDomicilio.getText());
            alumno.setTelefono(txtTelefono.getText());
            alumno.setEmergencias(txtEmergencias.getText());
            alumno.setEstatus(cboEstatus.getSelectedItem().toString());
            alumno.setCodigoPostal(txtCodigoPostal.getText());
            alumno.modificarAlumno(alumno);
            actualizarDatos();
        }
    }

    public void eliminarAlumno() {
        if (txtNoControl.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No puedes eliminar algo que no haz seleccionado");

        } else {
            BalAlumnos alumno = new BalAlumnos();
            alumno.eliminarAlumno(Integer.parseInt(txtNoControl.getText()));
            actualizarDatos();
        }
    }

    public void Actions() {
        if (cboAcciones.getSelectedItem().equals("Agregar")) {
            agregarAlumno();
        } else if (cboAcciones.getSelectedItem().equals("Modificar") && !txtNoControl.getText().equals("")) {
            modificarAlumno();
        } else if (cboAcciones.getSelectedItem().equals("Eliminar")) {
            eliminarAlumno();
        } else {
            JOptionPane.showMessageDialog(null, "No puedes modificar y/o eliminar campos vacios");
        }

    }

    /**
     * Telefono This method is called from within the constructor to initialize
     * the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreA = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNoControl = new javax.swing.JTextField();
        txtLugarN = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtFechaN = new com.toedter.calendar.JDateChooser();
        cboSemestre = new javax.swing.JComboBox<>();
        txtFechaP = new com.toedter.calendar.JDateChooser();
        cboGrupo = new javax.swing.JComboBox<>();
        cboPeriodo = new javax.swing.JComboBox<>();
        cboAcciones = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        btnBuscarAlumno = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtEmergencias = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMadre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPadre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDomicilio = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCodigoPostal = new javax.swing.JTextField();
        cboEstatus = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cboSemestreL = new javax.swing.JComboBox<>();
        cboGrupoL = new javax.swing.JComboBox<>();
        txtPeriodoL = new com.toedter.calendar.JDateChooser();
        cboPeriodoL = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnPendientes = new javax.swing.JButton();
        btnBajas = new javax.swing.JButton();
        btnActivos = new javax.swing.JButton();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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

        jPanel1.setBackground(new java.awt.Color(42, 157, 143));

        jPanel2.setBackground(new java.awt.Color(42, 157, 143));

        jLabel13.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DATOS GENERALES DEL ALUMNO");

        jPanel3.setBackground(new java.awt.Color(42, 157, 143));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre del alumno");

        txtCorreo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha de nacimiento");

        txtNombreA.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNombreA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreAKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreAKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lugar de nacimiento");

        txtNoControl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNoControl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNoControlKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoControlKeyTyped(evt);
            }
        });

        txtLugarN.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtLugarN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLugarNKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Correo electronico");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No de Control");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Semestre");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Grupo");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Periodo");

        txtFechaN.setDateFormatString("yyyy/MM/dd");

        cboSemestre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "1", "2", "3", "4", "5", "6" }));
        cboSemestre.setToolTipText("");
        cboSemestre.setPreferredSize(new java.awt.Dimension(39, 29));

        txtFechaP.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaP.setForeground(new java.awt.Color(255, 255, 255));
        txtFechaP.setDateFormatString("yyyy");
        txtFechaP.setInheritsPopupMenu(true);

        cboGrupo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "A", "B", "C", "D", "E", "F", "G" }));
        cboGrupo.setMinimumSize(new java.awt.Dimension(39, 29));
        cboGrupo.setPreferredSize(new java.awt.Dimension(39, 29));

        cboPeriodo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "A", "B" }));
        cboPeriodo.setPreferredSize(new java.awt.Dimension(39, 29));
        cboPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPeriodoActionPerformed(evt);
            }
        });

        cboAcciones.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboAcciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agregar", "Modificar", "Eliminar" }));
        cboAcciones.setRequestFocusEnabled(false);

        btnLimpiar.setBackground(new java.awt.Color(232, 59, 45));
        btnLimpiar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setRequestFocusEnabled(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEjecutar.setBackground(new java.awt.Color(0, 153, 255));
        btnEjecutar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEjecutar.setForeground(new java.awt.Color(255, 255, 255));
        btnEjecutar.setText("Ejecutar");
        btnEjecutar.setBorderPainted(false);
        btnEjecutar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEjecutar.setRequestFocusEnabled(false);
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        btnBuscarAlumno.setBackground(new java.awt.Color(0, 153, 255));
        btnBuscarAlumno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscarAlumno.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarAlumno.setText("Search");
        btnBuscarAlumno.setBorderPainted(false);
        btnBuscarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAlumnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtNombreA, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaN, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLugarN, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cboAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtFechaP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(cboSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboPeriodo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboGrupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarAlumno)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFechaN, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLugarN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFechaP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboPeriodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(42, 157, 143));

        txtEmergencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmergenciasActionPerformed(evt);
            }
        });

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Domicilio ");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tel??fono");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Emergencias");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre de padre o tutor");

        txtMadre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMadreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMadreKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombre de madre");

        txtPadre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPadreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPadreKeyTyped(evt);
            }
        });

        txtDomicilio.setColumns(20);
        txtDomicilio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDomicilio.setRows(5);
        jScrollPane1.setViewportView(txtDomicilio);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Estatus");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Codigo postal");

        txtCodigoPostal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodigoPostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoPostalActionPerformed(evt);
            }
        });
        txtCodigoPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoPostalKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoPostalKeyTyped(evt);
            }
        });

        cboEstatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cboEstatus.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(txtMadre)
                    .addComponent(txtCodigoPostal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmergencias, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTelefono)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cboEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPadre))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel6)
                .addGap(32, 32, 32)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(txtMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmergencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBackground(new java.awt.Color(42, 157, 143));

        jLabel17.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Seleccione para generar lista ");

        cboSemestreL.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboSemestreL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "1", "2", "3", "4", "5", "6" }));
        cboSemestreL.setToolTipText("");
        cboSemestreL.setPreferredSize(new java.awt.Dimension(45, 29));

        cboGrupoL.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboGrupoL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "A", "B", "C", "D", "E", "F", "G" }));
        cboGrupoL.setPreferredSize(new java.awt.Dimension(45, 29));

        txtPeriodoL.setBackground(new java.awt.Color(255, 255, 255));
        txtPeriodoL.setForeground(new java.awt.Color(255, 255, 255));
        txtPeriodoL.setDateFormatString("yyyy");
        txtPeriodoL.setInheritsPopupMenu(true);
        txtPeriodoL.setMinimumSize(new java.awt.Dimension(81, 29));

        cboPeriodoL.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cboPeriodoL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "A", "B" }));
        cboPeriodoL.setPreferredSize(new java.awt.Dimension(45, 29));

        tblAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAlumnos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblAlumnos.setRowHeight(30);
        tblAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlumnosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAlumnos);

        btnBuscar.setBackground(new java.awt.Color(0, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnPendientes.setBackground(new java.awt.Color(204, 153, 0));
        btnPendientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPendientes.setForeground(new java.awt.Color(255, 255, 255));
        btnPendientes.setText("Pendientes");
        btnPendientes.setBorderPainted(false);
        btnPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPendientes.setRequestFocusEnabled(false);
        btnPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendientesActionPerformed(evt);
            }
        });

        btnBajas.setBackground(new java.awt.Color(232, 59, 45));
        btnBajas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBajas.setForeground(new java.awt.Color(255, 255, 255));
        btnBajas.setText("Inactivos");
        btnBajas.setBorderPainted(false);
        btnBajas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBajas.setRequestFocusEnabled(false);
        btnBajas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajasActionPerformed(evt);
            }
        });

        btnActivos.setBackground(new java.awt.Color(0, 204, 51));
        btnActivos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnActivos.setForeground(new java.awt.Color(255, 255, 255));
        btnActivos.setText("Activos");
        btnActivos.setBorderPainted(false);
        btnActivos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActivos.setRequestFocusEnabled(false);
        btnActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(cboSemestreL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGrupoL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPeriodoL, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboPeriodoL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBajas, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboPeriodoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBajas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPeriodoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboGrupoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboSemestreL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void cboPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPeriodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPeriodoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        Actions();
        Limpiar();
        txtNoControl.setEditable(true);
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void txtEmergenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmergenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmergenciasActionPerformed

    private void txtCodigoPostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoPostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoPostalActionPerformed

    private void btnActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivosActionPerformed
        model.setNumRows(0);
        model.setColumnCount(0);
        model.addColumn("NoControl");
        model.addColumn("Nombre");
        model.addColumn("Semestre");
        model.addColumn("Estatus");
        tblAlumnos.getTableHeader().setReorderingAllowed(false);
        ArrayList<BalAlumnos> modelo;

        modelo = control.listaActivos();
        try {
            int cantidad = modelo.size();
            model.setNumRows(cantidad);
            int x = 0;
            Iterator<BalAlumnos> itrAlumnos = modelo.iterator();
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
    }//GEN-LAST:event_btnActivosActionPerformed

    private void btnBajasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajasActionPerformed
        model.setNumRows(0);
        model.setColumnCount(0);
        model.addColumn("NoControl");
        model.addColumn("Nombre");
        model.addColumn("Semestre");
        model.addColumn("Estatus");
        tblAlumnos.getTableHeader().setReorderingAllowed(false);
        ArrayList<BalAlumnos> modelo;

        modelo = control.listaInactivos();
        try {
            int cantidad = modelo.size();
            model.setNumRows(cantidad);
            int x = 0;
            Iterator<BalAlumnos> itrAlumnos = modelo.iterator();
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
    }//GEN-LAST:event_btnBajasActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (cboSemestreL.getSelectedItem().equals("...") || cboGrupoL.getSelectedItem().equals("...") || (txtPeriodoL.getDate() + "").equals("") || cboPeriodoL.getSelectedItem().equals("...")) {
            JOptionPane.showMessageDialog(null, "Tiene campos de busqueda vacios");
        } else {
            model.setNumRows(0);
            model.setColumnCount(0);
            model.addColumn("NoControl");
            model.addColumn("Nombre");
            model.addColumn("Semestre");
            model.addColumn("Estatus");
            tblAlumnos.getTableHeader().setReorderingAllowed(false);
            ArrayList<BalAlumnos> modelo;
            String fecha = (txtPeriodoL.getDate() + "").substring((txtPeriodoL.getDate() + "").length() - 4, (txtPeriodoL.getDate() + "").length());
            modelo = control.generarListaGrupo(cboSemestreL.getSelectedItem().toString(), cboGrupoL.getSelectedItem().toString(), fecha, cboPeriodoL.getSelectedItem().toString());
            try {
                int cantidad = modelo.size();
                model.setNumRows(cantidad);
                int x = 0;
                Iterator<BalAlumnos> itrAlumnos = modelo.iterator();
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
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendientesActionPerformed

    }//GEN-LAST:event_btnPendientesActionPerformed

    private void tblAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlumnosMouseClicked
        int fila = tblAlumnos.getSelectedRow();
        txtNoControl.setEditable(false);
        if (fila == -1) {
        } else {
            SimpleDateFormat fechaN = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat fechaP = new SimpleDateFormat("yyyy");
            cboAcciones.setSelectedIndex(1);
            ArrayList<BalAlumnos> modelo;
            modelo = control.consultarAlumno(Integer.parseInt(tblAlumnos.getValueAt(fila, 0).toString()));
            try {
                txtNoControl.setText(modelo.get(0).NoControl + "");
                txtNombreA.setText(modelo.get(0).Nombre);
                txtFechaN.setDate(fechaN.parse(modelo.get(0).FechaNac + ""));
                txtLugarN.setText(modelo.get(0).LugarNac);
                txtCorreo.setText(modelo.get(0).Correo);
                cboSemestre.setSelectedItem(modelo.get(0).Semestre);
                cboGrupo.setSelectedItem(modelo.get(0).Grupo);
                txtFechaP.setDateFormatString("yyyy");
                txtFechaP.setDate(fechaP.parse(modelo.get(0).PeriodoF + ""));
                cboPeriodo.setSelectedItem(modelo.get(0).PeriodoE);
                txtPadre.setText(modelo.get(0).PadreTutor);
                txtMadre.setText(modelo.get(0).Madre);
                txtDomicilio.setText(modelo.get(0).Domicilio);
                txtTelefono.setText(modelo.get(0).Telefono);
                txtEmergencias.setText(modelo.get(0).Emergencias);
                cboEstatus.setSelectedItem(modelo.get(0).Estatus);
                txtCodigoPostal.setText(modelo.get(0).CodigoPostal);
                actualizarDatos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_tblAlumnosMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        actualizarDatos();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnBuscarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAlumnoActionPerformed
        // TODO add your handling code here:
        if (!txtNoControl.getText().equals("")) {
            model.setNumRows(0);
            model.setColumnCount(0);
            model.addColumn("NoControl");
            model.addColumn("Nombre");
            model.addColumn("Semestre");
            model.addColumn("Estatus");
            tblAlumnos.getTableHeader().setReorderingAllowed(false);
            ArrayList<BalAlumnos> modelo;
            modelo = control.consultarAlumno(Integer.parseInt(txtNoControl.getText()));
            try {
                int cantidad = modelo.size();
                model.setNumRows(cantidad);
                int x = 0;
                Iterator<BalAlumnos> itrAlumnos = modelo.iterator();
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
    }//GEN-LAST:event_btnBuscarAlumnoActionPerformed

    private void txtNoControlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoControlKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        int size = txtNoControl.getText().length();
        boolean numero = key >= 48 && key <= 57;
        System.out.println(key);
         if (!numero || size>=8 )
        {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtNoControlKeyTyped

    private void txtNombreAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreAKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        int size = txtNombreA.getText().length();
        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

         if (!(minusculas || mayusculas || espacio) || size>=55)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreAKeyTyped

    private void txtPadreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPadreKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

         if (!(minusculas || mayusculas || espacio))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtPadreKeyTyped

    private void txtMadreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMadreKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

         if (!(minusculas || mayusculas || espacio))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtMadreKeyTyped

    private void txtCodigoPostalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPostalKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >= 48 && key <= 57;

         if (!numero)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoPostalKeyTyped

    private void txtLugarNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLugarNKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        int size = txtLugarN.getText().length();
        
         if (size>=55)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtLugarNKeyTyped

    private void txtNoControlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoControlKeyPressed
        // TODO add your handling code here:
        if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V){
            evt.consume();
        }
    }//GEN-LAST:event_txtNoControlKeyPressed

    private void txtNombreAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreAKeyPressed
        // TODO add your handling code here:
        if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreAKeyPressed

    private void txtPadreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPadreKeyPressed
        // TODO add your handling code here:
        if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V){
            evt.consume();
        }
    }//GEN-LAST:event_txtPadreKeyPressed

    private void txtMadreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMadreKeyPressed
        // TODO add your handling code here:
        if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V){
            evt.consume();
        }
    }//GEN-LAST:event_txtMadreKeyPressed

    private void txtCodigoPostalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoPostalKeyPressed
        // TODO add your handling code here:
        if(evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V){
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoPostalKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivos;
    private javax.swing.JButton btnBajas;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarAlumno;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnPendientes;
    private javax.swing.JComboBox<String> cboAcciones;
    private javax.swing.JComboBox<String> cboEstatus;
    private javax.swing.JComboBox<String> cboGrupo;
    private javax.swing.JComboBox<String> cboGrupoL;
    private javax.swing.JComboBox<String> cboPeriodo;
    private javax.swing.JComboBox<String> cboPeriodoL;
    private javax.swing.JComboBox<String> cboSemestre;
    private javax.swing.JComboBox<String> cboSemestreL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTextField txtCodigoPostal;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextArea txtDomicilio;
    private javax.swing.JTextField txtEmergencias;
    private com.toedter.calendar.JDateChooser txtFechaN;
    private com.toedter.calendar.JDateChooser txtFechaP;
    private javax.swing.JTextField txtLugarN;
    private javax.swing.JTextField txtMadre;
    public static javax.swing.JTextField txtNoControl;
    private javax.swing.JTextField txtNombreA;
    private javax.swing.JTextField txtPadre;
    private com.toedter.calendar.JDateChooser txtPeriodoL;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
