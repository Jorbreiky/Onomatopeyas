package animalesftaoad;

import bd.Conexion;
import java.awt.Color;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reproductor.Reproductor;
import utilerias.DiseñoResponsivo;
import utilerias.Sesion;

/*FORLUMARIO PRINCIPAL EN EL CUAL EL BENEFICIARIO SE PODRA REGISTRAR O BUSCAR SU NOMBRE
PARA ENTRAR A INTERACTUAR CON LAS ACTIVIDADES DE LA APLICACION*/
public class InicioSesion extends javax.swing.JFrame {

    public InicioSesion() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA EL FORMULARIO
        iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
        finCombo=false;
        beneficiario = null;
        inicioSesion = this;
        //new Reproductor("juanpirulero");
        new Reproductor().reproducirWAV("bienvenido",3000);//SE REPRODUCE EL SONIDO DE BIENVENIDA
        setVisible(true);//SE VIZUALIZA EL FORMULARIO
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
        DE LA CLASE DISEÑO RESPONSIVO*/
        new DiseñoResponsivo().componente(JLTitulo);
        new DiseñoResponsivo().componente(JBJugar);
        new DiseñoResponsivo().componente(JBRegistrar);
        new DiseñoResponsivo().componente(JCNombre);
        new DiseñoResponsivo().componente(JLFondo);
        new DiseñoResponsivo().componente(JLFoto);
        new DiseñoResponsivo().componente(JLLogo);
        new DiseñoResponsivo().componente(JLMejorPuntuacion);
        new DiseñoResponsivo().componente(JLUltimaPuntuacion);
        new DiseñoResponsivo().componente(JLUltimaSesion);
        new DiseñoResponsivo().componente(JTNombre);
        new DiseñoResponsivo().componente(jLabel1);
        
