package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
	Entorno e;
	double x;
	double y;
	double angulo;
	double velocidadX;
	Image murcielago;
	
    public Murcielago(double x, double y, Entorno e) { 
		this.x = x;
		this.y = y;
		this.angulo = 0;
		this.velocidadX = 5; // Velocidad inicial hacia la derecha
		this.murcielago = Herramientas.cargarImagen("murcielago.gif");
	}
	
/*	public void dibujar(Entorno e) {
		e.dibujarImagen(murcielago, this.x, this.y, 0, 0.4);
	}
	public void mover(Entorno e) {
		this.x += velocidadX;
		// Límite izquierdo (pared izquierda)
		if (this.x <= 35|| this.x >= 610){
			velocidadX *= -1;
		}

} */
	
	public void dibujarse(Entorno entorno)
	{
		//entorno.dibujarCirculo(this.x, this.y, 20,Color.RED);
		entorno.dibujarImagen(murcielago, x, y, angulo, 0.2);
	}
	
	public void cambiarAngulo(double x2, double y2){
		this.angulo = Math.atan2(y2 - this.y, x2 - this.x);
	}
	
	public void mover() {
		this.x += Math.cos(this.angulo)*1;
		this.y += Math.sin(this.angulo)*1;
	}

}