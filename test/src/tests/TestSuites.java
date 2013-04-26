package src.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import src.tests.sistema.entidades.personas.ciclistas.TestCiclista;
import src.tests.sistema.entidades.tiempo.TestReloj;
import src.tests.sistema.entidades.veiculos.TestBicicleta;
import src.tests.sistema.factoresexternos.TestFactoresExternos;

@RunWith(Suite.class)
@SuiteClasses({ TestCiclista.class, TestBicicleta.class, TestFactoresExternos.class, TestReloj.class})
public class TestSuites {

}
