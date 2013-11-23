/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author joao
 */
@Entity
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int NORMAL = 0;
    public static final int DEVOLVIDO = 1;
    public static final int ATRASADO = 2;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario usuario;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmprestimo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDevolucaoPrevista;
    @ManyToOne
    private Exemplar exemplar;
    @OneToOne
    private Devolucao devolucao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Emprestimo)) {
            return false;
        }
        Emprestimo other = (Emprestimo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Emprestimo[ id=" + id + " ]";
    }
}
