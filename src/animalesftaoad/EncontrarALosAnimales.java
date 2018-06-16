package animalesftaoad;

import bd.Conexion;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import reproductor.Reproductor;
import utilerias.Calculos;
import utilerias.DiseñoResponsivo;
import utilerias.JLGlobo;
import utilerias.Sesion;

/*FORMULARIO PARA LA ACTIVIDAD 2 ENCONTRAR A LOS ANIMALES*/
public class EncontrarALosAnimales extends javax.swing.JFrame {

    /*EN EL CONSTRUCTOR SE RECIBE COMO PARAMENTRO LA SESION QUE CONTIENE LOS DATOS DEL BENEFICIARIO
    CON LOS ACIERTOS E INTENTOS DE LAS ACTIVIDADES REALIZADAS EN ESTA SESION*/
    public EncontrarALosAnimales(Sesion sesion) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA EL FORMULARIO EN TODA LA PANTALLA
        this.sesion = sesion;//SE INICIALIZA LA SESION PARA SER UTILIZADA EN TODA LA CLASE
        iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
        iniciarAnimaciones();//SE INICIALIZAN LAS ANIMACIONES A MOSTRAR EN LA ACTIVIDAD
        entro = false;
        numGlobos = 15;//NUMERO DE GLOBOS A APARECER AL FELICITAR AL BENEFICIARIO POR CADA LOGRO
        reproductor = new Reproductor("");//SE INICIALIZA EL REPRODUCTOR
        new Reproductor().reproducirWAV("arrastralaimagen",3000);//SE REPRODUCE EL AUDIO DE LA INDICACION PARA ESTA ACTIVIDAD
        setVisible(true);//SE VIZUALIZA EL FORMULARIO EN PANTALLA
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
            DE LA CLASE DISEÑO RESPONSIVO*/
        new DiseñoResponsivo().componente(JBRegresar);
        new DiseñoResponsivo().componente(JLAnimacion1);
        new DiseñoResponsivo().componente(JLAnimacion2);
        new DiseñoResponsivo().componente(JLAnimacion3);
        new DiseñoResponsivo().componente(JLAnimacionPrincipal);
        new DiseñoResponsivo().componente(JLFondo);
        new DiseñoResponsivo().componente(JLIndicador1);
        new DiseñoResponsivo().componente(JLIndicador2);
        new DiseñoResponsivo().componente(JLLogo);
        new DiseñoResponsivo().componente(JLNombrePrincipal);
        new DiseñoResponsivo().componente(JLTitulo);
        
