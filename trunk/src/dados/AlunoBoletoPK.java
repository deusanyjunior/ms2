/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Embeddable
public class AlunoBoletoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name="id_boleto")
    private Boleto boleto;
}
