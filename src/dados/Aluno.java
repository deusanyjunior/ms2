package dados;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe utilizada para representar a entidade Aluno que contem dados cadastrais
 * do Aluno
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
@Entity
@Table(name="aluno")
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador da entidade Aluno
     */
    @Id @GeneratedValue
    @Column(name="id_aluno")
    private Long id;

    /**
     * Nome do aluno. Obs deve ser unico e não pode ser nulo
     */
    @Column(name="nome", nullable=false, unique=true)
    private String nome;

    /**
     * CPF do Aluno. Deve ser unico e diferente de nulo. Composto de 11 digitos
     */
    @Column(name="cpf", length=11, nullable=false, unique=true)
    private String cpf;

    /**
     * Matricula do Aluno. Ainda necessita de um metodo de geração de matriculas
     */
    @Column(name="matricula", nullable=false, unique=true)
    private String matricula;

    /**
     * Endereço do Aluno, composto apenas do logradouro e numero do imovel
     */
    @Column(name="endereco")
    private String endereco;

    /**
     * Bairro correspondente ao endereço do Aluno
     */
    @Column(name="bairro")
    private String bairro;

    /**
     * Cidade do Aluno
     */
    @Column(name="cidade", nullable=false)
    private String cidade;

    /**
     * CEP do aluno. Composto de 8 digitos
     */
    @Column(name="cep", length=8)
    private String cep;

    /**
     * Data de nascimento do Aluno
     */
    @Column(name="data_nascimento")
    private Date nascimento;

    /**
     * Telefone residencial do Aluno
     */
    @Column(name="fone", length=10)
    private String telefone;

    /**
     * Telefone celular do Aluno
     */
    @Column(name="celular", length=10)
    private String celular;

    /**
     * E-mail do Aluno
     */
    @Column(name="email")
    private String email;

    /**
     * O atributo turmas so sera trazido da base de dados quando acessado
     */
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="aluno_turma",
        joinColumns=@JoinColumn(name="id_aluno"),
        inverseJoinColumns=@JoinColumn(name="id_turma")
    )
    private Set<Turma> turmas;

    /**
     * O atributo instrumentos so sera trazido da base de dados quando acessado
     */
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="aluno_instrumento",
        joinColumns=@JoinColumn(name="id_aluno"),
        inverseJoinColumns=@JoinColumn(name="id_instrumento")
    )
    private Set<Instrumento> instrumentos;

    /**
     * O atributo boletos so sera trazido da base de dados quando acessado
     */
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="aluno_boleto",
        joinColumns=@JoinColumn(name="id_aluno"),
        inverseJoinColumns=@JoinColumn(name="id_boleto")
    )
    private Set<Boleto> boletos;

    /**
     * O atributo provas so sera trazido da base de dados quando acessado
     */
    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(
        name="aluno_prova",
        joinColumns=@JoinColumn(name="id_aluno"),
        inverseJoinColumns=@JoinColumn(name="id_prova")
    )
    private Set<Prova> provas;

    public Set<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(Set<Boleto> boletos) {
        this.boletos = boletos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(Set<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Prova> getProvas() {
        return provas;
    }

    public void setProvas(Set<Prova> provas) {
        this.provas = provas;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
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
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Aluno[id=" + id + "]";
    }
}
