package entities;

public class Peixe {
	private String especie;
	private String nomePopular;
	private double phMin;
	private double phMax;
	private double tempMin;
	private double tempMax;
	private int qtd=1;
	private double precoCompra;
	private double taxaLucro = 1.5;

	public Peixe(String especie, String nomePopular, double phMin, double phMax,
			double tempMin,	double tempMax, double precoCompra) {
		this.especie = especie;
		this.nomePopular = nomePopular;
		this.phMin = phMin;
		if(phMax<this.phMin) {
			throw new IllegalArgumentException("O pH máximo não pode ser menor que o pH mínimo.");
		}else {
			this.phMax = phMax;	
		}
		this.tempMin = tempMin;
		if(tempMax<this.tempMin) {
			throw new IllegalArgumentException("A temperatura máxima não pode ser menor que a temperatura mínima.");
		}else {
			this.tempMax = tempMax;	
		}
		this.precoCompra = precoCompra;
	}

	public boolean tempIdeal(Aquario a) {
		return (a.getTemperatura()>=tempMin)&&(a.getTemperatura()<=tempMax);
	}

	public boolean phIdeal(Aquario a) {
		return (a.getPh()>=phMin)&&(a.getPh()<=phMax);
	}

	public String getEspecie() {
		return especie;
	}

	public String getNomePopular() {
		return nomePopular;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}
	
	public double getPrecoVenda() {
		return precoCompra*taxaLucro;
	}

	public void setTaxaLucro(double taxaLucro) {
		this.taxaLucro = taxaLucro;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
		
}
