package com.web.libreria.egg.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity//*declaro la entidad
public class Libro {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Long isbn;
    private String titulo;
    private Integer anio;
    private Integer emplares;
    private Integer emplaresPrestados;
    private Integer emplaresRestantes;
    private Boolean alta;
        
    
    //Relaciones
    @ManyToOne
    @JoinColumn
    private Autor autor;

    //Relaciones
    @ManyToOne
    @JoinColumn
    private Editorial editorial;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the isbn
     */
    public Long getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    /**
     * @return the emplares
     */
    public Integer getEmplares() {
        return emplares;
    }

    /**
     * @param emplares the emplares to set
     */
    public void setEmplares(Integer emplares) {
        this.emplares = emplares;
    }

    /**
     * @return the emplaresPrestados
     */
    public Integer getEmplaresPrestados() {
        return emplaresPrestados;
    }

    /**
     * @param emplaresPrestados the emplaresPrestados to set
     */
    public void setEmplaresPrestados(Integer emplaresPrestados) {
        this.emplaresPrestados = emplaresPrestados;
    }

    /**
     * @return the emplaresRestantes
     */
    public Integer getEmplaresRestantes() {
        return emplaresRestantes;
    }

    /**
     * @param emplaresRestantes the emplaresRestantes to set
     */
    public void setEmplaresRestantes(Integer emplaresRestantes) {
        this.emplaresRestantes = emplaresRestantes;
    }

    /**
     * @return the alta
     */
    public Boolean getAlta() {
        return alta;
    }

    /**
     * @param alta the alta to set
     */
    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return the editorial
     */
    public Editorial getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
