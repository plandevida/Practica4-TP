package src.tests.sistema.testFactoresexternos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sistema.entrada.lectura.Lector;
import sistema.manager.VariablesDeContexto;
import src.tests.sistema.entidadesmock.FactoresExternosMock;
import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCiclistaMock;
import src.tests.sistema.entidadesmock.parseadores.parser.ParseadorCarreraMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;
import src.tests.utils.TestUtilidadesFactoresExternos;

@RunWith(JUnit4.class)
public class TestFactoresExternos {
	
	private BicicletaMock bicicleta;
	private BicicletaMock bicicletatest;
	private FactoresExternosMock factoresExternos;
	private Map<Integer, TramoCiclistaMock> mapa;
	
	
	TestUtilidadesFactoresExternos testUtilidadesFactoresExternos;
	@Before
	public void run() {
		
		Lector lectorConfiguracion = new Lector(VariablesDeContexto.DEFAULT_CONFIG_PATH, true);
		
		String configuracioncarreraciclista = lectorConfiguracion.cargarFicheroCompelto();
		
		mapa = new HashMap<Integer, TramoCiclistaMock>();
		
		ParseadorCarreraMock parseadorcarrera = new ParseadorCarreraMock(mapa);
		
		parseadorcarrera.parse(configuracioncarreraciclista);
		
		bicicleta = new BicicletaMock();
		bicicletatest = new BicicletaMock();
		
		List<BicicletaMock> listabicicletas = new ArrayList<BicicletaMock>();
		listabicicletas.add(bicicleta);
		
		factoresExternos = new FactoresExternosMock(listabicicletas, mapa);
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
		factoresExternos.ejecuta();
		
		//Comparamos que la velocidad, al modificarla con los valores externos es la correcta
		assertEquals("Error: La velocidad de la bicicleta al modificarla con los factores externos no es la correcta", bicicletatest.getVelocidad(), 
																													   bicicleta.getVelocidad(), 0);
	}
}