package edu.egg.libreria.entidades.servicios;

import edu.egg.libreria.errores.ErrorServicio;
import edu.egg.libreria.entidades.Autor;
import edu.egg.libreria.repositorios.AutorRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    @Autowired
    private AutorRepositorio autorrepositorio;
    
    public void agregarAutor(String nombre) throws ErrorServicio{
    
         if(nombre == null || nombre.isEmpty()){
            throw new ErrorServicio("El nombre del Autor  no puede ser nulo.");
        }
    Autor autor= new Autor();
    autor.setNombre(nombre);
    autorrepositorio.save(autor);
    }
    
    public void eliminarAutor(String idautor) throws ErrorServicio{
        
        Optional<Autor> respuesta = autorrepositorio.findById(idautor);
        if(respuesta.isPresent()){
            Autor autor= respuesta.get();
             autorrepositorio.delete(autor);
        }
        else{
        throw new ErrorServicio("No se encontro el autor");
        }
       
    }
    
}
