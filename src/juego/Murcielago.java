package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
	Entorno e;
	double x;
	double y;
	Image murcielago;
	
	public Murcielago(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.murcielago = Herramientas.cargarImagen("murcielago.gif");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(murcielago, this.x, this.y, 0, 0.4);
	}


	

}
