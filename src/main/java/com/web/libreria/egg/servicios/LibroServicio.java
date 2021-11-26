/*
--------------------------------------------------------
·Los servicios van ejecutar las funcionalidades 
para que la aplicacion cumple las peticiones del usuario
--------------------------------------------------------
 */
package com.web.libreria.egg.servicios;



import com.web.libreria.egg.entidades.Autor;
import com.web.libreria.egg.entidades.Editorial;
import com.web.libreria.egg.entidades.Libro;
import com.web.libreria.egg.errores.ErrorServicio;
import com.web.libreria.egg.repositorios.AutorRepositorio;
import com.web.libreria.egg.repositorios.EditorialRepositorio;
import com.web.libreria.egg.repositorios.LibroRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Marcamos la clase como un servicio con  => @Service y con esto declaramos que
 * es un servicio de Spring
 */
@Service
public class LibroServicio  {

    @Autowired//Esta variable se inicializa en el servidor de aplicaciones
    private LibroRepositorio libroRepositorio;
    
   
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    /*
     * En la clase vamos a crear las funcionalidades necesarias para administrar
     * los Libros ( Consulta, creación, modificación y dar de baja );
     */
    @Transactional
    public void guardarLibros(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, String idAutor, String idEditorial) throws ErrorServicio {
        
        comprobarDatos( isbn, titulo, anio, ejemplares);

        Autor autor = autorRepositorio.findById(idAutor).get();/* !*Busco el autor por id lo inserto en objeto */
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();/* !*Busco la Editorial   por id lo inserto en objeto */
        //Comprobación de los datos
        // Creo un objeto & Seteo los atributos del clase Libro
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEmplares(ejemplares);
        libro.setAlta(true);// Seteo el alta del libro en true
        libro.setEmplaresPrestados(ejemplaresPrestados);
        libro.setEmplaresRestantes(ejemplaresRestantes);
        //metodo para guardar Autor
        //metodo para guardar Editorial
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);// LLamo el metodo para presistir en base de datos dentro del repositorio

    }

    /*
     * Metodo para modificar los libros registradps
     */
    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares,String nombre, String nombreEditorial) throws ErrorServicio {

        comprobarDatos(isbn, titulo, anio, ejemplares);

        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEmplares(ejemplares);
//            libro.setAutor(autorServicio.guardarAutor(nombre));
//            libro.setEditorial(editorialServicio.guardarEditorial(nombreEditorial));

            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("no se encontro el Libro selecionado");
        }

    }
    @Transactional
    public void bajaLibro(String id) throws ErrorServicio {
      
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(false);

            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("no se encontro el Libro selecionado");
        }

    }
    @Transactional
    public void AltaLibro(String id) throws ErrorServicio {
      
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(true);

            libroRepositorio.save(libro);
        } else {
            throw new ErrorServicio("no se encontro el Libro selecionado");
        }

    }
    
        

    private void comprobarDatos( Long isbn, String titulo, Integer anio, Integer ejemplares) throws ErrorServicio {
        if (isbn == 0) {

            throw new ErrorServicio("El isbn no puede ser nulo");

        }
        // Compruebo si elvalor es nulo o esta vacío
        if (titulo == null || titulo.isEmpty()) {

            throw new ErrorServicio("El titulo no puede estar vacio");

        }
        if (anio == 0) {

            throw new ErrorServicio("Debe colocar un año");

        }
        if (ejemplares == 0) {

            throw new ErrorServicio("Debe colocar la cantidad de ejemplares");
        }
    }
}
