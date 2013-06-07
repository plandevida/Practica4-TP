package src.tests.sistema.testEntidades.personas.ciclistas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sistema.entrada.lectura.LectorManager;
import sistema.manager.VariablesDeContexto;
import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCarreraMock;
import src.tests.sistema.entidadesmock.parseadores.parser.ParseadorCarreraMock;
import src.tests.sistema.entidadesmock.personas.ciclistas.CiclistaMock;
import src.tests.sistema.entidadesmock.tiempo.RelojMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;
import src.tests.utils.TestUtilidadesBicicleta;

@RunWith(JUnit4.class)
public class TestCiclista {
	
	private CiclistaMock ciclista;
	
	private String nombreciclista = "Juan";
	private int numeromallot = 1;
	private int cadenciaciclista = 60;
	private double tiempopedalada = 1;
	private int peso = 65;
	private double fuerza = 100;
	private BicicletaMock bicicletaciclista;
	private RelojMock relojciclista;
	
	private Map<Integer, TramoCarreraMock> mapa;
	
	private TestUtilidadesBicicleta utilidadesBicicleta;
	
	@Before
	public void run() {
		
		LectorManager lectorConfiguracion = new LectorManager(VariablesDeContexto.DEFAULT_FILE_CONFIG_PATH, true);
		
		String configuracioncarreraciclista = lectorConfiguracion.cargarFicheroCompelto();
		
		mapa = new HashMap<Integer, TramoCarreraMock>();
		
		ParseadorCarreraMock parseadorcarrera = new ParseadorCarreraMock(mapa);
		
		parseadorcarrera.parse(configuracioncarreraciclista);
		
		utilidadesBicicleta = new TestUtilidadesBicicleta();
		
		relojciclista = RelojMock.getInstance();
		
		bicicletaciclista = new BicicletaMock();
		
		ciclista = new CiclistaMock(nombreciclista, numeromallot, cadenciaciclista, bicicletaciclista, tiempopedalada, relojciclista, peso, fuerza);
	}
	
	@Test
	public void testNoNulos() {
		
		// Comprobamos que las propiedades no sean nulas
		assertNotNull("Error: El ciclista es nulo", ciclista);
		assertNotNull("Error: El nombre es nulo", ciclista.getNombre());
		assertNotNull("Error: La cadencia es nula", ciclista.getCadencia());
		assertNotNull("Error: La bicicleta es nula", ciclista.getBicicletamontada());
		assertNotNull("Error: El ciclista no tiene reloj", ciclista.getReloj());
	}
	
	@Test
	public void testResultadosEsperados() {
		
		// Comprobamos que los resultados son los esperados
		
		// Incremento de la velocidad de la bicicleta al pedalear
		
		BicicletaMock bicicleta = ciclista.getBicicletamontada();
		
		ciclista.ejecuta();
		
		double velocidadesperada = utilidadesBicicleta.velocidadDeBici(ciclista.getCadencia(), 
																	bicicleta.getRadiorueda(), 
																	bicicleta.getPlatos()[bicicleta.getPlatoactual()], 
																	bicicleta.getPinhones()[bicicleta.getPinhonactual()],
																	bicicleta.getEspacioRecorrido());
		
		assertEquals("Error: La velocidad de la bicicleta no es la correcta", velocidadesperada, bicicleta.getVelocidad(), 0);
	}
}
