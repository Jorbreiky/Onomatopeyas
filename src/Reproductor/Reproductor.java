package reproductor;

import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Reproductor{
    
    //SE DECLARA EL REPRODUCTOR A UTILIZAR EN TODA LA CLASE
    private BasicPlayer player;
    
    public Reproductor(String nombre){
        try{
            player = new BasicPlayer();//SE INICIALIZA EL REPRODUCTOR MP3
            InputStream path = getClass().getResourceAsStream(nombre+".mp3");//SE CREA UN INPUTSTREAM 
            player.open(path);//SE ABRE EL INPUT STREAM DEL MP3 CON EL REPRODUCTOR
            player.play();//SE REPRODUCE EL SONIDO MP3
        }catch(Exception e){
            //MENSAJE DE ERROR EN CASO QUE APAREZCA CUALQUIER EXCEPTION
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage());
        }
    }
    public Reproductor(){
    }
    
    /*EL METODO PLAY SE CREO PARA ABRIR ARCHIVOS FUERA DEL PROYECTO
    COMO LOS SONIDOS MP3 DE LAS ANIMACIONES Y LOS MENSAJES DE FELICITACIONES
    DE LA APLICACION*/
    public void play(String ruta){
        try{
            player = new BasicPlayer();//SE INICIALIZA EL REPRODUCTOR MP3
            //InputStream path = getClass().getResourceAsStream(ruta);
            player.open(new File(ruta));//SE ABRE EL ARCHIVO CON LA RUTA DEL PARAMETRO
            player.play();//SE REPRODUCE EL AUDIO
        }catch(Exception e){
            //SE MANDA UN MENSAJE DE ERROR EN CASO QUE OCURRA CUALQUER EXCEPTION
            JOptionPane.showMessageDialog(null,"Error: "+e);
        }
    }
    
    /*EL METODO PARAR SE UTILIZA PARA PARAR UN AUDIO DEL REPRODUCTOR*/
    public void parar(){
        try {
            player.stop();//SE DETIENE EL AUDIO DEL REPRODUCTOR
        } catch (BasicPlayerException ex) {
            //EN CASO DE HABER CUALQUIER EXCEPTION SE MANDA EL MENSAJE CON EL ERROR
            JOptionPane.showMessageDialog(null,"Error: "+ex);
        }
    }
    
    /*METODO REPRODUCIRWAV SE UTILIZA PARA REPRODUCIR ALGUNOS AUDIOS WAV QUE SE NECESITAN EN 
    EN LA APLICACION COMO LOS MENSAJES DE FELICITACIONES QUE SE ENCUENTRAN EL PAQUETE REPRODUCTOR*/
    public void reproducirWAV(String nombre,int tiempo){
        //SE CREA UNA CLASE QUE HEREDA DE LA CLASE Thread IMPLEMENTANDO SUS METODOS
        class Hilo extends Thread{
            @Override
            public void run(){
                try {
                    Clip sonido = AudioSystem.getClip();// SE CREA UN REPRODUCTOR DE AUDIO WAV Y SE INICIALIZA
                    //SE ABRE EL ARCHIVO CON EL NOMBRE DEL PARAMETRO RECIBIDO EN EL METODO
                    sonido.open(AudioSystem.getAudioInputStream(getClass().getResource(nombre+".wav")));
                    sonido.start();//SE REPRODUCE EL SONIDO 
                    Thread.sleep(tiempo);//SE DETIENE EL PROGRAMA PARA PODER REPRODUCIR COMPLETAMENTE EL AUDIO
                    sonido.close();//SE CIERRA EL REPRODUCTOR
                } catch (Exception ex) {
                    System.out.println(ex);
                } 
            }
        } 
        new Hilo().start();//SE CREA UNA INSTANCIACION DEL HILO Y SE EJECUTA
    }
    
}