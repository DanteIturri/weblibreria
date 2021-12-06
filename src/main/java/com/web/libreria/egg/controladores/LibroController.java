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

import org.springframework.web.bind.annotation.PathVariable;
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

//* Envío de datos de autores, editoriales y Libros
    @GetMapping("/libros")
    public String libros(ModelMap modelo) {
        List<Autor> autores = autorrepositorio.findAll();//* guardo en una lista todos los autores
        modelo.put("autores", autores);//* envio la lista de autores al modelo
        List<Editorial> editoriales = editorialrepositorio.findAll();//* guardo en una lista todas las editoriales
        modelo.put("editoriales", editoriales);//* envio la lista de editoriales al modelo
        List<Libro> libros = librorepositorio.findAll();//* guardo en una lista todos los libros
        modelo.put("libros", libros);//* envio la lista de libros al modelo
        return "libros";
    }

    @GetMapping("/editar_libro/{id}")//! uso de path variable
    public String editar_libro(@PathVariable String id, ModelMap modelo) {

        modelo.put("libro",libroservicio.InsertarLibroPorId(id));
        List<Autor> autores = autorrepositorio.findAll();//* guardo en una lista todos los autores
        modelo.put("autores", autores);//* envio la lista de autores al modelo
        List<Editorial> editoriales = editorialrepositorio.findAll();//* guardo en una lista todas las editoriales
        modelo.put("editoriales", editoriales);//* envio la lista de editoriales al modelo
        return "editar_libro";
    }

//* metodo para guardar libro
    @PostMapping("/libros")
    public String libros(ModelMap modelo, @RequestParam(defaultValue = "0") Long isbn, @RequestParam String titulo, @RequestParam(defaultValue = "0") Integer anio, @RequestParam(defaultValue = "0") Integer ejemplares, @RequestParam(defaultValue = "0") Integer ejemplaresPrestados, @RequestParam(defaultValue = "0") Integer ejemplaresRestantes, @RequestParam(defaultValue = "null") String idAutor, @RequestParam(defaultValue = "null") String idEditorial) {
        //! Creo una lista de autores  para mostrar en pagina de post
        List<Autor> autores = autorrepositorio.findAll();
        //? envio la lista a traves del modelo
        modelo.put("autores", autores);
        //! Creo una lista de editoriales  para mostrar en pagina de post
        List<Editorial> editoriales = editorialrepositorio.findAll();
        //? envio la lista a traves del modelo
        modelo.put("editoriales", editoriales);
        List<Libro> libros = librorepositorio.findAll();
        modelo.put("libros", libros);
//todo        System.out.println("id Autor: " + idAutor);   para ver si funciona
//todo       System.out.println("id Editorial: " + idEditorial); para ver si funciona
        try {
            libroservicio.guardarLibros(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idAutor, idEditorial);
        } catch (ErrorServicio ex) {
            /*Logger.getLogger(LibroController.class.getName()).log(Level.SEVERE, null, ex);*/

            modelo.put("error", ex.getMessage());
            return "/libros";
        }
        return "libros";
    }
    @PostMapping("/editar_libro/{id}")
    public String editar_libro(ModelMap modelo, @PathVariable String id, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes, @RequestParam String idAutor, @RequestParam String idEditorial) {

        System.out.println("id: " + id);
        System.out.println("titulo: " + titulo);
      try{
        libroservicio.modificarLibro(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idAutor, idEditorial);
      }catch (ErrorServicio ex){
           modelo.put("error", ex.getMessage());
      }
  return "redirect:/libros";

    }

}
