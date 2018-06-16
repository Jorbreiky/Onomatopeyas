package animalesftaoad;

import bd.Conexion;
import java.awt.Color;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import reproductor.Reproductor;
import utilerias.DiseñoResponsivo;
import utilerias.Sesion;

/*FORMULARIO DEL MENU PRINCIPAL DE ACTIVIDADES PARA EL BENEFICIARIO*/
public class MenuPrincipal extends javax.swing.JFrame {

    /*EN EL CONSTRUCTOR SE RECIBE LA SESION CREADA EN EL INICIO DE SESION EN LA CUAL SE 
    REGISTRARA LOS INTENTOS Y ACIERTOS DEL BENEFICIARIO*/
    public MenuPrincipal(Sesion sesion) {
        initComponents();
        this.sesion = sesion;//SE INICIALIZA LA SESION PARA PODER SER MANEJADA EN TODA LA CLASE
        setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA LA VENTANA AL TAMAÑO DE LA PANTALL
        iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
        reproductor = new Reproductor("");//SE INICIALIZA EL REPRODUCTOR DE AUDIO
        setVisible(true);//SE VISUALIZA EL FORMULARIO
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
        DE LA CLASE DISEÑO RESPONSIVO*/
        new DiseñoResponsivo().componente(JBConocerAnimales);
        new DiseñoResponsivo().componente(JBIdentificarAnimales);
        new DiseñoResponsivo().componente(JBRegresar);
        new DiseñoResponsivo().componente(JLFondo);
        new DiseñoResponsivo().componente(JLLogo);
        new DiseñoResponsivo().componente(JLTitulo);
        new DiseñoResponsivo().componente(JBEncontrarALosAnimales);
        
        /*SE ASIGNA LA FUENTE Y TAMAÑO DE LA FUENTE A LOS JLABEL DEL FORMULARIO*/
        int size = 27;
        JBConocerAnimales.setFont(new DiseñoResponsivo().getFontTexto(size));
        JBEncontrarALosAnimales.setFont(new DiseñoResponsivo().getFontTexto(size));
        JBIdentificarAnimales.setFont(new DiseñoResponsivo().getFontTexto(size));
        JBRegresar.setFont(new DiseñoResponsivo().getFontTexto(size));
        JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());
        
        /*SE ASIGNA EL COLOR DE LA FUENTE A LOS LABEL DEL FORMULARIO*/
        JLTitulo.setForeground(Color.red);
        JBConocerAnimales.setForeground(Color.red);
        JBEncontrarALosAnimales.setForeground(Color.red);
        JBIdentificarAnimales.setForeground(Color.red);
        JLTitulo.setForeground(Color.BLUE);
        
        JLFondo.setIcon(new DiseñoResponsivo().getFondo("fondo"));//SE ASIGNA LA IMAGEN DE FONDO AL LABEL
        //SE ASIGNA EL ICONO DEL LOGO AL LABEL 
        new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
        
