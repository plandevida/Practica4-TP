package sistema.entidades.carretera.tramocarreraciclista;

/**
 * Clase que representa una curava de la carrera ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Curva {
	
	Double puntokilometrico;
	Double velocidadmaximacurva;
	int[] ciclistarhanpasadocurva;
	


	public Curva( Double kilometro, Double velocidadmaxima, int ciclistas) {
		puntokilometrico = kilometro;
		velocidadmaximacurva = velocidadmaxima;
		
		ciclistarhanpasadocurva= new int[ciclistas];
		
		for(int i=0;i<ciclistas;i++){
			ciclistarhanpasadocurva[i]= -1;
//			System.out.println("curvas "+ciclistarhanpasadocurva[i]);
		}
	}

	/**
	 * Obtiene el punto kilométrico donde está la curva.
	 * 
	 * @return El punto kilometrico
	 */
	public Double getPuntokilometrico() {
		return puntokilometrico;
	}

	/**
	 * Obtiene la velocidad máxima a la que se puede pasar por la curva.
	 * 
	 * @return La velocidad máxima de la curva.
	 */
	public Double getVelocidadmaximacurva() {
		return velocidadmaximacurva;
	}
	public int getCiclistarhanpasadocurva(int i) {
		int p = ciclistarhanpasadocurva[1];
		return p;
	}

	public void setCiclistarhanpasadocurva(int valor, int i) {
		
		ciclistarhanpasadocurva[i]=valor;
	}
}
