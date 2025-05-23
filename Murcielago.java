package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
	double x;
	double y;
	double velocidadX;
	Image murcielago;

	public Murcielago(double x, double y) {
		this.x = x;
		this.y = y;
		this.velocidadX = 2; // Velocidad inicial hacia la derecha
		this.murcielago = Herramientas.cargarImagen("murcielago.gif");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(murcielago, this.x, this.y, 0, 0.4);
	}

	public void mover(Entorno e) {
		this.x += velocidadX;
		// Límite izquierdo (pared izquierda)
		if (this.x <= 35|| this.x >= 610){
			velocidadX *= -1;
		}
	}
}
		