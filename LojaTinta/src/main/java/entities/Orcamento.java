package entities;

public class Orcamento {

	private final int MAX_PROD = 100;
	private final ProdutoSimples[] partes;
	private int posicao_disp;
	String nome_cliente;
	String email;

	Orcamento(String cliente, String mail) {
		partes = new ProdutoSimples[MAX_PROD]; 
		posicao_disp = 0;
		nome_cliente = cliente;
		email = mail;
	}

	void inclui(ProdutoSimples p) {
		if (posicao_disp != MAX_PROD) {
			partes[posicao_disp++] = p;
		}
	}

	void exclui(ProdutoSimples e) {
		int i = 0;
		while (i < posicao_disp) {
			if (partes[i] == e) {
				partes[i] = partes[posicao_disp - 1];
				posicao_disp--;
				i = 0;
			} else {
				i++;
			}
		}
	}

	float obtemPreco() {
		float total = 0;
		for (int i = 0; i < posicao_disp; i++) {
			total += partes[i].obtemPreco();
		}
		return total;
	}

	@Override
	public String toString() {
		String texto = "ORÇAMENTO " + "|Total: R$" + String.format ("%.2f", obtemPreco()) + "|\n";
		texto += "Cliente:  " + nome_cliente + "  E-mail:  " + email + "\n";
		texto += "--------------------------------------------------" + "\n";
		for (int i = 0; i < posicao_disp; i++) {
			texto += partes[i];
		}
		texto += "--------------------------------------------------" + "\n";
		return texto;
	}
}
