package sudo;

import bd.Conexion;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import utilerias.DiseñoResponsivo;

/*CLASE QUE SE ENCARGA DE LA GESTION DE ANIMACIONES
PARA LA APLICACION*/
public class AdminAnimales extends javax.swing.JFrame {
    public AdminAnimales() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA LA VENTANA
        iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
        inicializarTabla();//SE AGREGA EL MODELO A LA TABLA CON SUS RESPECTIVAS COLUMNAS
        setVisible(true);//SE VISUALIZA LA VENTANA
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
        DE LA CLASE DISEÑO RESPONSIVO*/
        new DiseñoResponsivo().componente(JBActualizarAnimaciones);
        new DiseñoResponsivo().componente(JBCancelar);
        new DiseñoResponsivo().componente(JBEliminar);
        new DiseñoResponsivo().componente(JBGuardar);
        new DiseñoResponsivo().componente(JTAnimacion);
        new DiseñoResponsivo().componente(JTAnimales);
        new DiseñoResponsivo().componente(JTNombre);
        new DiseñoResponsivo().componente(JTSonidoAnimal);
        new DiseñoResponsivo().componente(JTSonidoIndicacion);
        new DiseñoResponsivo().componente(JTSonidoNombre);
        new DiseñoResponsivo().componente(Play1);
        new DiseñoResponsivo().componente(Play2);
        new DiseñoResponsivo().componente(Play3);
        new DiseñoResponsivo().componente(Play4);
        new DiseñoResponsivo().componente(JLTitulo);
        new DiseñoResponsivo().componente(jLabel3);
        new DiseñoResponsivo().componente(jLabel4);
        new DiseñoResponsivo().componente(jLabel5);
        new DiseñoResponsivo().componente(jLabel6);
        new DiseñoResponsivo().componente(jLabel7);
        new DiseñoResponsivo().componente(jScrollPane1);
        new DiseñoResponsivo().componente(JLFondo);
        
        /*SE ASIGNA EL COLOR A LOS COMPONENTES DEL FORMULARIO*/
        Color color = Color.DARK_GRAY;
        JBActualizarAnimaciones.setForeground(color);
        JBCancelar.setForeground(color);
        JBEliminar.setForeground(color);
        JBGuardar.setForeground(color);
        JTAnimacion.setForeground(color);
        JTAnimales.setForeground(color);
        JTNombre.setForeground(color);
        JTSonidoAnimal.setForeground(color);
        JTSonidoIndicacion.setForeground(color);
        JTSonidoNombre.setForeground(color);
        JLTitulo.setForeground(color);
        jLabel3.setForeground(color);
        jLabel4.setForeground(color);
        jLabel5.setForeground(color);
        jLabel6.setForeground(color);
        jLabel7.setForeground(color);
        
