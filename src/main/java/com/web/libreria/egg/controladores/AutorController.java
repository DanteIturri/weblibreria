package com.web.libreria.egg.controladores;


import com.web.libreria.egg.entidades.Autor;
import com.web.libreria.egg.errores.ErrorServicio;
import com.web.libreria.egg.repositorios.AutorRepositorio;
import com.web.libreria.egg.servicios.AutorServicio;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class AutorController {
    @Autowired
    private AutorServicio autorservicio;
    @Autowired
    private AutorRepositorio autorrepositorio;

    @GetMapping("/autor")
    public String autor(ModelMap modelo) {
        List<Autor> autores = autorrepositorio.findAll();
        modelo.put("autores", autores);
        return "autor.html";
    }


    @GetMapping("/editar_autor")
    public String editar_autor() {
        return "editar_autor.html";
    }

    @PostMapping("/autorRegistro")
    public String autorRegistro(@RequestParam String nombre) {

//        System.out.println("nombre: " +nombre);

        try {
            autorservicio.guardarAutor(nombre);
        } catch (ErrorServicio ex) {
            Logger.getLogger(AutorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "autor.html";
    }
}
