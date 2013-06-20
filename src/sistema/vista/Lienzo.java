package sistema.vista;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entrada.imagenes.Imagenes;
import sistema.interfaces.ObjetosQueSeEjecutan;
import sistema.manager.VariablesDeContexto;

/**
 * Esta clase genera la representación del mapa mediante Swing , utilizando un
 * archivo de texto
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 * 
 */

public class Lienzo extends Canvas implements ObjetosQueSeEjecutan {

    private static final long serialVersionUID = 1L;
	 
    /**
	 * @uml.property  name="contentPane"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private JPanel contentPane;
    
    /**
	 * @uml.property  name="lista_de_ciclistas"
	 */
    private List<Ciclista> lista_de_ciclistas;
    
    /**
	 * @uml.property  name="carretera"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="sistema.entidades.carretera.tramocarreraciclista.TramoCarrera" qualifier="key:java.lang.Integer sistema.entidades.carretera.tramocarreraciclista.TramoCarrera"
	 */
    private Map<Integer, TramoCarrera> carretera;
    
    /**
	 * @uml.property  name="curva"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="sistema.entidades.carretera.tramocarreraciclista.Curva"
	 */
    private List<Curva> curva;

    public Lienzo(List<Ciclista> micic, Map<Integer, TramoCarrera> carreteradecarreraciclsta, List<Curva> curv) {

		curva = curv;
		lista_de_ciclistas = micic;
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setBounds(0, 0, VariablesDeContexto.ANCHO_LIENZO, VariablesDeContexto.ALTO_LIENZO);
	
		carretera = carreteradecarreraciclsta;
    }

