package GUI;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import com.teamdev.jxbrowser.chromium.a;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import oracle.net.aso.i;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Caty
 */
public class inicioSesion extends javax.swing.JFrame {

    //VARIABLES GLOBALES
    File imagenUsuario = null;
    int tipoUsuario;
    boolean a = false;
    private Connection conexion;

    //MODELOS DE COMBOBOX
    DefaultComboBoxModel modeloNacionalidad = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloHuertas = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloDedicacion = new DefaultComboBoxModel();

    //ARRAY PARA ID's Y NOMBRES
    ArrayList<Object> idHuertas = new ArrayList();
    ArrayList<String> nombreHuertas = new ArrayList();
    ArrayList<Object> idNacionalidad = new ArrayList();
    ArrayList<String> nombreNacionalidad = new ArrayList();
    ArrayList<Object> idDedicacion = new ArrayList();
    ArrayList<String> nombreDedicacion = new ArrayList();

    /**
     * Creates new form inicioSesion
     */
    public inicioSesion() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:HUERTA";
            conexion = DriverManager.getConnection(BaseDeDatos, "HUERTA", "Huerta_2018");
            if (conexion != null) {
                System.out.println("Conexion exitosa a esquema Huerta");
            } else {
                System.out.println("Conexion fallida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        setSize(316, 269);
        setLocationRelativeTo(null);
        ImageIcon imgThisImg = new ImageIcon("src/Images/LogoHuertica.jpeg");
        setIconImage(imgThisImg.getImage());
        Image scaleImage = imgThisImg.getImage().getScaledInstance(126, 124, Image.SCALE_DEFAULT);
        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);
        logoFoto.setIcon(imgThisImg2);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        fechaRegistro.setDateFormat(sdf);
        
        prepareInicio();

    }

    // CARGAR DATOS DE VENTANA PRINCIPAL
    public void prepareInicio() {
        usuarioInicio.setText("");
        passwordInicio.setText("");
    }

