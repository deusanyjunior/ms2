package dados;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="periodo")
public class Periodo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Periodo
     */
    @Id @GeneratedValue
    @Column(name="id_periodo")
    private Long id;

    /**
     * Nome do periodo
     */
    @Column(name="nome")
    private String nome;

    /**
     * Data de inicio do periodo
     */
    @Column(name="data_inicio")
    private Date dataInicio;

    /**
     * Data do fim do periodo
     */
    @Column(name="data_fim")
    private Date dataFim;

    /**
     * Conjunto de turmas deste periodo
     */
    @OneToMany
    @JoinTable(
        name="turma_periodo",
        joinColumns=@JoinColumn(name="id_periodo"),
        inverseJoinColumns=@JoinColumn(name="id_turma")
    )
    private Set<Turma> turmas;

    /**
     * Conjunto de boletos deste periodo
     */
    @OneToMany(mappedBy="periodo")
    private Set<Boleto> boletos;

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
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Periodo[id=" + id + "]";
    }

}
