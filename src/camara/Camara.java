package camara;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*CLASE QUE SE ENCARGA DE LA GESTION DE LA CAMARA EN EL REGISTRO DE LOS BENEFICIARIOS*/
public class Camara extends JLabel{
    
    private Webcam webcam;
    private WebcamPanel panelCamara;//SE CREA UN PANEL PARA VISUALIZAR LA CAMARA
    private JPanel panel;//ES EL JPANEL DEL CUAL SE OBTENDRAN LA POSICION Y TAMAÑO DE LA CAMARA
    private File archivoFoto;
    private ImageIcon foto;
    
    /*METODO QUE PERMITE OBTENER LA CAMARA PARA AGREGARLA EN ALGUN FORMULARIO,
    EL PARAMETRO QUE RECIBE ES PARA TOMAR EL TAMAÑO Y POSICION*/
    public WebcamPanel obtenerCamara(JPanel panel){
        this.panel=panel;
        this.panel.setVisible(false);
        webcam = Webcam.getDefault();
        try{
            //webcam.setViewSize(WebcamResolution.VGA.getSize());
            webcam.setViewSize(new Dimension(640,480));//SE DIMENCIONA LA CAMARA
            panelCamara = new WebcamPanel(webcam);//SE AGREGA LA CAMARA AL PANEL DE CAMARA 
            panelCamara.setBounds(panel.getBounds());//SE ASIGNA EL TAMAÑO Y POSICION DEL PANEL DE CAMARA
            //panelCamara.setFPSDisplayed(true);
            //panelCamara.setDisplayDebugInfo(true);
            //panelCamara.setImageSizeDisplayed(true);
            panelCamara.setMirrored(true);//SE VISUALIZAR EL PANEL DE LA CAMARA
            return panelCamara;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: "+e);
        }
        return panelCamara;
    }
    
    /*METODO QUE SE ENCARGA DE CERRAR LA CAMARA*/
    public void cerrarCamara(JFrame ventana){
        try{
            panel.setVisible(true);
            ventana.remove(panelCamara);
            webcam.close();
            panelCamara.setMirrored(false);
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Error: "+e);}
    }
    
    /*METODO QUE PERMITE OBTENER LA FOTO CAPTURADA DE LA CAAMARA Y GUARDARLA
    EN LA RUTA TEMP/TEMP.PNG*/
    public ImageIcon capturarFoto(){
        
        try {
            archivoFoto = new File("temp/temp.png");
            archivoFoto.mkdirs();
            ImageIO.write(webcam.getImage(),"PNG",archivoFoto);
            
            this.panel.setVisible(true);
            webcam.close();
            panelCamara.setMirrored(false);
            foto = new ImageIcon(archivoFoto.getAbsolutePath());
            return foto;
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,"No ha iniciado la camara");
        } catch (IOException ex) {
            Logger.getLogger(Camara.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /*SE OBTIENE LA FOTO CAMPTURADA*/
    public ImageIcon obtenerFoto(){
        try{
            return foto;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Aun no se captura foto");
            System.out.println("Error: "+e);
        }
        return null;
    }
}
