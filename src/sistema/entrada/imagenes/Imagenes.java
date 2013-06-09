package sistema.entrada.imagenes;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Imagenes {

	/**
	 * Última imagen cargada.
	 */
	private static ImageIcon cachedIcon;

	public static ImageIcon getMuerto() {
		cachedIcon = new ImageIcon(Imagenes.class.getResource("muerto.png"));

		return getCachedIcon();
	}

	public static ImageIcon getMuerto20x20() {
		cachedIcon = new ImageIcon(getMuerto().getImage().getScaledInstance(20, 20, Image.SCALE_FAST));

		return getCachedIcon();
	}

	/**
	 * Obtiene la última imagen cargada.
	 * 
	 * @return
	 */
	public static ImageIcon getCachedIcon() {
		return cachedIcon;
	}
}
