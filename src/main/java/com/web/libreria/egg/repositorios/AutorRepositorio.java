

package com.web.libreria.egg.repositorios;

import com.web.libreria.egg.entidades.Autor;

import org.springframework.data.jpa.repository.JpaRepository;



import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
   

    

}
