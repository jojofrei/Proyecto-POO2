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
     public static void actualizarRegistroUsuario(String nombreJugador,int nivel, double puntaje)
    {
        String rutaPuntaje = "src/recursos/puntaje.txt";        
        helper.HelperArchivo.actualizarRegistroJugador(rutaPuntaje, nombreJugador, String.valueOf(nivel), String.valueOf(puntaje));        
    }
        
    public static ArrayList<String> getCategoriasProducto()
    {
        String categoriasProductos = helper.Constantes.categoriasProductos;        
        String[] categoriaPart = categoriasProductos.split(",");
        ArrayList<String> listaCategorias = new ArrayList<>();       
        int indiceCategoriaRandom;        
        int i = 0;
        int verificadorRandom = 0;
        while(i < 2)
        {
            indiceCategoriaRandom = new Random().nextInt(3);
            if(indiceCategoriaRandom != verificadorRandom)
            {
                verificadorRandom = indiceCategoriaRandom;                
                listaCategorias.add(categoriaPart[indiceCategoriaRandom]);           
                i++;
            }
        }
        return listaCategorias;
    }
    public static ArrayList<Producto> getListaProductosCategoria(String categoria, int cantProductosMostrar)
    {
        String rutaProductos = helper.HelperArchivo.rutaArchivo+"/catalogoImagenes.csv";                
        ArrayList<Producto> listaProductos = getListaProductos(rutaProductos);                           
        ArrayList<Producto> listaProductosPorCategoria = new ArrayList<>();
        ArrayList<Producto> listaProductosCategoria = getListaProductosPorCategoria(categoria,listaProductos);
        int cantProductos = listaProductosCategoria.size();        
        int i = 0;
        int indiceRandom = 0;
        while(i < cantProductosMostrar)
        {
            indiceRandom = new Random().nextInt(cantProductos);            
            if(!listaProductosPorCategoria.contains(listaProductosCategoria.get(indiceRandom)))
            {
                listaProductosPorCategoria.add(listaProductosCategoria.get(indiceRandom));
                i++;
            }
        }
        return listaProductosPorCategoria;
    }
    
    public static ArrayList<Producto> getListaProductosPorCategoria(String categoria, ArrayList<Producto> productos)
    {
        ArrayList<Producto> listaProductosCategoria = new ArrayList<>();       
        for(Producto producto : productos)
        {            
            if(categoria.equalsIgnoreCase(producto.getCategoria()))                                
                listaProductosCategoria.add(producto);            
        }       
        return listaProductosCategoria;
    }public static ArrayList<Cliente> getListaRandomClientes(ArrayList<Producto> listaProductos)
    {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        //Generacion de cantidad de clientes
        int cantClientes  = 1 + new Random().nextInt(3);
        int cantMaximaClientes = Constantes.CANT_MAX_CLIENTES_MOSTRAR;
                
        int numeroCliente,pacienciaCliente;
        String nombreImagen;
        boolean ordenServida = false;
        boolean mostrarEnJuego = false;
        ArrayList<Producto> listaProductosOrdenados;
        for(int i = cantClientes; i<= cantMaximaClientes ; i++)
        {            
            //Generacion de datos del cliente
            numeroCliente = i;
            
            //Generacion de paciencia del cliente            
            pacienciaCliente = 3 + new Random().nextInt(Constantes.MAXIMA_PACIENCIA_CLIENTE);            
            
            listaProductosOrdenados = getListaProductosOrdenados(listaProductos);            
            nombreImagen = "cli"+i+".png";
            listaClientes.add(new Cliente(numeroCliente,
                                          pacienciaCliente,
                                          listaProductosOrdenados,
                                          ordenServida,
                                          nombreImagen,
                                          mostrarEnJuego));
        }        
        return listaClientes;
    }
        
    
    public static ArrayList<Producto> getListaProductosOrdenados(ArrayList<Producto> listaProductos)
    {
        //Generacion de Cantidad de opciones del menu
        int opcionesMenu = 1 + new Random().nextInt(3);
        int cantMaximaOpcionesMenu = Constantes.CANT_MAX_OPCIONES_MENU;
        
        //Lista de Productos Totales        
        int cantTotalProductos = listaProductos.size();        
        ArrayList<Producto> listaProductosOrdenados = new ArrayList<>();
        int indiceProductoGenerado;
        for(int i = opcionesMenu ; i <= cantMaximaOpcionesMenu ; i++)
        {
            //Generar producto Aleatorio
            indiceProductoGenerado = new Random().nextInt(cantTotalProductos);
            
            if(!listaProductosOrdenados.contains(listaProductos.get(indiceProductoGenerado)))
                listaProductosOrdenados.add(listaProductos.get(indiceProductoGenerado));
        }
        return listaProductosOrdenados;
    }
    
    public static ArrayList<Producto> getListaProductos(String rutaProductos)
    {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ArrayList<String> listaProductosArchivo = helper.HelperArchivo.leerLineasArchivo(rutaProductos);
        String nombreProducto,categoriaProducto,urlProducto = "";
        for(String productoString : listaProductosArchivo)
        {            
            String[] productoPart = productoString.split(",");
            nombreProducto       = productoPart[2];
            categoriaProducto    = productoPart[1];
            urlProducto          = productoPart[0];
            listaProductos.add(new Producto(nombreProducto, categoriaProducto, urlProducto));                        
        }
        return listaProductos;
    }
    
    public static boolean verificarEstadoOrdenServida(ArrayList<Producto> listaProductosCliente, ArrayList<Producto> listaProductosCocina)
    {
        boolean estadoOrdenServida = false;
        
        System.out.println("productos cliente");
        for(Producto producto: listaProductosCliente)
            System.out.println("nombre="+producto.getNombre()+",categoria="+producto.getCategoria());
        System.out.println("");
        System.out.println("productos cocinandose");
        for(Producto producto: listaProductosCocina)
            System.out.println("nombre="+producto.getNombre()+",categoria="+producto.getCategoria());
                                
        if(listaProductosCocina.containsAll(listaProductosCliente))
            estadoOrdenServida = true;
        return estadoOrdenServida;
        
    }
    
    public static int chequearSiEsSiguienteNivel(double dineroAcumulado,int nivel)
    {
        int siguienteNivel = 0;
        switch(nivel)
        {
            case 1:
                if(dineroAcumulado > Constantes.CANT_MAX_DINERO_GANADO_MENU_NIVEL1)
                    siguienteNivel = 2;
                else
                    siguienteNivel = nivel;
                break;
                
            case 2:
                if(dineroAcumulado > Constantes.CANT_MAX_DINERO_GANADO_MENU_NIVEL2)
                    siguienteNivel = 3;
                else
                    siguienteNivel = nivel;
                break;
                
            case 3:
                if(dineroAcumulado > Constantes.CANT_MAX_DINERO_GANADO_MENU_NIVEL3)
                    siguienteNivel = 4;
                else
                    siguienteNivel = nivel;
                break;
        }
        return siguienteNivel;
    }
    
