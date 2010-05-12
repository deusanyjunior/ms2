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
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="tipo_prova")
public class TipoProva implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade TipoProva
     */
    @Id @GeneratedValue
    @Column(name="id_tipo_prova")
    private Long id;

    /**
     * Nome do tipo da prova
     */
    @Column(name="nome")
    private String nome;

    /**
     * Conjunto de provas que possuem este tipo
     */
    @OneToMany(mappedBy="tipo")
    private Set<Prova> provas;

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
        if (!(object instanceof TipoProva)) {
            return false;
        }
        TipoProva other = (TipoProva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.TipoProva[id=" + id + "]";
    }

}
