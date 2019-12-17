package entities;

public class Arquivo extends Elemento {

    private final int tamanho;

    public Arquivo(String nome, int tamanho) {
        super(nome);
        this.tamanho = tamanho;
    }

    @Override
    public int getTamanho() {
        return tamanho;
    }
}
