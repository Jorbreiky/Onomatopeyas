package sudo;

import java.awt.Color;
import javax.swing.ImageIcon;
import reproductor.Reproductor;
import utilerias.DiseñoResponsivo;

/*FORMULARIO QUE MUESTRA LAS OPCIONES DEL ADMINISTRADOR*/
public class MenuAdmin extends javax.swing.JFrame {

    public MenuAdmin() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA LA VENTANA
        iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
        reproductor = new Reproductor("");
        setVisible(true);//SE VISUALIZA EL FORMULARIO
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
        DE LA CLASE DISEÑO RESPONSIVO*/
        new DiseñoResponsivo().componente(JBBeneficiarios);
        new DiseñoResponsivo().componente(JBAvances);
        new DiseñoResponsivo().componente(JBRegresar);
        new DiseñoResponsivo().componente(JLFondo);
        new DiseñoResponsivo().componente(JLLogo);
        new DiseñoResponsivo().componente(JLTitulo);
        
        /*SE DEFINE EL TAMAÑO DE LA FUENTE DE LOS COMPONENTES*/
        int size = 27;
        JBBeneficiarios.setFont(new DiseñoResponsivo().getFontTexto(size));
        JBAvances.setFont(new DiseñoResponsivo().getFontTexto(size));
        JBRegresar.setFont(new DiseñoResponsivo().getFontTexto(size));
        JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());
        
        /*SE ASIGNAN LOS ICONOS A LOS LABEL DEL FORMULARIO*/
        JLFondo.setIcon(new DiseñoResponsivo().getFondo("fondo"));
        new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JBRegresar,new ImageIcon(getClass().getResource("/img/regresar.png")));
        JBRegresar.setText("");
        /*SE DEFINE EL COLOR DE FUENTE DE LOS COMPONENTES*/
        JLTitulo.setForeground(Color.red);
        JBBeneficiarios.setForeground(Color.red);
        JBAvances.setForeground(Color.red);
        JLTitulo.setForeground(Color.BLUE);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLTitulo = new javax.swing.JLabel();
        JLLogo = new javax.swing.JLabel();
        JBBeneficiarios = new javax.swing.JButton();
        JBAvances = new javax.swing.JButton();
        JBRegresar = new javax.swing.JButton();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("MENU ADMINISTRADOR");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(300, 80, 900, 50);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(10, 10, 170, 140);

        JBBeneficiarios.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JBBeneficiarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar1.png"))); // NOI18N
        JBBeneficiarios.setText("Beneficiarios");
        JBBeneficiarios.setBorder(null);
        JBBeneficiarios.setContentAreaFilled(false);
        JBBeneficiarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBBeneficiarios.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar0.png"))); // NOI18N
        JBBeneficiarios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar2.png"))); // NOI18N
        JBBeneficiarios.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JBBeneficiarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JBBeneficiarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBeneficiariosActionPerformed(evt);
            }
        });
        getContentPane().add(JBBeneficiarios);
        JBBeneficiarios.setBounds(200, 180, 400, 340);

        JBAvances.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        JBAvances.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/avance1.png"))); // NOI18N
        JBAvances.setText("Avances");
        JBAvances.setBorder(null);
        JBAvances.setContentAreaFilled(false);
        JBAvances.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JBAvances.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/avance0.png"))); // NOI18N
        JBAvances.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/avance2.png"))); // NOI18N
        JBAvances.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        JBAvances.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        JBAvances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAvancesActionPerformed(evt);
            }
        });
        getContentPane().add(JBAvances);
        JBAvances.setBounds(790, 190, 360, 330);

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

    /*METODO DE ACCION DEL BOTON BENEFICIARIOS*/
    private void JBBeneficiariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBeneficiariosActionPerformed
        reproductor.parar();
        new AdministracionDiscapacitado();//VISUALIZA EL FORMULARIO DE GESTION DE BENEFICIARIOS
        dispose();
    }//GEN-LAST:event_JBBeneficiariosActionPerformed

    /*METODO DE ACCION DEL BOTON REGRESAR*/
    private void JBRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegresarActionPerformed
        System.exit(0);//SE CIERRA EL PROGRAMA POR COMPLETO
    }//GEN-LAST:event_JBRegresarActionPerformed

    /*METODO DE ACCION DEL BOTON MOSTRAR AVANCE*/
    private void JBAvancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAvancesActionPerformed
        reproductor.parar();
        new VisualizarAvance();//VISUALIZA EL FOMULARIO DE VISUALIZAR AVANCE
        dispose();
    }//GEN-LAST:event_JBAvancesActionPerformed

    public static void main(String[] arg){
        new MenuAdmin();
    }
    private Reproductor reproductor;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAvances;
    private javax.swing.JButton JBBeneficiarios;
    private javax.swing.JButton JBRegresar;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLTitulo;
    // End of variables declaration//GEN-END:variables
}
