package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Gondolf {
	double x;
	double y;
	double angulo;
	Image imgIzquierda;
	Image imgDerecha;
	Image imgFrente;
	Image imgEspalda;
	boolean moverse;
	
	
	public Gondolf(int x, int y) {
		this.x = x;
		this.y = y;
		moverse = false;
		imgIzquierda = Herramientas.cargarImagen("izquierdaMG.png");
		imgDerecha = Herramientas.cargarImagen("derecha.png");
		imgFrente = Herramientas.cargarImagen("frente.png");
		imgEspalda = Herramientas.cargarImagen("espaldaMG.png");
	}
	

}
