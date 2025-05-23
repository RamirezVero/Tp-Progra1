package juego;

import entorno.Entorno;
import entorno.Herramientas;


import java.awt.Color;
import java.awt.Image;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	// Variables y métodos propios de cada grupo
	private Entorno entorno;
	private Image fondo;
	Gondolf gondolf;
//	private Murcielago Murcielago;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El camino de Gondolf", 800, 600);
		// Inicializar lo que haga falta para el juego
		this.fondo = Herramientas.cargarImagen("suelo.jpg");
		this.entorno.dibujarImagen(fondo, 400, 300, 0);
//		this.Murcielago = new Murcielago(100, 100, 5); // x, y, velocidad
	
		gondolf = new  Gondolf(100, 100);
		this.entorno.dibujarImagen(Gondolf.class, this.gondolf.imgDerecha, this.entorno.0, 0);;

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		this.entorno.dibujarImagen(fondo, 400, 300, 0);
//		Murcielago.mover();
//		Murcielago.dibujar(entorno);
		
		
		this.entorno.dibujarImagen(gondolf.	imgDerecha, 400, 300,0, 0.5);
	//	this.entorno.dibujarRectangulo(500, 400, 100, 50, 0.0, Color.CYAN);
		

	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
	
}
