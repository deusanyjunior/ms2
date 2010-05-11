package dados;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="aluno_boleto")
public class AlunoBoleto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Chave composta pelos ids de Aluno e Boleto
     */
    @EmbeddedId
    private AlunoBoletoPK chaveComposta;

    /**
     * Data em que o boleto foi pago
     */
    @Column(name="data_pagamento")
    private Date dataPagamento;
}
