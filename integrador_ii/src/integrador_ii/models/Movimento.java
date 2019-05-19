package integrador_ii.models;

import java.util.Date;
import java.util.List;

public class Movimento {
	
	private Cliente cliente;
	private Conta conta;
	private int id;
	private Date dataMovimento;
	private Double valor;
	private List<MovimentoAlteracao> alteracoes;
	
	public Movimento(Cliente cliente, Conta conta, int id, Date dataMovimento, double valor) {
		this.cliente = cliente;
		this.conta = conta;
		this.id = id;
		this.dataMovimento = dataMovimento;
		this.valor = valor;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