        JLFondo.setIcon(new DiseñoResponsivo().getFondo("fondo"));//SE ASIGNA LA IMAGEN DE FONDO PARA LA VENTANA
        /*SE ASIGNA EL ICONO PARA EL LOGO EN LA VENTANA*/
        new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
        JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());//SE ASIGNA LA FUENTE DEL TITULO
        /*SE ASIGNA EL TAMAÑO Y FUENTE A LOS LABEL DEL FORMULARIO*/
        int sizeLabel = 26;
        jLabel1.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        JLMejorPuntuacion.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        JLUltimaPuntuacion.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        JLUltimaSesion.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        JTNombre.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel-5));
        JCNombre.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel-5));
        JBJugar.setFont(new DiseñoResponsivo().getFontIndicador(3));
        JBRegistrar.setFont(new DiseñoResponsivo().getFontIndicador(3));
        
        /*SE ASIGNA EL COLOR DE LA FUENTE DE LOS LABEL EN EL FORMULARIO*/
        JLTitulo.setForeground(Color.red);
        jLabel1.setForeground(Color.red);
        JLMejorPuntuacion.setForeground(Color.red);
        JLUltimaPuntuacion.setForeground(Color.red);
        JLUltimaSesion.setForeground(Color.red);
        JTNombre.setForeground(Color.red);
        JCNombre.setForeground(Color.red);
        JBJugar.setForeground(Color.red);
        JBRegistrar.setForeground(Color.red);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLLogo = new javax.swing.JLabel();
        JLTitulo = new javax.swing.JLabel();
        JLFoto = new javax.swing.JLabel();
        JBRegistrar = new javax.swing.JButton();
        JBJugar = new javax.swing.JButton();
        JLUltimaSesion = new javax.swing.JLabel();
        JLUltimaPuntuacion = new javax.swing.JLabel();
        JLMejorPuntuacion = new javax.swing.JLabel();
        JTNombre = new javax.swing.JTextField();
        JCNombre = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(20, 20, 170, 140);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("BIENVENIDO A LA GRANJA");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(170, 10, 1040, 90);
        getContentPane().add(JLFoto);
        JLFoto.setBounds(460, 90, 510, 380);

        JBRegistrar.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        JBRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar1.png"))); // NOI18N
        JBRegistrar.setText("Registrarse");
        JBRegistrar.setBorder(null);
        JBRegistrar.setContentAreaFilled(false);
        JBRegistrar.setFocusable(false);
        JBRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBRegistrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar0.png"))); // NOI18N
        JBRegistrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar2.png"))); // NOI18N
        JBRegistrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JBRegistrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JBRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(JBRegistrar);
        JBRegistrar.setBounds(50, 440, 350, 170);

        JBJugar.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        JBJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jugar1.png"))); // NOI18N
        JBJugar.setText("Jugar");
        JBJugar.setBorder(null);
        JBJugar.setContentAreaFilled(false);
        JBJugar.setFocusable(false);
        JBJugar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBJugar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jugar0.png"))); // NOI18N
        JBJugar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jugar2.png"))); // NOI18N
        JBJugar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JBJugar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JBJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBJugarActionPerformed(evt);
            }
        });
        getContentPane().add(JBJugar);
        JBJugar.setBounds(1000, 430, 350, 180);

        JLUltimaSesion.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JLUltimaSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLUltimaSesion.setText("Ultima Sesión: 28/09/2016");
        getContentPane().add(JLUltimaSesion);
        JLUltimaSesion.setBounds(440, 630, 510, 30);

        JLUltimaPuntuacion.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JLUltimaPuntuacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLUltimaPuntuacion.setText("Ultima Puntuacion: 0%");
        getContentPane().add(JLUltimaPuntuacion);
        JLUltimaPuntuacion.setBounds(440, 590, 510, 30);

        JLMejorPuntuacion.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JLMejorPuntuacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLMejorPuntuacion.setText("Mejor Puntuacion: 0%");
        getContentPane().add(JLMejorPuntuacion);
        JLMejorPuntuacion.setBounds(440, 550, 510, 30);

        JTNombre.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JTNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTNombreKeyReleased(evt);
            }
        });
        getContentPane().add(JTNombre);
        JTNombre.setBounds(530, 490, 400, 30);

        JCNombre.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JCNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCNombreActionPerformed(evt);
            }
        });
        JCNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JCNombreKeyReleased(evt);
            }
        });
        getContentPane().add(JCNombre);
        JCNombre.setBounds(530, 520, 400, 30);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombre:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(340, 490, 180, 30);
        getContentPane().add(JLFondo);
        JLFondo.setBounds(0, -10, 1390, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*METODO DE ACCION DEL BOTON REGISTRAR BENEFICIARIO*/
    private void JBRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegistrarActionPerformed
        new RegistrarBeneficiario();//SE VIZUALIZA EL FORMULARIO PARA REGISTRAR UN NUEVO VENEFICIARIO
    }//GEN-LAST:event_JBRegistrarActionPerformed

    /*METODO DE ACICON DEL BOTON JUGAR*/
    private void JBJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBJugarActionPerformed
        //SE CREA UNA SESION NUEVA CON LOS DATOS INICIALES PARA EL BENEFICIARIO
        Sesion sesion = new Sesion(beneficiario,new Conexion().obtenerIdSesion(),new GregorianCalendar(),0,0);
        /*SI SE SELECCIONO UN BENEFICIARIO PASA A VISUALIZAR EL MENU PRINCIPAL*/
        if(beneficiario!=null){
            new MenuPrincipal(sesion); //VISUALIZA EL MENU PRINCIPAL
            dispose();//OCULTA ESTE FORMULARIO
        }else{
            JOptionPane.showMessageDialog(null,"Selecciona tu Nombre");
        }
    }//GEN-LAST:event_JBJugarActionPerformed

    /*ESTE METODO ES LA ACCION AL DAR CLIC EN EL COMBO BOX PARA SELECCIONAR EL BENEFICIARIO*/
    private void JCNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCNombreActionPerformed
        int apuntador = JCNombre.getSelectedIndex();
        if(finCombo&&apuntador>=0){
            beneficiario = (Object[]) listaBeneficiarios.get(apuntador);
            new DiseñoResponsivo().adaptarImagenComponente(JLFoto,new Conexion().getFoto(beneficiario[0].toString()));
            JTNombre.setText(beneficiario[1]+" "+beneficiario[2]+" "+beneficiario[3]);
            JLMejorPuntuacion.setText("Mejor Puntuación: "+Math.round(Double.parseDouble(new Conexion().getMaxPuntuacion(beneficiario[0].toString())))+"  %");
            JLUltimaPuntuacion.setText("Ultima Puntuación: "+Math.round(Double.parseDouble(new Conexion().getUltimaPuntuacion(beneficiario[0].toString())))+"  %");
            JLUltimaSesion.setText("Ultima Sesión: "+new Conexion().getUltimaSesion(beneficiario[0].toString()));
            JCNombre.setVisible(false);
        }
    }//GEN-LAST:event_JCNombreActionPerformed

    private void JCNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCNombreKeyReleased
        
    }//GEN-LAST:event_JCNombreKeyReleased

    /*ESTE METODO ES LA ACCION DE LA CAJA DE TEXTO PARA BUSCAR EL NOMBRE EL CUAL
    SE EJECUTA AL SOLTAR UNA LETRA DEL TECLADO*/
    private void JTNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNombreKeyReleased
        try{
            JTNombre.setText(JTNombre.getText().toUpperCase());//SE CONVIERTE EL TEXTO DEL NOMBRE A MAYUSCULAS
            JCNombre.removeAllItems();//SE ELIMINAN LOS ITEMS DEL COMBOBOX
            finCombo=false;
            JCNombre.setVisible(true);//SE OCULTA EL COMBOBOX
            listaBeneficiarios = new ArrayList();//SE INICIALIZA LA LISTA DE BENEFICIARIOS QUE COINCIDAN CON EL PATRON DEL JTNOMBRE
            List lista = new Conexion().getBeneficiariosActivos();//SE OBTIENEN TODOS LOS BENEFICIARIOS REGISTRADOS 
            /*SE OBTIENEN LOS BENEFICIARIOS QUE COINCIDAN CON EL TEXTO DEL NOMBRE*/
            for(int i=0; i<lista.size(); i++){
                Object[] beneficiario = (Object[]) lista.get(i);//SE AGREGA EL BENEFCICIARIO A LA LISTA
                String nombre = beneficiario[1]+" "+beneficiario[2]+" "+beneficiario[3];
                if(nombre.matches(".*?"+JTNombre.getText()+".*?")){
                    JCNombre.addItem(beneficiario[1]+" "+beneficiario[2]+" "+beneficiario[3]);//SE AGREGA EL NOMBRE DEL BENEFICIARIO AL COMBOBOX
                    listaBeneficiarios.add(lista.get(i));//SE AGREGA EL BENEFCICIARIO A LA LISTA
                }
            }
            finCombo=true;
        }catch(Exception e){
            System.out.println("Error combo: "+e);
        }
    }//GEN-LAST:event_JTNombreKeyReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    public static void main(String args[]) {
        new InicioSesion();
    }
    
    
    private List listaBeneficiarios;
    private boolean finCombo;
    private Object[] beneficiario;
    private JFrame inicioSesion;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBJugar;
    private javax.swing.JButton JBRegistrar;
    private javax.swing.JComboBox<String> JCNombre;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLFoto;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLMejorPuntuacion;
    private javax.swing.JLabel JLTitulo;
    private javax.swing.JLabel JLUltimaPuntuacion;
    private javax.swing.JLabel JLUltimaSesion;
    private javax.swing.JTextField JTNombre;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
