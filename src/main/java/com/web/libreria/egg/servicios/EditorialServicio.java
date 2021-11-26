

package com.web.libreria.egg.servicios;


import com.web.libreria.egg.entidades.Editorial;
import com.web.libreria.egg.errores.ErrorServicio;
import com.web.libreria.egg.repositorios.EditorialRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio EditorialRepositorio;
    public void guardarEditorial( String nombre) throws ErrorServicio{
        /*Valido el nombre de la editorial*/
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        
        EditorialRepositorio.save(editorial);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void modificarAutor( String idEditorial, String nombre) throws ErrorServicio {
        Optional<Editorial> respuesta = EditorialRepositorio.findById(idEditorial);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
        } else {
            throw new ErrorServicio("No se Encontro el Autor a modificar");
        }
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void bajaEditorial( String idEditorial) throws ErrorServicio {
        Optional<Editorial> respuesta = EditorialRepositorio.findById(idEditorial);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(false);
        } else {
            throw new ErrorServicio("No se Encontro el Editorial");
        }
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void altaEditorial(String idEditorial) throws ErrorServicio {
        Optional<Editorial> respuesta = EditorialRepositorio.findById(idEditorial);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(true);
        } else {
            throw new ErrorServicio("No se Encontro el Editorial");
        }
    }
    private void validar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El nombre de la Editorial  no puede estar vacío");
        }
        
    }
}