    private int calculaYparaPuntoCiclista(Ciclista ciclista) {
		int yresu = 0;
		int dify = 0;
		int yacum = VariablesDeContexto.ALTO_VENTANA / 4;
		boolean encontrado = false;
		
		Iterator<Map.Entry<Integer,TramoCarrera>> it = carretera.entrySet().iterator();
		Iterator<Map.Entry<Integer,TramoCarrera>> itsiguiente = carretera.entrySet().iterator();
		
		if (it.hasNext()) {
		    itsiguiente.next();
		}
		while (itsiguiente.hasNext() && !encontrado) {
	
		    Entry<Integer, TramoCarrera> tramoactual = it.next();
	
		    if (it.hasNext()) {
	
			Entry<Integer, TramoCarrera> siguentetramo = itsiguiente.next();
	
				if (ciclista.getBicicletamontada().getEspacioRecorrido() >= tramoactual.getKey()
					&& ciclista.getBicicletamontada().getEspacioRecorrido() < siguentetramo.getKey()) {
				    encontrado = true;
		
				    int metro_en_el_tramo_del_ciclista = (int) (ciclista.getBicicletamontada()
					    .getEspacioRecorrido() - tramoactual.getKey());
				    int diftramos = siguentetramo.getKey() - tramoactual.getKey();
		
				    dify = (int)tramoactual.getValue().getPendiente();
		
				    yresu = metro_en_el_tramo_del_ciclista * dify / diftramos;
				    yresu = yacum
					    - (metro_en_el_tramo_del_ciclista * dify / diftramos)
					    - VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA / 2;
		
				}
	
		    }
		    yacum = yacum - (int)tramoactual.getValue().getPendiente();
	
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

    @Override
    public void ejecuta() {
	
	    repaint();
    }

    @Override
    public void paint(Graphics g) {

		Color colores[] = new Color[VariablesDeContexto.MAX_CICLISTAS];
		
		for (int i = 0; i < VariablesDeContexto.MAX_CICLISTAS; i++) {
			colores[i] = VariablesDeContexto.COLORES[ i%VariablesDeContexto.COLORES.length ];
		}
	
		super.paint(g);
	
		int x = 0;
		int y = VariablesDeContexto.ALTO_LIENZO / 2;
		double x_aux = 0;
		int max = (int) VariablesDeContexto.LONGITUD_CARRERA;
		
		Iterator<Map.Entry<Integer,TramoCarrera>> it = carretera.entrySet().iterator();
		Iterator<Map.Entry<Integer,TramoCarrera>> itsiguiente = carretera.entrySet().iterator();
		
		if (it.hasNext()) {
		    itsiguiente.next();
		}
		while (itsiguiente.hasNext()) {
	
		    Entry<Integer, TramoCarrera> tramoactual = it.next();
	
		    if (it.hasNext()) {
	
				Entry<Integer, TramoCarrera> tramosiguente = itsiguiente.next();
		
				// pintamos la carretera
				g.setColor(Color.BLACK);
				
				g.drawLine((int)x_aux, y, (int)((tramosiguente.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO), (int) (y - tramoactual.getValue().getPendiente()));
				
//				System.out.println(x_aux);
//				System.out.println((int)((tramosiguente.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO));
				
				// pintamos el cielo
				Polygon polygonCielo = creaPoligono(new Point((int)x_aux, 0),
					new Point((int)x_aux, y - 1),
					new Point((int)((tramosiguente.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO),
								(int)(y - tramoactual.getValue().getPendiente()) - 1), new Point(
								(int)((tramosiguente.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO), 0));
				
		
				pintaPoligono(polygonCielo, g, Color.CYAN);
		
				// pintamos el suelo
				Polygon polygonSuelo = creaPoligono(new Point((int)x_aux, VariablesDeContexto.ANCHO_LIENZO),
													new Point((int)x_aux, y + 1),
													new Point((int)((tramosiguente.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO),
															  (int)(y - tramoactual.getValue().getPendiente()) + 1), 
													new Point((int)((tramosiguente.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO), VariablesDeContexto.ALTO_VENTANA));
				
				pintaPoligono(polygonSuelo, g, Color.GREEN);
		
				x = tramosiguente.getKey() / VariablesDeContexto.FACTORESCALA;
				y = (int) (y - tramoactual.getValue().getPendiente());
				x_aux = (tramosiguente.getKey() / (double)max) * VariablesDeContexto.ANCHO_LIENZO;
		    }

		}
		
		// Pintamos la meta
		g.setColor(Color.RED);
		g.drawImage(Imagenes.getMeta35x25().getImage(), (int) VariablesDeContexto.META, 11, null);
	    g.drawLine((int) VariablesDeContexto.META, 0, (int) VariablesDeContexto.META, VariablesDeContexto.ALTO_LIENZO);

		// pintamos los PK de las curvas
		Iterator<Curva> itcurva = curva.iterator();
		int offsetY = 0;
		while (itcurva.hasNext()) {
			
		    Curva tramocurva = itcurva.next();
		    g.setColor(Color.black);
		    g.drawString("Curva en PK " + tramocurva.getPuntokilometrico().intValue(), tramocurva.getPuntokilometrico().intValue(), 11 + offsetY);
		    g.drawString("con velocidad maxima " + tramocurva.getVelocidadmaximacurva(), tramocurva.getPuntokilometrico().intValue(), 21 + offsetY);
		    g.drawLine(tramocurva.getPuntokilometrico().intValue(), 0, tramocurva.getPuntokilometrico().intValue(), VariablesDeContexto.ALTO_LIENZO);
	
		    offsetY += 27;
		}
		
		// aqui se pondra la informacion del ciclista para que se vaya pintando,
		int id_color_ciclista = 0;
		
		for (Ciclista ciclista : lista_de_ciclistas) {
			
		    g.setColor(colores[id_color_ciclista]);
	
		    if (calculaYparaPuntoCiclista(ciclista) == 0)
		    	y = VariablesDeContexto.ALTO_VENTANA / 4 - VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA / 2;
		    else
		    	y = calculaYparaPuntoCiclista(ciclista);
		    
		    x = (int) ((ciclista.getBicicletamontada().getEspacioRecorrido() / max) * VariablesDeContexto.ANCHO_LIENZO) - VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA / 2;
		    
		    if ( ciclista.isEstrellado() ) {
		    	g.drawImage(Imagenes.getMuerto20x20().getImage(), x, y, null);
		    }
		    else {
			    g.fillOval( x, y,
	    					VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA,
	    					VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA);
		    }
	
		    id_color_ciclista++;
		}
    }

    private void pintaPoligono(Polygon p, Graphics g, Color col) {
		g.setColor(col);
		g.fillPolygon(p);
    }
}