package juego;

//import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Roca {
	double x;
	double y;
	//double diametro;
	Image piedra;
	Entorno e;
		
	public Roca(double x, double y, Entorno e) {
		
		this.x = x;
		this.y = y;
		this.piedra = Herramientas.cargarImagen("piedra.jpg");
		
	}

	public void dibujar(Entorno e){
		e.dibujarImagen(piedra, this.x, this.y, 0, 0.3);
	}
	
	
	
}
