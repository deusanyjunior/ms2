/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dados;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="aluno_boleto")
public class AlunoBoleto implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AlunoBoletoPK chaveComposta;

    @Column(name="data_pagamento")
    private Date dataPagamento;
}
