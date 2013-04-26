package src.tests.sistema.factoresexternos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sistema.entidades.carretera.tramocarreraciclista.TramoCiclista;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.entrada.lectura.Lector;
import sistema.entrada.parseador.parser.ParseadorCarrera;
import sistema.factoresexternos.FactoresExternos;
import sistema.manager.Manager;
import src.tests.utils.TestUtilidadesFactoresExternos;

@RunWith(JUnit4.class)
public class TestFactoresExternos {
	
	private Bicicleta bicicleta;
	private Bicicleta bicicletatest;
	private FactoresExternos factoresExternos;
	private Map<Integer, TramoCiclista> mapa;
	
	
	TestUtilidadesFactoresExternos testUtilidadesFactoresExternos;
	@Before
	public void run() {
		
		Lector lectorConfiguracion = new Lector(Manager.DEFAULT_CONFIG_PATH, true);
		
		String configuracioncarreraciclista = lectorConfiguracion.cargarFicheroCompelto();
		
		mapa = new HashMap<Integer, TramoCiclista>();
		
		ParseadorCarrera parseadorcarrera = new ParseadorCarrera(mapa);
		
		parseadorcarrera.parse(configuracioncarreraciclista);
		
		bicicleta = new Bicicleta();
		bicicletatest = new Bicicleta();
		factoresExternos = new FactoresExternos(bicicleta, mapa);
		testUtilidadesFactoresExternos = new TestUtilidadesFactoresExternos(mapa, bicicletatest);
		
	
	}
	
	@Test
	public void testNoNulos() {
		
		// Comprobamos que las propiedades no sean nulas
		assertNotNull("Error: La bici es nula", bicicleta);
		assertNotNull("Error: El mapa es nulo", mapa);
		
	}
	
	@Test
	public void testResultadosEsperados() {
		
		//Se ejecutan los factores externos para modificar la velocidad
		testUtilidadesFactoresExternos.ejecutar();
		
		//Se ejecuta el objeto de la clase factores externos
		factoresExternos.ejecutar();
		
		//Comparamos que la velocidad, al modificarla con los valores externos es la correcta
		assertEquals("Error: La velocidad de la bicicleta al modificarla con los factores externos no es la correcta", bicicletatest.getVelocidad(), 
																													   bicicleta.getVelocidad(), 0);
	}
}