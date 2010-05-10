/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="tipo_disciplina")
public class TipoDisciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    @Column(name="id_tipo_disciplina")
    private Long id;

    @Column(name="nome_tipo", nullable=false, unique=true)
    private String nome;

    @OneToMany(mappedBy="tipo")
    private Set<Disciplina> disciplinas;

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
        if (!(object instanceof TipoDisciplina)) {
            return false;
        }
        TipoDisciplina other = (TipoDisciplina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.TipoDisciplina[id=" + id + "]";
    }

}
