

package com.web.libreria.egg.controladores;

import com.web.libreria.egg.entidades.Editorial;
import com.web.libreria.egg.errores.ErrorServicio;
import com.web.libreria.egg.repositorios.EditorialRepositorio;
import com.web.libreria.egg.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller

public class EditorialController {
    @Autowired
    EditorialServicio editorialServicio;
    @Autowired
    EditorialRepositorio editorialRepositorio;

    @GetMapping("/editorial")
    public String editorial(ModelMap modelo) {
        List<Editorial> editoriales = editorialRepositorio.findAll();
        modelo.put("editoriales", editoriales);

        return "editorial";
    }

    @GetMapping("/editar_editorial")
    public String editar_editorial() {return "editar_editorial.html";
    }

    @PostMapping("/editorialRegistro")
    public String editorialRegistro(@RequestParam String nombre) {
        /*System.out.println("nombre"+nombre);//Metodo para saber si comunica mi controlador*/
        try {
            editorialServicio.guardarEditorial(nombre);
        } catch (ErrorServicio errorServicio) {
            Logger.getLogger(EditorialController.class.getName()).log(Level.SEVERE, null, errorServicio);
        }
        return "editorial.html";
    }
}

