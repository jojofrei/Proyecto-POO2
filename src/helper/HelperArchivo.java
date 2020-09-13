/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jojofrei
 */
public class HelperArchivo {
    private static String rutaGeneral = Paths.get("").toAbsolutePath().toString();
    public static String rutaArchivo = rutaGeneral+"/src/archivo";          
    public static String rutaImagenesCliente = rutaGeneral+"/src/recursos/clientes";          
    public static String rutaImagenesProductos = rutaGeneral+"/src/recursos/productos"; 
    
    public static ArrayList<String> leerLineasArchivo(String name)
    {    
        ArrayList<String> lineasArchivo = new ArrayList<>();        
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(name));
            String line = reader.readLine();
            while (line != null) 
            {    
                lineasArchivo.add(line);
                // read next line
                line = reader.readLine();                
            }
            reader.close();
        } catch (IOException e) {
                e.printStackTrace();
        }     
        return lineasArchivo;                 
    }
    
    public static void guardarRegistroArchivo(String archivo,String juego)
    {
        try(FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)){
            out.println(juego);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Permite actualizar el registro del puntaje del jugador 
    public static void actualizarRegistroJugador(String ruta,String jugadorVerificar,String nuevoNivel,String nuevoPuntaje)
    {
        Scanner x;
        String temporal = "temp.txt";
        File antiguoArchivo = new File(ruta);
        File nuevoArchivo   = new File(temporal);
        String jugador,nivel,puntaje = "";
        try
        {
            FileWriter fw = new FileWriter(temporal,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(ruta));
            x.useDelimiter("[,\n]");
            while(x.hasNext())
            {
                jugador      = x.next();
                nivel        = x.next();
                puntaje      = x.next();
                if(jugadorVerificar.equalsIgnoreCase(jugador))               
                    pw.println(jugadorVerificar+","+nuevoNivel+","+nuevoPuntaje);
                else
                    pw.println(jugador+","+nivel+","+puntaje);
                
            }
            x.close();
            pw.flush();
            pw.close();
            antiguoArchivo.delete();
            File dump = new File(ruta);
            nuevoArchivo.renameTo(dump);
        }
        catch(Exception ex)
        {
            
        }        
    }
}
