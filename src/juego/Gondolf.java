package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Gondolf {
/*<<<<<<< HEAD
=======*/
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
//>>>>>>> 76687aa0c61c1c6161e7fc930587653e81b32215
	

}
