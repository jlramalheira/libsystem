/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DaoExemplar;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author joao
 */
@Entity
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private int ano;
    private String editora;
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        switch (categoria) {
            case "LIVRO":
                this.categoria = Categoria.LIVRO;
                break;
            case "PERIODICO":
                this.categoria = Categoria.PERIODICO;
                break;
            case "ENCICLOPEDIA":
                this.categoria = Categoria.ENCICLOPEDIA;
                break;
            case "MIDIA":
                this.categoria = Categoria.MIDIA;
                break;
            case "DICIONARIO":
                this.categoria = Categoria.DICIONARIO;
                break;
            case "MAPA":
                this.categoria = Categoria.MAPA;
                break;
        }

    }
    
    public int getExemplares(){
        return new DaoExemplar().getExemplares(this);
    }
    
    public int getExemplares(int status){
        return new DaoExemplar().getExemplaresByStatus(this,status);
    }
    
    public boolean hasDisponivel(){
        List<Exemplar> exemplares = new DaoExemplar().listByObraAndStatus(this,Exemplar.DISPONIVEL);
        return exemplares.isEmpty();
    }
    
    public boolean hasEmprestado(){
        List<Exemplar> exemplares = new DaoExemplar().listByObraAndStatus(this,Exemplar.EMPRESTADO);
        return exemplares.isEmpty();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obra)) {
            return false;
        }
        Obra other = (Obra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Obra[ id=" + id + " ]";
    }
}
