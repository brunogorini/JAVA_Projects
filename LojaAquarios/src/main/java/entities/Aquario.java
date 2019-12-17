package entities;

import java.util.ArrayList;
import java.util.List;

import exceptions.AquarioCheioException;
import exceptions.AquarioVazioException;
import exceptions.PeixeNaoEncontradoException;
import exceptions.PhNaoIdealException;
import exceptions.TempPhNaoIdealException;
import exceptions.TemperaturaNaoIdealException;

public class Aquario {

	private double comprimento; // centímetros
	private double largura; // centímetros
	private double altura; // centímetros
	private double ph = 7.0;
	private double temperatura = 25.0; // celsius
	private List<Peixe> peixes;
	private double saldoParc;

	public Aquario(double comprimento, double largura, double altura) {
		this.comprimento = comprimento;
		this.largura = largura;
		this.altura = altura;
		this.peixes = new ArrayList<Peixe>();
	}

	public Aquario(double comprimento, double largura, double altura, double ph, double temperatura) {
		this.comprimento = comprimento;
		this.largura = largura;
		this.altura = altura;
		this.ph = ph;
		this.temperatura = temperatura;
		this.peixes = new ArrayList<Peixe>();
	}

	public double getLitragem() {
		return comprimento*largura*altura/1000; // litros
	}

	public void compraPeixe(Peixe p) throws Exception{
		if((p.tempIdeal(this)) && (p.phIdeal(this))) {
			if(peixes.size()<maxNumPeixes()) {
				if(peixes.contains(p)) {
					p.setQtd(p.getQtd()+1);
				}else {
					peixes.add(p);
				}
				this.saldoParc -= p.getPrecoCompra();
			}else {
				throw new AquarioCheioException(String.format("O peixe %s não pode ser adicionado,"
						+ " pois o aquario de %.1fL está cheio!", p.getNomePopular(), getLitragem()));
			}
		}else if((!p.tempIdeal(this)) && (p.phIdeal(this))){
			throw new TemperaturaNaoIdealException(String.format("A temperatura de %.1fC não é a ideal"
					+ " para o peixe %s!", getTemperatura(), p.getNomePopular()));
		}else if((p.tempIdeal(this)) && !(p.phIdeal(this))){
			throw new PhNaoIdealException(String.format("O pH de %.1f não é a ideal"
					+ " para o peixe %s!", getPh(), p.getNomePopular()));
		}else if((!p.tempIdeal(this)) && !(p.phIdeal(this))){
			throw new TempPhNaoIdealException(String.format("A temperatura de %.1fC e o pH de %.1f não são ideais"
					+ " para o peixe %s!", getTemperatura(),getPh(), p.getNomePopular()));
		}
	}

	public void vendePeixe(Peixe p) throws Exception {
		if(peixes.size()==0) {
			throw new AquarioVazioException(String.format("Não há o que ser removido,"
					+ " pois aquario de %.1fL está vazio!", getLitragem()));
		}else if(peixes.contains(p)) {
			if(p.getQtd()==1) {
				peixes.remove(p);
			}else {
				p.setQtd(p.getQtd()-1);
			}
			this.saldoParc += p.getPrecoVenda();
		}else {
			throw new PeixeNaoEncontradoException(String.format("O peixe %s não está no aquario de "
					+ "%.1fL!%n", p.getNomePopular(), getLitragem()));
		}
	}

	public double getPh() {
		return ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public int maxNumPeixes() {
		return (int) (getLitragem()/2.5);
	}

	public double getSaldoParc() {
		return this.saldoParc;
	}

	public List<Peixe> getPeixes() {
		return peixes;
	}



}
