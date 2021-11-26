package com.web.libreria.egg.servicios;

import com.web.libreria.egg.entidades.Autor;
import com.web.libreria.egg.errores.ErrorServicio;
import com.web.libreria.egg.repositorios.AutorRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {
    
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}) 
    public void guardarAutor(String nombre) throws ErrorServicio {
        
        validar(nombre);//metodo validar
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
        
        autorRepositorio.save(autor);
        
        
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void modificarAutor( String idAutor, String nombre) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepositorio.findById(idAutor);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
        } else {
            throw new ErrorServicio("No se Encontro el Autor a modificar");
        }
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void bajaAutor( String idAutor) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepositorio.findById(idAutor);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);
        } else {
            throw new ErrorServicio("No se Encontro el Autor para la baja");
        }
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void altaAutor(String idAutor) throws ErrorServicio {
        Optional<Autor> respuesta = autorRepositorio.findById(idAutor);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(true);
        } else {
            throw new ErrorServicio("No se Encontro el Autor para el alta");
        }
    }
    
   
    
    
    private void validar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            
            throw new ErrorServicio("El nombre del Autor no puede estar vacío");
        }
        
    }
}
