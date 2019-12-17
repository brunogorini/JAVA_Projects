package entities;

public class ProdutoComposto extends ProdutoSimples {
	
	private final int MAX_PROD = 100;
    String nome_kit;
    private final ProdutoSimples[] partes;
    private int posicao_disp;

    ProdutoComposto(String c) {
        super();
        partes = new ProdutoSimples[MAX_PROD];
        nome_kit = c;
        posicao_disp = 0;
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

    @Override
    float obtemPreco() {
        float total = 0;
        for (int i = 0; i < posicao_disp; i++) {
            total += partes[i].obtemPreco();
        }
        return total;
    }

    @Override
    public String toString() {
        String texto = "Código: " + obtemCodigo() + " Produto: " + nome_kit + " Preço: R$" + String.format ("%.2f", obtemPreco()) + "\n";
        for (int i = 0; i < posicao_disp; i++) {
            texto += "-> " + partes[i];
        }
        return texto;
    }
}
