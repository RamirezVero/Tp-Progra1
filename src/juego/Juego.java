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
	
	private Roca[] rocas;
	private Color miGris;
	private Color miAzul;
	private Color miRojo;	
	private Murcielago murcielago;
	Gondolf gondolf;
	
	private String hechizoSeleccionado = "";
	private int vidaActual = 100;
	private int vidaMaxima = 100;
	private int manaActual = 100;
	private int manaMaximo = 100;
	int menuX = 648;
	int menuAncho = 610;
	int menuAlto = 600;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El camino de Gondolf", 800, 600);
		// Inicializar lo que haga falta para el juego
		this.fondo = Herramientas.cargarImagen("fondo.jpg");
		this.entorno.dibujarImagen(fondo, 400, 100, 0);
	
		this.miGris = new Color (122, 135, 150 );
		this.miAzul = new Color(17, 97, 158);
		this.miRojo =  new Color(145, 29, 6);
		this.botonAgua = new Boton ( 725, 150, 80, 60, "Agua", miAzul);
		this.botonFuego = new Boton ( 725, 250, 80, 60,"Fuego",miRojo);
		this.rocas = new Roca[5];
		this.rocas[0] = new Roca ( 100,300, 0.3, entorno);
		this.rocas[1] = new Roca ( 550,150, 0.4, entorno);
		this.rocas[2] = new Roca ( 350,200,0.5, entorno);
		this.rocas[3] = new Roca ( 250,450,0.6, entorno);
		this.rocas[4] = new Roca ( 450,350,0.7, entorno);

		this.murcielago = new Murcielago(100, 100, entorno); // x, y, velocidad
		this.gondolf = new Gondolf(400, 300);
		
		



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
		
		//botones
		botonAgua.dibujar(entorno);
		botonFuego.dibujar(entorno);
		
		//Piedras
		for (int i = 0; i < rocas.length; i++) {
		    rocas[i].dibujar(entorno);
		}	

		//Murcielago.mover();
		murcielago.dibujarse(entorno);
		murcielago.mover();
		murcielago.cambiarAngulo(gondolf.x, gondolf.y);
		gondolf.dibujar(entorno);
		
		// Movimiento a la izquierda
		if (entorno.estaPresionada('a')) {
		    boolean hayColision = false;
		   
		    for (Roca roca : rocas) {		    	 
		        if (gondolf.colisionariaCon(roca, -gondolf.velocidad, 0)) {
		            hayColision = true;
		            
		        }
		    }
		    if (!hayColision) {
		        gondolf.moverIzquierda();
		    }
		}
		// Movimiento a la derecha
		if (entorno.estaPresionada('d')) {
		    boolean hayColision = false;
		    for (Roca roca : rocas) {
		        if (gondolf.colisionariaCon(roca, gondolf.velocidad, 0)) {
		            hayColision = true;
		            
		        }
		    }
		    if (!hayColision) {
		        gondolf.moverDerecha();
		    }
		}
		// Movimiento hacia arriba
		if (entorno.estaPresionada('w')) {
		    boolean hayColision = false;
		    for (Roca roca : rocas) {
		        if (gondolf.colisionariaCon(roca, 0, -gondolf.velocidad)) {
		            hayColision = true;
		            
		        }
		    }
		    if (!hayColision) {
		        gondolf.moverArriba();
		    }
		}
		// Movimiento hacia abajo
		if (entorno.estaPresionada('s')) {
		    boolean hayColision = false;
		    for (Roca roca : rocas) {
		        if (gondolf.colisionariaCon(roca, 0, gondolf.velocidad)) {
		            hayColision = true;
		            
		        }
		    }
		    if (!hayColision) {
		        gondolf.moverAbajo();
		    }
		}

		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
		    double mouseX = entorno.mouseX();
		    double mouseY = entorno.mouseY();
		    
	    	boolean clickFueraMenu = !(mouseX >= menuX && mouseX <= menuX + menuAncho
	                              && mouseY >= 0 && mouseY <= menuAlto);

	    	if (clickFueraMenu) {
		        botonAgua.setColor(miAzul);
		        botonFuego.setColor(miRojo);
		        hechizoSeleccionado = ""; 
	    	}
		}
		
		// Coordenadas de la barra
		int barraX = 725;
		int barraYVida = 350;
		int barraYMana = 390;
		int anchoMaximo = 80;
		int alto = 15;
	
		// Vidas en Barra Roja - Maná en la azul
		int vidaActual = 100;
		int vidaMaxima = 100;
		int manaActual = 100;
		int manaMaxima = 100;
		
		// Cálculo de proporciones usando multiplicación con 1.0
		double proporcionVida = 1.0 * vidaActual / vidaMaxima;
		double proporcionMana = 1.0 * manaActual / manaMaxima;
		// Barra de vida
		entorno.dibujarRectangulo(barraX, barraYVida, anchoMaximo, alto, 0, Color.WHITE); // fondo
		entorno.dibujarRectangulo(barraX, barraYVida, anchoMaximo * proporcionVida, alto, 0, Color.RED); // relleno
		entorno.cambiarFont("Courier New", 14, Color.WHITE, entorno.NEGRITA);
		entorno.escribirTexto(vidaActual + "/" + vidaMaxima, barraX - 20, barraYVida + 5);

		// Barra de maná
		entorno.dibujarRectangulo(barraX, barraYMana, anchoMaximo, alto, 0, Color.WHITE); // fondo
		entorno.dibujarRectangulo(barraX, barraYMana, anchoMaximo * proporcionMana, alto, 0, Color.BLUE); // relleno
		entorno.cambiarFont("Courier New", 14, Color.WHITE, entorno.NEGRITA);
		entorno.escribirTexto(manaActual + "/" + manaMaxima, barraX - 20, barraYMana + 5);
		
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
		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
		    if (botonFuego.cursorSobreBoton(entorno)) {
		        if (!hechizoSeleccionado.equals("Fuego")) {
		            // Restaurar el color del otro botón
		            botonAgua.setColor(miAzul);
		            // Cambiar el color del botón actual
		            botonFuego.setColor(miGris);
		            // Guardar selección
		            hechizoSeleccionado = "Fuego";
		        }
		    }

		    if (botonAgua.cursorSobreBoton(entorno)) {
		        if (!hechizoSeleccionado.equals("Agua")) {
		            // Restaurar el color del otro botón
		            botonFuego.setColor(miRojo);
		            // Cambiar el color del botón actual
		            botonAgua.setColor(miGris);
		            // Guardar selección
		            hechizoSeleccionado = "Agua";
		        }
		    }
		}
		
		
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
	
}
