package src.tests.utils;

public class UtilidadesNumericas {

	public static double redondear( double numero, int decimales ) {
	    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	}
}
