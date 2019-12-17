package entities;

import java.util.ArrayList;

public class Pasta extends Elemento {
	
	private ArrayList<Elemento> elementos = new ArrayList<Elemento>();

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
		return getNome() + "\n" + texto;
	}
}
