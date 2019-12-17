package entities;

public abstract class Elemento {
	
	private final String nome;
	private static int tabulacao = 0;

	public Elemento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public int getTab() {
		return tabulacao;
	}

	public void setTab(int tabulacao) {
		Elemento.tabulacao = tabulacao;
	}

	public abstract int getTamanho();
}
