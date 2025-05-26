package juego;

//import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Roca {
	double x;
	double y;
	double ancho;
	double alto;
	double escala;
	Image piedra;
	Entorno e;
		
	public Roca(double x, double y, Entorno e) {
		
		this.x = x;
		this.y = y;
		this.ancho =10;
		this.alto =10;		
		this.piedra = Herramientas.cargarImagen("piedra.jpg");
		
	}

	public void dibujar(Entorno e, double escala){
		e.dibujarImagen(piedra, this.x, this.y, 0, escala);
	}

	
	//getters de los límites del objeto
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
