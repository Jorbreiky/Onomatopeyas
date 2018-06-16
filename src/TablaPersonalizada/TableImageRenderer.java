package TablaPersonalizada;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import utilerias.DiseñoResponsivo;

/*SE CREA LA CLASE TABLEIMAGERENDERER PARA AGREGAR UNA ETIQUETA AL JTABLE PERSONALIZADO PARA MOSTRAR 
LA FOTO DEL BENEFICIARIO*/
public class TableImageRenderer extends JLabel implements TableCellRenderer {

    /*EN ESTE METODO SE CONFIGIRA LOS DATOS DEL LABEL QUE CONTENDRA LA FOTO DEL BENEFICIARIO*/
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        try {
            if(value!=""){
                setSize(150,150);//SE ASIGNA EL TAMAÑO DE LA FOTO
                setHorizontalAlignment(CENTER);//SE CENTRARA LA IMAGEN DENTRO DEL LABEL EN FORMA HORIZONTAL
                setVerticalAlignment(CENTER);//SE CENTRARA LA IMAGEN DENTRO DEL LABEL EN FORMA VERTICAL
                ImageIcon img = (ImageIcon) value;//SE CONVIERTE EL VALOR DEL PARAMETRO COMO IMAGEICON
                new DiseñoResponsivo().adaptarImagenComponente(this, img);//SE ASIGNA LA IMGEN AL LABEL CON EL METODO                                                    //ADAPTAR COMPONENTE DE LA CLASE DISEÑORESPONSIVO
            }else{
                //SI EL VALOR ES NULL QUIERE DECIR QUE NO HAY FOTO DEL BENEFICIARIO Y LO RETORNA SIN FOTO
                return this;
            }
        } catch (Exception ex) {
            Logger.getLogger(TableImageRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
}
