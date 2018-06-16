package sudo;

import TablaPersonalizada.TableButtonRenderer;
import TablaPersonalizada.TableImageRenderer;
import TablaPersonalizada.TableRenderer;
import animalesftaoad.RegistrarBeneficiario;
import bd.Conexion;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import reproductor.Reproductor;
import utilerias.DiseñoResponsivo;

/*FORMULARIO PARA REALIZAR LA GESTION DE LOS BENEFICIARIOS PARA LA APLICACION*/
public class AdministracionDiscapacitado extends javax.swing.JFrame implements WindowFocusListener{

    public AdministracionDiscapacitado() {
        initComponents();
        iniciarDiseño();//SE INICIALIZA EL DISEÑO DE LOS COMPONENTE DEL FORMULARIO
        setExtendedState(MAXIMIZED_BOTH);//SE MAXIMIZA EL FORMULARIO
        inicializarTabla();//SE AGREGA EL MODELO A LA TABLA
        setVisible(true);//SE VISUALIZA EL FORMULARIO
    }
    
    /*METODO QUE SIRVE PARA RENDERIZAR LOS COMPONENTES DEL FORMULARIO PARA ADAPTARLO A 
    CUALQUIER PANTALLA DONDE SE EJECUTE LA APLICACION*/
    private void iniciarDiseño(){
        addWindowFocusListener(this);
        
        /*SE RENDERIZAN LOS COMPONENTES DEL FORMULARIO CON EL METODO COMPONENTE
        DE LA CLASE DISEÑO RESPONSIVO*/
        new DiseñoResponsivo().componente(JBRegresar);
        new DiseñoResponsivo().componente(JLFondo);
        new DiseñoResponsivo().componente(JLTitulo);
        new DiseñoResponsivo().componente(JTDiscapacitados);
        new DiseñoResponsivo().componente(jScrollPane2);
        new DiseñoResponsivo().componente(JLLogo);
        new DiseñoResponsivo().componente(JTBuscar);
        new DiseñoResponsivo().componente(jLabel1);
        /*SE ASIGNAN LOS ICONOS A LOS JLABEL*/
        JLFondo.setIcon(new DiseñoResponsivo().getFondo("fondo"));
        new DiseñoResponsivo().adaptarImagenComponente(JLLogo,new ImageIcon(getClass().getResource("/img/logo.png")));
        new DiseñoResponsivo().adaptarImagenComponente(JBRegresar,new ImageIcon(getClass().getResource("/img/regresar.png")));
        
        /*SE ASIGNA LA FUENTE A LOS COMPONENTESD DEL FORMULARIO*/
        int sizeLabel = 26;
        JLTitulo.setFont(new DiseñoResponsivo().getFontTitulo());
        JTDiscapacitados.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        jLabel1.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        JTBuscar.setFont(new DiseñoResponsivo().getFontTexto(sizeLabel));
        
        /*SE ASIGNA EL COLOR DE LA FUENTE DE LOS COMPONENTES DEL FORMULARIO*/
        JLTitulo.setForeground(Color.RED);
        JTDiscapacitados.setForeground(Color.RED);
        jLabel1.setForeground(Color.RED);
        JTBuscar.setForeground(Color.RED);
        JBRegresar.setText("");
    }
    
