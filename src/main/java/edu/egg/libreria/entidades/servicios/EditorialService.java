package edu.egg.libreria.entidades.servicios;


import edu.egg.libreria.errores.ErrorServicio;
import edu.egg.libreria.entidades.Editorial;
import edu.egg.libreria.repositorios.EditorialRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EditorialService {
    @Autowired
    private EditorialRepositorio editorialrepositorio;
    public void agregarEditorial(String nombre) throws ErrorServicio{
    
         if(nombre == null || nombre.isEmpty()){
            throw new ErrorServicio("El nombre del Editorial  no puede ser nulo.");
        }
    Editorial editorial= new Editorial();
    editorial.setNombre(nombre);
    editorialrepositorio.save(editorial);
    }
    
    public void eliminarEditorial(String ideditorial) throws ErrorServicio{
        
        Optional<Editorial> respuesta = editorialrepositorio.findById(ideditorial);
        if(respuesta.isPresent()){
            Editorial editorial= respuesta.get();
             editorialrepositorio.delete(editorial);
        }
        else{
        throw new ErrorServicio("No se encontro la editorial");
        }
       
    }
    
}
