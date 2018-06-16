package animalesftaoad;

import bd.Conexion;
import camara.Camara;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utilerias.DiseñoResponsivo;

/*FORMULARIO PARA REGISTRAR O MODIFICAR A UN BENEFICIARIO EN LA BASE DE DATOS*/
public class RegistrarBeneficiario extends javax.swing.JFrame {

    /*ESTE CONSTRUCTOR SIN PARAMETROS ES PARA REGISTRAR UN NUEVO BENEFICIARIO*/
    public RegistrarBeneficiario() {
        try{
            JTFecha = new JDateChooser();//SE INICIALIZA LA FECHA 
            JTFecha.setBounds(270, 450, 490, 40);//SE POSICIONA EL COMPONENTE DE LA FECHA EN EL FOMULARIO
            getContentPane().add(JTFecha);//SE AGREGA LA FECHA EN EL FORMULARIO
            initComponents();
            iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO

            /*SE OCULTAN LOS JCHECKBOX QUE SIRVEN PARA ACTIVAR LA MODIFICACION DE LOS CAMPOS AL MODIFICAR EL USUARIO*/
            JCNombre.setVisible(false);
            JCPaterno.setVisible(false);
            JCMaterno.setVisible(false);
            JCFecha.setVisible(false);

            setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA EL FORMULARIO EN LA PANTALL
            setVisible(true);//SE VISUALIZA EL FORMULARIO
            camara = new Camara();//SE CREA UNA INSTANCIACION DE LA CLASE CAMARA 
            camara.setBounds(JPCamara.getBounds());//SE PASA COMO PARAMETRO EL JPAL QUE REEMPLAZARA LA CAMARA
            
            /*SE ASIGNA EL EVENTO AL DAR CLIC EL MOUSE DENTRO DEL PANEL*/
            camara.addMouseListener(new MouseAdapter(){
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    CamaraPanelEventClicked();
                }
            });
            this.camaraActiva = false;
            inicializar();//SE VISUALIZA LA CAMARA EN EL PANEL 
        }catch(Exception e){
            
        }
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
        DE LA CLASE DISEÑO RESPONSIVO*/
        new DiseñoResponsivo().componente(JBRegistrar);
        new DiseñoResponsivo().componente(JBCancelar);
        new DiseñoResponsivo().componente(JLFondo);
        new DiseñoResponsivo().componente(JLTitulo);
        new DiseñoResponsivo().componente(JPCamara);
        new DiseñoResponsivo().componente(JLFoto);
        new DiseñoResponsivo().componente(JTCurp);
        new DiseñoResponsivo().componente(JTFecha);
        new DiseñoResponsivo().componente(JTMaterno);
        new DiseñoResponsivo().componente(JTNombre);
        new DiseñoResponsivo().componente(JTPaterno);
        new DiseñoResponsivo().componente(jLabel1);
        new DiseñoResponsivo().componente(jLabel2);
        new DiseñoResponsivo().componente(jLabel3);
        new DiseñoResponsivo().componente(jLabel4);
        new DiseñoResponsivo().componente(jLabel5);
        new DiseñoResponsivo().componente(jLabel6);
        int sizeLabel = 26;
        int size = 26;
        int fuenteButon = 4;
        int sizeButton = 60;
        /*SE ASIGNA LA FUENTE Y TAMAÑO DE LA FUENTE A LOS COMPONENTES DEL FORMULARIO*/
        jLabel1.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        jLabel2.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        jLabel3.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        jLabel4.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        jLabel5.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        jLabel6.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        JTFecha.setFont(new DiseñoResponsivo().getFontTexto(size));
        JTCurp.setFont(new DiseñoResponsivo().getFontTexto(size));
        JTNombre.setFont(new DiseñoResponsivo().getFontTexto(size));
        JTPaterno.setFont(new DiseñoResponsivo().getFontTexto(size));
        JTMaterno.setFont(new DiseñoResponsivo().getFontTexto(size));
        JTFecha.setFont(new DiseñoResponsivo().getFontTexto(size-5));
        JBRegistrar.setFont(new DiseñoResponsivo().getFontIndicador(fuenteButon));
        JBCancelar.setFont(new DiseñoResponsivo().getFontIndicador(sizeButton));
        JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());
        
        /*SE ASIGNA EL COLOR DE LA FUENTE A LOS COMPONENTES DEL FORMULARIO*/
        jLabel1.setForeground(Color.RED);
        jLabel2.setForeground(Color.RED);
        jLabel3.setForeground(Color.RED);
        jLabel4.setForeground(Color.RED);
        jLabel5.setForeground(Color.RED);
        jLabel6.setForeground(Color.RED);
        JTFecha.setForeground(Color.RED);
        JTCurp.setForeground(Color.RED);
        JTNombre.setForeground(Color.RED);
        JTPaterno.setForeground(Color.RED);
        JTMaterno.setForeground(Color.RED);
        JLTitulo.setForeground(Color.RED);
        JBRegistrar.setForeground(Color.RED);
        JBCancelar.setForeground(Color.RED);
        
        /*SE ASIGNAN LOS ICONOS A LOS JLABEL*/
        new DiseñoResponsivo().adaptarImagenComponente(JLFondo,new ImageIcon(getClass().getResource("/img/fondo.jpg")));
        new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
    }
    
    /*ESTE CONSTRUCTOR QUE RECIBE PARAMETROS SE UTILIZA PARA MODIFICAR EL BENEFICIARIO PASANDOLE COMO PARAMETROS LOS 
    DATOS DEL BENEFICIARIO */
    public RegistrarBeneficiario(String curp,String nombre,String paterno,String materno,String fechaNacimiento) {
        try{
            JTFecha = new JDateChooser();//SE INICIALIZA LA FECHA
            JTFecha.setBounds(250, 450, 490, 40);//SE DEFINE EL TAMAÑO Y POSICION DE LA FECHA
            getContentPane().add(JTFecha);//SE AGREGA LA FECHA AL FORMULARIO
            initComponents(); 
            /*SE DEFINEN LAS IMAGENES PARA LOS LABEL DEL FORMULARIO*/
            new DiseñoResponsivo().adaptarImagenComponente(JLFondo,new ImageIcon(getClass().getResource("/img/fondo.jpg")));
            new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
            
            iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
            JLTitulo.setText("MODIFICAR BENEFICIARIO");//SE MODIFICA EL TEXTO DEL TITULO
            /*SE ACTIVAN LOS CHECKBOX PARA HABILITAR LA MODIFICACION DEL BENEFICIARIO*/
            JTCurp.setEditable(false);
            JTNombre.setEditable(false);
            JTPaterno.setEditable(false);
            JTMaterno.setEditable(false);
            JTFecha.setEnabled(false);
            
            /*SE ASIGNA EL COLOR DE LA FUENTE DE LOS COMPONENTES DEL FORMULARIO*/
            JTCurp.setForeground(Color.GREEN);
            JTFecha.setForeground(Color.GREEN);
            JTMaterno.setForeground(Color.GREEN);
            JTNombre.setForeground(Color.GREEN);
            JTPaterno.setForeground(Color.GREEN);
        
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            /*SE ASIGNAN LOS DATOS DEL BENEFICIARIO A LAS CAJAS DE TEXTO DEL FORMULARIO*/
            JTCurp.setText(curp);
            JTNombre.setText(nombre);
            JTPaterno.setText(paterno);
            JTMaterno.setText(materno);
            JTFecha.setDate(formatoDelTexto.parse(fechaNacimiento));
            
            setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA LA VENTANA EN LA PANTALL
            setVisible(true);//SE VISUALIZA EL FORMULARIO
            
            camara = new Camara();//SE INICIALIZA LA CAMARA
            camara.setBounds(JPCamara.getBounds());//SE ASIGNA POSICION Y TAMAÑO A LA CAMARA
            /*SE ASIGNA ACCION AL EVENTO DEL RATON AL DAR CLIC EN LA CAMARA*/
            camara.addMouseListener(new MouseAdapter(){
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    CamaraPanelEventClicked();
                }
            });
            this.foto = new Conexion().getFoto(curp);//SE OBTIENE LA FOTO DEL BENEFICIARIO
            camaraActiva = false;
            JBRegistrar.setText("Guardar");
            JPCamara.setVisible(true);
            new DiseñoResponsivo().adaptarImagenComponente(JLFoto,foto);//SE ASIGNA LA FOTO AL JLABEL DEL FORMULARIO
        }catch(Exception e){
            
        }
    }

    /*METODO QUE AGREGA EL PANEL DE LA CAMARA AL FORMULARIO
    PARA TOMARSE LA FOTO PARA EL REGISTRO DEL BENEFICIARIO*/
    private void inicializar(){
        if(!camaraActiva){
            getContentPane().add(camara);//SE AGREGA LA CAMARA 
            getContentPane().add(camara.obtenerCamara(JPCamara));//SE VISUALIZA LA CAMARA
            camaraActiva=true;
        }else{
            camara.cerrarCamara(this);
            remove(camara);//SE ELIMINA LA CAMARA DEL FORMULARIO
        }
    }
    
    /*METODO QUE AL EJECUTAR CAPTURA LA FOTO DESDE LA CAMARA*/
    private void CamaraPanelEventClicked(){
        foto=camara.capturarFoto();//CAPTURA LA FOTO DE LA CAMARA
        camara.cerrarCamara(this);//CIERRA EL PANEL DE LA CAMARA
        remove(camara);//ELIMINA LA CAMARA DEL FORMULARIO
        new DiseñoResponsivo().adaptarImagenComponente(JLFoto,camara.obtenerFoto());//VISUALIZA LA NUEVA FOTO EN EL JLABEL
        camaraActiva=false;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPCamara = new javax.swing.JPanel();
        JLFoto = new javax.swing.JLabel();
        JLTitulo = new javax.swing.JLabel();
        JBRegistrar = new javax.swing.JButton();
        JBCancelar = new javax.swing.JButton();
        JLLogo = new javax.swing.JLabel();
        JTCurp = new javax.swing.JTextField();
        JTNombre = new javax.swing.JTextField();
        JTPaterno = new javax.swing.JTextField();
        JTMaterno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JCMaterno = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JCFecha = new javax.swing.JCheckBox();
        JCNombre = new javax.swing.JCheckBox();
        JCPaterno = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JPCamara.setBackground(new java.awt.Color(102, 255, 51));
        JPCamara.setLayout(null);

        JLFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLFotoMouseClicked(evt);
            }
        });
        JPCamara.add(JLFoto);
        JLFoto.setBounds(0, 0, 500, 380);

        getContentPane().add(JPCamara);
        JPCamara.setBounds(790, 140, 500, 380);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("REGISTRAR BENEFICIARIO");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(230, 10, 1050, 90);

        JBRegistrar.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        JBRegistrar.setText("Registrar");
        JBRegistrar.setBorder(null);
        JBRegistrar.setBorderPainted(false);
        JBRegistrar.setContentAreaFilled(false);
        JBRegistrar.setFocusable(false);
        JBRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(JBRegistrar);
        JBRegistrar.setBounds(930, 620, 310, 60);

        JBCancelar.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        JBCancelar.setText("Cancelar");
        JBCancelar.setBorder(null);
        JBCancelar.setBorderPainted(false);
        JBCancelar.setContentAreaFilled(false);
        JBCancelar.setFocusable(false);
                JBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(JBCancelar);
        JBCancelar.setBounds(270, 610, 290, 60);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(10, 10, 170, 140);

        JTCurp.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JTCurp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTCurp.setBorder(null);
        JTCurp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTCurpKeyReleased(evt);
            }
        });
        getContentPane().add(JTCurp);
        JTCurp.setBounds(270, 170, 460, 40);

        JTNombre.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JTNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTNombre.setBorder(null);
        JTNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTNombreKeyReleased(evt);
            }
        });
        getContentPane().add(JTNombre);
        JTNombre.setBounds(270, 240, 460, 40);

        JTPaterno.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JTPaterno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTPaterno.setBorder(null);
        JTPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTPaternoKeyReleased(evt);
            }
        });
        getContentPane().add(JTPaterno);
        JTPaterno.setBounds(270, 310, 460, 40);

        JTMaterno.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JTMaterno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTMaterno.setBorder(null);
        JTMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTMaternoKeyReleased(evt);
            }
        });
        getContentPane().add(JTMaterno);
        JTMaterno.setBounds(270, 380, 460, 40);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nacimiento:  ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 480, 260, 40);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("CURP:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 170, 270, 40);

        JCMaterno.setBorder(null);
        JCMaterno.setContentAreaFilled(false);
        JCMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCMaternoActionPerformed(evt);
            }
        });
        getContentPane().add(JCMaterno);
        JCMaterno.setBounds(750, 390, 20, 23);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 240, 270, 40);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Apellido Paterno:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 310, 270, 40);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Apellido Materno:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 380, 270, 40);

        JCFecha.setBorder(null);
        JCFecha.setContentAreaFilled(false);
        JCFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCFechaActionPerformed(evt);
            }
        });
        getContentPane().add(JCFecha);
        JCFecha.setBounds(750, 460, 20, 23);

        JCNombre.setBorder(null);
        JCNombre.setContentAreaFilled(false);
        JCNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCNombreActionPerformed(evt);
            }
        });
        getContentPane().add(JCNombre);
        JCNombre.setBounds(750, 250, 20, 23);

        JCPaterno.setBorder(null);
        JCPaterno.setContentAreaFilled(false);
        JCPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCPaternoActionPerformed(evt);
            }
        });
        getContentPane().add(JCPaterno);
        JCPaterno.setBounds(750, 320, 20, 23);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Fecha de  ");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 440, 260, 40);
        getContentPane().add(JLFondo);
        JLFondo.setBounds(-40, -20, 1550, 750);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*METODO DE ACCION AL BOTON DE REGISTRO O MODIFICACION DEL BENEFICIARIO*/
    private void JBRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegistrarActionPerformed
        try{
            /*SE OBTIENE EL TEXTO DE LAS CAJAS DE TEXTO EN CADA UNA DE LAS VARIABLE*/
            String curp = JTCurp.getText();
            String nombre = JTNombre.getText();
            String paterno = JTPaterno.getText();
            String materno = JTMaterno.getText();
            String patronFecha ="([0-2][1-9]|[3][0-1])/([0][1-9]|[1][0-2])/([2-9][0-9]|[0-1][0-9])";
            
            String fechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").format(JTFecha.getDate());
            //SE VALIDA QUE EL COLOR DE LA FUENTE DE LAS CAJAS DE TEXTO NO SEA ROJO, YA QUE SIGNIFICA QUE NO ESTA BIEN ESCRITO
            if(JTCurp.getForeground()!=Color.red){
                if(JTNombre.getForeground()!=Color.red){
                    if(JTPaterno.getForeground()!=Color.red){
                        if(JTMaterno.getForeground()!=Color.red){
                                //SI LA CAMARA ESTA ACTIVA, SE DESACTIVARA
                                if(camaraActiva){
                                    camara.cerrarCamara(this);   
                                }
                                /*SI EL TEXTO DEL BOTON ES GUARDAR, SE ACCEDERA A LA BASE DE DATOS PARA MODIFICAR AL BENEFICIARIO*/
                                if(JBRegistrar.getText().equalsIgnoreCase("Guardar")){
                                    //SE PASA LOS DATOS A LA CLASE CONEXION PARA MODIFICAR LOS NUEVOS DATOS DEL BENEFICIARIO
                                    String mensaje = new Conexion().modificarBeneficiario(curp, nombre, paterno, materno, fechaNacimiento, foto);
                                    JOptionPane.showMessageDialog(null,mensaje,"Mensaje",JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                }else{
                                    /*EN CASO QUE NO LO SEA ENTONCES SE PASARA A REGISTRAR UN NUEVO BENEFICIARIO A LA BASE DE DATOS*/
                                    String mensaje = new Conexion().agregarBeneficiario(curp, nombre, paterno, materno, fechaNacimiento, foto);
                                    JOptionPane.showMessageDialog(null,mensaje,"Mensaje",JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                }
                        }else{
                            JOptionPane.showMessageDialog(null,"Debe introducir el\n Apellido Materno del Beneficiario");
                            JTMaterno.requestFocus();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Debe introducir el\n Apellido Paterno del Beneficiario");
                        JTPaterno.requestFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Debe introducir el\n Nombre del Beneficiario");
                    JTNombre.requestFocus();
                }
            }else{
                JOptionPane.showMessageDialog(null,"Debe introducir la\n CURP del Beneficiario");
                JTCurp.requestFocus();
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Debes ingresar la fecha de nacimiento");
        }
    }//GEN-LAST:event_JBRegistrarActionPerformed

    /*METODO DE ACCION DEL JLABEL PARA ACTIVAR O DESACTIVAR LA CAMARA */
    private void JLFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLFotoMouseClicked
        inicializar();
    }//GEN-LAST:event_JLFotoMouseClicked

    /*METODO DE ACCION DEL BOTON CANCELAR*/
    private void JBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBCancelarActionPerformed
        if(camaraActiva){
            camara.cerrarCamara(this);//CIERRA LA CAMARA SI ESTA ACTIVA
            remove(camara);//ELIMINA LA CAMARA DEL FORMULARIO
        }
        dispose();//OCULTA EL FORMULARIO DE REGISTRO DE BENEFICIARIO
    }//GEN-LAST:event_JBCancelarActionPerformed

    /*METODO DE ACCION AL SOLTAR UNA TECLA DEL TECLADO*/
    private void JTCurpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTCurpKeyReleased
        JTCurp.setText(JTCurp.getText().toUpperCase());//SE CONVIERTE A MAYUSCULAS EL TEXTO DE LA CURP
        /*SE DEFINE EL PATRON DE LA EXPRESION REGULAR PARA VALIDAR 
        LA CURP*/
        String curp ="([A-Z]{4})";
        curp +="([2-9][0-9]|[0-1][0-9])([0][1-9]|[1][0-2])([0-2][1-9]|[3][0-1])";
        curp += "([H|M])";
        curp += "([A-Z]{2})";
        curp += "([A-Z0-9]{5})";
        //curp += "([A-Z]|[0-9]){2}";
        
        if(JTCurp.getText().matches(curp)){
            JTCurp.setForeground(Color.GREEN);//SI CUMPLE CON LA EXPRESION REGULAR CAMBIA EL COLOR DE LA FUENTE
        }else{
            JTCurp.setForeground(Color.red);//DE LO CONTRARIO ASIGNA EL COLOR DE FUENTE A ROJO
        }
        
    }//GEN-LAST:event_JTCurpKeyReleased

    /*METODO DE ACCION AL SOLTAR TECLA DEL TECLADO EN LA CAJA DE TEXTO PARA EL NOMBRE*/
    private void JTNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNombreKeyReleased
        JTNombre.setText(JTNombre.getText().toUpperCase());
        
        if(JTNombre.getText().matches("[A-Z|Ñ|\\s]+")){
            JTNombre.setForeground(Color.GREEN);
        }else{
            JTNombre.setForeground(Color.red);
        }
    }//GEN-LAST:event_JTNombreKeyReleased

    /*METODO DE ACCION AL SOLTAR TECLA DEL TECLADO EN LA CAJA DE TEXTO PARA EL APELLIDO PATERNO*/
    private void JTPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPaternoKeyReleased
        JTPaterno.setText(JTPaterno.getText().toUpperCase());
        if(JTPaterno.getText().matches("[A-Z|Ñ|\\s]+")){
            JTPaterno.setForeground(Color.GREEN);
        }else{
            JTPaterno.setForeground(Color.red);
        }
    }//GEN-LAST:event_JTPaternoKeyReleased

    /*METODO DE ACCION AL SOLTAR TECLA DEL TECLADO EN LA CAJA DE TEXTO PARA EL APELLIDO MATERNO*/
    private void JTMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTMaternoKeyReleased
        JTMaterno.setText(JTMaterno.getText().toUpperCase());
        if(JTMaterno.getText().matches("[A-Z|Ñ|\\s]+")){
            JTMaterno.setForeground(Color.GREEN);
        }else{
            JTMaterno.setForeground(Color.red);
        }
    }//GEN-LAST:event_JTMaternoKeyReleased

    /*METODO DE ACCION AL SELECCIONAR EL CHECBOX DEL NOMBRE*/
    private void JCNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCNombreActionPerformed
        if(JCNombre.isSelected()){
            JTNombre.setEditable(true);//HABILITA LA EDICION DEL NOMBRE
        }else{
            JTNombre.setEditable(false);//DESHABILITA LA EDICION DEL NOMBRE
        }
    }//GEN-LAST:event_JCNombreActionPerformed

    /*METODO DE ACCION AL SELECCIONAR EL CHECBOX DEL APELLIDO PATERNO*/
    private void JCPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCPaternoActionPerformed
        if(JCPaterno.isSelected()){
            JTPaterno.setEditable(true);//HABILITA LA EDICION DEL APELLIDO PATERNO
        }else{
            JTPaterno.setEditable(false);//DESHABILITA LA EDICION DEL APELLIDO PATERNO
        }
    }//GEN-LAST:event_JCPaternoActionPerformed

    /*METODO DE ACCION AL SELECCIONAR EL CHECBOX DEL APELLIDO MATERNO*/
    private void JCMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCMaternoActionPerformed
        if(JCMaterno.isSelected()){
            JTMaterno.setEditable(true);//HABILITA LA EDICION DEL APELLIDO MATERNO
        }else{
            JTMaterno.setEditable(false);//DESHABILITA LA EDICION DEL APELLIDO MATERNO
        }
    }//GEN-LAST:event_JCMaternoActionPerformed

    /*METODO DE ACCION AL SELECCIONAR EL CHECBOX DE LA FECHA DE NACIMIENTO*/
    private void JCFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCFechaActionPerformed
        if(JCFecha.isSelected()){
            JTFecha.setEnabled(true);//HABILITA LA EDICION DE LA FECHA DE NACIMIENTO            
        }else{
            JTFecha.setEnabled(false);//DESHABILITA LA EDICION DE LA FECHA DE NACIMIENTO
        }
    }//GEN-LAST:event_JCFechaActionPerformed

    
    public static void main(String args[]) {
        new RegistrarBeneficiario();
    }
    private Camara camara;
    private ImageIcon foto;
    private boolean camaraActiva;
    private JDateChooser JTFecha;    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBCancelar;
    private javax.swing.JButton JBRegistrar;
    private javax.swing.JCheckBox JCFecha;
    private javax.swing.JCheckBox JCMaterno;
    private javax.swing.JCheckBox JCNombre;
    private javax.swing.JCheckBox JCPaterno;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLFoto;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLTitulo;
    private javax.swing.JPanel JPCamara;
    private javax.swing.JTextField JTCurp;
    private javax.swing.JTextField JTMaterno;
    private javax.swing.JTextField JTNombre;
    private javax.swing.JTextField JTPaterno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
