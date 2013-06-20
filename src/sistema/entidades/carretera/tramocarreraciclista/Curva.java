package sistema.entidades.carretera.tramocarreraciclista;

/**
 * Clase que representa una curava de la carrera ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Curva {
	
	/**
	 * @uml.property  name="puntokilometrico"
	 */
	private Double puntokilometrico;
	/**
	 * @uml.property  name="velocidadmaximacurva"
	 */
	private Double velocidadmaximacurva;
	/**
	 * @uml.property  name="ciclistarhanpasadocurva" multiplicity="(0 -1)" dimension="1"
	 */
	private int[] ciclistarhanpasadocurva;

	public Curva( Double kilometro, Double velocidadmaxima, int ciclistas) {
		
		puntokilometrico = kilometro;
		velocidadmaximacurva = velocidadmaxima;
		
		ciclistarhanpasadocurva= new int[ciclistas];
		
		for(int i=0;i<ciclistas;i++){
			ciclistarhanpasadocurva[i]= -1;
		}
	}

	/**
	 * Obtiene el punto kilométrico donde está la curva.
	 * @return  El punto kilometrico
	 * @uml.property  name="puntokilometrico"
	 */
	public Double getPuntokilometrico() {
		
		return puntokilometrico;
	}

	/**
	 * Obtiene la velocidad máxima a la que se puede pasar por la curva.
	 * @return  La velocidad máxima de la curva.
	 * @uml.property  name="velocidadmaximacurva"
	 */
	public Double getVelocidadmaximacurva() {
		
		return velocidadmaximacurva;
	}
	public int getCiclistarhanpasadocurva(int i) {
		
		int p = ciclistarhanpasadocurva[1];
		
		return p;
	}

	public void setCiclistashanpasadocurva(int valor, int i) {
		
		ciclistarhanpasadocurva[i]=valor;
	}
}
