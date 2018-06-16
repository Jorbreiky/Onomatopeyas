package utilerias;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/*CLASE QUE SE ENCARGA DE LOS CALCULOS A UTILIZAR EN DIREFERENTES 
PARTES DE LA APLICACION*/
public class Calculos {
    
    /*METODO QUE RETORNA UNA LISTA DE NUMEROS ALEATORIOS DENTRO
    DEL RANDGO OBTENIDO DE LOS PARAMETROS*/
    public List obtenerAleatorios(int min,int max){
        List num = new ArrayList();
        do{
            boolean existe = false;
            //SE OBTIENE UN NUMERO ALEATORIO
            int numero = ThreadLocalRandom.current().nextInt(min,max + 1);
            for(int j=0; j<num.size(); j++){
                if(numero==Integer.parseInt(num.get(j).toString())){
                    existe=true;
                    break;
                }
            }
            if(!existe){
                num.add(numero);
            }
        }while((max-min)+1>num.size());
        return num;
    }
    
    /*METODO QUE SOLO RETIRNA UN SOLO NUMERO ALEATORIO*/
    public int obtenerAleatorio(int min,int max){
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }
    
    /*METODO QUE RECIBE EL VALOR A REDONDEAR Y LA CANTIDAD 
    DE DESIMALES A REDONDEAR*/
    public double redondear(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
    
    /*METODO QUE OBTIENE EL TAMAÑO DE LA PANTALLA 
    DE LA COMPUTADORA*/
    public Dimension getSizeScreen(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    /*METODO QUE RETORNA EL NUMERO DE ANIMALES DENTRO DE
    LAS ANIMACIONES*/
    public int animalesCount(){
        File file = new File("Animaciones/");
        File[] archivos = file.listFiles();
        return archivos.length-2;
    }
}
