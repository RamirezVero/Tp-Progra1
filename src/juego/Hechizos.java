package juego;

import entorno.Entorno;

import java.awt.Image;

import entorno.Herramientas;

public class Hechizos {

	Entorno e;
	double x;
	double y;
	double ancho;
	double alto;
	boolean activa;
	public double radio;
	Image imagen;
	boolean fueEliminado = false;
	Murcielago m;
	String tipo; // "Fuego" o "Agua"
	private int duracionFrames;

	public Hechizos(double x2, double y2, String tipo, Entorno e) {
		this.x = x2;
		this.y = y2;
		this.activa = true;
		this.tipo = tipo;
		this.e = e;
		this.duracionFrames = 15; //
		if (tipo.equals("Fuego")) {
			imagen = Herramientas.cargarImagen("hechizoFuego.gif");
			this.radio = 60;
		} else if (tipo.equals("Agua")) {
			imagen = Herramientas.cargarImagen("hechizoAgua.gif");
			this.radio = 35;
		}
		this.ancho = imagen.getWidth(null) * 0.25;
		this.alto = imagen.getHeight(null) * 0.25;
	}

	public void dibujar(Entorno entorno) {
		if (imagen != null) {
			entorno.dibujarImagen(imagen, x, y, 0, 0.4);
		}
		duracionFrames--;
		if (duracionFrames <= 0) {
			activa = false;
		}
	}

	public boolean hechizoTocaMurcielago(Murcielago m) {
		double dx = this.x - m.x;
		double dy = this.y - m.y;
		double distancia = Math.sqrt(dx * dx + dy * dy);
		return distancia < 40; 
	}
}