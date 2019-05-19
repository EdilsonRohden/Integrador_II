package integrador_ii.models;

public class Pessoa {
	
	protected Integer id;
	protected Cidade cidade;
	protected String nome;
	
	public Pessoa() {}

	public Pessoa(Integer id, Cidade cidade, String nome) {
		this.id = id;
		this.cidade = cidade;
		this.nome = nome;
	}
	
	public Pessoa(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
