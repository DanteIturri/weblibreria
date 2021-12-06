/**
 * El repositorio es el encargado de persistir(o se a de guardar)
 * nuestros objetos creados en la capa servicio en la base de datos.
*/

package com.web.libreria.egg.repositorios;

import com.web.libreria.egg.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{



}
