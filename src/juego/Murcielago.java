package juego;


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
	double ancho;
	double alto;
	double escala;
	
    public Murcielago(double x, double y, Entorno e) { 
		this.x = x;
		this.y = y;
		this.angulo = 0;
		this.velocidadX = 5; // Velocidad inicial hacia la derecha
		this.escala = 0.2;
		this.murcielago = Herramientas.cargarImagen("murcielago.gif");
		this.ancho = murcielago.getWidth(null)* this.escala ;
	    this.alto = murcielago.getHeight(null)* this.escala;
	    
	}

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
	
	public double getBordeDer() {
    	return this.x +(this.ancho/2);
    }    
    public double getBordeIzq() {
    	return this.x -(this.ancho/2);
    }
    public double getBordeSup() {
    	return this.y -(this.alto/2);
    }
    public double getBordeInf() {
    	return this.y +(this.alto/2);
    }


}