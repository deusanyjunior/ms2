/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name="aluno_turma")
public class AlunoTurma implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AlunoTurmaPK chaveComposta;

    @Column(name="numero_faltas")
    private Long faltas;

    public Long getFaltas() {
        return faltas;
    }

    public void setFaltas(Long faltas) {
        this.faltas = faltas;
    }

    public AlunoTurmaPK getChaveComposta() {
        return chaveComposta;
    }

    public void setChaveComposta(AlunoTurmaPK chaveComposta) {
        this.chaveComposta = chaveComposta;
    }
}
