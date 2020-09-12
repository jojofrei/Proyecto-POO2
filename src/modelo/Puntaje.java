package modelo;

public class Puntaje {
    private String jugador;
    private int nivel;
    private double puntaje;
    
    public Puntaje()
    {
        
    }
    
    public Puntaje(String jugador, int nivel, double puntaje)
    {
        this.jugador = jugador;
        this.nivel   = nivel;
        this.puntaje = puntaje;
    }
    
    public String getJugador()
    {
        return this.jugador;
    }
    
    public int getNivel()
    {
        return this.nivel;
    }
    
    public double getPuntaje()
    {
        return this.puntaje;
    }            
}
