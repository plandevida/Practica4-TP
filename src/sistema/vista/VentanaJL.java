package sistema.vista;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import sistema.controladores.ordenes.Dispatcher;
import sistema.interfaces.ObjetosQueSeEjecutan;

/**
 * esta clase corresponde a la interfaz grafica de la aplicacion
 * 
 * @author juancly
 * 
 */
public class VentanaJL extends JFrame implements ObjetosQueSeEjecutan {

    private Dispatcher comandero;

    private static final long serialVersionUID = -8170475180669923771L;

    private JPanel contentPane;
    private JPanel contentPrincipal;
    private JPanel contentLienzo;
    private JTextField tFreloj;
    private JTextField tFcomando;
    private JTextArea tFconsola;
    private String cadena;

    private JLabel lblComandos;
    private JLabel lblTiempo;
    private JLabel lblconsola;
    private JScrollPane sbrText;
    private Lienzo lienzo;
    private Escuchador escuchador;
    private String textoAnteriorScroll;

    private JTextArea textAreas[];
    private JLabel lblCiclistas[];
    private JButton botonAumCad[];
    private JButton botonDisCad[];
    private JButton botonDisPin[];
    private JButton botonAumPin[];
    private JButton botonDisPla[];
    private JButton botonAumPla[];
    private JButton botonFrenaPoco[];
    private JButton botonFrenaMas[];
    private Color color[];
    private JProgressBar barras[];
    
    private List<JTextComponent> componenetescreados;

    public VentanaJL(Dispatcher nuevo_comandero, Lienzo lien) {
		textAreas = new JTextArea[Constantes.MAX_CICLISTAS];
		lblCiclistas = new JLabel[Constantes.MAX_CICLISTAS];
		botonDisCad = new JButton[Constantes.MAX_CICLISTAS];
		botonAumCad = new JButton[Constantes.MAX_CICLISTAS];
		botonDisPin = new JButton[Constantes.MAX_CICLISTAS];
		botonAumPin = new JButton[Constantes.MAX_CICLISTAS];
		botonDisPla = new JButton[Constantes.MAX_CICLISTAS];
		botonAumPla = new JButton[Constantes.MAX_CICLISTAS];
		botonFrenaPoco = new JButton[Constantes.MAX_CICLISTAS];
		botonFrenaMas = new JButton[Constantes.MAX_CICLISTAS];
		barras = new JProgressBar[Constantes.MAX_CICLISTAS];
		color = new Color[Constantes.MAX_CICLISTAS];
		textoAnteriorScroll = "";
		comandero = nuevo_comandero;
		lienzo = lien;
		escuchador = new Escuchador(comandero);
		
		componenetescreados = new ArrayList<JTextComponent>();
		
		init();
    }

    private void init() {
    	crearGUI();
    }

