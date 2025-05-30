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
	double velocidad;
	boolean activa;
	Image imagen;
	boolean fueEliminado = false;
	String tipo; // "Fuego" o "Agua"

	public Hechizos(int x, int y, String tipo, Entorno e) {
		this.x = x;
		this.y = y;
		this.activa = true;
		this.tipo = tipo;
		this.e = e;
		if (tipo.equals("Fuego")) {
			imagen = Herramientas.cargarImagen("hechizoFuego.gif");
			this.velocidad = 5; // veloc para hechizo de fuego
		} else if (tipo.equals("Agua")) {
			imagen = Herramientas.cargarImagen("hechizoAgua.gif");
			this.velocidad = 3;
		}
		this.ancho = imagen.getWidth(null) * 0.25;
		this.alto = imagen.getHeight(null) * 0.25;
	}

	public void mover() {
		x += velocidad;
// Condiciones para desactivar el hechizo si sale de los límites
		if (x <= 0 || x >= 800) {
			activa = false;
		}
	}

	public void dibujar() {
		if (activa) {
		e.dibujarImagen(imagen, x, y, 0, 0.25); // Dibuja el hechizo en la pantalla
		}
	}
}