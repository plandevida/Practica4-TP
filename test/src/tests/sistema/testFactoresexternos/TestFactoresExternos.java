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

import sistema.entrada.lectura.LectorManager;
import sistema.factoresexternos.viento.MiViento;
import sistema.manager.VariablesDeContexto;
import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCarreraMock;
import src.tests.sistema.entidadesmock.factoresexternos.CurvioloMock;
import src.tests.sistema.entidadesmock.factoresexternos.EoloMock;
import src.tests.sistema.entidadesmock.factoresexternos.FactoresExternosMock;
import src.tests.sistema.entidadesmock.factoresexternos.PendioloMock;
import src.tests.sistema.entidadesmock.parseadores.parser.ParseadorCarreraMock;
import src.tests.sistema.entidadesmock.tiempo.RelojMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;
import src.tests.utils.TestUtilidadesFactoresExternos;

@RunWith(JUnit4.class)
public class TestFactoresExternos {
	
	private BicicletaMock bicicleta;
	private BicicletaMock bicicletatest;
	private FactoresExternosMock factoresExternos;
	private Map<Integer, TramoCarreraMock> mapa;
	
	
	TestUtilidadesFactoresExternos testUtilidadesFactoresExternos;
	@Before
	public void run() {
		
		LectorManager lectorConfiguracion = new LectorManager(VariablesDeContexto.DEFAULT_FILE_CONFIG_PATH, true);
		
		String configuracioncarreraciclista = lectorConfiguracion.cargarFicheroCompelto();
		
		mapa = new HashMap<Integer, TramoCarreraMock>();
		
		ParseadorCarreraMock parseadorcarrera = new ParseadorCarreraMock(mapa);
		
		parseadorcarrera.parse(configuracioncarreraciclista);
		
		bicicleta = new BicicletaMock();
		bicicletatest = new BicicletaMock();
		
		List<BicicletaMock> listabicicletas = new ArrayList<BicicletaMock>();
		listabicicletas.add(bicicleta);
		
		factoresExternos = new FactoresExternosMock(listabicicletas, mapa, new EoloMock(listabicicletas, RelojMock.getInstance(), new HashMap<Integer, MiViento>()), new CurvioloMock(), new PendioloMock(listabicicletas));
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