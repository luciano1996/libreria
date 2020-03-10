package edu.egg.libreria.repositorios;

import edu.egg.libreria.entidades.Prestamo;
import java.util.List;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo,String>{
    @Query("SELECT c FROM Prestamo WHERE c.libro.titulo=:titulo")
    public List<Prestamo> buscarPorLibroTitulo(@Param("titulo") String titulo);
    @Query("SELECT c FROM Prestamo WHERE c.cliente.documento=:documento")
    public List<Prestamo> buscarPorCliente(@Param("titulo") int documento);
    @Query("SELECT c FROM Prestamo WHERE c.libro.ISBN=:ISBN")
    public List<Prestamo> buscarPorLibroISBN(@Param("titulo") String ISBN);
}
