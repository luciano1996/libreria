/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.libreria.entidades.servicios;

import edu.egg.libreria.errores.ErrorServicio;
import edu.egg.libreria.entidades.Autor;
import edu.egg.libreria.entidades.Editorial;
import edu.egg.libreria.entidades.Libro;
import edu.egg.libreria.repositorios.AutorRepositorio;
import edu.egg.libreria.repositorios.EditorialRepositorio;
import edu.egg.libreria.repositorios.LibroRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucho
 */
public class LibroService {
    @Autowired
    private LibroRepositorio librorepositorio;
    @Autowired
    private AutorRepositorio autorrepositorio;
    @Autowired
    private EditorialRepositorio editorialrepositorio;
    
     public void agregarLibro(String ISBN, String titulo,int anio, int ejemplares,String idautor, String ideditorial ) throws ErrorServicio{
    Autor autor= autorrepositorio.findById(idautor).get();
   Editorial editorial= editorialrepositorio.findById(ideditorial).get();
         validar(ISBN,titulo,anio,ejemplares,autor,editorial);
    Libro libro= new Libro();
    libro.setTitulo(titulo);
    libro.setISBN(ISBN);
    libro.setAnio(anio);
    libro.setEjemplares(ejemplares);
    librorepositorio.save(libro);
    }
    
    public void eliminarLibro(String idlibro) throws ErrorServicio{
        
        Optional<Libro> respuesta = librorepositorio.findById(idlibro);
        if(respuesta.isPresent()){
            Libro libro= respuesta.get();
             librorepositorio.delete(libro);
        }
        else{
        throw new ErrorServicio("No se encontro el libro");
        }
       
    }
    public void validar(String ISBN, String titulo,int anio, int ejemplares,Autor autor,Editorial editorial) throws ErrorServicio{
           if(ISBN == null || ISBN.isEmpty()){
            throw new ErrorServicio("El nombre del usuario no puede ser nulo.");
        }

        if(titulo == null || titulo.isEmpty()){
            throw new ErrorServicio("El apellido del usuario no puede ser nulo.");
        }
        if(autor.getNombre()==null){
            throw new ErrorServicio("El nombre del autor no puede ser nulo.");
        }
        if(editorial.getNombre()==null){
          throw new ErrorServicio("el nombre de la editorial no puede ser nulo.");
        }
    }
}
