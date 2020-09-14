package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import modelo.Cliente;
import modelo.Juego;
import modelo.Producto;
import modelo.Puntaje;

public class HelperJuego 
{            
    //Se ingresa usuario :
    //Se verifica si el archivo existe, sino se crea el archivo 
    //Si Existe el archivo se va a buscar en el mismo la informacion del usuario
    //Se obtiene el objeto Juego si este existe
    //Si no se graba el nuevo usuario con puntaje0 y nivel 1
  
    
    public static Juego validarIngresoJugador(String usuario)
    {
       Juego juego;
       String rutaPuntaje = "src/recursos/puntaje.txt";
       try 
        {
            File archivo = new File(rutaPuntaje);
            if(!archivo.exists())
                archivo.createNewFile();
            juego = obtenerUsuarioJuego(usuario, rutaPuntaje);                        
            return juego;            
        }
        catch(Exception ex)
        {
            return null;
        }       
    }    
            
    public static Juego obtenerUsuarioJuego(String jugador,String archivo)
    {
        ArrayList<String> listaJuegos = helper.HelperArchivo.leerLineasArchivo(archivo);
        
        //Obtiene el jugador existente en el archivo       
        for(String lineaJuego : listaJuegos)
        {
            String lineaJuegoPart[] = lineaJuego.split(",");
            String usuario = lineaJuegoPart[0];
            String nivel   = lineaJuegoPart[1];
            String puntaje = lineaJuegoPart[2];
            if(jugador.equalsIgnoreCase(usuario))
                return new Juego(usuario,Integer.parseInt(nivel),Double.parseDouble(puntaje));
        }        
        
        Juego juego = new Juego(jugador,1,0);
        
        //Se procede a guardar el jugador en el archivo puntaje
        String lineaJuego = juego.getNombreUsuario()+","+juego.getNivelEnCurso()+","+juego.getDineroAcumulado();
        helper.HelperArchivo.guardarRegistroArchivo(archivo, lineaJuego);        
        return juego;
    }
