package utilerias;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.InputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*CLASE QUE SE ENCARGA DEL DISEÑO DE LOS COMPONENTES DE LOS FORMULARIOS DE LA APLICACION*/
public class DiseñoResponsivo {
    
    /*METODO QUE RETORNA UNA IMAGEN CON LAS DIMENSIONES DE LA PANTALLA DE LA COMPUTADORA*/
    public Icon getFondo(String nombre){
        ImageIcon img = new ImageIcon(getClass().getResource("/img/"+nombre+".jpg"));
        Icon icono = new ImageIcon(img.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, java.awt.Image.SCALE_DEFAULT));
        return icono;
    }
    
    /*METODO QUE SE ENCARGA DE RENDERIZAR EL COMPONENTE DEL 
    PARAMETRO PARA ADAPTARLO A LA PANTALLA DE LA COMPUTADORA*/
    public void componente(Component componente){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) Math.rint((componente.getX()/13)*(pantalla.width/100));
        int y = (int) Math.rint((componente.getY()/7)*(pantalla.height/100));
        int width = (int) Math.rint((componente.getWidth()/13)*(pantalla.width/100));
        int height = (int) Math.rint((componente.getHeight()/7)*(pantalla.height/100));
        
        componente.setBounds(x,y,width,height);
    }
    
    /*METODO QUE ADAPTA UNA IMAGEN A UN COMPONENTE JLABEL*/
    public void adaptarImagenComponente(JLabel JLImagen,ImageIcon imagen){
        try{
            imagen.getImage().flush();        
            Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(JLImagen.getWidth(), JLImagen.getHeight(), java.awt.Image.SCALE_DEFAULT));
            JLImagen.setIcon(icono);
        }catch(Exception e){
            JLImagen.setIcon(null);
        }
    }
    
    /*METODO QUE ADAPTA UNA IMAGEN A UN COMPONENTE JBUTON*/
    public void adaptarImagenComponente(JButton JBImagen,ImageIcon imagen){
        imagen.getImage().flush();
        JBImagen.setText("");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(JBImagen.getWidth(), JBImagen.getHeight(), java.awt.Image.SCALE_DEFAULT));
        JBImagen.setIcon(icono);
    }
    
    /*METODO QUE RETORNA LA FOTO DEL BENEFICIARIO*/
    public Icon getFotoBeneficiario(String nombre){
        ImageIcon img = new ImageIcon(nombre);
        Icon icono = new ImageIcon(img.getImage().getScaledInstance(100,100, java.awt.Image.SCALE_DEFAULT));
        return icono;
    }
    
    /*METODO QUE RETORNA LA FUENTE PERSONALIZADA PARA EL TITULO*/
    public Font getFontTitulo(){
        Font ttfBase = null;
        try {
            InputStream myStream = getClass().getResourceAsStream("chocolate.otf");
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            return ttfBase.deriveFont(Font.BOLD, 70);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error Fuente: "+ex);
        }
        return null;
    }
    
    /*METODO QUE RETORNA LA FUENTE PERSONALIZADA PARA EL NOMBRE DEL ANIMAL*/
    public Font getFontNombreAnimal(){
        Font ttfBase = null;
        try {
            InputStream myStream = getClass().getResourceAsStream("chocolate.otf");
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            return ttfBase.deriveFont(Font.BOLD, 60);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error Fuente: "+ex);
        }
        return null;
    }
    
    /*METODO QUE RETORNA LA FUENTE PERSONALIZADA PARA EL TEXTO NORMAL*/
    public Font getFontTexto(int size){
        Font ttfBase = null;
        try {
            InputStream myStream = getClass().getResourceAsStream("chocolate.otf");
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            return ttfBase.deriveFont(Font.BOLD, size);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error Fuente: "+ex);
        }
        return null;
    }
    
    /*METODO QUE RETORNA LA FUENTE PERSONALIZADA PARA EL INDICADOR DE LAS ACTIVIDADES*/
    public Font getFontIndicador(int fuente){
        Font ttfBase = null;
        try {
            InputStream myStream = getClass().getResourceAsStream("chocolate.otf");
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            return ttfBase.deriveFont(Font.BOLD, 40);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error Fuente: "+ex);
        }
        return null;
    }
    
    /*METODO QUE RETORNA LA FUENTE PERSONALIZADA */
    public Font getFontDisco(int fuente){
        Font ttfBase = null;
        try {
            InputStream myStream = getClass().getResourceAsStream("LSD.ttf");
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            return ttfBase.deriveFont(Font.BOLD, fuente);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error Fuente: "+ex);
        }
        return null;
    }
    
}
