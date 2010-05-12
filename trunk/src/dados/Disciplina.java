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
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="disciplina")
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Disciplina
     */
    @Id @GeneratedValue
    @Column(name="id_disciplina")
    private Long id;

    /**
     * Nome da disciplina. Deve ser único e diferente de nulo
     */
    @Column(name="nome", nullable=false, unique=true)
    private String nome;

    /**
     * Numero de horas-aula da disciplina
     */
    @Column(name="creditos")
    private Integer creditos;

    /**
     * Nivel da disciplina
     */
    @Column(name="nivel")
    private Integer nivel;

    /**
     * Conjunto de instrumentos que estão associadas a esta disciplina
     */
    @ManyToMany
    @JoinTable(
        name="disciplina_instrumento",
        joinColumns=@JoinColumn(name="id_disciplina"),
        inverseJoinColumns=@JoinColumn(name="id_instrumento")
    )
    private Set<Instrumento> instrumentos;

    /**
     * Tipo de disciplina
     */
    @ManyToOne
    @JoinTable(
        name="disciplina_tipo_diciplina",
        joinColumns=@JoinColumn(name="id_disciplina"),
        inverseJoinColumns=@JoinColumn(name="id_tipo_disciplina")
    )
    private TipoDisciplina tipo;

    /**
     * Conjunto de professores que ministram esta disciplina
     */
    @ManyToMany(mappedBy="ministradas")
    private Set<Professor> professores;

    /**
     * Conjunto de turmas que estão associadas a esta disciplina
     */
    @OneToMany
    @JoinTable(
        name="disciplina_turma",
        joinColumns=@JoinColumn(name="id_disciplina"),
        inverseJoinColumns=@JoinColumn(name="id_turma")
    )
    private Set<Turma> turmas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Disciplina[id=" + id + "]";
    }

}