        /*SE ASIGNA LA FUENTE A LOS COMPONENTES DEL FORMULARIO*/
        int fuente = 20;
        JTAnimales.setFont(new DiseñoResponsivo().getFontDisco(fuente));
        JTAnimacion.setFont(new DiseñoResponsivo().getFontDisco(fuente));
        JTNombre.setFont(new DiseñoResponsivo().getFontDisco(fuente));
        JTSonidoAnimal.setFont(new DiseñoResponsivo().getFontDisco(fuente));
        JTSonidoIndicacion.setFont(new DiseñoResponsivo().getFontDisco(fuente));
        JTSonidoNombre.setFont(new DiseñoResponsivo().getFontDisco(fuente));
        JBActualizarAnimaciones.setFont(new DiseñoResponsivo().getFontDisco(26));
        JBCancelar.setFont(new DiseñoResponsivo().getFontDisco(26));
        JBEliminar.setFont(new DiseñoResponsivo().getFontDisco(26));
        JBGuardar.setFont(new DiseñoResponsivo().getFontDisco(26));
        JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());
        jLabel3.setFont(new DiseñoResponsivo().getFontDisco(26));
        jLabel4.setFont(new DiseñoResponsivo().getFontDisco(26));
        jLabel5.setFont(new DiseñoResponsivo().getFontDisco(26));
        jLabel6.setFont(new DiseñoResponsivo().getFontDisco(26));
        jLabel7.setFont(new DiseñoResponsivo().getFontDisco(26));
        /*SE ASIGNA LOS ICONOS A LOS COMPONENTES QUE CONTENGA EL FORMULARIO*/
        JLFondo.setIcon(new DiseñoResponsivo().getFondo("fondo"));
        new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
        new DiseñoResponsivo().adaptarImagenComponente(Play1,new ImageIcon(getClass().getResource("/img/play2.png")));
        new DiseñoResponsivo().adaptarImagenComponente(Play2,new ImageIcon(getClass().getResource("/img/play2.png")));
        new DiseñoResponsivo().adaptarImagenComponente(Play3,new ImageIcon(getClass().getResource("/img/play2.png")));
        new DiseñoResponsivo().adaptarImagenComponente(Play4,new ImageIcon(getClass().getResource("/img/play2.png")));
    }
    
    /*METODO QUE CREA UN MODELO PARA LA TABLA A VISUALIZAR 
    LAS ANIMACIONES REGISTRADAS EN LA BASE DE DATOS*/
    private void inicializarTabla(){
        modelo = new DefaultTableModel();//SE CREA UN MODELO PARA LA TABLA
        //SE AGREGAN LAS COLUMNAS AL MODELO
        modelo.addColumn("NOMBRE");
        modelo.addColumn("AUDIO NOMBRE");
        modelo.addColumn("AUDIO ANIMAL");
        modelo.addColumn("ANIMACION");
        modelo.addColumn("SONIDO INDICACION");
        modelo.addColumn("TIEMPO");
        
        /*SE OBTIENEN LOS DATOS DE LAS ANIMACIONES REGISTRADOS*/
        List animales = new Conexion().getAnimales();
        //SE AGREGAN LOS DATOS DE LAS ANIMACIONES AL MODELO DE LA TABLA
        for(int i=0; i<animales.size(); i++){
            String[] fila = (String[])animales.get(i);
            modelo.addRow(fila);
        }
        //ASIGNAMOS EL MODELO A LA TABLA QUE CONTENDRA LOS REGISTROS
        JTAnimales.setModel(modelo);
        
        /*ASIGNAMOS EL TAMAÑO A LAS COLUMNAS DE LA TABLA*/
        JTAnimales.setAutoResizeMode(JTAnimales.AUTO_RESIZE_OFF);
        JTAnimales.getColumnModel().getColumn(0).setPreferredWidth(200);
        JTAnimales.getColumnModel().getColumn(1).setPreferredWidth(900);
        JTAnimales.getColumnModel().getColumn(2).setPreferredWidth(900);
        JTAnimales.getColumnModel().getColumn(3).setPreferredWidth(900);
        JTAnimales.getColumnModel().getColumn(4).setPreferredWidth(900);
        JTAnimales.getColumnModel().getColumn(5).setPreferredWidth(150);
        JTAnimales.setRowHeight(80);//SE ASIGNA EL ALTO DE LAS COLUMNAS
        /*SE ASIGNAN EL ALINEAMIENTO DE LAS CELDAS EN CENTRADAS*/
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(CENTER);
        JTAnimales.getColumnModel().getColumn(0).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(1).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(2).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(3).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(4).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(5).setCellRenderer(tcr);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAnimales = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JTSonidoIndicacion = new javax.swing.JTextField();
        JTNombre = new javax.swing.JTextField();
        JTSonidoNombre = new javax.swing.JTextField();
        JTSonidoAnimal = new javax.swing.JTextField();
        JTAnimacion = new javax.swing.JTextField();
        JBEliminar = new javax.swing.JButton();
        JBActualizarAnimaciones = new javax.swing.JButton();
        JBGuardar = new javax.swing.JButton();
        JBCancelar = new javax.swing.JButton();
        Play4 = new javax.swing.JButton();
        Play1 = new javax.swing.JButton();
        Play2 = new javax.swing.JButton();
        Play3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        JLLogo = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("GESTIONAR ANIMALES");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(270, 10, 820, 60);

        JTAnimales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTAnimales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTAnimalesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTAnimales);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 370, 1090, 240);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nombre:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(180, 80, 300, 40);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Sonido Nombre:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(180, 130, 300, 40);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Sonido Animal:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(180, 180, 300, 40);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Animacion:");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(180, 230, 300, 40);
        getContentPane().add(JTSonidoIndicacion);
        JTSonidoIndicacion.setBounds(490, 280, 610, 40);
        getContentPane().add(JTNombre);
        JTNombre.setBounds(490, 80, 610, 40);
        getContentPane().add(JTSonidoNombre);
        JTSonidoNombre.setBounds(490, 130, 610, 40);
        getContentPane().add(JTSonidoAnimal);
        JTSonidoAnimal.setBounds(490, 180, 610, 40);
        getContentPane().add(JTAnimacion);
        JTAnimacion.setBounds(490, 230, 610, 40);

        JBEliminar.setText("Eliminar");
        JBEliminar.setBorder(null);
        JBEliminar.setContentAreaFilled(false);
        JBEliminar.setFocusable(false);
        JBEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(JBEliminar);
        JBEliminar.setBounds(500, 330, 160, 30);

        JBActualizarAnimaciones.setText("Buscar Animaciones");
        JBActualizarAnimaciones.setBorder(null);
        JBActualizarAnimaciones.setContentAreaFilled(false);
        JBActualizarAnimaciones.setFocusable(false);
        JBActualizarAnimaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBActualizarAnimacionesActionPerformed(evt);
            }
        });
        getContentPane().add(JBActualizarAnimaciones);
        JBActualizarAnimaciones.setBounds(730, 330, 420, 30);

        JBGuardar.setText("Guardar Tabla");
        JBGuardar.setBorder(null);
        JBGuardar.setContentAreaFilled(false);
        JBGuardar.setFocusable(false);
        JBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(JBGuardar);
        JBGuardar.setBounds(900, 620, 250, 50);

        JBCancelar.setText("Cancelar");
        JBCancelar.setBorder(null);
        JBCancelar.setContentAreaFilled(false);
        JBCancelar.setFocusable(false);
        JBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(JBCancelar);
        JBCancelar.setBounds(610, 620, 250, 50);

        Play4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play2.png"))); // NOI18N
        Play4.setBorder(null);
        Play4.setContentAreaFilled(false);
        Play4.setFocusable(false);
        Play4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Play4ActionPerformed(evt);
            }
        });
        getContentPane().add(Play4);
        Play4.setBounds(1100, 280, 50, 40);

        Play1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play2.png"))); // NOI18N
        Play1.setBorder(null);
        Play1.setContentAreaFilled(false);
        Play1.setFocusable(false);
        Play1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Play1ActionPerformed(evt);
            }
        });
        getContentPane().add(Play1);
        Play1.setBounds(1100, 130, 50, 40);

        Play2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play2.png"))); // NOI18N
        Play2.setBorder(null);
        Play2.setContentAreaFilled(false);
        Play2.setFocusable(false);
        Play2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Play2ActionPerformed(evt);
            }
        });
        getContentPane().add(Play2);
        Play2.setBounds(1100, 180, 50, 40);

        Play3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play2.png"))); // NOI18N
        Play3.setBorder(null);
        Play3.setContentAreaFilled(false);
        Play3.setFocusable(false);
        Play3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Play3ActionPerformed(evt);
            }
        });
        getContentPane().add(Play3);
        Play3.setBounds(1100, 230, 50, 40);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Sonido Indicación:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel7);
        jLabel7.setBounds(180, 280, 300, 40);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(20, 20, 180, 150);

        JLFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLFondo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(JLFondo);
        JLFondo.setBounds(-20, -10, 1410, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Play1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Play1ActionPerformed
        play(JTSonidoNombre.getText());
    }//GEN-LAST:event_Play1ActionPerformed

    private void Play2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Play2ActionPerformed
        play(JTSonidoAnimal.getText());
    }//GEN-LAST:event_Play2ActionPerformed

    private void Play3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Play3ActionPerformed
        JOptionPane.showMessageDialog(null,"","ANIMACION",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(JTAnimacion.getText()));
    }//GEN-LAST:event_Play3ActionPerformed

    private void Play4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Play4ActionPerformed
        play(JTSonidoIndicacion.getText());
    }//GEN-LAST:event_Play4ActionPerformed

    /*METODO DE ACCION DEL BOTON GUARDAR, REALIZA EL BORRADO DE LAS ANIMACIONES 
    EN LA BASE DATOS PARA GUARDAR LAS NUEVAS DIRECCIONES*/
    private void JBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBGuardarActionPerformed
        new Conexion().vaciarTabla();//SE VACIA LA TABLA DE LAS ANIMACIONES DE LA BASE DE DATOS
        
        /*SE OBTIENEN LOS DATOS DE LA TABLA PARA AGREGARLOS A LA BASE DE DATOS*/
        for(int i=0; i<JTAnimales.getRowCount(); i++){
            String nombre = JTAnimales.getValueAt(i,0).toString();
            String sonidoNombre = JTAnimales.getValueAt(i,1).toString();
            String sonidoAnimal = JTAnimales.getValueAt(i,2).toString();
            String animacion = JTAnimales.getValueAt(i,3).toString();
            String indicacion = JTAnimales.getValueAt(i,4).toString();
            String tiempo = JTAnimales.getValueAt(i,5).toString();
            //SE VERIFICA QUE NINGUNO DE LOS DATOS DE ALGUNA ANIMACION ESTE VACIA DE LO CONTRARIO NO SE GUARDARA
            if(!nombre.isEmpty()&&!sonidoNombre.isEmpty()&&!sonidoAnimal.isEmpty()&&!animacion.isEmpty()&&!indicacion.isEmpty()&&!tiempo.isEmpty()){
                //SE PASAN LOS DATOS DE CADA REGISTRO PARA AGREGAR A LA BASE DE DATOS
                new Conexion().agregarAnimal(nombre, sonidoNombre, sonidoAnimal, animacion, indicacion,tiempo);
            }else{
                JOptionPane.showMessageDialog(null,"No debe haber datos vacios");
            }
        }
        JOptionPane.showMessageDialog(null,"Se ha terminado de guardar los datos");
    }//GEN-LAST:event_JBGuardarActionPerformed

    
    /*METODO DE ACCION DEL BOTON ELIMINAR ANIMACION*/
    private void JBEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBEliminarActionPerformed
        int fila = JTAnimales.getSelectedRow();//SE OBTIENE LA FILA SELECCIONADA
        String nombre = JTAnimales.getValueAt(fila,0).toString();//SE OBTIENE EL NOMBRE DEL ANIMAL
        String mensaje = new Conexion().eliminarAnimal(nombre);//SE ELIMINA EL REGISTRO DE LA BASE DE DATOS
        JOptionPane.showMessageDialog(null, mensaje);
        inicializarTabla();//SE ACTUALIZA LA TABLA
    }//GEN-LAST:event_JBEliminarActionPerformed

    /*METODO DE ACCION AL DAR CLIC A LA TABLA*/
    private void JTAnimalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTAnimalesMouseClicked
        int fila = JTAnimales.getSelectedRow();//SE OBTIEN LA FILA SELECCIONADA
        if(fila>=0){
            /*SE VISUALIZAR LOS DATOS EN LAS CAJAS DE TEXTO PARA SU REPRODUCCION*/
            JTNombre.setText(JTAnimales.getValueAt(fila,0).toString());
            JTSonidoNombre.setText(JTAnimales.getValueAt(fila,1).toString());
            JTSonidoAnimal.setText(JTAnimales.getValueAt(fila,2).toString());
            JTAnimacion.setText(JTAnimales.getValueAt(fila,3).toString());
            JTSonidoIndicacion.setText(JTAnimales.getValueAt(fila,4).toString());
        }
    }//GEN-LAST:event_JTAnimalesMouseClicked

    /*METODO DE ACCION DEL BOTON ACTUALIZAR ANIMACIONES, SU PROPOSITO ES LEER LOS ARCHIVOS 
    DE LAS ANIMACIONES AUTOMATICAMENTE Y AGREGARLOS A LA TABLA DE LAS ANIMACIONES*/
    private void JBActualizarAnimacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBActualizarAnimacionesActionPerformed
        modelo = new DefaultTableModel();//SE CREA EL MODELO PARA LA TABLA
        //SE AGREGAN LAS COLUMNAS AL MODELO
        modelo.addColumn("NOMBRE");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("SONIDO");
        modelo.addColumn("ANIMACION");
        modelo.addColumn("INDICACION");
        modelo.addColumn("DURACION");
        
        /*SE CREA UN BUFFEREDREADER PARA LEER EL ARCHIVO QUE CONTIENE LOS TIEMPOS 
        DE CADA ANIMACION*/
        BufferedReader time = null;
        File dir = null,tiempo = null;
        try{
            String directorio = "Animaciones";
            dir = new File(directorio);
            tiempo = new File(directorio+"/tiempo");
            /*SI EXISTEN LOS ARCHIVOS PASA A LEER LAS RUTAS DE LAS ANIMACIONES 
            Y AGREGA LOS DATOS AL MODELO*/
            if(dir.exists()&&tiempo.exists()){
                time = new BufferedReader(new FileReader(tiempo));
                File[] ficheros = dir.listFiles();
                for(int i=0; i<ficheros.length-1; i++){
                    for(int x=0;x<ficheros.length-1;x++){
                        String[] animacion = new String[6];
                        animacion[0] = ficheros[x].getName();
                        dir = new File(directorio+"/"+animacion[0]);
                        File[] archivos = dir.listFiles();
                        animacion[1] = archivos[3].getAbsolutePath().replace('\\','/');
                        animacion[2] = archivos[4].getAbsolutePath().replace('\\','/');
                        animacion[3] = archivos[0].getAbsolutePath().replace('\\','/');
                        animacion[4] = archivos[2].getAbsolutePath().replace('\\','/');
                        animacion[5] = time.readLine().replace(animacion[0]+"-","");
                        modelo.addRow(animacion);
                    }
                }
                
            }else{
                JOptionPane.showMessageDialog(null,"No se encuentran el archivo 'tiempo' en la carpeta de Animaciones");
            }
        }catch(Exception e){
            try {
                time.close();
            } catch (IOException ex) {
            }
        }
        JTAnimales.setModel(modelo);//SE ASIGNA EL MODELO A LA TABLA
        //SE DEFINE EL TAMAÑO DE CADA COLUMNA
        JTAnimales.setAutoResizeMode(JTAnimales.AUTO_RESIZE_OFF);
        JTAnimales.getColumnModel().getColumn(0).setPreferredWidth(200);
        JTAnimales.getColumnModel().getColumn(1).setPreferredWidth(900);
        JTAnimales.getColumnModel().getColumn(2).setPreferredWidth(900);
        JTAnimales.getColumnModel().getColumn(3).setPreferredWidth(900);
        JTAnimales.getColumnModel().getColumn(4).setPreferredWidth(900);
        JTAnimales.getColumnModel().getColumn(5).setPreferredWidth(150);
        JTAnimales.setRowHeight(80);//SE DEFINE EL ALTO DE LAS COLUMNAS
        /*SE DEFINE QUE EL VALOR DE LAS CELDAS SERAN CENTRADAS*/
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(CENTER);
        JTAnimales.getColumnModel().getColumn(0).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(1).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(2).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(3).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(4).setCellRenderer(tcr);
        JTAnimales.getColumnModel().getColumn(5).setCellRenderer(tcr);
    }//GEN-LAST:event_JBActualizarAnimacionesActionPerformed

    /*METODO DE ACCION DEL BOTON CANCELAR*/
    private void JBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBCancelarActionPerformed
        dispose();//OCULTA EL FORMULARIO DE LA GESTION DE ANIMACIONES
    }//GEN-LAST:event_JBCancelarActionPerformed
       
    /*METODO PARA REPRODUCIR EL SONIDO SELECCIONADO EN LAS CAJAS DE TEXTO*/
    private void play(String ruta){
        new reproductor.Reproductor().play(ruta);
    }
    public static void main(String args[]) {
        new AdminAnimales();
    }

    private DefaultTableModel modelo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBActualizarAnimaciones;
    private javax.swing.JButton JBCancelar;
    private javax.swing.JButton JBEliminar;
    private javax.swing.JButton JBGuardar;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLTitulo;
    private javax.swing.JTextField JTAnimacion;
    private javax.swing.JTable JTAnimales;
    private javax.swing.JTextField JTNombre;
    private javax.swing.JTextField JTSonidoAnimal;
    private javax.swing.JTextField JTSonidoIndicacion;
    private javax.swing.JTextField JTSonidoNombre;
    private javax.swing.JButton Play1;
    private javax.swing.JButton Play2;
    private javax.swing.JButton Play3;
    private javax.swing.JButton Play4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
