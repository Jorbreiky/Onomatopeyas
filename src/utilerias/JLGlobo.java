package utilerias;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*CLASE QUE HEREDA DE LA CLASE JLABEL PARA VISUALIZAR EL GLOBO EN PANTALLA*/
public class JLGlobo extends JLabel{
    private JFrame frame;
    private JLabel label;
    private int tiempo;
    private int x;
    private int y;
    
    /*EN EL CONSTRUCTOR SE OBTIENE EL NIVEL DEL GOLOBO Y EL JFRAME EN
    DONDE SE AGREGARA EL GLOBO*/
    public JLGlobo(JFrame frame,int globo){
        this.frame = frame;
        this.label = this;
        
        /*SE OBTIENE LA POSICION EN QUE APARECERA EL GLOBO*/
        x = new Calculos().obtenerAleatorio(0,new Calculos().getSizeScreen().width-200); 
        y = new Calculos().getSizeScreen().height;
        setBounds(x,y,200,300);
        
        //SE DEFINE LA IMAGEN DEL GLOBO A MOSTRAR
        switch(globo){
            case 1:
                new DiseñoResponsivo().adaptarImagenComponente(this,new ImageIcon(getClass().getResource("/img/globo1.png")));
                break;
            case 2:
                new DiseñoResponsivo().adaptarImagenComponente(this,new ImageIcon(getClass().getResource("/img/globo2.png")));
                break;
            case 3:
                new DiseñoResponsivo().adaptarImagenComponente(this,new ImageIcon(getClass().getResource("/img/globo3.png")));
                break;
            case 4:
                new DiseñoResponsivo().adaptarImagenComponente(this,new ImageIcon(getClass().getResource("/img/globo4.png")));
                break;
            case 5:
                new DiseñoResponsivo().adaptarImagenComponente(this,new ImageIcon(getClass().getResource("/img/globo5.png")));
                break;
        }    
        tiempo = new Calculos().obtenerAleatorio(10,30);// SE DEFINE EL TIEMPO A MOSTRARSE
        setVisible(true);
        frame.getContentPane().add(this,0);// SE AGREGA EL GLOBO AL JFRAME EN EL NIVEL CERO
        iniciar();//SE INICIALIZA LA ANIMACION DEL GLOBO
    }
    
    /*METODO QUE SE ENCARGA DE EJECUTAR LA ANIMACION DEL GLOBO*/
    private void iniciar(){
        class Hilo extends Thread{
            @Override
            public void run(){
                try{
                    for(int i=0; i<y+300; i+=10){
                        Thread.sleep(tiempo);
                        setBounds(x,y-i,200,300);
                        //System.out.println(getBounds());
                    }
                    frame.remove(label);
                }catch(Exception e){
                    
                }
            }
        } 
        new Hilo().start();//SE INICIA LA ANIMACION DEL GLOBO
    }
}