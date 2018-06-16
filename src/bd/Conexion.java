package bd;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utilerias.Calculos;

/*CLASE QUE SE USARA PARA REALIZAR LA CONEXION A LA BASE DE DATOS DE APACHE DERBY*/
public class Conexion {
    
    private Connection conexion;//DECLARACION DE LA CONEXION
    private ResultSet resultado;//DECLARACION DEL RESULTSET PARA OBTENER LAS CONSULTAS
    
    /*CONTRUCTOR EN EL CUAL SIRVE PARA REALIZAR LA CONEXION A LA BASE DE DATOS,
    Y SI NO EXISTE LA BASE DE DATOS, SE ENCARGA DE CREARLA JUNTO CON LAS TABLAS REQUERIDAS*/
    public Conexion(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conexion = DriverManager.getConnection("jdbc:derby:BDAsociacion");
        }catch(Exception sqle){
            try{
                JOptionPane.showMessageDialog(null,"Puede Tardar en Cargar \n Espere por favor!!!");
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                conexion = DriverManager.getConnection("jdbc:derby:BDAsociacion;create=true");
                conexion.createStatement().execute("create table beneficiario(curp varchar(18),nombre varchar(20),apellidoPaterno varchar(20),apellidoMaterno varchar(20),fechaNacimiento varchar(10),foto blob(16M),status varchar(8),constraint pkBeneficiario primary key(curp))");
                conexion.createStatement().execute("create table sesion(id int not null GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),horaInicio varchar(5),horaFin varchar(5),fecha varchar(10),constraint pkSesion primary key(id))");
                conexion.createStatement().execute("create table actividad(idSesion int,curpBeneficiario varchar(18),numeroAciertos float,numeroIntentos float,constraint fkBeneficiario foreign key(curpBeneficiario) references beneficiario(curp),constraint fkSesion foreign key(idSesion) references sesion(id))");
                conexion.createStatement().execute("create table animal(nombre varchar(20) not null,sonidoNombre varchar(200),sonidoAnimal varchar(200), animacion varchar(200),sonidoPregunta varchar(200),tiempo float,constraint pkAnimal primary key(nombre))");
                JOptionPane.showMessageDialog(null,"Base de Batos Creada Correctamente");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"ERROR: "+e);
            }
            
        }
    }
    
    /*METODO QUE REALIZA LA CONSULTA A LA BASE DE DATOS Y RETORNA 
    TODOS LOS BENEFICIARIOS REGISTRADOS EN UNA LISTA*/
    public List getBeneficiarios(){
        try{
            resultado = conexion.createStatement().executeQuery("select curp,nombre,apellidoPaterno,apellidoMaterno,fechaNacimiento,foto,status from beneficiario");
            List filas = new ArrayList();
            while(resultado.next()){
                Object[] fila = new Object[7];
                fila[0] = resultado.getString(1);
                fila[1] = resultado.getString(2);
                fila[2] = resultado.getString(3);
                fila[3] = resultado.getString(4);
                fila[4] = resultado.getString(5);
                Blob blob = resultado.getBlob(6);//SE OBTIENE LA FOTO GUARDADA EN LA BASE DE DATOS
                fila[5] = (ImageIcon) new ObjectInputStream(blob.getBinaryStream()).readObject();//SE CONVIERTE A IMAGEICON
                fila[6] = resultado.getString(7);
                filas.add(fila);
            }
            conexion.close();
            return filas;
        }catch(Exception e){System.out.println("Algo salio mal en la base de datos \n"+e);return null;}
    }
    
    /*SE OBTIENE EL REGISTRO DE UN BENEFICIARIO EN ESPECIFICO CON SU CURP*/
    public List getBeneficiario(String curp){
        List lista = new ArrayList();
        try{
            resultado = conexion.createStatement().executeQuery("select curp,nombre,apellidoPaterno,apellidoMaterno,fechaNacimiento,foto,status from beneficiario where curp='"+curp+"'");
            Object[] fila = new Object[6];
            resultado.next();
                fila[1] = resultado.getString(1);
                fila[2] = resultado.getString(2);
                fila[3] = resultado.getString(3);
                fila[4] = resultado.getString(4);
                fila[5] = resultado.getString(5);
                Blob blob = resultado.getBlob(6);//SE OBTIENE LA FOTO DEL BENEFICIARIO
                fila[0] = (ImageIcon) new ObjectInputStream(blob.getBinaryStream()).readObject();//SE CONVIERTE A IMAGE ICON
            lista.add(fila);
            //conexion.close();
            conexion.commit();
            return lista;
        }catch(Exception e){return null;}
    }
    
    /*SE CONSULTAN LAS SESIONES REGISTRADAS EN LA BASE DE DATOS DE UN BENEFICIARIO EN 
    ESPECIFICO Y LAS RETORNA EN UNA LISTA*/
    public List getHistorialSesiones(String curp){
        try{
            resultado = conexion.createStatement().executeQuery("select s.id,s.horaInicio,s.horaFin,s.fecha,a.numeroIntentos,a.numeroAciertos from sesion as s join actividad as a on a.idSesion=s.id where a.curpBeneficiario='"+curp+"'");
            List filas = new ArrayList();
            while(resultado.next()){
                Object[] fila = new Object[6];
                fila[0] = resultado.getString(1);
                fila[1] = resultado.getString(2);
                fila[2] = resultado.getString(3);
                fila[3] = resultado.getString(4);
                fila[4] = resultado.getString(5);
                fila[5] = resultado.getString(6);
                filas.add(fila);
            }
            conexion.close();
            return filas;
        }catch(Exception e){
            System.out.println("Error desde la base de datos\n"+e);
            return null;}
    }
    
    /*SE OBTIENEN LOS BENEFICIARIOS REGISTRADOS EN LA BASE DE DATOS EL CUAL
    SU ESTADO SEA ACTIVO, SE UTILIZA PARA VISUALIZAR EN EL INICIO DE SESION*/
    public List getBeneficiariosActivos(){
        try{
            resultado = conexion.createStatement().executeQuery("select curp,nombre,apellidoPaterno,apellidoMaterno,fechaNacimiento,foto from beneficiario where status='ACTIVO'");
            List filas = new ArrayList();
            while(resultado.next()){
                Object[] fila = new Object[6];
                fila[0] = resultado.getString(1);
                fila[1] = resultado.getString(2);
                fila[2] = resultado.getString(3);
                fila[3] = resultado.getString(4);
                fila[4] = resultado.getString(5);
                Blob blob = resultado.getBlob(6);
                fila[5] = (ImageIcon) new ObjectInputStream(blob.getBinaryStream()).readObject();
                filas.add(fila);
            }
            conexion.close();
            return filas;
        }catch(Exception e){
            System.out.println("Error desde la base de datos\n"+e);
            return null;}
    }
    
    /*SE OBTIENE EL REGISTRO DE CADA UNO DE LOS ANIMALES Y LOS 
    RETORNA EN UNA LISTA*/
    public List getAnimales(){
        try{
            resultado = conexion.createStatement().executeQuery("select * from animal");
            List filas = new ArrayList();
            while(resultado.next()){
                String[] fila = new String[6];
                fila[0] = resultado.getString(1);
                fila[1] = resultado.getString(2);
                fila[2] = resultado.getString(3);
                fila[3] = resultado.getString(4);
                fila[4] = resultado.getString(5);
                fila[5] = resultado.getString(6);
                filas.add(fila);
            }
            conexion.close();
            return filas;
        }catch(Exception e){System.out.println("problemal al retornar los animales");return null;}
    }
    
    /*METODO QUE PERMITE REGISTRAR UN NUEVO BENEFICIARIO EN LA BASE DE DATOS,
    RECIBIENDO COMO PARAMETROS LOS DATOS DEL BENEFICIARIO*/
    public String agregarBeneficiario(String curp,String nombre,String apellidoPaterno,String apellidoMaterno,String fechaNacimiento,ImageIcon foto){
        try{
            PreparedStatement ps = conexion.prepareStatement("insert into beneficiario(curp,nombre,apellidoPaterno,apellidoMaterno,fechaNacimiento,foto,status) values('"+curp+"','"+nombre+"','"+apellidoPaterno+"','"+apellidoMaterno+"','"+fechaNacimiento+"',?,'ACTIVO')");            
            Blob blob = conexion.createBlob();//SE CREA UN BLOB PARA CONVERTIR LA FOTO DEL BENEICIARIO
            new ObjectOutputStream(blob.setBinaryStream(1)).writeObject(foto);//SE CONVIERTE LA FOTO A BLOB
            ps.setBlob(1, blob);//SE ESCRIBR LA FOTO CONVERTIDA AL BLOB
            ps.execute();//SE EJECUTA LA QUERY
            blob.free();
            ps.close();//SE CIERRA LA QUERY YA EJECUTADA
            conexion.commit();//SE REALIZA EL COMMIT A LA BASE DE DATOS
            conexion.close();
            return "Agregado Correctamente";
        }catch(Exception e){ return "Error al guardar :"+e.getMessage();}
    }
    
    /*METODO QUE REALIZA EL REGISTRO DE UN NUEVO ANIMAL O ANIMACION PARA LA APLICACION,
    EL CUAL RECIBE COMO PARAMENTROS LOS DATOS DE CADA ANIMACION*/
    public String agregarAnimal(String nombre,String sonidoNombre,String sonidoAnimal,String animacion,String indicacion,String tiempo){
       try{
            conexion.createStatement().execute("insert into animal values('"+nombre+"','"+sonidoNombre+"','"+sonidoAnimal+"','"+animacion+"','"+indicacion+"',"+tiempo+")");
            conexion.commit();
            conexion.close();
            return "Agregado Correctamente";
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error: "+e);
           return e.getMessage();
       }
    }
    
    /*METODO QUE REALIZA EL REGISTRO DE LA SESION EN LA BASE DE DATOS*/
    public String registrarSesion(String horaInicio,String horaFinal,String fecha){
        try{
            conexion.createStatement().execute("insert into sesion(horaInicio,horaFin,fecha) values('"+horaInicio+"','"+horaFinal+"','"+fecha+"')");
            conexion.commit();
            conexion.close();
            return "Registrado Correctamente";
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error: "+e);
           return e.getMessage();
       }
    }
    
    /*SE REALIZA EL REGISTRO DE LA ACTIVIDAD EN LA BASE DE DATOS*/
    public String registrarActividad(String idSesion,String curp,int intentos,int aciertos){
        try{
            conexion.createStatement().execute("insert into actividad values("+idSesion+",'"+curp+"',"+aciertos+","+intentos+")");
            conexion.commit();
            conexion.close();
            return "Registrado Correctamente";
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error: "+e);
           return e.getMessage();
       }
    }
    
    /*METODO QUE ELIMINA TODA LA TABLA CORRECPONDIENTE A LAS ANIMACIONES DE LA 
    BASE DE DATOS*/
    public String vaciarTabla(){
       try{
            conexion.createStatement().execute("delete from animal");
            conexion.commit();
            conexion.close();
            return "Vaciado Correctamente";
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Error: "+e);
           return e.getMessage();
       }
    }
    
    /*METODO PARA REALIZAR LA MODIFICAICON DE UN BENEFICIARIO EN 
    ESPECIFICO, RECIBIENDO COMO PARAMETROS LOS DATOS DEL BENEFICIARIO*/
    public String modificarBeneficiario(String curp,String nombre,String apellidoPaterno,String apellidoMaterno,String fechaNacimiento,ImageIcon foto){
        try{
            PreparedStatement ps = conexion.prepareStatement("update beneficiario set nombre='"+nombre+"',apellidoPaterno='"+apellidoPaterno+"',apellidoMaterno='"+apellidoMaterno+"',fechaNacimiento='"+fechaNacimiento+"', foto=? where curp='"+curp+"'");
            Blob blob = conexion.createBlob();
            new ObjectOutputStream(blob.setBinaryStream(1)).writeObject(foto);
            ps.setBlob(1, blob);
            ps.executeUpdate();
            ps.close();
            conexion.commit();
            conexion.close();
            return "Modificado Correctamente";
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERROR EN LA ACTALIZADA DE DATOS: "+e);
            return e.getMessage();
        }    
    }
    
    /*METODO QUE CAMBIA EL ESTADO DEL BENEFICIARIO PARA NO SER VISIBLE EN
    EL INICIO DE SESION*/
    public String eliminarBeneficiario(String curp){
        try {
            conexion.createStatement().execute("update beneficiario set status='INACTIVO' where curp='"+curp+"'");
            conexion.commit();
            conexion.close();
            return "Dado de baja Correctamente";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    /*METODO QUE ELIMINA AL UNA ANIMACION DE LA BASE DE DATOS*/
    public String eliminarAnimal(String nombre){
        try{
            conexion.createStatement().execute("delete from animal where nombre='"+nombre+"'");
            conexion.commit();
            conexion.close();
            return "Eliminado Correctamente";
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: "+e);
            return e.getMessage();
        }
    }
    
    /*METODO QUE PERMITE ACTIVAR LA CUENTA DE UN BENEFICIARIO QUE SE ENCONTRABA INACTIVO*/
    public String activarBeneficiario(String curp){
        try {
            conexion.createStatement().execute("update beneficiario set status='ACTIVO' where curp='"+curp+"'");
            conexion.commit();
            conexion.close();
            return "Dado de alto Correctamente";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    /*METODO QUE PERMITE OBTENER UN ID PARA LA SESION NUEVA*/
    public String obtenerIdSesion(){
        String texto="";
        try{
            ResultSet resultado = conexion.createStatement().executeQuery("select max(id)+1 from sesion");
            resultado.next();
            String  id = resultado.getString(1);
            conexion.close();
            if(id.equalsIgnoreCase("null")){
                texto = "1";
            }else{
                texto = id;
            }
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,"Error al pedir idSesion\n"+e);
            texto = "1";
        }
        return texto;
    }
    
    /*METODO QUE RETORNA LA FOTO DE UN BENEFICIARIO EN ESPECIFICO*/
    public ImageIcon getFoto(String curp){
        ImageIcon imagen = null;
        try{
            ResultSet resultado = conexion.createStatement().executeQuery("select foto from beneficiario where curp='"+curp+"'");
            //resultado.next();
            if (resultado.next()) {
              Blob photo = resultado.getBlob(1);
              imagen = (ImageIcon) new ObjectInputStream(photo.getBinaryStream()).readObject();
            }
            conexion.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: "+ e);
        }
        return imagen;
    }
    
    /*METODO QUE RETORNA LA MAXIMA PUNTUACION DE UN BENEFICIARIO*/
    public String getMaxPuntuacion(String curp){
        String texto="0";
        try{
            ResultSet resultado = conexion.createStatement().executeQuery("select max((100/numeroIntentos)*numeroAciertos) from actividad where curpBeneficiario='"+curp+"'");
            if(resultado.next()){
                texto = String.valueOf(new Calculos().redondear(resultado.getDouble(1),2));
            }
            conexion.close();
        }catch(Exception e){
            texto = "no hay datos "+e;
        }
        return texto;
    }
    
    /*METODO QUE RETORNA LA ULTIMA PUNTUACION OBTENIDA EN LA ULTIMA SESION*/
    public String getUltimaPuntuacion(String curp){
        String texto="0";
        try{
            ResultSet resultado = conexion.createStatement().executeQuery("select (100/numeroIntentos)*numeroAciertos from actividad where idSesion=(select max(s.id) from sesion as s join actividad as a on s.id=a.idSesion where a.curpBeneficiario = '"+curp+"')");
            if(resultado.next()){
                texto = String.valueOf(new Calculos().redondear(resultado.getDouble(1),2));
            }
            conexion.close();
        }catch(Exception e){
            texto = "no hay datos "+e;
        }
        return texto;
    }
    
    /*METODO QUE RETORNA FECHA DE LA ULTIMA SESION*/
    public String getUltimaSesion(String curp){
        String texto="";
        try{    
            ResultSet resultado = conexion.createStatement().executeQuery("select fecha from sesion where id=(select max(s.id) from sesion as s join actividad as a on s.id=a.idSesion where a.curpBeneficiario = '"+curp+"')");
            if(resultado.next()){
                texto = resultado.getString(1);
            }else{
                texto = "";
            }
            conexion.close();
        }catch(Exception e){
            return "no hay datos "+e;
        }
        return texto;
    }
    
    /*METODO QUE PERMITE OBTENER EL NUMERO DE SESIONES REALIZADAS POR UN BENEFICIARIO EN ESPECIFICO*/
    public int getNumeroSesiones(String curp){
        try{
            ResultSet resultado = conexion.createStatement().executeQuery("select count(s.id) from sesion as s join actividad as a on a.idSesion=s.id join beneficiario as b on b.curp=a.curpBeneficiario where b.curp = '"+curp+"'");
            if(resultado.next()){
                return resultado.getInt(1);
            }else{
                return 0;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }
    
    /*METODO QUE PERMITE CERRAR LA CONEXION A LA BASE DE DATOS*/
    public void close(){
        try {
            conexion.close();
        } catch (SQLException ex) {
        }
    }
}
