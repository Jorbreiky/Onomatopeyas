package utilerias;

import java.util.Calendar;
import javax.swing.ImageIcon;

/*CLASE SESION QUE SE UTILIZA PARA CONTROLAR LOS
DATOS DE LAS SESIONES*/
public class Sesion {
    /*CLASE QUE CONTIENE LOS DATOS DEL BENEFICIARIO, FECHA EN QUE INICIA LA SESION,
    LA FECHA EN QUE TERMINA LA SESION, EL NUMERO DE INTENTOS Y ACIERTOS*/
    private Object[] beneficiario;
    private Calendar fechaInicial;
    private Calendar fechaFinal;
    private int intentos;
    private int aciertos;
    private String idSesion;
    
    /*EL CONTRUCTOR RECIBE LOS DATOS INICIALES PARA CREAR UNA SESION Y PODER UTILIZARSE
    DENTRO DE LA APLICACION*/
    public Sesion(Object[] beneficiario,String idSesion,Calendar fechaInicial,int intentos,int aciertos){
        this.beneficiario = beneficiario;
        this.idSesion = idSesion;
        this.fechaInicial = fechaInicial;
        this.intentos = intentos;
        this.aciertos = aciertos;
    }
    
    //METODO QUE DEBUELVE LA CURP DEL BENEFICIARIO
    public String getCurp(){
        return beneficiario[0].toString();
    }
    
    //METODO QUE DEBUELVE EL NOMBRE DEL BENEFICIARIO
    public String getNombre(){
        return beneficiario[1].toString();
    }
    
    //METODO QUE DEBUELVE LA FOTO DEL BENEFICIARIO
    public ImageIcon getFoto(){
        return (ImageIcon)beneficiario[5];
    }
    
    //METODO QUE DEBUELVE EL ID DE LA SESION
    public String getIdSesion(){
        return idSesion;
    }
    
    //METODO QUE MODIFICA LA FECHA FINAL DE LA SESION
    public void setFechaFinal(Calendar fechaFinal){
        this.fechaFinal = fechaFinal;
    }
    
    //METODO QUE DEBUELVE LA HORA INICIAL DE LA SESION
    public String getHoraInicial(){
        return fechaInicial.get(Calendar.HOUR_OF_DAY)+":"+fechaInicial.get(Calendar.MINUTE);
    }
    
    //METODO QUE DEBUELVE LA HORA FINAL DE LA SESION
    public String getHoraFinal(){
        return fechaFinal.get(Calendar.HOUR_OF_DAY)+":"+fechaFinal.get(Calendar.MINUTE);
    }
    
    //METODO QUE DEBUELVE LA FECHA DE LA SESION
    public String getFecha(){
        return fechaInicial.get(Calendar.DAY_OF_MONTH)+"/"+(fechaInicial.get(Calendar.MONTH)+1)+"/"+fechaInicial.get(Calendar.YEAR);
    }
    
    //METODO QUE DEBUELVE LOS INTENTOS DE LA SESION
    public int getIntentos() {
        return intentos;
    }

    //METODO QUE AUMENTA LOS INTENTOS DE LA SESION
    public void aumentarIntentos() {
        this.intentos += 1;
    }
    
    //METODO QUE DEBUELVE LOS ACIERTOS DE LA SESION
    public int getAciertos() {
        return aciertos;
    }

    //METODO QUE AUMENTA LOS ACIERTOS DE LA SESION
    public void aumentarAciertos() {
        this.aciertos += 1;
        this.intentos += 1; 
    }    
}
