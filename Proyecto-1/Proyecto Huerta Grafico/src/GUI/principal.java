package GUI;

import static GUI.inicioSesion.convertToBlob;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import oracle.jdbc.OracleTypes;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ComboBoxModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Caty
 */
public class principal extends javax.swing.JFrame {

    //VARIABLES GLOBALES
    int tipoUsuario;
    int idUser;
    int tipoTrueque = 0;
    int tipoVenta = 0;
    int idUsuario = 0;
    int idTrueque = 0;
    int idVenta = 0;
    int tipoAbono = 0;
    int idHuerta;
    int idPlantaI;
    int estadoCatalogo = 0;
    String usuario;
    File imagenUsuario = null;
    private Connection conexion;
    private int catalogo;
    
    int tipoAdHuerta = 0;
    int tipoAdCatalogo = 0;
    int tipoAdUsuario = 0;
    int tipoAdmHortaliza = 0;
    int tipoAdArbol = 0;
    int tipoAdAbono = 0;
    int tipoConsulta = 0;

    //PARA GOOGLE MAPS
    Browser browser = new Browser();
    BrowserView view = new BrowserView(browser);
    String urlBase = "http://maps.google.com/maps?q=";
    //MODELOS DE TABLAS
    DefaultTableModel modeloBitacora = new DefaultTableModel();
    DefaultTableModel modeloAudUsuarios = new DefaultTableModel();
    DefaultTableModel modeloAudGeneral = new DefaultTableModel();
    DefaultTableModel modeloAudHuertas = new DefaultTableModel();
    DefaultTableModel modeloConsultas = new DefaultTableModel();

    //MODELOS DE LISTAS    
    DefaultListModel modeloHortalizas = new DefaultListModel();
    DefaultListModel modeloArboles = new DefaultListModel();
   

    //MODELOS DE COMBOBOX        
//Trueques y Ventas    
    DefaultComboBoxModel modeloTruequesP1 = new DefaultComboBoxModel(); //trueque planta 1
    DefaultComboBoxModel modeloTruequesP2 = new DefaultComboBoxModel(); //trueque planta 2
    DefaultComboBoxModel modeloVentas = new DefaultComboBoxModel();
//Catalogos    
    DefaultComboBoxModel modeloColor = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloTipo = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloPropiedad = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloCaracteristica = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloReproduccion = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloXilema = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloCambium = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloCorteza = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloPais = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloProvincia = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloCanton = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloDistrito = new DefaultComboBoxModel();
//Combo box generales    
    DefaultComboBoxModel modeloHuertas = new DefaultComboBoxModel(); //huertas box  
    DefaultComboBoxModel modeloHortalizasBox = new DefaultComboBoxModel(); //hortalizas box   
    DefaultComboBoxModel modeloArbolesBox = new DefaultComboBoxModel(); //arboles box
    DefaultComboBoxModel modeloNacionalidad = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloDedicacion = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloUsuarios = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloAbonosBox = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloPlantas= new DefaultComboBoxModel();
    DefaultComboBoxModel modeloLocacion = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloConsultasBox = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloInvitado = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloEstadistica = new DefaultComboBoxModel();
    //ARRAYS PARA ID's Y NOMBRES
    
    ArrayList<Object> idHortalizaHuerta = new ArrayList(); //HUERTAS
    ArrayList<String> nombreHortalizaHuerta = new ArrayList();
    
    ArrayList<Object> idArbolHuerta = new ArrayList(); //HUERTAS
    ArrayList<String> nombreArbolHuerta = new ArrayList();
//Huertas    
    ArrayList<Object> idHuertas = new ArrayList(); //HUERTAS
    ArrayList<String> nombreHuertas = new ArrayList();
//Hortalizas
    ArrayList<Object> idHortalizas = new ArrayList(); //HORTALIZAS
    ArrayList<String> nombreHortalizas = new ArrayList();
//Arboles    
    ArrayList<Object> idArboles = new ArrayList(); //ÁRBOLES
    ArrayList<String> nombreArboles = new ArrayList();
//Trueques    
    ArrayList<Object> idTruequesP1 = new ArrayList(); //TRUEQUES PLANTA 1
    ArrayList<String> nombreTruequesP1 = new ArrayList();
    ArrayList<Object> idTruequesP2 = new ArrayList(); //TRUEQUES PLANTA 2
    ArrayList<String> nombreTruequesP2 = new ArrayList();
//Ventas   
    ArrayList<Object> idVentasP = new ArrayList(); //VENTAS HUERTAS
    ArrayList<String> nombreVentasP = new ArrayList();
//Catalogos    
    ArrayList<Object> idColor = new ArrayList(); //COLOR
    ArrayList<String> nombreColor = new ArrayList();
    ArrayList<Object> idTipo = new ArrayList(); //TIPO
    ArrayList<String> nombreTipo = new ArrayList();
    ArrayList<Object> idPropiedad = new ArrayList(); //PROPIEDAD
    ArrayList<String> nombrePropiedad = new ArrayList();
    ArrayList<Object> idCaracteristica = new ArrayList(); //CARACTERISTICA
    ArrayList<String> nombreCaracteristica = new ArrayList();
    ArrayList<Object> idReproduccion = new ArrayList(); //REPRODUCCION
    ArrayList<String> nombreReproduccion = new ArrayList();
    ArrayList<Object> idXilema = new ArrayList(); //XILEMA
    ArrayList<String> nombreXilema = new ArrayList();
    ArrayList<Object> idCambium = new ArrayList(); //CAMBIUM
    ArrayList<String> nombreCambium = new ArrayList();
    ArrayList<Object> idCorteza = new ArrayList(); //CORTEZA
    ArrayList<String> nombreCorteza = new ArrayList();
    ArrayList<Object> idPais = new ArrayList(); //PAIS
    ArrayList<String> nombrePais = new ArrayList();
    ArrayList<Object> idProvincia = new ArrayList(); //PROVINCIA
    ArrayList<String> nombreProvincia = new ArrayList();
    ArrayList<Object> idCanton = new ArrayList(); //CANTON
    ArrayList<String> nombreCanton = new ArrayList();
    ArrayList<Object> idDistrito = new ArrayList(); //DISTRITO
    ArrayList<String> nombreDistrito = new ArrayList();
//Usuario
    ArrayList<Object> idUsuarioArray = new ArrayList();
    ArrayList<String> nombreUsuarioArray = new ArrayList();
    ArrayList<Object> idNacionalidad = new ArrayList();
    ArrayList<String> nombreNacionalidad = new ArrayList();
    ArrayList<Object> idDedicacion = new ArrayList();
    ArrayList<String> nombreDedicacion = new ArrayList();
    ArrayList<Object> idAbono = new ArrayList();
    ArrayList<String> nombreAbono = new ArrayList();
    ArrayList<Object> idPlanta = new ArrayList();
    ArrayList<String> nombrePlanta = new ArrayList();
    /**
     * Creates new form principal
     */
    public principal() {
        initComponents();
        setSize(985, 649);
        setLocationRelativeTo(null);
        ImageIcon imgThisImg = new ImageIcon("src/Images/LogoHuertica.jpeg");
        Image scaleImage = imgThisImg.getImage().getScaledInstance(588, 414, Image.SCALE_DEFAULT);
        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);
        logoInicio.setIcon(imgThisImg2);
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
        //SE PONE LA VENTANA DE INICIO
        panelPrincipal.removeAll();
        panelPrincipal.add(panelInicio, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();

        //SE DEFINEN LOS NOMBRES DE LAS COLUMNAS DE AUDITORÍAS Y BITÁCORA DE ABONOS
        String audGeneral[] = {"NOMBRE AUDITORÍA", "FECHA CREACIÓN", "CREADO POR", "ACCIÓN"};
        String audUsuarios[] = {"NOMBRE", "CREADO POR", "FECHA CREACIÓN", "MODIFICADO POR", "FECHA MODIFICACIÓN"};
        String audHuertas[] = {"NOMBRE", "CREADO POR", "FECHA CREACIÓN", "MODIFICADO POR", "FECHA MODIFICACIÓN"};
        String bitacora[] = {"ABONO", "PLANTA", "FECHA", "PERSONA"};
        modeloAudGeneral.setColumnIdentifiers(audGeneral);
        modeloAudHuertas.setColumnIdentifiers(audHuertas);
        modeloAudUsuarios.setColumnIdentifiers(audUsuarios);
        modeloBitacora.setColumnIdentifiers(bitacora);

        //Otras cosas
        lugarCatalogo.setVisible(false);
        jLabel76.setVisible(false);
        prepareConsultas();
        jCheckBox1.setEnabled(false);
    }

//-----------------------------------CONEXIÓN CON BASE DE DATOS
//        public static void main(String[] args) throws SQLException, SQLException, SQLException {
//            principal conexion = new principal();
//            conexion.Conectar();
//        }
//-----------------------------------PREPARES
    //CARGAR DATOS DE VENTANA PARA HUERTAS
    
    public void prepareConsultas(){
        modeloConsultasBox.addElement("Listado de contactos");
        modeloConsultasBox.addElement("Listado de contactos de interesados");
        modeloConsultasBox.addElement("Listado de plantas no abonadas");
        modeloConsultasBox.addElement("Listado de usuarios");
        modeloConsultasBox.addElement("Listado de hortalizas por color");
        modeloConsultasBox.addElement("Listado de hortalizas por clasificacion");
        modeloConsultasBox.addElement("Historico de abonos");
        modeloConsultasBox.addElement("Listado de ventas");
        modeloConsultasBox.addElement("Listado de trueques");
        modeloConsultasBox.addElement("Dias mayores trueques");
        modeloConsultasBox.addElement("Dias mayores ventas");
        modeloInvitado.addElement("HORTALIZA");
        modeloInvitado.addElement("ARBOL");
        modeloEstadistica.addElement("Personas por edad");
        modeloEstadistica.addElement( "Hortalizas por color");
        modeloEstadistica.addElement( "Hortalizas por tipo Consumible");
        modeloEstadistica.addElement( "Arbol por tipo Reproducción");
        modeloEstadistica.addElement( "Arbol por tipo Xilema");
        modeloEstadistica.addElement("Arbol por tipo Cambium");
        modeloEstadistica.addElement("Arbol por tipo Corteza");
        modeloEstadistica.addElement("Trueques por año");
        modeloEstadistica.addElement("Ventas por año");
        modeloEstadistica.addElement("Top Trueques");
        modeloEstadistica.addElement("Top ventas hortaliza");
        modeloEstadistica.addElement( "Top ventas arbol");
    }
    
    public void cambiarVariable(int i, int j, String us) throws SQLException{
        tipoUsuario = i;
        idUser = j;
        jToggleButton1.setVisible(false);
        if(tipoUsuario == 0){
            buttonCrearHortaliza.setVisible(false);
            buttonCrearArbol.setVisible(false);
            truequeP1.setVisible(false);
            jToggleButton1.setVisible(true);
            buttonOpciones.setVisible(false);
        }
        if(tipoUsuario > 1){
            buttonOpciones.setVisible(false);
        }
        String nom = "{call paqUtilidades.personaHuertaid(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idUser);
        cstmt.registerOutParameter(2, OracleTypes.INTEGER);
        cstmt.execute();
        idHuerta = cstmt.getInt(2);
        usuario = us;
        jLabel57.setText(usuario);
    }
    
    public void prepareHuertas() throws SQLException {
        //SE VACÍAN LOS CAMPOS
        huertasNombre.setText("...");
        labelSldCalificacion.setText("...");

        //SE CARGA MAPS
        panelMap.add(view, BorderLayout.CENTER);    //CARGAR TODAS LAS HUERTAS!!!!!!!!!!!!!!!!!
        panelMap.setVisible(true);              //CARGAR TODAS LAS HUERTAS!!!!!!!!!!!!!!!!!
        panelMap.revalidate();                  //CARGAR TODAS LAS HUERTAS!!!!!!!!!!!!!!!!!
        panelMap.repaint();
        browser.loadURL("http://maps.google.com/");

        //SE OBTIENE EL ID Y EL NOMBRE DE LAS HUERTAS
        //SE CARGAN LOS ARRAY Y MODEL PARA LA LISTA
        idHuertas.clear();
        idHortalizas.clear();
        idArboles.clear();
        nombreHuertas.clear();
        nombreHortalizas.clear();
        nombreArboles.clear();
        modeloHuertas.removeAllElements();
        modeloHortalizasBox.removeAllElements();
        modeloArbolesBox.removeAllElements();

        
        String nom = "{? = call paqUtilidades.huertaInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idHuertas.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreHuertas.add(cursor.getString(i));
                }
            }
        }

        for (String huerta : nombreHuertas) {
            modeloHuertas.addElement(huerta);
        }               
    }

    //CARGAR DATOS DE VENTANA PARA HORTALIZAS
    public void prepareHortalizas() throws SQLException {
        //SE VACÍAN LOS CAMPOS
        hortalizasNombre.setText("...");
        hortalizasTipo.setText("...");
        hortalizasColor.setText("...");
        hortalizasCaracteristica.setText("...");
        hortalizasCaracteristica.setText("...");
        hortalizasPrecio.setText("...");
        hortalizasCantidad.setText("...");

        //SE OBTIENE EL ID Y EL NOMBRE DE LAS HORTALIZAS
        //SE CARGAN LOS ARRAY Y MODEL PARA LA LISTA
        idHortalizas.clear();
        nombreHortalizas.clear();
        idHuertas.clear();
        nombreHuertas.clear();
        modeloHortalizas.removeAllElements();
        modeloHuertas.removeAllElements();
        
//        String nom = "{? = call paqUtilidades.hortalizaInfo()}";
//        CallableStatement cstmt = conexion.prepareCall(nom);
//        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
//        cstmt.execute();
//        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//        while (cursor.next()) {
//            for (int i = 1; i < 3; i++) {
//                System.out.println("Name = " + cursor.getString(i));
//                if (i == 1) {
//                    idHortalizas.add(cursor.getInt(i));
//                } else if (i == 2) {
//                    nombreHortalizas.add(cursor.getString(i));
//                }
//            }
//        }
        
        String nom2 = "{? = call paqUtilidades.huertaInfo()}";
        CallableStatement cstmt2 = conexion.prepareCall(nom2);
        cstmt2.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt2.execute();
        ResultSet cursor2 = (ResultSet) cstmt2.getObject(1);
        while (cursor2.next()) {
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    idHuertas.add(cursor2.getInt(i));
                } else if (i == 2) {
                    nombreHuertas.add(cursor2.getString(i));
                }
            }
        }

//        for (String hortaliza : nombreHortalizas) {
//            modeloHortalizas.addElement(hortaliza);
//        }
        for (String huerta : nombreHuertas) {
            modeloHuertas.addElement(huerta);
        }
    }

    //CARGAR DATOS DE VENTANA PARA ÁRBOLES
    public void prepareArboles() throws SQLException {
        //SE OBTIENE EL ID Y EL NOMBRE DE LAS HORTALIZAS
        //SE CARGAN LOS ARRAY Y MODEL PARA LA LISTA
        idArboles.clear();
        nombreArboles.clear();
        idHuertas.clear();
        nombreHuertas.clear();
        modeloArboles.removeAllElements();
        modeloHuertas.removeAllElements();
//        String nom = "{? = call paqUtilidades.arbolInfo()}";
//        CallableStatement cstmt = conexion.prepareCall(nom);
//        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
//        cstmt.execute();
//        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//        while (cursor.next()) {
//            for (int i = 1; i < 2; i++) {
//                System.out.println("Name = " + cursor.getString(i));
//                if (i == 1) {
//                    idArboles.add(cursor.getInt(i));
//                } else if (i == 2) {
//                    nombreArboles.add(cursor.getString(i));
//                }
//            }
//        }
        
        String nom2 = "{? = call paqUtilidades.huertaInfo()}";
        CallableStatement cstmt2 = conexion.prepareCall(nom2);
        cstmt2.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt2.execute();
        ResultSet cursor2 = (ResultSet) cstmt2.getObject(1);
        while (cursor2.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor2.getString(i));
                if (i == 1) {
                    idHuertas.add(cursor2.getInt(i));
                } else if (i == 2) {
                    nombreHuertas.add(cursor2.getString(i));
                }
            }
        }

//        for (String arbol : nombreArboles) {
//            modeloArboles.addElement(arbol);
//        }
        for (String huerta : nombreHuertas) {
            modeloHuertas.addElement(huerta);
        }
    }

    //CARGAR DATOS DE VENTANA PARA TRUEQUES
    public void prepareTrueques() throws SQLException {
        //VACIAMOS LOS ARRAY Y MODEL DE COMBOBOX
        idHuertas.clear();
        nombreHuertas.clear();
        idTruequesP1.clear();
        nombreTruequesP1.clear();
        idTruequesP2.clear();
        nombreTruequesP2.clear();
        modeloHuertas.removeAllElements();
        modeloTruequesP1.removeAllElements();
        modeloTruequesP2.removeAllElements();

        //SE OBTIENE EL ID Y EL NOMBRE DE LAS HUERTAS
        //SE CARGAN LOS ARRAY Y MODEL PARA LA LISTA        
        String nom = "{? = call paqUtilidades.huertaInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idHuertas.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreHuertas.add(cursor.getString(i));
                }
            }
        }

        for (String huerta : nombreHuertas) {
            modeloHuertas.addElement(huerta);
        }
        //SE SETEAN LOS BOTONES Y COMBOBOX DE TRUEQUE
        buttonHH.setVisible(true);
        buttonHA.setVisible(true);
        buttonAH.setVisible(true);
        buttonAA.setVisible(true);
        buttonHH.setEnabled(true);
        buttonHA.setEnabled(true);
        buttonAH.setEnabled(true);
        buttonAA.setEnabled(true);
        truequeHuertas.setEnabled(true);
        truequeP1.setEnabled(false);
        truequeP2.setEnabled(false);
        buttonRealizarTrueque.setVisible(false);
        buttonCancelarTrueque.setVisible(false);

        //SE SETEAN LOS BOTONES DEL MENÚ PRINCIPAL
        buttonInicio.setEnabled(true);
        buttonHuertas.setEnabled(true);
        buttonHortalizas.setEnabled(true);
        buttonArboles.setEnabled(true);
        buttonTrueques.setEnabled(true);
        buttonVentas.setEnabled(true);
        buttonOpciones.setEnabled(true);
        buttonCerrarSesion.setEnabled(true);

        //SE SETEA TIPO DE TRUEQUE
        tipoTrueque = 0;

    }

    //CARGAR DATOS DE VENTANA PARA VENTAS
    public void prepareVentas() {
        //VACIAMOS LOS ARRAY Y MODEL DE COMBOBOX
        idVentasP.clear();
        nombreVentasP.clear();
        modeloVentas.removeAllElements();

        //SE SETEAN LOS BOTONES Y COMBOBOX DE VENTA
        buttonVentaH.setVisible(true);
        buttonVentaA.setVisible(true);
        buttonVentaH.setEnabled(true);
        buttonVentaA.setEnabled(true);
        ventaPlantas.setEnabled(false);
        buttonRealizarVenta.setVisible(false);
        buttonCancelarVenta.setVisible(false);

        //SE SETEAN LOS BOTONES DEL MENÚ PRINCIPAL
        buttonInicio.setEnabled(true);
        buttonHuertas.setEnabled(true);
        buttonHortalizas.setEnabled(true);
        buttonArboles.setEnabled(true);
        buttonTrueques.setEnabled(true);
        buttonVentas.setEnabled(true);
        buttonOpciones.setEnabled(true);
        buttonCerrarSesion.setEnabled(true);
        //SE SETEA TIPO DE VENTA
        tipoVenta = 0;
    }

    public void prepareEstadisticas() {

    }

    public void prepareBitacora() throws SQLException {

    }

    public void prepareAuditorias() {
        panelAuditorias.removeAll();
        panelAudGeneral.setSize(548, 470);
        panelAuditorias.revalidate();
        panelAuditorias.repaint();
    }

    public void prepareAdDatos() {

    }

    public void prepareAdCatalogos() {
        //SE COLOCAN LOS COSOS EN EL MODELO Y LUEGO VERIFICAMOS EL ESTADO DEL PRIMER ELEMENTO PARA CAMBIAR EL NOMBRE DEL BOTON DE DESACTIVAR O ACTIVAR
	
        //COLOR
	//Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//
//        //TIPO
//	//Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar1.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor1.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar1.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor1.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //PROPIEDAD
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar2.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor2.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar2.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor2.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //CARACTERISTICA
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar3.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor3.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar3.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor3.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //REPRODUCCION
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar4.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor4.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar4.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor4.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //XILEMA
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar5.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor5.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar5.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor5.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //CAMBIUM
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar6.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor6.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar6.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor6.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //CORTEZA
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar7.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor7.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar7.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor7.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //PAIS
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar8.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor8.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar8.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor8.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //PROVINCIA
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar9.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor9.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar9.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor9.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //CANTON
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar10.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor10.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar10.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor10.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }
//        
//        //DISTRITO
//        //Estado activado = 1
//	if (getEstadoCatalogo(colorAdministrar11.getSelectedItem()) == 1){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor11.setText("Activar");
//		estadoCatalogo = 1; //Cambio la variable global
//        }
//	//Estado desactivado = 2
//	if (getEstadoCatalogo(colorAdministrar11.getSelectedItem()) == 2){ //AQUI DEBE VERIFICARSE EL ESTADO DEL CATALOGO (FUNCION getEstadoCatalogo inventada)
//            	buttonDesactivarColor11.setText("Desactivar");  //Se cambia el nombre del boton
//		estadoCatalogo = 2;
//        }

    }

    public void prepareAdUsuarios() {

    }

    public void prepareAdHortalizas() throws SQLException {
        cargarInfoHuertaBox();
        cargarInfoColor();
        cargarInfoTipo();
        cargarInfoPropiedad();
        cargarInfoCaracteristica();
    }

    public void prepareAdArboles() throws SQLException {
        cargarInfoHuertaBox();
        cargarInfoReproduccion();
        cargarInfoCambium();
        cargarInfoCorteza();
        cargarInfoXilema();
    }
    
    public void prepareAdHuertas() throws SQLException {
        nombreAdHuerta.setText("");
        latitudAdHuerta.setText("");
        longitudAdHuerta.setText("");
        capitalAdHuerta.setText("");
        nombreAdHuerta.setEnabled(false);
        latitudAdHuerta.setEnabled(false);
        longitudAdHuerta.setEnabled(false);
        capitalAdHuerta.setEnabled(false);
        distritoAdHuerta.setEnabled(false);        
        jLabel48.setVisible(false);
        capitalAdHuerta.setVisible(false);
        jTextArea1.setVisible(false);
        jScrollPane11.setVisible(false);
        buttonAceptarAdHuerta.setVisible(false);
        buttonCancelarAdHuerta.setVisible(false);
        huertasAdministrar.setVisible(true);
        huertasAdministrar.setEnabled(true);
        buttonVerAdHuerta.setVisible(true);
        buttonCambiarAdHuerta.setVisible(false);
        buttonModificarAdHuerta.setVisible(true);
        buttonModificarAdHuerta.setEnabled(false);
        buttonCrearAdHuerta.setVisible(true);
	buttonCrearAdHuerta.setEnabled(true);
	tipoAdHuerta = 0;
        
        panelMap1.add(view, BorderLayout.CENTER);    
        panelMap1.setVisible(true);              
        panelMap1.revalidate();                  
        panelMap1.repaint();
        browser.loadURL("http://maps.google.com/");
    }

    public void prepareComentarios() {
        comentariosHuerta.setText("");
        calificacionUsuario.setText("...");
        comentarioUsuario.setText("");

        //CARGAR LOS COMENTARIOS
    }

    public void prepareDialogCatalogos() {

    }

//-----------------------------------CARGAR DATOS
    //CARGAR DATOS DE HUERTA EN VENTANA HUERTAS
    public void cargarInfoHuerta(int idHuerta) throws SQLException {
        String url = "";
        url += urlBase;

        String nom = "{call paqUtilidades.huertaInfoGeneral(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idHuerta);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);

        //1 = nombre
        //2 = latitud
        //3 = longitud
        //4 = id_distrito
        //5 = capital
        while (cursor.next()) {
            for (int i = 1; i < 6; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    huertasNombre.setText(cursor.getString(i));
                } else if (i == 2) {
                    url += cursor.getString(i) + ",";
                } else if (i == 3) {
                    url += cursor.getString(i);
                }
            }
        }
        browser.loadURL(url);              

        nom = "{call paqHuerta.getPromedioHuerta(?,?)}";
        CallableStatement cstmt2 = conexion.prepareCall(nom);
        cstmt2.setInt(1, idHuerta);
        cstmt2.registerOutParameter(2, OracleTypes.INTEGER);
        cstmt2.execute();        
        sliderCalificacion.setEnabled(false);
        sliderCalificacion.setValue(cstmt2.getInt(2));
        labelSldCalificacion.setText(String.valueOf(sliderCalificacion.getValue()));
        modeloHortalizasBox.removeAllElements();
        
        nom = "{call paqUtilidades.hortalizasHuerta(?,?)}";
        CallableStatement cstmt3 = conexion.prepareCall(nom);
        cstmt3.setInt(1, getHuerta());
        cstmt3.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt3.execute();
        ResultSet cursor2 = (ResultSet) cstmt3.getObject(2);
        while(cursor2.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idHortalizas.add(cursor2.getInt(i));
                } else if (i == 2) {
                    nombreHortalizas.add(cursor2.getString(i));
                }
            }
        }
        
        nom = "{call paqUtilidades.arbolHuerta(?,?)}";
        CallableStatement cstmt4 = conexion.prepareCall(nom);
        cstmt4.setInt(1, getHuerta());
        cstmt4.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt4.execute();
        ResultSet cursor3 = (ResultSet) cstmt4.getObject(2);
        while(cursor3.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idArboles.add(cursor3.getInt(i));
                } else if (i == 2) {
                    nombreArboles.add(cursor3.getString(i));
                }
            }
        }
        
        for (String arboles : nombreArboles) {
            modeloArbolesBox.addElement(arboles);
        }
        
        for (String hortaliza : nombreHortalizas) {
            modeloHortalizasBox.addElement(hortaliza);
        }
        
        for (String huerta : nombreHuertas) {
            modeloHuertas.addElement(huerta);
        }
    }
    //CARGAR DATOS DE HORTALIZA EN VENTANA HORTALIZAS
    public void cargarInfoHortaliza(int idHortaliza) throws SQLException, FileNotFoundException, IOException {
        System.out.println(idHortaliza);
        String nom = "{call paqUtilidades.hortalizaInfoGeneral(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idHortaliza);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);

        //1 = nombre
        //2 = precio
        //3 = color
        //4 = tipo
        //5 = propiedad
        //6 = caracteristica
        //7 = cantidad
        //8 = foto
        while (cursor.next()) {
            for (int i = 1; i < 9; i++) {
                switch (i) {
                    case 1:
                        hortalizasNombre.setText(cursor.getString(i));
                        break;
                    case 2:
                        hortalizasColor.setText(cursor.getString(i));
                        break;
                    case 3:
                        hortalizasTipo.setText(cursor.getString(i));
                        
                        break;
                    case 4:
                        hortalizasPropiedad.setText(cursor.getString(i));
                        
                        break;
                    case 5:
                        hortalizasCaracteristica.setText(cursor.getString(i));
                        break;
                    case 6:
                        hortalizasCantidad.setText(cursor.getString(i));
                        break;
                    case 7:
                        hortalizasPrecio.setText(cursor.getString(i));
                        break;
                    case 8:
                        File image = new File("C:\\Users\\Gabriel\\Documents\\images\\foto.jpg");
                        FileOutputStream fos = new FileOutputStream(image);
                        byte[] buffer = new byte[1];
                        InputStream is = cursor.getBinaryStream(i);
                        while (is.read(buffer) > 0) {
                          fos.write(buffer);
                        }
                        fos.close();
                        ImageIcon imgThisImg = new ImageIcon("C:\\Users\\Gabriel\\Documents\\images\\foto.jpg");
                        Image scaleImage = imgThisImg.getImage().getScaledInstance(390, 310, Image.SCALE_DEFAULT);
                        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);

                        fotoHortalizas.setSize(390, 310);
                        fotoHortalizas.setIcon(imgThisImg2);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    //CARGAR DATOS DE ÁRBOL EN VENTANA ÁRBOLES
    public void cargarInfoArbol(int idArbol) throws SQLException, FileNotFoundException, IOException {
        System.out.println(idArbol);
        String nom = "{call paqUtilidades.arbolInfoGeneral(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idArbol);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);
        //1 = nombre
        //2 = precio
        //3 = extincion
        //4 = reproduccion
        //5 = xilema
        //6 = cambium
        //7 = corteza
        //8 = cantidad
        //9 = foto
        while (cursor.next()) {
            for (int i = 1; i < 10; i++) {
                if (i == 1) {
                    arbolesNombre.setText(cursor.getString(i));
                } else if (i == 2) {
                    arbolesPrecio.setText(cursor.getString(i));
                } else if (i == 3) {
                    if(cursor.getInt(i) == 0){
                        arbolesExtincion.setText("No");
                    }
                    else{
                        arbolesExtincion.setText("Si");
                    }
                } else if (i == 4) {
                    arbolesReproduccion.setText(cursor.getString(i));
                } else if (i == 5) {
                    arbolesXilema.setText(cursor.getString(i));
                } else if (i == 6) {
                    arbolesCambium.setText(cursor.getString(i));
                } else if (i == 7) {
                    arbolesCorteza.setText(cursor.getString(i));
                } else if (i == 8) {
                    arbolesCantidad.setText(cursor.getString(i));
                } else if (i == 9) {
                    File image = new File("C:\\Users\\Gabriel\\Documents\\images\\foto.jpg");
                    FileOutputStream fos = new FileOutputStream(image);
                    byte[] buffer = new byte[1];
                    InputStream is = cursor.getBinaryStream(i);
                    while (is.read(buffer) > 0) {
                      fos.write(buffer);
                    }
                    fos.close();
                    ImageIcon imgThisImg = new ImageIcon("C:\\Users\\Gabriel\\Documents\\images\\foto.jpg");
                    Image scaleImage = imgThisImg.getImage().getScaledInstance(390, 310, Image.SCALE_DEFAULT);
                    ImageIcon imgThisImg2 = new ImageIcon(scaleImage);

                    fotoArboles.setSize(390, 310);
                    fotoArboles.setIcon(imgThisImg2);
                }
            }
        }
    }

    public void cargarInfoTruequeP1() {

    }

    public void cargarInfoTruequeP2() {

    }

    public void cargarInfoVenta() {

    }

    public void cargarInfoAudGeneral() throws SQLException {
        String nom = "{? = call paqUtilidades.auditoriaGeneralInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);

        //1 = nombre tabla
        //2 = fecha creacion
        //3 = creado por
        //4 = accion
        int cantRows = 0;
        while (cursor.next()) {
            cantRows += 1;
            modeloAudGeneral.setNumRows(cantRows);
            for (int i = 1; i < 5; i++) {
                modeloAudGeneral.setValueAt(cursor.getString(i), cantRows - 1, i - 1);
            }
        }
    }

    public void cargarInfoAudUsuarios() throws SQLException {
        String nom = "{? = call paqUtilidades.auditoriaPersona()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//NOMBRE
        //1 = creado por
        //2 = fecha creacion
        //3 = editado por
        //4 = fecha edicion
        int cantRows = 0;
        while (cursor.next()) {
            cantRows += 1;
            modeloAudUsuarios.setNumRows(cantRows);
            for (int i = 1; i < 5; i++) {
                modeloAudUsuarios.setValueAt(cursor.getString(i), cantRows - 1, i - 1);
            }
        }
    }

    public void cargarInfoAudHuertas() throws SQLException {
        String nom = "{? = call paqUtilidades.auditoriaHuerta()}";

        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);

        cstmt.execute();

        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//nombre
        //1 = creado por
        //2 = fecha creacion
        //3 = editado por
        //4 = fecha edicion
        int cantRows = 0;
        while (cursor.next()) {
            cantRows += 1;
            modeloAudHuertas.setNumRows(cantRows);
            for (int i = 1; i < 5; i++) {
                modeloAudHuertas.setValueAt(cursor.getString(i), cantRows - 1, i - 1);
            }
        }
    }

    public void cargarInfoColor() throws SQLException {
        idColor.clear();
        nombreColor.clear();
        modeloColor.removeAllElements();
        String nom = "{? = call paqUtilidades.colorInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idcolor
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idColor.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreColor.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreColor) {
            modeloColor.addElement(hortaliza);
        }
    }

    public void cargarInfoTipo() throws SQLException {
        idTipo.clear();
        nombreTipo.clear();
        modeloTipo.removeAllElements();
        String nom = "{? = call paqUtilidades.tipoInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idtipo
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idTipo.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreTipo.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreTipo) {
            modeloTipo.addElement(hortaliza);
        }
    }

    public void cargarInfoPropiedad() throws SQLException {
        idPropiedad.clear();
        nombrePropiedad.clear();
        modeloPropiedad.removeAllElements();
        String nom = "{? = call paqUtilidades.propiedadInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idpropiedad
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idPropiedad.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombrePropiedad.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombrePropiedad) {
            modeloPropiedad.addElement(hortaliza);
        }
    }

    public void cargarInfoCatalogos() throws SQLException{
        cargarInfoCaracteristica();
        cargarInfoPropiedad();
        cargarInfoTipo();
        cargarInfoColor();
        cargarInfoReproduccion();
        cargarInfoXilema();
        cargarInfoCambium();
        cargarInfoCorteza();
        cargarInfoPais();
        cargarInfoProvincia();
        cargarInfoCanton();
        cargarInfoDistrito();
    }
    
    public void cargarInfoCaracteristica() throws SQLException {
        idCaracteristica.clear();
        nombreCaracteristica.clear();
        modeloCaracteristica.removeAllElements();
        String nom = "{? = call paqUtilidades.caracteristicaInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idcaracteristica
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idCaracteristica.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreCaracteristica.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreCaracteristica) {
            modeloCaracteristica.addElement(hortaliza);
        }
    }

    public void cargarInfoReproduccion() throws SQLException {
        idReproduccion.clear();
        nombreReproduccion.clear();
        modeloReproduccion.removeAllElements();
        String nom = "{? = call paqUtilidades.reproduccionInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        //1 idreproduccion
        //2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idReproduccion.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreReproduccion.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreReproduccion) {
            modeloReproduccion.addElement(hortaliza);
        }
    }

    public void cargarInfoXilema() throws SQLException {
        idXilema.clear();
        nombreXilema.clear();
        modeloXilema.removeAllElements();
        String nom = "{? = call paqUtilidades.xilemaInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idxilema
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idXilema.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreXilema.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreXilema) {
            modeloXilema.addElement(hortaliza);
        }
    }

    public void cargarInfoCambium() throws SQLException {
        idCambium.clear();
        nombreCambium.clear();
        modeloCambium.removeAllElements();
        String nom = "{? = call paqUtilidades.cambiumInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idcambium
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idCambium.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreCambium.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreCambium) {
            modeloCambium.addElement(hortaliza);
        }
    }

    public void cargarInfoCorteza() throws SQLException {
        idCorteza.clear();
        nombreCorteza.clear();
        modeloCorteza.removeAllElements();
        String nom = "{? = call paqUtilidades.cortezaInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idcorteza
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idCorteza.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreCorteza.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreCorteza) {
            modeloCorteza.addElement(hortaliza);
        }
    }

    public void cargarInfoPais() throws SQLException {
        idPais.clear();
        nombrePais.clear();
        modeloPais.removeAllElements();
        String nom = "{? = call paqUtilidades.paisInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idpais
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                cursor.getString(i);
                if (i == 1) {
                    idPais.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombrePais.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombrePais) {
            modeloPais.addElement(hortaliza);
        }
    }

    public void cargarInfoProvincia() throws SQLException {
        idProvincia.clear();
        nombreProvincia.clear();
        modeloProvincia.removeAllElements();
        String nom = "{? = call paqUtilidades.provinciaInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idprovincia
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idProvincia.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreProvincia.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreProvincia) {
            modeloProvincia.addElement(hortaliza);
        }
    }

    public void cargarInfoCanton() throws SQLException {
        idCanton.clear();
        nombreCanton.clear();
        modeloCanton.removeAllElements();
        String nom = "{? = call paqUtilidades.cantonInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 idcanton
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idCanton.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreCanton.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreCanton) {
            modeloCanton.addElement(hortaliza);
        }
    }

    public void cargarInfoDistrito() throws SQLException {
        idDistrito.clear();
        nombreDistrito.clear();
        modeloDistrito.removeAllElements();
        String nom = "{? = call paqUtilidades.distritoInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
//1 iddistrito
//2 nombre
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idDistrito.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreDistrito.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreDistrito) {
            modeloDistrito.addElement(hortaliza);
        }        
    }

    public void prepareAdAbonos() throws SQLException {
        
    }
    
    public void cargarInfoAbonos() throws SQLException {
        String nom = "{? = call paqUtilidades.abonoInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        //1 idcambium
        //2 nombre
        while(cursor.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idAbono.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreAbono.add(cursor.getString(i));
                }
            }
        }        
                
        for (String hortaliza : nombreAbono) {
            modeloAbonosBox.addElement(hortaliza);
        }
    }

    public void cargarInfoEstadisticas() {

    }

    public void cargarInfoConsultas() {

    }

    public void cargarInfoBitacora() {

    }

    public void cargarInfoComentarios() {

    }

    public void cargarInfoHuertaBox() throws SQLException {
        String nom = "{? = call paqUtilidades.huertaInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while (cursor.next()) {
            for (int i = 1; i < 3; i++) {
                System.out.println("Name = " + cursor.getString(i));
                if (i == 1) {
                    idHuertas.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreHuertas.add(cursor.getString(i));
                }
            }
        }
        for (String hortaliza : nombreHuertas) {
            modeloHuertas.addElement(hortaliza);
        }
    }

