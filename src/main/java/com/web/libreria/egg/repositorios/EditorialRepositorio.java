package com.web.libreria.egg.repositorios;

import com.web.libreria.egg.entidades.Editorial;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

 
    
}
