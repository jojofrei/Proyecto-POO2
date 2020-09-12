package modelo;

import java.util.ArrayList;

public class Cliente {
    private int numeroCliente;
    private int paciencia;
    private ArrayList<Producto> productosOrdenados;
    private boolean ordenServida;
    private String nombreImagen;
    private boolean mostrarEnJuego;

    public Cliente()
    {
        
    }
    
    public Cliente(int numeroCliente, int paciencia, ArrayList<Producto> productos, 
                   boolean ordenServida, String nombreImagen, boolean mostrarEnJuego)
    {
        this.numeroCliente       = numeroCliente;
        this.paciencia           = paciencia;
        this.productosOrdenados  = productos;
        this.ordenServida        = ordenServida;
        this.nombreImagen        = nombreImagen;
        this.mostrarEnJuego      = mostrarEnJuego;
    }
    
    public int getNumeroCliente()
    {
        return this.numeroCliente;
    }
    
    public int getPaciencia()
    {
        return this.paciencia;
    }
    
    public ArrayList<Producto> getProductosOrdenados()
    {
        return this.productosOrdenados;
    }
    
    public String getNombreImagen()
    {
        return this.nombreImagen;
    }
    
    public void setPaciencia(int paciencia)
    {
        this.paciencia = paciencia;
    }
    
    public void setOrdenServida(boolean estado)
    {
        this.ordenServida = estado;
    }        
}
