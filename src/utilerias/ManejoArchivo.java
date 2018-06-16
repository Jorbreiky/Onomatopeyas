package utilerias;

import bd.Conexion;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/*CLASE QUE SE ENCARGA DE EXPORTAR EL REGISTRO DEL
BENEFICIARIO EN EL FORMULARIO MOSTRAR AVANCE*/
public class ManejoArchivo {
    
    /*METODO QUE RECIBE COMO PARAMETRO EL ID DEL BENEFICIARIO PARA 
    REALIZAR LA EXPORTACION DEL REGISTRO DE DICHO BENEFICIARIO A UN ARCHIVO
    EXCEL*/
    public void exportar(String curp){
        try{
            String ruta = buscarRuta();//SE OBTIENE LA RUTA DONDE SE CREARA EL ARCHIVO EXCEL
            //SE OBTIENEN LOS DATOS DEL BENEFICIARIO DESDE LA BASE DE DATOS
            Object[] beneficiario = (Object[])new Conexion().getBeneficiario(curp).get(0);
            //SE CREA EL ARCHIVO EXCEL
            WritableWorkbook workbook = Workbook.createWorkbook(new File(ruta+"/"+beneficiario[1].toString()+".xls"));
            WritableSheet sheet = workbook.createSheet(beneficiario[1].toString(), 0);
            int bandera = 1;
            //SE DEFINE LA FUENTE Y TAMAÑO PARA EL ARCHIVO EXCEL
            WritableFont titleFont = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD);
            WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
            
            titleFormat.setAlignment(Alignment.CENTRE);//SE DEFINE QUE EL TEXTO SERA CENTRODO
            titleFormat.setBorder(Border.ALL, BorderLineStyle.DOTTED);//SE DEFINE EL BORDER DE LAS CELDAS
            
            //SE DEFINE LA FUENTE Y TAMAÑO DEL TEXTO PARA LAS SESIONES 
            WritableFont textFont = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
            WritableCellFormat textFormat = new WritableCellFormat(textFont);
            //SE DEFINE QUE SERA CENTRADO EL TEXTO DE LAS SESIONES
            textFormat.setAlignment(Alignment.CENTRE);
            textFormat.setBorder(Border.ALL, BorderLineStyle.DOTTED);
            textFormat.setBackground(Colour.AQUA);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            //SE AGREGAN COLUMNAS CON TEXTO 
            sheet.addCell(new jxl.write.Label(1,bandera,"FAMILIAS TRABAJANDO EN TALLERES",titleFormat));
            bandera++;
            sheet.addCell(new jxl.write.Label(1,bandera,"DE ARTES Y OFICIOS PARA PERSONAS AUTISTAS",titleFormat));
            bandera++;
            sheet.addCell(new jxl.write.Label(1,bandera,"Y OTRAS DISCAPACIDADES A.C.",titleFormat));
            
            //SE AGREGAN LAS COLUMNAS DE LOS DATOS DEL BENEFICIARIO
            bandera = 5;
            String[] encabezado = new String[]{"FOTO","CURP","NOMBRE","APELLIDO PATERNO","APELLIDO MATERNO","FECHA DE NACIMIENTO"};
            //SE AGREGA EL ENCABEZADO AL ARCHIVO EXCEL
            for(int i=0; i<encabezado.length; i++){
                sheet.addCell(new jxl.write.Label(i+1,bandera,encabezado[i],textFormat));
                CellView cell = sheet.getColumnView(i);
                cell.setAutosize(true);
                //cell.setSize(100);
                sheet.setColumnView(i, cell);
            }
            bandera++;
            //SE AGREGA LOS DATOS DEL BENEFICIARIO AL ARCHIVO EXCEL
            for(int i=0; i<beneficiario.length; i++){
                if(i==0){
                    ImageIcon imagen = (ImageIcon)beneficiario[i];//SE CONVIERTE LA FOTO DEL BENEFICIARIO A IMAGEICON
                    if(imagen!=null){
                        //SE CONVIERTE LA IMAGEICON A IMAGE
                        Image img = imagen.getImage();
                        BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2 = bi.createGraphics();
                        g2.drawImage(img, 0, 0, null);
                        g2.dispose();
                    
                        //SE CREA EL ARCHIVO FISICO DE LA FOTO
                        File foto = new File("temp/temp1.png");
                        ImageIO.write(bi, "png",foto);
                        //SE AGREGA LA FOTO AL ARCHIVO EXCEL
                        sheet.addImage(new jxl.write.WritableImage(i+1,bandera,1.0,1.0,foto));
                        CellView cell = sheet.getColumnView(i+1);
                        cell.setDimension(100);
                        cell.setAutosize(true);
                        sheet.setRowView(i+1,300);
                        sheet.setHeader("header 1","header 2","header 3");
                        sheet.setColumnView(i+1, cell);
                    }else{
                        JOptionPane.showMessageDialog(null,"El beneficiario no tiene foto disponible");
                    }
                }else{
                    sheet.addCell(new jxl.write.Label(i+1,bandera,beneficiario[i].toString(),textFormat));
                }
            }          
            //SE AGREGAN LAS COLUMNAS PARA LA SESIONES EN EL EXCEL
            bandera+=3;
            encabezado = new String[]{"HORA INICIO","HORA FIN","FECHA","INTENTOS","ACIERTOS"};
            for(int i=0; i<encabezado.length; i++){
                sheet.addCell(new Label(i+2,bandera,encabezado[i],textFormat));
            }
            bandera++;//8
            
            //SE OBTIENE EL HISTORIAL DEL BENEFICIARIO
            List historial = new Conexion().getHistorialSesiones(curp);
            //SE AGREGAN LA INFORMACION DE LAS SESIONES AL EXCEL
            for(int i=0; i<historial.size(); i++){
                Object[] fila = (Object[])historial.get(i);
                for(int j=1; j<fila.length; j++){
                    sheet.addCell(new jxl.write.Label(j+1,bandera,fila[j].toString()));
                }
                bandera++;
            }
            
            workbook.write();
            workbook.close();
            System.out.println("Ejemplo finalizado.");
        }catch (Exception ex){
            System.out.println("Error al crear el fichero.\n"+ex);
        }
        JOptionPane.showMessageDialog(null,"Exportado Correctamente");
    }
    
    /*METODO QUE SE ENCARGA DE BUSCAR LA RUTA MEDIANTE
    UN FILE CHOOSER Y DEVOLVER LA RUTA ELEGIDA*/
    private String buscarRuta(){
        String ruta = "";
        try{
            JFileChooser j = new JFileChooser("");
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            j.showSaveDialog(null);
            ruta = j.getSelectedFile().getCanonicalPath();
        }catch(Exception e){
            System.out.println(e);
        }
        return ruta.replace('\\', '/');
    }
}
