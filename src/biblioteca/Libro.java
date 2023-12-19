/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

/**
 *
 * @author Damian
 */
public class Libro {

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) throws Exception {
        if(precio<=0)
            throw new Exception("El valor del libro debe ser mayor que 0");
        this.precio = precio;
    }
    String codigoLibro, titulo, autor;
    int precio; 
   

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) throws Exception {
        if(codigoLibro.isEmpty())
            throw new Exception("Debe agregar un Codigo de Libro.");
        
        this.codigoLibro=codigoLibro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) throws Exception {
        if(autor.isEmpty())
            throw new Exception("Debe agregar un Autor.");
            
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws Exception {
        if(titulo.isEmpty())
            throw new Exception("Debe agregar un Titulo.");
        
        this.titulo = titulo;
    }
    
    
}
