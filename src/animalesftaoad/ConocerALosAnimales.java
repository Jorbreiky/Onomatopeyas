package animalesftaoad;

import bd.Conexion;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import reproductor.Reproductor;
import utilerias.Calculos;
import utilerias.DiseñoResponsivo;
import utilerias.Sesion;

/*SE CREA UN FORMULARIO PARA LA ACTIVIDAD 1 CONOCER A LOS ANIMALES*/
public class ConocerALosAnimales extends javax.swing.JFrame {

    /*EN EL CONTRUCTOR RECIBE COMO PARAMETRO LA SESION LA CUAL CONTIENE LOS DATOS DEL
    BENEFICIARIO, LOS ACIERTOS Y LOS INTENTOS*/
    public ConocerALosAnimales(Sesion sesion) {
        try{
            initComponents();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        this.sesion = sesion;//SE ASIGNA LA SESION PARA PODER UTILIZARLA EN TODO EL FORMULARIO
        setExtendedState(MAXIMIZED_BOTH);//MAXIMIZAMOS EL FORMULARIO EN TODA LA PANTALLA
        reproductor = new Reproductor("");//SE INICIALIZA EL REPRODUCTOR
        iniciarDiseño();//SE EJECUTA EL METODO INICIAR DISEÑO
        setVisible(true);//SE VIZUALIZA EL FORMULARIO EN LA PANTALLA
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        try{
            /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
            DE LA CLASE DISEÑO RESPONSIVO*/
            new DiseñoResponsivo().componente(JLNombreAnimal);
            new DiseñoResponsivo().componente(JBAnterior);
            new DiseñoResponsivo().componente(JBRegresar);
            new DiseñoResponsivo().componente(JBSiguiente);
            new DiseñoResponsivo().componente(JLAnimacion);
            new DiseñoResponsivo().componente(JLFondo);
            new DiseñoResponsivo().componente(JLLogo);
            new DiseñoResponsivo().componente(JLTitulo);
            JLTitulo.setForeground(Color.red);//SE ASIGNA EL COLOR DE FUENTE DEL TITULO
            JLNombreAnimal.setForeground(Color.CYAN);//SE ASIGNA EL COLOR DE FUENTE DEL NOMBRE DEL ANIMAL
            JLTitulo.setText("CONOCER A LOS ANIMALES");//SE ASIGNA EL TEXTO DEL TITULO 
            JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());//SE ASIGNA LA FUENTE AL TITULO
            JLNombreAnimal.setFont(new DiseñoResponsivo().getFontTexto(70));//SE ASIGNA LA FUENTE DESDE LA CLASE DISEÑO RESPONSIVO
            order = new Calculos().obtenerAleatorios(0,new Calculos().animalesCount());//SE OBTIENEN NUMEROS ALEATORIOS PARA LOS ANIMALES
            JBRegresar.setText("");
            posicion = 0;//SE INICIALIZA LA POSICION DONDE COMENZARA A MOSTRARSE EL ANIMAL EN LA PANTALLA
            animales = new Conexion().getAnimales();//SE OBTIENE LOS DATOS DE LOS ANIMALES REGISTRADOS EN LA BASE DE DATOS
            String[] animal = (String[]) animales.get(Integer.parseInt(order.get(posicion).toString()));//SE OBTIENE LOS ANIMALES ORDENADOS
                               
            //SE OBTIENEN LOS DATOS DEL ANIMAL 
            nombre = animal[0];//NOMBRE DEL ANIMAL
            sonidoNombre = animal[1];//RUTA DEL AUDIO DEL NOMBRE DEL ANIMAL
            sonidoAnimal = animal[2];//RUTA DEL AUDIO DEL SONIDO DEL ANIMAL
            animacion = animal[3];//RUTA DE LA ANIMACION GIF DEL ANIMAL
            tiempo = Double.parseDouble(animal[5]);//TIEMPO QUE DURA EL AUDIO DEL SONIDO DEL ANIMAL
            new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion,new ImageIcon(animacion.replace(".gif","2.gif")));//SE ASIGNA LA IMAGEN AL JLABEL
            JLNombreAnimal.setText(nombre);//DE DEFINE EL TEXTO DEL LABEL DEL NOMBRE DEL ANIMAL
            //SE ASIGNAN LOS ICONOS A CADA UNO DE LOS BOTONES RENDERIZADOS CON LA CLASE DISEÑO RESPONSIVO
            new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
            new DiseñoResponsivo().adaptarImagenComponente(JBRegresar,new ImageIcon(getClass().getResource("/img/regresar.png")));
            new DiseñoResponsivo().adaptarImagenComponente(JBAnterior,new ImageIcon(getClass().getResource("/img/flechaIzquierda.png")));
            new DiseñoResponsivo().adaptarImagenComponente(JBSiguiente,new ImageIcon(getClass().getResource("/img/flechaDerecha.png")));
            new DiseñoResponsivo().adaptarImagenComponente(JLFondo,new ImageIcon(getClass().getResource("/img/fondo.jpg")));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: "+e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBSiguiente = new javax.swing.JButton();
        JBAnterior = new javax.swing.JButton();
        JLNombreAnimal = new javax.swing.JLabel();
        JLLogo = new javax.swing.JLabel();
        JLAnimacion = new javax.swing.JLabel();
        JLTitulo = new javax.swing.JLabel();
        JBRegresar = new javax.swing.JButton();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 255));
        getContentPane().setLayout(null);

        JBSiguiente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JBSiguiente.setText(">");
        JBSiguiente.setBorder(null);
        JBSiguiente.setContentAreaFilled(false);
        JBSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(JBSiguiente);
        JBSiguiente.setBounds(1220, 330, 110, 80);

        JBAnterior.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JBAnterior.setText("<");
        JBAnterior.setBorder(null);
        JBAnterior.setContentAreaFilled(false);
        JBAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAnteriorActionPerformed(evt);
            }
        });
        getContentPane().add(JBAnterior);
        JBAnterior.setBounds(60, 330, 110, 80);

        JLNombreAnimal.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        JLNombreAnimal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLNombreAnimal.setText("NOMBRE");
        JLNombreAnimal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLNombreAnimalMouseClicked(evt);
            }
        });
        getContentPane().add(JLNombreAnimal);
        JLNombreAnimal.setBounds(240, 590, 900, 60);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(10, 10, 170, 140);

        JLAnimacion.setText("IMAGEN PRINCIPAL");
        JLAnimacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLAnimacionMouseClicked(evt);
            }
        });
        getContentPane().add(JLAnimacion);
        JLAnimacion.setBounds(190, 100, 1010, 550);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("CONOCER A LOS ANIMALES");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(250, 10, 900, 80);

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
        JLFondo.setBounds(0, 0, 1450, 810);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JLAnimacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLAnimacionMouseClicked
        /*LA CLASE HILO NOS AYUDA A REPRODUCIR LA ANIMACION CON SUS SONIDOS SIN QUE SE DETENGA LA APLICACION*/
        class Hilo extends Thread{
            @Override
            public void run(){
                try{
                    for(int i=0; i<1; i++){
                        reproductor.play(sonidoAnimal);//SE REPRODUCE EL SONIDO DEL ANIMAL
                        //SE ADAPTA LA IMAGEN DE LA ANIMACION GIF AL JLABEL
                        new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion,new ImageIcon(animacion));
                        Thread.sleep((long) tiempo*1000);//SE DETIENE EL TIEMPO PARA REPRODUCIR EL SONIDO DEL ANIMAL
                    }
                    //AL TERMINAR SE ASIGNA LA IMAGEN SIN ANIMACION DEL ANIMAL
                    new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion,new ImageIcon(animacion.replace(".gif", "2.gif")));
                }catch(Exception e){
                    
                }
            }
        } 
        new Hilo().start();//SE EJECUTA LA CLASE HILO
    }//GEN-LAST:event_JLAnimacionMouseClicked

    /*ESTE METODO ES LA ACCION DEL BOTON 'ANTERIOR' PARA CAMBIAR DE ANIMAL EN PANTALLA*/
    private void JBAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAnteriorActionPerformed
        if(posicion==0){
            posicion=order.size()-1;
        }else{
            posicion --;
        }
        String[] animal = (String[]) animales.get(Integer.parseInt(order.get(posicion).toString()));
        nombre = animal[0];
        sonidoNombre = animal[1];
        sonidoAnimal = animal[2];
        animacion = animal[3];
        tiempo = Double.parseDouble(animal[5]);
        new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion,new ImageIcon(animacion.replace(".gif","2.gif")));
        JLNombreAnimal.setText(nombre);
    }//GEN-LAST:event_JBAnteriorActionPerformed

    /*ESTE METODO ES LA ACCION AL DAR CLIC EN EL JLABEL DEL NOMBRE DEL ANIMAL*/
    private void JLNombreAnimalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLNombreAnimalMouseClicked
        new Reproductor().play(sonidoNombre);//SE REPRODUCE EL SONIDO DEL NOMBRE
    }//GEN-LAST:event_JLNombreAnimalMouseClicked

    /*ESTE METODO ES LA ACCION DEL BOTON 'SIGUIENTE' PARA CAMBIAR DE ANIMAL EN PANTALLA*/
    private void JBSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSiguienteActionPerformed
        if(posicion==order.size()-1){
            posicion=0;
        }else{
            posicion ++;
        }
        String[] animal = (String[]) animales.get(Integer.parseInt(order.get(posicion).toString()));
        nombre = animal[0];
        sonidoNombre = animal[1];
        sonidoAnimal = animal[2];
        animacion = animal[3];
        tiempo = Double.parseDouble(animal[5]);
        new DiseñoResponsivo().adaptarImagenComponente(JLAnimacion,new ImageIcon(animacion.replace(".gif","2.gif")));
        JLNombreAnimal.setText(nombre);
    }//GEN-LAST:event_JBSiguienteActionPerformed

    private void JLFondoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_JLFondoAncestorAdded
       
    }//GEN-LAST:event_JLFondoAncestorAdded

    /*ESTE METODO ES LA ACCION DEL BOTON 'REGRESAR EL CUAL NOS ENVIA AL MENU PRINCIPAL'*/
    private void JBRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegresarActionPerformed
        reproductor.parar();
        new MenuPrincipal(sesion);
        dispose();
    }//GEN-LAST:event_JBRegresarActionPerformed

    private Reproductor reproductor;//SE DECLARA EL REPRODUCTOR DE SONIDOS
    private List animales;//SE DECLARA LA LISTA QUE CONTENDRA LA INFORMACION DE LOS ANIMALES
    private int posicion;
    private List order;//SE DECLARA LA LISTA LA CUAL SE CREARA ORDENADA A PARTIR DE LA LISTA 'animales'
    //VARIABLES PARA UTILIZAR LOS DATOS DE UNA ANIMAL EN ESPECIFICO
    private String nombre;
    private String sonidoNombre;
    private String sonidoAnimal;
    private String animacion;
    private double tiempo;
    
    private Sesion sesion;//SE DECLARA LA SESION PARA PODERLA MANEJAR Y DEVOLVER EN ESTE FORMULARIO
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAnterior;
    private javax.swing.JButton JBRegresar;
    private javax.swing.JButton JBSiguiente;
    private javax.swing.JLabel JLAnimacion;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLNombreAnimal;
    private javax.swing.JLabel JLTitulo;
    // End of variables declaration//GEN-END:variables
}
