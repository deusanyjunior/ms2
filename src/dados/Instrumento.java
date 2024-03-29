package dados;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="instrumento")
public class Instrumento implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Instrumento
     */
    @Id @GeneratedValue
    @Column(name="id_instrumento")
    private Long id;

    /**
     * Nome do instrumento
     */
    @Column(name="nome", nullable=false, unique=true)
    private String nome;

    /**
     * Conjunto de disciplinas que esta associada a este instrumento
     */
    @ManyToMany(mappedBy="instrumentos")
    private Set<Disciplina> disciplinas;

    /**
     * Conjunto de alunos que estão associados a este instrumento
     */
    @ManyToMany(mappedBy="instrumentos")
    private Set<Aluno> alunos;

    /**
     * Conjunto de professores que estão associados a este instrumento
     */
    @ManyToMany(mappedBy="instrumentos")
    private Set<Professor> professores;

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
        if (!(object instanceof Instrumento)) {
            return false;
        }
        Instrumento other = (Instrumento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Instrumento[id=" + id + "]";
    }
}
