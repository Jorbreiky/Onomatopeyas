package sudo;

import TablaPersonalizada.TableButtonRenderer;
import TablaPersonalizada.TableImageRenderer;
import TablaPersonalizada.TableRenderer;
import bd.Conexion;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import reproductor.Reproductor;
import utilerias.DiseñoResponsivo;

/*FORMULARIO QUE VISULIZA EL AVANCE DE LOS BENEFICIARIOS*/
public class VisualizarAvance extends javax.swing.JFrame {
    
    public VisualizarAvance() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA EL FORMULARIO
        iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
        inicializarTabla();//SE AGREGA EL MODELO A LA TABLA CON SUS RESPECTIVAS COLUMNAS
        porcentaje = JLSilueta.getHeight();
        reproductor = new Reproductor("");
        setVisible(true);//SE VISUALIZA EL FORMULARIO
        try{
            /*SE EJECUTA ESTA LINE PARA VISUALIZAR EL FILE CHOOSER CON INTERFAZ DE WINDOWS*/
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
        DE LA CLASE DISEÑO RESPONSIVO*/
        new DiseñoResponsivo().componente(JBRegresar);
        new DiseñoResponsivo().componente(JLFondo);
        new DiseñoResponsivo().componente(JLFondo1);
        new DiseñoResponsivo().componente(JLLogo);
        new DiseñoResponsivo().componente(JLMejorPuntaje);
        new DiseñoResponsivo().componente(JLNombre);
        new DiseñoResponsivo().componente(JLSesiones);
        new DiseñoResponsivo().componente(JLSilueta);
        new DiseñoResponsivo().componente(JLTitulo);
        new DiseñoResponsivo().componente(JLUltimaSesion);
        new DiseñoResponsivo().componente(JLUltimoPuntaje);
        new DiseñoResponsivo().componente(JTBeneficiarios);
        new DiseñoResponsivo().componente(jScrollPane1);
        
        /*SE ASIGNA LA FUENTE A LOS COMPONENTESD DEL FORMULARIO*/
        JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());
        int size = 25;
        JTBeneficiarios.setFont(new DiseñoResponsivo().getFontTexto(size));
        JLSilueta.setFont(new DiseñoResponsivo().getFontTexto(size));
        JLUltimoPuntaje.setFont(new DiseñoResponsivo().getFontTexto(size));
        JLNombre.setFont(new DiseñoResponsivo().getFontTexto(size));
        JLSesiones.setFont(new DiseñoResponsivo().getFontTexto(size));
        JLUltimaSesion.setFont(new DiseñoResponsivo().getFontTexto(size));
        JLMejorPuntaje.setFont(new DiseñoResponsivo().getFontTexto(size));
        
        /*SE ASIGNA EL COLOR DE LA FUENTE DE LOS COMPONENTES DEL FORMULARIO*/
        JLTitulo.setForeground(Color.red);
        JLMejorPuntaje.setForeground(Color.red);
        JLNombre.setForeground(Color.red);
        JLSesiones.setForeground(Color.red);
        JLUltimaSesion.setForeground(Color.red);
        JLUltimoPuntaje.setForeground(Color.red);
        
