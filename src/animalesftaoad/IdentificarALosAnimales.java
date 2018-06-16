package animalesftaoad;

import bd.Conexion;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import reproductor.Reproductor;
import utilerias.Calculos;
import utilerias.DiseñoResponsivo;
import utilerias.JLGlobo;
import utilerias.Sesion;

/*FORMULARIO PARA LA ACTIVIDAD 3 IDENTIFICAR A LOS ANIMALES*/
public class IdentificarALosAnimales extends javax.swing.JFrame {

    /*EN EL CONSTRUCTOR SE RECIBE COMO PARAMENTRO LA SESION QUE CONTIENE LOS DATOS DEL BENEFICIARIO
    CON LOS ACIERTOS E INTENTOS DE LAS ACTIVIDADES REALIZADAS EN ESTA SESION*/
    public IdentificarALosAnimales(Sesion sesion) {
        initComponents();
        this.sesion = sesion;//SE INICIALIZA LA SESION PARA SER UTILIZADA EN TODA LA CLASE
        iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
        iniciarAnimaciones();//SE INICIALIZAN LAS ANIMACIONES A MOSTRAR EN LA ACTIVIDAD
        setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA EL FORMULARIO EN TODA LA PANTALLA       
        reproductor = new Reproductor("");//SE INICIALIZA EL REPRODUCTOR
        numGlobos = 15;//NUMERO DE GLOBOS A APARECER AL FELICITAR AL BENEFICIARIO POR CADA LOGRO
        setVisible(true);//SE VIZUALIZA EL FORMULARIO EN PANTALLA
    }
    
