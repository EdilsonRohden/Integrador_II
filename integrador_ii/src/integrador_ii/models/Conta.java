package integrador_ii.models;

public class Conta {
	
	private Integer id;
	private String descricaoP;
	private String descricaoS;
	private Integer idP;
	private Integer idS;
	
	public Conta() {}
	
	public Conta(Integer id, Integer idP, Integer idS, String descricaoP, String descricaoS) {
		this.id = id;
		this.idP = idP;
		this.idS = idS;
		this.descricaoP = descricaoP;
		this.descricaoS = descricaoS;
	}
	
	public Conta(Integer id) {
		this.id = id;
	}

	public Conta(Integer id, Integer idP, Integer idS, String descricaoS) {
		this.id = id;
		this.idP = idP;
		this.idS = idS;
		this.descricaoS = descricaoS;
	}

	public String getDescricaoP() {
		return descricaoP;
	}
	public void setDescricaoP(String descricaoP) {
		this.descricaoP = descricaoP;
	}
	public String getDescricaoS() {
		return descricaoS;
	}
	public void setDescricaoS(String descricaoS) {
		this.descricaoS = descricaoS;
	}
	public int getIdP() {
		return idP;
	}
	public void setIdP(int idP) {
		this.idP = idP;
	}
	public int getIdS() {
		return idS;
	}
	public void setIdS(int idS) {
		this.idS = idS;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
