/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;
import Entidades.*;
import DBManager.*;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author DANIEL
 */
public class Principal extends javax.swing.JFrame {
    private Usuario actual;
    private Insertable buscado;
    private ResultSet consulta;
    private boolean actualizar;
    public static final int LOGINFRAME = 0,
                    FRAMEALMACEN = 1,
                    FRAMECLIENTE = 2,
                    FRAMEENVIO = 3,
                    FRAMEPAQUETE = 4,
                    FRAMEPUNTOCONTROL = 5,
                    FRAMERUTA = 6,
                    FRAMEUSUARIO = 7;
    
    /**
     * Creates new form Principal
     */
    public Principal() {        
        initComponents();
        incorrectValuesLbl.setVisible(false);
        usrShowPassBtn.setIcon(new ImageIcon(((new ImageIcon(getClass().getResource("/Graficos/eye.png"))).getImage().getScaledInstance(usrShowPassBtn.getWidth(), usrShowPassBtn.getHeight(), Image.SCALE_SMOOTH))));
        signOut();
        actualizar = false;
        connectionManager.connect();
        fillCombo();
    }

    public void fillCombo(){
         almacenCodDestinoCombo.removeAllItems(); 
         almacenCodEnvioCombo.removeAllItems();
         almacenCodPCCombo.removeAllItems();
         almacenCodRutaCombo.removeAllItems();
         envioCodPaquete.removeAllItems();
         envioCodRutaCombo.removeAllItems();
         envioCodDestinoCombo.removeAllItems();     
         paquetePaquetesCombo.removeAllItems();
         pcDestinoCombo.removeAllItems();
         pcRutaCombo.removeAllItems();                
         rutaCodDestinoCombo.removeAllItems();
         
         almacenCodDestinoCombo.addItem("Default"); //Destino
         almacenCodEnvioCombo.addItem("Default"); //Envio
         almacenCodPCCombo.addItem("Default"); //PC
         almacenCodRutaCombo.addItem("Default"); //Ruta
         envioCodPaquete.addItem("Default"); //Paquete
         envioCodRutaCombo.addItem("Default"); //Ruta
         envioCodDestinoCombo.addItem("Default");  //Destino
         paquetePaquetesCombo.addItem("Default"); //Paquete
         pcDestinoCombo.addItem("Default"); //Destino
         pcRutaCombo.addItem("Default"); //Ruta
         rutaCodDestinoCombo.addItem("Default"); //Destino
        
        try {
        buscado = new Destino(0, "", 0.00);
        consulta = connectionManager.select(buscado,new String[] {"Direccion"}, null);
            while (consulta.next()){
                pcDestinoCombo.addItem(consulta.getString("Direccion"));
                almacenCodDestinoCombo.addItem(consulta.getString("Direccion"));
                envioCodDestinoCombo.addItem(consulta.getString("Direccion"));
                rutaCodDestinoCombo.addItem(consulta.getString("Direccion"));
            }
        buscado = new Envio(0,0,0,0);
        consulta = connectionManager.select(buscado,new String[] {"Codigo"}, null);
            while (consulta.next()){
                almacenCodEnvioCombo.addItem((consulta.getInt("Codigo")));
               }
         buscado = new PuntoControl(0,0,0,0,0,"");
        consulta = connectionManager.select(buscado,new String[] {"Codigo"}, null);
            while (consulta.next()){
                almacenCodPCCombo.addItem((consulta.getInt("Codigo")));
               }
            buscado = new Ruta(0,0);
        consulta = connectionManager.select(buscado,new String[] {"Codigo"}, null);
            while (consulta.next()){
                almacenCodRutaCombo.addItem((consulta.getInt("Codigo")));
                envioCodRutaCombo.addItem((consulta.getInt("Codigo")));
                pcRutaCombo.addItem((consulta.getInt("Codigo")));
               }
            buscado = new Paquete(0,0,"",0);
            consulta = connectionManager.select(buscado,new String[] {"Codigo"}, null);
            while (consulta.next()){
                envioCodPaquete.addItem((consulta.getInt("Codigo")));
                paquetePaquetesCombo.addItem((consulta.getInt("Codigo")));
               }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] fields(String fields){
        return fields.split(",");
    }
    
    public void clear(boolean all, int frame){
        switch (frame) {
            case LOGINFRAME:
                //        logInFrame = 0
                logInUsrTxt.setText("");
                LogInPassTxt.setText("");
                incorrectValuesLbl.setVisible(false);
                if (!all) break;
            case FRAMEALMACEN:
                //        frameAlmacen = 1
                almacenCodDestinoCombo.setSelectedIndex(0);
                almacenCodEnvioCombo.setSelectedIndex(0);
                almacenCodPCCombo.setSelectedIndex(0);
                almacenCodRutaCombo.setSelectedIndex(0);
                
                almacenCostoTxt.setText("");
                almacenTarifaTxt.setText("");
                almacenTiempoTxt.setText("");
                if (!all) break;
            case FRAMECLIENTE:
                //        frameCliente = 2
                clientNameTxt.setText("");
                clientNitTxt.setText("");
                if (!all) break;
            case FRAMEENVIO:
                //        frameEnvio = 3
                envioCodigoTxt.setText("");
                envioRecibidoCheck.setSelected(false);
                envioCodDestinoCombo.setSelectedIndex(0);
                envioCodRutaCombo.setSelectedIndex(0);
                envioCodPaquete.setSelectedIndex(0);
                if (!all) break;
            case FRAMEPAQUETE:
                //        framePaquete = 4
                paqueteNitTxt.setText("");
                paquetePesoTxt.setText("0");
                paquetePriorizadoCheck.setSelected(false);
                paquetePaquetesCombo.setSelectedIndex(0);
                if (!all) break;
            case FRAMEPUNTOCONTROL:
                //        framePuntoControl = 5
                pcCodigoTxt.setText("");
                pcDestinoCombo.setSelectedIndex(0);
                pcDireccionTxt.setText("");
                pcRutaCombo.setSelectedIndex(0);
                pcTarifaDeOperacionTxt.setText("0");
                if (!all) break;
            case FRAMERUTA:
                //        frameRuta = 6
                rutaCodDestinoCombo.setSelectedIndex(0);
                rutaCodigoTxt.setText("");
                rutaEstadoCheck.setSelected(false);
                destinoCodigoTxt.setText("");
                destinoCuotaTxt.setText("0");
                destinoDireccionTxt.setText("");
                if (!all) break;
            case FRAMEUSUARIO:
                //        frameUsuario = 7
                usrNameTxt.setText("");
                usrPassTxt.setText("");
                usrTypeCombo.setSelectedIndex(0);
                usrUsrTxt.setText("");
                if (!all) break;
            default:
//                if (all) throw new AssertionError();
                break;
        }

    }
    
    public void signOut(){
        logInFrame.setVisible(true);
        menuAdministradorFile.setVisible(false);
        menuOperadoresFile.setVisible(false);
        menuRecepcionistaFile.setVisible(false);
        menuAdministradorFile.setEnabled(false);
        menuOperadoresFile.setEnabled(false);
        menuRecepcionistaFile.setEnabled(false);
        frameCliente.doDefaultCloseAction();
        frameUsuario.doDefaultCloseAction();
        framePaquete.doDefaultCloseAction();
        frameAlmacen.doDefaultCloseAction();
        frameEnvio.doDefaultCloseAction();
        framePuntoControl.doDefaultCloseAction();
        frameRuta.doDefaultCloseAction();
        clear(true, 0);
        actual = null;
    }
    
    public void save(Insertable nuevo, String[] fields, String[] conditions,int frame){
        try {   
//            if (!connectionManager.select(table, table, table+" = '"+usr+"'").isBeforeFirst()) {
                if (!actualizar && connectionManager.insert(nuevo)){
                    JOptionPane.showMessageDialog(almacenTiempoTxt, nuevo.primaryKey() + " Correctamente Ingresado");
                    clear(false, frame);
               // consulta = connectionManager.select(nuevo, new String[] {"*"}, null);
            } else if (actualizar && connectionManager.update(nuevo, fields, conditions)){
                JOptionPane.showMessageDialog(frameUsuario, nuevo.primaryKey() + " Correctamente actualizado");
                actualizar = false;
                    clear(false, frame);
            }else {
                JOptionPane.showMessageDialog(rootPane, nuevo.getClass().getSimpleName() + " Ya existente");
            }
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Insertable recuperar(ResultSet recuperado, String table){
         Insertable buscado;
        try {
            switch (table.toLowerCase()){
             case "almacen":
                    buscado = new Almacen(recuperado.getInt("CodEnvio"),
                    recuperado.getInt("CodPc"),
                            recuperado.getInt("CodRuta"),
                            recuperado.getInt("CodDestino"),
                            recuperado.getInt("Tiempo"),
                            recuperado.getDouble("TarifaA"),
                            recuperado.getDouble("Costo")
                    );
                    break;
             case "cliente":
                 buscado = new Cliente(recuperado.getString("Nombre")
                        , consulta.getString("Nit"));
                 break;
             case "destino":
                    buscado = new Destino(recuperado.getInt("Codigo"),
                    recuperado.getString("Direccion"),
                            recuperado.getDouble("Cuota")
                    );
                    break;
             case "envio":
                    buscado = new Envio(recuperado.getInt("Codigo"),
                            recuperado.getInt("CodPaquete"),
                            recuperado.getInt("CodRuta"),
                            recuperado.getInt("CodDestino"),
                            recuperado.getDouble("CostoT"),
                            recuperado.getInt("TiempoT"),
                            recuperado.getInt("Recibido")
                    );
                    break;
             case "paquete":
                    buscado = new Paquete(recuperado.getInt("Codigo"),
                    recuperado.getDouble("Peso"),
                            recuperado.getString("NIT"),
                            recuperado.getInt("Priorizado")
                    );
                    break;
             case "puntocontrol":
                    buscado = new PuntoControl(recuperado.getInt("Codigo"),
                    recuperado.getInt("CodRuta"),
                            recuperado.getInt("CodDestino"),
                            recuperado.getDouble("Tarifa"),
                            recuperado.getDouble("TarifaG"),
                            recuperado.getInt("Limite"),
                            recuperado.getString("Ubicacion")
                    );
                    break;
             case "ruta":
                    buscado = new Ruta(recuperado.getInt("Codigo"),
                    recuperado.getInt("CodDestino"),
                            recuperado.getInt("Estado")
                    );
                    break;
             case "usuario":
                    buscado = new Usuario(recuperado.getString("Nombre"),
                    recuperado.getString("Usuario"),
                            recuperado.getString("Password"),
                            recuperado.getInt("Tipo")
                    );
                    break;
             default:
                 buscado = null;
            }
            return buscado;
        } catch (Exception e) {
        }
        return null;
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
        jDesktopPane1 = new javax.swing.JDesktopPane();
        logInFrame = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logInUsrTxt = new javax.swing.JTextField();
        LogInPassTxt = new javax.swing.JPasswordField();
        logInBtn = new javax.swing.JButton();
        incorrectValuesLbl = new javax.swing.JLabel();
        frameCliente = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        clientNameTxt = new javax.swing.JTextField();
        clientNitTxt = new javax.swing.JTextField();
        clientSearchBtn = new javax.swing.JButton();
        clientSaveBtn = new javax.swing.JButton();
        frameUsuario = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        usrPassTxt = new javax.swing.JPasswordField();
        usrShowPassBtn = new javax.swing.JToggleButton();
        usrUsrTxt = new javax.swing.JTextField();
        usrNameTxt = new javax.swing.JTextField();
        usrTypeCombo = new javax.swing.JComboBox<>();
        usrSaveBtn = new javax.swing.JButton();
        usrSearchBtn = new javax.swing.JButton();
        framePaquete = new javax.swing.JInternalFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        paqueteNitTxt = new javax.swing.JTextField();
        paquetePesoTxt = new javax.swing.JFormattedTextField();
        paquetePriorizadoCheck = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        paqueteSaveBtn = new javax.swing.JButton();
        paquetePaquetesCombo = new javax.swing.JComboBox<>();
        frameRuta = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        rutaCodDestinoCombo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        rutaCodigoTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rutaEstadoCheck = new javax.swing.JCheckBox();
        rutaSaveBtn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        destinoPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        destinoCodigoTxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        destinoDireccionTxt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        destinoCuotaTxt = new javax.swing.JFormattedTextField();
        destinoSaveBtn = new javax.swing.JButton();
        framePuntoControl = new javax.swing.JInternalFrame();
        jLabel21 = new javax.swing.JLabel();
        pcDestinoCombo = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        pcRutaCombo = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        pcCodigoTxt = new javax.swing.JTextField();
        pcDireccionTxt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        pcLimiteSpinner = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        pcTarifaDeOperacionTxt = new javax.swing.JFormattedTextField();
        pcSaveBtn = new javax.swing.JButton();
        frameEnvio = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        envioCodigoTxt = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        envioCodDestinoCombo = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        envioCodRutaCombo = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        envioCodPaquete = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        envioRecibidoCheck = new javax.swing.JCheckBox();
        envioGuardarBtn = new javax.swing.JButton();
        envioBuscarBtn = new javax.swing.JButton();
        frameAlmacen = new javax.swing.JInternalFrame();
        jPanel7 = new javax.swing.JPanel();
        almacenCodEnvioCombo = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        almacenCodDestinoCombo = new javax.swing.JComboBox<>();
        almacenCodRutaCombo = new javax.swing.JComboBox<>();
        almacenCodPCCombo = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        almacenTarifaTxt = new javax.swing.JTextField();
        almacenTiempoTxt = new javax.swing.JFormattedTextField();
        jLabel35 = new javax.swing.JLabel();
        almacenCostoTxt = new javax.swing.JTextField();
        almacenGuardarBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAdministradorFile = new javax.swing.JMenu();
        administradorMenuModificar = new javax.swing.JMenu();
        administradorMenuUsuario = new javax.swing.JMenuItem();
        administradorMenuRuta = new javax.swing.JMenuItem();
        administradorMenuPC = new javax.swing.JMenuItem();
        menuOperadoresFile = new javax.swing.JMenu();
        operadorMenuModificar = new javax.swing.JMenu();
        operadorMenuAlmacen = new javax.swing.JMenuItem();
        menuRecepcionistaFile = new javax.swing.JMenu();
        recepcionistaMenModificar = new javax.swing.JMenu();
        recepcionistaMenuCliente = new javax.swing.JMenuItem();
        recepcionistaMenuEnvio = new javax.swing.JMenuItem();
        sesionMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jDesktopPane1.setMaximumSize(getMaximumSize());

        logInFrame.setClosable(true);
        logInFrame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        logInFrame.setIconifiable(true);
        logInFrame.setTitle("Log In");
        logInFrame.setPreferredSize(new java.awt.Dimension(215, 310));
        logInFrame.setVisible(true);

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        logInBtn.setText("Ingresar");
        logInBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInBtnActionPerformed(evt);
            }
        });

        incorrectValuesLbl.setForeground(new java.awt.Color(204, 0, 0));
        incorrectValuesLbl.setText("Usuario/Contraseña Incorrecto");

        javax.swing.GroupLayout logInFrameLayout = new javax.swing.GroupLayout(logInFrame.getContentPane());
        logInFrame.getContentPane().setLayout(logInFrameLayout);
        logInFrameLayout.setHorizontalGroup(
            logInFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logInFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(logInFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LogInPassTxt)
                    .addComponent(logInUsrTxt))
                .addContainerGap())
            .addGroup(logInFrameLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(logInBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logInFrameLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(incorrectValuesLbl)
                .addGap(22, 22, 22))
        );
        logInFrameLayout.setVerticalGroup(
            logInFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInFrameLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(incorrectValuesLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(logInFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(logInUsrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(logInFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LogInPassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logInBtn)
                .addContainerGap())
        );

        frameCliente.setClosable(true);
        frameCliente.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frameCliente.setIconifiable(true);
        frameCliente.setTitle("Clientes");
        frameCliente.setMaximumSize(new java.awt.Dimension(425, 235));
        frameCliente.setMinimumSize(new java.awt.Dimension(425, 235));
        frameCliente.setPreferredSize(new java.awt.Dimension(425, 235));
        frameCliente.setVisible(true);

        jLabel3.setText("Nombre");

        jLabel4.setText("NIT");

        clientSearchBtn.setText("Buscar");

        clientSaveBtn.setText("Guardar");
        clientSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientSaveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clientNameTxt)
                    .addComponent(clientNitTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(clientSearchBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clientSaveBtn)
                .addGap(142, 142, 142))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(clientNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(clientNitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientSearchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clientSaveBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout frameClienteLayout = new javax.swing.GroupLayout(frameCliente.getContentPane());
        frameCliente.getContentPane().setLayout(frameClienteLayout);
        frameClienteLayout.setHorizontalGroup(
            frameClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameClienteLayout.setVerticalGroup(
            frameClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameUsuario.setClosable(true);
        frameUsuario.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frameUsuario.setIconifiable(true);
        frameUsuario.setTitle("Usuario");
        frameUsuario.setMaximumSize(new java.awt.Dimension(400, 300));
        frameUsuario.setMinimumSize(new java.awt.Dimension(400, 300));
        frameUsuario.setPreferredSize(new java.awt.Dimension(400, 300));
        frameUsuario.setVisible(true);

        jLabel5.setText("Usuario");

        jLabel6.setText("Nombre");

        jLabel7.setText("Contraseña");

        jLabel8.setText("Tipo");

        usrShowPassBtn.setPreferredSize(new java.awt.Dimension(20, 20));
        usrShowPassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usrShowPassBtnActionPerformed(evt);
            }
        });

        usrTypeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Operador", "Recepcionista" }));

        usrSaveBtn.setText("Guardar");
        usrSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usrSaveBtnActionPerformed(evt);
            }
        });

        usrSearchBtn.setText("Buscar");
        usrSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usrSearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usrSaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usrPassTxt)
                            .addComponent(usrUsrTxt)
                            .addComponent(usrNameTxt)
                            .addComponent(usrTypeCombo, 0, 130, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usrShowPassBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usrSearchBtn))
                .addGap(67, 67, 67))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(usrUsrTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usrSearchBtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(usrNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(usrPassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(usrShowPassBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(usrTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(usrSaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameUsuarioLayout = new javax.swing.GroupLayout(frameUsuario.getContentPane());
        frameUsuario.getContentPane().setLayout(frameUsuarioLayout);
        frameUsuarioLayout.setHorizontalGroup(
            frameUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameUsuarioLayout.setVerticalGroup(
            frameUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        framePaquete.setClosable(true);
        framePaquete.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        framePaquete.setIconifiable(true);
        framePaquete.setTitle("Paquetes");
        framePaquete.setToolTipText("");
        framePaquete.setMaximumSize(new java.awt.Dimension(355, 280));
        framePaquete.setMinimumSize(new java.awt.Dimension(355, 280));
        framePaquete.setPreferredSize(new java.awt.Dimension(355, 280));
        framePaquete.setVisible(true);

        jLabel9.setText("Nit");

        jLabel10.setText("Peso");

        jLabel11.setText("Priorización");

        paquetePesoTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        jLabel12.setText("Codigo");

        paqueteSaveBtn.setText("Guardar");
        paqueteSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paqueteSaveBtnActionPerformed(evt);
            }
        });

        paquetePaquetesCombo.setEditable(true);
        paquetePaquetesCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(paqueteNitTxt)
                            .addComponent(paquetePesoTxt)
                            .addComponent(paquetePaquetesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paqueteSaveBtn)
                            .addComponent(paquetePriorizadoCheck))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(paquetePaquetesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(paqueteNitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(paquetePesoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(paquetePriorizadoCheck))
                .addGap(34, 34, 34)
                .addComponent(paqueteSaveBtn)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout framePaqueteLayout = new javax.swing.GroupLayout(framePaquete.getContentPane());
        framePaquete.getContentPane().setLayout(framePaqueteLayout);
        framePaqueteLayout.setHorizontalGroup(
            framePaqueteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(framePaqueteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        framePaqueteLayout.setVerticalGroup(
            framePaqueteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(framePaqueteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameRuta.setClosable(true);
        frameRuta.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frameRuta.setIconifiable(true);
        frameRuta.setTitle("Destino & Ruta");
        frameRuta.setPreferredSize(new java.awt.Dimension(255, 425));
        frameRuta.setVisible(true);

        jPanel5.setPreferredSize(new java.awt.Dimension(210, 310));

        jLabel13.setText("Codgio Destino");

        rutaCodDestinoCombo.setEditable(true);
        rutaCodDestinoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item1", " " }));

        jLabel14.setText("Codigo");

        jLabel15.setText("Activa");

        rutaSaveBtn.setText("Guardar");
        rutaSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rutaSaveBtnActionPerformed(evt);
            }
        });

        jLabel16.setText("Ruta");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rutaCodDestinoCombo, 0, 79, Short.MAX_VALUE)
                            .addComponent(rutaCodigoTxt))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rutaSaveBtn)
                            .addComponent(rutaEstadoCheck))
                        .addGap(65, 65, 65))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel16)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(rutaCodDestinoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(rutaCodigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rutaEstadoCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(rutaSaveBtn)
                .addContainerGap())
        );

        destinoPanel.setPreferredSize(new java.awt.Dimension(210, 310));

        jLabel17.setText("Destino");

        jLabel18.setText("Codigo");

        jLabel19.setText("Direccion");

        jLabel20.setText("Cuota");

        destinoCuotaTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        destinoSaveBtn.setText("Guardar");
        destinoSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinoSaveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout destinoPanelLayout = new javax.swing.GroupLayout(destinoPanel);
        destinoPanel.setLayout(destinoPanelLayout);
        destinoPanelLayout.setHorizontalGroup(
            destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destinoPanelLayout.createSequentialGroup()
                .addGroup(destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(destinoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(destinoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(44, 44, 44)
                                .addGroup(destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addGroup(destinoPanelLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(destinoDireccionTxt)
                                            .addComponent(destinoCodigoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))))
                            .addComponent(jLabel19)
                            .addGroup(destinoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(destinoCuotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(destinoPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(destinoSaveBtn)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        destinoPanelLayout.setVerticalGroup(
            destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(destinoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(destinoCodigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(destinoDireccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(destinoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(destinoCuotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(destinoSaveBtn)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameRutaLayout = new javax.swing.GroupLayout(frameRuta.getContentPane());
        frameRuta.getContentPane().setLayout(frameRutaLayout);
        frameRutaLayout.setHorizontalGroup(
            frameRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRutaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destinoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        frameRutaLayout.setVerticalGroup(
            frameRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameRutaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destinoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );

        framePuntoControl.setClosable(true);
        framePuntoControl.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        framePuntoControl.setIconifiable(true);
        framePuntoControl.setTitle("Punto de Control");
        framePuntoControl.setVisible(true);

        jLabel21.setText("Destino");

        pcDestinoCombo.setEditable(true);
        pcDestinoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Ruta");

        pcRutaCombo.setEditable(true);
        pcRutaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setText("Codigo");

        jLabel24.setText("Direccion");

        jLabel25.setText("Limite");

        jLabel26.setText("Tarifa De Operacion");

        pcSaveBtn.setText("Guardar");
        pcSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pcSaveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout framePuntoControlLayout = new javax.swing.GroupLayout(framePuntoControl.getContentPane());
        framePuntoControl.getContentPane().setLayout(framePuntoControlLayout);
        framePuntoControlLayout.setHorizontalGroup(
            framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(framePuntoControlLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(framePuntoControlLayout.createSequentialGroup()
                        .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(framePuntoControlLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pcLimiteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(framePuntoControlLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(pcDestinoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(framePuntoControlLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(pcCodigoTxt)))
                        .addGap(32, 32, 32)
                        .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pcRutaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pcDireccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(framePuntoControlLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pcSaveBtn)
                            .addComponent(pcTarifaDeOperacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        framePuntoControlLayout.setVerticalGroup(
            framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(framePuntoControlLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(pcDestinoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(pcRutaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(pcCodigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pcDireccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(pcLimiteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(framePuntoControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(pcTarifaDeOperacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(pcSaveBtn)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        frameEnvio.setClosable(true);
        frameEnvio.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frameEnvio.setIconifiable(true);
        frameEnvio.setTitle("Envio");
        frameEnvio.setVisible(true);

        jLabel27.setText("Codigo");

        jLabel28.setText("Codigo Destino");

        envioCodDestinoCombo.setEditable(true);
        envioCodDestinoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel29.setText("Codigo Ruta");

        envioCodRutaCombo.setEditable(true);
        envioCodRutaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel30.setText("Codigo Paquete");

        envioCodPaquete.setEditable(true);
        envioCodPaquete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel31.setText("Recibido");

        envioGuardarBtn.setText("Guardar");
        envioGuardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envioGuardarBtnActionPerformed(evt);
            }
        });

        envioBuscarBtn.setText("Buscar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(envioCodPaquete, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(60, 60, 60)
                        .addComponent(envioCodigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(envioCodDestinoCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(envioCodRutaCombo, 0, 107, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(envioBuscarBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(envioGuardarBtn)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(envioRecibidoCheck)))
                .addGap(165, 165, 165))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(envioCodigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(envioBuscarBtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(envioCodDestinoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(envioCodRutaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(envioCodPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(envioRecibidoCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(envioGuardarBtn)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout frameEnvioLayout = new javax.swing.GroupLayout(frameEnvio.getContentPane());
        frameEnvio.getContentPane().setLayout(frameEnvioLayout);
        frameEnvioLayout.setHorizontalGroup(
            frameEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameEnvioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameEnvioLayout.setVerticalGroup(
            frameEnvioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameEnvioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        frameAlmacen.setClosable(true);
        frameAlmacen.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frameAlmacen.setIconifiable(true);
        frameAlmacen.setTitle("Almacen");
        frameAlmacen.setVisible(true);

        almacenCodEnvioCombo.setEditable(true);
        almacenCodEnvioCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel32.setText("Codigo de Envio");

        jLabel33.setText("Punto de Control Actual");

        almacenCodDestinoCombo.setEditable(true);
        almacenCodDestinoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        almacenCodRutaCombo.setEditable(true);
        almacenCodRutaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        almacenCodPCCombo.setEditable(true);
        almacenCodPCCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel34.setText("Tiempo");

        almacenTarifaTxt.setText("Tarifa");

        almacenTiempoTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("+#,##0"))));

        jLabel35.setText("Costo");

        almacenGuardarBtn.setText("Guardar");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(30, 30, 30)
                        .addComponent(almacenCodEnvioCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(18, 18, 18)
                                .addComponent(almacenCodDestinoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(almacenTiempoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(almacenCostoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(almacenCodRutaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(almacenCodPCCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(almacenTarifaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(almacenGuardarBtn))))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(almacenCodEnvioCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(almacenCodDestinoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(almacenCodRutaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(almacenCodPCCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(almacenTarifaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(almacenTiempoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(almacenCostoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(almacenGuardarBtn)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout frameAlmacenLayout = new javax.swing.GroupLayout(frameAlmacen.getContentPane());
        frameAlmacen.getContentPane().setLayout(frameAlmacenLayout);
        frameAlmacenLayout.setHorizontalGroup(
            frameAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameAlmacenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        frameAlmacenLayout.setVerticalGroup(
            frameAlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameAlmacenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDesktopPane1.setLayer(logInFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(frameCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(frameUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(framePaquete, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(frameRuta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(framePuntoControl, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(frameEnvio, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(frameAlmacen, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(framePaquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(logInFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(frameUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(framePuntoControl)
                            .addComponent(frameCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 387, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(frameRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(frameEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(frameAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(framePaquete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logInFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                    .addComponent(frameUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(frameRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(frameEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)
                                .addComponent(frameAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(frameCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(framePuntoControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuAdministradorFile.setText("Archivo");

        administradorMenuModificar.setText("Modificar");

        administradorMenuUsuario.setText("Usuario");
        administradorMenuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administradorMenuUsuarioActionPerformed(evt);
            }
        });
        administradorMenuModificar.add(administradorMenuUsuario);

        administradorMenuRuta.setText("Ruta");
        administradorMenuRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administradorMenuRutaActionPerformed(evt);
            }
        });
        administradorMenuModificar.add(administradorMenuRuta);

        administradorMenuPC.setText("Punto De Control");
        administradorMenuPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administradorMenuPCActionPerformed(evt);
            }
        });
        administradorMenuModificar.add(administradorMenuPC);

        menuAdministradorFile.add(administradorMenuModificar);

        jMenuBar1.add(menuAdministradorFile);

        menuOperadoresFile.setText("Archivo");

        operadorMenuModificar.setText("Modificar");

        operadorMenuAlmacen.setText("Almacen");
        operadorMenuAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operadorMenuAlmacenActionPerformed(evt);
            }
        });
        operadorMenuModificar.add(operadorMenuAlmacen);

        menuOperadoresFile.add(operadorMenuModificar);

        jMenuBar1.add(menuOperadoresFile);

        menuRecepcionistaFile.setText("Archivo");

        recepcionistaMenModificar.setText("Modificar");

        recepcionistaMenuCliente.setText("Cliente");
        recepcionistaMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recepcionistaMenuClienteActionPerformed(evt);
            }
        });
        recepcionistaMenModificar.add(recepcionistaMenuCliente);

        recepcionistaMenuEnvio.setText("Envio");
        recepcionistaMenuEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recepcionistaMenuEnvioActionPerformed(evt);
            }
        });
        recepcionistaMenModificar.add(recepcionistaMenuEnvio);

        menuRecepcionistaFile.add(recepcionistaMenModificar);

        jMenuBar1.add(menuRecepcionistaFile);

        sesionMenu.setText("Sesion");

        jMenuItem1.setText("Iniciar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        sesionMenu.add(jMenuItem1);

        jMenuItem2.setText("Cerrar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        sesionMenu.add(jMenuItem2);

        jMenuBar1.add(sesionMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (!logInFrame.isVisible() && actual == null) {
            clear(false, 0);
            logInFrame.setVisible(true);
        }
        System.out.println(logInFrame.isVisible());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        signOut();
        actual = null;
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void logInBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInBtnActionPerformed
//    frameCliente.setVisible(true);
//    frameUsuario.setVisible(true);
//    connectionManager.check();
        String uName = "";
        String uPass;
        Usuario iniciando;
        incorrectValuesLbl.setVisible(false);
        try {
            uName = logInUsrTxt.getText();
            uPass = String.copyValueOf(LogInPassTxt.getPassword());
            iniciando = new Usuario("", uName, uPass, 0);
//            consulta = connectionManager.select("Usuario", "*", "Usuario = '" +uName+ "' AND Password = '" + uPass + "'");
            consulta = connectionManager.select(iniciando, new String[] {"*"}, new String[] {"usuario", "password"});
                consulta.first();
             if (!consulta.wasNull()) {
//                actual = new Usuario(consulta.getString("Nombre")
//                        , consulta.getString("Usuario")
//                        , consulta.getString("Password"),
//                        consulta.getInt("Tipo"));
                actual = (Usuario) recuperar(consulta, Usuario.class.getSimpleName());
            } else {
                incorrectValuesLbl.setVisible(true);
            }
            System.out.println(actual.getNombre());
        } catch (Exception e) {
           incorrectValuesLbl.setVisible(true);
            //e.printStackTrace();
        }
        if (actual != null) {
            logInFrame.doDefaultCloseAction();
            switch (actual.getTipo()) {
                case 1:
                    menuAdministradorFile.setVisible(true);
                    menuAdministradorFile.setEnabled(true);
                    break;
                case 2:
                    menuOperadoresFile.setVisible(true);
                    menuOperadoresFile.setEnabled(true);
                    break;
                case 3:
                       menuRecepcionistaFile.setVisible(true);
                       menuRecepcionistaFile.setEnabled(true);
                       break;
                default:
                    throw new AssertionError();
            }
        }
        

//    connectionManager.select("Usuario", "Usuario", "Password = 'test'");
    }//GEN-LAST:event_logInBtnActionPerformed

    private void clientSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientSaveBtnActionPerformed
        Cliente nuevo;
        String nit = clientNitTxt.getText(),
                nombre = clientNameTxt.getText();
        nuevo = new Cliente(nombre, nit);
        save(nuevo, null, null, FRAMECLIENTE);
//        try {
////            if (!connectionManager.select(table, table, table+" = '"+usr+"'").isBeforeFirst()) {
//                if (connectionManager.insert(nuevo)){
//                    JOptionPane.showMessageDialog(almacenTiempoTxt, nuevo.getNit() + " Correctamente Ingresado");
//                
//            } else {
//                JOptionPane.showMessageDialog(rootPane, "NIT ya ingresado");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }//GEN-LAST:event_clientSaveBtnActionPerformed

    private void usrShowPassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usrShowPassBtnActionPerformed
        
        if (usrShowPassBtn.isSelected()) {
            usrPassTxt.setEchoChar((char)0);
        } else {
            usrPassTxt.setEchoChar('*');
        }
    }//GEN-LAST:event_usrShowPassBtnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(this.MAXIMIZED_BOTH); 
    }//GEN-LAST:event_formWindowOpened

    private void administradorMenuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administradorMenuUsuarioActionPerformed
        frameUsuario.setVisible(true);
    }//GEN-LAST:event_administradorMenuUsuarioActionPerformed

    private void usrSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usrSaveBtnActionPerformed
         String //table = Usuario.class.getSimpleName(),
                    usr = usrUsrTxt.getText(),
                    pass = String.valueOf(usrPassTxt.getPassword()),
                    name = usrNameTxt.getText();
            int type = usrTypeCombo.getSelectedIndex()+1;
            Usuario nuevo = new Usuario(name, usr, pass, type);
            save(nuevo, null, null, FRAMEUSUARIO);
//        try {   
////            if (!connectionManager.select(table, table, table+" = '"+usr+"'").isBeforeFirst()) {
//                if (actualizar == false && connectionManager.insert(nuevo)){
//                    JOptionPane.showMessageDialog(almacenTiempoTxt, nuevo.getUsuario() + " Correctamente Ingresado");
//                    clear(false, 7);
//               // consulta = connectionManager.select(nuevo, new String[] {"*"}, null);
//            } else if (actualizar == true && connectionManager.update(nuevo, new String[] {"nombre", "usuario", "password", "tipo"}, new String[] {"usuario"})){
//                JOptionPane.showMessageDialog(frameUsuario, nuevo.getUsuario() + " Correctamente actualizado");
//                actualizar = false;
//                    clear(false, 7);
//            }else {
//                JOptionPane.showMessageDialog(rootPane, "Nombre de usuario no disponible");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_usrSaveBtnActionPerformed

    private void administradorMenuRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administradorMenuRutaActionPerformed
        frameRuta.setVisible(true);
    }//GEN-LAST:event_administradorMenuRutaActionPerformed

    private void administradorMenuPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administradorMenuPCActionPerformed
        framePuntoControl.setVisible(true);
    }//GEN-LAST:event_administradorMenuPCActionPerformed

    private void operadorMenuAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operadorMenuAlmacenActionPerformed
        frameAlmacen.setVisible(true);
    }//GEN-LAST:event_operadorMenuAlmacenActionPerformed

    private void recepcionistaMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recepcionistaMenuClienteActionPerformed
        frameCliente.setVisible(true);
    }//GEN-LAST:event_recepcionistaMenuClienteActionPerformed

    private void recepcionistaMenuEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recepcionistaMenuEnvioActionPerformed
        framePaquete.setVisible(true);
    }//GEN-LAST:event_recepcionistaMenuEnvioActionPerformed

    private void usrSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usrSearchBtnActionPerformed
       String usrName = "";
        clear(false, 7);
        if (usrName.equals("")) usrName = JOptionPane.showInputDialog("Porfavor ingrese el Nombre de Usuario a buscar");
        buscado = new Usuario("", usrName, "", 0);
        consulta = connectionManager.select(buscado, new String[] {"*"}, new String[] {"usuario"});
         try {    
            consulta.first();
            if (!consulta.wasNull()) {
                  buscado = recuperar(consulta, "Usuario");
//                buscado = new Usuario(consulta.getString("Nombre")
//                       , consulta.getString("Usuario")
//                       , consulta.getString("Password"),
//                       consulta.getInt("Tipo"));
                
                usrNameTxt.setText(((Usuario) buscado).getNombre());
                usrPassTxt.setText(((Usuario) buscado).getPassword());
                usrUsrTxt.setText(((Usuario) buscado).getUsuario());
                usrTypeCombo.setSelectedIndex(((Usuario) buscado).getTipo() - 1);
                actualizar = true;
            }
           } catch (Exception ex) {
              // Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(frameUsuario, usrName + " Inexistente");
               actualizar = false;
           }
           
    }//GEN-LAST:event_usrSearchBtnActionPerformed

    private void destinoSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinoSaveBtnActionPerformed
        Destino nuevo;
        String ubicacion = destinoDireccionTxt.getText();
            int codigo = Integer.parseInt(destinoCodigoTxt.getText());
            double cuota = Double.parseDouble(destinoCuotaTxt.getText());
            nuevo = new Destino(codigo, ubicacion, cuota);
            save(nuevo, null, null, FRAMERUTA);
//        try {
//            
//            if (!actualizar && connectionManager.insert(nuevo)) {
//                JOptionPane.showMessageDialog(frameRuta, nuevo.getDireccion() + " Correctamente ingresado");
//                clear(false,6);
//            } //else if (actualizar && connectionManager.update(nuevo, fields, conditions))
//                  
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_destinoSaveBtnActionPerformed

    private void paqueteSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paqueteSaveBtnActionPerformed
        Paquete nuevo;
        int codigo = Integer.parseInt((String) paquetePaquetesCombo.getSelectedItem());
        double peso = Double.parseDouble(paquetePesoTxt.getText());
        String nit = paqueteNitTxt.getText();
        boolean priorizado = paquetePriorizadoCheck.isSelected();
        nuevo = new Paquete(codigo, peso, nit, priorizado);
        save(nuevo, null, null, FRAMEENVIO);
//        try {
//            if (!actualizar && connectionManager.insert(nuevo)) {
//                JOptionPane.showMessageDialog(framePaquete, nuevo.getCodigo() + " correctamente ingresado");
//            } else {
//                JOptionPane.showMessageDialog(rootPane, "Paquete ya ingresado");
// 
//            }
//        } catch (Exception e) {
//        }
        
    }//GEN-LAST:event_paqueteSaveBtnActionPerformed

    private void pcSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pcSaveBtnActionPerformed
        PuntoControl nuevo;
        int codigo = Integer.parseInt(pcCodigoTxt.getText()),
                codRuta = Integer.parseInt(String.valueOf(pcRutaCombo.getSelectedItem())),
                codDestino = Integer.parseInt(String.valueOf(pcDestinoCombo.getSelectedItem())),
                limite = Integer.parseInt(String.valueOf(pcLimiteSpinner.getValue()));
        double tarifa = Double.parseDouble(pcTarifaDeOperacionTxt.getText());
        String direccion = pcDireccionTxt.getText();
        
        nuevo = new PuntoControl(codigo, codRuta, codDestino, tarifa, limite, direccion);
        save(nuevo, null, null, FRAMEPUNTOCONTROL);
    }//GEN-LAST:event_pcSaveBtnActionPerformed

    private void rutaSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rutaSaveBtnActionPerformed
        Ruta nueva;
        int codigo = Integer.parseInt(rutaCodigoTxt.getText()),
                codDestino = Integer.parseInt(String.valueOf(rutaCodDestinoCombo.getSelectedItem()));
        nueva = new Ruta(codigo, codDestino);
        save(nueva, null, null, FRAMERUTA);
    }//GEN-LAST:event_rutaSaveBtnActionPerformed

    private void envioGuardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envioGuardarBtnActionPerformed
        Envio nuevo;
        int codigo = Integer.parseInt(envioCodigoTxt.getText()),
                codPaquete = Integer.parseInt(String.valueOf(envioCodPaquete.getSelectedItem())),
                codRuta = Integer.parseInt(String.valueOf(envioCodRutaCombo.getSelectedItem())),
                codDestino = Integer.parseInt(String.valueOf(envioCodDestinoCombo.getSelectedItem()));
        nuevo = new Envio(codigo, codPaquete, codRuta, codDestino);
        save(nuevo, null, null, FRAMEENVIO);
    }//GEN-LAST:event_envioGuardarBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
            
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField LogInPassTxt;
    private javax.swing.JMenu administradorMenuModificar;
    private javax.swing.JMenuItem administradorMenuPC;
    private javax.swing.JMenuItem administradorMenuRuta;
    private javax.swing.JMenuItem administradorMenuUsuario;
    private javax.swing.JComboBox<String> almacenCodDestinoCombo;
    private javax.swing.JComboBox<String> almacenCodEnvioCombo;
    private javax.swing.JComboBox<String> almacenCodPCCombo;
    private javax.swing.JComboBox<String> almacenCodRutaCombo;
    private javax.swing.JTextField almacenCostoTxt;
    private javax.swing.JButton almacenGuardarBtn;
    private javax.swing.JTextField almacenTarifaTxt;
    private javax.swing.JFormattedTextField almacenTiempoTxt;
    private javax.swing.JTextField clientNameTxt;
    private javax.swing.JTextField clientNitTxt;
    private javax.swing.JButton clientSaveBtn;
    private javax.swing.JButton clientSearchBtn;
    private javax.swing.JTextField destinoCodigoTxt;
    private javax.swing.JFormattedTextField destinoCuotaTxt;
    private javax.swing.JTextField destinoDireccionTxt;
    private javax.swing.JPanel destinoPanel;
    private javax.swing.JButton destinoSaveBtn;
    private javax.swing.JButton envioBuscarBtn;
    private javax.swing.JComboBox<String> envioCodDestinoCombo;
    private javax.swing.JComboBox<String> envioCodPaquete;
    private javax.swing.JComboBox<String> envioCodRutaCombo;
    private javax.swing.JTextField envioCodigoTxt;
    private javax.swing.JButton envioGuardarBtn;
    private javax.swing.JCheckBox envioRecibidoCheck;
    private javax.swing.JInternalFrame frameAlmacen;
    private javax.swing.JInternalFrame frameCliente;
    private javax.swing.JInternalFrame frameEnvio;
    private javax.swing.JInternalFrame framePaquete;
    private javax.swing.JInternalFrame framePuntoControl;
    private javax.swing.JInternalFrame frameRuta;
    private javax.swing.JInternalFrame frameUsuario;
    private javax.swing.JLabel incorrectValuesLbl;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton logInBtn;
    private javax.swing.JInternalFrame logInFrame;
    private javax.swing.JTextField logInUsrTxt;
    private javax.swing.JMenu menuAdministradorFile;
    private javax.swing.JMenu menuOperadoresFile;
    private javax.swing.JMenu menuRecepcionistaFile;
    private javax.swing.JMenuItem operadorMenuAlmacen;
    private javax.swing.JMenu operadorMenuModificar;
    private javax.swing.JTextField paqueteNitTxt;
    private javax.swing.JComboBox<String> paquetePaquetesCombo;
    private javax.swing.JFormattedTextField paquetePesoTxt;
    private javax.swing.JCheckBox paquetePriorizadoCheck;
    private javax.swing.JButton paqueteSaveBtn;
    private javax.swing.JTextField pcCodigoTxt;
    private javax.swing.JComboBox<String> pcDestinoCombo;
    private javax.swing.JTextField pcDireccionTxt;
    private javax.swing.JSpinner pcLimiteSpinner;
    private javax.swing.JComboBox<String> pcRutaCombo;
    private javax.swing.JButton pcSaveBtn;
    private javax.swing.JFormattedTextField pcTarifaDeOperacionTxt;
    private javax.swing.JMenu recepcionistaMenModificar;
    private javax.swing.JMenuItem recepcionistaMenuCliente;
    private javax.swing.JMenuItem recepcionistaMenuEnvio;
    private javax.swing.JComboBox<String> rutaCodDestinoCombo;
    private javax.swing.JTextField rutaCodigoTxt;
    private javax.swing.JCheckBox rutaEstadoCheck;
    private javax.swing.JButton rutaSaveBtn;
    private javax.swing.JMenu sesionMenu;
    private javax.swing.JTextField usrNameTxt;
    private javax.swing.JPasswordField usrPassTxt;
    private javax.swing.JButton usrSaveBtn;
    private javax.swing.JButton usrSearchBtn;
    private javax.swing.JToggleButton usrShowPassBtn;
    private javax.swing.JComboBox<String> usrTypeCombo;
    private javax.swing.JTextField usrUsrTxt;
    // End of variables declaration//GEN-END:variables
}
