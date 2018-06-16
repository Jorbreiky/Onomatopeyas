package TablaPersonalizada;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import utilerias.DiseñoResponsivo;

/*SE CREA UNA CLASE QUE HEREDA DE LA CLASE BUTTON E IMPLEMENTA LA CLASE TABLECELLRENDERER
LA CUAL NOS AYUDARA A ASIGNAR EL EVENTO A LAS COLUMNAS QUE DECLAREMOS COMO BUTTON*/
public class TableButtonRenderer extends JButton implements TableCellRenderer{
    
    public TableButtonRenderer(){
      setOpaque(true);//SE DEFINE LA PROPIEDAD DEL BOTON A CREAR DENTRO DE LA TABLA
      setContentAreaFilled(false);//SE CREA EL BOTON TRANSPARENTE 
      setBorder(null);//SE LE QUITA EL BORDER AL BOTON 
    }

    /*ESTE METODO DEBUELVE EL COMPONENTE A AGREGAR A LA TABLA PERSONALIZADA QUE CREAREMOS*/
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        setFont(new DiseñoResponsivo().getFontTexto(26));//SE ASIGNA LA FUENTE AL COMPONENTE QUE SE AGREGARA A LA TABLA
        setForeground(Color.RED);//SE ASINA EL COLOR DE LA FUENTE 
        setBackground(UIManager.getColor("Button.background"));//SE LE ASIGNA EL COLOR DEL BOTON
        setText((value == null) ? "" : value.toString());//SI EL VAALOR ES DIFERENTE DE NULL ASIGNA EL VALOR
        
        //SE CREA EL BOTON CON EL CORRESPONDIENTE TEXTO PARA AGREGARLO A LA TABLA PERSONALIZADA
        if(table.getValueAt(row,2).toString().equalsIgnoreCase("EXPORTAR")){
            setText("Exportar");//SE ASIGNA EL TEXTO AL BOTON
            setIcon(new ImageIcon(getClass().getResource("/img/export.png")));//SE ASIGNA EL ICONO A MOSTRAR EN EL BOTON
            setPressedIcon(new ImageIcon(getClass().getResource("/img/export.png")));//SE ASIGNA EL ICONO AL PRESIONAR EL BOTON
            return this;
        }
        
        /*SI EL VALOR ES AGREGAR, SE CREARA UN BOTON CON EL TEXTO AGREGAR CON SUS RESPECTIVOS ICONOS*/
        if(getText().equalsIgnoreCase("Agregar")){
            setText("Agregar");
            setIcon(new ImageIcon(getClass().getResource("/img/agregar1.png")));
            setPressedIcon(new ImageIcon(getClass().getResource("/img/agregar0.png")));
            setRolloverIcon(new ImageIcon(getClass().getResource("/img/agregar2.png")));
        }else{
            /*EN CASO QUE EL VALOR SEA ACTIVO SE CREARA EL BOTON PARA ELIMINAR AL USUARIO CON SUS RESPECTIVOS ICONOS*/
            if(table.getValueAt(row,7).toString().equalsIgnoreCase("ACTIVO")){
                setText("Eliminar");
                setIcon(new ImageIcon(getClass().getResource("/img/eliminar1.png")));
                setPressedIcon(new ImageIcon(getClass().getResource("/img/eliminar0.png")));
                setRolloverIcon(new ImageIcon(getClass().getResource("/img/eliminar2.png")));
            }else{
                /*Y SI EL VALOR ES INACTIVO SE CREARA EL BOTON PARA ACTIVAR AL USUARIO*/
                if(table.getValueAt(row,7).toString().equalsIgnoreCase("INACTIVO")){
                    setText("Activar");
                    setIcon(new ImageIcon(getClass().getResource("/img/agregar1.png")));
                    setPressedIcon(new ImageIcon(getClass().getResource("/img/eliminar0.png")));
                }
            }
        }
        return this;
    }
  }