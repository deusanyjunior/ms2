package dados;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="prova")
public class Prova implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Prova
     */
    @Id @GeneratedValue
    @Column(name="id_prova")
    private Long id;

    /**
     * Nota respectiva a esta prova
     */
    @Column(name="nota", precision=2)
    private Float nota;

    /**
     * Tipo de prova
     */
    @ManyToOne
    @JoinTable(
        name="prova_tipo_prova",
        joinColumns=@JoinColumn(name="id_prova"),
        inverseJoinColumns=@JoinColumn(name="id_tipo_prova")
    )
    private TipoProva tipo;

    /**
     * O aluno proprietario desta prova
     */
    @ManyToOne
    private Aluno aluno;

    /**
     * A turma correspondente a esta prova
     */
    @ManyToOne
    private Turma turma;

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
        if (!(object instanceof Prova)) {
            return false;
        }
        Prova other = (Prova) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Prova[id=" + id + "]";
    }

}
