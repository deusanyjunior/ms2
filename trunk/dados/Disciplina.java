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
@Table(name="disciplina")
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    @Column(name="id_disciplina")
    private Long id;

    @Column(name="nome", nullable=false, unique=true)
    private String nome;

    @Column(name="creditos")
    private Integer creditos;

    @Column(name="nivel")
    private Integer nivel;
    
    @ManyToMany
    @JoinTable(
        name="disciplina_instrumento",
        joinColumns=@JoinColumn(name="id_disciplina"),
        inverseJoinColumns=@JoinColumn(name="id_instrumento")
    )
    private Set<Instrumento> instrumentos;

    @ManyToOne
    @JoinTable(
        name="disciplina_tipo_diciplina",
        joinColumns=@JoinColumn(name="id_disciplina"),
        inverseJoinColumns=@JoinColumn(name="id_tipo_disciplina")
    )
    private TipoDisciplina tipo;

    @ManyToMany(mappedBy="ministradas")
    private Set<Professor> professores;

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
