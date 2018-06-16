package TablaPersonalizada;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;

/*SE CREA UNA CLASE QUE HEREDA DE MOUSE ADAPTER LA CUAL NOS AYUDARA A EJECUTAR 
LOS EVENTOS DE LOS COMPONENTES DENTRO DE LA TABLA*/
public class JTableButtonMouseListener extends MouseAdapter {
      private final JTable table;//SE INSTANCEA UNA JTABLE 

      public JTableButtonMouseListener(JTable table) {
        this.table = table;//SE INICIALIZA LA TABLA PIDIENDOLA COMO PARAMETRO
      }

      /*METODO HEREDADO DE LA CLASE MOUSE ADAPTER EL CUAL SE EJECUTARA AL DAR CLIC 
      EL MOUSE DENTRO DE LA TABLA CREADA*/
      @Override 
      public void mouseClicked(MouseEvent e) {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX());
        int row    = e.getY()/table.getRowHeight(); 
        System.out.println("Col :"+column + "row:"+row);
        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
          Object value = table.getValueAt(row, column);
          System.out.println("Value :"+value.getClass().getName());
          if (value instanceof JButton) {
            ((JButton)value).doClick();
          }

        }
      }
    }