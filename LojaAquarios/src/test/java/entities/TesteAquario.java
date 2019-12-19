package entities;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import exceptions.AquarioCheioException;
import exceptions.AquarioVazioException;
import exceptions.PeixeNaoEncontradoException;
import exceptions.PhNaoIdealException;
import exceptions.TempPhNaoIdealException;
import exceptions.TemperaturaNaoIdealException;


public class TesteAquario {
	
	private LojaAquarios loja1;
	private Aquario aqua1;
	private Peixe peixe1;
	private Peixe peixe2;
	
	@Before
	public void setup() {
		loja1 = new LojaAquarios("Happy Fish");
		aqua1 =new Aquario(15.0, 15.0, 15.0, 7.5, 25.0);
		loja1.adicionaAquario(aqua1);
		peixe1 = new Peixe("Poecilia reticulata","Guppy", 7.0, 8.0, 18.0, 28.0, 10.0);
		peixe2 = new Peixe("Leuciscus idus","Escalo-prateado", 7.0, 7.5, 4.0, 20.0, 30.0);
	}
	
	@Test
	public void testeGetLitragem() {
		
		//validação
		assertThat(aqua1.getLitragem(), closeTo(3.375, 0.001));
	}
	
	@Test
	public void testeMaxNumPeixes() {
		assertThat(aqua1.maxNumPeixes(), is(equalTo(1)));
	}
	
	@Test(expected = AquarioCheioException.class)
    public void testeCompraPeixe_AquaCheio() throws Exception {
    	
		//ação
		aqua1.compraPeixe(peixe1);
		aqua1.compraPeixe(peixe1);
      
	}
	
	@Test(expected = TemperaturaNaoIdealException.class)
    public void testeCompraPeixe_TemperaturaNaoIdeal() throws Exception {
    			
		//ação
		aqua1.compraPeixe(peixe2);
      
	}
	
	@Test(expected = PhNaoIdealException.class)
    public void testeCompraPeixe_PhNaoIdeal() throws Exception {
    	
		//cenário
		Peixe peixe3 = new Peixe("Paracheirodon axelrodi","Tetra-cardinal", 4.0, 6.0, 23.0, 27.0, 8.0);
						
		//ação
		aqua1.compraPeixe(peixe3);
      
	}
	
	@Test(expected = TempPhNaoIdealException.class)
    public void testeCompraPeixe_TempPhNaoIdeal() throws Exception {
    	
		//cenário
		Peixe peixe4 = new Peixe("Pethia ticto","Barbo Ticto", 6.5, 7.0, 14.0, 22.0, 15.0);
						
		//ação
		aqua1.compraPeixe(peixe4);
      
	}
	
	@Test(expected = AquarioVazioException.class)
    public void testeVendaPeixe_AquarioVazio() throws Exception {
		
		//ação
		aqua1.vendePeixe(peixe1);
      
	}
	
	@Test(expected = PeixeNaoEncontradoException.class)
    public void testeVendaPeixe_PeixeNaoEncontrado() throws Exception {
		
		//cenário
		aqua1.compraPeixe(peixe1);
		
		//ação
		aqua1.vendePeixe(peixe2);
      
	}

}