    /*METODO QUE CREA UN MODELO PARA LA TABLA A VISUALIZAR 
    LOS BENEFICIARIOS REGISTRADOS EN LA BASE DE DATOS*/
    private void inicializarTabla(){
            try{
                /*SE DECLARAN EL TEXTO DE COLUMAS QUE CONTENDRA LA TABLA PERSONALIZADA*/
                String[] columnas = { "FOTO", "","CURP", "NOMBRE","PATERNO","MATERNO","FECHA NACIMIENTO","STATUS"};
                /*SE AGREGA LA PRIMERA FILA QUE CONTENDRA EL MODELO*/
                Object[][] datos = {{ "","Agregar","","","","","","" }};
                /*METODO QUE DEFINE QUE COLUMNA SERA EDITABLE, EN ESTE CASO LA COLUMNA QUE CONTARA CON 
                EL BOTON*/
                modelo = new DefaultTableModel(datos,columnas){
                    private static final long serialVersionUID = 1L;
                    @Override
                    public boolean isCellEditable(int row, int column){
                      return column == 1;
                    }
              };
                /*SE ASIGNA EL MODELO A LA TABLA*/
                JTDiscapacitados.setModel(modelo);
                
                /*SE TOMA EL MODELO DE LA TABLA*/
                modelo = (DefaultTableModel) JTDiscapacitados.getModel();
                
                /*SE OBTIENE LA LISTA DE LOS BENEFICIARIOS REGISTRADOS EN LA BASE DE DATOS*/
                List discapacitados = new Conexion().getBeneficiarios();
                
                /*SE AGREGA CADA BENEFICIARIO EN EL MODELO*/
                for(int i=0; i<discapacitados.size(); i++){
                    Object[] beneficiario = (Object[]) discapacitados.get(i);
                    modelo.addRow(new Object[]{beneficiario[5],"Eliminar",beneficiario[0],beneficiario[1],beneficiario[2],beneficiario[3],beneficiario[4],beneficiario[6]});
                }        
                
                JTDiscapacitados.setModel(modelo);//SE ASIGNA EL MODELO DE NUEVO A LA TABLA CON LOS DATOS NUEVOS
                /*ASIGNAMOS LA COLUMNA QUE SERA DE TIPO BOTON O DE TIPO IMAGEN*/
                JTDiscapacitados.getColumnModel().getColumn(1).setCellRenderer(new TableButtonRenderer());//SE ASIGNA LOS EVENTOS A LA COLUMNA 1(BOTON)
                JTDiscapacitados.getColumnModel().getColumn(1).setCellEditor(new TableRenderer(new JCheckBox()));//SE ASIGNA QUE SEA PULSABLE EL BOTON
                JTDiscapacitados.getColumnModel().getColumn(0).setCellRenderer(new TableImageRenderer());/*SE ASIGNA LA COLUMNA QUE SERA PARA 
                MOSTRAR LA FOTO DEL BENEFICIARIO*/

                /*SE ASIGNA EL TAMAÑO PARA CADA COLUMNA DE LA TABLA PERSONALIZADA*/
                JTDiscapacitados.setAutoResizeMode(JTDiscapacitados.AUTO_RESIZE_OFF);
                JTDiscapacitados.getColumnModel().getColumn(0).setPreferredWidth(150);
                JTDiscapacitados.getColumnModel().getColumn(1).setPreferredWidth(250);
                JTDiscapacitados.getColumnModel().getColumn(2).setPreferredWidth(350);
                JTDiscapacitados.getColumnModel().getColumn(3).setPreferredWidth(200);
                JTDiscapacitados.getColumnModel().getColumn(4).setPreferredWidth(200);
                JTDiscapacitados.getColumnModel().getColumn(5).setPreferredWidth(200);
                JTDiscapacitados.getColumnModel().getColumn(6).setPreferredWidth(200);
                JTDiscapacitados.getColumnModel().getColumn(7).setPreferredWidth(100);
                JTDiscapacitados.setRowHeight(150);//SE ASIGNA EL ALTO DE LAS CELDAS
                
                /*SE ASIGNA QUE EL VALOR DE LAS COLUMNAS SEA CENTRADO*/
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(CENTER);
                JTDiscapacitados.getColumnModel().getColumn(2).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(3).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(4).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(5).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(6).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(7).setCellRenderer(tcr);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error en la tabla: "+e);
            }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        JTDiscapacitados = new javax.swing.JTable();
        JLTitulo = new javax.swing.JLabel();
        JLLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JTBuscar = new javax.swing.JTextField();
        JBRegresar = new javax.swing.JButton();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        getContentPane().setLayout(null);

        JTDiscapacitados.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        JTDiscapacitados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTDiscapacitados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTDiscapacitadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTDiscapacitados);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(300, 200, 1000, 402);

        JLTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        JLTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLTitulo.setText("GESTION DE BENEFICIARIOS");
        getContentPane().add(JLTitulo);
        JLTitulo.setBounds(230, 40, 1010, 70);
        getContentPane().add(JLLogo);
        JLLogo.setBounds(20, 20, 180, 150);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Buscar por Nombre:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(310, 150, 300, 40);

        JTBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(JTBuscar);
        JTBuscar.setBounds(610, 150, 360, 40);

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
        JBRegresar.setBounds(1270, 610, 100, 90);

        JLFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLFondo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(JLFondo);
        JLFondo.setBounds(-20, 0, 1410, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*AL OBTENER EL FOCUS EL FORMULARIO PASA A ACTUALIZAR LA TABLA*/
    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        inicializarTabla();
    }//GEN-LAST:event_formFocusGained

