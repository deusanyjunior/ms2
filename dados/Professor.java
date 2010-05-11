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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="professor")
public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Professor
     */
    @Id @GeneratedValue
    @Column(name="id_professor")
    private Long id;

    /**
     * Nome do professor. Deve ser unico e diferente de nulo
     */
    @Column(name="nome", nullable=false, unique=true)
    private String nome;

    /**
     * CPF do professor. Deve ser unico, diferente de nulo e composto de 11 digitos
     */
    @Column(name="cpf", length=11, nullable=false, unique=true)
    private String cpf;

    /**
     * E-mail do professor
     */
    @Column(name="email")
    private String email;

    /**
     * Conjunto de disciplinas ministradas pelo professor
     */
    @ManyToMany
    @JoinTable(
        name="professor_disciplina",
        joinColumns=@JoinColumn(name="id_professor"),
        inverseJoinColumns=@JoinColumn(name="id_disciplina")
    )
    private Set<Disciplina> ministradas;

    /**
     * Conjunto de instrumentos que estão associados a este professor
     */
    @ManyToMany
    @JoinTable(
        name="professor_instrumento",
        joinColumns=@JoinColumn(name="id_professor"),
        inverseJoinColumns=@JoinColumn(name="id_instrumento")
    )
    private Set<Instrumento> instrumentos;

    /**
     * Conjunto de turmas ensinadas por este professor
     */
    @OneToMany
    @JoinTable(
        name="professor_turma",
        joinColumns=@JoinColumn(name="id_professor"),
        inverseJoinColumns=@JoinColumn(name="id_turma")
    )
    private Set<Turma> turmas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Professor[id=" + id + "]";
    }

}