    /**
     * este metodo,crea y coloca los componentes de la interfaz grafica.
     */
    private void crearGUI() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA);
	
		contentPrincipal = crearJPanel(contentPrincipal, 0, 0, 0, 0);
		colocarJPanelEnJFrame(contentPrincipal);
	
		contentPane = crearJPanel(contentPrincipal, 0, 0,
			Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA / 2);
	
		colocarJPanelEnJPanel(contentPane, contentPrincipal);
	
		contentLienzo = crearJPanel(contentPrincipal, 0,
			contentPane.getHeight(), Constantes.ANCHO_VENTANA,
			Constantes.ALTO_VENTANA / 2);
		colocarJPanelEnJPanel(contentLienzo, contentPrincipal);
	
		contentLienzo.add(lienzo);
		color[0] = Constantes.COLOR_CIC_0;
		color[1] = Constantes.COLOR_CIC_1;
		color[2] = Constantes.COLOR_CIC_2;
		color[3] = Constantes.COLOR_CIC_3;
		color[4] = Constantes.COLOR_CIC_4;
		color[5] = Constantes.COLOR_CIC_5;
		int i = 0;
		int y = Constantes.Y_INICIAL;
		for (i = 0; i < Constantes.MAX_CICLISTAS; i++) {
		    if (i > 2) {
		    	y = lblCiclistas[i % 3].getY() + Constantes.ALTO_TEXTBOX
		    			+ Constantes.ALTO_BOTON;
		    }
		    
		    lblCiclistas[i] = null;
		    lblCiclistas[i] = crearJLabel(
			    lblCiclistas[i],
			    "Ciclista" + i,
			    Constantes.X_INICIAL
				    + i
				    % 3
				    * (Constantes.ANCHO_TEXTBOX + Constantes.ANCHO_BOTON * 2),
			    y, Constantes.ANCHO_BOTON, Constantes.ALTO_BOTON, color[i]);
		    colocarJLabelEnJPanel(lblCiclistas[i], contentPane);
		    
		    /*TEXT AREA DE CICLISTA*/
		    textAreas[i] = crearJTextArea(textAreas[i], lblCiclistas[i].getX(),
			    lblCiclistas[i].getHeight() + y, Constantes.ANCHO_TEXTBOX,
			    Constantes.ALTO_TEXTBOX-Constantes.ALTO_BOTON);
		    colocarJTextAreaEnJPanel(textAreas[i], contentPane);
		    
		    /*BARRA DE PROGESO*/
		    barras[i] = new JProgressBar(0,100);
		    barras[i].setBounds(textAreas[i].getX(),textAreas[i].getY()+Constantes.ALTO_TEXTBOX-Constantes.ALTO_BOTON
			    ,Constantes.ANCHO_TEXTBOX,Constantes.ALTO_BOTON);
		    barras[i].setValue(100);
		    barras[i].setString("100%");
		    barras[i].setStringPainted(true);
		    contentPane.add(barras[i]);
		    
		    /*BOTON DISMINUYE CADENCIA*/
		    botonDisCad[i] = null;
		    botonDisCad[i] = crearJButton(botonDisCad[i],
			    Constantes.CADENCIA_MENOS, textAreas[i].getWidth()
				    + textAreas[i].getX(), textAreas[i].getY(),
			    Constantes.ANCHO_BOTON, Constantes.ALTO_BOTON);
		    
		    escuchador.asignaMouseClicked(botonDisCad[i], "asignacadencia " + i
		     + " " + Constantes.BAJAR);
		    
		    /* Implementacion de prueba que funciona*/
		    escuchador.asignaMouseClicked(botonDisCad[i], "ciclista " + i
		    + " cadencia " + 1 + " periodo " + 1);
		    colocarJButtonEnJPanel(botonDisCad[i], contentPane);
	
		    /*BOTON AUMENTA CADENCIA*/
		    botonAumCad[i] = null;
		    botonAumCad[i] = crearJButton(botonAumCad[i],
			    Constantes.CADENCIA_MAS, textAreas[i].getWidth()
				    + textAreas[i].getX(), botonDisCad[i].getY()
				    + Constantes.ALTO_BOTON, Constantes.ANCHO_BOTON,
			    Constantes.ALTO_BOTON);
	
		    escuchador.asignaMouseClicked(botonAumCad[i], "asignacadencia " + i
		    + " " + Constantes.SUBIR);
		    
		    /* Implementacion de prueba que funciona*/
		    escuchador.asignaMouseClicked(botonAumCad[i], "ciclista " + i
			    + " cadencia " + 1 + " periodo " + 1);
		    colocarJButtonEnJPanel(botonAumCad[i], contentPane);
	
		    /*BOTON DISMINUYE PINON*/
		    botonDisPin[i] = null;
		    botonDisPin[i] = crearJButton(botonDisPin[i],
			    Constantes.PINON_MENOS, textAreas[i].getWidth()
				    + textAreas[i].getX(), botonAumCad[i].getY()
				    + Constantes.ALTO_BOTON, Constantes.ANCHO_BOTON,
			    Constantes.ALTO_BOTON);
		    
		    escuchador.asignaMouseClicked(botonDisPin[i], "bicicleta " + i + " cambia pinon " + Constantes.BAJAR );
	
		    colocarJButtonEnJPanel(botonDisPin[i], contentPane);
		    
		    /*BOTON AUMENTA PINON*/
		    botonAumPin[i] = null;
		    botonAumPin[i] = crearJButton(botonAumPin[i], Constantes.PINON_MAS,
			    textAreas[i].getWidth() + textAreas[i].getX(),
			    botonDisPin[i].getY() + Constantes.ALTO_BOTON,
			    Constantes.ANCHO_BOTON, Constantes.ALTO_BOTON);
		    escuchador.asignaMouseClicked(botonAumPin[i], "bicicleta " + i + " cambia pinon " + Constantes.SUBIR );
		    colocarJButtonEnJPanel(botonAumPin[i], contentPane);
	
		    /*BOTON DISMINUYE PLATO*/
		    botonDisPla[i] = null;
		    botonDisPla[i] = crearJButton(botonDisPla[i],
			    Constantes.PLATO_MENOS, botonDisCad[i].getWidth()
				    + botonDisCad[i].getX(), textAreas[i].getY(),
			    Constantes.ANCHO_BOTON, Constantes.ALTO_BOTON);
		    escuchador.asignaMouseClicked(botonDisPla[i], "bicicleta " + i + " cambia plato " + Constantes.BAJAR );
	
		    colocarJButtonEnJPanel(botonDisPla[i], contentPane);
	
		    
		    /*BOTON AUMENTA PLATO*/
		    botonAumPla[i] = null;
		    botonAumPla[i] = crearJButton(botonAumPla[i], Constantes.PLATO_MAS,
			    botonAumCad[i].getWidth() + botonAumCad[i].getX(),
			    botonAumCad[i].getY(), Constantes.ANCHO_BOTON,
			    Constantes.ALTO_BOTON);
		    escuchador.asignaMouseClicked(botonAumPla[i], "bicicleta " + i + " cambia plato " + Constantes.SUBIR );
	
		    colocarJButtonEnJPanel(botonAumPla[i], contentPane);
	
		    /*BOTON FRENA POCO*/
		    botonFrenaPoco[i] = null;
		    botonFrenaPoco[i] = crearJButton(botonFrenaPoco[i],
			    Constantes.FRENO_MENOS, botonDisPin[i].getWidth()
				    + botonDisPin[i].getX(), botonDisPin[i].getY(),
			    Constantes.ANCHO_BOTON, Constantes.ALTO_BOTON);
		    
		    escuchador.asignaMouseClicked(botonFrenaPoco[i], "frenar " + i
		    	    + " 1");
		    
		    /* Implementacion de prueba que funciona*/
		    escuchador.asignaMouseClicked(botonFrenaPoco[i], "ciclista " + i + " frena " + 0.1);
	
		    colocarJButtonEnJPanel(botonFrenaPoco[i], contentPane);
	
		    /*BOTON FRENA MAS*/
		    botonFrenaMas[i] = null;
		    botonFrenaMas[i] = crearJButton(botonFrenaMas[i],
			    Constantes.FRENO_MAS, botonAumPin[i].getWidth()
				    + botonAumPin[i].getX(), botonAumPin[i].getY(),
			    Constantes.ANCHO_BOTON, Constantes.ALTO_BOTON);
		    
		    escuchador.asignaMouseClicked(botonFrenaMas[i], "frenar " + i
		    	    + " 1");
		    
		    /* Implementacion de prueba que funciona*/
		    escuchador.asignaMouseClicked(botonFrenaMas[i], "ciclista " + i + " frena " + 1);
		    
		    colocarJButtonEnJPanel(botonFrenaMas[i], contentPane);
		}
	
		lblTiempo = crearJLabel(lblTiempo, "Tiempo", textAreas[3].getX(),
			textAreas[3].getY() + textAreas[3].getHeight()+Constantes.ALTO_BOTON, 70, 15,
			Color.black);
	
		colocarJLabelEnJPanel(lblTiempo, contentPane);
	
		tFreloj = crearJTextField(tFreloj, lblTiempo.getX(), lblTiempo.getY()
			+ lblTiempo.getHeight(), 114, 19);
		tFreloj.setEditable(false);
	
		colocarJTextFieldEnJPanel(tFreloj, contentPane);
	
		cadena = "";
	
		lblComandos = crearJLabel(lblComandos, "Comandos", textAreas[4].getX(),
			textAreas[4].getY() + textAreas[4].getHeight()+Constantes.ALTO_BOTON, 114, 15,
			Color.black);
	
		colocarJLabelEnJPanel(lblComandos, contentPane);
		tFcomando = crearJTextField(tFcomando, lblComandos.getX(),
			lblComandos.getY() + lblComandos.getHeight(),
			Constantes.ANCHO_TEXTBOX, Constantes.ALTO_TEXTBOX);
		escuchador.asignaKeyPressed(tFcomando, tFcomando.getText());
	
		colocarJTextFieldEnJPanel(tFcomando, contentPane);
	
		JLabel lblConsola = crearJLabel(lblComandos, "Consola",
			textAreas[5].getX(),
			textAreas[4].getY() + textAreas[4].getHeight()+Constantes.ALTO_BOTON, 114, 15,
			Color.black);
	
		colocarJLabelEnJPanel(lblConsola, contentPane);
	
		tFconsola = crearJTextArea(tFconsola, lblConsola.getX(),
			lblConsola.getY() + lblConsola.getHeight(),
			Constantes.ANCHO_TEXTBOX, Constantes.ALTO_TEXTBOX);
	
		tFconsola.setLineWrap(true);
		sbrText = crearJScrollPane(sbrText, tFconsola, lblConsola.getX(),
			lblConsola.getY() + lblConsola.getHeight(),
			Constantes.ANCHO_TEXTBOX, Constantes.ALTO_TEXTBOX);
	
		colocarJScrollPaneEnJPanel(sbrText, contentPane);
	
		this.setVisible(true);

    }
    
    /**
	 * Añade nueva información en un área de texto o crea una nueva
	 * si fuese necesario.
	 * 
	 * @param id Objeto que mostrará sus datos.
	 * @param mensaje Los datos formateados a poner en el área.
	 */
	public void ponerDatosEnVentana(String id, Object... mensaje) {
		JTextComponent componenteexistente = null;
		
		switch(id) {
		/*case "ruloj":
			
			int hora = (Integer) mensaje[0];
			int minuto = (Integer) mensaje[1];
			int segundo = (Integer) mensaje[2];
			
			relojGrafico.setTime(segundo, minuto, hora);
			
			break;*/
		case "#ayudaMain":
			
			tFconsola.setText(tFconsola.getText()
					+ "CiclistaManager <número_ciclistas> <fichero_comandos> <unidad_tiempo> "
					+ "<número_platos> <dientes_plato (separados por espacios)> <número_piñones>"
					+ " <dientes_piñones (separados por espacios)> <radio_rueda>");
			break;
		default:
			for (JTextComponent componente : componenetescreados) {
				if (componente.getName().equals(id)) {
					componente.setText( (String) mensaje[0]);
					
					componenteexistente = componente;
				}
			}
			
			if (componenteexistente == null) {
//				anadirTextArea(id);
//				
//				ponerDatosEnVentana(id, mensaje);
			}
			break;
		}
	}

    /**
     * este metodo , asigna un texto al textArea de ciclista que se quiera
     * 
     * @param id
     * @param mensaje
     */
    public void ponTextoEnTextArea(String id, String mensaje) {
	
		switch (id) {
		case "ciclista0":
		    if(mensaje.contains("Fuerza"))
		    obtenFuerzadelMensaje(mensaje,0);
		    else 
			textAreas[0].setText(textAreas[0].getText() + "\n" + mensaje);
		    break;
		case "ciclista1":
		    if(mensaje.contains("Fuerza"))
			    obtenFuerzadelMensaje(mensaje,1);
		    else 
			textAreas[1].setText(textAreas[1].getText() + "\n" + mensaje);
		    break;
		case "ciclista2":
		    if(mensaje.contains("Fuerza"))
			    obtenFuerzadelMensaje(mensaje,2);
		    else 
			textAreas[2].setText(textAreas[2].getText() + "\n" + mensaje);
		    break;
		case "ciclista3":
		    if(mensaje.contains("Fuerza"))
			    obtenFuerzadelMensaje(mensaje,3);
		    else 
			textAreas[3].setText(textAreas[3].getText() + "\n" + mensaje);
		    break;
		case "ciclista4":
		    if(mensaje.contains("Fuerza"))
			    obtenFuerzadelMensaje(mensaje,4);
		    else 
			textAreas[4].setText(textAreas[4].getText() + "\n" + mensaje);
		    break;
		case "ciclista5":
		    if(mensaje.contains("Fuerza"))
			    obtenFuerzadelMensaje(mensaje,5);
		    else 
			textAreas[5].setText(textAreas[5].getText() + "\n" + mensaje);
		    break;
	
		case "reloj":
		    tFreloj.setText(mensaje);
		    break;
		case "consola":
		    String mens = tFconsola.getText().toString() + mensaje;
	
		    if (!mensaje.equalsIgnoreCase("")) {
			tFconsola.setText(mens + "\n");
	
		    }
		    break;
	
		}
    }

    /**
     * metodo heredado de la interfazEjecuta, el cual se ejecuta, cada vuelta
     * del bucle principal
     */
    @Override
    public void ejecuta() {

		textAreas[0].setText("");
	
		textAreas[1].setText("");
	
		textAreas[2].setText("");
		textAreas[3].setText("");
		textAreas[4].setText("");
	
		textAreas[5].setText("");
		// tFconsola.setText("");
		tFreloj.setText("");
		// comprobamos que texto que se escribio antes, no corresponde con el
		// texto
		// actual, para poder mover el scrollBar y que no se mueva a no ser que
		// se
		// introduzca un nuevo comando
	
		// A veces da este error
		// at javax.swing.BufferStrategyPaintManager.flushAccumulatedRegion
	
		if (!textoAnteriorScroll.equalsIgnoreCase(tFconsola.getText())) {
		    sbrText.getVerticalScrollBar().setValue(
			    sbrText.getVerticalScrollBar().getMaximum());
		}
		
		textoAnteriorScroll = tFconsola.getText();
    }
	
    private void colocarJButtonEnJPanel(JButton bt, JPanel destino) {
    	destino.add(bt);
    }

    private void colocarJScrollPaneEnJPanel(JScrollPane sc, JPanel destino) {
    	destino.add(sc);
    }

    private void colocarJPanelEnJFrame(JPanel panel) {
    	setContentPane(panel);
    }

    private void colocarJPanelEnJPanel(JPanel origen, JPanel destino) {
    	destino.add(origen);
    }

    private void colocarJTextAreaEnJPanel(JTextArea origen, JPanel destino) {
    	destino.add(origen);
    }

    private void colocarJTextFieldEnJPanel(JTextField origen, JPanel destino) {
    	destino.add(origen);
    }

    private void colocarJLabelEnJPanel(JLabel origen, JPanel destino) {
    	destino.add(origen);
    }

    private JPanel crearJPanel(JPanel panel, int x, int y, int ancho, int alto) {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(x, y, ancho, alto);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));

	return panel;
    }

    private JLabel crearJLabel(JLabel label, String nombre, int x, int y,
	    int ancho, int alto, Color color) {
		label = new JLabel(nombre);
		label.setBounds(x, y, ancho, alto);
		label.setForeground(color);
		return label;
    }

    private JTextArea crearJTextArea(JTextArea text, int x, int y, int ancho,
	    int alto) {
		text = new JTextArea();
		text.setBounds(x, y, ancho, alto);
		return text;
    }

    private JTextField crearJTextField(JTextField text, int x, int y,
	    int ancho, int alto) {
		text = new JTextField();
		text.setBounds(x, y, ancho, alto);
		return text;
    }

    private JButton crearJButton(JButton bt, String texto, int x, int y,
	    int ancho, int alto) {
		bt = new JButton();
		bt.setBounds(x, y, ancho, alto);
	
		bt.setText(texto);
		bt.setVisible(true);
		bt.setBounds(x, y, ancho, alto);
		return bt;
    }

    private JScrollPane crearJScrollPane(JScrollPane scroll, JTextArea tf, int x, int y, int ancho, int alto) {
    	
		scroll = new JScrollPane(tf);
		scroll.setBounds(x, y, ancho, alto);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return scroll;
    }
    
    private String obtenFuerzadelMensaje(String mensaje, int id) {
		if(mensaje.contains("Fuerza:")){
		    String fuerza = mensaje.substring(mensaje.indexOf(":")+1);
		    barras[id].setValue((int)(Double.parseDouble(fuerza)));
		    barras[id].setString(fuerza.substring(0,5)+"%");
		}
	
		
		return "";
	
    }
}
