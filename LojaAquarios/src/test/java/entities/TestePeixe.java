package entities;

import static org.junit.Assert.assertThat;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class TestePeixe {

	private LojaAquarios loja1;
	private Aquario aqua1;
	
	@Before
	public void setup() {
		loja1 = new LojaAquarios("Happy Fish");
		aqua1 =new Aquario(15.0, 15.0, 15.0, 7.5, 25.0);
		loja1.adicionaAquario(aqua1);
	}
	
	@Test
	public void testePhmaxMenorPhMin(){
		try {
			Peixe peixe1 = new Peixe("Poecilia reticulata","Guppy", 8.0, 7.0, 18.0, 28.0, 10.0);
			Assert.fail();
			aqua1.compraPeixe(peixe1);
		} catch (Exception e) {
			assertThat(e.getMessage(),is("O pH máximo não pode ser menor que o pH mínimo."));
		}

	}
	
	@Test
	public void testeTempmaxMenorTempMin(){
		try {
			Peixe peixe1 = new Peixe("Poecilia reticulata","Guppy", 7.0, 8.0, 28.0, 18.0, 10.0);
			Assert.fail();
			aqua1.compraPeixe(peixe1);
		} catch (Exception e) {
			assertThat(e.getMessage(),is("A temperatura máxima não pode ser menor que a temperatura mínima."));
		}

	}
}