    //CARGAR DATOS DE VENTANA PARA REGISTRARSE
    public void prepareRegistro() throws SQLException {
        
        //SE VACÍAN LOS CAMPOS
        nombreRegistro.setText("");
        apellidoRegistro.setText("");
        cedulaRegistro.setText("");
        emailRegistro.setText("");
        telefonoRegistro.setText("");
        usuarioRegistro.setText("");
        passwordRegistro.setText("");
        ojala.setText("FOTO");
    
        
        //SE OBTIENE LA INFO DE HUERTAS, NACIONALIDADES Y DEDICACIÓN
        //SE CARGAN LOS ARRAY Y MODELS PARA COMBOBOX
    
        idNacionalidad.clear();
        idHuertas.clear();
        idDedicacion.clear();
        nombreNacionalidad.clear();
        nombreHuertas.clear();
        nombreDedicacion.clear();
        modeloNacionalidad.removeAllElements();
        modeloHuertas.removeAllElements();
        modeloDedicacion.removeAllElements();

        String nom = "{? =call paqUtilidades.huertaInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    idHuertas.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreHuertas.add(cursor.getString(i));
                }
            }
        }

        String nom2 = "{? =call paqUtilidades.paisInfo()}";
        CallableStatement cstmt2 = conexion.prepareCall(nom2);
        cstmt2.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt2.execute();
        ResultSet cursor2 = (ResultSet) cstmt2.getObject(1);
        while (cursor2.next()) {
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    idNacionalidad.add(cursor2.getInt(i));
                } else if (i == 2) {
                    nombreNacionalidad.add(cursor2.getString(i));
                }
            }
        }

        String nom3 = "{? = call paqUtilidades.dedicacionInfo()}";
        CallableStatement cstmt3 = conexion.prepareCall(nom3);
        cstmt3.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt3.execute();
        ResultSet cursor3 = (ResultSet) cstmt3.getObject(1);
        cursor3.next();
        cursor3.next();
        while (cursor3.next()) {
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    idDedicacion.add(cursor3.getInt(i));
                } else if (i == 2) {
                    nombreDedicacion.add(cursor3.getString(i));
                }
            }
        }

        for (String huerta : nombreHuertas) {
            modeloHuertas.addElement(huerta);
        }
        for (String nacionalidad : nombreNacionalidad) {
            modeloNacionalidad.addElement(nacionalidad);
        }
        
        for (String dedicacion : nombreDedicacion){
            modeloDedicacion.addElement(dedicacion);
        }

        imagenUsuario = null;

    }

    //REVISIÓN CAMPOS DE VENTANA PRINCIPAL
    public boolean revisarCamposInicio() {
        if ("".equals(usuarioInicio.getText())) {
            return false;
        }
        if ("".equals(String.valueOf(passwordInicio.getPassword()))) {
            return false;
        }
        return true;
    }

    //REVISIÓN CAMPOS DE VENTANA PARA REGISTRARSE
    public boolean revisarCamposRegistro() {
        if ("".equals(nombreRegistro.getText())) {
            return false;
        }
        if ("".equals(apellidoRegistro.getText())) {
            return false;
        }
        if ("".equals(cedulaRegistro.getText())) {
            return false;
        }
        if ("".equals(emailRegistro.getText())) {
            return false;
        }
        if ("".equals(telefonoRegistro.getText())) {
            return false;
        }
        if ("".equals(usuarioRegistro.getText())) {
            return false;
        }
        if ("".equals(String.valueOf(passwordRegistro.getPassword()))) {
            return false;
        }
        if (imagenUsuario == null) {
            return false;
        }

        return true;
    }

    //OBTENER LA HUERTA PARA EL REGISTRO
    public int getHuerta() {
        int posSeleccionado = huertaRegistro.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }

    //OBTENER LA NACIONALIDAD PARA EL REGISTRO
    public int getNacionalidad() {
        int posSeleccionado = nacionalidadRegistro.getSelectedIndex();
        int id = (Integer) idNacionalidad.get(posSeleccionado);
        return id;
    }

    //OBTENER LA DEDICACIÓN PARA EL REGISTRO
    public int getDedicacion() {
        int posSeleccionado = dedicacionRegistro.getSelectedIndex();
        int id = (Integer) idDedicacion.get(posSeleccionado);
        return id;
    }

    //CODIFICACIÓN DE LA CONTRASEÑA
    public String codificarPass(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest();
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                result.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //CONVERSIÓN DEL ARCHIVO A BLOB
    public static byte[] convertToBlob(String filePath) throws IOException {
        byte[] fileContent = null;
        StringBuffer fileContentStr = new StringBuffer("");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContentStr.append(line).append("\n");
            }
            fileContent = fileContentStr.toString().trim().getBytes();
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return fileContent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registroDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        fechaRegistro = new datechooser.beans.DateChooserCombo();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        usuarioRegistro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ojala = new javax.swing.JLabel();
        apellidoRegistro = new javax.swing.JTextField();
        telefonoRegistro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cedulaRegistro = new javax.swing.JTextField();
        emailRegistro = new javax.swing.JTextField();
        nombreRegistro = new javax.swing.JTextField();
        passwordRegistro = new javax.swing.JPasswordField();
        dedicacionRegistro = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        buttonRegistrarse = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        nacionalidadRegistro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        huertaRegistro = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        dialogFoto = new javax.swing.JDialog();
        elegirFoto = new javax.swing.JFileChooser();
        registroExitosoD = new javax.swing.JDialog();
        registroFallidoD = new javax.swing.JDialog();
        dialogInvitado = new javax.swing.JDialog();
        apellidoRegistro1 = new javax.swing.JTextField();
        telefonoRegistro1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        nacionalidadRegistro1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        passwordInicio = new javax.swing.JPasswordField();
        usuarioInicio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Logo = new javax.swing.JPanel();
        logoFoto = new javax.swing.JLabel();
        buttonReady = new javax.swing.JButton();

        registroDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        registroDialog.setTitle("REGISTRARSE");
        registroDialog.setResizable(false);
        registroDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(184, 139, 74));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fechaRegistro.setCurrentView(new datechooser.view.appearance.AppearancesList("Dali",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(255, 255, 255),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true),
            new datechooser.view.appearance.ViewAppearance("Mine",
                new datechooser.view.appearance.custom.CustomCellAppearance(new java.awt.Color(46, 53, 50),
                    new java.awt.Color(255, 255, 255),
                    (javax.swing.border.Border)null,
                    new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 153),
                    1.0f),
                new datechooser.view.appearance.custom.CustomCellAppearance(new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 255, 255),
                    (javax.swing.border.Border)null,
                    new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 102),
                    1.0f),
                new datechooser.view.appearance.custom.CustomCellAppearance(new java.awt.Color(184, 139, 74),
                    new java.awt.Color(255, 255, 255),
                    (javax.swing.border.Border)null,
                    new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 153),
                    1.0f),
                new datechooser.view.appearance.custom.CustomCellAppearance(new java.awt.Color(46, 53, 50),
                    new java.awt.Color(0, 0, 102),
                    (javax.swing.border.Border)null,
                    new java.awt.Font("Serif", java.awt.Font.ITALIC, 10),
                    new java.awt.Color(0, 0, 255),
                    1.0f),
                new datechooser.view.appearance.custom.CustomCellAppearance(new java.awt.Color(255, 255, 255),
                    new java.awt.Color(0, 0, 0),
                    (javax.swing.border.Border)null,
                    new java.awt.Font("Serif", java.awt.Font.BOLD, 12),
                    new java.awt.Color(0, 0, 255),
                    1.0f),
                new datechooser.view.appearance.custom.CustomCellAppearance(new java.awt.Color(204, 204, 204),
                    new java.awt.Color(102, 102, 102),
                    (javax.swing.border.Border)null,
                    new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(255, 0, 0),
                    1.0f),
                (datechooser.view.BackRenderer)null,
                true,
                true)));
    fechaRegistro.setCalendarBackground(new java.awt.Color(0, 0, 0));
    fechaRegistro.setCalendarPreferredSize(new java.awt.Dimension(390, 200));
    fechaRegistro.setNothingAllowed(false);
    fechaRegistro.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    fechaRegistro.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
    fechaRegistro.setLocale(new java.util.Locale("es", "", ""));
    fechaRegistro.setMaxDate(new java.util.GregorianCalendar(2018, 4, 6));
    fechaRegistro.setMinDate(new java.util.GregorianCalendar(1900, 0, 1));
    fechaRegistro.setNavigateFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
    fechaRegistro.setCurrentNavigateIndex(0);
    fechaRegistro.setShowOneMonth(true);
    jPanel3.add(fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 140, -1));
    fechaRegistro.getAccessibleContext().setAccessibleName("");

    jButton5.setBackground(new java.awt.Color(46, 53, 50));
    jButton5.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton5.setForeground(new java.awt.Color(204, 204, 204));
    jButton5.setText("Elegir Archivo...");
    jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });
    jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, 100, -1));

    jLabel7.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel7.setText("Dedicación:");
    jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, -1, -1));

    jLabel6.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel6.setText("Contraseña:");
    jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 133, -1, -1));

    usuarioRegistro.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    usuarioRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            usuarioRegistroKeyTyped(evt);
        }
    });
    jPanel3.add(usuarioRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 96, 220, -1));

    jLabel8.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel8.setText("Usuario:");
    jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 99, -1, -1));

    ojala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    ojala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    ojala.setPreferredSize(new java.awt.Dimension(250, 250));
    jPanel3.add(ojala, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 210, 180));

    apellidoRegistro.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    apellidoRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            apellidoRegistroKeyTyped(evt);
        }
    });
    jPanel3.add(apellidoRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 17, 220, -1));

    telefonoRegistro.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    telefonoRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            telefonoRegistroKeyTyped(evt);
        }
    });
    jPanel3.add(telefonoRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 57, 220, -1));

    jLabel1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel1.setText("Apellido:");
    jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 20, -1, -1));

    jLabel4.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel4.setText("Teléfono:");
    jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 60, -1, -1));

    cedulaRegistro.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    cedulaRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            cedulaRegistroKeyTyped(evt);
        }
    });
    jPanel3.add(cedulaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 220, -1));

    emailRegistro.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    emailRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            emailRegistroKeyPressed(evt);
        }
    });
    jPanel3.add(emailRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 220, -1));

    nombreRegistro.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    nombreRegistro.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nombreRegistroActionPerformed(evt);
        }
    });
    nombreRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            nombreRegistroKeyTyped(evt);
        }
    });
    jPanel3.add(nombreRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 220, -1));

    passwordRegistro.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            passwordRegistroKeyTyped(evt);
        }
    });
    jPanel3.add(passwordRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 131, 160, -1));

    dedicacionRegistro.setModel(modeloDedicacion);
    jPanel3.add(dedicacionRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 140, -1));

    jLabel9.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel9.setText("Nacionalidad:");
    jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

    buttonRegistrarse.setBackground(new java.awt.Color(0, 0, 0));
    buttonRegistrarse.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
    buttonRegistrarse.setText("REGISTRARSE");
    buttonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRegistrarseActionPerformed(evt);
        }
    });
    jPanel3.add(buttonRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 110, -1));

    jButton4.setBackground(new java.awt.Color(0, 0, 0));
    jButton4.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton4.setForeground(new java.awt.Color(255, 255, 255));
    jButton4.setText("CANCELAR");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });
    jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 105, -1));

    jLabel13.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel13.setText("Fecha de nacimiento:");
    jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

    nacionalidadRegistro.setModel(modeloNacionalidad);
    jPanel3.add(nacionalidadRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 140, -1));

    jLabel2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel2.setText("Nombre:");
    jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

    jLabel3.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel3.setText("E-mail:");
    jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

    jLabel10.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel10.setText("Cédula:");
    jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

    huertaRegistro.setModel(modeloHuertas);
    jPanel3.add(huertaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 140, -1));

    jLabel14.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel14.setText("Huerta:");
    jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

    jToggleButton1.setText("show");
    jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jToggleButton1ActionPerformed(evt);
        }
    });
    jPanel3.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 60, 20));

    registroDialog.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 400));

    elegirFoto.setAcceptAllFileFilterUsed(false);
    elegirFoto.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    elegirFoto.setBackground(new java.awt.Color(0, 0, 0));
    elegirFoto.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
    elegirFoto.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            elegirFotoActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout dialogFotoLayout = new javax.swing.GroupLayout(dialogFoto.getContentPane());
    dialogFoto.getContentPane().setLayout(dialogFotoLayout);
    dialogFotoLayout.setHorizontalGroup(
        dialogFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dialogFotoLayout.createSequentialGroup()
            .addGap(7, 7, 7)
            .addComponent(elegirFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
            .addGap(7, 7, 7))
    );
    dialogFotoLayout.setVerticalGroup(
        dialogFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dialogFotoLayout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(elegirFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
            .addGap(6, 6, 6))
    );

    registroExitosoD.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    javax.swing.GroupLayout registroExitosoDLayout = new javax.swing.GroupLayout(registroExitosoD.getContentPane());
    registroExitosoD.getContentPane().setLayout(registroExitosoDLayout);
    registroExitosoDLayout.setHorizontalGroup(
        registroExitosoDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 400, Short.MAX_VALUE)
    );
    registroExitosoDLayout.setVerticalGroup(
        registroExitosoDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 300, Short.MAX_VALUE)
    );

    registroFallidoD.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    javax.swing.GroupLayout registroFallidoDLayout = new javax.swing.GroupLayout(registroFallidoD.getContentPane());
    registroFallidoD.getContentPane().setLayout(registroFallidoDLayout);
    registroFallidoDLayout.setHorizontalGroup(
        registroFallidoDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 400, Short.MAX_VALUE)
    );
    registroFallidoDLayout.setVerticalGroup(
        registroFallidoDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 300, Short.MAX_VALUE)
    );

    dialogInvitado.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    dialogInvitado.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    apellidoRegistro1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    dialogInvitado.getContentPane().add(apellidoRegistro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, -1));

    telefonoRegistro1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    dialogInvitado.getContentPane().add(telefonoRegistro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 220, -1));

    jLabel5.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel5.setText("NOMBRE:");
    dialogInvitado.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 20, 220, -1));

    jLabel16.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel16.setText("APELLIDO:");
    dialogInvitado.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 220, -1));

    jLabel15.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel15.setText("NACIONALIDAD:");
    dialogInvitado.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

    nacionalidadRegistro1.setModel(modeloNacionalidad);
    dialogInvitado.getContentPane().add(nacionalidadRegistro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 220, -1));

    jButton6.setBackground(new java.awt.Color(0, 0, 0));
    jButton6.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton6.setForeground(new java.awt.Color(255, 255, 255));
    jButton6.setText("CANCELAR");
    jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton6ActionPerformed(evt);
        }
    });
    dialogInvitado.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 130, 20));

    jButton7.setBackground(new java.awt.Color(0, 0, 0));
    jButton7.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton7.setForeground(new java.awt.Color(255, 255, 255));
    jButton7.setText("ENTRAR");
    jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
        }
    });
    dialogInvitado.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 130, 20));

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("LA HUERTICA");
    setResizable(false);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanel1.setBackground(new java.awt.Color(224, 164, 88));
    jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel11.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel11.setText("Usuario");
    jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 40, -1));

    jLabel12.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel12.setText("Contraseña");
    jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

    passwordInicio.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    passwordInicio.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            passwordInicioActionPerformed(evt);
        }
    });
    jPanel1.add(passwordInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, -1));

    usuarioInicio.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jPanel1.add(usuarioInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 111, -1));

    jButton1.setBackground(new java.awt.Color(0, 0, 0));
    jButton1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton1.setForeground(new java.awt.Color(255, 255, 255));
    jButton1.setText("INVITADO");
    jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });
    jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 130, 20));

    jButton2.setBackground(new java.awt.Color(0, 0, 0));
    jButton2.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton2.setForeground(new java.awt.Color(255, 255, 255));
    jButton2.setText("REGISTRARSE");
    jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });
    jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 130, 20));

    jButton3.setBackground(new java.awt.Color(0, 0, 0));
    jButton3.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton3.setForeground(new java.awt.Color(255, 255, 255));
    jButton3.setText("INICIAR SESIÓN");
    jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });
    jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 130, 20));

    getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 150, 240));

    jPanel4.setBackground(new java.awt.Color(46, 53, 50));

    Logo.setBackground(new java.awt.Color(0, 0, 0));

    logoFoto.setText("jLabel17");

    javax.swing.GroupLayout LogoLayout = new javax.swing.GroupLayout(Logo);
    Logo.setLayout(LogoLayout);
    LogoLayout.setHorizontalGroup(
        LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(logoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    LogoLayout.setVerticalGroup(
        LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(logoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    buttonReady.setBackground(new java.awt.Color(0, 0, 0));
    buttonReady.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonReady.setForeground(new java.awt.Color(255, 255, 255));
    buttonReady.setText("BYE");
    buttonReady.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonReady.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonReadyActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(buttonReady, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(buttonReady)
            .addContainerGap(24, Short.MAX_VALUE))
    );

    getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 240));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
        try {
            prepareRegistro();
            registroDialog.setSize(680, 425);
            registroDialog.setLocationRelativeTo(null);
            registroDialog.setVisible(true);
            
            setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(inicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed


    private void buttonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarseActionPerformed
        if (!revisarCamposRegistro()) {
            return;
        }
        try {
            String nom = "{call paqPersona.crearPersona(?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement crearPersona = conexion.prepareCall(nom);
            crearPersona.setInt(1, Integer.parseInt(cedulaRegistro.getText()));
            crearPersona.setString(2, nombreRegistro.getText());
            crearPersona.setString(3, apellidoRegistro.getText());
            crearPersona.setString(4, emailRegistro.getText());
            crearPersona.setInt(5, Integer.parseInt(telefonoRegistro.getText()));

            //Se cambia el formato de la fecha
            String input = fechaRegistro.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date dt = null;
            dt = sdf.parse(input);
            java.sql.Date dtSql = new java.sql.Date(dt.getTime());

            crearPersona.setDate(6, dtSql);

            int nacionalidad = getNacionalidad();
            crearPersona.setInt(7, nacionalidad);

            //Se convierte a blob
            
            File blob2 = new File(imagenUsuario.getAbsolutePath());
            FileInputStream in = new FileInputStream(blob2);

            crearPersona.setBinaryStream(8, in, (int)blob2.length());

            int huerta = getHuerta();
            crearPersona.setInt(9, huerta);

            int dedicacion = getDedicacion();
            crearPersona.setInt(10, dedicacion);

            crearPersona.setString(11, usuarioRegistro.getText());

            crearPersona.setString(12, codificarPass(String.valueOf(passwordRegistro.getPassword())));
            crearPersona.execute();
        } catch (SQLException | ParseException | IOException ex) {
            Logger.getLogger(inicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }

        setEnabled(true);
        setVisible(true);
        registroDialog.dispose();
    }//GEN-LAST:event_buttonRegistrarseActionPerformed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        prepareInicio();
        setEnabled(true);
        setVisible(true);
        registroDialog.dispose();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
//        principal p = new principal();
//        p.setVisible(true);
            modeloNacionalidad.removeAllElements();
            idNacionalidad.clear();
            nombreNacionalidad.clear();
            dialogInvitado.setSize(274, 272);
            dialogInvitado.setLocationRelativeTo(null);
            dialogInvitado.setVisible(true);
            setVisible(false);
            String nom2 = "{? =call paqUtilidades.paisInfo()}";
            CallableStatement cstmt2 = conexion.prepareCall(nom2);
            cstmt2.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt2.execute();
            ResultSet cursor2 = (ResultSet) cstmt2.getObject(1);
            while (cursor2.next()) {
                for (int i = 1; i < 3; i++) {
                    if (i == 1) {
                        idNacionalidad.add(cursor2.getInt(i));
                    } else if (i == 2) {
                        nombreNacionalidad.add(cursor2.getString(i));
                    }
                }
            }
            for (String nacionalidad : nombreNacionalidad) {
                modeloNacionalidad.addElement(nacionalidad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void passwordInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordInicioActionPerformed

    }//GEN-LAST:event_passwordInicioActionPerformed

    private void nombreRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreRegistroActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //dialogFoto.setSize(670, 400);
        //dialogFoto.setVisible(true);   .
        elegirFoto.showOpenDialog(null);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void buttonReadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReadyActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonReadyActionPerformed

    private void elegirFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegirFotoActionPerformed
        int result = elegirFoto.showOpenDialog(null);
        imagenUsuario = elegirFoto.getSelectedFile();

        ImageIcon imgThisImg = new ImageIcon(imagenUsuario.getAbsolutePath());
        Image scaleImage = imgThisImg.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT);
        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);

        ojala.setSize(180, 170);
        ojala.setIcon(imgThisImg2);

        System.out.println(elegirFoto.getSelectedFile());
        try {
            System.out.println(convertToBlob(elegirFoto.getSelectedFile().getAbsolutePath()));
        } catch (IOException ex) {
            Logger.getLogger(inicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result == JFileChooser.APPROVE_OPTION) {
            dialogFoto.dispose();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            dialogFoto.dispose();
        }
        //elegirFoto.cancelSelection();
    }//GEN-LAST:event_elegirFotoActionPerformed

    private void cedulaRegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaRegistroKeyTyped
        char c = evt.getKeyChar();
        if(!Character.isDigit(c) && (int)c != 8){	
            evt.consume();
            //getToolkit.beep();
            JOptionPane.showMessageDialog(this, "Inserte solo NUMEROS", "ERROR", WIDTH);
            
        }
    }//GEN-LAST:event_cedulaRegistroKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!revisarCamposInicio()) {
            return;
        }
        try {
            
            String nom = "{call paqUsuario.accesoCuenta(?,?,?)}";
            CallableStatement crearUsuario = conexion.prepareCall(nom);
            crearUsuario.setString(1, usuarioInicio.getText());
            crearUsuario.setString(2, codificarPass(String.valueOf(passwordInicio.getPassword())));
            crearUsuario.registerOutParameter(3, OracleTypes.INTEGER);
            crearUsuario.execute();
            if (crearUsuario.getInt(3) == 1) {
                String nom2 = "{call paqUtilidades.personaUser(?,?)}";
                CallableStatement cstmt = conexion.prepareCall(nom2);
                cstmt.setString(1, usuarioInicio.getText());
                cstmt.registerOutParameter(2, OracleTypes.CURSOR);
                cstmt.execute();
                ResultSet cursor = (ResultSet) cstmt.getObject(2);
                cursor.next();
                principal p = new principal();
                dispose();
                p.cambiarVariable(cursor.getInt(2), cursor.getInt(1), usuarioInicio.getText());
                p.setVisible(true);
                p.setSize(985, 649);
            }                
        } catch (SQLException ex) {
            Logger.getLogger(inicioSesion.class.getName()).log(Level.SEVERE, null, ex);
            //CONTRASEÑA INCORRECTA
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dialogInvitado.setVisible(false);
        setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    public int getPais(){
        int posSeleccionado = nacionalidadRegistro1.getSelectedIndex();
        int id = (Integer) idNacionalidad.get(posSeleccionado);
        return id;
    }
    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String nom = "{call paqPersona.crearVisitante(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setString(1, apellidoRegistro1.getText());
            cstmt.setString(2, telefonoRegistro1.getText());
            cstmt.setInt(3, getPais());
            cstmt.execute();
            dialogInvitado.dispose();
            nom = "{call paqUtilidades.personaGuest(?)}";
            CallableStatement cstmt2 = conexion.prepareCall(nom);
            cstmt2.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt2.execute();
            principal p = new principal();
            p.cambiarVariable(0, cstmt2.getInt(1), "Guest");
            p.setVisible(true);
            p.setSize(985, 649);
        } catch (SQLException ex) {
            Logger.getLogger(inicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void telefonoRegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoRegistroKeyTyped
        char c = evt.getKeyChar();
        if(!Character.isDigit(c) && (int)c != 8){	
            evt.consume();
            //getToolkit.beep();
            JOptionPane.showMessageDialog(this, "Inserte solo NUMEROS", "ERROR", WIDTH);
            
        }
    }//GEN-LAST:event_telefonoRegistroKeyTyped

    private void nombreRegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreRegistroKeyTyped
        char c = evt.getKeyChar();
        if(Character.isDigit(c)){	
                evt.consume();
                JOptionPane.showMessageDialog(this, "Inserte solo LETRAS", "ERROR", WIDTH);
        } else if ((int)c > 32 && (int)c <=47 && (int)c != 8){
                evt.consume();
                JOptionPane.showMessageDialog(this, "Inserte solo LETRAS", "ERROR", WIDTH);
        }
    }//GEN-LAST:event_nombreRegistroKeyTyped

    private void apellidoRegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoRegistroKeyTyped
        char c = evt.getKeyChar();
        if(Character.isDigit(c)){	
                evt.consume();
                JOptionPane.showMessageDialog(this, "Inserte solo LETRAS", "ERROR", WIDTH);
        } else if ((int)c > 32 && (int)c <=47 && (int)c != 8){
                evt.consume();
                JOptionPane.showMessageDialog(this, "Inserte solo LETRAS", "ERROR", WIDTH);
        }
    }//GEN-LAST:event_apellidoRegistroKeyTyped

    private void usuarioRegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioRegistroKeyTyped
        char c = evt.getKeyChar();
        if((int)c == 32){	
            evt.consume();
            JOptionPane.showMessageDialog(this, "No se permiten espacios", "ERROR", WIDTH);
        }
    }//GEN-LAST:event_usuarioRegistroKeyTyped

    private void emailRegistroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailRegistroKeyPressed
        char c = evt.getKeyChar();
        if((int)c == 32){	
            evt.consume();
            JOptionPane.showMessageDialog(this, "No se permiten espacios", "ERROR", WIDTH);
        }
    }//GEN-LAST:event_emailRegistroKeyPressed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        char i = passwordRegistro.getEchoChar();
        if (a) {  // a es una variable boolean en true
            passwordRegistro.setEchoChar((char)0); // este método es el que hace visible el texto del jPasswordField
            a = false;
        } else {
            passwordRegistro.setEchoChar(i); // i es el char
            a = true;
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void passwordRegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordRegistroKeyTyped
//        if (evt.get.getStateChange() == ItemEvent.SELECTED) {
//            passwordRegistro.setEchoChar('x'); 
//        } else {  
//            passwordRegistro.setEchoChar((char) 0);  
//        }
    }//GEN-LAST:event_passwordRegistroKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Logo;
    private javax.swing.JTextField apellidoRegistro;
    private javax.swing.JTextField apellidoRegistro1;
    private javax.swing.JButton buttonReady;
    private javax.swing.JButton buttonRegistrarse;
    private javax.swing.JTextField cedulaRegistro;
    private javax.swing.JComboBox<String> dedicacionRegistro;
    private javax.swing.JDialog dialogFoto;
    private javax.swing.JDialog dialogInvitado;
    private javax.swing.JFileChooser elegirFoto;
    private javax.swing.JTextField emailRegistro;
    private datechooser.beans.DateChooserCombo fechaRegistro;
    private javax.swing.JComboBox<String> huertaRegistro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel logoFoto;
    private javax.swing.JComboBox<String> nacionalidadRegistro;
    private javax.swing.JComboBox<String> nacionalidadRegistro1;
    private javax.swing.JTextField nombreRegistro;
    private javax.swing.JLabel ojala;
    private javax.swing.JPasswordField passwordInicio;
    private javax.swing.JPasswordField passwordRegistro;
    private javax.swing.JDialog registroDialog;
    private javax.swing.JDialog registroExitosoD;
    private javax.swing.JDialog registroFallidoD;
    private javax.swing.JTextField telefonoRegistro;
    private javax.swing.JTextField telefonoRegistro1;
    private javax.swing.JTextField usuarioInicio;
    private javax.swing.JTextField usuarioRegistro;
    // End of variables declaration//GEN-END:variables
}
