package integrador_ii.models;

import java.sql.Date;
import java.util.List;

public class Movimento {
	
	private Conta conta;
	private int id;
	private Date dataMovimento;
	private Double valor;
	private String descricao;
	private List<MovimentoAlteracao> alteracoes;
	
	public Movimento(Cliente cliente, Conta conta, int id, Date dataMovimento, double valor) {
		this.conta = conta;
		this.id = id;
		this.dataMovimento = dataMovimento;
		this.valor = valor;
	}
	
	public Movimento(int idConta, Date dataMovimento, double valor, String descricao) {
		this.conta = new Conta(idConta);
		this.dataMovimento = dataMovimento;
		this.valor = valor;
		this.descricao = descricao;
		
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataMovimento() {
		return dataMovimento;
	}
	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public List<MovimentoAlteracao> getAlteracoes() {
		return alteracoes;
	}
	public void setAlteracoes(List<MovimentoAlteracao> alteracoes) {
		this.alteracoes = alteracoes;
	}
	
}
