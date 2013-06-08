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
	 
    private JPanel contentPane;
    
    private List<Ciclista> lista_de_ciclistas;
    
    private Map<Integer, TramoCarrera> carretera;
    
    private List<Curva> curva;

    public Lienzo(List<Ciclista> micic, Map<Integer, TramoCarrera> carreteradecarreraciclsta, List<Curva> curv) {

		curva = curv;
		lista_de_ciclistas = micic;
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setBounds(0, 0, VariablesDeContexto.ANCHO_LIENZO, VariablesDeContexto.ALTO_LIENZO);
	
		carretera = carreteradecarreraciclsta;
    }

    private int calculaYparaPuntoCiclista(Ciclista cic/*, Map<Integer, TramoCarrera> carreteradecarreraciclsta*/) {
		int yresu = 0;
		int dify = 0;
		int yacum = VariablesDeContexto.ALTO_VENTANA / 4;
		boolean encontrado = false;
		
		Iterator<Map.Entry<Integer,TramoCarrera>> it = carretera.entrySet().iterator();
		Iterator<Map.Entry<Integer,TramoCarrera>> itaux = carretera.entrySet().iterator();
		
		if (it.hasNext()) {
		    itaux.next();
		}
		while (itaux.hasNext() && !encontrado) {
	
		    Entry<Integer, TramoCarrera> tramoini = it.next();
	
		    if (it.hasNext()) {
	
			Entry<Integer, TramoCarrera> tramofin = itaux.next();
	
				if (cic.getBicicletamontada().getEspacioRecorrido() >= tramoini.getKey()
					&& cic.getBicicletamontada().getEspacioRecorrido() < tramofin.getKey()) {
				    encontrado = true;
		
				    int metro_en_el_tramo_del_ciclista = (int) (cic.getBicicletamontada()
					    .getEspacioRecorrido() - tramoini.getKey());
				    int diftramos = tramofin.getKey() - tramoini.getKey();
		
				    dify = (int)tramoini.getValue().getPendiente();
		
//				    int yfintramo = yacum + dify; // No la estaban usando :$
				    yresu = metro_en_el_tramo_del_ciclista * dify / diftramos;
				    yresu = yacum
					    - (metro_en_el_tramo_del_ciclista * dify / diftramos)
					    - VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA / 2;
		
				}
	
		    }
		    yacum = yacum - (int)tramoini.getValue().getPendiente();
	
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
		int xfin = 0;
		int y = VariablesDeContexto.ALTO_LIENZO / 2;
		double x_aux = 0;
		int i = 0;
		int max = (int) VariablesDeContexto.LONGITUD_CARRERA;
		
		Iterator<Map.Entry<Integer,TramoCarrera>> it = carretera.entrySet().iterator();
		Iterator<Map.Entry<Integer,TramoCarrera>> itaux = carretera.entrySet().iterator();
		
		if (it.hasNext()) {
		    itaux.next();
		}
		while (itaux.hasNext()) {
	
		    Entry<Integer, TramoCarrera> tramoini = it.next();
	
		    if (it.hasNext()) {
	
				Entry<Integer, TramoCarrera> tramofin = itaux.next();
		
				// pintamos la carretera
				g.setColor(Color.BLACK);
				
//				g.drawLine(x, y, (tramofin.getKey() / max)*Constantes.ANCHO_VariablesDeContexto, y	
//					- tramoini.getValue());
				g.drawLine((int)x_aux, y, (int)((tramofin.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO), (int) (y - tramoini.getValue().getPendiente()));
				
				System.out.println(x_aux);
				System.out.println((int)((tramofin.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO));
				
				// pintamos el cielo
				Polygon polygonCielo = creaPoligono(new Point((int)x_aux, 0),
					new Point((int)x_aux, y - 1),
					new Point((int)((tramofin.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO),
								(int)(y - tramoini.getValue().getPendiente()) - 1), new Point(
								(int)((tramofin.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO), 0));
				
//				Polygon polygon = new Polygon();
		
				pintaPoligono(polygonCielo, g, Color.CYAN);
		
				// pintamos el suelo
		
				Polygon polygonSuelo = creaPoligono(new Point((int)x_aux, VariablesDeContexto.ANCHO_LIENZO),
													new Point((int)x_aux, y + 1),
													new Point((int)((tramofin.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO),
															(int)(y - tramoini.getValue().getPendiente()) + 1), new Point(
															(int)((tramofin.getKey()/(double)max)*VariablesDeContexto.ANCHO_LIENZO), VariablesDeContexto.ALTO_VENTANA));
				pintaPoligono(polygonSuelo, g, Color.GREEN);
		
				x = tramofin.getKey() / VariablesDeContexto.FACTORESCALA;
				y = (int) (y - tramoini.getValue().getPendiente());
				x_aux= (tramofin.getKey()/(double)max)* VariablesDeContexto.ANCHO_LIENZO;
		    }

		}

		// pintamos los PK de las curvas
	
		Iterator<Curva> itcurva = curva.iterator();
		while (itcurva.hasNext()) {
			
		    Curva tramocurva = itcurva.next();
		    g.setColor(Color.black);
		    g.drawString("Curva en PK " + tramocurva.getPuntokilometrico().intValue(), tramocurva.getPuntokilometrico().intValue() - 60, 30);
		    g.drawString("con velocidad maxima " + tramocurva.getVelocidadmaximacurva(), tramocurva.getPuntokilometrico().intValue() - 80, 40);
	
		}
		
		// aqui se pondra la informacion del ciclista para que se vaya pintando,
		int id_color_ciclista = 0;
		
		for (Ciclista ciclista : lista_de_ciclistas) {
			
		    g.setColor(colores[id_color_ciclista]);
	
		    if (calculaYparaPuntoCiclista(ciclista) == 0)
		    	y = VariablesDeContexto.ALTO_VENTANA / 4 - VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA / 2;
		    else
		    	y = calculaYparaPuntoCiclista(ciclista);
		    
		    g.fillOval( (int) ((ciclista.getBicicletamontada().getEspacioRecorrido() / max) * VariablesDeContexto.ANCHO_LIENZO) - VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA / 2,
    					y,
    					VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA,
    					VariablesDeContexto.ANCHO_REPRESENTACION_CICLISTA);
	
		    id_color_ciclista++;
		}

    }

    private void pintaPoligono(Polygon p, Graphics g, Color col) {
		g.setColor(col);
		g.fillPolygon(p);
    }

}