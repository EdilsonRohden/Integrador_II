package integrador_ii.models;

public class Usuario extends Pessoa{

	private String login;
	private String senha;
	private boolean adm;
	
	public Usuario() {}
	
	public Usuario(Integer id, String login, String senha, boolean adm) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.adm = adm;
	}
	public Usuario(Integer id) {
		this.id = id;
	}

	public Usuario(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAdm() {
		return adm;
	}
	public void setAdm(boolean adm) {
		this.adm = adm;
	}
	
	
	
}
