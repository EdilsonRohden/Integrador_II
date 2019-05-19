package integrador_ii.models;

import java.util.List;

public class Cliente extends Pessoa{

	private Integer idPessoa;
	private String bairro;
	private String cep;
	private String fone;
	private String email;
	private List<Movimento> movimentacoes;
	
	public Cliente() {}
	
	public Cliente(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
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
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Movimento> getMovimentacoes() {
		return movimentacoes;
	}
	public void setMovimentacoes(List<Movimento> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
}
