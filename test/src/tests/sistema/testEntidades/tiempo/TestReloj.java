package src.tests.sistema.testEntidades.tiempo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import src.tests.sistema.entidadesmock.tiempo.Reloj;




@RunWith(JUnit4.class)
public class TestReloj {

	private Reloj reloj;
	
	@Before
	public void run() {
			
		reloj = new Reloj();
		
	}
	
	@Test
	public void testNoNulos() {
		
		// Comprobamos que las propiedades no sean nulas
		assertNotNull("Error: El reloj es nulo", reloj);
		assertNotNull("Error: La hora es nula", reloj.getHoras());
		assertNotNull("Error: Los minutos son nulos", reloj.getMinutos());
		assertNotNull("Error: Los segundos son nulos", reloj.getSegundos());
		assertNotNull("Error: Los impulsos son nulos", reloj.getImpulsos());
	}
	
	@Test
	public void testResultadosEsperados() {
		
		for ( int i = 0; i < 100*60*60; i++) {
			
			reloj.nuevoImpulso();
			
			//Se mira que al hacer 100 impulsos aumente 1 segundo
			if(i == 100) assertEquals("Error: La velocidad de la bicicleta no es la correcta", 1, reloj.getSegundos(), 0);
			
			//Se mira que al hacer 60 segundos aumente 1 minuto
			if(i == 100*60) assertEquals("Error: La velocidad de la bicicleta no es la correcta", 1, reloj.getMinutos(), 0);
			
			//Se mira que al hacer 60 minutos aumente 1 hora
			if(i == 100*60*60) assertEquals("Error: La velocidad de la bicicleta no es la correcta", 1, reloj.getHoras(), 0);
		}
		
		//Cuando pasa 1 hora el for termina y los impulsos vuelven a cero
		assertEquals("Error: La velocidad de la bicicleta no es la correcta", 0, reloj.getImpulsos(), 0);

		
		
	}
}
