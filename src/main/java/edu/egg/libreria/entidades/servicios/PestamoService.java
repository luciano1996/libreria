/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.libreria.entidades.servicios;

import edu.egg.libreria.entidades.Cliente;
import edu.egg.libreria.entidades.Libro;
import edu.egg.libreria.entidades.Prestamo;
import edu.egg.libreria.errores.ErrorServicio;
import edu.egg.libreria.repositorios.ClienteRepositorio;
import edu.egg.libreria.repositorios.LibroRepositorio;
import edu.egg.libreria.repositorios.PrestamoRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lucho
 */
public class PestamoService {
    @Autowired
    private PrestamoRepositorio prestamorepositorio;
    @Autowired
    private LibroRepositorio librorepositorio;
    @Autowired
    private ClienteRepositorio clienterepositorio;
    
    public void agregarPrestamo(String ISBN, String idcliente,Date fecha ) throws ErrorServicio{
        Optional<Libro> respuesta = librorepositorio.findById(ISBN);
        if (respuesta.isPresent()) {
            
            Optional<Cliente> respuesta2 = clienterepositorio.findById(idcliente);
            if (respuesta.isPresent()) {
                
            Libro libro= respuesta.get();
            Cliente cliente= respuesta2.get();
            Prestamo prestamo= new Prestamo();
            prestamo.setCliente(cliente);
            prestamo.setLibro(libro);
            prestamo.setFecha(new Date());
            prestamo.setDevolucion(fecha);
            prestamorepositorio.save(prestamo);
            }else{
        throw new ErrorServicio ("El cliente especificado no existe");
        }
        }else{
        throw new ErrorServicio ("El libro especificado no existe");
        }
    }
    
    public void eliminarPrestamo(String idprestamo) throws ErrorServicio{
        
        Optional<Prestamo> respuesta = prestamorepositorio.findById(idprestamo);
        if(respuesta.isPresent()){
            Prestamo prestamo= respuesta.get();
             prestamorepositorio.delete(prestamo);
        }
        else{
        throw new ErrorServicio("No se encontro el libro");
        }
       
    }
   
}
