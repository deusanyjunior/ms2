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
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="turma")
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Turma
     */
    @Id @GeneratedValue
    @Column(name="id_turma")
    private Long id;

    /**
     * Número para identificar a turma
     */
    @Column(name="numero")
    private Integer numero;

    /**
     * Número da sala da turma
     */
    @Column(name="sala")
    private Integer sala;

    /**
     * Conjunto de horarios para esta turma
     */
    @ManyToMany
    @JoinTable(
        name="turma_horario",
        joinColumns=@JoinColumn(name="id_turma"),
        inverseJoinColumns=@JoinColumn(name="id_horario")
    )
    private Set<Horario> horarios;

    /**
     * Disciplina referente a esta turma
     */
    @ManyToOne
    private Disciplina disciplina;

    /**
     * Professor desta turma
     */
    @ManyToOne
    private Professor professor;

    /**
     * Conjunto de provas desta turma
     */
    @OneToMany
    @JoinTable(
        name="turma_prova",
        joinColumns=@JoinColumn(name="id_turma"),
        inverseJoinColumns=@JoinColumn(name="id_prova")
    )
    private Set<Prova> provas;

    /**
     * Periodo desta turma
     */
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
