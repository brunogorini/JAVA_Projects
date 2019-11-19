
/**
 * AD2 - Programação OO
 *
 * @author Bruno Gorini
 */
import java.util.*;

public class SistemaArquivos {

    public static void main(String[] args) {
        Pasta p1 = new Pasta("dir1");
        p1.adiciona(new Arquivo("arquivo1.txt", 150));
        p1.adiciona(new Arquivo("arquivo2.txt", 200));
        Pasta p2 = new Pasta("dir2");
        p2.adiciona(new Arquivo("arquivo3.txt", 500));
        Pasta p3 = new Pasta("dir3");
        p3.adiciona(new Arquivo("arquivo4.txt", 350));
        p3.adiciona(p2);
        Pasta raiz = new Pasta("c:/");
        raiz.adiciona(p1);
        raiz.adiciona(p3);
        raiz.remove("dir1/arquivo1.txt");
        System.out.println(raiz.getTamanho());
        System.out.println(raiz);
    }
}

abstract class Elemento {

    protected final String nome;
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

class Arquivo extends Elemento {

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

class Pasta extends Elemento {

    protected ArrayList<Elemento> elementos = new ArrayList<Elemento>();

    public Pasta(String nome) {
        super(nome);
    }

    public void adiciona(Elemento elemento) {
        elementos.add(elemento);
    }

    @Override
    public int getTamanho() {
        int tamanho = 0;
        for (Elemento e : elementos) {
            tamanho += e.getTamanho();
        }
        return tamanho;
    }

    public void remove(String caminho) {
        long contador = caminho.chars().filter(ch -> ch == '/').count();
        if (contador == 0) {
            for (Elemento e : elementos) {
                if (caminho.equals(e.getNome())) {
                    elementos.remove(e);
                    break;
                }
            }
        } else {
            String[] parts = caminho.split("/");
            for (Elemento e : elementos) {
                int i = 0;
                if (parts[i].equals(e.getNome())) {
                    i++;
                    if (e instanceof Pasta) {
                        Pasta p = (Pasta) e;
                        p.remove(caminho.substring(caminho.indexOf('/') + 1));
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String texto = "";
        int tab = getTab();
        tab++;
        for (Elemento e : elementos) {
            if (e instanceof Pasta) {
                Pasta p = (Pasta) e;
                for (int i = 0; i < tab; i++) {
                    texto += "\t";
                }
                setTab(tab);
                texto += p.toString() + "\n";
            } else {
                for (int i = 0; i < tab; i++) {
                    texto += "\t";
                }
                texto += e.getNome() + "\n";
            }
        }
        setTab(0);
        return this.nome + "\n" + texto;
    }
}