//-----------------------------------TRUEQUES Y VENTAS (CREAR, REALIZAR, CANCELAR)
    //CREAR TRUEQUE
    public void crearTrueque(int idHuerta) throws SQLException {/*
        String nom = "{? = call ventatruequePaq.crearTrueque(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idUsuario);
        cstmt.setInt(2, idHuerta);
        cstmt.registerOutParameter(1, OracleTypes.INTEGER);
        cstmt.execute();

        idTrueque = cstmt.getInt(1);  */
    }

    //REALIZAR TRUEQUE
    public void realizarTrueque(int idPlanta1, int idPlanta2) throws SQLException {/*
        if (tipoTrueque == 0) {
            return;
        }
        if (tipoTrueque == 1) { //TRUEQUE AA
            String nom = "{? = call ventatruequePaq.crearTruequeArbolAA(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);

            cstmt.setInt(1, idPlanta1); //ARBOL 1
            cstmt.setInt(2, idPlanta2); //ARBOL 2
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);

            cstmt.execute();
        } else if (tipoTrueque == 2) { //TRUEQUE AH
            String nom = "{? = call ventatruequePaq.crearTruequeArbolAH(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);

            cstmt.setInt(1, idPlanta1); //ARBOL
            cstmt.setInt(2, idPlanta2); //HORTALIZA
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);

            cstmt.execute();
        } else if (tipoTrueque == 3) { //TRUEQUE HH
            String nom = "{? = call ventatruequePaq.crearTruequeHortalizaHH(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);

            cstmt.setInt(1, idPlanta1); //HORTALIZA 1
            cstmt.setInt(2, idPlanta2); //HORTALIZA 2
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);

            cstmt.execute();
        } else if (tipoTrueque == 4) { //TRUEQUE HA
            String nom = "{? = call ventatruequePaq.crearTruequeHortalizaHA(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);

            cstmt.setInt(1, idPlanta1); //HORTALIZA
            cstmt.setInt(2, idPlanta2); //ARBOL
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);

            cstmt.execute();
        }*/
    }

    //CANCELAR TRUEQUE
    public void cancelarTrueque() throws SQLException {/*
        String nom = "{? = call ventatruequePaq.borrarTrueque(?)}";

        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idTrueque);
        cstmt.execute();*/
    }

    //CREAR VENTA
    public void crearVenta() throws SQLException {
        String nom = "{call ventatruequePaq.crearVenta(?,?)}";

        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idUser);
        cstmt.registerOutParameter(2, OracleTypes.INTEGER);
        cstmt.execute();
        idVenta = cstmt.getInt(2);
    }

    //CANCELAR VENTA
    public void cancelarVenta() throws SQLException {/*
        String nom = "{? = call ventatruequePaq.borrarVenta(?)}";

        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idVenta);
        cstmt.execute();*/
    }

    //REPRODUCCIÓN
    public void crearReproduccion(String nombre, String descripcion) throws SQLException {/*
        String nom = "{? = call paqPlantas.crearReproduccion(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setString(1, nombre);
        cstmt.setString(2, descripcion);
        cstmt.execute();*/
    }

    public void borrarReproduccion(int idReproduccion) throws SQLException {/*
        String nom = "{? = call paqPlantas.borrarReproduccion(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setInt(1, idReproduccion);
        cstmt.execute();*/
    }

    //XILEMA
    public void crearXilema(String nombre, String descripcion) throws SQLException {/*
        String nom = "{? = call paqPlantas.crearXilema(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setString(1, nombre);
        cstmt.setString(2, descripcion);
        cstmt.execute();*/
    }

    public void borrarXilema(int idXilema) throws SQLException {/*
        String nom = "{? = call paqPlantas.borrarXilema(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setInt(1, idXilema);
        cstmt.execute();*/
    }

    //CAMBIUM
    public void crearCambium(String nombre, String descripcion) throws SQLException {/*
        String nom = "{? = call paqPlantas.crearCambium(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setString(1, nombre);
        cstmt.setString(2, descripcion);
        cstmt.execute();*/
    }

    public void borrarCambium(int idCambium) throws SQLException {/*
        String nom = "{? = call paqPlantas.borrarCambium(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setInt(1, idCambium);
        cstmt.execute();*/
    }

    //CORTEZA
    public void crearCorteza(String nombre, String descripcion) throws SQLException {/*
        String nom = "{? = call paqPlantas.crearCorteza(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setString(1, nombre);
        cstmt.setString(2, descripcion);
        cstmt.execute();*/
    }

    public void borrarCorteza(int idCorteza) throws SQLException {/*
        String nom = "{? = call paqPlantas.borrarCorteza(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setInt(1, idCorteza);
        cstmt.execute();*/
    }

    //PAÍS    
    public void crearPais(String nombre) throws SQLException {/*
        String nom = "{? = call paqLocacion.crearPais(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setString(1, nombre);
        cstmt.execute();*/
    }

    public void borrarPais(int idPais) throws SQLException {/*
        String nom = "{? = call paqLocacion.borrarPais(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setInt(1, idPais);
        cstmt.execute();*/
    }

    //PROVINCIA
    public void crearProvincia(String nombre, int idPais) throws SQLException {/*
        String nom = "{? = call paqLocacion.crearProvincia(?, ?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setString(1, nombre);
        cstmt.setInt(1, idPais);
        cstmt.execute();*/
    }

    public void borrarProvincia(int idProvincia) throws SQLException {/*
        String nom = "{? = call paqLocacion.borrarProvincia(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setInt(1, idProvincia);
        cstmt.execute();*/

    }

    //CANTÓN
    public void crearCanton(String nombre, int idProvincia) throws SQLException {/*
        String nom = "{? = call paqLocacion.crearCanton(?, ?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setString(1, nombre);
        cstmt.setInt(1, idProvincia);
        cstmt.execute();*/
    }

    public void borrarCanton(int idCanton) throws SQLException {/*
        String nom = "{? = call paqLocacion.borrarCanton(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setInt(1, idCanton);
        cstmt.execute();*/
    }

    //DISTRITO
    public void crearDistrito(String nombre, int idCanton) throws SQLException {/*
        String nom = "{? = call paqLocacion.crearCanton(?, ?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setString(1, nombre);
        cstmt.setInt(1, idCanton);
        cstmt.execute();*/
    }

    public void borrarDistrito(int idDistrito) throws SQLException {/*
        String nom = "{? = call paqLocacion.borrarDistrito(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);

        cstmt.setInt(1, idDistrito);
        cstmt.execute();*/
    }

//-----------------------------------FUNCIONES PARA AD HUERTAS, USUARIOS, HORTALIZAS, ARBOLES, ABONOS

    public void editarUsuario(int idUsuario, String username, String nuevaClave, String viejaClave) throws SQLException {
        //Nombre de usuario
        String nom = "{? = call paqUsuario.editarUsuario(?, ?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idUsuario);
        cstmt.setString(2, username);
        cstmt.execute();

        //Clave de usuario
        String nom2 = "{? = call paqUsuario.editarUsuario(?, ?, ?)}";
        CallableStatement cstmt2 = conexion.prepareCall(nom2);
        cstmt2.setInt(1, idUsuario);
        cstmt2.setString(2, nuevaClave);
        cstmt2.setString(3, viejaClave);
        cstmt2.execute();
    }

    public void crearPersona() {
        /*try {
            String nom = "{call paqPersona.crearPersona(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
//            byte[] imagenRegistro = convertToBlob(imagenUsuario.getAbsolutePath());
//            Blob blob = new SerialBlob(imagenRegistro);
            
            File blob2 = new File(imagenUsuario.getAbsolutePath());
            FileInputStream in = new FileInputStream(blob2);

            // the cast to int is necessary because with JDBC 4 there is 
            // also a version of this method with a (int, long) 
            // but that is not implemented by Oracle
            crearPersona.setBinaryStream(8, in, (int)blob2.length());
            
            //crearPersona.setBlob(8, blob);

            crearPersona.setInt(9, 1);

            int huerta = getHuerta();
            crearPersona.setInt(10, huerta);

            int dedicacion = getDedicacion();
            crearPersona.setInt(11, dedicacion);

            crearPersona.setString(12, usuarioRegistro.getText());

            crearPersona.setString(13, codificarPass(String.valueOf(passwordRegistro.getPassword())));
            crearPersona.execute();
        } catch (SQLException | ParseException | IOException ex) {
            Logger.getLogger(inicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void editarPersona() {

    }

    public void crearAbono(int idAbono, int idPersona) throws SQLException {
        String nom = "{? = call paqAbono.crearRegistroAbono(?, ?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idAbono);
        cstmt.setInt(2, idPersona);
        cstmt.execute();
    }

    public void registrarAbonoArbol(int idAbono, int idArbol) throws SQLException {

    }

    public void registrarAbonoHortaliza(int idAbono, int idHortaliza) throws SQLException {

    }

    public void editarAbono(int idAbono, String nombre, String descripcion) throws SQLException {

    }

//-----------------------------------GETS PARA LOS ID's
    //OBTENER LA HUERTA PARA HUERTAS
    public int getHuerta() {
        if (idHuertas.isEmpty()) {
            return -1;
        }
        int posSeleccionado = huertas.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }

    //OBTENER LA HORTALIZA PARA HORTALIZAS
    public int getHortaliza() {
        if (idHortalizaHuerta.isEmpty()) {
            return -1;
        }
        int posSeleccionado = hortalizasList.getSelectedIndex();
        if (posSeleccionado == -1) {
            return -1;
        }
        int id = (Integer) idHortalizaHuerta.get(posSeleccionado);
        return id;
    }

    //OBTENER LA HUERTA PARA HORTALIZAS
    public int getHortalizaHuerta() {
        if (idHuertas.isEmpty()) {
            return -1;
        }
        int posSeleccionado = hortalizasHuertas.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }

    //OBTENER EL ÁRBOL PARA  ÁRBOLES
    public int getArbol() {
        if (idArbolHuerta.isEmpty()) {
            return -1;
        }
        int posSeleccionado = arbolesList.getSelectedIndex();
        if (posSeleccionado == -1) {
            return -1;
        }
        int id = (Integer) idArbolHuerta.get(posSeleccionado);
        return id;
    }

    //OBTENER LA HUERTA PARA ÁRBOLES
    public int getArbolHuerta() {
        if (idHuertas.isEmpty()) {
            return -1;
        }
        int posSeleccionado = arbolesHuertas.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }

    //OBTENER LA HUERTA PARA TRUEQUES
    public int getHuertaTrueque() {
        if (idHuertas.isEmpty()) {
            return -1;
        }
        int posSeleccionado = truequeHuertas.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }

    //OBTENER LA PLANTA 1 PARA TRUEQUES
    public int getPlanta1Trueque() {
        if (idTruequesP1.isEmpty()) {
            return -1;
        }
        int posSeleccionado = truequeP1.getSelectedIndex();
        int id = (Integer) idTruequesP1.get(posSeleccionado);
        return id;
    }

    //OBTENER LA PLANTA 2 PARA TRUEQUES
    public int getPlanta2Trueque() {
        if (idTruequesP2.isEmpty()) {
            return -1;
        }
        int posSeleccionado = truequeP2.getSelectedIndex();
        int id = (Integer) idTruequesP2.get(posSeleccionado);
        return id;
    }

    //OBTENER LA PLANTA PARA VENTAS
    public int getPlantaVenta() {
        if (idVentasP.isEmpty()) {
            return -1;
        }
        int posSeleccionado = ventaPlantas.getSelectedIndex();
        int id = (Integer) idVentasP.get(posSeleccionado);
        return id;
    }

    public int getCalificacion(int idHortaliza) throws SQLException {/*
        String nom = "{? = call paqHuerta.getPromedioHuerta(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idHortaliza);
        cstmt.registerOutParameter(1, OracleTypes.INTEGER);
        cstmt.execute();
        int calificacion = cstmt.getInt(1);
        return calificacion;*/
        return 0;
    }

//-----------------------------------REVISAR CAMPOS
    public boolean revisarCamposHortaliza() {
        return false;

    }

    public boolean revisarCamposArbol() {
        return false;

    }

    public boolean revisarCamposHuerta() {
        return false;

    }

    public boolean revisarCamposAbono() {
        return false;

    }

    public boolean revisarCamposCatalogos() {
        return false;

    }

    public boolean revisarCamposComentarios() {
        return false;

    }