    /*METODO DE ACCION AL DAR CLIC EN LA TABLA */
    private void JTDiscapacitadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTDiscapacitadosMouseClicked
        if(evt.getClickCount()==1){
            //JOptionPane.showMessageDialog(null,"","",JOptionPane.INFORMATION_MESSAGE,new Conexion().getFoto(JTDiscapacitados.getModel().getValueAt(JTDiscapacitados.getSelectedRow(),2).toString()));
        }else if(evt.getClickCount()==2){
            /*SE OBTIENE LOS DATOS DEL BENEFICIARIO SELECCIONADO PARA ENVIARLOS AL FORMULARIO REGISTROBENEFICIARIO
            PARA PODER MODIFICARLOS*/
            String curp = JTDiscapacitados.getModel().getValueAt(JTDiscapacitados.getSelectedRow(),2).toString();
            String nombre = JTDiscapacitados.getModel().getValueAt(JTDiscapacitados.getSelectedRow(),3).toString();
            String paterno = JTDiscapacitados.getModel().getValueAt(JTDiscapacitados.getSelectedRow(),4).toString();
            String materno = JTDiscapacitados.getModel().getValueAt(JTDiscapacitados.getSelectedRow(),5).toString();
            String fecha = JTDiscapacitados.getModel().getValueAt(JTDiscapacitados.getSelectedRow(),6).toString();
            //reproductor.parar();
            new RegistrarBeneficiario(curp, nombre, paterno, materno, fecha);
        }   
            
    }//GEN-LAST:event_JTDiscapacitadosMouseClicked

    
    /*METODO DE ACCION AL SOLTAR UNA TECLA EN LA CAJA DE TEXTO DE BUSQUEDA DEL
    BENEFICIARIO*/
    private void JTBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTBuscarKeyReleased
        JTBuscar.setText(JTBuscar.getText().toUpperCase());//SE CONVIERTE EL TEXTO A BUSCAR EN MAYUSCULAS
        try{
            //SE DEFINEN LAS COLUMNAS DE LA TABLA
            String[] columnas = { "FOTO", "","CURP", "NOMBRE","PATERNO","MATERNO","FECHA NACIMIENTO","STATUS"};
                /*SE AGREGA LA PRIMERA FILA */
                Object[][] datos = {{ "","Agregar","","","","","","" }};
                //SE DEFINE QUE COLUMNA DE LA TABLA CONTENDRA EL BOTON
                modelo = new DefaultTableModel(datos,columnas){
                    private static final long serialVersionUID = 1L;
                    @Override
                    public boolean isCellEditable(int row, int column){
                      return column == 1;
                    }
              };
                //SE OBTIENE LA LISTA DE BENEFICIARIOS REGISTRADOS EN LA BASE DE DATOS
                List discapacitados = new Conexion().getBeneficiarios();

                //SE AGREGAN LOS BENFICIARIOS AL MODELO 
                for(int i=0; i<=discapacitados.size()-1; i++){
                    Object beneficiario[] = (Object[])discapacitados.get(i);
                    if(JTBuscar.getText().equals("")){
                        modelo.addRow(new Object[]{beneficiario[5],"Eliminar",beneficiario[0],beneficiario[1],beneficiario[2],beneficiario[3],beneficiario[4],beneficiario[6]});
                    }else{
                        /*SI CUMPLE CON LA EXPRESION REGULAR, EL BENEFICIARIO ES AGREGADO AL MODELO DE LA TABLA*/
                        if(beneficiario[1].toString().matches(".*?"+JTBuscar.getText()+".*?")){
                            modelo.addRow(new Object[]{beneficiario[5],"Eliminar",beneficiario[0],beneficiario[1],beneficiario[2],beneficiario[3],beneficiario[4],beneficiario[6]});
                        }
                    }
                }        
                JTDiscapacitados.setModel(modelo);//SE ASIGNA EL MODELO A LA TABLA
                //SE ASIGNAN LOS EVENTOS A LAS COLUMNAS 
                JTDiscapacitados.getColumnModel().getColumn(1).setCellRenderer(new TableButtonRenderer());
                JTDiscapacitados.getColumnModel().getColumn(1).setCellEditor(new TableRenderer(new JCheckBox()));
                JTDiscapacitados.getColumnModel().getColumn(0).setCellRenderer(new TableImageRenderer());

                /*SE DEFINE EL TAMAÑO DE LAS COLUMNAS*/
                JTDiscapacitados.setAutoResizeMode(JTDiscapacitados.AUTO_RESIZE_OFF);
                JTDiscapacitados.getColumnModel().getColumn(0).setPreferredWidth(150);
                JTDiscapacitados.getColumnModel().getColumn(1).setPreferredWidth(250);
                JTDiscapacitados.getColumnModel().getColumn(2).setPreferredWidth(250);
                JTDiscapacitados.getColumnModel().getColumn(3).setPreferredWidth(200);
                JTDiscapacitados.getColumnModel().getColumn(4).setPreferredWidth(200);
                JTDiscapacitados.getColumnModel().getColumn(5).setPreferredWidth(200);
                JTDiscapacitados.getColumnModel().getColumn(6).setPreferredWidth(200);
                JTDiscapacitados.getColumnModel().getColumn(7).setPreferredWidth(100);
                JTDiscapacitados.setRowHeight(150);//SE DEFINE EL ALTO DE LAS CELDAS
                //SE DEFINE QUE EL TEXTO DE LAS CELDAS SERA CENTRADO
                DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
                tcr.setHorizontalAlignment(CENTER);
                JTDiscapacitados.getColumnModel().getColumn(2).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(3).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(4).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(5).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(6).setCellRenderer(tcr);
                JTDiscapacitados.getColumnModel().getColumn(7).setCellRenderer(tcr);
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error en la busqueda: \n"+e);
        }
    }//GEN-LAST:event_JTBuscarKeyReleased

    /*METODO DE ACCION DEL BOTON REGRESAR*/
    private void JBRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBRegresarActionPerformed
        new MenuAdmin();//VISUALIZA EL MENU DEL ADMINISTRADOR
        dispose();//SE OCULTA EL FORMULARIO
    }//GEN-LAST:event_JBRegresarActionPerformed

    private DefaultTableModel modelo;
    private Reproductor reproductor;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBRegresar;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLLogo;
    private javax.swing.JLabel JLTitulo;
    private javax.swing.JTextField JTBuscar;
    private javax.swing.JTable JTDiscapacitados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowGainedFocus(WindowEvent e) {
        inicializarTabla();
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
    }
}
