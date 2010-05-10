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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="turma")
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    @Column(name="id_turma")
    private Long id;

    @Column(name="numero")
    private Integer numero;

    @Column(name="sala")
    private Integer sala;

    @ManyToMany
    @JoinTable(
        name="turma_horario",
        joinColumns=@JoinColumn(name="id_turma"),
        inverseJoinColumns=@JoinColumn(name="id_horario")
    )
    private Set<Horario> horarios;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private Professor professor;

    @OneToMany
    @JoinTable(
        name="turma_prova",
        joinColumns=@JoinColumn(name="id_turma"),
        inverseJoinColumns=@JoinColumn(name="id_prova")
    )
    private Set<Prova> provas;

    @ManyToOne
    private Periodo periodo;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Turma[id=" + id + "]";
    }

}
