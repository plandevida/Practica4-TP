package sistema.vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JPanel;

import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.interfaces.ObjetosQueSeEjecutan;

/**
 * Esta clase genera la representación del mapa mediante Swing , utilizando un
 * archivo de texto
 * 
 * @author Juan Luis Perez
 * @author Juan Carlos Marco
 * @author Emilio Alvarez
 * 
 */

public class Lienzo extends Canvas implements ObjetosQueSeEjecutan {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
//    private EntradaFichero entrada;
    private Graphics g;
    private List<Ciclista> lista_de_ciclistas;
//    private Pendiente pendiente;
    private TreeMap<Integer, Integer> arbol;
//    private Curva curva;
    public Lienzo(List<Ciclista> micic /*,Pendiente pen,Curva curv*/ ) {

//	curva = curv;
	lista_de_ciclistas = micic;
	contentPane = new JPanel();
	contentPane.setLayout(null);
	setBounds(0, 0, Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA / 2);

//	entrada = new EntradaFichero();
//	pendiente = pen;
//	pendiente.setArbol(entrada.convertirFicheroAArbol("carretera.txt", ":;"));
//	arbol = pendiente.getArbol();
//	matriz = entrada.convertirAArrayFichero("carretera.txt", ":;");
	
    }

    @Override
    public void paint(Graphics g) {
	
	Color colores[] = new Color[6];
	colores[0] = Constantes.COLOR_CIC_0;
	colores[1] = Constantes.COLOR_CIC_1;
	colores[2] = Constantes.COLOR_CIC_2;
	colores[3] = Constantes.COLOR_CIC_3;
	colores[4] = Constantes.COLOR_CIC_4;
	colores[5] = Constantes.COLOR_CIC_5;

	super.paint(g);

	int x = 0;
	int xfin = 0;
	int y = Constantes.ALTO_VENTANA / 4;
	int yfin = 0;

	int i = 0;

	Iterator<Entry<Integer, Integer>> it = arbol.entrySet().iterator();
	Iterator<Entry<Integer, Integer>> itaux = arbol.entrySet().iterator();
	if (it.hasNext()) {
	    itaux.next();
	}
	while (itaux.hasNext()) {

	    Entry<Integer, Integer> tramoini = it.next();

	    if (it.hasNext()) {

		Entry<Integer, Integer> tramofin = itaux.next();

		// pintamos la carretera

		g.setColor(Color.BLACK);
		g.drawLine((int) x, y, tramofin.getKey()
			/ Constantes.FACTORESCALA, y - tramoini.getValue());

		// pintamos el cielo
		Polygon polygonCielo;
		polygonCielo = creaPoligono(
			new Point(x, 0),
			new Point(x, y - 1),
			new Point((int) tramofin.getKey()
				/ Constantes.FACTORESCALA, (y - tramoini
				.getValue()) - 1), new Point(tramofin.getKey()
				/ Constantes.FACTORESCALA, 0));
		Polygon polygon = new Polygon();

		pintaPoligono(polygonCielo, g, Color.CYAN);

		// pintamos el suelo

		Polygon polygonSuelo;
		polygonSuelo = creaPoligono(
			new Point(x, Constantes.ALTO_VENTANA),
			new Point(x, y + 1),
			new Point((int) tramofin.getKey()
				/ Constantes.FACTORESCALA, (y - (int) tramoini
				.getValue()) + 1),
			new Point((int) tramofin.getKey()
				/ Constantes.FACTORESCALA,
				Constantes.ALTO_VENTANA));
		pintaPoligono(polygonSuelo, g, Color.GREEN);

		x = tramofin.getKey() / Constantes.FACTORESCALA;
		y = y - tramoini.getValue();
	    }

	}

	//pintamos los PK de las curvas
	
	/*Iterator<Entry<Integer, Integer>> itcurva = curva.getArbolCurvas().entrySet().iterator();
	while (itcurva.hasNext()) 
	{
	    Entry<Integer, Integer> tramocurva =  itcurva.next();
	    g.setColor(Color.black);
	    g.drawLine(tramocurva.getKey(), 0, tramocurva.getKey(),
		    Constantes.ALTO_VENTANA);
	    g.drawString("Curva en PK "+ tramocurva.getKey() , tramocurva.getKey()-60, 30);
	    g.drawString("con velocidad maxima " 
		    + tramocurva.getValue() , tramocurva.getKey()-80, 40);
	  
	}*/
	// aqui se pondra la informacion del ciclista para que se vaya pintando,
	// ahora solo se pinta un punto en pantalla
	// for (i = 0; i < cic.size(); i++) {
	int id_color_ciclista = 0;
	for (Ciclista c : lista_de_ciclistas) {
	    g.setColor(colores[id_color_ciclista]);

	    if (calculaYparaPuntoCiclista(c, arbol) == 0)
		y = Constantes.ALTO_VENTANA / 4
			- Constantes.ANCHO_PUNTO_CICLISTA / 2;
	    else
		y = calculaYparaPuntoCiclista(c, arbol);
	    g.fillOval(
		    (int) (c.getBicicletamontada().getEspacioRecorrido() / Constantes.FACTORESCALA)
			    - Constantes.ANCHO_PUNTO_CICLISTA / 2, y,
		    Constantes.ANCHO_PUNTO_CICLISTA,
		    Constantes.ANCHO_PUNTO_CICLISTA);

	    id_color_ciclista++;

	}

    }

    private int calculaYparaPuntoCiclista(Ciclista cic,
	    TreeMap<Integer, Integer> ar) {
	int yresu = 0;
	int dify = 0;
	int yacum = Constantes.ALTO_VENTANA / 4;
	boolean encontrado = false;
	Iterator<Entry<Integer, Integer>> it = ar.entrySet().iterator();
	Iterator<Entry<Integer, Integer>> itaux = ar.entrySet().iterator();
	if (it.hasNext()) {
	    itaux.next();
	}
	while (itaux.hasNext() && !encontrado) {

	    Entry<Integer, Integer> tramoini = it.next();

	    if (it.hasNext()) {

		Entry<Integer, Integer> tramofin = itaux.next();

		if (cic.getBicicletamontada().getEspacioRecorrido() >= tramoini.getKey()
			&& cic.getBicicletamontada().getEspacioRecorrido() < tramofin
				.getKey()) {
		    encontrado = true;

		    int metro_en_el_tramo_del_ciclista = (int) (cic.getBicicletamontada()
			    .getEspacioRecorrido() - tramoini.getKey());
		    int diftramos = tramofin.getKey() - tramoini.getKey();

		    dify = tramoini.getValue();

		    int yfintramo = yacum + dify;
		    yresu = metro_en_el_tramo_del_ciclista * dify / diftramos;
		    yresu = yacum
			    - (metro_en_el_tramo_del_ciclista * dify / diftramos)
			    - Constantes.ANCHO_PUNTO_CICLISTA / 2;

		}

	    }
	    yacum = yacum - tramoini.getValue();

	}

	return yresu;
    }

    private Polygon creaPoligono(Point p1, Point p2, Point p3, Point p4) {
	Polygon polygon = new Polygon();
	polygon.addPoint(p1.x, p1.y);
	polygon.addPoint(p2.x, p2.y);
	polygon.addPoint(p3.x, p3.y);
	polygon.addPoint(p4.x, p4.y);
	return polygon;
    }

    private void pintaPoligono(Polygon p, Graphics mig, Color col) {
	mig.setColor(col);
	mig.fillPolygon(p);
    }

    @Override
    public void ejecuta() {
	// TODO Auto-generated method stub
//	arbol = pendiente.getArbol();
	repaint();
    }

}
