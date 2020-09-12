package modelo;

import java.util.ArrayList;

public class Juego 
{
    private String nombreUsuario;
    private int nivelEnCurso;
    private ArrayList<Cliente> listaClientes;
    private double dineroAcumulado;
    
    public Juego()
    {
        
    }
        
    public Juego(String nombreUsuario, int nivelEnCurso,ArrayList<Cliente> clientes, double dineroAcumulado)
    {
        this.nombreUsuario   = nombreUsuario;
        this.nivelEnCurso    = nivelEnCurso;
        this.listaClientes   = clientes;
        this.dineroAcumulado = dineroAcumulado;
    }
    
    public Juego(String nombreUsuario, int nivelEnCurso, double dineroAcumulado)
    {
        this.nombreUsuario   = nombreUsuario;
        this.nivelEnCurso    = nivelEnCurso;        
        this.dineroAcumulado = dineroAcumulado;
    }
    
    public String getNombreUsuario()
    {
        return this.nombreUsuario;
    }
    
    public int getNivelEnCurso()
    {
        return this.nivelEnCurso;
    }
    
    public ArrayList<Cliente> getListaClientes()
    {
        return this.listaClientes;
    }
    
    public double getDineroAcumulado()
    {
        return this.dineroAcumulado;
    }      
}
