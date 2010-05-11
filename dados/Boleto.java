package dados;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="boleto")
public class Boleto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Boleto
     */
    @Id @GeneratedValue
    @Column(name="id_boleto")
    private Long id;

    /**
     * Valor em Reais do Boleto
     */
    @Column(name="valor", precision=2)
    private Float valor;

    @ManyToOne
    @JoinColumn(name="id_periodo")
    private Periodo periodo;

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
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Boleto[id=" + id + "]";
    }

}
