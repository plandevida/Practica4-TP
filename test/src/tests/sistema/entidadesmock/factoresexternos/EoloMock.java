package src.tests.sistema.entidadesmock.factoresexternos;

import java.util.List;
import java.util.Map;

import sistema.factoresexternos.viento.MiViento;
import src.tests.sistema.entidadesmock.tiempo.RelojMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;

public class EoloMock {
	 
	private List<BicicletaMock> bicicletas;
	private RelojMock relojTiempo;
	private Map<Integer, MiViento> mapameteorologico;
	
	public EoloMock(List<BicicletaMock> listabicicletas, RelojMock reloj, Map<Integer, MiViento> mapaviento) {
		
		bicicletas = listabicicletas;
		relojTiempo = reloj;
		mapameteorologico = mapaviento;
	}
	
	/**
	 * Mira el viento que hay en el tramo actual y devuelve un factor
	 * 
	 * @return devuelve un porcentaje calculado a traves del viento y su direccion
	 */
	private double aceleracionViento() {
		
		MiViento viento = MiViento.NULO;
		
		if ( mapameteorologico.containsKey( relojTiempo.getHoras() ) ) {
			
			viento = mapameteorologico.get( relojTiempo.getHoras() );
		}
		
		return viento.getAceleracion();
	}

	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 */
	public void setVientoModificado() {

		for(BicicletaMock bici : bicicletas) {
			
			bici.setViento(aceleracionViento());
		}
	}
	
	/**
	 * Obtiene el reloj que usa Eolo.
	 * 
	 * @return el reloj
	 */
	public RelojMock getReloj() {
		return relojTiempo;
	}

}
