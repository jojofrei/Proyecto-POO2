/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author jojofrei
 */
public class HelperArchivo {
    //private static String rutaGeneral = Paths.get("").toAbsolutePath().toString();
    //public static String rutaArchivo = rutaGeneral+"/src/archivo";          
    //public static String rutaImagenesCliente = rutaGeneral+"/src/recursos/clientes";          
    //public static String rutaImagenesProductos = rutaGeneral+"/src/recursos/productos"; 
    
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
    
}