        /*SE ASIGNAN LOS ICONOS A LOS BOTONES DEL FORMULARIO*/
        new DiseñoResponsivo().adaptarImagenComponente(JBRegresar,new ImageIcon(getClass().getResource("/img/regresar.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JBConocerAnimales,new ImageIcon(getClass().getResource("/img/icono1.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JBIdentificarAnimales,new ImageIcon(getClass().getResource("/img/icono2.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JBEncontrarALosAnimales,new ImageIcon(getClass().getResource("/img/icono3.png")));
        JBRegresar.setText("");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLTitulo = new javax.swing.JLabel();
        JLLogo = new javax.swing.JLabel();
        JBEncontrarALosAnimales = new javax.swing.JButton();
        JBConocerAnimales = new javax.swing.JButton();
        JBIdentificarAnimales = new javax.swing.JButton();
        JBRegresar = new javax.swing.JButton();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("MENU PRINCIPAL");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(300, 80, 900, 50);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(10, 10, 170, 140);

        JBEncontrarALosAnimales.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JBEncontrarALosAnimales.setText("Encontrar a los animales");
        JBEncontrarALosAnimales.setBorder(null);
        JBEncontrarALosAnimales.setContentAreaFilled(false);
        JBEncontrarALosAnimales.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBEncontrarALosAnimales.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JBEncontrarALosAnimales.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JBEncontrarALosAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBEncontrarALosAnimalesActionPerformed(evt);
            }
        });
        getContentPane().add(JBEncontrarALosAnimales);
        JBEncontrarALosAnimales.setBounds(880, 290, 400, 240);

        JBConocerAnimales.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JBConocerAnimales.setText("Conocer a los animales");
        JBConocerAnimales.setBorder(null);
        JBConocerAnimales.setContentAreaFilled(false);
        JBConocerAnimales.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBConocerAnimales.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JBConocerAnimales.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JBConocerAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBConocerAnimalesActionPerformed(evt);
            }
        });
        getContentPane().add(JBConocerAnimales);
        JBConocerAnimales.setBounds(60, 290, 400, 240);

        JBIdentificarAnimales.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JBIdentificarAnimales.setText("Identificar a los Animales");
        JBIdentificarAnimales.setBorder(null);
        JBIdentificarAnimales.setContentAreaFilled(false);
        JBIdentificarAnimales.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBIdentificarAnimales.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JBIdentificarAnimales.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JBIdentificarAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBIdentificarAnimalesActionPerformed(evt);
            }
        });
        getContentPane().add(JBIdentificarAnimales);
        JBIdentificarAnimales.setBounds(470, 290, 400, 240);

        JBRegresar.setText("Regresar");
        JBRegresar.setBorder(null);
        JBRegresar.setContentAreaFilled(false);
        JBRegresar.setFocusable(false);
        JBRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(JBRegresar);
        JBRegresar.setBounds(1220, 590, 100, 90);
        getContentPane().add(JLFondo);
        JLFondo.setBounds(0, -30, 1450, 750);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*ESTE METODO ES LA ACCION DEL BOTON PARA LA ACTIVDAD 1 'CONOCER A LOS ANIMALES'*/
    private void JBConocerAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBConocerAnimalesActionPerformed
        new ConocerALosAnimales(sesion);
        dispose();
    }//GEN-LAST:event_JBConocerAnimalesActionPerformed

    /*ESTE METODO ES LA ACCION AL BOTON DE SALIR EL CUAL AL SER PRESIONADO GUARDA LA SESION DEL BENEFICIARIO
    EN LA BASE DE DATOS*/
    private void JBRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegresarActionPerformed
        try{
            if(sesion.getIntentos()>0){//SE BERIFICA SI SE REALIZARON INTENTOS
                sesion.setFechaFinal(new GregorianCalendar());//SE ASIGNA LA FECHA FINAL DE LA SESION A LA SESION 
                System.out.println(new Conexion().registrarSesion(sesion.getHoraInicial(),sesion.getHoraFinal(),sesion.getFecha()));
                System.out.println(new Conexion().registrarActividad(sesion.getIdSesion(),sesion.getCurp(), sesion.getIntentos(),sesion.getAciertos()));
            }
            reproductor.parar();//SE DETIENE EL REPRODUCTORS
            new InicioSesion();//SE VISUALIZA EL INICIO DE SESION
            dispose();//SE OCULTA EL MENU PRINCIPAL
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_JBRegresarActionPerformed

    /*ESTE METODO ES LA ACCION DEL BOTON PARA LA ACTIVDAD 1 'IDENTIFICAR A LOS ANIMALES'*/
    private void JBIdentificarAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBIdentificarAnimalesActionPerformed
        reproductor.parar();
        new IdentificarALosAnimales(sesion);
        dispose();
    }//GEN-LAST:event_JBIdentificarAnimalesActionPerformed

    /*ESTE METODO ES LA ACCION DEL BOTON PARA LA ACTIVDAD 1 'ENCONTRAR A LOS ANIMALES'*/
    private void JBEncontrarALosAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBEncontrarALosAnimalesActionPerformed
        reproductor.parar();
        new EncontrarALosAnimales(sesion);
        dispose();
    }//GEN-LAST:event_JBEncontrarALosAnimalesActionPerformed

    private Reproductor reproductor;
    private Sesion sesion;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBConocerAnimales;
    private javax.swing.JButton JBEncontrarALosAnimales;
    private javax.swing.JButton JBIdentificarAnimales;
    private javax.swing.JButton JBRegresar;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLTitulo;
    // End of variables declaration//GEN-END:variables
}