//--------------------------------PIE CHART
    private static final long serialVersionUID = 1L;

    public void pieChart(String chartTitle, ArrayList valores, ArrayList nombres) {     
        PieDataset dataset = createDataset(valores, nombres);        
        JFreeChart chart = createChart(dataset, chartTitle);        
        ChartPanel chartPanel = new ChartPanel(chart);        
        chartPanel.setPreferredSize(new java.awt.Dimension(417,475));         
        panelGrafico.removeAll();
        panelGrafico.add(chartPanel, BorderLayout.CENTER);
        panelGrafico.revalidate();
        panelGrafico.repaint();
    }

    private PieDataset createDataset(ArrayList valores, ArrayList nombres) {
        DefaultPieDataset result = new DefaultPieDataset();        
        for (int i=0; i < valores.size(); i++){
            result.setValue(String.valueOf(nombres.get(i)), Integer.parseInt(String.valueOf(valores.get(i))));
        }
        return result;

    }

    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                title, // chart title
                dataset, // data
                true, // include legend
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInicio = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        logoInicio = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        panelHuertas = new JPanel(new BorderLayout());
        buttonCambiarHuerta = new javax.swing.JButton();
        panelMap = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        sliderCalificacion = new javax.swing.JSlider();
        buttonVerMas = new javax.swing.JButton();
        labelSldCalificacion = new javax.swing.JLabel();
        buttonVerHuerta = new javax.swing.JButton();
        huertasNombre = new javax.swing.JLabel();
        huertas = new javax.swing.JComboBox<>();
        hortalizasH = new javax.swing.JComboBox<>();
        arbolesH = new javax.swing.JComboBox<>();
        labelCalificacion3 = new javax.swing.JLabel();
        labelCalificacion4 = new javax.swing.JLabel();
        labelCalificacion5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        panelHortalizas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hortalizasList = new javax.swing.JList<>();
        hortalizasHuertas = new javax.swing.JComboBox<>();
        hortalizasNombre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        hortalizasCantidad = new javax.swing.JLabel();
        buttonCrearHortaliza = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        buttonAgUnidadesHortalizas = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        hortalizasColor = new javax.swing.JLabel();
        hortalizasTipo = new javax.swing.JLabel();
        hortalizasCaracteristica = new javax.swing.JLabel();
        hortalizasPrecio = new javax.swing.JLabel();
        buttonCambiarHortaliza = new javax.swing.JButton();
        buttonVerHortaliza = new javax.swing.JButton();
        fotoHortalizas = new javax.swing.JLabel();
        hortalizasPropiedad = new javax.swing.JLabel();
        panelArboles = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        arbolesReproduccion = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        arbolesCantidad = new javax.swing.JLabel();
        arbolesCambium = new javax.swing.JLabel();
        arbolesXilema = new javax.swing.JLabel();
        arbolesCorteza = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        arbolesList = new javax.swing.JList<>();
        arbolesHuertas = new javax.swing.JComboBox<>();
        arbolesNombre = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        buttonAgUnidadesArboles = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        buttonCrearArbol = new javax.swing.JButton();
        buttonCambiarArbol = new javax.swing.JButton();
        buttonVerArbol = new javax.swing.JButton();
        arbolesExtincion = new javax.swing.JLabel();
        arbolesPrecio = new javax.swing.JLabel();
        fotoArboles = new javax.swing.JLabel();
        panelTrueques = new javax.swing.JPanel();
        truequeP1 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        truequeHuertas = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        buttonAA = new javax.swing.JButton();
        buttonHH = new javax.swing.JButton();
        buttonHA = new javax.swing.JButton();
        buttonAH = new javax.swing.JButton();
        buttonCancelarTrueque = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        truequeP2 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        buttonRealizarTrueque = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        panelVentas = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        buttonVentaH = new javax.swing.JButton();
        buttonVentaA = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        ventaPlantas = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        buttonCancelarVenta = new javax.swing.JButton();
        buttonRealizarVenta = new javax.swing.JButton();
        opcionesAdministrador = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        buttonBitacora = new javax.swing.JButton();
        buttonConsultas = new javax.swing.JButton();
        buttonAuditoria = new javax.swing.JButton();
        buttonAdDatos = new javax.swing.JButton();
        buttonSalirAdmin = new javax.swing.JButton();
        buttonEstadisticas = new javax.swing.JButton();
        dialogEstadisticas = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        buttonSalirEstadisticas = new javax.swing.JButton();
        panelGrafico = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        dialogBitacora = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        buttonSalirBitacora = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableBitacora = new javax.swing.JTable();
        dialogConsultas = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        buttonSalirConsultas = new javax.swing.JButton();
        panelNombreConsultas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        buttonCambiarAdUsuario1 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        usuariosAdministrar1 = new javax.swing.JComboBox<>();
        buttonVerAdUsuario1 = new javax.swing.JButton();
        panelFiltrosConsulta = new javax.swing.JPanel();
        dialogAuditoria = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        buttonAudHuertas = new javax.swing.JButton();
        panelAuditorias = new javax.swing.JPanel();
        buttonSalirAuditoria1 = new javax.swing.JButton();
        buttonAudGeneral = new javax.swing.JButton();
        buttonAudUsuarios = new javax.swing.JButton();
        dialogAdDatos = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        buttonSalirAdDatos = new javax.swing.JButton();
        panelAdDatos = new javax.swing.JPanel();
        buttonAdCatalogos = new javax.swing.JButton();
        buttonAdUsuarios = new javax.swing.JButton();
        buttonAdHuertas = new javax.swing.JButton();
        buttonAdHortalizas = new javax.swing.JButton();
        buttonAdArboles = new javax.swing.JButton();
        buttonAdAbonos = new javax.swing.JButton();
        dialogComentarios = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        buttonSalirComentarios = new javax.swing.JButton();
        labelCalificacion1 = new javax.swing.JLabel();
        sliderCalificacion1 = new javax.swing.JSlider();
        jScrollPane14 = new javax.swing.JScrollPane();
        comentarioUsuario = new javax.swing.JTextArea();
        labelCalificacion2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buttonEnviarCalificacion = new javax.swing.JButton();
        calificacionUsuario = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        comentariosHuerta = new javax.swing.JTextArea();
        panelAudUsuarios = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        panelAudHuertas = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableAudHuertas = new javax.swing.JTable();
        panelAudGeneral = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableAudGeneral = new javax.swing.JTable();
        panelAdCatalogos = new javax.swing.JPanel();
        colorAdministrar = new javax.swing.JComboBox<>();
        buttonDesactivarColor = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        buttonAgregarColor = new javax.swing.JButton();
        buttonAgregarColor1 = new javax.swing.JButton();
        colorAdministrar1 = new javax.swing.JComboBox<>();
        buttonDesactivarColor1 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        buttonAgregarColor2 = new javax.swing.JButton();
        buttonAgregarColor3 = new javax.swing.JButton();
        colorAdministrar2 = new javax.swing.JComboBox<>();
        buttonDesactivarColor2 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        buttonAgregarColor4 = new javax.swing.JButton();
        buttonAgregarColor5 = new javax.swing.JButton();
        colorAdministrar3 = new javax.swing.JComboBox<>();
        buttonDesactivarColor3 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        buttonAgregarColor6 = new javax.swing.JButton();
        buttonAgregarColor7 = new javax.swing.JButton();
        colorAdministrar4 = new javax.swing.JComboBox<>();
        buttonDesactivarColor4 = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        buttonAgregarColor8 = new javax.swing.JButton();
        buttonAgregarColor9 = new javax.swing.JButton();
        colorAdministrar5 = new javax.swing.JComboBox<>();
        buttonDesactivarColor5 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        buttonAgregarColor10 = new javax.swing.JButton();
        buttonAgregarColor11 = new javax.swing.JButton();
        colorAdministrar6 = new javax.swing.JComboBox<>();
        buttonDesactivarColor6 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        buttonAgregarColor12 = new javax.swing.JButton();
        buttonAgregarColor13 = new javax.swing.JButton();
        colorAdministrar7 = new javax.swing.JComboBox<>();
        buttonDesactivarColor7 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        buttonAgregarColor14 = new javax.swing.JButton();
        buttonAgregarColor15 = new javax.swing.JButton();
        colorAdministrar8 = new javax.swing.JComboBox<>();
        buttonDesactivarColor8 = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        buttonAgregarColor16 = new javax.swing.JButton();
        buttonAgregarColor17 = new javax.swing.JButton();
        colorAdministrar9 = new javax.swing.JComboBox<>();
        buttonDesactivarColor9 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        buttonAgregarColor18 = new javax.swing.JButton();
        buttonAgregarColor19 = new javax.swing.JButton();
        colorAdministrar10 = new javax.swing.JComboBox<>();
        buttonDesactivarColor10 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        buttonAgregarColor20 = new javax.swing.JButton();
        buttonAgregarColor21 = new javax.swing.JButton();
        colorAdministrar11 = new javax.swing.JComboBox<>();
        buttonDesactivarColor11 = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        buttonAgregarColor22 = new javax.swing.JButton();
        buttonAgregarColor23 = new javax.swing.JButton();
        panelAdUsuarios = new javax.swing.JPanel();
        buttonCambiarAdUsuario = new javax.swing.JButton();
        nombreAdUsuario = new javax.swing.JTextField();
        emailAdUsuario = new javax.swing.JTextField();
        cedulaAdUsuario = new javax.swing.JTextField();
        apellidoAdUsuario = new javax.swing.JTextField();
        telefonoAdUsuario = new javax.swing.JTextField();
        usuarioAdUsuario = new javax.swing.JTextField();
        passwordAdUsuario = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fechaAdUsuario = new datechooser.beans.DateChooserCombo();
        jLabel15 = new javax.swing.JLabel();
        huertaAdUsuario = new javax.swing.JComboBox<>();
        dedicacionAdUsuario = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        nacionalidadAdUsuario = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        fotoAdUsuario = new javax.swing.JLabel();
        elegirArchivo = new javax.swing.JButton();
        buttonCrearAdUsuario = new javax.swing.JButton();
        buttonModificarAdUsuario = new javax.swing.JButton();
        usuariosAdministrar = new javax.swing.JComboBox<>();
        buttonVerAdUsuario = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        buttonAceptarAdUsuario = new javax.swing.JButton();
        buttonCancelarAdUsuario = new javax.swing.JButton();
        panelAdHuertas = new javax.swing.JPanel();
        panelMap1 = new javax.swing.JPanel();
        buttonCambiarAdHuerta = new javax.swing.JButton();
        huertasAdministrar = new javax.swing.JComboBox<>();
        buttonVerAdHuerta = new javax.swing.JButton();
        nombreAdHuerta = new javax.swing.JTextField();
        longitudAdHuerta = new javax.swing.JTextField();
        latitudAdHuerta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        buttonCrearAdHuerta = new javax.swing.JButton();
        buttonModificarAdHuerta = new javax.swing.JButton();
        distritoAdHuerta = new javax.swing.JComboBox<>();
        capitalAdHuerta = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        buttonCancelarAdHuerta = new javax.swing.JButton();
        buttonAceptarAdHuerta = new javax.swing.JButton();
        groupHortalizas = new javax.swing.ButtonGroup();
        panelAdHortalizas = new javax.swing.JPanel();
        cantidadAdHortaliza = new javax.swing.JTextField();
        precioAdHortaliza = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        huertaAdHortaliza = new javax.swing.JComboBox<>();
        colorAdHortaliza = new javax.swing.JComboBox<>();
        tipoAdHortaliza = new javax.swing.JComboBox<>();
        propiedadAdHortaliza = new javax.swing.JComboBox<>();
        caracteristicaAdHortaliza = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        nombreAdHortaliza = new javax.swing.JTextField();
        ojala1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        buttonCambiarAdHortaliza = new javax.swing.JButton();
        hortalizasAdHortaliza = new javax.swing.JComboBox<>();
        buttonVerAdHortaliza = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        buttonAceptarAdHortaliza = new javax.swing.JButton();
        buttonCancelarAdHortaliza = new javax.swing.JButton();
        buttonCrearAdHortaliza = new javax.swing.JButton();
        buttonModificarAdHortaliza = new javax.swing.JButton();
        panelAdArboles = new javax.swing.JPanel();
        cantidadAdArbol = new javax.swing.JTextField();
        precioAdArbol = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        huertaAdArbol = new javax.swing.JComboBox<>();
        reproduccionAdArbol = new javax.swing.JComboBox<>();
        xilemaAdArbol = new javax.swing.JComboBox<>();
        cambiumAdArbol = new javax.swing.JComboBox<>();
        cortezaAdArbol = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        nombreAdArbol = new javax.swing.JTextField();
        ojala2 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        buttonCambiarAdArbol = new javax.swing.JButton();
        arbolesAdArbol = new javax.swing.JComboBox<>();
        buttonVerAdArbol = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        buttonAceptarAdArbol = new javax.swing.JButton();
        buttonCancelarAdArbol = new javax.swing.JButton();
        buttonCrearAdArbol = new javax.swing.JButton();
        buttonModificarAdArbol = new javax.swing.JButton();
        panelAdAbonos = new javax.swing.JPanel();
        panelCrearAbono = new javax.swing.JPanel();
        buttonCambiarAdArbol1 = new javax.swing.JButton();
        arbolesAdArbol1 = new javax.swing.JComboBox<>();
        buttonVerAdArbol1 = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        nombreAdArbol1 = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        descripcionCatalogo1 = new javax.swing.JTextArea();
        jLabel90 = new javax.swing.JLabel();
        buttonCrearAdArbol1 = new javax.swing.JButton();
        buttonModificarAdArbol1 = new javax.swing.JButton();
        buttonAceptarAdArbol1 = new javax.swing.JButton();
        buttonCancelarAdArbol1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        arbolesAdArbol2 = new javax.swing.JComboBox<>();
        jLabel91 = new javax.swing.JLabel();
        arbolesAdArbol3 = new javax.swing.JComboBox<>();
        jLabel92 = new javax.swing.JLabel();
        buttonVerAdArbol2 = new javax.swing.JButton();
        buttonCambiarAdArbol2 = new javax.swing.JButton();
        jLabel93 = new javax.swing.JLabel();
        arbolesAdArbol4 = new javax.swing.JComboBox<>();
        jLabel94 = new javax.swing.JLabel();
        buttonCrearAdArbol2 = new javax.swing.JButton();
        dialogFoto = new javax.swing.JDialog();
        elegirFoto = new javax.swing.JFileChooser();
        catalogos = new javax.swing.JDialog();
        nombreCatalogo = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        lugarCatalogo = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        descripcionCatalogo = new javax.swing.JTextArea();
        buttonAceptarCatalogo = new javax.swing.JButton();
        buttonCancelarCatalogo = new javax.swing.JButton();
        panelFiltro1 = new javax.swing.JPanel();
        fechaAdUsuario1 = new datechooser.beans.DateChooserCombo();
        jLabel44 = new javax.swing.JLabel();
        fechaAdUsuario2 = new datechooser.beans.DateChooserCombo();
        jLabel45 = new javax.swing.JLabel();
        nombreCatalogo1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        buttonAceptarCatalogo1 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        panelFiltro2 = new javax.swing.JPanel();
        fechaAdUsuario3 = new datechooser.beans.DateChooserCombo();
        jLabel47 = new javax.swing.JLabel();
        buttonAceptarCatalogo2 = new javax.swing.JButton();
        panelFiltro3 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        usuariosAdministrar2 = new javax.swing.JComboBox<>();
        buttonAceptarCatalogo3 = new javax.swing.JButton();
        dialogInvitado = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jToggleButton6 = new javax.swing.JToggleButton();
        hortalizaInvitado = new javax.swing.JPanel();
        precioAdHortaliza1 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        colorAdHortaliza1 = new javax.swing.JComboBox<>();
        tipoAdHortaliza1 = new javax.swing.JComboBox<>();
        propiedadAdHortaliza1 = new javax.swing.JComboBox<>();
        caracteristicaAdHortaliza1 = new javax.swing.JComboBox<>();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        nombreAdHortaliza1 = new javax.swing.JTextField();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        ojala4 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        arbolInvitado = new javax.swing.JPanel();
        precioAdArbol1 = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        reproduccionAdArbol1 = new javax.swing.JComboBox<>();
        xilemaAdArbol1 = new javax.swing.JComboBox<>();
        cambiumAdArbol1 = new javax.swing.JComboBox<>();
        cortezaAdArbol1 = new javax.swing.JComboBox<>();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        nombreAdArbol2 = new javax.swing.JTextField();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        ojala3 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JPanel();
        buttonHuertas = new javax.swing.JButton();
        buttonArboles = new javax.swing.JButton();
        buttonInicio = new javax.swing.JButton();
        buttonHortalizas = new javax.swing.JButton();
        buttonCerrarSesion = new javax.swing.JButton();
        buttonTrueques = new javax.swing.JButton();
        buttonOpciones = new javax.swing.JButton();
        buttonVentas = new javax.swing.JButton();
        buttonAbonos = new javax.swing.JButton();

        panelInicio.setBackground(new java.awt.Color(224, 164, 88));

        jLabel19.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 48)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("LA HUERTICA");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );

        jLabel55.setText("Sistema administrador de Huertas Comunitarias");

        jLabel56.setText("Usuario Conectado:");

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicioLayout.createSequentialGroup()
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel56)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInicioLayout.createSequentialGroup()
                        .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInicioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel19))
                            .addGroup(panelInicioLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel55)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel55)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57))
                .addGap(22, 22, 22))
        );

        panelHuertas.setBackground(new java.awt.Color(19, 111, 99));
        panelHuertas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonCambiarHuerta.setBackground(new java.awt.Color(0, 0, 0));
        buttonCambiarHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonCambiarHuerta.setForeground(new java.awt.Color(255, 255, 255));
        buttonCambiarHuerta.setText("CAMBIAR");
        buttonCambiarHuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCambiarHuertaActionPerformed(evt);
            }
        });
        panelHuertas.add(buttonCambiarHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 80, -1));

        panelMap.setBackground(new java.awt.Color(0, 0, 0));
        panelMap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MAPA DE HUERTAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nirmala UI Semilight", 0, 11))); // NOI18N
        panelMap.setForeground(new java.awt.Color(255, 255, 255));
        panelMap.setOpaque(false);
        panelMap.setLayout(new java.awt.BorderLayout());
        panelHuertas.add(panelMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 660, 550));

        jLabel20.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 18)); // NOI18N
        jLabel20.setText("Huertas Disponibles");
        panelHuertas.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        sliderCalificacion.setBackground(new java.awt.Color(0, 0, 0));
        sliderCalificacion.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        sliderCalificacion.setForeground(new java.awt.Color(255, 255, 255));
        sliderCalificacion.setMaximum(5);
        sliderCalificacion.setMinimum(1);
        sliderCalificacion.setPaintTicks(true);
        sliderCalificacion.setEnabled(false);
        sliderCalificacion.setFocusable(false);
        sliderCalificacion.setOpaque(false);
        sliderCalificacion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderCalificacionStateChanged(evt);
            }
        });
        panelHuertas.add(sliderCalificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 170, 20));

        buttonVerMas.setBackground(new java.awt.Color(0, 0, 0));
        buttonVerMas.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonVerMas.setForeground(new java.awt.Color(255, 255, 255));
        buttonVerMas.setText("VER MÁS");
        buttonVerMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerMasActionPerformed(evt);
            }
        });
        panelHuertas.add(buttonVerMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 100, -1));

        labelSldCalificacion.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 18)); // NOI18N
        labelSldCalificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSldCalificacion.setText("...");
        panelHuertas.add(labelSldCalificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 60, 30));

        buttonVerHuerta.setBackground(new java.awt.Color(0, 0, 0));
        buttonVerHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonVerHuerta.setForeground(new java.awt.Color(255, 255, 255));
        buttonVerHuerta.setText("VER");
        buttonVerHuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerHuertaActionPerformed(evt);
            }
        });
        panelHuertas.add(buttonVerHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 80, -1));

        huertasNombre.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        huertasNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        huertasNombre.setText("...");
        panelHuertas.add(huertasNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 270, 50));

        huertas.setBackground(new java.awt.Color(46, 53, 50));
        huertas.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        huertas.setForeground(new java.awt.Color(255, 255, 255));
        huertas.setModel(modeloHuertas        );
        panelHuertas.add(huertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 260, -1));

        hortalizasH.setBackground(new java.awt.Color(46, 53, 50));
        hortalizasH.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasH.setForeground(new java.awt.Color(255, 255, 255));
        hortalizasH.setModel(modeloHortalizasBox);
        panelHuertas.add(hortalizasH, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 270, -1));

        arbolesH.setBackground(new java.awt.Color(46, 53, 50));
        arbolesH.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesH.setForeground(new java.awt.Color(255, 255, 255));
        arbolesH.setModel(modeloArbolesBox        );
        panelHuertas.add(arbolesH, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 270, -1));

        labelCalificacion3.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        labelCalificacion3.setText("ÁRBOLES:");
        panelHuertas.add(labelCalificacion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 80, -1));

        labelCalificacion4.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        labelCalificacion4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCalificacion4.setText("CALIFICACIÓN:");
        panelHuertas.add(labelCalificacion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 160, -1));

        labelCalificacion5.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        labelCalificacion5.setText("HORTALIZAS:");
        panelHuertas.add(labelCalificacion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 80, -1));

        jCheckBox1.setText("Interesado");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        panelHuertas.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, -1, -1));

        panelHortalizas.setBackground(new java.awt.Color(162, 112, 53));
        panelHortalizas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hortalizasList.setBackground(new java.awt.Color(46, 53, 50));
        hortalizasList.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasList.setForeground(new java.awt.Color(255, 255, 255));
        hortalizasList.setModel(modeloHortalizas);
        hortalizasList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        hortalizasList.setSelectionBackground(new java.awt.Color(180, 184, 171));
        jScrollPane2.setViewportView(hortalizasList);

        panelHortalizas.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 195, 460));

        hortalizasHuertas.setBackground(new java.awt.Color(46, 53, 50));
        hortalizasHuertas.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasHuertas.setForeground(new java.awt.Color(255, 255, 255));
        hortalizasHuertas.setModel(modeloHuertas);
        hortalizasHuertas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hortalizasHuertasItemStateChanged(evt);
            }
        });
        hortalizasHuertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hortalizasHuertasActionPerformed(evt);
            }
        });
        panelHortalizas.add(hortalizasHuertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 195, -1));

        hortalizasNombre.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 36)); // NOI18N
        hortalizasNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hortalizasNombre.setText("...");
        hortalizasNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelHortalizas.add(hortalizasNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 630, -1));

        jLabel6.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel6.setText("PRECIO:");
        panelHortalizas.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel7.setText("COLOR:");
        panelHortalizas.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        jLabel8.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel8.setText("TIPO:");
        panelHortalizas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jLabel9.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel9.setText("PROPIEDAD:");
        panelHortalizas.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        hortalizasCantidad.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasCantidad.setText("...");
        panelHortalizas.add(hortalizasCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 190, -1));

        buttonCrearHortaliza.setBackground(new java.awt.Color(0, 0, 0));
        buttonCrearHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonCrearHortaliza.setForeground(new java.awt.Color(255, 255, 255));
        buttonCrearHortaliza.setText("CREAR");
        buttonCrearHortaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCrearHortalizaActionPerformed(evt);
            }
        });
        panelHortalizas.add(buttonCrearHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 70, -1));

        jLabel17.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel17.setText("CANTIDAD:");
        panelHortalizas.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, -1, -1));

        buttonAgUnidadesHortalizas.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgUnidadesHortalizas.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonAgUnidadesHortalizas.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgUnidadesHortalizas.setText("AGREGAR UNIDADES");
        buttonAgUnidadesHortalizas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgUnidadesHortalizasActionPerformed(evt);
            }
        });
        panelHortalizas.add(buttonAgUnidadesHortalizas, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 170, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        panelHortalizas.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 620, 10));

        jLabel14.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel14.setText("CARACTERISTICA:");
        panelHortalizas.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        hortalizasColor.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasColor.setText("...");
        panelHortalizas.add(hortalizasColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 260, -1));

        hortalizasTipo.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasTipo.setText("...");
        panelHortalizas.add(hortalizasTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 270, -1));

        hortalizasCaracteristica.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasCaracteristica.setText("...");
        panelHortalizas.add(hortalizasCaracteristica, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 210, -1));

        hortalizasPrecio.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasPrecio.setText("...");
        panelHortalizas.add(hortalizasPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 210, -1));

        buttonCambiarHortaliza.setBackground(new java.awt.Color(0, 0, 0));
        buttonCambiarHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonCambiarHortaliza.setForeground(new java.awt.Color(255, 255, 255));
        buttonCambiarHortaliza.setText("CAMBIAR");
        buttonCambiarHortaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCambiarHortalizaActionPerformed(evt);
            }
        });
        panelHortalizas.add(buttonCambiarHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 80, -1));

        buttonVerHortaliza.setBackground(new java.awt.Color(0, 0, 0));
        buttonVerHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonVerHortaliza.setForeground(new java.awt.Color(255, 255, 255));
        buttonVerHortaliza.setText("VER");
        buttonVerHortaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerHortalizaActionPerformed(evt);
            }
        });
        panelHortalizas.add(buttonVerHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 80, -1));

        fotoHortalizas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fotoHortalizas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelHortalizas.add(fotoHortalizas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 390, 310));

        hortalizasPropiedad.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        hortalizasPropiedad.setText("...");
        panelHortalizas.add(hortalizasPropiedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 240, -1));

        panelArboles.setBackground(new java.awt.Color(19, 111, 99));
        panelArboles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel23.setText("EXTINCIÓN:");
        panelArboles.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        jLabel24.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel24.setText("CAMBIUM:");
        panelArboles.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        jLabel25.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel25.setText("XILEMA:");
        panelArboles.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        arbolesReproduccion.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesReproduccion.setText("...");
        panelArboles.add(arbolesReproduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 220, -1));

        jLabel28.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel28.setText("CORTEZA:");
        panelArboles.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jLabel29.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel29.setText("REPRODUCCIÓN:");
        panelArboles.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, -1, -1));

        arbolesCantidad.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesCantidad.setText("...");
        panelArboles.add(arbolesCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 190, -1));

        arbolesCambium.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesCambium.setText("...");
        panelArboles.add(arbolesCambium, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 250, -1));

        arbolesXilema.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesXilema.setText("...");
        panelArboles.add(arbolesXilema, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 260, -1));

        arbolesCorteza.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesCorteza.setText("...");
        panelArboles.add(arbolesCorteza, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 250, -1));

        arbolesList.setBackground(new java.awt.Color(46, 53, 50));
        arbolesList.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesList.setForeground(new java.awt.Color(255, 255, 255));
        arbolesList.setModel(modeloArboles);
        arbolesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        arbolesList.setSelectionBackground(new java.awt.Color(180, 184, 171));
        jScrollPane4.setViewportView(arbolesList);

        panelArboles.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 195, 460));

        arbolesHuertas.setBackground(new java.awt.Color(46, 53, 50));
        arbolesHuertas.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesHuertas.setForeground(new java.awt.Color(255, 255, 255));
        arbolesHuertas.setModel(modeloHuertas);
        arbolesHuertas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                arbolesHuertasItemStateChanged(evt);
            }
        });
        arbolesHuertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arbolesHuertasActionPerformed(evt);
            }
        });
        panelArboles.add(arbolesHuertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 195, -1));

        arbolesNombre.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 36)); // NOI18N
        arbolesNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arbolesNombre.setText("NOMBRE");
        arbolesNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelArboles.add(arbolesNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 630, -1));

        jLabel12.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel12.setText("PRECIO");
        panelArboles.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, -1, -1));

        jLabel18.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel18.setText("CANTIDAD");
        panelArboles.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, -1, -1));

        buttonAgUnidadesArboles.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgUnidadesArboles.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonAgUnidadesArboles.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgUnidadesArboles.setText("AGREGAR UNIDADES");
        buttonAgUnidadesArboles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgUnidadesArbolesActionPerformed(evt);
            }
        });
        panelArboles.add(buttonAgUnidadesArboles, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 170, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        panelArboles.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 620, 10));

        buttonCrearArbol.setBackground(new java.awt.Color(0, 0, 0));
        buttonCrearArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonCrearArbol.setForeground(new java.awt.Color(255, 255, 255));
        buttonCrearArbol.setText("CREAR");
        buttonCrearArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCrearArbolActionPerformed(evt);
            }
        });
        panelArboles.add(buttonCrearArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 80, -1));

        buttonCambiarArbol.setBackground(new java.awt.Color(0, 0, 0));
        buttonCambiarArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonCambiarArbol.setForeground(new java.awt.Color(255, 255, 255));
        buttonCambiarArbol.setText("CAMBIAR");
        buttonCambiarArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCambiarArbolActionPerformed(evt);
            }
        });
        panelArboles.add(buttonCambiarArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 80, -1));

        buttonVerArbol.setBackground(new java.awt.Color(0, 0, 0));
        buttonVerArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonVerArbol.setForeground(new java.awt.Color(255, 255, 255));
        buttonVerArbol.setText("VER");
        buttonVerArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerArbolActionPerformed(evt);
            }
        });
        panelArboles.add(buttonVerArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 80, -1));

        arbolesExtincion.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesExtincion.setText("...");
        panelArboles.add(arbolesExtincion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 220, -1));

        arbolesPrecio.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        arbolesPrecio.setText("...");
        panelArboles.add(arbolesPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 210, -1));

        fotoArboles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fotoArboles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelArboles.add(fotoArboles, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 390, 310));

        panelTrueques.setBackground(new java.awt.Color(162, 112, 53));
        panelTrueques.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        truequeP1.setBackground(new java.awt.Color(46, 53, 50));
        truequeP1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        truequeP1.setForeground(new java.awt.Color(255, 255, 255));
        truequeP1.setModel(modeloTruequesP1);
        panelTrueques.add(truequeP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 200, -1));

        jLabel34.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Seleccione la huerta con la que desea realizar el trueque--->");
        panelTrueques.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 410, -1));

        truequeHuertas.setBackground(new java.awt.Color(46, 53, 50));
        truequeHuertas.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        truequeHuertas.setForeground(new java.awt.Color(255, 255, 255));
        truequeHuertas.setModel(modeloHuertas);
        panelTrueques.add(truequeHuertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 220, -1));

        jLabel36.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("HUERTA");
        panelTrueques.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 75, -1));

        jLabel37.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        jLabel37.setText("TIPO DE TRUEQUE");
        panelTrueques.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        buttonAA.setBackground(new java.awt.Color(0, 0, 0));
        buttonAA.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonAA.setForeground(new java.awt.Color(255, 255, 255));
        buttonAA.setText("ÁRBOL X ÁRBOL");
        buttonAA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAAActionPerformed(evt);
            }
        });
        panelTrueques.add(buttonAA, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, 180, -1));

        buttonHH.setBackground(new java.awt.Color(0, 0, 0));
        buttonHH.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonHH.setForeground(new java.awt.Color(255, 255, 255));
        buttonHH.setText("HORTALIZA X HORTALIZA");
        buttonHH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHHActionPerformed(evt);
            }
        });
        panelTrueques.add(buttonHH, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 180, -1));

        buttonHA.setBackground(new java.awt.Color(0, 0, 0));
        buttonHA.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonHA.setForeground(new java.awt.Color(255, 255, 255));
        buttonHA.setText("HORTALIZA X ÁRBOL");
        buttonHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHAActionPerformed(evt);
            }
        });
        panelTrueques.add(buttonHA, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 180, -1));

        buttonAH.setBackground(new java.awt.Color(0, 0, 0));
        buttonAH.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonAH.setForeground(new java.awt.Color(255, 255, 255));
        buttonAH.setText("ÁRBOL X HORTALIZA");
        buttonAH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAHActionPerformed(evt);
            }
        });
        panelTrueques.add(buttonAH, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 180, -1));

        buttonCancelarTrueque.setBackground(new java.awt.Color(0, 0, 0));
        buttonCancelarTrueque.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        buttonCancelarTrueque.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelarTrueque.setText("CANCELAR");
        buttonCancelarTrueque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarTruequeActionPerformed(evt);
            }
        });
        panelTrueques.add(buttonCancelarTrueque, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, 190, 70));

        jSeparator3.setBackground(new java.awt.Color(46, 53, 50));
        jSeparator3.setForeground(new java.awt.Color(46, 53, 50));
        panelTrueques.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 810, 10));

        jSeparator4.setBackground(new java.awt.Color(46, 53, 50));
        jSeparator4.setForeground(new java.awt.Color(46, 53, 50));
        panelTrueques.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 810, 10));

        jSeparator5.setBackground(new java.awt.Color(46, 53, 50));
        jSeparator5.setForeground(new java.awt.Color(46, 53, 50));
        panelTrueques.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 810, 10));

        truequeP2.setBackground(new java.awt.Color(46, 53, 50));
        truequeP2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        truequeP2.setForeground(new java.awt.Color(255, 255, 255));
        truequeP2.setModel(modeloTruequesP2);
        panelTrueques.add(truequeP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 200, -1));

        jLabel38.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("PLANTA");
        panelTrueques.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 140, -1));

        jLabel35.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("MI PLANTA");
        panelTrueques.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 140, -1));

        buttonRealizarTrueque.setBackground(new java.awt.Color(0, 0, 0));
        buttonRealizarTrueque.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        buttonRealizarTrueque.setForeground(new java.awt.Color(255, 255, 255));
        buttonRealizarTrueque.setText("REALIZAR TRUEQUE");
        buttonRealizarTrueque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRealizarTruequeActionPerformed(evt);
            }
        });
        panelTrueques.add(buttonRealizarTrueque, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 190, 70));

        jLabel39.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("CAMBIAR POR --->");
        panelTrueques.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 140, -1));

        jToggleButton1.setText("CREAR");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        panelTrueques.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 70, -1));

        panelVentas.setBackground(new java.awt.Color(19, 111, 99));
        panelVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("TIPO DE COMPRA");
        panelVentas.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 210, -1));

        buttonVentaH.setBackground(new java.awt.Color(46, 53, 50));
        buttonVentaH.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonVentaH.setForeground(new java.awt.Color(255, 255, 255));
        buttonVentaH.setText("HORTALIZA");
        buttonVentaH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVentaHActionPerformed(evt);
            }
        });
        panelVentas.add(buttonVentaH, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 180, -1));

        buttonVentaA.setBackground(new java.awt.Color(46, 53, 50));
        buttonVentaA.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonVentaA.setForeground(new java.awt.Color(255, 255, 255));
        buttonVentaA.setText("ÁRBOL");
        buttonVentaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVentaAActionPerformed(evt);
            }
        });
        panelVentas.add(buttonVentaA, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 180, -1));

        jSeparator7.setBackground(new java.awt.Color(46, 53, 50));
        jSeparator7.setForeground(new java.awt.Color(46, 53, 50));
        panelVentas.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 810, 10));

        jSeparator8.setBackground(new java.awt.Color(46, 53, 50));
        jSeparator8.setForeground(new java.awt.Color(46, 53, 50));
        panelVentas.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 810, 10));

        ventaPlantas.setBackground(new java.awt.Color(46, 53, 50));
        ventaPlantas.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        ventaPlantas.setForeground(new java.awt.Color(255, 255, 255));
        ventaPlantas.setModel(modeloVentas);
        panelVentas.add(ventaPlantas, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, 200, -1));

        jLabel41.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("PLANTA");
        panelVentas.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, 140, -1));

        buttonCancelarVenta.setBackground(new java.awt.Color(0, 0, 0));
        buttonCancelarVenta.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        buttonCancelarVenta.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancelarVenta.setText("CANCELAR COMPRA");
        buttonCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarVentaActionPerformed(evt);
            }
        });
        panelVentas.add(buttonCancelarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 190, 70));

        buttonRealizarVenta.setBackground(new java.awt.Color(0, 0, 0));
        buttonRealizarVenta.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        buttonRealizarVenta.setForeground(new java.awt.Color(255, 255, 255));
        buttonRealizarVenta.setText("REALIZAR COMPRA");
        buttonRealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRealizarVentaActionPerformed(evt);
            }
        });
        panelVentas.add(buttonRealizarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 190, 70));

        opcionesAdministrador.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        opcionesAdministrador.setTitle("ADMINISTRADOR");
        opcionesAdministrador.setBackground(new java.awt.Color(102, 0, 102));
        opcionesAdministrador.setResizable(false);
        opcionesAdministrador.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(184, 139, 74));

        buttonBitacora.setBackground(new java.awt.Color(0, 0, 0));
        buttonBitacora.setForeground(new java.awt.Color(255, 255, 255));
        buttonBitacora.setText("VER BITÁCORA");
        buttonBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBitacoraActionPerformed(evt);
            }
        });

        buttonConsultas.setBackground(new java.awt.Color(0, 0, 0));
        buttonConsultas.setForeground(new java.awt.Color(255, 255, 255));
        buttonConsultas.setText("VER CONSULTAS");
        buttonConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConsultasActionPerformed(evt);
            }
        });

        buttonAuditoria.setBackground(new java.awt.Color(0, 0, 0));
        buttonAuditoria.setForeground(new java.awt.Color(255, 255, 255));
        buttonAuditoria.setText("VER AUDITORÍA");
        buttonAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAuditoriaActionPerformed(evt);
            }
        });

        buttonAdDatos.setBackground(new java.awt.Color(0, 0, 0));
        buttonAdDatos.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdDatos.setText("ADMINISTRAR DATOS");
        buttonAdDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdDatosActionPerformed(evt);
            }
        });

        buttonSalirAdmin.setBackground(new java.awt.Color(0, 0, 0));
        buttonSalirAdmin.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalirAdmin.setText("SALIR");
        buttonSalirAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirAdminActionPerformed(evt);
            }
        });

        buttonEstadisticas.setBackground(new java.awt.Color(0, 0, 0));
        buttonEstadisticas.setForeground(new java.awt.Color(255, 255, 255));
        buttonEstadisticas.setText("VER ESTADÍSTICAS");
        buttonEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEstadisticasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAdDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAuditoria, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(buttonSalirAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(buttonBitacora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEstadisticas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonConsultas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAuditoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAdDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(buttonSalirAdmin)
                .addGap(60, 60, 60))
        );

        opcionesAdministrador.getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 340));

        dialogEstadisticas.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogEstadisticas.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(162, 112, 53));

        buttonSalirEstadisticas.setBackground(new java.awt.Color(0, 0, 0));
        buttonSalirEstadisticas.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalirEstadisticas.setText("SALIR");
        buttonSalirEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirEstadisticasActionPerformed(evt);
            }
        });

        panelGrafico.setBackground(new java.awt.Color(46, 53, 50));
        panelGrafico.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REPORTE ESTADÍSTICAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nirmala UI Semilight", 0, 12))); // NOI18N
        panelGrafico.setLayout(new java.awt.CardLayout());

        jComboBox1.setModel(modeloEstadistica);
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 76, Short.MAX_VALUE)
                        .addComponent(buttonSalirEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSalirEstadisticas)))
                .addContainerGap())
        );

        javax.swing.GroupLayout dialogEstadisticasLayout = new javax.swing.GroupLayout(dialogEstadisticas.getContentPane());
        dialogEstadisticas.getContentPane().setLayout(dialogEstadisticasLayout);
        dialogEstadisticasLayout.setHorizontalGroup(
            dialogEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogEstadisticasLayout.setVerticalGroup(
            dialogEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dialogBitacora.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogBitacora.setResizable(false);

        jPanel6.setBackground(new java.awt.Color(162, 112, 53));

        buttonSalirBitacora.setBackground(new java.awt.Color(0, 0, 0));
        buttonSalirBitacora.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalirBitacora.setText("SALIR");
        buttonSalirBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirBitacoraActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TABLA DE ABONOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nirmala UI", 0, 14))); // NOI18N
        jPanel11.setOpaque(false);

        jScrollPane5.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane5.setForeground(new java.awt.Color(255, 255, 255));

        tableBitacora.setAutoCreateRowSorter(true);
        tableBitacora.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        tableBitacora.setModel(modeloBitacora);
        tableBitacora.setEnabled(false);
        tableBitacora.setSelectionBackground(new java.awt.Color(153, 153, 153));
        jScrollPane5.setViewportView(tableBitacora);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(buttonSalirBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonSalirBitacora))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout dialogBitacoraLayout = new javax.swing.GroupLayout(dialogBitacora.getContentPane());
        dialogBitacora.getContentPane().setLayout(dialogBitacoraLayout);
        dialogBitacoraLayout.setHorizontalGroup(
            dialogBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogBitacoraLayout.setVerticalGroup(
            dialogBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dialogConsultas.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogConsultas.setResizable(false);

        jPanel7.setBackground(new java.awt.Color(162, 112, 53));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonSalirConsultas.setBackground(new java.awt.Color(0, 0, 0));
        buttonSalirConsultas.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalirConsultas.setText("SALIR");
        buttonSalirConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirConsultasActionPerformed(evt);
            }
        });
        jPanel7.add(buttonSalirConsultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 460, 89, -1));

        panelNombreConsultas.setBackground(new java.awt.Color(0, 0, 0));
        panelNombreConsultas.setBorder(javax.swing.BorderFactory.createTitledBorder("CONSULTA"));
        panelNombreConsultas.setOpaque(false);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea2);

        javax.swing.GroupLayout panelNombreConsultasLayout = new javax.swing.GroupLayout(panelNombreConsultas);
        panelNombreConsultas.setLayout(panelNombreConsultasLayout);
        panelNombreConsultasLayout.setHorizontalGroup(
            panelNombreConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
        );
        panelNombreConsultasLayout.setVerticalGroup(
            panelNombreConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        jPanel7.add(panelNombreConsultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 478));

        buttonCambiarAdUsuario1.setBackground(new java.awt.Color(0, 0, 0));
        buttonCambiarAdUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        buttonCambiarAdUsuario1.setText("CAMBIAR");
        buttonCambiarAdUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCambiarAdUsuario1ActionPerformed(evt);
            }
        });
        jPanel7.add(buttonCambiarAdUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 90, -1));

        jLabel43.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("TIPO CONSULTA");
        jPanel7.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 210, -1));

        usuariosAdministrar1.setBackground(new java.awt.Color(46, 53, 50));
        usuariosAdministrar1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        usuariosAdministrar1.setForeground(new java.awt.Color(255, 255, 255));
        usuariosAdministrar1.setModel(modeloConsultasBox);
        usuariosAdministrar1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                usuariosAdministrar1ItemStateChanged(evt);
            }
        });
        jPanel7.add(usuariosAdministrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 210, -1));

        buttonVerAdUsuario1.setBackground(new java.awt.Color(0, 0, 0));
        buttonVerAdUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        buttonVerAdUsuario1.setText("VER");
        buttonVerAdUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerAdUsuario1ActionPerformed(evt);
            }
        });
        jPanel7.add(buttonVerAdUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 90, -1));

        panelFiltrosConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder("FILTROS"));
        panelFiltrosConsulta.setOpaque(false);
        panelFiltrosConsulta.setLayout(new java.awt.CardLayout());
        jPanel7.add(panelFiltrosConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 220, 300));

        javax.swing.GroupLayout dialogConsultasLayout = new javax.swing.GroupLayout(dialogConsultas.getContentPane());
        dialogConsultas.getContentPane().setLayout(dialogConsultasLayout);
        dialogConsultasLayout.setHorizontalGroup(
            dialogConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogConsultasLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dialogConsultasLayout.setVerticalGroup(
            dialogConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dialogAuditoria.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogAuditoria.setBackground(new java.awt.Color(162, 112, 53));
        dialogAuditoria.setResizable(false);

        jPanel8.setBackground(new java.awt.Color(162, 112, 53));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonAudHuertas.setBackground(new java.awt.Color(0, 0, 0));
        buttonAudHuertas.setForeground(new java.awt.Color(255, 255, 255));
        buttonAudHuertas.setText("HUERTAS");
        buttonAudHuertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAudHuertasActionPerformed(evt);
            }
        });
        jPanel8.add(buttonAudHuertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 54, 89, -1));

        javax.swing.GroupLayout panelAuditoriasLayout = new javax.swing.GroupLayout(panelAuditorias);
        panelAuditorias.setLayout(panelAuditoriasLayout);
        panelAuditoriasLayout.setHorizontalGroup(
            panelAuditoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        panelAuditoriasLayout.setVerticalGroup(
            panelAuditoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        jPanel8.add(panelAuditorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 450));

        buttonSalirAuditoria1.setBackground(new java.awt.Color(0, 0, 0));
        buttonSalirAuditoria1.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalirAuditoria1.setText("SALIR");
        buttonSalirAuditoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirAuditoria1ActionPerformed(evt);
            }
        });
        jPanel8.add(buttonSalirAuditoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, 89, -1));

        buttonAudGeneral.setBackground(new java.awt.Color(0, 0, 0));
        buttonAudGeneral.setForeground(new java.awt.Color(255, 255, 255));
        buttonAudGeneral.setText("GENERAL");
        buttonAudGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAudGeneralActionPerformed(evt);
            }
        });
        jPanel8.add(buttonAudGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 25, 89, -1));

        buttonAudUsuarios.setBackground(new java.awt.Color(0, 0, 0));
        buttonAudUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        buttonAudUsuarios.setText("USUARIOS");
        buttonAudUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAudUsuariosActionPerformed(evt);
            }
        });
        jPanel8.add(buttonAudUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 83, 89, -1));

        javax.swing.GroupLayout dialogAuditoriaLayout = new javax.swing.GroupLayout(dialogAuditoria.getContentPane());
        dialogAuditoria.getContentPane().setLayout(dialogAuditoriaLayout);
        dialogAuditoriaLayout.setHorizontalGroup(
            dialogAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogAuditoriaLayout.setVerticalGroup(
            dialogAuditoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dialogAdDatos.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogAdDatos.setBackground(new java.awt.Color(162, 112, 53));
        dialogAdDatos.setResizable(false);

        jPanel9.setBackground(new java.awt.Color(162, 112, 53));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonSalirAdDatos.setBackground(new java.awt.Color(0, 0, 0));
        buttonSalirAdDatos.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalirAdDatos.setText("SALIR");
        buttonSalirAdDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirAdDatosActionPerformed(evt);
            }
        });
        jPanel9.add(buttonSalirAdDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 480, 89, -1));

        panelAdDatos.setBackground(new java.awt.Color(255, 102, 204));
        panelAdDatos.setLayout(new java.awt.CardLayout());
        jPanel9.add(panelAdDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 508));

        buttonAdCatalogos.setBackground(new java.awt.Color(0, 0, 0));
        buttonAdCatalogos.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdCatalogos.setText("CATÁLOGOS");
        buttonAdCatalogos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdCatalogosActionPerformed(evt);
            }
        });
        jPanel9.add(buttonAdCatalogos, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 95, -1));

        buttonAdUsuarios.setBackground(new java.awt.Color(0, 0, 0));
        buttonAdUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdUsuarios.setText("USUARIOS");
        buttonAdUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdUsuariosActionPerformed(evt);
            }
        });
        jPanel9.add(buttonAdUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 95, -1));

        buttonAdHuertas.setBackground(new java.awt.Color(0, 0, 0));
        buttonAdHuertas.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdHuertas.setText("HUERTAS");
        buttonAdHuertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdHuertasActionPerformed(evt);
            }
        });
        jPanel9.add(buttonAdHuertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, 95, -1));

        buttonAdHortalizas.setBackground(new java.awt.Color(0, 0, 0));
        buttonAdHortalizas.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdHortalizas.setText("HORTALIZAS");
        buttonAdHortalizas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdHortalizasActionPerformed(evt);
            }
        });
        jPanel9.add(buttonAdHortalizas, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, -1, -1));

        buttonAdArboles.setBackground(new java.awt.Color(0, 0, 0));
        buttonAdArboles.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdArboles.setText("ÁRBOLES");
        buttonAdArboles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdArbolesActionPerformed(evt);
            }
        });
        jPanel9.add(buttonAdArboles, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 95, -1));

        buttonAdAbonos.setBackground(new java.awt.Color(0, 0, 0));
        buttonAdAbonos.setForeground(new java.awt.Color(255, 255, 255));
        buttonAdAbonos.setText("ABONOS");
        buttonAdAbonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdAbonosActionPerformed(evt);
            }
        });
        jPanel9.add(buttonAdAbonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 95, -1));

        javax.swing.GroupLayout dialogAdDatosLayout = new javax.swing.GroupLayout(dialogAdDatos.getContentPane());
        dialogAdDatos.getContentPane().setLayout(dialogAdDatosLayout);
        dialogAdDatosLayout.setHorizontalGroup(
            dialogAdDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogAdDatosLayout.setVerticalGroup(
            dialogAdDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );

        dialogComentarios.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogComentarios.setResizable(false);

        jPanel10.setBackground(new java.awt.Color(162, 112, 53));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonSalirComentarios.setBackground(new java.awt.Color(0, 0, 0));
        buttonSalirComentarios.setForeground(new java.awt.Color(255, 255, 255));
        buttonSalirComentarios.setText("SALIR");
        buttonSalirComentarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirComentariosActionPerformed(evt);
            }
        });
        jPanel10.add(buttonSalirComentarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 466, 89, -1));

        labelCalificacion1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        labelCalificacion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCalificacion1.setText("COMENTARIO:");
        jPanel10.add(labelCalificacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 290, -1));

        sliderCalificacion1.setBackground(new java.awt.Color(0, 0, 0));
        sliderCalificacion1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        sliderCalificacion1.setForeground(new java.awt.Color(255, 255, 255));
        sliderCalificacion1.setMaximum(5);
        sliderCalificacion1.setMinimum(1);
        sliderCalificacion1.setPaintTicks(true);
        sliderCalificacion1.setFocusable(false);
        sliderCalificacion1.setOpaque(false);
        sliderCalificacion1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderCalificacion1StateChanged(evt);
            }
        });
        jPanel10.add(sliderCalificacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 170, 20));

        jScrollPane14.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane14.setAutoscrolls(true);

        comentarioUsuario.setBackground(new java.awt.Color(46, 53, 50));
        comentarioUsuario.setColumns(20);
        comentarioUsuario.setForeground(new java.awt.Color(255, 255, 255));
        comentarioUsuario.setLineWrap(true);
        comentarioUsuario.setRows(5);
        comentarioUsuario.setWrapStyleWord(true);
        comentarioUsuario.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        comentarioUsuario.setSelectionColor(new java.awt.Color(180, 184, 171));
        jScrollPane14.setViewportView(comentarioUsuario);

        jPanel10.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 290, 60));

        labelCalificacion2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        labelCalificacion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCalificacion2.setText("CALIFICACIÓN:");
        jPanel10.add(labelCalificacion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 290, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DEJAR MI COMENTARIO Y CALIFICACIÓN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nirmala UI", 0, 10))); // NOI18N
        jPanel1.setOpaque(false);

        buttonEnviarCalificacion.setBackground(new java.awt.Color(0, 0, 0));
        buttonEnviarCalificacion.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        buttonEnviarCalificacion.setForeground(new java.awt.Color(255, 255, 255));
        buttonEnviarCalificacion.setText("ENVIAR");
        buttonEnviarCalificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnviarCalificacionActionPerformed(evt);
            }
        });

        calificacionUsuario.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        calificacionUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        calificacionUsuario.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonEnviarCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(calificacionUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(calificacionUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(buttonEnviarCalificacion)
                .addGap(20, 20, 20))
        );

        jPanel10.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 310, 280));

        jScrollPane15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "COMENTARIOS DE LA HUERTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nirmala UI", 0, 11))); // NOI18N

        comentariosHuerta.setColumns(20);
        comentariosHuerta.setLineWrap(true);
        comentariosHuerta.setRows(5);
        comentariosHuerta.setWrapStyleWord(true);
        jScrollPane15.setViewportView(comentariosHuerta);

        jPanel10.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 470));

        javax.swing.GroupLayout dialogComentariosLayout = new javax.swing.GroupLayout(dialogComentarios.getContentPane());
        dialogComentarios.getContentPane().setLayout(dialogComentariosLayout);
        dialogComentariosLayout.setHorizontalGroup(
            dialogComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        dialogComentariosLayout.setVerticalGroup(
            dialogComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        jTable3.setModel(modeloAudUsuarios);
        jScrollPane10.setViewportView(jTable3);

        javax.swing.GroupLayout panelAudUsuariosLayout = new javax.swing.GroupLayout(panelAudUsuarios);
        panelAudUsuarios.setLayout(panelAudUsuariosLayout);
        panelAudUsuariosLayout.setHorizontalGroup(
            panelAudUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        panelAudUsuariosLayout.setVerticalGroup(
            panelAudUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
        );

        tableAudHuertas.setModel(modeloAudHuertas);
        jScrollPane9.setViewportView(tableAudHuertas);

        javax.swing.GroupLayout panelAudHuertasLayout = new javax.swing.GroupLayout(panelAudHuertas);
        panelAudHuertas.setLayout(panelAudHuertasLayout);
        panelAudHuertasLayout.setHorizontalGroup(
            panelAudHuertasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
        );
        panelAudHuertasLayout.setVerticalGroup(
            panelAudHuertasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );

        tableAudGeneral.setModel(modeloAudGeneral);
        tableAudGeneral.setEnabled(false);
        jScrollPane8.setViewportView(tableAudGeneral);

        javax.swing.GroupLayout panelAudGeneralLayout = new javax.swing.GroupLayout(panelAudGeneral);
        panelAudGeneral.setLayout(panelAudGeneralLayout);
        panelAudGeneralLayout.setHorizontalGroup(
            panelAudGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        panelAudGeneralLayout.setVerticalGroup(
            panelAudGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
        );

        panelAdCatalogos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        colorAdministrar.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar.setModel(modeloColor
        );
        panelAdCatalogos.add(colorAdministrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 210, -1));

        buttonDesactivarColor.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColorActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 80, 20));

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HORTALIZAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("COLOR");
        jPanel18.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor.setText("EDITAR");
        buttonAgregarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColorActionPerformed(evt);
            }
        });
        jPanel18.add(buttonAgregarColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor1.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor1.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor1.setText("AGREGAR");
        buttonAgregarColor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor1ActionPerformed(evt);
            }
        });
        jPanel18.add(buttonAgregarColor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 420, 80));

        colorAdministrar1.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar1.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar1.setModel(modeloTipo        );
        panelAdCatalogos.add(colorAdministrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 210, -1));

        buttonDesactivarColor1.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor1.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor1.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor1ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 80, 20));

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HORTALIZAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel77.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("TIPO");
        jPanel25.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor2.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor2.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor2.setText("EDITAR");
        buttonAgregarColor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor2ActionPerformed(evt);
            }
        });
        jPanel25.add(buttonAgregarColor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor3.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor3.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor3.setText("AGREGAR");
        buttonAgregarColor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor3ActionPerformed(evt);
            }
        });
        jPanel25.add(buttonAgregarColor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 420, 80));

        colorAdministrar2.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar2.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar2.setModel(modeloPropiedad
        );
        panelAdCatalogos.add(colorAdministrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 210, -1));

        buttonDesactivarColor2.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor2.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor2.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor2ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 80, 20));

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HORTALIZAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel78.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("PROPIEDAD");
        jPanel26.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor4.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor4.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor4.setText("EDITAR");
        buttonAgregarColor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor4ActionPerformed(evt);
            }
        });
        jPanel26.add(buttonAgregarColor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor5.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor5.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor5.setText("AGREGAR");
        buttonAgregarColor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor5ActionPerformed(evt);
            }
        });
        jPanel26.add(buttonAgregarColor5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 420, 80));

        colorAdministrar3.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar3.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar3.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar3.setModel(modeloCaracteristica);
        panelAdCatalogos.add(colorAdministrar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 210, -1));

        buttonDesactivarColor3.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor3.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor3.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor3ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 80, 20));

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HORTALIZAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel79.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("CARACTERÍSTICA");
        jPanel27.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor6.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor6.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor6.setText("EDITAR");
        buttonAgregarColor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor6ActionPerformed(evt);
            }
        });
        jPanel27.add(buttonAgregarColor6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor7.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor7.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor7.setText("AGREGAR");
        buttonAgregarColor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor7ActionPerformed(evt);
            }
        });
        jPanel27.add(buttonAgregarColor7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 420, 80));

        colorAdministrar4.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar4.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar4.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar4.setModel(modeloReproduccion
        );
        panelAdCatalogos.add(colorAdministrar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 210, -1));

        buttonDesactivarColor4.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor4.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor4.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor4ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 80, 20));

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÁRBOLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel80.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("REPRODUCCIÓN");
        jPanel28.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor8.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor8.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor8.setText("EDITAR");
        buttonAgregarColor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor8ActionPerformed(evt);
            }
        });
        jPanel28.add(buttonAgregarColor8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor9.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor9.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor9.setText("AGREGAR");
        buttonAgregarColor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor9ActionPerformed(evt);
            }
        });
        jPanel28.add(buttonAgregarColor9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 420, 80));

        colorAdministrar5.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar5.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar5.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar5.setModel(modeloXilema
        );
        panelAdCatalogos.add(colorAdministrar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 210, -1));

        buttonDesactivarColor5.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor5.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor5.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor5ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 80, 20));

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÁRBOLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel81.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("XILEMA");
        jPanel29.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor10.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor10.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor10.setText("EDITAR");
        buttonAgregarColor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor10ActionPerformed(evt);
            }
        });
        jPanel29.add(buttonAgregarColor10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor11.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor11.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor11.setText("AGREGAR");
        buttonAgregarColor11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor11ActionPerformed(evt);
            }
        });
        jPanel29.add(buttonAgregarColor11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 420, 80));

        colorAdministrar6.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar6.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar6.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar6.setModel(modeloCambium        );
        panelAdCatalogos.add(colorAdministrar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 210, -1));

        buttonDesactivarColor6.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor6.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor6.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor6ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 80, 20));

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÁRBOLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel82.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("CAMBIUM");
        jPanel30.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor12.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor12.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor12.setText("EDITAR");
        buttonAgregarColor12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor12ActionPerformed(evt);
            }
        });
        jPanel30.add(buttonAgregarColor12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor13.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor13.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor13.setText("AGREGAR");
        buttonAgregarColor13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor13ActionPerformed(evt);
            }
        });
        jPanel30.add(buttonAgregarColor13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 420, 80));

        colorAdministrar7.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar7.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar7.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar7.setModel(modeloCorteza
        );
        panelAdCatalogos.add(colorAdministrar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 210, -1));

        buttonDesactivarColor7.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor7.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor7.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor7ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, 80, 20));

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ÁRBOLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel83.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("CORTEZA");
        jPanel31.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor14.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor14.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor14.setText("EDITAR");
        buttonAgregarColor14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor14ActionPerformed(evt);
            }
        });
        jPanel31.add(buttonAgregarColor14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor15.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor15.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor15.setText("AGREGAR");
        buttonAgregarColor15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor15ActionPerformed(evt);
            }
        });
        jPanel31.add(buttonAgregarColor15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 420, 80));

        colorAdministrar8.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar8.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar8.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar8.setModel(modeloPais        );
        panelAdCatalogos.add(colorAdministrar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 210, -1));

        buttonDesactivarColor8.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor8.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor8.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor8ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor8, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 80, 20));

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PERSONA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel84.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("PAÍS");
        jPanel32.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor16.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor16.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor16.setText("EDITAR");
        buttonAgregarColor16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor16ActionPerformed(evt);
            }
        });
        jPanel32.add(buttonAgregarColor16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor17.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor17.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor17.setText("AGREGAR");
        buttonAgregarColor17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor17ActionPerformed(evt);
            }
        });
        jPanel32.add(buttonAgregarColor17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 420, 80));

        colorAdministrar9.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar9.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar9.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar9.setModel(modeloProvincia
        );
        panelAdCatalogos.add(colorAdministrar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 210, -1));

        buttonDesactivarColor9.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor9.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor9.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor9ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, 80, 20));

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PERSONA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel85.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("PROVINCIA");
        jPanel33.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor18.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor18.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor18.setText("EDITAR");
        buttonAgregarColor18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor18ActionPerformed(evt);
            }
        });
        jPanel33.add(buttonAgregarColor18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor19.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor19.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor19.setText("AGREGAR");
        buttonAgregarColor19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor19ActionPerformed(evt);
            }
        });
        jPanel33.add(buttonAgregarColor19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 420, 80));

        colorAdministrar10.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar10.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar10.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar10.setModel(modeloCanton        );
        panelAdCatalogos.add(colorAdministrar10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, 210, -1));

        buttonDesactivarColor10.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor10.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor10.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor10ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 380, 80, 20));

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PERSONA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel86.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("CANTÓN");
        jPanel34.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor20.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor20.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor20.setText("EDITAR");
        buttonAgregarColor20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor20ActionPerformed(evt);
            }
        });
        jPanel34.add(buttonAgregarColor20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor21.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor21.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor21.setText("AGREGAR");
        buttonAgregarColor21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor21ActionPerformed(evt);
            }
        });
        jPanel34.add(buttonAgregarColor21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, 420, 80));

        colorAdministrar11.setBackground(new java.awt.Color(46, 53, 50));
        colorAdministrar11.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        colorAdministrar11.setForeground(new java.awt.Color(255, 255, 255));
        colorAdministrar11.setModel(modeloDistrito        );
        panelAdCatalogos.add(colorAdministrar11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 460, 210, -1));

        buttonDesactivarColor11.setBackground(new java.awt.Color(0, 0, 0));
        buttonDesactivarColor11.setForeground(new java.awt.Color(255, 255, 255));
        buttonDesactivarColor11.setText("ACTIVAR/DESACTIVAR");
        buttonDesactivarColor11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDesactivarColor11ActionPerformed(evt);
            }
        });
        panelAdCatalogos.add(buttonDesactivarColor11, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 460, 80, 20));

        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PERSONA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 8))); // NOI18N
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel87.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 12)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("DISTRITO");
        jPanel35.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 13, 210, -1));

        buttonAgregarColor22.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor22.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor22.setText("EDITAR");
        buttonAgregarColor22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor22ActionPerformed(evt);
            }
        });
        jPanel35.add(buttonAgregarColor22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 80, 20));

        buttonAgregarColor23.setBackground(new java.awt.Color(0, 0, 0));
        buttonAgregarColor23.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregarColor23.setText("AGREGAR");
        buttonAgregarColor23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarColor23ActionPerformed(evt);
            }
        });
        jPanel35.add(buttonAgregarColor23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 20));

        panelAdCatalogos.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 420, 80));

        panelAdUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonCambiarAdUsuario.setBackground(new java.awt.Color(0, 0, 0));
        buttonCambiarAdUsuario.setForeground(new java.awt.Color(255, 255, 255));
        buttonCambiarAdUsuario.setText("CAMBIAR");
        buttonCambiarAdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCambiarAdUsuarioActionPerformed(evt);
            }
        });
        panelAdUsuarios.add(buttonCambiarAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, -1, -1));

        nombreAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        nombreAdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreAdUsuarioActionPerformed(evt);
            }
        });
        panelAdUsuarios.add(nombreAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 220, -1));

        emailAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        panelAdUsuarios.add(emailAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 220, -1));

        cedulaAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        cedulaAdUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaAdUsuarioKeyTyped(evt);
            }
        });
        panelAdUsuarios.add(cedulaAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 220, -1));

        apellidoAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        panelAdUsuarios.add(apellidoAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 220, -1));

        telefonoAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        panelAdUsuarios.add(telefonoAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 220, -1));

        usuarioAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        panelAdUsuarios.add(usuarioAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 220, -1));
        panelAdUsuarios.add(passwordAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 220, -1));

        jLabel10.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel10.setText("Contraseña:");
        panelAdUsuarios.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jLabel11.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel11.setText("Usuario:");
        panelAdUsuarios.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel4.setText("Teléfono:");
        panelAdUsuarios.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel1.setText("Apellido:");
        panelAdUsuarios.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel2.setText("Nombre:");
        panelAdUsuarios.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel3.setText("E-mail:");
        panelAdUsuarios.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel13.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
        jLabel13.setText("Cédula:");
        panelAdUsuarios.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        fechaAdUsuario.setCurrentView(new datechooser.view.appearance.AppearancesList("Dali",
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
    fechaAdUsuario.setCalendarBackground(new java.awt.Color(0, 0, 0));
    fechaAdUsuario.setCalendarPreferredSize(new java.awt.Dimension(390, 200));
    fechaAdUsuario.setNothingAllowed(false);
    fechaAdUsuario.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    fechaAdUsuario.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
    fechaAdUsuario.setLocale(new java.util.Locale("es", "", ""));
    fechaAdUsuario.setMaxDate(new java.util.GregorianCalendar(2018, 4, 6));
    fechaAdUsuario.setMinDate(new java.util.GregorianCalendar(1900, 0, 1));
    fechaAdUsuario.setNavigateFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
    fechaAdUsuario.setCurrentNavigateIndex(0);
    fechaAdUsuario.setShowOneMonth(true);
    panelAdUsuarios.add(fechaAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 140, -1));

    jLabel15.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel15.setText("USUARIOS EXISTENTES");
    panelAdUsuarios.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, -1, -1));

    huertaAdUsuario.setModel(modeloHuertas);
    panelAdUsuarios.add(huertaAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 140, -1));

    dedicacionAdUsuario.setModel(modeloDedicacion);
    panelAdUsuarios.add(dedicacionAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 140, -1));

    jLabel16.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel16.setText("Dedicación:");
    panelAdUsuarios.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

    nacionalidadAdUsuario.setModel(modeloNacionalidad);
    panelAdUsuarios.add(nacionalidadAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 140, -1));

    jLabel21.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel21.setText("Nacionalidad:");
    panelAdUsuarios.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

    jLabel22.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel22.setText("Fecha de nacimiento:");
    panelAdUsuarios.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

    fotoAdUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    fotoAdUsuario.setText("FOTO");
    fotoAdUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    fotoAdUsuario.setPreferredSize(new java.awt.Dimension(250, 250));
    panelAdUsuarios.add(fotoAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 300, 270));

    elegirArchivo.setBackground(new java.awt.Color(46, 53, 50));
    elegirArchivo.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    elegirArchivo.setForeground(new java.awt.Color(204, 204, 204));
    elegirArchivo.setText("Elegir Archivo...");
    elegirArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    elegirArchivo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            elegirArchivoActionPerformed(evt);
        }
    });
    panelAdUsuarios.add(elegirArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, 100, -1));

    buttonCrearAdUsuario.setBackground(new java.awt.Color(0, 0, 0));
    buttonCrearAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCrearAdUsuario.setForeground(new java.awt.Color(255, 255, 255));
    buttonCrearAdUsuario.setText("CREAR");
    buttonCrearAdUsuario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCrearAdUsuarioActionPerformed(evt);
        }
    });
    panelAdUsuarios.add(buttonCrearAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 110, -1));

    buttonModificarAdUsuario.setBackground(new java.awt.Color(0, 0, 0));
    buttonModificarAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonModificarAdUsuario.setForeground(new java.awt.Color(255, 255, 255));
    buttonModificarAdUsuario.setText("MODIFICAR");
    buttonModificarAdUsuario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonModificarAdUsuarioActionPerformed(evt);
        }
    });
    panelAdUsuarios.add(buttonModificarAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 110, -1));

    usuariosAdministrar.setBackground(new java.awt.Color(46, 53, 50));
    usuariosAdministrar.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    usuariosAdministrar.setForeground(new java.awt.Color(255, 255, 255));
    usuariosAdministrar.setModel(modeloUsuarios);
    panelAdUsuarios.add(usuariosAdministrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 440, 260, -1));

    buttonVerAdUsuario.setBackground(new java.awt.Color(0, 0, 0));
    buttonVerAdUsuario.setForeground(new java.awt.Color(255, 255, 255));
    buttonVerAdUsuario.setText("VER");
    buttonVerAdUsuario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonVerAdUsuarioActionPerformed(evt);
        }
    });
    panelAdUsuarios.add(buttonVerAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 70, -1));

    jLabel26.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel26.setText("Huerta:");
    panelAdUsuarios.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, -1, -1));

    buttonAceptarAdUsuario.setBackground(new java.awt.Color(0, 0, 0));
    buttonAceptarAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonAceptarAdUsuario.setForeground(new java.awt.Color(255, 255, 255));
    buttonAceptarAdUsuario.setText("ACEPTAR");
    buttonAceptarAdUsuario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAceptarAdUsuarioActionPerformed(evt);
        }
    });
    panelAdUsuarios.add(buttonAceptarAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 110, -1));

    buttonCancelarAdUsuario.setBackground(new java.awt.Color(0, 0, 0));
    buttonCancelarAdUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCancelarAdUsuario.setForeground(new java.awt.Color(255, 255, 255));
    buttonCancelarAdUsuario.setText("CANCELAR");
    buttonCancelarAdUsuario.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCancelarAdUsuarioActionPerformed(evt);
        }
    });
    panelAdUsuarios.add(buttonCancelarAdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 440, 110, -1));

    panelAdHuertas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelMap1.setBackground(new java.awt.Color(0, 0, 0));
    panelMap1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GEOLOCALIZACIÓN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Nirmala UI Semilight", 0, 11))); // NOI18N
    panelMap1.setForeground(new java.awt.Color(255, 255, 255));
    panelMap1.setOpaque(false);
    panelMap1.setLayout(new java.awt.BorderLayout());
    panelAdHuertas.add(panelMap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 520, 410));

    buttonCambiarAdHuerta.setBackground(new java.awt.Color(0, 0, 0));
    buttonCambiarAdHuerta.setForeground(new java.awt.Color(255, 255, 255));
    buttonCambiarAdHuerta.setText("CAMBIAR");
    buttonCambiarAdHuerta.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCambiarAdHuertaActionPerformed(evt);
        }
    });
    panelAdHuertas.add(buttonCambiarAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

    huertasAdministrar.setBackground(new java.awt.Color(46, 53, 50));
    huertasAdministrar.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    huertasAdministrar.setForeground(new java.awt.Color(255, 255, 255));
    huertasAdministrar.setModel(modeloHuertas);
    panelAdHuertas.add(huertasAdministrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 260, -1));

    buttonVerAdHuerta.setBackground(new java.awt.Color(0, 0, 0));
    buttonVerAdHuerta.setForeground(new java.awt.Color(255, 255, 255));
    buttonVerAdHuerta.setText("VER");
    buttonVerAdHuerta.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonVerAdHuertaActionPerformed(evt);
        }
    });
    panelAdHuertas.add(buttonVerAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 70, -1));

    nombreAdHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    nombreAdHuerta.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nombreAdHuertaActionPerformed(evt);
        }
    });
    panelAdHuertas.add(nombreAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 220, -1));

    longitudAdHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    longitudAdHuerta.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            longitudAdHuertaKeyTyped(evt);
        }
    });
    panelAdHuertas.add(longitudAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 220, -1));

    latitudAdHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    panelAdHuertas.add(latitudAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 220, -1));

    jLabel5.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel5.setText("LATITUD:");
    panelAdHuertas.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, -1));

    jLabel31.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel31.setText("NOMBRE:");
    panelAdHuertas.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, -1, -1));

    jLabel32.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel32.setText("LONGITUD:");
    panelAdHuertas.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, -1));

    jLabel33.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel33.setText("HUERTAS EXISTENTES");
    panelAdHuertas.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

    jScrollPane11.setEnabled(false);
    jScrollPane11.setFocusable(false);
    jScrollPane11.setOpaque(false);

    jTextArea1.setEditable(false);
    jTextArea1.setColumns(1);
    jTextArea1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jTextArea1.setLineWrap(true);
    jTextArea1.setRows(1);
    jTextArea1.setText("Para saber las coordenadas de latitud y longitud utilice el mapa de al lado, dando click derecho y seleccionando la opción de \"¿Qué hay aquí?\". Abajo aparecerá el nombre del lugar con la lantitud y longitud respectivamente");
    jTextArea1.setWrapStyleWord(true);
    jTextArea1.setEnabled(false);
    jTextArea1.setFocusable(false);
    jTextArea1.setOpaque(false);
    jScrollPane11.setViewportView(jTextArea1);

    panelAdHuertas.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 290, 70));

    buttonCrearAdHuerta.setBackground(new java.awt.Color(0, 0, 0));
    buttonCrearAdHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCrearAdHuerta.setForeground(new java.awt.Color(255, 255, 255));
    buttonCrearAdHuerta.setText("CREAR");
    buttonCrearAdHuerta.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCrearAdHuertaActionPerformed(evt);
        }
    });
    panelAdHuertas.add(buttonCrearAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 110, -1));

    buttonModificarAdHuerta.setBackground(new java.awt.Color(0, 0, 0));
    buttonModificarAdHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonModificarAdHuerta.setForeground(new java.awt.Color(255, 255, 255));
    buttonModificarAdHuerta.setText("MODIFICAR");
    buttonModificarAdHuerta.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonModificarAdHuertaActionPerformed(evt);
        }
    });
    panelAdHuertas.add(buttonModificarAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, 110, -1));

    distritoAdHuerta.setModel(modeloDistrito);
    panelAdHuertas.add(distritoAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 220, -1));

    capitalAdHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    capitalAdHuerta.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            capitalAdHuertaKeyTyped(evt);
        }
    });
    panelAdHuertas.add(capitalAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 220, -1));

    jLabel48.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel48.setText("CAPITAL:");
    panelAdHuertas.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, -1, -1));

    jLabel49.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel49.setText("DISTRITO:");
    panelAdHuertas.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, -1, -1));

    buttonCancelarAdHuerta.setBackground(new java.awt.Color(0, 0, 0));
    buttonCancelarAdHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCancelarAdHuerta.setForeground(new java.awt.Color(255, 255, 255));
    buttonCancelarAdHuerta.setText("CANCELAR");
    buttonCancelarAdHuerta.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCancelarAdHuertaActionPerformed(evt);
        }
    });
    panelAdHuertas.add(buttonCancelarAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 110, -1));

    buttonAceptarAdHuerta.setBackground(new java.awt.Color(0, 0, 0));
    buttonAceptarAdHuerta.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonAceptarAdHuerta.setForeground(new java.awt.Color(255, 255, 255));
    buttonAceptarAdHuerta.setText("ACEPTAR");
    buttonAceptarAdHuerta.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAceptarAdHuertaActionPerformed(evt);
        }
    });
    panelAdHuertas.add(buttonAceptarAdHuerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 110, -1));

    panelAdHortalizas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    cantidadAdHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    cantidadAdHortaliza.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cantidadAdHortalizaActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(cantidadAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 130, -1));

    precioAdHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    panelAdHortalizas.add(precioAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 320, -1));

    jLabel27.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel27.setText("CANTIDAD:");
    panelAdHortalizas.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

    jLabel30.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel30.setText("TIPO:");
    panelAdHortalizas.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

    jLabel50.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel50.setText("HUERTA:");
    panelAdHortalizas.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

    jLabel51.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel51.setText("PRECIO:");
    panelAdHortalizas.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

    jLabel52.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel52.setText("NOMBRE:");
    panelAdHortalizas.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

    jLabel53.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel53.setText("COLOR:");
    panelAdHortalizas.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

    huertaAdHortaliza.setModel(modeloHuertas);
    panelAdHortalizas.add(huertaAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 270, -1));

    colorAdHortaliza.setModel(modeloColor        );
    panelAdHortalizas.add(colorAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 270, -1));

    tipoAdHortaliza.setModel(modeloTipo);
    panelAdHortalizas.add(tipoAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 270, -1));

    propiedadAdHortaliza.setModel(modeloPropiedad);
    panelAdHortalizas.add(propiedadAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 270, -1));

    caracteristicaAdHortaliza.setModel(modeloCaracteristica);
    panelAdHortalizas.add(caracteristicaAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 270, -1));

    jLabel61.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel61.setText("PROPIEDAD:");
    panelAdHortalizas.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

    jLabel62.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel62.setText("CARACTERÍSTICA:");
    panelAdHortalizas.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

    nombreAdHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    nombreAdHortaliza.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nombreAdHortalizaActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(nombreAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 320, -1));

    ojala1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    ojala1.setText("FOTO");
    ojala1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    ojala1.setPreferredSize(new java.awt.Dimension(250, 250));
    panelAdHortalizas.add(ojala1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 300, 270));

    jButton9.setBackground(new java.awt.Color(46, 53, 50));
    jButton9.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton9.setForeground(new java.awt.Color(204, 204, 204));
    jButton9.setText("Elegir Archivo...");
    jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton9ActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 100, -1));

    buttonCambiarAdHortaliza.setBackground(new java.awt.Color(0, 0, 0));
    buttonCambiarAdHortaliza.setForeground(new java.awt.Color(255, 255, 255));
    buttonCambiarAdHortaliza.setText("CAMBIAR");
    buttonCambiarAdHortaliza.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCambiarAdHortalizaActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(buttonCambiarAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, -1, -1));

    hortalizasAdHortaliza.setBackground(new java.awt.Color(46, 53, 50));
    hortalizasAdHortaliza.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    hortalizasAdHortaliza.setForeground(new java.awt.Color(255, 255, 255));
    hortalizasAdHortaliza.setModel(modeloHortalizasBox);
    panelAdHortalizas.add(hortalizasAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 260, -1));

    buttonVerAdHortaliza.setBackground(new java.awt.Color(0, 0, 0));
    buttonVerAdHortaliza.setForeground(new java.awt.Color(255, 255, 255));
    buttonVerAdHortaliza.setText("VER");
    buttonVerAdHortaliza.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonVerAdHortalizaActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(buttonVerAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 70, -1));

    jLabel63.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel63.setText("HORTALIZAS EXISTENTES");
    panelAdHortalizas.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, -1, -1));

    buttonAceptarAdHortaliza.setBackground(new java.awt.Color(0, 0, 0));
    buttonAceptarAdHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonAceptarAdHortaliza.setForeground(new java.awt.Color(255, 255, 255));
    buttonAceptarAdHortaliza.setText("ACEPTAR");
    buttonAceptarAdHortaliza.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAceptarAdHortalizaActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(buttonAceptarAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 110, -1));

    buttonCancelarAdHortaliza.setBackground(new java.awt.Color(0, 0, 0));
    buttonCancelarAdHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCancelarAdHortaliza.setForeground(new java.awt.Color(255, 255, 255));
    buttonCancelarAdHortaliza.setText("CANCELAR");
    buttonCancelarAdHortaliza.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCancelarAdHortalizaActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(buttonCancelarAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 440, 110, -1));

    buttonCrearAdHortaliza.setBackground(new java.awt.Color(0, 0, 0));
    buttonCrearAdHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCrearAdHortaliza.setForeground(new java.awt.Color(255, 255, 255));
    buttonCrearAdHortaliza.setText("CREAR");
    buttonCrearAdHortaliza.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCrearAdHortalizaActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(buttonCrearAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 110, -1));

    buttonModificarAdHortaliza.setBackground(new java.awt.Color(0, 0, 0));
    buttonModificarAdHortaliza.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonModificarAdHortaliza.setForeground(new java.awt.Color(255, 255, 255));
    buttonModificarAdHortaliza.setText("MODIFICAR");
    buttonModificarAdHortaliza.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonModificarAdHortalizaActionPerformed(evt);
        }
    });
    panelAdHortalizas.add(buttonModificarAdHortaliza, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 110, -1));

    panelAdArboles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    cantidadAdArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    cantidadAdArbol.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cantidadAdArbolActionPerformed(evt);
        }
    });
    panelAdArboles.add(cantidadAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 100, -1));

    precioAdArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    panelAdArboles.add(precioAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 320, -1));

    jLabel64.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel64.setText("CANTIDAD:");
    panelAdArboles.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

    jLabel65.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel65.setText("XILEMA:");
    panelAdArboles.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

    jLabel66.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel66.setText("HUERTA:");
    panelAdArboles.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

    jLabel67.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel67.setText("PRECIO:");
    panelAdArboles.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

    jLabel68.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel68.setText("NOMBRE:");
    panelAdArboles.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

    jLabel69.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel69.setText("REPRODUCCION:");
    panelAdArboles.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

    jLabel70.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel70.setText("EXTINCIÓN:");
    panelAdArboles.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

    groupHortalizas.add(jRadioButton3);
    jRadioButton3.setText("SÍ");
    panelAdArboles.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

    groupHortalizas.add(jRadioButton4);
    jRadioButton4.setText("NO");
    panelAdArboles.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, -1, -1));

    huertaAdArbol.setModel(modeloHuertas);
    panelAdArboles.add(huertaAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 270, -1));

    reproduccionAdArbol.setModel(modeloReproduccion);
    panelAdArboles.add(reproduccionAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 270, -1));

    xilemaAdArbol.setModel(modeloXilema);
    panelAdArboles.add(xilemaAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 270, -1));

    cambiumAdArbol.setModel(modeloCambium);
    panelAdArboles.add(cambiumAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 270, -1));

    cortezaAdArbol.setModel(modeloCorteza);
    panelAdArboles.add(cortezaAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 270, -1));

    jLabel71.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel71.setText("CAMBIUM:");
    panelAdArboles.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

    jLabel72.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel72.setText("CORTEZA:");
    panelAdArboles.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

    nombreAdArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    nombreAdArbol.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nombreAdArbolActionPerformed(evt);
        }
    });
    nombreAdArbol.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            nombreAdArbolKeyTyped(evt);
        }
    });
    panelAdArboles.add(nombreAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 320, -1));

    ojala2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    ojala2.setText("FOTO");
    ojala2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    ojala2.setPreferredSize(new java.awt.Dimension(250, 250));
    panelAdArboles.add(ojala2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 300, 270));

    jButton11.setBackground(new java.awt.Color(46, 53, 50));
    jButton11.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton11.setForeground(new java.awt.Color(204, 204, 204));
    jButton11.setText("Elegir Archivo...");
    jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton11ActionPerformed(evt);
        }
    });
    panelAdArboles.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 100, -1));

    buttonCambiarAdArbol.setBackground(new java.awt.Color(0, 0, 0));
    buttonCambiarAdArbol.setForeground(new java.awt.Color(255, 255, 255));
    buttonCambiarAdArbol.setText("CAMBIAR");
    buttonCambiarAdArbol.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCambiarAdArbolActionPerformed(evt);
        }
    });
    panelAdArboles.add(buttonCambiarAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, -1, -1));

    arbolesAdArbol.setBackground(new java.awt.Color(46, 53, 50));
    arbolesAdArbol.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    arbolesAdArbol.setForeground(new java.awt.Color(255, 255, 255));
    arbolesAdArbol.setModel(modeloArbolesBox);
    panelAdArboles.add(arbolesAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 260, -1));

    buttonVerAdArbol.setBackground(new java.awt.Color(0, 0, 0));
    buttonVerAdArbol.setForeground(new java.awt.Color(255, 255, 255));
    buttonVerAdArbol.setText("VER");
    buttonVerAdArbol.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonVerAdArbolActionPerformed(evt);
        }
    });
    panelAdArboles.add(buttonVerAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 70, -1));

    jLabel73.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel73.setText("ÁRBOLES EXISTENTES");
    panelAdArboles.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, -1, -1));

    buttonAceptarAdArbol.setBackground(new java.awt.Color(0, 0, 0));
    buttonAceptarAdArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonAceptarAdArbol.setForeground(new java.awt.Color(255, 255, 255));
    buttonAceptarAdArbol.setText("ACEPTAR");
    buttonAceptarAdArbol.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAceptarAdArbolActionPerformed(evt);
        }
    });
    panelAdArboles.add(buttonAceptarAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 110, -1));

    buttonCancelarAdArbol.setBackground(new java.awt.Color(0, 0, 0));
    buttonCancelarAdArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCancelarAdArbol.setForeground(new java.awt.Color(255, 255, 255));
    buttonCancelarAdArbol.setText("CANCELAR");
    buttonCancelarAdArbol.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCancelarAdArbolActionPerformed(evt);
        }
    });
    panelAdArboles.add(buttonCancelarAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 440, 110, -1));

    buttonCrearAdArbol.setBackground(new java.awt.Color(0, 0, 0));
    buttonCrearAdArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCrearAdArbol.setForeground(new java.awt.Color(255, 255, 255));
    buttonCrearAdArbol.setText("CREAR");
    buttonCrearAdArbol.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCrearAdArbolActionPerformed(evt);
        }
    });
    panelAdArboles.add(buttonCrearAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 110, -1));

    buttonModificarAdArbol.setBackground(new java.awt.Color(0, 0, 0));
    buttonModificarAdArbol.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonModificarAdArbol.setForeground(new java.awt.Color(255, 255, 255));
    buttonModificarAdArbol.setText("MODIFICAR");
    buttonModificarAdArbol.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonModificarAdArbolActionPerformed(evt);
        }
    });
    panelAdArboles.add(buttonModificarAdArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 110, -1));

    panelAdAbonos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelCrearAbono.setBorder(javax.swing.BorderFactory.createTitledBorder("ABONOS"));
    panelCrearAbono.setOpaque(false);

    buttonCambiarAdArbol1.setBackground(new java.awt.Color(0, 0, 0));
    buttonCambiarAdArbol1.setForeground(new java.awt.Color(255, 255, 255));
    buttonCambiarAdArbol1.setText("CAMBIAR");
    buttonCambiarAdArbol1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCambiarAdArbol1ActionPerformed(evt);
        }
    });

    arbolesAdArbol1.setBackground(new java.awt.Color(46, 53, 50));
    arbolesAdArbol1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    arbolesAdArbol1.setForeground(new java.awt.Color(255, 255, 255));
    arbolesAdArbol1.setModel(modeloAbonosBox);

    buttonVerAdArbol1.setBackground(new java.awt.Color(0, 0, 0));
    buttonVerAdArbol1.setForeground(new java.awt.Color(255, 255, 255));
    buttonVerAdArbol1.setText("VER");
    buttonVerAdArbol1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonVerAdArbol1ActionPerformed(evt);
        }
    });

    jLabel88.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel88.setText("ABONOS EXISTENTES");

    jLabel89.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel89.setText("NOMBRE:");

    nombreAdArbol1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    nombreAdArbol1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nombreAdArbol1ActionPerformed(evt);
        }
    });

    jScrollPane13.setBackground(new java.awt.Color(255, 255, 255));
    jScrollPane13.setAutoscrolls(true);

    descripcionCatalogo1.setColumns(20);
    descripcionCatalogo1.setForeground(new java.awt.Color(255, 255, 255));
    descripcionCatalogo1.setLineWrap(true);
    descripcionCatalogo1.setRows(5);
    descripcionCatalogo1.setWrapStyleWord(true);
    descripcionCatalogo1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
    descripcionCatalogo1.setSelectionColor(new java.awt.Color(180, 184, 171));
    jScrollPane13.setViewportView(descripcionCatalogo1);

    jLabel90.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel90.setText("DESCRIPCIÓN:");

    buttonCrearAdArbol1.setBackground(new java.awt.Color(0, 0, 0));
    buttonCrearAdArbol1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCrearAdArbol1.setForeground(new java.awt.Color(255, 255, 255));
    buttonCrearAdArbol1.setText("CREAR");
    buttonCrearAdArbol1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCrearAdArbol1ActionPerformed(evt);
        }
    });

    buttonModificarAdArbol1.setBackground(new java.awt.Color(0, 0, 0));
    buttonModificarAdArbol1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonModificarAdArbol1.setForeground(new java.awt.Color(255, 255, 255));
    buttonModificarAdArbol1.setText("MODIFICAR");
    buttonModificarAdArbol1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonModificarAdArbol1ActionPerformed(evt);
        }
    });

    buttonAceptarAdArbol1.setBackground(new java.awt.Color(0, 0, 0));
    buttonAceptarAdArbol1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonAceptarAdArbol1.setForeground(new java.awt.Color(255, 255, 255));
    buttonAceptarAdArbol1.setText("ACEPTAR");
    buttonAceptarAdArbol1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAceptarAdArbol1ActionPerformed(evt);
        }
    });

    buttonCancelarAdArbol1.setBackground(new java.awt.Color(0, 0, 0));
    buttonCancelarAdArbol1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCancelarAdArbol1.setForeground(new java.awt.Color(255, 255, 255));
    buttonCancelarAdArbol1.setText("CANCELAR");
    buttonCancelarAdArbol1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCancelarAdArbol1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelCrearAbonoLayout = new javax.swing.GroupLayout(panelCrearAbono);
    panelCrearAbono.setLayout(panelCrearAbonoLayout);
    panelCrearAbonoLayout.setHorizontalGroup(
        panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelCrearAbonoLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(arbolesAdArbol1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelCrearAbonoLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(buttonVerAdArbol1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(40, 40, 40)
                    .addComponent(buttonCambiarAdArbol1))
                .addGroup(panelCrearAbonoLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonModificarAdArbol1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(buttonAceptarAdArbol1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(28, 28, 28)
                    .addGroup(panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonCrearAdArbol1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancelarAdArbol1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCrearAbonoLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(nombreAdArbol1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    panelCrearAbonoLayout.setVerticalGroup(
        panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelCrearAbonoLayout.createSequentialGroup()
            .addComponent(jLabel88)
            .addGap(5, 5, 5)
            .addComponent(arbolesAdArbol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addGroup(panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buttonVerAdArbol1)
                .addComponent(buttonCambiarAdArbol1))
            .addGap(45, 45, 45)
            .addComponent(jLabel89)
            .addGap(5, 5, 5)
            .addComponent(nombreAdArbol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(jLabel90)
            .addGap(5, 5, 5)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buttonModificarAdArbol1)
                .addComponent(buttonCrearAdArbol1))
            .addGap(25, 25, 25)
            .addGroup(panelCrearAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonAceptarAdArbol1)
                .addComponent(buttonCancelarAdArbol1))
            .addGap(0, 20, Short.MAX_VALUE))
    );

    panelAdAbonos.add(panelCrearAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 300, 430));

    jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));

    arbolesAdArbol2.setBackground(new java.awt.Color(46, 53, 50));
    arbolesAdArbol2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    arbolesAdArbol2.setForeground(new java.awt.Color(255, 255, 255));
    arbolesAdArbol2.setModel(modeloArbolesBox);

    jLabel91.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel91.setText("PERSONA");

    arbolesAdArbol3.setBackground(new java.awt.Color(46, 53, 50));
    arbolesAdArbol3.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    arbolesAdArbol3.setForeground(new java.awt.Color(255, 255, 255));
    arbolesAdArbol3.setModel(modeloAbonosBox);

    jLabel92.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel92.setText("ABONO");

    buttonVerAdArbol2.setBackground(new java.awt.Color(0, 0, 0));
    buttonVerAdArbol2.setForeground(new java.awt.Color(255, 255, 255));
    buttonVerAdArbol2.setText("HORTALIZA");
    buttonVerAdArbol2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonVerAdArbol2ActionPerformed(evt);
        }
    });

    buttonCambiarAdArbol2.setBackground(new java.awt.Color(0, 0, 0));
    buttonCambiarAdArbol2.setForeground(new java.awt.Color(255, 255, 255));
    buttonCambiarAdArbol2.setText("ÁRBOL");
    buttonCambiarAdArbol2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCambiarAdArbol2ActionPerformed(evt);
        }
    });

    jLabel93.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel93.setText("TIPO DE PLANTA");

    arbolesAdArbol4.setBackground(new java.awt.Color(46, 53, 50));
    arbolesAdArbol4.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    arbolesAdArbol4.setForeground(new java.awt.Color(255, 255, 255));
    arbolesAdArbol4.setModel(modeloPlantas);

    jLabel94.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel94.setText("PLANTA");

    buttonCrearAdArbol2.setBackground(new java.awt.Color(0, 0, 0));
    buttonCrearAdArbol2.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCrearAdArbol2.setForeground(new java.awt.Color(255, 255, 255));
    buttonCrearAdArbol2.setText("CREAR");
    buttonCrearAdArbol2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCrearAdArbol2ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
    jPanel13.setLayout(jPanel13Layout);
    jPanel13Layout.setHorizontalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel13Layout.createSequentialGroup()
            .addGap(67, 67, 67)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(buttonVerAdArbol2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(buttonCambiarAdArbol2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(arbolesAdArbol2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(arbolesAdArbol3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(arbolesAdArbol4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(71, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonCrearAdArbol2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(147, 147, 147))
    );
    jPanel13Layout.setVerticalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel13Layout.createSequentialGroup()
            .addGap(9, 9, 9)
            .addComponent(jLabel91)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(arbolesAdArbol2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel92)
            .addGap(5, 5, 5)
            .addComponent(arbolesAdArbol3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(37, 37, 37)
            .addComponent(jLabel93)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buttonVerAdArbol2)
                .addComponent(buttonCambiarAdArbol2))
            .addGap(28, 28, 28)
            .addComponent(jLabel94)
            .addGap(5, 5, 5)
            .addComponent(arbolesAdArbol4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
            .addComponent(buttonCrearAdArbol2)
            .addGap(45, 45, 45))
    );

    panelAdAbonos.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 410, 430));

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

    catalogos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    nombreCatalogo.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    nombreCatalogo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nombreCatalogoActionPerformed(evt);
        }
    });
    catalogos.getContentPane().add(nombreCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 220, -1));

    jLabel74.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel74.setText("DESCRIPCIÓN:");
    catalogos.getContentPane().add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

    jLabel75.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel75.setText("NOMBRE:");
    catalogos.getContentPane().add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

    jLabel76.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel76.setText("PAÍS");
    catalogos.getContentPane().add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 220, -1));

    lugarCatalogo.setModel(modeloLocacion);
    catalogos.getContentPane().add(lugarCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 220, -1));

    jScrollPane12.setBackground(new java.awt.Color(255, 255, 255));
    jScrollPane12.setAutoscrolls(true);

    descripcionCatalogo.setColumns(20);
    descripcionCatalogo.setForeground(new java.awt.Color(255, 255, 255));
    descripcionCatalogo.setLineWrap(true);
    descripcionCatalogo.setRows(5);
    descripcionCatalogo.setWrapStyleWord(true);
    descripcionCatalogo.setDisabledTextColor(new java.awt.Color(255, 255, 255));
    descripcionCatalogo.setSelectionColor(new java.awt.Color(180, 184, 171));
    jScrollPane12.setViewportView(descripcionCatalogo);

    catalogos.getContentPane().add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 220, 30));

    buttonAceptarCatalogo.setBackground(new java.awt.Color(0, 0, 0));
    buttonAceptarCatalogo.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonAceptarCatalogo.setForeground(new java.awt.Color(255, 255, 255));
    buttonAceptarCatalogo.setText("ACEPTAR");
    buttonAceptarCatalogo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAceptarCatalogoActionPerformed(evt);
        }
    });
    catalogos.getContentPane().add(buttonAceptarCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 100, 20));

    buttonCancelarCatalogo.setBackground(new java.awt.Color(0, 0, 0));
    buttonCancelarCatalogo.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCancelarCatalogo.setForeground(new java.awt.Color(255, 255, 255));
    buttonCancelarCatalogo.setText("CANCELAR");
    buttonCancelarCatalogo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCancelarCatalogoActionPerformed(evt);
        }
    });
    catalogos.getContentPane().add(buttonCancelarCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 100, 20));

    panelFiltro1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    fechaAdUsuario1.setCurrentView(new datechooser.view.appearance.AppearancesList("Dali",
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
fechaAdUsuario1.setCalendarBackground(new java.awt.Color(0, 0, 0));
fechaAdUsuario1.setCalendarPreferredSize(new java.awt.Dimension(390, 200));
fechaAdUsuario1.setNothingAllowed(false);
fechaAdUsuario1.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
fechaAdUsuario1.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
fechaAdUsuario1.setLocale(new java.util.Locale("es", "", ""));
fechaAdUsuario1.setMinDate(new java.util.GregorianCalendar(1900, 0, 1));
fechaAdUsuario1.setNavigateFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
fechaAdUsuario1.setCurrentNavigateIndex(0);
fechaAdUsuario1.setShowOneMonth(true);
panelFiltro1.add(fechaAdUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 140, -1));

jLabel44.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
jLabel44.setText("FECHA INICIO");
panelFiltro1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, -1));

