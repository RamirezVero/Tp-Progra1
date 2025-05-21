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
	private char direccionActual; // 'a', 'd', 'w', 's'
	

	Entorno e;
	double velocidad;
	double escala;
	
	public Gondolf(int x, int y) {
		this.x = x;
		this.y = y;		
		this.velocidad= 3.0;
		this.escala =  0.1;
		imgIzquierda = Herramientas.cargarImagen("izquierdaMG.jpg");
		imgDerecha = Herramientas.cargarImagen("derecha.jpg");
		imgFrente = Herramientas.cargarImagen("frente.jpg");
		imgEspalda = Herramientas.cargarImagen("espaldaMG.jpg");
		direccionActual = 'w';
	}

	
	public void dibujar(Entorno e){
		if(this.direccionActual == 'd') {
			e.dibujarImagen(imgDerecha, x, y, 0,escala);
		}		
		if(this.direccionActual == 'a'){
			e.dibujarImagen(imgIzquierda, x, y, 0,escala);
		}
		
		if(this.direccionActual == 'w') {
			e.dibujarImagen(imgEspalda, x, y, 0,escala);
		}
		if(this.direccionActual == 's') {
			e.dibujarImagen(imgFrente, x, y, 0,escala);
		}	
	}
	
		public void moverDerecha () {
			if (this.x + this.velocidad < 640) {
				this.x += this.velocidad;
				direccionActual = 'd';
			}
		}
		public void moverIzquieda () {
			if (this.x - this.velocidad > 0) {
				this.x -= this.velocidad;
				this.direccionActual = 'a';
			}
		}
		public void moverArriba() {
			if (this.y - this.velocidad > 0) {
				this.y -= this.velocidad;
				direccionActual = 'w';
			}
		}
		
		public void moverAbajo() {
			if (this.y + this.velocidad <800) {
				this.y += this.velocidad;				
				direccionActual = 's';
			}
		}

}
