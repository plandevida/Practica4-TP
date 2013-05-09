package sistema.vista;

import java.awt.Color;

public class Constantes {
	public static final double FUERZA_G = 9.80665;
    public static final double MASA_BICICLETA_ESTANDAR = 0.7138;

    public static int MAX_CICLISTAS = 6;

    public static final int NUM_PIN_0 = 17;
    public static final int NUM_PIN_1 = 16;
    public static final int NUM_PIN_2 = 15;
    public static final int NUM_PIN_3 = 14;
    public static final int NUM_PIN_4 = 13;
    public static final int NUM_PIN_5 = 12;

    public static final int NUM_PINONES = 6;

    public static final int NUM_PLA_0 = 36;
    public static final int NUM_PLA_1 = 40;
    public static final int NUM_PLA_2 = 44;

    public static final int NUM_PLATOS = 3;

    public static final int X_INICIAL = 5;
    public static final int Y_INICIAL = 5;

    public static final int ANCHO_BOTON = 89;
    public static final int ALTO_BOTON = 25;
    public static final int ANCHO_TEXTBOX = 275;
    public static final int ALTO_TEXTBOX = ALTO_BOTON * 4;

    public static final int ANCHO_VENTANA = 1366;
    public static final int ALTO_VENTANA = 768;

    public static final String CADENCIA_MENOS = "Caden-";
    public static final String CADENCIA_MAS = "Caden+";
    public static final String PINON_MAS = "Pin+";
    public static final String PINON_MENOS = "Pin-";
    public static final String PLATO_MAS = "Pla+";
    public static final String PLATO_MENOS = "Pla-";
    public static final String FRENO_MAS = "Fre+";
    public static final String FRENO_MENOS = "Fre-";

    public static final double DISTANCIA_FRENADO = 10;
    public static final double FACTORVIENTO = 0.1;
    public static final double FACTORPENDIENTE = 0.1;
    public static final int FACTORESCALA = 1;

    public static final int NUM_DECIMALES = 3;

    //sirven para subir o bajar plato, cadncia, pinon...
    public static final int SUBIR = -2;
    public static final int BAJAR = -1;

    public static final int ANCHO_PUNTO_CICLISTA = 20;
    public static final Color COLOR_CIC_0 = Color.MAGENTA;
    public static final Color COLOR_CIC_1 = Color.blue;
    public static final Color COLOR_CIC_2 = Color.DARK_GRAY;
    public static final Color COLOR_CIC_3 = Color.white;
    public static final Color COLOR_CIC_4 = Color.ORANGE;
    public static final Color COLOR_CIC_5 = Color.RED;

    public static final int MAX_METROS_CARRETERA = 15000;

    public static double IMPULSO_RELOJ_SEGUNDOS = 1;
    public static double IMPULSO_RELOJ_MILISEGUNDOS = IMPULSO_RELOJ_SEGUNDOS * 1000;

    public static String FICHERO_DE_COMANDOS = "ficherorandom";
}