fechaAdUsuario2.setCurrentView(new datechooser.view.appearance.AppearancesList("Dali",
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
fechaAdUsuario2.setCalendarBackground(new java.awt.Color(0, 0, 0));
fechaAdUsuario2.setCalendarPreferredSize(new java.awt.Dimension(390, 200));
fechaAdUsuario2.setNothingAllowed(false);
fechaAdUsuario2.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
fechaAdUsuario2.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
fechaAdUsuario2.setLocale(new java.util.Locale("es", "", ""));
fechaAdUsuario2.setMinDate(new java.util.GregorianCalendar(1900, 0, 1));
fechaAdUsuario2.setNavigateFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
fechaAdUsuario2.setCurrentNavigateIndex(0);
fechaAdUsuario2.setShowOneMonth(true);
panelFiltro1.add(fechaAdUsuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 140, -1));

jLabel45.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
jLabel45.setText("TOP");
panelFiltro1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 140, -1));

nombreCatalogo1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
nombreCatalogo1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
    nombreCatalogo1ActionPerformed(evt);
    }
    });
    panelFiltro1.add(nombreCatalogo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 140, -1));

    jLabel46.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel46.setText("FECHA FINAL");
    panelFiltro1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 140, -1));

    buttonAceptarCatalogo1.setBackground(new java.awt.Color(0, 0, 0));
    buttonAceptarCatalogo1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonAceptarCatalogo1.setForeground(new java.awt.Color(255, 255, 255));
    buttonAceptarCatalogo1.setText("FILTRAR");
    buttonAceptarCatalogo1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAceptarCatalogo1ActionPerformed(evt);
        }
    });
    panelFiltro1.add(buttonAceptarCatalogo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 100, 20));

    jCheckBox2.setText("Ascendente");
    panelFiltro1.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

    panelFiltro2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    fechaAdUsuario3.setCurrentView(new datechooser.view.appearance.AppearancesList("Dali",
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
fechaAdUsuario3.setCalendarBackground(new java.awt.Color(0, 0, 0));
fechaAdUsuario3.setCalendarPreferredSize(new java.awt.Dimension(390, 200));
fechaAdUsuario3.setNothingAllowed(false);
fechaAdUsuario3.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
fechaAdUsuario3.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
fechaAdUsuario3.setLocale(new java.util.Locale("es", "", ""));
fechaAdUsuario3.setMaxDate(new java.util.GregorianCalendar(2018, 4, 6));
fechaAdUsuario3.setMinDate(new java.util.GregorianCalendar(1900, 0, 1));
fechaAdUsuario3.setNavigateFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 11));
fechaAdUsuario3.setCurrentNavigateIndex(0);
fechaAdUsuario3.setShowOneMonth(true);
panelFiltro2.add(fechaAdUsuario3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 140, -1));