        /*SE ASIGNAN LOS ICONOS A LOS JLABEL*/
        new DiseñoResponsivo().adaptarImagenComponente(JLFondo,new ImageIcon(getClass().getResource("/img/fondo.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JLFondo1,new ImageIcon(getClass().getResource("/img/verde.jpg")));
        new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
        JBRegresar.setText("");
        new DiseñoResponsivo().adaptarImagenComponente(JBRegresar,new ImageIcon(getClass().getResource("/img/regresar.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JLSilueta,new ImageIcon(getClass().getResource("/img/fondo.jpg")));
    }
    
     /*METODO QUE CREA UN MODELO PARA LA TABLA A VISUALIZAR 
    LOS BENEFICIARIOS REGISTRADOS EN LA BASE DE DATOS*/
    private void inicializarTabla(){
        /*SE DECLARAN EL TEXTO DE COLUMAS QUE CONTENDRA LA TABLA PERSONALIZADA*/
        String[] columnas = { "FOTO", "NOMBRE","","CURP"};
        Object[][] datos = null;
        /*METODO QUE DEFINE QUE COLUMNA SERA EDITABLE, EN ESTE CASO LA COLUMNA QUE CONTARA CON 
                EL BOTON*/
        modelo = new DefaultTableModel(datos,columnas){
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column){
                return column == 2;
            }
        };
        //SE OBTIENE  LA LISTA DE LOS BENEFICIARIOS REGISTRADOS
        beneficiarios = new Conexion().getBeneficiariosActivos();
        
        //SE AGREGAN LOS BENEFICIARIOS AL MODELO DE LA TABLA
        for(int i=0; i<beneficiarios.size(); i++){
            Object[] fila = (Object[])beneficiarios.get(i);
            modelo.addRow(new Object[]{fila[5],fila[1],"Exportar",fila[0]});
        }
        JTBeneficiarios.setModel(modelo);//SE ASIGNA EL MODELO A LA TABLA
        JTBeneficiarios.getColumnModel().getColumn(2).setCellRenderer(new TableButtonRenderer());
        JTBeneficiarios.getColumnModel().getColumn(2).setCellEditor(new TableRenderer(new JCheckBox()));
        JTBeneficiarios.getColumnModel().getColumn(0).setCellRenderer(new TableImageRenderer());
        
        //SE DEFINE EL ANCHO DE LAS COLUMNAS DE LA TABLA
        JTBeneficiarios.setAutoResizeMode(JTBeneficiarios.AUTO_RESIZE_OFF);
        JTBeneficiarios.getColumnModel().getColumn(0).setPreferredWidth((JTBeneficiarios.getWidth()/3)-10);
        JTBeneficiarios.getColumnModel().getColumn(1).setPreferredWidth((JTBeneficiarios.getWidth()/3)+4);
        JTBeneficiarios.getColumnModel().getColumn(2).setPreferredWidth((JTBeneficiarios.getWidth()/3)-10);
        JTBeneficiarios.getColumnModel().getColumn(3).setMinWidth(0);
        JTBeneficiarios.getColumnModel().getColumn(3).setMaxWidth(0);
        JTBeneficiarios.getColumnModel().getColumn(3).setPreferredWidth(0);
        JTBeneficiarios.setRowHeight(150);//SE DEFINE EL ALTO DE LAS FILAS
        
        //SE CENTRA EL CONTENIDO DE LAS CELDAS DE LA TABLA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(CENTER);
        JTBeneficiarios.getColumnModel().getColumn(1).setCellRenderer(tcr);
        JTBeneficiarios.getColumnModel().getColumn(2).setCellRenderer(tcr);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTBeneficiarios = new javax.swing.JTable();
        JLUltimoPuntaje = new javax.swing.JLabel();
        JLNombre = new javax.swing.JLabel();
        JLSesiones = new javax.swing.JLabel();
        JLUltimaSesion = new javax.swing.JLabel();
        JLMejorPuntaje = new javax.swing.JLabel();
        JBRegresar = new javax.swing.JButton();
        JLLogo = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();
        JLSilueta = new javax.swing.JLabel();
        JLFondo1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("Visualizar Avance");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(270, 20, 820, 60);

        JTBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTBeneficiarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTBeneficiariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTBeneficiarios);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 230, 460, 402);

        JLUltimoPuntaje.setText("ULTIMO PUNTAJE:");
        getContentPane().add(JLUltimoPuntaje);
        JLUltimoPuntaje.setBounds(930, 280, 400, 30);

        JLNombre.setText("NOMBRE:");
        getContentPane().add(JLNombre);
        JLNombre.setBounds(930, 160, 400, 30);

        JLSesiones.setText("NUMERO DE SESIONES:");
        getContentPane().add(JLSesiones);
        JLSesiones.setBounds(930, 190, 400, 30);

        JLUltimaSesion.setText("ULTIMA SESION:");
        getContentPane().add(JLUltimaSesion);
        JLUltimaSesion.setBounds(930, 220, 400, 30);

        JLMejorPuntaje.setText("MEJOR PUNTAJE:");
        getContentPane().add(JLMejorPuntaje);
        JLMejorPuntaje.setBounds(930, 250, 400, 30);

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
        getContentPane().add(JLLogo);
        JLLogo.setBounds(20, 20, 170, 140);

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

        JLSilueta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/verde.jpg"))); // NOI18N
        getContentPane().add(JLSilueta);
        JLSilueta.setBounds(530, 170, 380, 470);

        JLFondo1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                JLFondo1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(JLFondo1);
        JLFondo1.setBounds(0, 0, 1450, 810);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*METODO DE ACCION AL DAR CLIC EN ALGUN REGISTRO DE LA TABLA DE BENEFICIARIOS*/
    private void JTBeneficiariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTBeneficiariosMouseClicked
        try{
            int fila = JTBeneficiarios.getSelectedRow();//SE OBTIENE LA FILA SELECCIONADA
            if(fila>=0){
                Object[] benefiaciario = (Object[])beneficiarios.get(fila);//SE OBTIENEN LOS DATOS DEL BENEFICIARIO
                //SE VISUALIZA EL AVANCE EN LA VENTANA
                JLNombre.setText("NOMBRE: "+benefiaciario[1]+" "+benefiaciario[2]);
                JLSesiones.setText("NUMERO DE SESIONES: "+new Conexion().getNumeroSesiones(benefiaciario[0].toString()));
                JLUltimaSesion.setText("ULTIMA SESIÓN: "+new Conexion().getUltimaSesion(benefiaciario[0].toString()));
                JLMejorPuntaje.setText("MEJOR PUNTUACIÓN: "+Math.round(Double.parseDouble(new Conexion().getMaxPuntuacion(benefiaciario[0].toString())))+" %");
                JLUltimoPuntaje.setText("ULTIMA PUNTUACIÓN: "+Math.round(Double.parseDouble(new Conexion().getUltimaPuntuacion(benefiaciario[0].toString())))+" %");
                double heigth = porcentaje - Double.parseDouble(new Conexion().getMaxPuntuacion(benefiaciario[0].toString()))*(porcentaje/100.0);
                JLSilueta.setBounds(JLSilueta.getX(),JLSilueta.getY(),JLSilueta.getWidth(),(int)heigth);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_JTBeneficiariosMouseClicked

    private void JLFondoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_JLFondoAncestorAdded

    }//GEN-LAST:event_JLFondoAncestorAdded

    /*METODO DE ACCION DEL BOTON REGRESAR*/
    private void JBRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegresarActionPerformed
        reproductor.parar();
        new MenuAdmin();//VISUALIZA EL MENU DEL ADMINISTRADOR
        dispose();
    }//GEN-LAST:event_JBRegresarActionPerformed

    private void JLFondo1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_JLFondo1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_JLFondo1AncestorAdded

    private DefaultTableModel modelo;
    private List beneficiarios;
    private int porcentaje;
    private Reproductor reproductor;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBRegresar;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLFondo1;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLMejorPuntaje;
    private javax.swing.JLabel JLNombre;
    private javax.swing.JLabel JLSesiones;
    private javax.swing.JLabel JLSilueta;
    private javax.swing.JLabel JLTitulo;
    private javax.swing.JLabel JLUltimaSesion;
    private javax.swing.JLabel JLUltimoPuntaje;
    private javax.swing.JTable JTBeneficiarios;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