    /*SE CREAN LAS LISTAS LOS DATOS DE LOS ANIMALES CONTENIDOS EN LA BASE DE DATOS
    ASI COMO ORDENANDOLOS DE FORMA ALEATORIA PARA SU VIZUALISACION EN LA ACTIVIDAD*/
    private void iniciarAnimaciones(){
        
        class Hilo extends Thread{
            @Override
            public void run(){
                try{
                    if(animal1!=null){
                        switch(seleccionado){
                            case 0:
                                Thread.sleep((long)Double.parseDouble(animal1[5])*1000);
                                break;
                            case 1:
                                Thread.sleep((long)Double.parseDouble(animal2[5])*1000);
                                break;
                            case 2:
                                Thread.sleep((long)Double.parseDouble(animal3[5])*1000);
                                break;
                        } 
                    }
                    
                    seleccionado = Integer.parseInt(new Calculos().obtenerAleatorios(0,2).get(0).toString());
                    orden = new Calculos().obtenerAleatorios(0,new Calculos().animalesCount());
                    animales = new Conexion().getAnimales();
                    animal1 = (String[])animales.get(Integer.parseInt(orden.get(0).toString()));
                    animal2 = (String[])animales.get(Integer.parseInt(orden.get(1).toString()));
                    animal3 = (String[])animales.get(Integer.parseInt(orden.get(2).toString()));
                    new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion1,new ImageIcon(animal1[3].replace(".gif","2.gif")));
                    new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion2,new ImageIcon(animal2[3].replace(".gif","2.gif")));
                    new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion3,new ImageIcon(animal3[3].replace(".gif","2.gif")));
                    JLIndicador1.setText("Intentos: "+sesion.getIntentos());
                    JLIndicador2.setText("Aciertos: "+sesion.getAciertos());
                    JLNombre1.setText(animal1[0]);
                    JLNombre2.setText(animal2[0]);
                    JLNombre3.setText(animal3[0]);
                    System.out.println(seleccionado);
                    switch(seleccionado){
                        case 0:
                            indicacion = animal1[4];
                            break;
                        case 1:
                            indicacion = animal2[4];
                            break;
                        case 2:
                            indicacion = animal3[4];
                            break;
                    }
                    if(indicacion!=null){
                        Thread.sleep(1000);
                        new Reproductor().play(indicacion);
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Error en las animaciones: "+e);
                }
            }
        }
        new Hilo().start();
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        try{
            /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
            DE LA CLASE DISEÑO RESPONSIVO*/
            new DiseñoResponsivo().componente(JBRegresar);
            new DiseñoResponsivo().componente(JLAnimacion1);
            new DiseñoResponsivo().componente(JLAnimacion2);
            new DiseñoResponsivo().componente(JLAnimacion3);
            new DiseñoResponsivo().componente(JLFondo);
            new DiseñoResponsivo().componente(JLIndicacion);
            new DiseñoResponsivo().componente(JLIndicador1);
            new DiseñoResponsivo().componente(JLIndicador2);
            new DiseñoResponsivo().componente(JLLogo);
            new DiseñoResponsivo().componente(JLNombre1);
            new DiseñoResponsivo().componente(JLNombre2);
            new DiseñoResponsivo().componente(JLNombre3);
            new DiseñoResponsivo().componente(JLTitulo);
            
            /*SE DEFINIEN LAS FUENTES A LOS LABEL DEL FORMULARIO*/
            JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());
            JLNombre1.setFont(new DiseñoResponsivo().getFontNombreAnimal());
            JLNombre2.setFont(new DiseñoResponsivo().getFontNombreAnimal());
            JLNombre3.setFont(new DiseñoResponsivo().getFontNombreAnimal());
            JLIndicacion.setFont(new DiseñoResponsivo().getFontIndicador(5));
            JLIndicador1.setFont(new DiseñoResponsivo().getFontIndicador(2));
            JLIndicador2.setFont(new DiseñoResponsivo().getFontIndicador(2));
            
            /*SE DEFINE EL COLOR DE LA FUENTE DE CADA LABEL DEL FORMULARIO*/
            JLTitulo.setForeground(Color.red);
            JLNombre1.setForeground(Color.BLUE);
            JLNombre2.setForeground(Color.BLUE);
            JLNombre3.setForeground(Color.BLUE);
            JLIndicacion.setForeground(Color.red);
            JLIndicador1.setForeground(Color.red);
            JLIndicador2.setForeground(Color.red);
            
            /*SE ASIGNAN LOS ICONOS DE LOS BOTONES*/
            new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
            new DiseñoResponsivo().adaptarImagenComponente(JBRegresar,new ImageIcon(getClass().getResource("/img/regresar.png")));
            new DiseñoResponsivo().adaptarImagenComponente(JLFondo,new ImageIcon(getClass().getResource("/img/fondo.jpg")));
            JBRegresar.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: \n"+e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLNombre3 = new javax.swing.JLabel();
        JLLogo = new javax.swing.JLabel();
        JLAnimacion3 = new javax.swing.JLabel();
        JLTitulo = new javax.swing.JLabel();
        JLAnimacion2 = new javax.swing.JLabel();
        JLIndicador2 = new javax.swing.JLabel();
        JLNombre2 = new javax.swing.JLabel();
        JLAnimacion1 = new javax.swing.JLabel();
        JLNombre1 = new javax.swing.JLabel();
        JBRegresar = new javax.swing.JButton();
        JLIndicacion = new javax.swing.JLabel();
        JLIndicador1 = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 255));
        getContentPane().setLayout(null);

        JLNombre3.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        JLNombre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLNombre3.setText("NOMBRE");
        JLNombre3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLNombre3MouseClicked(evt);
            }
        });
        getContentPane().add(JLNombre3);
        JLNombre3.setBounds(920, 510, 400, 60);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(30, 10, 170, 140);

        JLAnimacion3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        JLAnimacion3.setText("ANIMACION 1");
        JLAnimacion3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLAnimacion3MouseClicked(evt);
            }
        });
        getContentPane().add(JLAnimacion3);
        JLAnimacion3.setBounds(920, 170, 390, 330);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("IDENTIFICAR A LOS ANIMALES");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(210, 30, 1160, 100);

        JLAnimacion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        JLAnimacion2.setText("ANIMACION 1");
        JLAnimacion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLAnimacion2MouseClicked(evt);
            }
        });
        getContentPane().add(JLAnimacion2);
        JLAnimacion2.setBounds(470, 170, 390, 330);

        JLIndicador2.setText("8   |   10");
        getContentPane().add(JLIndicador2);
        JLIndicador2.setBounds(880, 640, 310, 50);

        JLNombre2.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        JLNombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLNombre2.setText("NOMBRE");
        JLNombre2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLNombre2MouseClicked(evt);
            }
        });
        getContentPane().add(JLNombre2);
        JLNombre2.setBounds(470, 510, 400, 60);

        JLAnimacion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        JLAnimacion1.setText("ANIMACION 1");
        JLAnimacion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLAnimacion1MouseClicked(evt);
            }
        });
        getContentPane().add(JLAnimacion1);
        JLAnimacion1.setBounds(30, 170, 390, 330);

        JLNombre1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        JLNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLNombre1.setText("NOMBRE");
        JLNombre1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLNombre1MouseClicked(evt);
            }
        });
        getContentPane().add(JLNombre1);
        JLNombre1.setBounds(30, 510, 400, 60);

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
        JBRegresar.setBounds(1220, 600, 100, 90);

        JLIndicacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JLIndicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sound-drawn-blue.png"))); // NOI18N
        JLIndicacion.setText("ENCUENTRA A ...");
        JLIndicacion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        JLIndicacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLIndicacionMouseClicked(evt);
            }
        });
        getContentPane().add(JLIndicacion);
        JLIndicacion.setBounds(130, 620, 580, 70);

        JLIndicador1.setText("Puntaje: 9/14");
        getContentPane().add(JLIndicador1);
        JLIndicador1.setBounds(880, 590, 310, 50);

        JLFondo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                JLFondoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(JLFondo);
        JLFondo.setBounds(-10, 0, 1470, 820);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*ESTE METODO ES LA ACCION AL DAR CLIC EN EL LABEL DEL NOMBRE DEL ANIMAL 3*/
    private void JLNombre3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLNombre3MouseClicked
        /*SI EL ANIMAL SELECCIONADO ES 2*/
        if(seleccionado==2){
            new Reproductor().reproducirWAV("muybien",2000);//SE REPRODUCE EL AUDIO DE FELICITACION
            sesion.aumentarAciertos();//SE AUMENTAN LOS ACIERTOS DE LA SESION
            //SE VISUALIZACION LOS GRLOBOS EN PANTALLA
            for(int i=0; i<numGlobos; i++){
                new JLGlobo(this,1);
                new JLGlobo(this,2);
                new JLGlobo(this,3);
                new JLGlobo(this,4);
                new JLGlobo(this,5);
            }
            JLAnimacion3MouseClicked(evt);//SE EJECTA EL EVENTO DE CLIC DEL LABEL3
            iniciarAnimaciones();//REINICIAN LAS ANIMACION A MOSTRAR LOS ANIMALES EN EL FORMULARION
        }else{
            /*SE REPRODUCE EL SONIDO DE INTENTARLO DE NUEVO*/
            new Reproductor().reproducirWAV("intentalodenuevo",2000);
            sesion.aumentarIntentos();//AUMENTAN LOS INTENTOS EN LA SESION
        }
        JLIndicador1.setText("Intentos: "+sesion.getIntentos());
        JLIndicador2.setText("Aciertos: "+sesion.getAciertos());
    }//GEN-LAST:event_JLNombre3MouseClicked

    private void JLFondoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_JLFondoAncestorAdded
        
    }//GEN-LAST:event_JLFondoAncestorAdded

    /*ESTE METODO ES LA ACCION AL DAR CLIC EN EL LABEL DEL NOMBRE DEL ANIMAL 2*/
    private void JLNombre2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLNombre2MouseClicked
        /*SI EL ANIMAL SELECCIONADO ES 1*/
        if(seleccionado==1){
            sesion.aumentarAciertos();//SE AUMENTAN LOS ACIERTOS DE LA SESION
            new Reproductor().reproducirWAV("muybien",2000);//SE REPRODUCE EL AUDIO DE FELICITACION
            //SE VISUALIZACION LOS GRLOBOS EN PANTALLA
            for(int i=0; i<numGlobos; i++){
                new JLGlobo(this,1);
                new JLGlobo(this,2);
                new JLGlobo(this,3);
                new JLGlobo(this,4);
                new JLGlobo(this,5);
            }
            JLAnimacion2MouseClicked(evt);//SE EJECTA EL EVENTO DE CLIC DEL LABEL2
            iniciarAnimaciones();//REINICIAN LAS ANIMACION A MOSTRAR LOS ANIMALES EN EL FORMULARION
        }else{
            /*SE REPRODUCE EL SONIDO DE INTENTARLO DE NUEVO*/
            new Reproductor().reproducirWAV("intentalodenuevo",2000);
            sesion.aumentarIntentos();//AUMENTAN LOS INTENTOS EN LA SESION
        }
        JLIndicador1.setText("Intentos: "+sesion.getIntentos());
        JLIndicador2.setText("Aciertos: "+sesion.getAciertos());
    }//GEN-LAST:event_JLNombre2MouseClicked

    private void JLNombre1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLNombre1MouseClicked
        /*SI EL ANIMAL SELECCIONADO ES 1*/
        if(seleccionado==0){
            sesion.aumentarAciertos();//SE AUMENTAN LOS ACIERTOS DE LA SESION
            new Reproductor().reproducirWAV("muybien",2000);//SE REPRODUCE EL AUDIO DE FELICITACION
            //SE VISUALIZACION LOS GRLOBOS EN PANTALLA
            for(int i=0; i<numGlobos; i++){
                new JLGlobo(this,1);
                new JLGlobo(this,2);
                new JLGlobo(this,3);
                new JLGlobo(this,4);
                new JLGlobo(this,5);
            }
            JLAnimacion1MouseClicked(evt);//SE EJECTA EL EVENTO DE CLIC DEL LABEL1
            iniciarAnimaciones();//REINICIAN LAS ANIMACION A MOSTRAR LOS ANIMALES EN EL FORMULARION
        }else{
            /*SE REPRODUCE EL SONIDO DE INTENTARLO DE NUEVO*/
            new Reproductor().reproducirWAV("intentalodenuevo",2000);
            sesion.aumentarIntentos();//AUMENTAN LOS INTENTOS EN LA SESION
        }
        JLIndicador1.setText("Intentos: "+sesion.getIntentos());
        JLIndicador2.setText("Aciertos: "+sesion.getAciertos());
    }//GEN-LAST:event_JLNombre1MouseClicked

    /*ESTE ES EL EVENTO DEL BOTON REGRESAR AL MENU PRINCIPAL*/
    private void JBRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegresarActionPerformed
        reproductor.parar();//SE DETIENE EL REPRODUCTOR
        new MenuPrincipal(sesion);//SE VISUALIZA EL MENU PRINCIPAL
        dispose();//SE OCULTA ESTE FORMULARIO
    }//GEN-LAST:event_JBRegresarActionPerformed

    /*ESTE METODO ES EL EVENTO AL DAR CLIC EN EL LABEL3
    SE REPROCUCE LA ANIMACION DEL ANIMAL3*/
    private void JLAnimacion3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion3MouseClicked
        
        class Hilo extends Thread{
            @Override
            public void run(){
                try{
                    reproductor.play(animal3[2]);
                    new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion3,new ImageIcon(animal3[3]));
                    Thread.sleep((long)Double.parseDouble(animal3[5])*1000);
                    new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion3,new ImageIcon(animal3[3].replace(".gif", "2.gif")));
                }catch(Exception e){
                    
                }
            }
        }
        new Hilo().start();
    }//GEN-LAST:event_JLAnimacion3MouseClicked

    /*ESTE METODO ES EL EVENTO AL DAR CLIC EN EL LABEL2
    SE REPROCUCE LA ANIMACION DEL ANIMAL2*/
    private void JLAnimacion2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion2MouseClicked

        class Hilo extends Thread{
            @Override
            public void run(){
                try{
                    for(int i=0; i<1; i++){
                        reproductor.play(animal2[2]);
                        new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion2,new ImageIcon(animal2[3]));
                        Thread.sleep((long)Double.parseDouble(animal2[5])*1000);
                    }
                    new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion2,new ImageIcon(animal2[3].replace(".gif", "2.gif")));
                }catch(Exception e){
                    
                }
            }
        }
        new Hilo().start();
    }//GEN-LAST:event_JLAnimacion2MouseClicked

    /*ESTE METODO ES EL EVENTO AL DAR CLIC EN EL LABEL1
    SE REPROCUCE LA ANIMACION DEL ANIMAL1*/
    private void JLAnimacion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion1MouseClicked

        class Hilo extends Thread{
            @Override
            public void run(){
                try{
                    for(int i=0; i<1; i++){
                        reproductor.play(animal1[2]);
                        new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion1,new ImageIcon(animal1[3]));
                        Thread.sleep((long)Double.parseDouble(animal1[5])*1000);
                    }
                    new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion1,new ImageIcon(animal1[3].replace(".gif", "2.gif")));
                }catch(Exception e){
                    
                }
            }
        }
        new Hilo().start();
    }//GEN-LAST:event_JLAnimacion1MouseClicked

    /*ESTE METODO ES EL EVENTO AL DAR CLIC EN EL LABEL DE INDICACION
    SE REPROCUCE EL AUDIO DE LA INDICACION*/
    private void JLIndicacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLIndicacionMouseClicked
        reproductor.play(indicacion);//REPRODUCE LA INDICACION DEL ANIMAL PRINCIPAL
    }//GEN-LAST:event_JLIndicacionMouseClicked

    private Reproductor reproductor;
    private List animales;//LISTA DE INFORMACION DE LOS ANIMALES DE LA BASE DE DATOS
    private List orden;//LISTA DE NUMEROS ORDENADOS PARA LA LISTA DE ANIMALES
    private int seleccionado;//POSICION DEL ANIMAL SELECCIONADO EN LA LISTA
    private int numGlobos;//NUMERO DE GLOBOS QUE APARECERAN AL FELICITAR AL BENEFICIARIO
    private String[] animal1;//INFORMACION DEL ANIMAL1
    private String[] animal2;//INFORMACION DEL ANIMAL2
    private String[] animal3;//INFORMACION DEL ANIMAL3
    private String indicacion;//RUTA DEL AUDIO DE INDICACION
    private Sesion sesion;//SESION DEL BENEFICIARIO QUE CONTIENE LOS ACIERTOS E INTENTOS
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBRegresar;
    private javax.swing.JLabel JLAnimacion1;
    private javax.swing.JLabel JLAnimacion2;
    private javax.swing.JLabel JLAnimacion3;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLIndicacion;
    private javax.swing.JLabel JLIndicador1;
    private javax.swing.JLabel JLIndicador2;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLNombre1;
    private javax.swing.JLabel JLNombre2;
    private javax.swing.JLabel JLNombre3;
    private javax.swing.JLabel JLTitulo;
    // End of variables declaration//GEN-END:variables
}
