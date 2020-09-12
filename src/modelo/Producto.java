package modelo;

public class Producto 
{
    private String nombre;
    private String categoria;
    private String nombreImagen;
    
    public Producto()
    {
        
    }
            
    public Producto(String nombre, String categoria, String nombreImagen)
    {
        this.nombre       = nombre;
        this.categoria    = categoria;
        this.nombreImagen = nombreImagen;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    public String getCategoria()
    {
        return this.categoria;
    }
    
    public String getNombreImagen()
    {
        return this.nombreImagen;
    }
    
}
