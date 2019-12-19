package entities;

import org.junit.Before;
import org.junit.Test;
import exceptions.AquarioNaoVazioException;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.closeTo;

public class TesteLojaAquarios {
	
	private LojaAquarios loja1;
	private Aquario aqua1;
	private Peixe peixe1;
	private Peixe peixe2;
	private Peixe peixe3;
	private Peixe peixe4;
	
	@Before
	public void setuo() {
		loja1 = new LojaAquarios("Happy Fish");
		aqua1 =new Aquario(25.0, 25.0, 25.0, 7.5, 25.0);
		loja1.adicionaAquario(aqua1);
		peixe1 = new Peixe("Poecilia reticulata","Guppy albino", 7.0, 8.0, 18.0, 28.0, 10.0);
		peixe2 = new Peixe("Poecilia reticulata","Guppy dumbo", 7.0, 8.0, 18.0, 28.0, 10.0);
		peixe3 = new Peixe("Xiphophorus hellerii","Espada sangue", 7.0, 8.0, 22.0, 28.0, 9.0);
		peixe4 = new Peixe("Poecilia latipinna","Molinesia balão", 7.0, 8.0, 20.0, 30.0, 15.0);
	}

	@Test
	public void testeAdicionaAquario() {
		
		//validação
		assertThat(loja1.getAquarios(), hasItem(aqua1));
	}
	
    @Test(expected = AquarioNaoVazioException.class)
    public void testeRemoveAquario() throws Exception {
    	
    	//cenário
		aqua1.compraPeixe(peixe1);
		
		//ação
		loja1.removeAquario(aqua1);
      
	}
    
    @Test
    public void testeSetSaldo() throws Exception {
    	
    	//cenário
    	aqua1.compraPeixe(peixe1);
		aqua1.compraPeixe(peixe2);
		aqua1.compraPeixe(peixe3);
		aqua1.compraPeixe(peixe4);

		//ação
		aqua1.vendePeixe(peixe1);
		aqua1.vendePeixe(peixe2);
		aqua1.vendePeixe(peixe3);
		aqua1.vendePeixe(peixe4);
		
		//validação
		assertThat(loja1.setSaldo(),closeTo(22.00, 0.01));

    }

}
