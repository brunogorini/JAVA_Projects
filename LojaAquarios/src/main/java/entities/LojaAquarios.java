package entities;

import java.util.ArrayList;
import java.util.List;

import exceptions.AquarioNaoVazioException;

public class LojaAquarios {

	private String nomeLoja;
	private List<Aquario> aquarios;
	private double saldo=0.0;

	public LojaAquarios(String nomeLoja) {
		this.nomeLoja= nomeLoja;
		this.aquarios = new ArrayList<Aquario>();
	}

	public LojaAquarios(String nomeLoja, double saldo) {
		this.nomeLoja= nomeLoja;
		this.aquarios = new ArrayList<Aquario>();
		this.saldo = saldo;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public void adicionaAquario(Aquario a) {
		aquarios.add(a);
	}

	public void removeAquario(Aquario a) throws AquarioNaoVazioException{
		if(a.getPeixes().isEmpty()) {
			aquarios.remove(a);
		}else {
			throw new AquarioNaoVazioException(String.format("O aquario de %.1fL "
					+ "não pode ser removido, pois está com peixes!", a.getLitragem()));
		}
	}

	public double setSaldo() {
		double balanco = saldo;
		for(Aquario a : aquarios) {
			balanco += a.getSaldoParc();
		}
		return balanco;
	}

	@Override
	public String toString() {
		String texto = "";
		texto += String.format("LOJA %s%n", nomeLoja);
		texto += String.format("Saldo em caixa: %.2f reais%n", setSaldo());
		for(Aquario a : aquarios) {
			texto += String.format("_________________________________________________%n");
			texto += String.format("Aquário de %.2fL:%n", a.getLitragem());
			texto += String.format("pH: %.1f%n", a.getPh());
			texto += String.format("Temperatura: %.1fC%n", a.getTemperatura());
			texto += String.format("Peixes:%n");
			if(a.getPeixes().isEmpty()) {
				texto += "Não há peixes neste aquário!";
			}else {
				for(Peixe p : a.getPeixes()) {
					texto += String.format("Nome: %s\t", p.getNomePopular());
					texto += String.format("Preço: R$%.2f\t", p.getPrecoVenda());
					texto += String.format("Quantidade: %d%n", p.getQtd());
				}
			}
		}
		texto += "\n\n";
		return texto;
	}

	public static void main(String[] args) {
		try {
			LojaAquarios loja1 = new LojaAquarios("Happy Fish");
			Aquario aqua1 =new Aquario(100.0, 20.0, 20.0, 7.0,25.0);
			loja1.adicionaAquario(aqua1);
			Peixe peixe1 = new Peixe("Poecilia reticulata","Guppy albino",6.5,7.5,20.0,30.0,10.0);
			Peixe peixe2 = new Peixe("Poecilia reticulata","Guppy dumbo",6.5,7.5,20.0,30.0,20.0);
			Peixe peixe3 = new Peixe("Xiphophorus hellerii","Espada sangue",6.5,7.5,20.0,30.0,30.0);
			Peixe peixe4 = new Peixe("Poecilia latipinna","Molinesia balão",6.5,7.5,20.0,30.0,40.0);

			aqua1.compraPeixe(peixe1);
			aqua1.compraPeixe(peixe2);
			aqua1.compraPeixe(peixe3);
			aqua1.compraPeixe(peixe4);
			aqua1.compraPeixe(peixe4);

			System.out.print(loja1);
			aqua1.vendePeixe(peixe1);
			aqua1.vendePeixe(peixe2);
			aqua1.vendePeixe(peixe3);
			aqua1.vendePeixe(peixe4);
			aqua1.vendePeixe(peixe4);
			System.out.print(loja1);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
