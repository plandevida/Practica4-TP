package src.tests.sistema.entidadesmock.tiempo;

import sistema.interfaces.ObjetosQueSeEjecutan;

public class ContadorMock implements ObjetosQueSeEjecutan {
	//el numero de impulsos que lleva 
	/**
	 * @uml.property  name="impulsos"
	 */
	protected int impulsos;
	
	public ContadorMock() {
		impulsos = 0;
	}
	/**
	 * Metodo que ejecuta.
	 */
	public void ejecuta() {
		
		nuevoImpulso();
	}
	
	/**
	 * Método que añade un nuevo impulso al actual.
	 */
	public void nuevoImpulso() {
		impulsos++;
	}
	
	/**
	 * Metodo para obtener los impulsos.
	 * @return  Los impulsos.
	 * @uml.property  name="impulsos"
	 */
	public int getImpulsos() {
		return impulsos;
	}
}
