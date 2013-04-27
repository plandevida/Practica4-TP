package src.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import src.tests.sistema.testEntidades.personas.ciclistas.TestCiclista;
import src.tests.sistema.testEntidades.tiempo.TestReloj;
import src.tests.sistema.testEntidades.veiculos.TestBicicleta;
import src.tests.sistema.testFactoresexternos.TestFactoresExternos;

@RunWith(Suite.class)
@SuiteClasses({ TestCiclista.class, TestBicicleta.class, TestFactoresExternos.class, TestReloj.class})
public class TestSuites {

}
