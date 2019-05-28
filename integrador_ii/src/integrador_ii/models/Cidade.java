package integrador_ii.models;

public class Cidade {

	private Estado estado;
	private Integer codigoIbge;
	private String nome;
	
	public Cidade(Integer codigoIbge, String nome, Estado estado) {
		this.estado = estado;
		this.nome = nome;
		this.codigoIbge = codigoIbge;
	}
	public Cidade(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	public Cidade() {}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Integer getCodigoIbge() {
		return codigoIbge;
	}
	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
