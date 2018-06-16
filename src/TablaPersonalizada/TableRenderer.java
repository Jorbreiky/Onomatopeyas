package TablaPersonalizada;

import animalesftaoad.RegistrarBeneficiario;
import bd.Conexion;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import utilerias.ManejoArchivo;

/*SE CREA LA CLASE TABLE RENDERER PARA CREAR LOS BOTONES QUE CONTENDRA LA TABLA PERSONALIZADA*/
public class TableRenderer extends DefaultCellEditor{
    private JButton button;
    private String label;
    private boolean clicked;
    private int row, col;
    private JTable table;

    public TableRenderer(JCheckBox checkBox){
      super(checkBox);
      button = new JButton();
      button.setOpaque(true);
      button.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          fireEditingStopped();
        }
      });
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
      this.table = table;
      this.row = row;
      this.col = column;

      button.setForeground(Color.black);
      button.setBackground(UIManager.getColor("Button.background"));
      label = (value == null) ? "" : value.toString();
      button.setText(label);
      clicked = true;
      return button;
    }
    
    /*SE INICIALIZAN LOS VALORES DE LA TABLA */
    @Override
    public Object getCellEditorValue(){
      if (clicked){
          if(table.getColumnCount()<=4){
              if(table.getValueAt(row,2).toString().equalsIgnoreCase("Exportar")){
                  int fila = table.getSelectedRow();
                  if(fila>=0){
                      String curp = table.getValueAt(fila,3).toString();
                      new ManejoArchivo().exportar(curp);
                  }
              }
          }else{
            if(button.getText().equalsIgnoreCase("Agregar")){
                new RegistrarBeneficiario();
            }else{
                if(table.getValueAt(row,7).toString().equalsIgnoreCase("ACTIVO")){
                    JOptionPane.showMessageDialog(null,new Conexion().eliminarBeneficiario(table.getValueAt(row,2).toString()));
                }else{
                    if(table.getValueAt(row,7).toString().equalsIgnoreCase("INACTIVO")){
                        JOptionPane.showMessageDialog(null,new Conexion().activarBeneficiario(table.getValueAt(row,2).toString()));
                    }
                }
            }
          }
      }
      clicked = false;
      return new String(label);
    }

    @Override
    public boolean stopCellEditing(){
      clicked = false;
      return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped(){
      super.fireEditingStopped();
    }
  }