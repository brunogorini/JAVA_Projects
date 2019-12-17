package entities;

public class ProdutoSimples {
	private int cod;
    String nome_produto;
    private static int proxCod = 0;
    private float preco;

    ProdutoSimples() {
        this.cod = ++proxCod;
    }

    ProdutoSimples(float p, String s) {
        this();
        preco = p;
        nome_produto = s;
    }

    float obtemPreco() {
        return preco;
    }

    int obtemCodigo() {
        return cod;
    }

    @Override
    public String toString() {
        return "C�digo: " + cod + " Produto: " + nome_produto + " Pre�o: R$ " + String.format ("%.2f", preco) + "\n";
    }
}
