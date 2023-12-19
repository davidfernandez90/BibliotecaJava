/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class DAOSQLite {
    public DAOSQLite(String url) throws Exception{
        try {
            // db parameters
            
            // create a connection to the database
            conn = DriverManager.getConnection(driver+url);
            
            st = conn.createStatement();
                    
        } catch (SQLException ex) {
           throw new Exception("Error al conectar con la Base de Datos.\n verifique el archivo.");
           
        }    
    }
    
    public Libro BuscarLibro (String codigoLibro){
        Libro libro;
        String query = "SELECT * FROM Libros WHERE Cod_de_libro='" 
                + codigoLibro +"'"; 
        
        try {
           ResultSet rs = st.executeQuery(query);
           if (rs.next()){
            libro=new Libro();
            libro.setCodigoLibro(codigoLibro);
            libro.setAutor(rs.getString("Autor"));
            libro.setTitulo(rs.getString("Titulo"));
            libro.setPrecio(rs.getInt("Precio"));
            
            return libro;            
           }else
               return null;           
        } catch (Exception ex) {
            return null;
        }
        
          }
        
    public void insertarLibro (Libro libro) throws Exception{
        String query = "INSERT INTO Libros (Cod_de_libro,Titulo, Autor,Precio)"
                 + "VALUES ('" + libro.getCodigoLibro() + "','"+ 
                libro.getTitulo() +"', '" + libro.getAutor() + "'," + 
                libro.getPrecio() + ")";

        
        if (this.ExisteLibro(libro.getCodigoLibro()))
            throw new Exception("El libro ya existe.");
                
        
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
           throw new Exception("No se pudo insertar el Libro.");
        }
    } 
    
    public void Desconectar () throws Exception{
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new Exception("Error al desconectar.");
        }
    }
    
    private boolean ExisteLibro (String Cod_de_libro){
        String query = "SELECT * FROM Libros WHERE Cod_de_libro= '" 
                + Cod_de_libro +"'"; 
        
        try {
           ResultSet rs = st.executeQuery(query);
           return (rs.next());
        } catch (SQLException ex) {
            return false;
        }
        
     
    }
     
     void BorrarLibro(String CodigoLibro) throws Exception {
         String query = "DELETE FROM Libros WHERE Cod_de_libro='" + 
                 CodigoLibro + "'";
         
        if(!this.ExisteLibro(CodigoLibro))
            throw new Exception("El libro no existe y no puede ser borrado.");
         
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            throw new Exception("No es posible eliminar el libro.");
        }
        
    }
     
    public void ModificarLibro(Libro libro) throws Exception { //
        String query = "UPDATE Libros SET Titulo='" + libro.getTitulo() + 
                "', Autor='" + libro.getAutor() + "', Precio=" + libro.getPrecio() 
                + " WHERE Cod_de_libro='" + libro.getCodigoLibro() + "'";
        
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            throw new Exception("No es posible modificar al libro."); 
        }
        
    }

    
        
    private static String driver = "jdbc:sqlite:";
    //pongo driver, por que el URL la pasa el usuario
    private Connection conn = null;
    private Statement st = null; 

    

   
            
}
