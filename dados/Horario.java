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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="horario")
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Horario
     */
    @Id @GeneratedValue
    @Column(name="id_horario")
    private Long id;

    /**
     * Hora que começa a aula
     */
    @Column(name="hora_inicio", nullable=false)
    private Integer horaInicio;

    /**
     * Hora que termina a aula
     */
    @Column(name="hora_fim", nullable=false)
    private Integer horaFim;

    /**
     * Dia da semana (Segunda, Terça, Quarta, Quinta, Sexta, Sábado, Domingo)
     */
    @Column(name="dia", nullable=false)
    private String dia;

    /**
     * Conjunto de turmas que tem este horário
     */
    @ManyToMany(mappedBy="horarios")
    private Set<Turma> turmas;

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
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Horario[id=" + id + "]";
    }

}
