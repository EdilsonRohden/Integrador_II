package integrador_ii.models;

public class PlanoDeConta {

	private String tipoConta;
	private double totalizadores;
	
	public PlanoDeConta() {}
	public PlanoDeConta(String tipoConta, double totalizadores) {
		this.tipoConta = tipoConta;
		this.totalizadores = totalizadores;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public double getTotalizadores() {
		return totalizadores;
	}
	public void setTotalizadores(double totalizadores) {
		this.totalizadores = totalizadores;
	}
	
	
	
}
