package juego;


import java.awt.Color;
import java.awt.Image;


import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	// Variables y métodos propios de cada grupo
	private Entorno entorno;
	private Image fondo;
	private Boton botonAgua;
	private Boton botonFuego;
	private Roca piedra1;
	private Roca piedra2;
	private Roca piedra3;
	private Roca piedra4;
	private Roca piedra5;
	private Color miGris;
	private Color miAzul;
	private Color miRojo;
	

	private Murcielago murcielago;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El camino de Gondolf", 800, 600);
		// Inicializar lo que haga falta para el juego
		this.fondo = Herramientas.cargarImagen("fondo.jpg");
		this.entorno.dibujarImagen(fondo, 400, 300, 0);

		this.miGris = new Color (122, 135, 150 );
		this.miAzul = new Color(17, 97, 158);
		this.miRojo =  new Color(145, 29, 6);
		this.botonAgua = new Boton ( 725, 150, 80, 60, "Agua", miAzul);
		this.botonFuego = new Boton ( 725, 250, 80, 60,"Fuego",miRojo);
		this.piedra1 = new Roca ( 100,300,entorno);
		this.piedra2 = new Roca ( 550,150,entorno);
		this.piedra3 = new Roca ( 350,200,entorno);
		this.piedra4 = new Roca ( 250,450,entorno);
		this.piedra5 = new Roca ( 450,350,entorno);

		this.murcielago = new Murcielago(100, 100, entorno); // x, y, velocidad
		


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
		//Murcielago.mover();
		murcielago.dibujar(entorno);
		
		//botones
		botonAgua.dibujar(entorno);
		botonFuego.dibujar(entorno);	
		
		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)
				&& botonFuego.cursorSobreBoton(entorno)) {				
				botonFuego.setColor(miGris);
				//botonFuego.estaPresionado(entorno.BOTON_IZQUIERDO);
		}
		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)
				&& botonAgua.cursorSobreBoton(entorno)) {				
				botonAgua.setColor(miGris);
				//botonAgua.estaPresionado(entorno.BOTON_IZQUIERDO);
		}
		
		//Piedras		
		piedra1.dibujar(entorno);
		piedra2.dibujar(entorno);
		piedra3.dibujar(entorno);
		piedra4.dibujar(entorno);
		piedra5.dibujar(entorno);
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