jLabel47.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
jLabel47.setText("FECHA INICIO");
panelFiltro2.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 140, -1));

buttonAceptarCatalogo2.setBackground(new java.awt.Color(0, 0, 0));
buttonAceptarCatalogo2.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
buttonAceptarCatalogo2.setForeground(new java.awt.Color(255, 255, 255));
buttonAceptarCatalogo2.setText("FILTRAR");
buttonAceptarCatalogo2.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonAceptarCatalogo2ActionPerformed(evt);
    }
    });
    panelFiltro2.add(buttonAceptarCatalogo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 100, 20));

    panelFiltro3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel54.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel54.setText("HUERTA");
    panelFiltro3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

    usuariosAdministrar2.setBackground(new java.awt.Color(46, 53, 50));
    usuariosAdministrar2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    usuariosAdministrar2.setForeground(new java.awt.Color(255, 255, 255));
    usuariosAdministrar2.setModel(modeloHuertas);
    panelFiltro3.add(usuariosAdministrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 170, -1));

    buttonAceptarCatalogo3.setBackground(new java.awt.Color(0, 0, 0));
    buttonAceptarCatalogo3.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonAceptarCatalogo3.setForeground(new java.awt.Color(255, 255, 255));
    buttonAceptarCatalogo3.setText("FILTRAR");
    buttonAceptarCatalogo3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAceptarCatalogo3ActionPerformed(evt);
        }
    });
    panelFiltro3.add(buttonAceptarCatalogo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 100, 20));

    jPanel12.setLayout(new java.awt.CardLayout());

    jToggleButton6.setText("SALIR");
    jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jToggleButton6ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToggleButton6)
            .addGap(81, 81, 81))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap(57, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jToggleButton6)
            .addGap(22, 22, 22))
    );

    javax.swing.GroupLayout dialogInvitadoLayout = new javax.swing.GroupLayout(dialogInvitado.getContentPane());
    dialogInvitado.getContentPane().setLayout(dialogInvitadoLayout);
    dialogInvitadoLayout.setHorizontalGroup(
        dialogInvitadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    dialogInvitadoLayout.setVerticalGroup(
        dialogInvitadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    hortalizaInvitado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    precioAdHortaliza1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    hortalizaInvitado.add(precioAdHortaliza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 320, -1));

    jLabel95.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel95.setText("TIPO:");
    hortalizaInvitado.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

    jLabel97.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel97.setText("PRECIO:");
    hortalizaInvitado.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

    jLabel98.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel98.setText("NOMBRE:");
    hortalizaInvitado.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

    jLabel99.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel99.setText("COLOR:");
    hortalizaInvitado.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

    colorAdHortaliza1.setModel(modeloColor        );
    hortalizaInvitado.add(colorAdHortaliza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 270, -1));

    tipoAdHortaliza1.setModel(modeloTipo);
    hortalizaInvitado.add(tipoAdHortaliza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 270, -1));

    propiedadAdHortaliza1.setModel(modeloPropiedad);
    hortalizaInvitado.add(propiedadAdHortaliza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 270, -1));

    caracteristicaAdHortaliza1.setModel(modeloCaracteristica);
    hortalizaInvitado.add(caracteristicaAdHortaliza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 270, -1));

    jLabel101.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel101.setText("PROPIEDAD:");
    hortalizaInvitado.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

    jLabel102.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel102.setText("CARACTERÍSTICA:");
    hortalizaInvitado.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

    nombreAdHortaliza1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    nombreAdHortaliza1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nombreAdHortaliza1ActionPerformed(evt);
        }
    });
    hortalizaInvitado.add(nombreAdHortaliza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 320, -1));

    jToggleButton2.setText("CREAR");
    jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jToggleButton2ActionPerformed(evt);
        }
    });
    hortalizaInvitado.add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, -1, -1));

    jToggleButton3.setText("CANCELAR");
    jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jToggleButton3ActionPerformed(evt);
        }
    });
    hortalizaInvitado.add(jToggleButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, -1, -1));

    ojala4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    ojala4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    ojala4.setPreferredSize(new java.awt.Dimension(250, 250));
    hortalizaInvitado.add(ojala4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 300, 270));

    jButton12.setBackground(new java.awt.Color(46, 53, 50));
    jButton12.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton12.setForeground(new java.awt.Color(204, 204, 204));
    jButton12.setText("Elegir Archivo...");
    jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton12.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton12ActionPerformed(evt);
        }
    });
    hortalizaInvitado.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, 100, -1));

    arbolInvitado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    precioAdArbol1.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    arbolInvitado.add(precioAdArbol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 320, -1));

    jLabel104.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel104.setText("XILEMA:");
    arbolInvitado.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

    jLabel106.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel106.setText("PRECIO:");
    arbolInvitado.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

    jLabel107.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel107.setText("NOMBRE:");
    arbolInvitado.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

    jLabel108.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel108.setText("REPRODUCCION:");
    arbolInvitado.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

    jLabel109.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel109.setText("EXTINCIÓN:");
    arbolInvitado.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

    groupHortalizas.add(jRadioButton7);
    jRadioButton7.setText("SÍ");
    arbolInvitado.add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

    groupHortalizas.add(jRadioButton8);
    jRadioButton8.setText("NO");
    arbolInvitado.add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

    reproduccionAdArbol1.setModel(modeloReproduccion);
    arbolInvitado.add(reproduccionAdArbol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 270, -1));

    xilemaAdArbol1.setModel(modeloXilema);
    arbolInvitado.add(xilemaAdArbol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 270, -1));

    cambiumAdArbol1.setModel(modeloCambium);
    arbolInvitado.add(cambiumAdArbol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 270, -1));

    cortezaAdArbol1.setModel(modeloCorteza);
    arbolInvitado.add(cortezaAdArbol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 270, -1));

    jLabel110.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel110.setText("CAMBIUM:");
    arbolInvitado.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

    jLabel111.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 11)); // NOI18N
    jLabel111.setText("CORTEZA:");
    arbolInvitado.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

    nombreAdArbol2.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    nombreAdArbol2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nombreAdArbol2ActionPerformed(evt);
        }
    });
    nombreAdArbol2.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            nombreAdArbol2KeyTyped(evt);
        }
    });
    arbolInvitado.add(nombreAdArbol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 320, -1));

    jToggleButton4.setText("CREAR");
    jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jToggleButton4ActionPerformed(evt);
        }
    });
    arbolInvitado.add(jToggleButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, -1, -1));

    jToggleButton5.setText("CANCELAR");
    arbolInvitado.add(jToggleButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, -1, -1));

    ojala3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    ojala3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
    ojala3.setPreferredSize(new java.awt.Dimension(250, 250));
    arbolInvitado.add(ojala3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 300, 270));

    jButton10.setBackground(new java.awt.Color(46, 53, 50));
    jButton10.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    jButton10.setForeground(new java.awt.Color(204, 204, 204));
    jButton10.setText("Elegir Archivo...");
    jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButton10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton10ActionPerformed(evt);
        }
    });
    arbolInvitado.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 100, -1));

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("LA HUERTICA");
    setLocation(new java.awt.Point(0, 0));
    setResizable(false);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    panelPrincipal.setBackground(new java.awt.Color(238, 229, 233));
    panelPrincipal.setLayout(new java.awt.CardLayout());
    getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 570));

    buttonHuertas.setBackground(new java.awt.Color(75, 82, 79));
    buttonHuertas.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
    buttonHuertas.setForeground(new java.awt.Color(224, 224, 224));
    buttonHuertas.setText("Huertas");
    buttonHuertas.setBorderPainted(false);
    buttonHuertas.setContentAreaFilled(false);
    buttonHuertas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonHuertas.setFocusPainted(false);
    buttonHuertas.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonHuertasActionPerformed(evt);
        }
    });
    getContentPane().add(buttonHuertas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 90, 50));

    buttonArboles.setBackground(new java.awt.Color(75, 82, 79));
    buttonArboles.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
    buttonArboles.setForeground(new java.awt.Color(224, 224, 224));
    buttonArboles.setText("Árboles");
    buttonArboles.setBorderPainted(false);
    buttonArboles.setContentAreaFilled(false);
    buttonArboles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonArboles.setFocusPainted(false);
    buttonArboles.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonArbolesActionPerformed(evt);
        }
    });
    getContentPane().add(buttonArboles, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 80, 50));

    buttonInicio.setBackground(new java.awt.Color(75, 82, 79));
    buttonInicio.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
    buttonInicio.setForeground(new java.awt.Color(224, 224, 224));
    buttonInicio.setText("Inicio");
    buttonInicio.setBorderPainted(false);
    buttonInicio.setContentAreaFilled(false);
    buttonInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonInicio.setFocusPainted(false);
    buttonInicio.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonInicioActionPerformed(evt);
        }
    });
    getContentPane().add(buttonInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 50));

    buttonHortalizas.setBackground(new java.awt.Color(75, 82, 79));
    buttonHortalizas.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
    buttonHortalizas.setForeground(new java.awt.Color(224, 224, 224));
    buttonHortalizas.setText("Hortalizas");
    buttonHortalizas.setBorderPainted(false);
    buttonHortalizas.setContentAreaFilled(false);
    buttonHortalizas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonHortalizas.setFocusPainted(false);
    buttonHortalizas.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonHortalizasActionPerformed(evt);
        }
    });
    getContentPane().add(buttonHortalizas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 100, 50));

    buttonCerrarSesion.setBackground(new java.awt.Color(75, 82, 79));
    buttonCerrarSesion.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonCerrarSesion.setForeground(new java.awt.Color(224, 224, 224));
    buttonCerrarSesion.setText("Cerrar Sesión");
    buttonCerrarSesion.setBorderPainted(false);
    buttonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCerrarSesionActionPerformed(evt);
        }
    });
    getContentPane().add(buttonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 80, -1));

    buttonTrueques.setBackground(new java.awt.Color(75, 82, 79));
    buttonTrueques.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
    buttonTrueques.setForeground(new java.awt.Color(224, 224, 224));
    buttonTrueques.setText("Trueques");
    buttonTrueques.setBorderPainted(false);
    buttonTrueques.setContentAreaFilled(false);
    buttonTrueques.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonTrueques.setFocusPainted(false);
    buttonTrueques.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonTruequesActionPerformed(evt);
        }
    });
    getContentPane().add(buttonTrueques, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 100, 50));

    buttonOpciones.setBackground(new java.awt.Color(75, 82, 79));
    buttonOpciones.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
    buttonOpciones.setForeground(new java.awt.Color(224, 224, 224));
    buttonOpciones.setText("Opciones de Administrador");
    buttonOpciones.setBorderPainted(false);
    buttonOpciones.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonOpcionesActionPerformed(evt);
        }
    });
    getContentPane().add(buttonOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 110, -1));

    buttonVentas.setBackground(new java.awt.Color(75, 82, 79));
    buttonVentas.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
    buttonVentas.setForeground(new java.awt.Color(224, 224, 224));
    buttonVentas.setText("Compras");
    buttonVentas.setBorderPainted(false);
    buttonVentas.setContentAreaFilled(false);
    buttonVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonVentas.setFocusPainted(false);
    buttonVentas.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonVentasActionPerformed(evt);
        }
    });
    getContentPane().add(buttonVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 90, 50));

    buttonAbonos.setBackground(new java.awt.Color(75, 82, 79));
    buttonAbonos.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
    buttonAbonos.setForeground(new java.awt.Color(224, 224, 224));
    buttonAbonos.setText("Abonos");
    buttonAbonos.setBorderPainted(false);
    buttonAbonos.setContentAreaFilled(false);
    buttonAbonos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    buttonAbonos.setFocusPainted(false);
    buttonAbonos.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAbonosActionPerformed(evt);
        }
    });
    getContentPane().add(buttonAbonos, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 0, 90, 50));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpcionesActionPerformed
        opcionesAdministrador.setSize(206, 330);
        opcionesAdministrador.setLocationRelativeTo(null);
        opcionesAdministrador.setVisible(true);
        setVisible(false);
        setEnabled(false);
    }//GEN-LAST:event_buttonOpcionesActionPerformed

    private void buttonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInicioActionPerformed
        panelPrincipal.removeAll();
        panelPrincipal.add(panelInicio, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }//GEN-LAST:event_buttonInicioActionPerformed

    private void buttonHuertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHuertasActionPerformed
        try {
            prepareHuertas();
            jCheckBox1.setEnabled(false);
            panelPrincipal.removeAll();
            panelHuertas.setSize(980, 570);
            panelPrincipal.add(panelHuertas, BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
            huertas.setEnabled(true);
            buttonCambiarHuerta.setVisible(false);
            buttonVerHuerta.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonHuertasActionPerformed

    public int getHuertas5() {
        int posSeleccionado = hortalizasHuertas.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }
    
    public void getArbolxHuerta() throws SQLException{
        idArbolHuerta.clear();
        nombreArbolHuerta.clear();
        modeloArboles.removeAllElements();
        int posSeleccionado = arbolesHuertas.getSelectedIndex();
        if(posSeleccionado == -1){
            return;
        }
        int id = (Integer) idHuertas.get(posSeleccionado);
        String nom = "{call paqUtilidades.arbolHuerta(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, id);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);
        while(cursor.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idArbolHuerta.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreArbolHuerta.add(cursor.getString(i));
                }
            }
        }
        
        for (String hortaliza : nombreArbolHuerta) {
            modeloArboles.addElement(hortaliza);
        }
    }
    
    public int getHuerta6(){
        int posSeleccionado = hortalizasHuertas.getSelectedIndex();
        if(posSeleccionado == -1){
            return -1;
        }
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }
    
    public void getHortalizaxHuerta(int id) throws SQLException {
        idHortalizaHuerta.clear();
        nombreHortalizaHuerta.clear();
        modeloHortalizas.removeAllElements();
        String nom = "{call paqUtilidades.hortalizasHuerta(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, id);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);
        while(cursor.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idHortalizaHuerta.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreHortalizaHuerta.add(cursor.getString(i));
                }
            }
        }
        
        for (String hortaliza : nombreHortalizaHuerta) {
            modeloHortalizas.addElement(hortaliza);
        }
    }
    
    private void buttonHortalizasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHortalizasActionPerformed
        try {
            prepareAdHuertas();
            prepareHortalizas();
            panelPrincipal.removeAll();
            panelHortalizas.setSize(980, 570);
            panelPrincipal.add(panelHortalizas, BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
            hortalizasList.setEnabled(true);
            hortalizasHuertas.setEnabled(true);
            buttonCambiarHortaliza.setVisible(false);
            buttonVerHortaliza.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonHortalizasActionPerformed

    private void buttonArbolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonArbolesActionPerformed
        try {
            prepareAdHuertas();
            prepareArboles();
            panelPrincipal.removeAll();
            panelArboles.setSize(980, 570);
            panelPrincipal.add(panelArboles, BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
            arbolesList.setEnabled(true);
            arbolesHuertas.setEnabled(true);
            buttonCambiarArbol.setVisible(false);
            buttonVerArbol.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonArbolesActionPerformed

    private void buttonTruequesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTruequesActionPerformed
        buttonRealizarTrueque.setEnabled(false);
        jToggleButton1.setEnabled(false);
        try {
            prepareTrueques();
            panelPrincipal.removeAll();
            panelTrueques.setSize(980, 570);
            panelPrincipal.add(panelTrueques, BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonTruequesActionPerformed

    private void buttonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVentasActionPerformed
        prepareVentas();
        panelPrincipal.removeAll();
        panelVentas.setSize(980, 570);
        panelPrincipal.add(panelVentas, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }//GEN-LAST:event_buttonVentasActionPerformed

    private void buttonSalirAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirAdminActionPerformed
        setEnabled(true);
        opcionesAdministrador.dispose();
        setVisible(true);
    }//GEN-LAST:event_buttonSalirAdminActionPerformed

    private void buttonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarSesionActionPerformed
        inicioSesion i = new inicioSesion();
        i.setSize(316, 269);
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_buttonCerrarSesionActionPerformed

    public void getHortalizaxHuerta2(int id) throws SQLException{
        idHortalizaHuerta.clear();
        nombreHortalizaHuerta.clear();
        modeloTruequesP1.removeAllElements();
        String nom = "{call paqUtilidades.hortalizasHuerta(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, id);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);
        while(cursor.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idHortalizaHuerta.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreHortalizaHuerta.add(cursor.getString(i));
                }
            }
        }
        
        for (String hortaliza : nombreHortalizaHuerta) {
            modeloTruequesP1.addElement(hortaliza);
        }
    }
    
    public void getHortalizaxHuerta3(int id) throws SQLException{
        idHortalizaHuerta.clear();
        nombreHortalizaHuerta.clear();
        modeloTruequesP2.removeAllElements();
        String nom = "{call paqUtilidades.hortalizasHuerta(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, id);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);
        while(cursor.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idHortalizaHuerta.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreHortalizaHuerta.add(cursor.getString(i));
                }
            }
        }
        
        for (String hortaliza : nombreHortalizaHuerta) {
            modeloTruequesP2.addElement(hortaliza);
        }
    }
    
    public void getArbolxHuerta(int id) throws SQLException{
        idArbolHuerta.clear();
        nombreArbolHuerta.clear();
        modeloTruequesP2.removeAllElements();
        String nom = "{call paqUtilidades.ArbolHuerta(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, id);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);
        while(cursor.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idArbolHuerta.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreArbolHuerta.add(cursor.getString(i));
                }
            }
        }
        
        for (String hortaliza : nombreArbolHuerta) {
            modeloTruequesP2.addElement(hortaliza);
        }
    }
    
    public void getArbolxHuerta2(int id) throws SQLException{
        idArbolHuerta.clear();
        nombreArbolHuerta.clear();
        modeloTruequesP1.removeAllElements();
        String nom = "{call paqUtilidades.ArbolHuerta(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, id);
        cstmt.registerOutParameter(2, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(2);
        while(cursor.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idArbolHuerta.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreArbolHuerta.add(cursor.getString(i));
                }
            }
        }
        
        for (String hortaliza : nombreArbolHuerta) {
            modeloTruequesP1.addElement(hortaliza);
        }
    }
    
    public int getHuerta7(){
        if (idHuertas.isEmpty()) {
            return -1;
        }
        int posSeleccionado = truequeHuertas.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }
    
    private void buttonAAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAAActionPerformed
        try {
            buttonHH.setBackground(Color.BLACK);
            buttonHA.setBackground(Color.BLACK);
            buttonAH.setBackground(Color.BLACK);
            buttonAA.setBackground(Color.WHITE);
            truequeHuertas.setEnabled(false);
            buttonRealizarTrueque.setEnabled(true);
            getArbolxHuerta2(idHuerta);
            getArbolxHuerta(getHuerta7());
            tipoTrueque = 1; //AA
            truequeP1.setEnabled(true);
            truequeP2.setEnabled(true);
            if(tipoUsuario != 0){
                buttonRealizarTrueque.setVisible(true);
            }
            buttonCancelarTrueque.setVisible(true);
            jToggleButton1.setEnabled(true);
            jPanel12.removeAll();
            jPanel12.setSize(890, 508);
            jPanel12.add(arbolInvitado, BorderLayout.CENTER);
            jPanel12.revalidate();
            jPanel12.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAAActionPerformed

    private void buttonHHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHHActionPerformed
        try {
            buttonRealizarTrueque.setEnabled(true);
            buttonHH.setBackground(Color.WHITE);
            buttonHA.setBackground(Color.BLACK);
            buttonAH.setBackground(Color.BLACK);
            buttonAA.setBackground(Color.BLACK);
            truequeHuertas.setEnabled(false);
            getHortalizaxHuerta2(idHuerta);
            getHortalizaxHuerta3(getHuerta7());
            //SE CREA EL TRUEQUE Y DEFINO TIPO DE TRUEQUE
            tipoTrueque = 3; //HH
            truequeP1.setEnabled(true);
            truequeP2.setEnabled(true);
            if(tipoUsuario != 0){
                buttonRealizarTrueque.setVisible(true);
            }
            buttonCancelarTrueque.setVisible(true);
            jToggleButton1.setEnabled(true);
            jPanel12.removeAll();
            jPanel12.setSize(890, 508);
            jPanel12.add(hortalizaInvitado, BorderLayout.CENTER);
            jPanel12.revalidate();
            jPanel12.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonHHActionPerformed

    
    
    private void buttonHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHAActionPerformed
        try {
            buttonRealizarTrueque.setEnabled(true);
            buttonHH.setBackground(Color.BLACK);
            buttonHA.setBackground(Color.WHITE);
            buttonAH.setBackground(Color.BLACK);
            buttonAA.setBackground(Color.BLACK);
            truequeHuertas.setEnabled(false);
            getHortalizaxHuerta2(idHuerta);
            getArbolxHuerta(getHuerta7());
            tipoTrueque = 4; //HA
            //SE SETEAN LOS BOTONES Y COMBOBOX DE TRUEQUE
            truequeP1.setEnabled(true);
            truequeP2.setEnabled(true);
            if(tipoUsuario != 0){
                buttonRealizarTrueque.setVisible(true);
            }
            buttonCancelarTrueque.setVisible(true);
            jToggleButton1.setEnabled(true);
            jPanel12.removeAll();
            jPanel12.setSize(890, 508);
            jPanel12.add(hortalizaInvitado, BorderLayout.CENTER);
            jPanel12.revalidate();
            jPanel12.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonHAActionPerformed

    
    private void buttonAHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAHActionPerformed
        try {
            buttonHH.setBackground(Color.BLACK);
            buttonHA.setBackground(Color.BLACK);
            buttonAH.setBackground(Color.WHITE);
            buttonAA.setBackground(Color.BLACK);
            truequeHuertas.setEnabled(false);
            buttonRealizarTrueque.setEnabled(true);
            getHortalizaxHuerta3(getHuerta7());            
            getArbolxHuerta2(idHuerta);
            //SE CREA EL TRUEQUE Y DEFINO TIPO DE TRUEQUE
            tipoTrueque = 2; //AH
            //SE SETEAN LOS BOTONES Y COMBOBOX DE TRUEQUE
            truequeP1.setEnabled(true);
            truequeP2.setEnabled(true);
            if(tipoUsuario != 0){
                buttonRealizarTrueque.setVisible(true);
            }
            buttonCancelarTrueque.setVisible(true);
            jToggleButton1.setEnabled(true);
            jPanel12.removeAll();
            jPanel12.setSize(890, 508);
            jPanel12.add(arbolInvitado, BorderLayout.CENTER);
            jPanel12.revalidate();
            jPanel12.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAHActionPerformed

    private void sliderCalificacionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderCalificacionStateChanged
        labelSldCalificacion.setText(String.valueOf(sliderCalificacion.getValue()));
    }//GEN-LAST:event_sliderCalificacionStateChanged

    //Codigos para modulo estadistico 


//1. Personas con intervalos 
public void personaIntervalos(){
        try {
            ArrayList<String> datos = new ArrayList();
            ArrayList<Integer> cantidad = new ArrayList();
            String titulo  =  "Personas por edad";
            CallableStatement cstmt;
            String nom;
            nom = "{call paqEstadistico.persona018MG(?)}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt.execute();
            cantidad.add(cstmt.getInt(1));
            datos.add("0-18");
            
            nom = "{call paqEstadistico.persona1930MG(?)}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt.execute();
            cantidad.add(cstmt.getInt(1));
            datos.add("19-30");
            
            
            nom = "{call paqEstadistico.persona3045MG(?)}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt.execute();
            cantidad.add(cstmt.getInt(1));
            datos.add("30-45");
            
            
            nom = "{call paqEstadistico.persona4655MG(?)}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt.execute();
            cantidad.add(cstmt.getInt(1));
            datos.add("46-55");
            
            
            nom = "{call paqEstadistico.persona5565MG(?)}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt.execute();
            cantidad.add(cstmt.getInt(1));
            datos.add("55-65");
            
            
            nom = "{call paqEstadistico.persona6675MG(?)}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt.execute();
            cantidad.add(cstmt.getInt(1));
            datos.add("66-77");
            
            
            nom = "{call paqEstadistico.persona7585MG(?)}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt.execute();
            cantidad.add(cstmt.getInt(1));
            datos.add("75-85");
            
            
            nom = "{call paqEstadistico.persona85plusMG (?)}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.INTEGER);
            cstmt.execute();
            cantidad.add(cstmt.getInt(1));
            datos.add("85-plus");
            
            pieChart(titulo,cantidad, datos);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

///Hortalizas por color 
public void hortalizaColor(){
        try {
            ArrayList<String> datos = new ArrayList();
            ArrayList<Integer> cantidad = new ArrayList();
            String titulo  =  "Hortalizas por color";
            CallableStatement cstmt;
            String nom;
            
            nom = "{? = call paqEstadistico.hortalizaColorMG()}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while(cursor.next()){
                System.out.println(cursor.getString(1));
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
            }
            pieChart(titulo,cantidad, datos);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
}


 //Hortalizas consuible 
public void hortalizaConsumible() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Hortalizas por tipo Consumible";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.hortalizaTipoMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}

 //Arbol reproduccion 
public void arbolReproduccion() throws SQLException{
        try {
            ArrayList<String> datos = new ArrayList();
            ArrayList<Integer> cantidad = new ArrayList();
            String titulo  =  "Arbol por tipo Reproducción";
            CallableStatement cstmt;
            String nom;
            
            nom = "{? = call paqEstadistico.arbolReproduccionMG()}";
            cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while(cursor.next()){
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
            }
            pieChart(titulo,cantidad, datos);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
}

//Arbol Xilema
public void arbolXiema() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Arbol por tipo Xilema";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.arbolXilemaMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}
//Arbol  Cambium 
public void arbolcambium() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Arbol por tipo Cambium";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.arbolCambiumMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}

//Arbol Corteza 
public void arbolCorteza() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Arbol por tipo Corteza";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.arbolCortezaMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}
//Trueques por ano 
public void truequesAno() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Trueques por año";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.totalTruequesMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(String.valueOf(cursor.getInt(1)));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}

//Ventas por ano 
public void ventasAno() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Ventas por año";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.totalVentasMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(String.valueOf(cursor.getInt(1)));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}

//Top trueques 
public void topTrueques() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Top Trueques";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.topTruequesMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}


// Top ventas hortaliza
public void topVentasH() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Top ventas hortaliza";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.topVentasHortalizaMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}



//Top ventas arbol
public void topVentasA() throws SQLException{
        ArrayList<String> datos = new ArrayList();
        ArrayList<Integer> cantidad = new ArrayList();
        String titulo  =  "Top ventas arbol";
        CallableStatement cstmt;
        String nom;
        
        nom = "{? = call paqEstadistico.topVentasArbolMG()}";
        cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        while(cursor.next()){
                datos.add(cursor.getString(1));
                cantidad.add(cursor.getInt(2));
        }
        pieChart(titulo,cantidad, datos);
}
        
    private void buttonEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEstadisticasActionPerformed
        setEnabled(true);
        opcionesAdministrador.dispose();
        dialogEstadisticas.setSize(800, 500);
        dialogEstadisticas.setLocationRelativeTo(null);
        dialogEstadisticas.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonEstadisticasActionPerformed

    private void buttonBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBitacoraActionPerformed
        setEnabled(true);
        opcionesAdministrador.dispose();
        dialogBitacora.setSize(680, 500);
        dialogBitacora.setLocationRelativeTo(null);
        dialogBitacora.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonBitacoraActionPerformed

    private void buttonConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConsultasActionPerformed
        setEnabled(true);
        opcionesAdministrador.dispose();
        dialogConsultas.setSize(750, 600);
        dialogConsultas.setLocationRelativeTo(null);
        dialogConsultas.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonConsultasActionPerformed

    private void buttonAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAuditoriaActionPerformed
        setEnabled(true);
        opcionesAdministrador.dispose();
        dialogAuditoria.setSize(680, 500);
        dialogAuditoria.setLocationRelativeTo(null);
        dialogAuditoria.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonAuditoriaActionPerformed

    private void buttonAdDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdDatosActionPerformed
        setEnabled(true);
        buttonAdCatalogos.setVisible(true);
        buttonAdUsuarios.setVisible(true);
        buttonAdHuertas.setVisible(true);
        buttonAdHortalizas.setVisible(true);
        buttonAdArboles.setVisible(true);
        buttonAdAbonos.setVisible(true);
        opcionesAdministrador.dispose();
        dialogAdDatos.setSize(1050, 570);
        dialogAdDatos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonAdDatosActionPerformed

    private void buttonSalirEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirEstadisticasActionPerformed
        setEnabled(true);
        dialogEstadisticas.dispose();
        opcionesAdministrador.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonSalirEstadisticasActionPerformed

    private void buttonSalirBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirBitacoraActionPerformed
        setEnabled(true);
        dialogBitacora.dispose();
        opcionesAdministrador.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonSalirBitacoraActionPerformed

    private void buttonSalirConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirConsultasActionPerformed
        setEnabled(true);
        dialogConsultas.dispose();
        opcionesAdministrador.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonSalirConsultasActionPerformed

    private void buttonAudHuertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAudHuertasActionPerformed
        try {
            cargarInfoAudHuertas();
            panelAuditorias.removeAll();
            panelAudHuertas.setSize(548, 470);
            panelAuditorias.add(panelAudHuertas, BorderLayout.CENTER);
            panelAuditorias.revalidate();
            panelAuditorias.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAudHuertasActionPerformed

    private void buttonSalirAdDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirAdDatosActionPerformed
        dialogAdDatos.dispose();
        opcionesAdministrador.setVisible(false);
        setVisible(true);
        setEnabled(true);
        panelPrincipal.removeAll();
        panelPrincipal.add(panelInicio, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }//GEN-LAST:event_buttonSalirAdDatosActionPerformed

    private void buttonSalirComentariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirComentariosActionPerformed
        setEnabled(true);
        dialogComentarios.setVisible(false);
    }//GEN-LAST:event_buttonSalirComentariosActionPerformed

    private void buttonVerMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerMasActionPerformed
        prepareComentarios();
        dialogComentarios.setSize(800, 600);
        dialogComentarios.setLocationRelativeTo(null);
        dialogComentarios.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonVerMasActionPerformed

    private void buttonVentaHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVentaHActionPerformed
        try {
            modeloVentas.removeAllElements();
            idHortalizas.clear();
            nombreHortalizas.clear();
            buttonVentaH.setBackground(Color.WHITE);
            buttonVentaA.setBackground(Color.BLACK);
            //SE SETEAN LOS BOTONES Y COMBOBOX DE VENTA
            ventaPlantas.setEnabled(true);
            buttonRealizarVenta.setVisible(true);
            buttonCancelarVenta.setVisible(true);
            //SE SETEAN LOS BOTONES DEL MENÚ PRINCIPAL
            //SE SETEA TIPO DE VENTA
            String nom = "{? = call paqUtilidades.hortalizaInfo()}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while(cursor.next()){
                for(int i = 1; i<3; i++){
                    if (i == 1) {
                        idHortalizas.add(cursor.getInt(i));
                    } else if (i == 2) {
                        nombreHortalizas.add(cursor.getString(i));
                    }
                }
            }
            
            for (String hortalizas : nombreHortalizas) {
                modeloVentas.addElement(hortalizas);
            }
            
            tipoVenta = 2;
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonVentaHActionPerformed

    private void buttonVentaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVentaAActionPerformed
        try {
            modeloVentas.removeAllElements();
            idArboles.clear();
            nombreArboles.clear();
            buttonVentaH.setBackground(Color.BLACK);
            buttonVentaA.setBackground(Color.WHITE);
            //SE SETEAN LOS BOTONES Y COMBOBOX DE VENTA
            ventaPlantas.setEnabled(true);
            buttonRealizarVenta.setVisible(true);
            buttonCancelarVenta.setVisible(true);
            String nom = "{? = call paqUtilidades.arbolInfo()}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while(cursor.next()){
                for(int i = 1; i<3; i++){
                    if (i == 1) {
                        idArboles.add(cursor.getInt(i));
                    } else if (i == 2) {
                        nombreArboles.add(cursor.getString(i));
                    }
                }
            }
            
            for (String arboles : nombreArboles) {
                modeloVentas.addElement(arboles);
            }
            //SE SETEAN LOS BOTONES DEL MENÚ PRINCIPAL
            //SE SETEA TIPO DE VENTA
            tipoVenta = 1;
            
            //crearVenta();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonVentaAActionPerformed

    private void buttonSalirAuditoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirAuditoria1ActionPerformed
        setEnabled(true);
        dialogAuditoria.dispose();
        opcionesAdministrador.setVisible(true);
        setEnabled(false);
    }//GEN-LAST:event_buttonSalirAuditoria1ActionPerformed

    private void buttonAudGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAudGeneralActionPerformed
        try {
            cargarInfoAudGeneral();
            panelAuditorias.removeAll();
            panelAudGeneral.setSize(548, 470);
            panelAuditorias.add(panelAudGeneral, BorderLayout.CENTER);
            panelAuditorias.revalidate();
            panelAuditorias.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAudGeneralActionPerformed

    private void buttonAudUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAudUsuariosActionPerformed
        try {
            cargarInfoAudUsuarios();
            panelAuditorias.removeAll();
            panelAudUsuarios.setSize(548, 470);
            panelAuditorias.add(panelAudUsuarios, BorderLayout.CENTER);
            panelAuditorias.revalidate();
            panelAuditorias.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAudUsuariosActionPerformed
    
    public int revisarInteresado() throws SQLException{
        String nom = "{call paqUtilidades.interesadoPersona(?,?,?)}";
	CallableStatement cstmt = conexion.prepareCall(nom);
	cstmt.setInt(1, idUser);
	cstmt.setInt(2, getHuerta());
	cstmt.registerOutParameter(3, OracleTypes.INTEGER);
	cstmt.execute();
        return cstmt.getInt(3);
    }
    
    private void buttonVerHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerHuertaActionPerformed
        try {
            if(revisarInteresado() == 1){
                jCheckBox1.setSelected(true);
            }
            if(getHuerta() == -1){
                return;
            }
            jCheckBox1.setEnabled(true);
            cargarInfoHuerta(getHuerta());
            huertas.setEnabled(false);
            buttonCambiarHuerta.setVisible(true);
            buttonVerHuerta.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonVerHuertaActionPerformed

    private void buttonCambiarHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarHuertaActionPerformed
        huertas.setEnabled(true);
        buttonCambiarHuerta.setVisible(false);
        buttonVerHuerta.setVisible(true);
    }//GEN-LAST:event_buttonCambiarHuertaActionPerformed

    private void buttonVerHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerHortalizaActionPerformed
        try {
            if(getHortaliza() == -1){
                return;
            }
            cargarInfoHortaliza(getHortaliza());
            hortalizasList.setEnabled(false);
            hortalizasHuertas.setEnabled(false);
            buttonCambiarHortaliza.setVisible(true);
            buttonVerHortaliza.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonVerHortalizaActionPerformed

    private void buttonCambiarHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarHortalizaActionPerformed
        hortalizasList.setEnabled(true);
        hortalizasHuertas.setEnabled(true);
        buttonCambiarHortaliza.setVisible(false);
        buttonVerHortaliza.setVisible(true);
    }//GEN-LAST:event_buttonCambiarHortalizaActionPerformed

    private void buttonVerArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerArbolActionPerformed
        if(getArbol() == -1){
            return;
        }
        try {
            cargarInfoArbol(getArbol());
            arbolesList.setEnabled(false);
            arbolesHuertas.setEnabled(false);
            buttonCambiarArbol.setVisible(true);
            buttonVerArbol.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_buttonVerArbolActionPerformed

    private void buttonCambiarArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarArbolActionPerformed
        arbolesList.setEnabled(true);
        arbolesHuertas.setEnabled(true);
        buttonCambiarArbol.setVisible(false);
        buttonVerArbol.setVisible(true);
    }//GEN-LAST:event_buttonCambiarArbolActionPerformed

    private void buttonAdCatalogosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdCatalogosActionPerformed
        // TODO add your handling code here:
        try {
            cargarInfoCatalogos();
            panelAdHuertas.setSize(890, 508);
            panelAdDatos.removeAll();
            panelAdDatos.setSize(890, 508);
            panelAdDatos.add(panelAdCatalogos, BorderLayout.CENTER);
            panelAdDatos.revalidate();
            panelAdDatos.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAdCatalogosActionPerformed

    private void buttonAdUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdUsuariosActionPerformed
        prepareAdUsuarios();	
	panelAdUsuarios.setSize(885,510);
        panelAdDatos.removeAll();
        panelAdDatos.add(panelAdUsuarios, BorderLayout.CENTER);
        panelAdDatos.revalidate();
        panelAdDatos.repaint();
    }//GEN-LAST:event_buttonAdUsuariosActionPerformed

    private void buttonAdHuertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdHuertasActionPerformed
        try {
            prepareAdHuertas();
            panelAdHuertas.setSize(890, 508);
            panelAdDatos.removeAll();
            panelAdDatos.setSize(890, 508);
            panelAdDatos.add(panelAdHuertas, BorderLayout.CENTER);
            panelAdDatos.revalidate();
            panelAdDatos.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAdHuertasActionPerformed

    private void buttonAdHortalizasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdHortalizasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAdHortalizasActionPerformed

    private void buttonAdArbolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdArbolesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAdArbolesActionPerformed

    private void buttonAdAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdAbonosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAdAbonosActionPerformed

    private void buttonCancelarTruequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarTruequeActionPerformed
        buttonRealizarTrueque.setEnabled(false);
        truequeHuertas.setEnabled(true);
        idArbolHuerta.clear();
        nombreArbolHuerta.clear();
        idHortalizaHuerta.clear();
        nombreHortalizaHuerta.clear();
        modeloTruequesP1.removeAllElements();
        modeloTruequesP2.removeAllElements();
        buttonHH.setBackground(Color.BLACK);
        buttonHA.setBackground(Color.BLACK);
        buttonAH.setBackground(Color.BLACK);
        buttonAA.setBackground(Color.BLACK);
    }//GEN-LAST:event_buttonCancelarTruequeActionPerformed

    public void crearTrueque() throws SQLException{
        String nom = "{call ventatruequePaq.crearTrueque(?,?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.setInt(1, idUser);
        cstmt.setInt(2, idHuerta);
        cstmt.execute();
    }
    
    public int getHorta1(){
        if (idHortalizaHuerta.isEmpty()) {
            return -1;
        }
        int posSeleccionado = truequeP1.getSelectedIndex();
        int id = (Integer) idHortalizaHuerta.get(posSeleccionado);
        return id;
    }
    
    public int getHorta2(){
        if (idHortalizaHuerta.isEmpty()) {
            return -1;
        }
        int posSeleccionado = truequeP2.getSelectedIndex();
        int id = (Integer) idHortalizaHuerta.get(posSeleccionado);
        return id;
    }
    
    public int getArb1(){
        if (idArbolHuerta.isEmpty()) {
            return -1;
        }
        int posSeleccionado = truequeP1.getSelectedIndex();
        int id = (Integer) idArbolHuerta.get(posSeleccionado);
        return id;
    }
    
    public int getArb2(){
        if (idArbolHuerta.isEmpty()) {
            return -1;
        }
        int posSeleccionado = truequeP2.getSelectedIndex();
        int id = (Integer) idArbolHuerta.get(posSeleccionado);
        return id;
    }
    
    public int getArbolGuest() throws SQLException{
        String nom = "{call paqUtilidades.arbolGuest(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.INTEGER);
        cstmt.execute();
        return cstmt.getInt(1);
    }
    
    public int getHortalizaGuest() throws SQLException{
        String nom = "{call paqUtilidades.hortalizaGuest(?)}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.INTEGER);
        cstmt.execute();
        return cstmt.getInt(1);
    }
    
    private void buttonRealizarTruequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRealizarTruequeActionPerformed
        try {
            crearTrueque();
            if(tipoUsuario != 0){
                if(tipoTrueque == 1){
                    String nom = "{call ventatruequePaq.crearTruequeArbolAA(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setInt(1, getArb1()); //ARBOL 1
                    cstmt.setInt(2, getArb2()); //ARBOL 2
                    cstmt.execute();
                }
                else if(tipoTrueque == 2){
                    String nom = "{call ventatruequePaq.crearTruequeArbolAH(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    System.out.println(getArb1());
                    System.out.println(getHorta2());
                    cstmt.setInt(1, getArb1()); //ARBOL
                    cstmt.setInt(2, getHorta2()); //HORTALIZA
                    cstmt.execute();
                }
                else if(tipoTrueque == 3){
                    String nom = "{call ventatruequePaq.crearTruequeHortalizaHH(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setInt(1, getHorta1()); //HORTALIZA 1
                    cstmt.setInt(2, getHorta2()); //HORTALIZA 2
                    cstmt.execute();
                }
                else if(tipoTrueque == 4){
                    String nom = "{call ventatruequePaq.crearTruequeHortalizaHA(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setInt(1, getHorta1()); //HORTALIZA
                    cstmt.setInt(2, getArb2()); //ARBOL
                    cstmt.execute();
                }
            }
            else if(tipoUsuario == 0){
                if(tipoTrueque == 1){
                    String nom = "{call ventatruequePaq.crearTruequeArbolAA(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setInt(1, getArbolGuest()); //ARBOL 1
                    cstmt.setInt(2, getArb2()); //ARBOL 2
                    cstmt.execute();
                }
                else if(tipoTrueque == 2){
                    String nom = "{call ventatruequePaq.crearTruequeArbolAH(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    System.out.println(getArb1());
                    System.out.println(getHorta2());
                    cstmt.setInt(1, getArbolGuest()); //ARBOL
                    cstmt.setInt(2, getHorta2()); //HORTALIZA
                    cstmt.execute();
                }
                else if(tipoTrueque == 3){
                    String nom = "{call ventatruequePaq.crearTruequeHortalizaHH(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setInt(1, getHortalizaGuest()); //HORTALIZA 1
                    cstmt.setInt(2, getHorta2()); //HORTALIZA 2
                    cstmt.execute();
                }
                else if(tipoTrueque == 4){
                    String nom = "{call ventatruequePaq.crearTruequeHortalizaHA(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setInt(1, getHortalizaGuest()); //HORTALIZA
                    cstmt.setInt(2, getArb2()); //ARBOL
                    cstmt.execute();
                }
            }
            //LANZAR DIALOG NO SE PUDO
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonRealizarTruequeActionPerformed

    public int getArbol2(){
        if (idArboles.isEmpty()) {
            return -1;
        }
        int posSeleccionado = ventaPlantas.getSelectedIndex();
        if(posSeleccionado == -1){
            return -1;
        }
        int id = (Integer) idArboles.get(posSeleccionado);
        return id;
    }
    
    public int getHortaliza2(){
        if (idHortalizas.isEmpty()) {
            return -1;
        }
        int posSeleccionado = ventaPlantas.getSelectedIndex();
        if(posSeleccionado == -1){
            return -1;
        }
        int id = (Integer) idHortalizas.get(posSeleccionado);
        return id;
    }
    
    private void buttonRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRealizarVentaActionPerformed
        try {
            crearVenta();
            if(tipoVenta == 1){
                String nom = "{call ventatruequePaq.crearVentaArbol(?,?)}";
                CallableStatement cstmt = conexion.prepareCall(nom);
                cstmt.setInt(1, getArbol2());
                cstmt.setInt(2, idVenta);
                cstmt.execute();
            }
            if(tipoVenta == 2){
                String nom = "{call ventatruequePaq.crearVentaHortaliza(?,?)}";
                CallableStatement cstmt = conexion.prepareCall(nom);
                cstmt.setInt(1, getHortaliza2());
                cstmt.setInt(2, idVenta);
                cstmt.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonRealizarVentaActionPerformed

    private void buttonCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarVentaActionPerformed
        try {
            cancelarVenta();
            prepareVentas();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCancelarVentaActionPerformed

    private void buttonAgregarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColorActionPerformed

    private void buttonDesactivarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColorActionPerformed

    private void buttonCambiarAdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarAdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCambiarAdUsuarioActionPerformed

    private void nombreAdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreAdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreAdUsuarioActionPerformed

    private void cedulaAdUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaAdUsuarioKeyTyped
        //validarNumeros();

        /*char caracter = evt.getKeyChar();
        if((caracter < '0')||()||()){
            evt.consume();
        }*/
    }//GEN-LAST:event_cedulaAdUsuarioKeyTyped

    private void elegirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegirArchivoActionPerformed
        //dialogFoto.setSize(670, 400);
        //dialogFoto.setVisible(true);   .
        elegirFoto.showOpenDialog(null);
    }//GEN-LAST:event_elegirArchivoActionPerformed

    private void buttonCrearAdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearAdUsuarioActionPerformed
        /*if (!revisarCamposRegistro()) {
            return;
        }*/
 /*try {
            String nom = "{? = call paqPersona.crearPersona(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
            byte[] imagenRegistro = convertToBlob(imagenUsuario.getAbsolutePath());
            SerialBlob blob = new SerialBlob(imagenRegistro);
            crearPersona.setBlob(8, blob);

            crearPersona.setInt(9, 1);

            int huerta = getHuerta();
            crearPersona.setInt(10, huerta);

            int dedicacion = getDedicacion();
            crearPersona.setInt(11, dedicacion);

            crearPersona.setString(12, usuarioRegistro.getText());

            crearPersona.setString(13, codificarPass(String.valueOf(passwordRegistro.getPassword())));
            crearPais.execute();
        } catch (SQLException | ParseException | IOException ex) {
            Logger.getLogger(inicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        setEnabled(true);
        /*registroDialog.dispose();*/
    }//GEN-LAST:event_buttonCrearAdUsuarioActionPerformed

    private void buttonModificarAdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarAdUsuarioActionPerformed
        /*prepareInicio();*/
        setEnabled(true);
        /*registroDialog.dispose();*/
    }//GEN-LAST:event_buttonModificarAdUsuarioActionPerformed

    private void buttonVerAdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerAdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonVerAdUsuarioActionPerformed

    private void elegirFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegirFotoActionPerformed
        int result = elegirFoto.showOpenDialog(null);
        imagenUsuario = elegirFoto.getSelectedFile();

        ImageIcon imgThisImg = new ImageIcon(imagenUsuario.getAbsolutePath());
        Image scaleImage = imgThisImg.getImage().getScaledInstance(180, 170, Image.SCALE_DEFAULT);
        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);

        fotoAdUsuario.setSize(180, 170);
        fotoAdUsuario.setIcon(imgThisImg2);

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

    private void buttonCambiarAdHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarAdHuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCambiarAdHuertaActionPerformed

    private void buttonVerAdHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerAdHuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonVerAdHuertaActionPerformed

    private void nombreAdHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreAdHuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreAdHuertaActionPerformed

    private void longitudAdHuertaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_longitudAdHuertaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_longitudAdHuertaKeyTyped

    private void buttonCrearAdHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearAdHuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCrearAdHuertaActionPerformed

    private void buttonModificarAdHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarAdHuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonModificarAdHuertaActionPerformed

    private void capitalAdHuertaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_capitalAdHuertaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_capitalAdHuertaKeyTyped

    private void cantidadAdHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadAdHortalizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadAdHortalizaActionPerformed

    private void nombreAdHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreAdHortalizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreAdHortalizaActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int result = elegirFoto.showOpenDialog(null);
        imagenUsuario = elegirFoto.getSelectedFile();

        ImageIcon imgThisImg = new ImageIcon(imagenUsuario.getAbsolutePath());
        Image scaleImage = imgThisImg.getImage().getScaledInstance(300, 270, Image.SCALE_DEFAULT);
        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);
        
        ojala1.setSize(300, 270);
        ojala1.setIcon(imgThisImg2);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void buttonCambiarAdHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarAdHortalizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCambiarAdHortalizaActionPerformed

    private void buttonVerAdHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerAdHortalizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonVerAdHortalizaActionPerformed

    private void cantidadAdArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadAdArbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadAdArbolActionPerformed

    private void nombreAdArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreAdArbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreAdArbolActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int result = elegirFoto.showOpenDialog(null);
        imagenUsuario = elegirFoto.getSelectedFile();

        ImageIcon imgThisImg = new ImageIcon(imagenUsuario.getAbsolutePath());
        Image scaleImage = imgThisImg.getImage().getScaledInstance(300, 270, Image.SCALE_DEFAULT);
        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);
        
        ojala2.setSize(300, 270);
        ojala2.setIcon(imgThisImg2);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void buttonCambiarAdArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarAdArbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCambiarAdArbolActionPerformed

    private void buttonVerAdArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerAdArbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonVerAdArbolActionPerformed

    private void nombreCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreCatalogoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreCatalogoActionPerformed

    private void buttonAceptarCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarCatalogoActionPerformed
        try {
            if(catalogo == 1){
                try {
                    String nom = "{call paqPlantas.crearColor(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setString(2, descripcionCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 2){
                try {
                    String nom = "{call paqPlantas.crearTipo(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setString(2, descripcionCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 3){
                try {
                    String nom = "{call paqPlantas.crearPropiedad(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setString(2, descripcionCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 4){
                try {
                    String nom = "{call paqPlantas.crearCaracteristica(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setString(2, descripcionCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 5){
                try {
                    String nom = "{call paqPlantas.crearReproduccion(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setString(2, descripcionCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 6){
                try {
                    String nom = "{call paqPlantas.crearXilema(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setString(2, descripcionCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 7){
                try {
                    String nom = "{call paqPlantas.crearCambium(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setString(2, descripcionCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 8){
                try {
                    String nom = "{call paqPlantas.crearCorteza(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setString(2, descripcionCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 9){
                try {
                    String nom = "{call paqLocacion.crearPais(?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 10){
                try {
                    String nom = "{call paqLocacion.crearProvincia(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setInt(2, getPais());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 11){
                try {
                    String nom = "{call paqLocacion.crearCanton(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setInt(2, getProvincia());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(catalogo == 12){
                try {
                    String nom = "{call paqLocacion.crearDistrito(?,?)}";
                    CallableStatement cstmt = conexion.prepareCall(nom);
                    cstmt.setString(1, nombreCatalogo.getText());
                    cstmt.setInt(2, getCanton());
                    cstmt.execute();
                    catalogos.setVisible(false);
                    dialogAdDatos.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            cargarInfoCatalogos();
            nombreCatalogo.setText("");
            descripcionCatalogo.setText("");
            lugarCatalogo.setVisible(false);
            jLabel76.setVisible(false);
            jLabel74.setVisible(true);
            descripcionCatalogo.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAceptarCatalogoActionPerformed

    public int getProvincia(){
        if (idProvincia.isEmpty()) {
            return -1;
        }
        int posSeleccionado = lugarCatalogo.getSelectedIndex();
        int id = (Integer) idProvincia.get(posSeleccionado);
        return id;
    }
    
    public int getCanton(){
        if (idCanton.isEmpty()) {
            return -1;
        }
        int posSeleccionado = lugarCatalogo.getSelectedIndex();
        int id = (Integer) idCanton.get(posSeleccionado);
        return id;
    }
    
    public int getPais(){
        if (idPais.isEmpty()) {
            return -1;
        }
        int posSeleccionado = lugarCatalogo.getSelectedIndex();
        int id = (Integer) idPais.get(posSeleccionado);
        return id;
    }
    
    private void buttonCancelarCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarCatalogoActionPerformed
        catalogos.setVisible(false);
        dialogAdDatos.setVisible(true);
    }//GEN-LAST:event_buttonCancelarCatalogoActionPerformed

    private void sliderCalificacion1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderCalificacion1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_sliderCalificacion1StateChanged

    private void buttonEnviarCalificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEnviarCalificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEnviarCalificacionActionPerformed

    private void buttonAceptarAdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarAdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAceptarAdUsuarioActionPerformed

    private void buttonCancelarAdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarAdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelarAdUsuarioActionPerformed

    private void buttonCancelarAdHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarAdHuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelarAdHuertaActionPerformed

    private void buttonAceptarAdHuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarAdHuertaActionPerformed
        try {
            String nom = "{call paqHuerta.crearHuerta(?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            
            cstmt.setString(1, nombreAdHuerta.getText());
            cstmt.setFloat(2, Float.parseFloat(latitudAdHuerta.getText()));
            cstmt.setFloat(3, Float.parseFloat(longitudAdHuerta.getText()));
            cstmt.setInt(4, getDisrtito());
            cstmt.setInt(5, Integer.parseInt(capitalAdHuerta.getText()));
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAceptarAdHuertaActionPerformed

//    ArrayList<Object> idDistrito = new ArrayList(); //DISTRITO
//    ArrayList<String> nombreDistrito = new ArrayList();
    
    public int getDisrtito() {
        int posSeleccionado = distritoAdHuerta.getSelectedIndex();
        int id = (Integer) idDistrito.get(posSeleccionado);
        return id;
    }
    
    public int getHuerta2() {
        int posSeleccionado = huertaAdHortaliza.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }
    
    public int getColor() {
        int posSeleccionado = colorAdHortaliza.getSelectedIndex();
        int id = (Integer) idColor.get(posSeleccionado);
        return id;
    }
    
    public int getTipo() {
        int posSeleccionado = tipoAdHortaliza.getSelectedIndex();
        int id = (Integer) idTipo.get(posSeleccionado);
        return id;
    }
    
    public int getPropiedad() {
        int posSeleccionado = propiedadAdHortaliza.getSelectedIndex();
        int id = (Integer) idPropiedad.get(posSeleccionado);
        return id;
    }
    
    public int getCaracteristica() {
        int posSeleccionado = caracteristicaAdHortaliza.getSelectedIndex();
        int id = (Integer) idCaracteristica.get(posSeleccionado);
        return id;
    }
    
    private void buttonAceptarAdHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarAdHortalizaActionPerformed
        try {
            String nom = "{call paqPlantas.crearHortaliza(?,?,?,?,?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setString(1, nombreAdHortaliza.getText());
            cstmt.setInt(2, Integer.parseInt(precioAdHortaliza.getText()));
            File blob2 = new File(imagenUsuario.getAbsolutePath());
            FileInputStream in = new FileInputStream(blob2);

            cstmt.setBinaryStream(3, in, (int)blob2.length());
            cstmt.setInt(4, getHuerta2());
            cstmt.setInt(5, getColor());
            cstmt.setInt(6, getTipo());
            cstmt.setInt(7, getPropiedad());
            cstmt.setInt(8, getCaracteristica());
            cstmt.setInt(9, Integer.parseInt(cantidadAdHortaliza.getText()));
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAceptarAdHortalizaActionPerformed

    private void buttonCancelarAdHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarAdHortalizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelarAdHortalizaActionPerformed

    private void buttonCrearAdHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearAdHortalizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCrearAdHortalizaActionPerformed

    private void buttonModificarAdHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarAdHortalizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonModificarAdHortalizaActionPerformed

    private int getExtincion(){
        if(jRadioButton3.isSelected()){
            return 1;
        }
        else if(jRadioButton4.isSelected()){
            return 0;
        }
        return 0;
    }
    
    public int getHuerta3() {
        int posSeleccionado = huertaAdArbol.getSelectedIndex();
        int id = (Integer) idHuertas.get(posSeleccionado);
        return id;
    }
    
    public int getReproduccion() {
        int posSeleccionado = reproduccionAdArbol.getSelectedIndex();
        int id = (Integer) idReproduccion.get(posSeleccionado);
        return id;
    }
    
    public int getXilema() {
        int posSeleccionado = xilemaAdArbol.getSelectedIndex();
        int id = (Integer) idXilema.get(posSeleccionado);
        return id;
    }
    
    public int getCambium() {
        int posSeleccionado = cambiumAdArbol.getSelectedIndex();
        int id = (Integer) idCambium.get(posSeleccionado);
        return id;
    }
    
    public int getCorteza() {
        int posSeleccionado = cortezaAdArbol.getSelectedIndex();
        int id = (Integer) idCorteza.get(posSeleccionado);
        return id;
    }
    
    private void buttonAceptarAdArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarAdArbolActionPerformed
        try {
            String nom = "{call paqPlantas.crearArbol(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setString(1, nombreAdArbol.getText());
            cstmt.setInt(2, Integer.parseInt(precioAdArbol.getText()));
            cstmt.setInt(3, getExtincion());
            File blob2 = new File(imagenUsuario.getAbsolutePath());
            FileInputStream in = new FileInputStream(blob2);

            cstmt.setBinaryStream(4, in, (int)blob2.length());
            cstmt.setInt(5, getHuerta3());
            cstmt.setInt(6, getReproduccion());
            cstmt.setInt(7, getXilema());
            cstmt.setInt(8, getCambium());
            cstmt.setInt(9, getCorteza());
            cstmt.setInt(10, Integer.parseInt(cantidadAdArbol.getText()));
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAceptarAdArbolActionPerformed

    private void buttonCancelarAdArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarAdArbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelarAdArbolActionPerformed

    private void buttonCrearAdArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearAdArbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCrearAdArbolActionPerformed

    private void buttonModificarAdArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarAdArbolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonModificarAdArbolActionPerformed

    private void buttonAgregarColor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor1ActionPerformed
        setEnabled(true);
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 1;
    }//GEN-LAST:event_buttonAgregarColor1ActionPerformed

    private void buttonDesactivarColor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor1ActionPerformed

    private void buttonAgregarColor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor2ActionPerformed
//        setEnabled(true);
//        opcionesAdministrador.dispose();
//        catalogos.setSize(260, 250);
//        catalogos.setLocationRelativeTo(null);
//        dialogAdDatos.setVisible(false);
//        catalogos.setVisible(true);
//        setEnabled(false);
//        catalogo = 5;
    }//GEN-LAST:event_buttonAgregarColor2ActionPerformed

    private void buttonAgregarColor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor3ActionPerformed
        setEnabled(true);
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 2;
    }//GEN-LAST:event_buttonAgregarColor3ActionPerformed

    private void buttonDesactivarColor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor2ActionPerformed

    private void buttonAgregarColor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor4ActionPerformed

    }//GEN-LAST:event_buttonAgregarColor4ActionPerformed

    private void buttonAgregarColor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor5ActionPerformed
        // TODO add your handling code here:setEnabled(true);
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 3;
    }//GEN-LAST:event_buttonAgregarColor5ActionPerformed

    private void buttonDesactivarColor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor3ActionPerformed

    private void buttonAgregarColor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor6ActionPerformed

    private void buttonAgregarColor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor7ActionPerformed
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 4;
    }//GEN-LAST:event_buttonAgregarColor7ActionPerformed

    private void buttonDesactivarColor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor4ActionPerformed

    private void buttonAgregarColor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor8ActionPerformed

    private void buttonAgregarColor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor9ActionPerformed
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 5;
    }//GEN-LAST:event_buttonAgregarColor9ActionPerformed

    private void buttonDesactivarColor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor5ActionPerformed

    private void buttonAgregarColor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor10ActionPerformed

    private void buttonAgregarColor11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor11ActionPerformed
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 6;
    }//GEN-LAST:event_buttonAgregarColor11ActionPerformed

    private void buttonDesactivarColor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor6ActionPerformed

    private void buttonAgregarColor12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor12ActionPerformed

    private void buttonAgregarColor13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor13ActionPerformed
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 7;
    }//GEN-LAST:event_buttonAgregarColor13ActionPerformed

    private void buttonDesactivarColor7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor7ActionPerformed

    private void buttonAgregarColor14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor14ActionPerformed

    private void buttonAgregarColor15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor15ActionPerformed
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 8;
    }//GEN-LAST:event_buttonAgregarColor15ActionPerformed

    private void buttonDesactivarColor8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor8ActionPerformed

    private void buttonAgregarColor16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor16ActionPerformed

    private void buttonAgregarColor17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor17ActionPerformed
        jLabel74.setVisible(false);
        descripcionCatalogo.setVisible(false);
        opcionesAdministrador.dispose();
        catalogos.setSize(260, 250);
        catalogos.setLocationRelativeTo(null);
        dialogAdDatos.setVisible(false);
        catalogos.setVisible(true);
        setEnabled(false);
        catalogo = 9;
    }//GEN-LAST:event_buttonAgregarColor17ActionPerformed

    private void buttonDesactivarColor9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor9ActionPerformed

    private void buttonAgregarColor18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor18ActionPerformed

    public void cargarLocacion(int num) throws SQLException{
        if(num == 1){
            idPais.clear();
            nombrePais.clear();
            modeloLocacion.removeAllElements();
            String nom = "{? = call paqUtilidades.paisInfo()}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while (cursor.next()) {
                for (int i = 1; i < 3; i++) {
                    cursor.getString(i);
                    if (i == 1) {
                        idPais.add(cursor.getInt(i));
                    } else if (i == 2) {
                        nombrePais.add(cursor.getString(i));
                    }
                }
            }
            for (String hortaliza : nombrePais) {
                modeloLocacion.addElement(hortaliza);
            }
        }
        else if(num == 2){
            idProvincia.clear();
            nombreProvincia.clear();
            modeloLocacion.removeAllElements();
            String nom = "{? = call paqUtilidades.provinciaInfo()}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while (cursor.next()) {
                for (int i = 1; i < 3; i++) {
                    if (i == 1) {
                        idProvincia.add(cursor.getInt(i));
                    } else if (i == 2) {
                        nombreProvincia.add(cursor.getString(i));
                    }
                }
            }
            for (String hortaliza : nombreProvincia) {
                modeloLocacion.addElement(hortaliza);
            }
        }
        else if(num == 3){
            idCanton.clear();
            nombreCanton.clear();
            modeloLocacion.removeAllElements();
            String nom = "{? = call paqUtilidades.cantonInfo()}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor = (ResultSet) cstmt.getObject(1);
            while (cursor.next()) {
                for (int i = 1; i < 3; i++) {
                    System.out.println("Name = " + cursor.getString(i));
                    if (i == 1) {
                        idCanton.add(cursor.getInt(i));
                    } else if (i == 2) {
                        nombreCanton.add(cursor.getString(i));
                    }
                }
            }
            for (String hortaliza : nombreCanton) {
                modeloLocacion.addElement(hortaliza);
            }
        }
    }
    
    private void buttonAgregarColor19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor19ActionPerformed
        try {
            cargarLocacion(1);
            jLabel74.setVisible(false);
            descripcionCatalogo.setVisible(false);
            jLabel76.setVisible(true);
            jLabel76.setText("PAIS");
            lugarCatalogo.setVisible(true);
            opcionesAdministrador.dispose();
            catalogos.setSize(260, 250);
            catalogos.setLocationRelativeTo(null);
            dialogAdDatos.setVisible(false);
            catalogos.setVisible(true);
            setEnabled(false);
            catalogo = 10;
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAgregarColor19ActionPerformed

    private void buttonDesactivarColor10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor10ActionPerformed

    private void buttonAgregarColor20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor20ActionPerformed

    private void buttonAgregarColor21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor21ActionPerformed
        try {
            cargarLocacion(2);
            jLabel74.setVisible(false);
            descripcionCatalogo.setVisible(false);
            jLabel76.setVisible(true);
            jLabel76.setText("PROVINCIA");
            lugarCatalogo.setVisible(true);
            opcionesAdministrador.dispose();
            catalogos.setSize(260, 250);
            catalogos.setLocationRelativeTo(null);
            dialogAdDatos.setVisible(false);
            catalogos.setVisible(true);
            setEnabled(false);
            catalogo = 11;
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAgregarColor21ActionPerformed

    private void buttonDesactivarColor11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDesactivarColor11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDesactivarColor11ActionPerformed

    private void buttonAgregarColor22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAgregarColor22ActionPerformed

    private void buttonAgregarColor23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarColor23ActionPerformed
        try {
            cargarLocacion(3);
            jLabel74.setVisible(false);
            descripcionCatalogo.setVisible(false);
            jLabel76.setVisible(true);
            jLabel76.setText("CANTON");
            lugarCatalogo.setVisible(true);
            opcionesAdministrador.dispose();
            catalogos.setSize(260, 250);
            catalogos.setLocationRelativeTo(null);
            dialogAdDatos.setVisible(false);
            catalogos.setVisible(true);
            setEnabled(false);
            catalogo = 12;
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAgregarColor23ActionPerformed

    private void buttonCambiarAdArbol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarAdArbol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCambiarAdArbol1ActionPerformed

    private void buttonVerAdArbol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerAdArbol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonVerAdArbol1ActionPerformed

    private void nombreAdArbol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreAdArbol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreAdArbol1ActionPerformed

    private void buttonCrearAdArbol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearAdArbol1ActionPerformed
        try {
            String nom = "{call paqAbono.crearAbono(?, ?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setString(1, nombreAdArbol1.getText());
            cstmt.setString(2, descripcionCatalogo1.getText());
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCrearAdArbol1ActionPerformed

    private void buttonModificarAdArbol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarAdArbol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonModificarAdArbol1ActionPerformed

    private void buttonVerAdArbol2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerAdArbol2ActionPerformed
        modeloPlantas.removeAllElements();
        idPlanta.clear();
        nombrePlanta.clear();
        try {
            tipoAbono = 1;
            buttonVerAdArbol2.setBackground(Color.WHITE);
            buttonCambiarAdArbol2.setBackground(Color.BLACK);
            String nom = "{? = call paqUtilidades.hortalizaInfo()}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor2 = (ResultSet) cstmt.getObject(1);
            //1 idcambium
            //2 nombre
            while(cursor2.next()){
                for(int i = 1; i<3; i++){
                    if (i == 1) {
                        idPlanta.add(cursor2.getInt(i));
                    } else if (i == 2) {
                        nombrePlanta.add(cursor2.getString(i));
                    }
                }
            }
            for (String planta : nombrePlanta) {
                modeloPlantas.addElement(planta);
            }
            arbolesAdArbol4.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonVerAdArbol2ActionPerformed

    private void buttonCambiarAdArbol2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarAdArbol2ActionPerformed
        try {
            modeloPlantas.removeAllElements();
            idPlanta.clear();
            nombrePlanta.clear();
            tipoAbono = 2;
            buttonVerAdArbol2.setBackground(Color.BLACK);
            buttonCambiarAdArbol2.setBackground(Color.WHITE);
            String nom = "{? = call paqUtilidades.arbolInfo()}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet cursor2 = (ResultSet) cstmt.getObject(1);
            //1 idcambium
            //2 nombre
            while(cursor2.next()){
                for(int i = 1; i<3; i++){
                    if (i == 1) {
                        idPlanta.add(cursor2.getInt(i));
                    } else if (i == 2) {
                        nombrePlanta.add(cursor2.getString(i));
                    }
                }
            }
            for (String planta : nombrePlanta) {
                modeloPlantas.addElement(planta);
            }
            arbolesAdArbol4.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCambiarAdArbol2ActionPerformed

    public int getAbono() {
        if (idAbono.isEmpty()) {
            return -1;
        }
        int posSeleccionado = arbolesAdArbol3.getSelectedIndex();
        int id = (Integer) idAbono.get(posSeleccionado);
        return id;
    }
    
    public int getHortalizas() {
        if (idHortalizas.isEmpty()) {
            return -1;
        }
        int posSeleccionado = arbolesAdArbol4.getSelectedIndex();
        int id = (Integer) idHortalizas.get(posSeleccionado);
        return id;
    }
    
    public int getArboles() {
        if (idArboles.isEmpty()) {
            return -1;
        }
        int posSeleccionado = arbolesAdArbol4.getSelectedIndex();
        int id = (Integer) idArboles.get(posSeleccionado);
        return id;
    }
    
    private void buttonCrearAdArbol2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearAdArbol2ActionPerformed
        try {
            String nom = "{call paqAbono.crearRegistroAbono(?, ?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setInt(1, getAbono());
            cstmt.setInt(2, idUser);
            cstmt.execute();
            if(tipoAbono == 1){
                System.out.println(getHortalizas());
                String nom2 = "{call paqAbono.registrarAbonoHortaliza(?, ?)}";
                CallableStatement cstmt2 = conexion.prepareCall(nom2);
                cstmt2.setInt(1, getAbono());
                cstmt2.setInt(2, getHortalizas());
                cstmt2.execute();
            }
            else if(tipoAbono == 2){
                String nom3 = "{call paqAbono.registrarAbonoArbol(?, ?)}";
                CallableStatement cstmt3 = conexion.prepareCall(nom3);
                cstmt3.setInt(1, getAbono());
                cstmt3.setInt(2, getArboles());
                cstmt3.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCrearAdArbol2ActionPerformed

    private void buttonAceptarAdArbol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarAdArbol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAceptarAdArbol1ActionPerformed

    private void buttonCancelarAdArbol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarAdArbol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelarAdArbol1ActionPerformed

    private void buttonCambiarAdUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiarAdUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCambiarAdUsuario1ActionPerformed

    private void buttonVerAdUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerAdUsuario1ActionPerformed
        int sel = usuariosAdministrar1.getSelectedIndex();
        System.out.println(sel);
        System.out.println(tipoConsulta);
        switch (tipoConsulta) {
            case 1: // Solo dos fechas y textfield de numeros
                //cargarDatosConsulta
		nombreCatalogo1.setText("10");
                
                panelFiltrosConsulta.removeAll();
                panelFiltrosConsulta.add(panelFiltro1, BorderLayout.CENTER);
                panelFiltrosConsulta.revalidate();
                panelFiltrosConsulta.repaint();
                panelFiltrosConsulta.setVisible(true);
                if(sel == 9){
                    if(jCheckBox2.isSelected()){
                        try {
                            String nom = "{? = call paqCursores.diasMayoresTruequesAsc(?,?,?)}";
                            CallableStatement cstmt = conexion.prepareCall(nom);
                            cstmt.setInt(1, Integer.parseInt(nombreCatalogo1.getText()));
                            
                            String input = fechaAdUsuario1.getText();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            java.util.Date dt = null;
                            dt = sdf.parse(input);
                            java.sql.Date dtSql = new java.sql.Date(dt.getTime());
                            cstmt.setDate(2, dtSql);
                            
                            String input2 = fechaAdUsuario2.getText();
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                            java.util.Date dt2 = null;
                            dt2 = sdf2.parse(input2);
                            java.sql.Date dtSql2 = new java.sql.Date(dt2.getTime());
                            cstmt.setDate(3, dtSql2);
                            
                            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                            cstmt.execute();
                            ResultSet cursor = (ResultSet) cstmt.getObject(1);
                            while(cursor.next()){
                                jTextArea2.setText(String.valueOf(cursor.getDate(1)) + "\t" + cursor.getInt(2));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        try {
                            String nom = "{? = call paqCursores.diasMayoresTruequesDesc(?,?,?)}";
                            CallableStatement cstmt = conexion.prepareCall(nom);
                            cstmt.setInt(1, Integer.parseInt(nombreCatalogo1.getText()));
                            
                            String input = fechaAdUsuario1.getText();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            java.util.Date dt = null;
                            dt = sdf.parse(input);
                            java.sql.Date dtSql = new java.sql.Date(dt.getTime());
                            cstmt.setDate(2, dtSql);
                            
                            String input2 = fechaAdUsuario2.getText();
                            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                            java.util.Date dt2 = null;
                            dt2 = sdf2.parse(input2);
                            java.sql.Date dtSql2 = new java.sql.Date(dt2.getTime());
                            cstmt.setDate(3, dtSql2);
                            
                            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                            cstmt.execute();
                            ResultSet cursor = (ResultSet) cstmt.getObject(1);
                            while(cursor.next()){
                                jTextArea2.setText(String.valueOf(cursor.getDate(1)) + "\t" + cursor.getInt(2));
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                break;
            case 2: //Solo una fecha
		//cargar info de la consulta
                panelFiltrosConsulta.removeAll();
                panelFiltrosConsulta.add(panelFiltro2, BorderLayout.CENTER);
                panelFiltrosConsulta.revalidate();
                panelFiltrosConsulta.repaint();
                panelFiltrosConsulta.setVisible(true);
                break;
            case 3: //un combo box
		//cargar datos de la consulta y del combo box
                panelFiltrosConsulta.removeAll();
                panelFiltrosConsulta.add(panelFiltro3, BorderLayout.CENTER);
                panelFiltrosConsulta.revalidate();
                panelFiltrosConsulta.repaint();
                panelFiltrosConsulta.setVisible(true);
                
                break;
            case 4:
                panelFiltrosConsulta.setVisible(false);
                if(sel == 4){
                    String texto = "";
                    try {
                        String nom = "{? = call paqConsultas.hortalizaColor()}";
                        CallableStatement cstmt = conexion.prepareCall(nom);
                        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        cstmt.execute();
                        ResultSet cursor = (ResultSet) cstmt.getObject(1);
                        while(cursor.next()){
                            texto += (cursor.getString(1) + "\t" + cursor.getString(2) + "\n");
                        }
                        texto += "\n\nTotales\n\n";
                        nom = "{? = call paqConsultas.totalHortalizaColor()}";
                        CallableStatement cstmt2 = conexion.prepareCall(nom);
                        cstmt2.registerOutParameter(1, OracleTypes.CURSOR);
                        cstmt2.execute();
                        ResultSet cursor2 = (ResultSet) cstmt2.getObject(1);
                        while(cursor2.next()){
                                texto += (cursor2.getString(1) + "\t" + String.valueOf(cursor2.getInt(2)) + "\n");
                        }
                        jTextArea2.setText(texto);
                    } catch (SQLException ex) {
                        Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(sel == 5){
                   String texto = "";
                    try {
                        String nom = "{? = call paqConsultas.hortalizaTipo()}";
                        CallableStatement cstmt = conexion.prepareCall(nom);
                        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        cstmt.execute();
                        ResultSet cursor = (ResultSet) cstmt.getObject(1);
                        while(cursor.next()){
                            texto += (cursor.getString(1) + "\t" + cursor.getString(2) + "\n");
                        }
                        texto += "\n\nTotales\n\n";
                        nom = "{? = call paqConsultas.totalHortalizaTipo()}";
                        CallableStatement cstmt2 = conexion.prepareCall(nom);
                        cstmt2.registerOutParameter(1, OracleTypes.CURSOR);
                        cstmt2.execute();
                        ResultSet cursor2 = (ResultSet) cstmt2.getObject(1);
                        while(cursor2.next()){
                                texto += (cursor2.getString(1) + "\t" + String.valueOf(cursor2.getInt(2)) + "\n");
                        }
                        jTextArea2.setText(texto);
                    } catch (SQLException ex) {
                        Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_buttonVerAdUsuario1ActionPerformed

    private void nombreCatalogo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreCatalogo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreCatalogo1ActionPerformed

    private void buttonAceptarCatalogo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarCatalogo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAceptarCatalogo1ActionPerformed

    private void buttonAceptarCatalogo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarCatalogo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAceptarCatalogo2ActionPerformed

    private void buttonAceptarCatalogo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAceptarCatalogo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAceptarCatalogo3ActionPerformed

    private void buttonCrearHortalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearHortalizaActionPerformed
        setVisible(false);
        buttonAdCatalogos.setVisible(false);
        buttonAdUsuarios.setVisible(false);
        buttonAdHuertas.setVisible(false);
        buttonAdHortalizas.setVisible(false);
        buttonAdArboles.setVisible(false);
        buttonAdAbonos.setVisible(false);
        huertaAdHortaliza.removeAllItems();
        colorAdHortaliza.removeAllItems();
        tipoAdHortaliza.removeAllItems();
        propiedadAdHortaliza.removeAllItems();
        caracteristicaAdHortaliza.removeAllItems();
        try {
            setEnabled(true);
            opcionesAdministrador.dispose();
            dialogAdDatos.setSize(1050, 570);
            dialogAdDatos.setLocationRelativeTo(null);
            dialogAdDatos.setVisible(true);
            setEnabled(false);
            
            prepareAdHortalizas();
            panelAdHortalizas.setSize(890, 508);
            panelAdDatos.removeAll();
            panelAdDatos.setSize(890, 508);
            panelAdDatos.add(panelAdHortalizas, BorderLayout.CENTER);
            panelAdDatos.revalidate();
            panelAdDatos.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCrearHortalizaActionPerformed

    private void buttonCrearArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearArbolActionPerformed
        setVisible(false);
        buttonAdCatalogos.setVisible(false);
        buttonAdUsuarios.setVisible(false);
        buttonAdHuertas.setVisible(false);
        buttonAdHortalizas.setVisible(false);
        buttonAdArboles.setVisible(false);
        buttonAdAbonos.setVisible(false);
        try {
            setEnabled(true);
            opcionesAdministrador.dispose();
            dialogAdDatos.setSize(1050, 570);
            dialogAdDatos.setLocationRelativeTo(null);
            dialogAdDatos.setVisible(true);
            setEnabled(false);
            
            prepareAdArboles();
            panelAdArboles.setSize(890, 508);
            panelAdDatos.removeAll();
            panelAdDatos.setSize(890, 508);
            panelAdDatos.add(panelAdArboles, BorderLayout.CENTER);
            panelAdDatos.revalidate();
            panelAdDatos.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCrearArbolActionPerformed

    private void hortalizasHuertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hortalizasHuertasActionPerformed
        try {    
            getHortalizaxHuerta(getHuerta6());
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hortalizasHuertasActionPerformed

    private void arbolesHuertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arbolesHuertasActionPerformed
        
    }//GEN-LAST:event_arbolesHuertasActionPerformed

    public void actualizarPlantas() throws SQLException{
        String nom = "{? = call paqUtilidades.arbolInfo()}";
        CallableStatement cstmt = conexion.prepareCall(nom);
        cstmt.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt.execute();
        ResultSet cursor = (ResultSet) cstmt.getObject(1);
        //1 idarbol
        //2 nombre
        while(cursor.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idArboles.add(cursor.getInt(i));
                } else if (i == 2) {
                    nombreArboles.add(cursor.getString(i));
                }
            }
        }
        String nom2 = "{? = call paqUtilidades.HortalizaInfo()}";
        CallableStatement cstmt2 = conexion.prepareCall(nom2);
        cstmt2.registerOutParameter(1, OracleTypes.CURSOR);
        cstmt2.execute();
        ResultSet cursor2 = (ResultSet) cstmt2.getObject(1);
        //1 idarbol
        //2 nombre
        while(cursor2.next()){
            for(int i = 1; i<3; i++){
                if (i == 1) {
                    idHortalizas.add(cursor2.getInt(i));
                } else if (i == 2) {
                    nombreHortalizas.add(cursor2.getString(i));
                }
            }
        }
    }
    
    private void buttonAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAbonosActionPerformed
        modeloPlantas.removeAllElements();
        arbolesAdArbol4.setEnabled(false);
        if(tipoUsuario > 1){
            arbolesAdArbol2.setVisible(false);
            jLabel91.setVisible(false);
        }
        else if(tipoUsuario == 1){
            arbolesAdArbol2.setVisible(true);
            jLabel91.setVisible(true);
        }
        try {
            actualizarPlantas();
            cargarInfoAbonos();
            setVisible(false);
            buttonAdCatalogos.setVisible(false);
            buttonAdUsuarios.setVisible(false);
            buttonAdHuertas.setVisible(false);
            buttonAdHortalizas.setVisible(false);
            buttonAdArboles.setVisible(false);
            buttonAdAbonos.setVisible(false);
            setEnabled(true);
            opcionesAdministrador.dispose();
            dialogAdDatos.setSize(1050, 570);
            dialogAdDatos.setLocationRelativeTo(null);
            dialogAdDatos.setVisible(true);
            setEnabled(false);
            panelAdArboles.setSize(890, 508);
            panelAdDatos.removeAll();
            panelAdDatos.setSize(890, 508);
            panelAdDatos.add(panelAdAbonos, BorderLayout.CENTER);
            panelAdDatos.revalidate();
            panelAdDatos.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAbonosActionPerformed

    private void nombreAdArbolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreAdArbolKeyTyped
//        char c = evt.getKeyChar();
//
//        if(Character.isDigit(c)){	
//                evt.consume();
//                getToolkit.beep();
//                JOptionePane.showMessageDialog(this, "Ingrese solo letras");
//        }
    }//GEN-LAST:event_nombreAdArbolKeyTyped

    private void hortalizasHuertasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hortalizasHuertasItemStateChanged
        try {    
            getHortalizaxHuerta(getHuerta6());
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hortalizasHuertasItemStateChanged

    private void arbolesHuertasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_arbolesHuertasItemStateChanged
        try {
            getArbolxHuerta();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_arbolesHuertasItemStateChanged

    private void usuariosAdministrar1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usuariosAdministrar1ItemStateChanged
        int sel = usuariosAdministrar1.getSelectedIndex();
        if(sel == 9 || sel == 10){
            //dos calendarios
            tipoConsulta = 1;
        }
        else if(sel == 0 || sel == 1){
            //combo box
            tipoConsulta = 3;
        }
        else if(sel == 2 || sel == 3){
            //
            tipoConsulta = 2;
        }
        else{
            tipoConsulta = 4;
        }
        
    }//GEN-LAST:event_usuariosAdministrar1ItemStateChanged

    private void buttonAgUnidadesHortalizasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgUnidadesHortalizasActionPerformed
        try {
            // agregarHortaliza (id_hortaliza number ,p_cantidad number )
            if(getHortaliza() == -1){
                return;
            }
            String nom = "{call paqPlantas.agregarHortaliza(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setInt(1, getHortaliza());
            cstmt.setInt(2, 1);
            cstmt.execute();
            cargarInfoHortaliza(getHortaliza());
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAgUnidadesHortalizasActionPerformed

    private void buttonAgUnidadesArbolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgUnidadesArbolesActionPerformed
        try {
            // agregarHortaliza (id_hortaliza number ,p_cantidad number )
            if(getArbol() == -1){
                return;
            }
            String nom = "{call paqPlantas.agregarArbol(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setInt(1, getArbol());
            cstmt.setInt(2, 1);
            cstmt.execute();
            cargarInfoArbol(getArbol());
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAgUnidadesArbolesActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged

    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        System.out.println("Entre");
        if(jCheckBox1.isSelected()){
            System.out.println("Entre2");
            try {
                String nom = "{call paqPersona.insertarInteresado(?,?)}";
                CallableStatement cstmt = conexion.prepareCall(nom);
                cstmt.setInt(1, idUser);
                cstmt.setInt(2, getHuerta());
                cstmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                System.out.println("Entre3");
                String nom = "{call paqPersona.borrarInteresado(?,?)}";
                CallableStatement cstmt = conexion.prepareCall(nom);
                cstmt.setInt(1, idUser);
                cstmt.setInt(2, getHuerta());
                cstmt.execute(); 
            } catch (SQLException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void nombreAdHortaliza1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreAdHortaliza1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreAdHortaliza1ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        dialogInvitado.dispose();
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void nombreAdArbol2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreAdArbol2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreAdArbol2ActionPerformed

    private void nombreAdArbol2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreAdArbol2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreAdArbol2KeyTyped

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        dialogInvitado.setSize(1050, 570);
        dialogInvitado.setLocationRelativeTo(null);
        dialogInvitado.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        setVisible(true);
        dialogInvitado.setVisible(false);
    }//GEN-LAST:event_jToggleButton6ActionPerformed
   
    public int getColor2() {
        int posSeleccionado = colorAdHortaliza1.getSelectedIndex();
        int id = (Integer) idColor.get(posSeleccionado);
        return id;
    }
    
    public int getTipo2() {
        int posSeleccionado = tipoAdHortaliza1.getSelectedIndex();
        int id = (Integer) idTipo.get(posSeleccionado);
        return id;
    }
    
    public int getPropiedad2() {
        int posSeleccionado = propiedadAdHortaliza1.getSelectedIndex();
        int id = (Integer) idPropiedad.get(posSeleccionado);
        return id;
    }
    
    public int getCaracteristica2() {
        int posSeleccionado = caracteristicaAdHortaliza1.getSelectedIndex();
        int id = (Integer) idCaracteristica.get(posSeleccionado);
        return id;
    }
    
    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        try {
            String nom = "{call paqPlantas.crearHortaliza(?,?,?,?,?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setString(1, nombreAdHortaliza1.getText());
            cstmt.setInt(2, Integer.parseInt(precioAdHortaliza1.getText()));
            File blob2 = new File(imagenUsuario.getAbsolutePath());
            FileInputStream in = new FileInputStream(blob2);
            cstmt.setBinaryStream(3, in, (int)blob2.length());
            cstmt.setInt(4, 0);
            cstmt.setInt(5, getColor2());
            cstmt.setInt(6, getTipo2());
            cstmt.setInt(7, getPropiedad2());
            cstmt.setInt(8, getCaracteristica2());
            cstmt.setInt(9, 1);
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        dialogInvitado.dispose();
        buttonRealizarTrueque.setVisible(true);
        setVisible(true);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        try {
            String nom = "{call paqPlantas.crearArbol(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(nom);
            cstmt.setString(1, nombreAdArbol2.getText());
            cstmt.setInt(2, Integer.parseInt(precioAdArbol1.getText()));
            cstmt.setInt(3, getExtincion());
            File blob2 = new File(imagenUsuario.getAbsolutePath());
            FileInputStream in = new FileInputStream(blob2);
            cstmt.setBinaryStream(4, in, (int)blob2.length());
            cstmt.setInt(5, 0);
            cstmt.setInt(6, getReproduccion());
            cstmt.setInt(7, getXilema());
            cstmt.setInt(8, getCambium());
            cstmt.setInt(9, getCorteza());
            cstmt.setInt(10, 1);
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        dialogInvitado.dispose();
        buttonRealizarTrueque.setVisible(true);
        setVisible(true);
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int result = elegirFoto.showOpenDialog(null);
        imagenUsuario = elegirFoto.getSelectedFile();

        ImageIcon imgThisImg = new ImageIcon(imagenUsuario.getAbsolutePath());
        Image scaleImage = imgThisImg.getImage().getScaledInstance(300, 270, Image.SCALE_DEFAULT);
        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);
        
        ojala3.setSize(300, 270);
        ojala3.setIcon(imgThisImg2);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int result = elegirFoto.showOpenDialog(null);
        imagenUsuario = elegirFoto.getSelectedFile();

        ImageIcon imgThisImg = new ImageIcon(imagenUsuario.getAbsolutePath());
        Image scaleImage = imgThisImg.getImage().getScaledInstance(300, 270, Image.SCALE_DEFAULT);
        ImageIcon imgThisImg2 = new ImageIcon(scaleImage);
        
        ojala4.setSize(300, 270);
        ojala4.setIcon(imgThisImg2);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        try {
            int sel = jComboBox1.getSelectedIndex()+1;
            switch(sel){
                case 1:
                    personaIntervalos();
                    break;
                case 2:
                    hortalizaColor();
                    break;
                case 3:
                    hortalizaColor();
                    break;
                case 4:
                    arbolReproduccion();
                    break;
                case 5:
                    arbolXiema();
                    break;
                case 6:
                    arbolcambium();
                    break;
                case 7:
                    arbolCorteza();
                    break;
                case 8:
                    truequesAno();
                    break;
                case 9:
                    ventasAno();
                    break;
                case 10:
                    topTrueques();
                    break;
                case 11:
                    topVentasH();
                    break;
                case 12:
                    topVentasA();
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @return
     */
    public JPanel panelPrincipal() {
        return panelPrincipal;
    }

    public void main(String args[]) throws SQLException {
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
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public principal Conectar() throws SQLException {
        ResultSet rs = null;
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
//            String nom = "{? = call paqUtilidades.paisInfo()}";
//        CallableStatement crearPais = conexion.prepareCall(nom);
//        crearPais.setString(1, "Belgica");
//        crearPais.execute();
//            CallableStatement cstmt = conexion.prepareCall(nom);
//            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
//            cstmt.execute();
//            ResultSet cursor = (ResultSet) cstmt.getObject(1);
//            while (cursor.next()) {
//                System.out.println("Name = " + cursor.getString(2));
//            }
        return this;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoAdUsuario;
    private javax.swing.JPanel arbolInvitado;
    private javax.swing.JComboBox<String> arbolesAdArbol;
    private javax.swing.JComboBox<String> arbolesAdArbol1;
    private javax.swing.JComboBox<String> arbolesAdArbol2;
    private javax.swing.JComboBox<String> arbolesAdArbol3;
    private javax.swing.JComboBox<String> arbolesAdArbol4;
    private javax.swing.JLabel arbolesCambium;
    private javax.swing.JLabel arbolesCantidad;
    private javax.swing.JLabel arbolesCorteza;
    private javax.swing.JLabel arbolesExtincion;
    private javax.swing.JComboBox<String> arbolesH;
    private javax.swing.JComboBox<String> arbolesHuertas;
    private javax.swing.JList<String> arbolesList;
    private javax.swing.JLabel arbolesNombre;
    private javax.swing.JLabel arbolesPrecio;
    private javax.swing.JLabel arbolesReproduccion;
    private javax.swing.JLabel arbolesXilema;
    private javax.swing.JButton buttonAA;
    private javax.swing.JButton buttonAH;
    private javax.swing.JButton buttonAbonos;
    private javax.swing.JButton buttonAceptarAdArbol;
    private javax.swing.JButton buttonAceptarAdArbol1;
    private javax.swing.JButton buttonAceptarAdHortaliza;
    private javax.swing.JButton buttonAceptarAdHuerta;
    private javax.swing.JButton buttonAceptarAdUsuario;
    private javax.swing.JButton buttonAceptarCatalogo;
    private javax.swing.JButton buttonAceptarCatalogo1;
    private javax.swing.JButton buttonAceptarCatalogo2;
    private javax.swing.JButton buttonAceptarCatalogo3;
    private javax.swing.JButton buttonAdAbonos;
    private javax.swing.JButton buttonAdArboles;
    private javax.swing.JButton buttonAdCatalogos;
    private javax.swing.JButton buttonAdDatos;
    private javax.swing.JButton buttonAdHortalizas;
    private javax.swing.JButton buttonAdHuertas;
    private javax.swing.JButton buttonAdUsuarios;
    private javax.swing.JButton buttonAgUnidadesArboles;
    private javax.swing.JButton buttonAgUnidadesHortalizas;
    private javax.swing.JButton buttonAgregarColor;
    private javax.swing.JButton buttonAgregarColor1;
    private javax.swing.JButton buttonAgregarColor10;
    private javax.swing.JButton buttonAgregarColor11;
    private javax.swing.JButton buttonAgregarColor12;
    private javax.swing.JButton buttonAgregarColor13;
    private javax.swing.JButton buttonAgregarColor14;
    private javax.swing.JButton buttonAgregarColor15;
    private javax.swing.JButton buttonAgregarColor16;
    private javax.swing.JButton buttonAgregarColor17;
    private javax.swing.JButton buttonAgregarColor18;
    private javax.swing.JButton buttonAgregarColor19;
    private javax.swing.JButton buttonAgregarColor2;
    private javax.swing.JButton buttonAgregarColor20;
    private javax.swing.JButton buttonAgregarColor21;
    private javax.swing.JButton buttonAgregarColor22;
    private javax.swing.JButton buttonAgregarColor23;
    private javax.swing.JButton buttonAgregarColor3;
    private javax.swing.JButton buttonAgregarColor4;
    private javax.swing.JButton buttonAgregarColor5;
    private javax.swing.JButton buttonAgregarColor6;
    private javax.swing.JButton buttonAgregarColor7;
    private javax.swing.JButton buttonAgregarColor8;
    private javax.swing.JButton buttonAgregarColor9;
    private javax.swing.JButton buttonArboles;
    private javax.swing.JButton buttonAudGeneral;
    private javax.swing.JButton buttonAudHuertas;
    private javax.swing.JButton buttonAudUsuarios;
    private javax.swing.JButton buttonAuditoria;
    private javax.swing.JButton buttonBitacora;
    private javax.swing.JButton buttonCambiarAdArbol;
    private javax.swing.JButton buttonCambiarAdArbol1;
    private javax.swing.JButton buttonCambiarAdArbol2;
    private javax.swing.JButton buttonCambiarAdHortaliza;
    private javax.swing.JButton buttonCambiarAdHuerta;
    private javax.swing.JButton buttonCambiarAdUsuario;
    private javax.swing.JButton buttonCambiarAdUsuario1;
    private javax.swing.JButton buttonCambiarArbol;
    private javax.swing.JButton buttonCambiarHortaliza;
    private javax.swing.JButton buttonCambiarHuerta;
    private javax.swing.JButton buttonCancelarAdArbol;
    private javax.swing.JButton buttonCancelarAdArbol1;
    private javax.swing.JButton buttonCancelarAdHortaliza;
    private javax.swing.JButton buttonCancelarAdHuerta;
    private javax.swing.JButton buttonCancelarAdUsuario;
    private javax.swing.JButton buttonCancelarCatalogo;
    private javax.swing.JButton buttonCancelarTrueque;
    private javax.swing.JButton buttonCancelarVenta;
    private javax.swing.JButton buttonCerrarSesion;
    private javax.swing.JButton buttonConsultas;
    private javax.swing.JButton buttonCrearAdArbol;
    private javax.swing.JButton buttonCrearAdArbol1;
    private javax.swing.JButton buttonCrearAdArbol2;
    private javax.swing.JButton buttonCrearAdHortaliza;
    private javax.swing.JButton buttonCrearAdHuerta;
    private javax.swing.JButton buttonCrearAdUsuario;
    private javax.swing.JButton buttonCrearArbol;
    private javax.swing.JButton buttonCrearHortaliza;
    private javax.swing.JButton buttonDesactivarColor;
    private javax.swing.JButton buttonDesactivarColor1;
    private javax.swing.JButton buttonDesactivarColor10;
    private javax.swing.JButton buttonDesactivarColor11;
    private javax.swing.JButton buttonDesactivarColor2;
    private javax.swing.JButton buttonDesactivarColor3;
    private javax.swing.JButton buttonDesactivarColor4;
    private javax.swing.JButton buttonDesactivarColor5;
    private javax.swing.JButton buttonDesactivarColor6;
    private javax.swing.JButton buttonDesactivarColor7;
    private javax.swing.JButton buttonDesactivarColor8;
    private javax.swing.JButton buttonDesactivarColor9;
    private javax.swing.JButton buttonEnviarCalificacion;
    private javax.swing.JButton buttonEstadisticas;
    private javax.swing.JButton buttonHA;
    private javax.swing.JButton buttonHH;
    private javax.swing.JButton buttonHortalizas;
    private javax.swing.JButton buttonHuertas;
    private javax.swing.JButton buttonInicio;
    private javax.swing.JButton buttonModificarAdArbol;
    private javax.swing.JButton buttonModificarAdArbol1;
    private javax.swing.JButton buttonModificarAdHortaliza;
    private javax.swing.JButton buttonModificarAdHuerta;
    private javax.swing.JButton buttonModificarAdUsuario;
    private javax.swing.JButton buttonOpciones;
    private javax.swing.JButton buttonRealizarTrueque;
    private javax.swing.JButton buttonRealizarVenta;
    private javax.swing.JButton buttonSalirAdDatos;
    private javax.swing.JButton buttonSalirAdmin;
    private javax.swing.JButton buttonSalirAuditoria1;
    private javax.swing.JButton buttonSalirBitacora;
    private javax.swing.JButton buttonSalirComentarios;
    private javax.swing.JButton buttonSalirConsultas;
    private javax.swing.JButton buttonSalirEstadisticas;
    private javax.swing.JButton buttonTrueques;
    private javax.swing.JButton buttonVentaA;
    private javax.swing.JButton buttonVentaH;
    private javax.swing.JButton buttonVentas;
    private javax.swing.JButton buttonVerAdArbol;
    private javax.swing.JButton buttonVerAdArbol1;
    private javax.swing.JButton buttonVerAdArbol2;
    private javax.swing.JButton buttonVerAdHortaliza;
    private javax.swing.JButton buttonVerAdHuerta;
    private javax.swing.JButton buttonVerAdUsuario;
    private javax.swing.JButton buttonVerAdUsuario1;
    private javax.swing.JButton buttonVerArbol;
    private javax.swing.JButton buttonVerHortaliza;
    private javax.swing.JButton buttonVerHuerta;
    private javax.swing.JButton buttonVerMas;
    private javax.swing.JLabel calificacionUsuario;
    private javax.swing.JComboBox<String> cambiumAdArbol;
    private javax.swing.JComboBox<String> cambiumAdArbol1;
    private javax.swing.JTextField cantidadAdArbol;
    private javax.swing.JTextField cantidadAdHortaliza;
    private javax.swing.JTextField capitalAdHuerta;
    private javax.swing.JComboBox<String> caracteristicaAdHortaliza;
    private javax.swing.JComboBox<String> caracteristicaAdHortaliza1;
    private javax.swing.JDialog catalogos;
    private javax.swing.JTextField cedulaAdUsuario;
    private javax.swing.JComboBox<String> colorAdHortaliza;
    private javax.swing.JComboBox<String> colorAdHortaliza1;
    private javax.swing.JComboBox<String> colorAdministrar;
    private javax.swing.JComboBox<String> colorAdministrar1;
    private javax.swing.JComboBox<String> colorAdministrar10;
    private javax.swing.JComboBox<String> colorAdministrar11;
    private javax.swing.JComboBox<String> colorAdministrar2;
    private javax.swing.JComboBox<String> colorAdministrar3;
    private javax.swing.JComboBox<String> colorAdministrar4;
    private javax.swing.JComboBox<String> colorAdministrar5;
    private javax.swing.JComboBox<String> colorAdministrar6;
    private javax.swing.JComboBox<String> colorAdministrar7;
    private javax.swing.JComboBox<String> colorAdministrar8;
    private javax.swing.JComboBox<String> colorAdministrar9;
    private javax.swing.JTextArea comentarioUsuario;
    private javax.swing.JTextArea comentariosHuerta;
    private javax.swing.JComboBox<String> cortezaAdArbol;
    private javax.swing.JComboBox<String> cortezaAdArbol1;
    private javax.swing.JComboBox<String> dedicacionAdUsuario;
    private javax.swing.JTextArea descripcionCatalogo;
    private javax.swing.JTextArea descripcionCatalogo1;
    private javax.swing.JDialog dialogAdDatos;
    private javax.swing.JDialog dialogAuditoria;
    private javax.swing.JDialog dialogBitacora;
    private javax.swing.JDialog dialogComentarios;
    private javax.swing.JDialog dialogConsultas;
    private javax.swing.JDialog dialogEstadisticas;
    private javax.swing.JDialog dialogFoto;
    private javax.swing.JDialog dialogInvitado;
    private javax.swing.JComboBox<String> distritoAdHuerta;
    private javax.swing.JButton elegirArchivo;
    private javax.swing.JFileChooser elegirFoto;
    private javax.swing.JTextField emailAdUsuario;
    private datechooser.beans.DateChooserCombo fechaAdUsuario;
    private datechooser.beans.DateChooserCombo fechaAdUsuario1;
    private datechooser.beans.DateChooserCombo fechaAdUsuario2;
    private datechooser.beans.DateChooserCombo fechaAdUsuario3;
    private javax.swing.JLabel fotoAdUsuario;
    private javax.swing.JLabel fotoArboles;
    private javax.swing.JLabel fotoHortalizas;
    private javax.swing.ButtonGroup groupHortalizas;
    private javax.swing.JPanel hortalizaInvitado;
    private javax.swing.JComboBox<String> hortalizasAdHortaliza;
    private javax.swing.JLabel hortalizasCantidad;
    private javax.swing.JLabel hortalizasCaracteristica;
    private javax.swing.JLabel hortalizasColor;
    private javax.swing.JComboBox<String> hortalizasH;
    private javax.swing.JComboBox<String> hortalizasHuertas;
    private javax.swing.JList<String> hortalizasList;
    private javax.swing.JLabel hortalizasNombre;
    private javax.swing.JLabel hortalizasPrecio;
    private javax.swing.JLabel hortalizasPropiedad;
    private javax.swing.JLabel hortalizasTipo;
    private javax.swing.JComboBox<String> huertaAdArbol;
    private javax.swing.JComboBox<String> huertaAdHortaliza;
    private javax.swing.JComboBox<String> huertaAdUsuario;
    private javax.swing.JComboBox<String> huertas;
    private javax.swing.JComboBox<String> huertasAdministrar;
    private javax.swing.JLabel huertasNombre;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JLabel labelCalificacion1;
    private javax.swing.JLabel labelCalificacion2;
    private javax.swing.JLabel labelCalificacion3;
    private javax.swing.JLabel labelCalificacion4;
    private javax.swing.JLabel labelCalificacion5;
    private javax.swing.JLabel labelSldCalificacion;
    private javax.swing.JTextField latitudAdHuerta;
    private javax.swing.JLabel logoInicio;
    private javax.swing.JTextField longitudAdHuerta;
    private javax.swing.JComboBox<String> lugarCatalogo;
    private javax.swing.JComboBox<String> nacionalidadAdUsuario;
    private javax.swing.JTextField nombreAdArbol;
    private javax.swing.JTextField nombreAdArbol1;
    private javax.swing.JTextField nombreAdArbol2;
    private javax.swing.JTextField nombreAdHortaliza;
    private javax.swing.JTextField nombreAdHortaliza1;
    private javax.swing.JTextField nombreAdHuerta;
    private javax.swing.JTextField nombreAdUsuario;
    private javax.swing.JTextField nombreCatalogo;
    private javax.swing.JTextField nombreCatalogo1;
    private javax.swing.JLabel ojala1;
    private javax.swing.JLabel ojala2;
    private javax.swing.JLabel ojala3;
    private javax.swing.JLabel ojala4;
    private javax.swing.JDialog opcionesAdministrador;
    private javax.swing.JPanel panelAdAbonos;
    private javax.swing.JPanel panelAdArboles;
    private javax.swing.JPanel panelAdCatalogos;
    private javax.swing.JPanel panelAdDatos;
    private javax.swing.JPanel panelAdHortalizas;
    private javax.swing.JPanel panelAdHuertas;
    private javax.swing.JPanel panelAdUsuarios;
    private javax.swing.JPanel panelArboles;
    private javax.swing.JPanel panelAudGeneral;
    private javax.swing.JPanel panelAudHuertas;
    private javax.swing.JPanel panelAudUsuarios;
    private javax.swing.JPanel panelAuditorias;
    private javax.swing.JPanel panelCrearAbono;
    private javax.swing.JPanel panelFiltro1;
    private javax.swing.JPanel panelFiltro2;
    private javax.swing.JPanel panelFiltro3;
    private javax.swing.JPanel panelFiltrosConsulta;
    private javax.swing.JPanel panelGrafico;
    private javax.swing.JPanel panelHortalizas;
    private javax.swing.JPanel panelHuertas;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelMap;
    private javax.swing.JPanel panelMap1;
    private javax.swing.JPanel panelNombreConsultas;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTrueques;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JPasswordField passwordAdUsuario;
    private javax.swing.JTextField precioAdArbol;
    private javax.swing.JTextField precioAdArbol1;
    private javax.swing.JTextField precioAdHortaliza;
    private javax.swing.JTextField precioAdHortaliza1;
    private javax.swing.JComboBox<String> propiedadAdHortaliza;
    private javax.swing.JComboBox<String> propiedadAdHortaliza1;
    private javax.swing.JComboBox<String> reproduccionAdArbol;
    private javax.swing.JComboBox<String> reproduccionAdArbol1;
    private javax.swing.JSlider sliderCalificacion;
    private javax.swing.JSlider sliderCalificacion1;
    private javax.swing.JTable tableAudGeneral;
    private javax.swing.JTable tableAudHuertas;
    private javax.swing.JTable tableBitacora;
    private javax.swing.JTextField telefonoAdUsuario;
    private javax.swing.JComboBox<String> tipoAdHortaliza;
    private javax.swing.JComboBox<String> tipoAdHortaliza1;
    private javax.swing.JComboBox<String> truequeHuertas;
    private javax.swing.JComboBox<String> truequeP1;
    private javax.swing.JComboBox<String> truequeP2;
    private javax.swing.JTextField usuarioAdUsuario;
    private javax.swing.JComboBox<String> usuariosAdministrar;
    private javax.swing.JComboBox<String> usuariosAdministrar1;
    private javax.swing.JComboBox<String> usuariosAdministrar2;
    private javax.swing.JComboBox<String> ventaPlantas;
    private javax.swing.JComboBox<String> xilemaAdArbol;
    private javax.swing.JComboBox<String> xilemaAdArbol1;
    // End of variables declaration//GEN-END:variables
}
