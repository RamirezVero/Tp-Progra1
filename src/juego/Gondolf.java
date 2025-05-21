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
	Entorno e;
	
	
	public Gondolf(int x, int y) {
		this.x = x;
		this.y = y;
		moverse = false;
		imgIzquierda = Herramientas.cargarImagen("izquierdaMG.jpg");
		imgDerecha = Herramientas.cargarImagen("derecha.jpg");
		imgFrente = Herramientas.cargarImagen("frente.jpg");
		imgEspalda = Herramientas.cargarImagen("espaldaMG.jpg");
	}

	
	public void dibujar(Entorno e){
		e.dibujarImagen(imgIzquierda, this.x, this.y, 0, 0.3);
	}

}
