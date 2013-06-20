package src.tests.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.factoresexternos.viento.MiViento;
import sistema.manager.VariablesDeContexto;
import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCarreraMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;

public class TestUtilidadesFactoresExternos {
	
	/**
	 * @uml.property  name="carreteradecarreraciclista"
	 * @uml.associationEnd  qualifier="reco:java.lang.Integer src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCarreraMock"
	 */
	private Map<Integer, TramoCarreraMock> carreteradecarreraciclista;
	/**
	 * @uml.property  name="bici"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private BicicletaMock bici;
	/**
	 * @uml.property  name="listacurvas"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="sistema.entidades.carretera.tramocarreraciclista.Curva"
	 */
	private List<Curva> listacurvas;
	
	public TestUtilidadesFactoresExternos(Map<Integer, TramoCarreraMock> carreteradecarreraciclista, BicicletaMock bici){
		this.carreteradecarreraciclista = carreteradecarreraciclista;
		this.bici = bici;
		listacurvas = new ArrayList<Curva>();
	}
	
	
	/**
	 *  Busca el tramo en el que se encuentra la bici 
	 * @return devuelve el tramo
	 */
	public TramoCarreraMock tramoActual() {
		
		TramoCarreraMock tramo = new TramoCarreraMock(0, 0);
		
		for(Integer reco : carreteradecarreraciclista.keySet()) {

			if ( carreteradecarreraciclista.get(reco).getKilometros() <= (int) bici.getEspacioRecorrido() ) {
				tramo = carreteradecarreraciclista.get(reco);
			}
		}
		return tramo;
	}
	
	/**
	 * Calcular el factor de la pendiente, gracias al angulo de la pendiente 
	 * 
	 * @return devuelve el factorpendiente
	 */
	public double pendienteTramoActual() {
		
		int angulograd = 0;
		double angulorad = 0d;
		double factorpendiente = 0d;
		
		TramoCarreraMock tramo = tramoActual();
		
		angulograd = tramo.getPendiente();
		angulorad = (angulograd * Math.PI)/180;
		

		factorpendiente = Math.cos(angulorad);
		
		if (angulograd < 0) {
			factorpendiente = factorpendiente + 1d;

		}

		
		return factorpendiente;
	}
	
	/**
	 * Mira el viento que hay en el tramo actual y devuelve un factor
	 * 
	 * @return devuelve un porcentaje calculado a traves del viento y su direccion
	 */
	public double vientoTramoActual(){
		
		MiViento viento = VariablesDeContexto.direcionvientoinicial;
		
		return viento.getAceleracion();
		
	}
	
	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 * 
	 */
	public void setVelocidadModificada() {

		double velocidad = bici.getVelocidad() * pendienteTramoActual();
		
		velocidad = velocidad + velocidad * vientoTramoActual();
		
		bici.setVelocidad(velocidad);
	}
	public void añadirCurva(){
		listacurvas.add(new Curva(5d,5d,4));
	}
	
	public List<Curva> getListacurvas() {
		return listacurvas;
	}


	public void setListacurvas(List<Curva> listacurvas) {
		this.listacurvas = listacurvas;
	}


	/**
	 * Ejecuta los factores externos
	 * 
	 */
	public boolean ejecutar() {
		
		setVelocidadModificada();
		añadirCurva();
		
		return true;
	}
}