        /*ASIGNA LA FUENTE DE LA CLASE DISEÑO RESPONSIVO A CADA UNO DE LOS LABEL E INDICADORES DEL
        FORMULARIO*/
        JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());
        JLIndicador1.setFont(new DiseñoResponsivo().getFontIndicador(2));
        JLIndicador2.setFont(new DiseñoResponsivo().getFontIndicador(2));
        JLNombrePrincipal.setFont(new DiseñoResponsivo().getFontNombreAnimal());
        /*SE ASIGNA EL COLOR DE LA FUENTE DE LOS LABEL DEL FORMULARIO*/
        JLTitulo.setForeground(Color.red);
        JLIndicador1.setForeground(Color.red);
        JLIndicador2.setForeground(Color.red);
        JLNombrePrincipal.setForeground(Color.red);
        /*SE ASIGNA LOS ICONOS A LOS BOTONES DEL FORMULARIO*/
        new DiseñoResponsivo().adaptarImagenComponente(JBRegresar,new ImageIcon(getClass().getResource("/img/regresar.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JLFondo,new ImageIcon(getClass().getResource("/img/fondo.jpg")));
    }
    
    /*SE CREAN LAS LISTAS LOS DATOS DE LOS ANIMALES CONTENIDOS EN LA BASE DE DATOS
    ASI COMO ORDENANDOLOS DE FORMA ALEATORIA PARA SU VIZUALISACION EN LA ACTIVIDAD*/
    private void iniciarAnimaciones(){
        try{
            seleccionado = Integer.parseInt(new Calculos().obtenerAleatorios(0,2).get(0).toString());//SE OBTIENE EL ANIMAL A MOSTRAR COMO PRINCIPAL
            orden = new Calculos().obtenerAleatorios(0,new Calculos().animalesCount());//SE OBTIENE EL ORDEN DE LOS ANIMALES ALEATORIAMENTE
            animales = new Conexion().getAnimales();//SE OBTIENEN LOS DATOS DE LOS ANIMALES DE LA BASE DE DATOS
            animal1 = (String[])animales.get(Integer.parseInt(orden.get(0).toString()));//SE ASIGNA EL ANIMAL 1
            animal2 = (String[])animales.get(Integer.parseInt(orden.get(1).toString()));//SE ASIGNA EL ANIMAL 2
            animal3 = (String[])animales.get(Integer.parseInt(orden.get(2).toString()));//SE ASIGNA EL ANIMAL 3
            /*SE ASIGNAN LAS ANIMACIONES ESTATICAS A CADA LABEL DEL FORMULARIO PARA LA ACTIVIDAD*/
            new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion1,new ImageIcon(animal1[3].replace(".gif","2.gif")));
            new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion2,new ImageIcon(animal2[3].replace(".gif","2.gif")));
            new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion3,new ImageIcon(animal3[3].replace(".gif","2.gif")));
            /*SE INICIALIZAN LOS INDICADORES DE LOS INTENTOS Y LOS ACIERTOS */
            JLIndicador1.setText("Intentos: "+sesion.getIntentos());
            JLIndicador2.setText("Aciertos: "+sesion.getAciertos());
            /*SE ASIGNA CUAL SERA EL ANIMAL SELECCIONADO PARA MOSTRARSE COMO PRINCIPAL*/
            switch(seleccionado){
                case 0:
                    animalSeleccionado = animal1;
                    break;
                case 1:
                    animalSeleccionado = animal2;
                    break;
                case 2:
                    animalSeleccionado = animal3;
                    break;
            }
            /*SE ASIGNA LA IMAGEN DEL ANIMAL PRINCIPAL AL JLABEL PRINCIPAL*/
            new DiseñoResponsivo().adaptarImagenComponente(JLAnimacionPrincipal,new ImageIcon(animalSeleccionado[3].replace(".gif","2.gif")));
            JLNombrePrincipal.setText(animalSeleccionado[0]);//SE ASIGNA EL NOMBRE DEL ANIMAL SELECCIONADO
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: "+e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLLogo = new javax.swing.JLabel();
        JLTitulo = new javax.swing.JLabel();
        JBRegresar = new javax.swing.JButton();
        JLAnimacion3 = new javax.swing.JLabel();
        JLAnimacion1 = new javax.swing.JLabel();
        JLAnimacion2 = new javax.swing.JLabel();
        JLIndicador2 = new javax.swing.JLabel();
        JLIndicador1 = new javax.swing.JLabel();
        JLNombrePrincipal = new javax.swing.JLabel();
        JLAnimacionPrincipal = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 255));
        getContentPane().setLayout(null);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(20, 20, 170, 140);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("ENCONTRAR A LOS ANIMALES");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(250, 10, 1020, 80);

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

        JLAnimacion3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/4.jpg"))); // NOI18N
        JLAnimacion3.setText("jLabel1");
        JLAnimacion3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                JLAnimacion3MouseDragged(evt);
            }
        });
        JLAnimacion3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JLAnimacion3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JLAnimacion3MouseReleased(evt);
            }
        });
        getContentPane().add(JLAnimacion3);
        JLAnimacion3.setBounds(880, 440, 260, 140);

        JLAnimacion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/4.jpg"))); // NOI18N
        JLAnimacion1.setText("jLabel1");
        JLAnimacion1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                JLAnimacion1MouseDragged(evt);
            }
        });
        JLAnimacion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JLAnimacion1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JLAnimacion1MouseReleased(evt);
            }
        });
        getContentPane().add(JLAnimacion1);
        JLAnimacion1.setBounds(880, 120, 260, 140);

        JLAnimacion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/4.jpg"))); // NOI18N
        JLAnimacion2.setText("jLabel1");
        JLAnimacion2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                JLAnimacion2MouseDragged(evt);
            }
        });
        JLAnimacion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JLAnimacion2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JLAnimacion2MouseReleased(evt);
            }
        });
        getContentPane().add(JLAnimacion2);
        JLAnimacion2.setBounds(880, 280, 260, 140);

        JLIndicador2.setText("Aciertos: 8");
        getContentPane().add(JLIndicador2);
        JLIndicador2.setBounds(880, 640, 310, 50);

        JLIndicador1.setText("Intentos: 10");
        getContentPane().add(JLIndicador1);
        JLIndicador1.setBounds(880, 590, 310, 50);

        JLNombrePrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLNombrePrincipal.setText("NOMBRE");
        getContentPane().add(JLNombrePrincipal);
        JLNombrePrincipal.setBounds(140, 550, 430, 40);

        JLAnimacionPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/4.jpg"))); // NOI18N
        JLAnimacionPrincipal.setText("jLabel1");
        JLAnimacionPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JLAnimacionPrincipalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLAnimacionPrincipalMouseExited(evt);
            }
        });
        getContentPane().add(JLAnimacionPrincipal);
        JLAnimacionPrincipal.setBounds(50, 180, 650, 420);

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
        JLFondo.setBounds(0, 0, 1460, 820);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*EVENTO DEL BOTON REGRESAR AL MENU PRINCIPAL*/
    private void JBRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegresarActionPerformed
        reproductor.parar();//DETIENE EL AUDIO
        new MenuPrincipal(sesion);//SE MUESTRA LA VENTANA DEL MENU PRINCIPAL
        dispose();//SE OCULTA LA VENTANA DE LA ACTIVIDAD 2
    }//GEN-LAST:event_JBRegresarActionPerformed

    /*AL DAR CLIC EN EL LABEL DEL ANIMAL 1*/
    private void JLAnimacion1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion1MousePressed
        entro = false;
        mouse=MouseInfo.getPointerInfo().getLocation();//SE OBTIENE LA POSICION DEL PUNTERO
        posicionInicial = JLAnimacion1.getLocation();//SE INICIALIZA LA POCICION DEL JLABEL1
        //SE OBTIENE LA DIFERENCIA DE POSICION DEL PUNTERO
        x = mouse.x - JLAnimacion1.getX();
        y = mouse.y - JLAnimacion1.getY();
    }//GEN-LAST:event_JLAnimacion1MousePressed

    private void JLAnimacion2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion2MousePressed
        entro = false;
        mouse=MouseInfo.getPointerInfo().getLocation();//SE OBTIENE LA POSICION DEL PUNTERO
        posicionInicial = JLAnimacion2.getLocation();//SE INICIALIZA LA POCICION DEL JLABEL2
        //SE OBTIENE LA DIFERENCIA DE POSICION DEL PUNTERO
        x = mouse.x - JLAnimacion2.getX();
        y = mouse.y - JLAnimacion2.getY();
    }//GEN-LAST:event_JLAnimacion2MousePressed

    private void JLAnimacion3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion3MousePressed
        entro = false;
        mouse=MouseInfo.getPointerInfo().getLocation();//SE OBTIENE LA POSICION DEL PUNTERO
        posicionInicial = JLAnimacion3.getLocation();//SE INICIALIZA LA POCICION DEL JLABEL3
        //SE OBTIENE LA DIFERENCIA DE POSICION DEL PUNTERO
        x = mouse.x - JLAnimacion3.getX();
        y = mouse.y - JLAnimacion3.getY();
    }//GEN-LAST:event_JLAnimacion3MousePressed

    /*EVENTO DEL JLABEL1 AL ARRASTRAR CON EL MOUSE*/
    private void JLAnimacion1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion1MouseDragged
        mouse=MouseInfo.getPointerInfo().getLocation();//SE OBTIENE LA POSICION ACTUAL DEL MOUSE
        /*SE MUEVE EL JLABEL1 CON EL MOUSE RE-POSICIONANDO EL JLABEL1*/
        JLAnimacion1.setBounds(mouse.x - x,mouse.y - y,JLAnimacion1.getWidth(),JLAnimacion1.getHeight());
        /*EN CASO QUE AL MOMENTO DE ARRASTRAR LA IMAGEN ENTRA AL AREA DEL JLABEL PRINCIPAL Y CAMBIA EL VALOR 
        DE LA VARIABLE ENTRO*/
        if(mouse.x>JLAnimacionPrincipal.getX()
        &&mouse.x<JLAnimacionPrincipal.getX()+JLAnimacionPrincipal.getWidth()
        &&mouse.y>JLAnimacionPrincipal.getY()
        &&mouse.y<JLAnimacionPrincipal.getY()+JLAnimacionPrincipal.getHeight()){
            entro = true;
        }else{
            entro = false;
        }
    }//GEN-LAST:event_JLAnimacion1MouseDragged

    private void JLAnimacion2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion2MouseDragged
        mouse=MouseInfo.getPointerInfo().getLocation();//SE OBTIENE LA POSICION ACTUAL DEL MOUSE
        /*SE MUEVE EL JLABEL1 CON EL MOUSE RE-POSICIONANDO EL JLABEL1*/
        JLAnimacion2.setBounds(mouse.x - x,mouse.y - y,JLAnimacion2.getWidth(),JLAnimacion2.getHeight());
        /*EN CASO QUE AL MOMENTO DE ARRASTRAR LA IMAGEN ENTRA AL AREA DEL JLABEL PRINCIPAL Y CAMBIA EL VALOR 
        DE LA VARIABLE ENTRO*/
        if(mouse.x>JLAnimacionPrincipal.getX()
        &&mouse.x<JLAnimacionPrincipal.getX()+JLAnimacionPrincipal.getWidth()
        &&mouse.y>JLAnimacionPrincipal.getY()
        &&mouse.y<JLAnimacionPrincipal.getY()+JLAnimacionPrincipal.getHeight()){
            entro = true;
        }else{
            entro = false;
        }
    }//GEN-LAST:event_JLAnimacion2MouseDragged

    private void JLAnimacion3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion3MouseDragged
        mouse=MouseInfo.getPointerInfo().getLocation();//SE OBTIENE LA POSICION ACTUAL DEL MOUSE
        /*SE MUEVE EL JLABEL1 CON EL MOUSE RE-POSICIONANDO EL JLABEL1*/
        JLAnimacion3.setBounds(mouse.x - x,mouse.y - y,JLAnimacion3.getWidth(),JLAnimacion3.getHeight());
        /*EN CASO QUE AL MOMENTO DE ARRASTRAR LA IMAGEN ENTRA AL AREA DEL JLABEL PRINCIPAL Y CAMBIA EL VALOR 
        DE LA VARIABLE ENTRO*/
        if(mouse.x>JLAnimacionPrincipal.getX()
        &&mouse.x<JLAnimacionPrincipal.getX()+JLAnimacionPrincipal.getWidth()
        &&mouse.y>JLAnimacionPrincipal.getY()
        &&mouse.y<JLAnimacionPrincipal.getY()+JLAnimacionPrincipal.getHeight()){
            entro = true;
        }else{
            entro = false;
        }
    }//GEN-LAST:event_JLAnimacion3MouseDragged

    private void JLAnimacion1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion1MouseReleased
        if(entro){
            /*SE COMPARA EL NOMBRE DEL ANIMAL1 CON EL ANIMAL SELECCIONADO*/
            if(animal1[0].equals(animalSeleccionado[0])){
                sesion.aumentarAciertos();//AUMENTA LOS ACIERTOS EN LA SESION DEL BENEFICIARIO
                new Reproductor().reproducirWAV("muybien",2000);//SE REPRODUCE EL SONIDO DE FELICITACIONES
                /*SE VISUALIZAN LOS GLOBOS EN LA PANTALLA*/
                for(int i=0; i<numGlobos; i++){
                    new JLGlobo(this,1);
                    new JLGlobo(this,2);
                    new JLGlobo(this,3);
                    new JLGlobo(this,4);
                    new JLGlobo(this,5);
                }
                /*LA CLASE HILO ES PARA REPRODUCIR LA ANIMACION DEL ANIMAL SELECCIONADO*/
                class Hilo extends Thread{
                    @Override
                    public void run(){
                        try{                            
                            for(int i=0; i<1; i++){
                                reproductor.play(animalSeleccionado[2]);
                                new DiseñoResponsivo().adaptarImagenComponente(JLAnimacionPrincipal,new ImageIcon(animalSeleccionado[3]));
                                Thread.sleep((long)Double.parseDouble(animalSeleccionado[5])*1000);
                            }
                            new DiseñoResponsivo().adaptarImagenComponente(JLAnimacionPrincipal,new ImageIcon(animalSeleccionado[3].replace(".gif", "2.gif")));
                            Thread.sleep(1000);
                            /*AL TERMINAR DE REPRODUCIR LA ANIMACION SE INICIALIZAN NUEVAMENTE LOS ANIMALES A MOSTRAR EN LA PANTALLA*/
                            iniciarAnimaciones();
                        }catch(Exception e){

                        }
                    }
                } 
                new Hilo().start();// SE INICIA LA ANIMACION PRINCIPAL
                JLAnimacion1.setLocation(posicionInicial);//SE REGRESA EL JLABEL1 A SU POSICION INICIAL
                
            }else{
                /*SI NO ES EL ANIMAL CORRECTO SE REPRODUCE EL AUDIO DE INTENTAR DE NUEVO*/
                new Reproductor().reproducirWAV("intentalodenuevo",2000);
                JLAnimacion1.setLocation(posicionInicial);//SE REGRESA EL JLABEL1 A SU POSICION INICIAL
                sesion.aumentarIntentos();//SE AUMENTAN LOS INTENTOS EN LA SESION DEL BENEFICIARIO
            }
        }else{
            JLAnimacion1.setLocation(posicionInicial);//SE REGRESA EL JLABEL1 A SU POSICION INICIAL
        }
        JLIndicador1.setText("Intentos: "+sesion.getIntentos());
        JLIndicador2.setText("Aciertos: "+sesion.getAciertos());
    }//GEN-LAST:event_JLAnimacion1MouseReleased

    private void JLAnimacion2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion2MouseReleased
        if(entro){
            /*SE COMPARA EL NOMBRE DEL ANIMAL1 CON EL ANIMAL SELECCIONADO*/
            if(animal2[0].equals(animalSeleccionado[0])){
                sesion.aumentarAciertos();//AUMENTA LOS ACIERTOS EN LA SESION DEL BENEFICIARIO
                new Reproductor().reproducirWAV("muybien",2000);//SE REPRODUCE EL SONIDO DE FELICITACIONES
                /*SE VISUALIZAN LOS GLOBOS EN LA PANTALLA*/
                for(int i=0; i<numGlobos; i++){
                    new JLGlobo(this,1);
                    new JLGlobo(this,2);
                    new JLGlobo(this,3);
                    new JLGlobo(this,4);
                    new JLGlobo(this,5);
                }
                /*LA CLASE HILO ES PARA REPRODUCIR LA ANIMACION DEL ANIMAL SELECCIONADO*/
                class Hilo extends Thread{
                    @Override
                    public void run(){
                        try{
                            for(int i=0; i<1; i++){
                                reproductor.play(animalSeleccionado[2]);
                                new DiseñoResponsivo().adaptarImagenComponente(JLAnimacionPrincipal,new ImageIcon(animalSeleccionado[3]));
                                Thread.sleep((long)Double.parseDouble(animalSeleccionado[5])*1000);
                            }
                            new DiseñoResponsivo().adaptarImagenComponente(JLAnimacionPrincipal,new ImageIcon(animalSeleccionado[3].replace(".gif", "2.gif")));
                            Thread.sleep(1000);
                            /*AL TERMINAR DE REPRODUCIR LA ANIMACION SE INICIALIZAN NUEVAMENTE LOS ANIMALES A MOSTRAR EN LA PANTALLA*/
                            iniciarAnimaciones();
                        }catch(Exception e){

                        }
                    }
                } 
                new Hilo().start();// SE INICIA LA ANIMACION PRINCIPAL
                JLAnimacion2.setLocation(posicionInicial);//SE REGRESA EL JLABEL1 A SU POSICION INICIAL
            }else{
                 /*SI NO ES EL ANIMAL CORRECTO SE REPRODUCE EL AUDIO DE INTENTAR DE NUEVO*/
                new Reproductor().reproducirWAV("intentalodenuevo",2000);
                sesion.aumentarIntentos();//SE AUMENTAN LOS INTENTOS EN LA SESION DEL BENEFICIARIO
                JLAnimacion2.setLocation(posicionInicial);//SE VUELVE A LA POSICION INICIAL DEL JLABEL
            }
        }else{
            JLAnimacion2.setLocation(posicionInicial);//SE REGRESA EL JLABEL1 A SU POSICION INICIAL
        }
        JLIndicador1.setText("Intentos: "+sesion.getIntentos());
        JLIndicador2.setText("Aciertos: "+sesion.getAciertos());
    }//GEN-LAST:event_JLAnimacion2MouseReleased

    private void JLAnimacion3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacion3MouseReleased
        if(entro){
            /*SE COMPARA EL NOMBRE DEL ANIMAL1 CON EL ANIMAL SELECCIONADO*/
            if(animal3[0].equals(animalSeleccionado[0])){
                sesion.aumentarAciertos();//AUMENTA LOS ACIERTOS EN LA SESION DEL BENEFICIARIO
                new Reproductor().reproducirWAV("muybien",2000);//SE REPRODUCE EL SONIDO DE FELICITACIONES
                /*SE VISUALIZAN LOS GLOBOS EN LA PANTALLA*/
                for(int i=0; i<numGlobos; i++){
                    new JLGlobo(this,1);
                    new JLGlobo(this,2);
                    new JLGlobo(this,3);
                    new JLGlobo(this,4);
                    new JLGlobo(this,5);
                }
                /*LA CLASE HILO ES PARA REPRODUCIR LA ANIMACION DEL ANIMAL SELECCIONADO*/
                class Hilo extends Thread{
                    @Override
                    public void run(){
                        try{
                            for(int i=0; i<1; i++){
                                reproductor.play(animalSeleccionado[2]);
                                new DiseñoResponsivo().adaptarImagenComponente(JLAnimacionPrincipal,new ImageIcon(animalSeleccionado[3]));
                                Thread.sleep((long)Double.parseDouble(animalSeleccionado[5])*1000);
                            }
                            new DiseñoResponsivo().adaptarImagenComponente(JLAnimacionPrincipal,new ImageIcon(animalSeleccionado[3].replace(".gif", "2.gif")));
                            Thread.sleep(1000);
                             /*AL TERMINAR DE REPRODUCIR LA ANIMACION SE INICIALIZAN NUEVAMENTE LOS ANIMALES A MOSTRAR EN LA PANTALLA*/
                            iniciarAnimaciones();
                        }catch(Exception e){

                        }
                    }
                } 
                new Hilo().start();// SE INICIA LA ANIMACION PRINCIPAL
                JLAnimacion3.setLocation(posicionInicial);//SE REGRESA EL JLABEL1 A SU POSICION INICIAL
            }else{
                /*SI NO ES EL ANIMAL CORRECTO SE REPRODUCE EL AUDIO DE INTENTAR DE NUEVO*/
                new Reproductor().reproducirWAV("intentalodenuevo",2000);
                sesion.aumentarIntentos();//SE AUMENTAN LOS INTENTOS EN LA SESION DEL BENEFICIARIO
                JLAnimacion3.setLocation(posicionInicial);//SE REGRESA EL JLABEL1 A SU POSICION INICIAL
            }
        }else{
            JLAnimacion3.setLocation(posicionInicial);//SE REGRESA EL JLABEL1 A SU POSICION INICIAL
        }
        JLIndicador1.setText("Intentos: "+sesion.getIntentos());
        JLIndicador2.setText("Aciertos: "+sesion.getAciertos());
    }//GEN-LAST:event_JLAnimacion3MouseReleased

    private void JLAnimacionPrincipalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacionPrincipalMouseEntered
        
    }//GEN-LAST:event_JLAnimacionPrincipalMouseEntered

    private void JLAnimacionPrincipalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacionPrincipalMouseExited
        
    }//GEN-LAST:event_JLAnimacionPrincipalMouseExited

    private void JLFondoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_JLFondoAncestorAdded

    }//GEN-LAST:event_JLFondoAncestorAdded

    private Sesion sesion;
    private Reproductor reproductor;
    private Point mouse;
    private Point posicionInicial;
    private int x,y,seleccionado,numGlobos;
    private List orden,animales;
    private String[] animal1,animal2,animal3,animalSeleccionado;
    private boolean entro;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBRegresar;
    private javax.swing.JLabel JLAnimacion1;
    private javax.swing.JLabel JLAnimacion2;
    private javax.swing.JLabel JLAnimacion3;
    private javax.swing.JLabel JLAnimacionPrincipal;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLIndicador1;
    private javax.swing.JLabel JLIndicador2;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLNombrePrincipal;
    private javax.swing.JLabel JLTitulo;
    // End of variables declaration//GEN-END:variables
}
