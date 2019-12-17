package entities;

public class LojaTinta {

	private final int MAX_PROD = 1000;
	String nome_loja;
	private ProdutoSimples[] produtos;
	private int posicao_disp;

	LojaTinta(String n) {
		nome_loja = n;
		produtos = new ProdutoSimples[MAX_PROD];
		posicao_disp = 0;
	}

	void incluirProduto(ProdutoSimples p) {
		if (posicao_disp != MAX_PROD) {
			produtos[posicao_disp++] = p;
		}
	}

	@Override
	public String toString() {
		String texto = "Catálogo da loja de tinta (" + nome_loja + "):\n";
		boolean existe = false;
		texto += "-- Produtos Simples --\n";
		for (int i = 0; i < posicao_disp; i++) {
			if (!(produtos[i] instanceof ProdutoComposto)){
				existe = true;
				texto += produtos[i];
			}
		}    
		if (!existe) {texto = texto.replace("-- Produtos Simples --\n", "");} else {existe = false;}
		texto += "-- Produtos Compostos --\n";
		for (int i = 0; i < posicao_disp; i++) {
			if (produtos[i] instanceof ProdutoComposto) {
				existe = true;
				texto += produtos[i];
			}
		}
		if (!existe) {texto = texto.replace("-- Produtos Compostos --\n", "");}
		texto += "\n";
		return texto;
	}



	void excluirProduto(ProdutoSimples e) {
		int i = 0;
		while (i < posicao_disp) {
			if (produtos[i] instanceof ProdutoComposto) {
				((ProdutoComposto) produtos[i]).exclui(e);
			}
			if (produtos[i] == e) {
				produtos[i] = produtos[posicao_disp - 1];
				posicao_disp--;
				i = 0;
			} else {
				i++;
			}
		}
	}

	public static void main(String[] args) {
		LojaTinta matriz = new LojaTinta("Matriz");
		LojaTinta filial = new LojaTinta("Filial");
		ProdutoSimples pincel = new ProdutoSimples(15F, "Pincel");
		ProdutoSimples borracha = new ProdutoSimples(10F, "Borracha");
		ProdutoSimples bandeja = new ProdutoSimples(25F, "Bandeja");
		ProdutoSimples lixa = new ProdutoSimples(20F, "Lixa");
		ProdutoSimples rolo = new ProdutoSimples(40F, "Rolo");
		ProdutoSimples tinta_vermelha = new ProdutoSimples(80F, "Tinta Vermelha");
		ProdutoSimples tinta_azul = new ProdutoSimples(80F, "Tinta Azul");
		ProdutoSimples tinta_amarela = new ProdutoSimples(80F, "Tinta Amarela");
		ProdutoComposto kit1 = new ProdutoComposto("Kit Pintura Grande");
		ProdutoComposto kit2 = new ProdutoComposto("Kit Pintura Pequeno");
		ProdutoComposto tinta_roxa = new ProdutoComposto("Tinta Composta Roxa");
		ProdutoComposto tinta_verde = new ProdutoComposto("Tinta Composta Verde");
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Orcamento cliente1 = new Orcamento("Gabriel Diniz", "gabriel.diniz@gmail.com");
		Orcamento cliente2 = new Orcamento("Luciana Almeida", "lu_almeida@hotmail.com");
		kit1.inclui(pincel);
		kit1.inclui(borracha);
		kit1.inclui(tinta_vermelha);
		kit1.inclui(tinta_azul);
		kit1.inclui(tinta_amarela);
		kit1.inclui(bandeja);
		kit1.inclui(lixa);
		kit2.inclui(rolo);
		kit2.inclui(tinta_vermelha);
		kit2.inclui(tinta_azul);
		kit2.inclui(tinta_amarela);
		tinta_roxa.inclui(tinta_azul);
		tinta_roxa.inclui(tinta_vermelha);
		tinta_verde.inclui(tinta_azul);
		tinta_verde.inclui(tinta_amarela);
		matriz.incluirProduto(pincel);
		matriz.incluirProduto(borracha);
		matriz.incluirProduto(tinta_vermelha);
		matriz.incluirProduto(tinta_azul);
		matriz.incluirProduto(tinta_amarela);
		matriz.incluirProduto(tinta_roxa);
		matriz.incluirProduto(tinta_verde);
		matriz.incluirProduto(bandeja);
		matriz.incluirProduto(lixa);
		matriz.incluirProduto(rolo);
		matriz.incluirProduto(kit1);
		filial.incluirProduto(kit2);
		filial.incluirProduto(rolo);
		filial.incluirProduto(tinta_vermelha);
		filial.incluirProduto(tinta_azul);
		filial.incluirProduto(tinta_amarela);
		System.out.println(matriz);
		System.out.println(filial);
		filial.excluirProduto(kit2);
		System.out.println(filial);
		carrinho.inclui(kit2);
		System.out.println(carrinho);
		carrinho.inclui(bandeja);
		System.out.println(carrinho);
		cliente1.inclui(pincel);
		cliente1.inclui(borracha);
		cliente1.inclui(tinta_vermelha);
		cliente1.inclui(tinta_azul);
		System.out.println(cliente1);
		cliente2.inclui(kit1);
		cliente2.inclui(tinta_roxa);
		cliente2.inclui(tinta_verde);
		System.out.println(cliente2);
	}
}

