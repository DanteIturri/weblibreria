package com.web.libreria.egg.controladores;

import com.web.libreria.egg.entidades.Autor;

import com.web.libreria.egg.entidades.Editorial;
import com.web.libreria.egg.entidades.Libro;
import com.web.libreria.egg.errores.ErrorServicio;
import com.web.libreria.egg.repositorios.AutorRepositorio;
import com.web.libreria.egg.repositorios.EditorialRepositorio;
import com.web.libreria.egg.repositorios.LibroRepositorio;
import com.web.libreria.egg.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LibroController {
    @Autowired
    private LibroServicio libroservicio;
    @Autowired
    private AutorRepositorio autorrepositorio;
    @Autowired
    private EditorialRepositorio editorialrepositorio;
    @Autowired
    private LibroRepositorio librorepositorio;


    @GetMapping("/libros")
    public String libros(ModelMap modelo) {
        List<Autor> autores = autorrepositorio.findAll();
        modelo.put("autores", autores);
        List<Editorial> editoriales = editorialrepositorio.findAll();
        modelo.put("editoriales", editoriales);
        List<Libro> libros = librorepositorio.findAll();
        modelo.put("libros", libros);
        return "libros";
    }

    @GetMapping("/editar_libro")
    public String editar_libro() {
        return "editar_libro";
    }


    @PostMapping("/libros")
    public String libros(ModelMap modelo, @RequestParam(defaultValue = "0") Long isbn, @RequestParam String titulo, @RequestParam(defaultValue = "0") Integer anio, @RequestParam(defaultValue = "0") Integer ejemplares, @RequestParam(defaultValue = "0") Integer ejemplaresPrestados, @RequestParam(defaultValue = "0") Integer ejemplaresRestantes, @RequestParam(defaultValue = "null") String idAutor, @RequestParam(defaultValue = "null") String idEditorial) {
        List<Autor> autores = autorrepositorio.findAll();
        modelo.put("autores", autores);
        List<Editorial> editoriales = editorialrepositorio.findAll();
        modelo.put("editoriales", editoriales);
        List<Libro> libros = librorepositorio.findAll();
        modelo.put("libros", libros);
//        System.out.println("id Autor: " + idAutor); // * para ver si funciona
//        System.out.println("id Editorial: " + idEditorial);// * para ver si funciona
        try {
            libroservicio.guardarLibros(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idAutor, idEditorial);
        } catch (ErrorServicio ex) {
            /*Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);*/

            modelo.put("error", ex.getMessage());
            return "/libros";
        }
        return "libros";
    }

}
