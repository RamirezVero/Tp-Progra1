package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.util.Random;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	// Variables y métodos propios de cada grupo
	private Entorno entorno;
	private Image fondo;
	private Image gameOver;
	private Image ganador;

	private Boton botonAgua;
	private Boton botonFuego;
	private Roca[] rocas;
	private Color miGris;
	private Color miAzul;
	private Color miRojo;				
	private Murcielago[] murcielagos;
	private int cantMurcielagos = 10;
	private boolean perdiste = false;
	Gondolf gondolf;
	Random random = new Random();
	
	
	private String hechizoSeleccionado = "";

	private int contEnemigosEliminados = 0;

	int menuX = 648;
	int menuAncho = 610;
	int menuAlto = 600;
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El camino de Gondolf", 800, 600);
		int altoPantalla = entorno.alto();    // 600
		// Inicializar lo que haga falta para el juego
		this.fondo = Herramientas.cargarImagen("fondo.jpg");
		this.gameOver = Herramientas.cargarImagen("perdiste.jpg");
		this.ganador = Herramientas.cargarImagen("ganaste.jpg");
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
		this.rocas[4] = new Roca ( 500,350,0.7, entorno);


		murcielagos = new Murcielago[cantMurcielagos];
		this.gondolf = new Gondolf(400, 300);

		for (int i = 0; i < cantMurcielagos; i++) {
        int xRandom = random.nextInt(menuX);  // sólo entre 0 y 647 para no entrar al menú
        int yRandom = random.nextInt(altoPantalla);  // entre 0 y 599, todo el alto
        murcielagos[i] = new Murcielago(xRandom, yRandom, entorno);
}


		// Inicia el juego!
		this.entorno.iniciar();
		
	}
	private Murcielago generarMurcielagoAleatorioFueraDelMenu() {
	    int xRandom = random.nextInt(menuX); // Evita el menú
	    int yRandom = random.nextInt(entorno.alto());
	    return new Murcielago(xRandom, yRandom, entorno);
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
		//hud.dibujar(entorno);
	   //actualizaciones)
		//hud.actualizarEstado(vidaActual, vidaMaxima, manaActual, manaMaxima, contEnemigosEliminados);
		//hud.setVisibleSegúnEstadoDelJugador(gondolf.estaVivo());
		
		
		//Piedras
		for (int i = 0; i < rocas.length; i++) {
		    rocas[i].dibujar(entorno);
		}	

		//Murcielago.mover();
		for (int i = 0; i < murcielagos.length; i++) {
		    Murcielago m = murcielagos[i];
		    
		    if (m != null) {
		        m.mover();
		        m.cambiarAngulo(gondolf.x, gondolf.y);
		        m.dibujarse(entorno);

		        // Colisión con Gondolf
		        if (gondolf.colisionaCon(m)) {
		            gondolf.pierdeVida();
		            contEnemigosEliminados ++;
		            System.out.println("Gondolf tiene " + gondolf.getVidaActual() + " vidas");

		            // Murciélago desaparece y se crea uno nuevo en una posición válida
		            murcielagos[i] = generarMurcielagoAleatorioFueraDelMenu();
		        }
		    }
		}		
		for (Murcielago m : murcielagos) {
		    if (gondolf.colisionaCon(m)) {
		        gondolf.pierdeVida();
		        contEnemigosEliminados ++;
		    }
		}
		
		if (gondolf.getVidaActual() <= 0) {
		    perdiste = true;
		}
		if (perdiste) {
			this.entorno.dibujarImagen(gameOver, 400, 300, 0);
		    /* Pantalla negra semitransparente
		    entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, entorno.ancho(), entorno.alto(), 0, miGris);
		    
		    entorno.cambiarFont("Arial", 50, Color.RED, entorno.NEGRITA);
		    entorno.escribirTexto("PERDISTE", entorno.ancho()/2 - 100, entorno.alto()/2);*/
		    
		    return; 
		}
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
		int barraYEliminados = 430;
		int anchoMaximo = 100;
		int alto = 20;
	
		
		
		// Cálculo de proporciones usando multiplicación con 1.0
		//double proporcionVida = 1.0 * vidaActual / vidaMaxima;
		double proporcionMana = 1.0 * gondolf.getManaActual() / gondolf.getManaMaxima();
		// Barra de vida
		double proporcionVida = 1.0 * gondolf.getVidaActual() / gondolf.getVidaMaxima();

		entorno.dibujarRectangulo(barraX, barraYVida, anchoMaximo, alto, 0, Color.WHITE); // fondo
		entorno.dibujarRectangulo(barraX, barraYVida, (int)(anchoMaximo * proporcionVida), alto, 0, Color.RED); // relleno
		entorno.cambiarFont("Courier New", 14, Color.WHITE, entorno.NEGRITA);
		entorno.escribirTexto( gondolf.getVidaActual() + "/" + gondolf.getVidaMaxima(), barraX - 20, barraYVida + 5);
		// Barra de maná
		entorno.dibujarRectangulo(barraX, barraYMana, anchoMaximo, alto, 0, Color.WHITE); // fondo
		entorno.dibujarRectangulo(barraX, barraYMana, anchoMaximo * proporcionMana, alto, 0, Color.BLUE); // relleno
		entorno.cambiarFont("Courier New", 14, Color.WHITE, entorno.NEGRITA);
		entorno.escribirTexto(gondolf.getManaActual() + "/" + gondolf.getManaMaxima(), barraX - 20, barraYMana + 5);
		
		String eliminados=String.format("Eliminados:"+ contEnemigosEliminados);
		entorno.dibujarRectangulo(barraX,barraYEliminados, anchoMaximo, alto, 0, Color.BLACK); // fondo
        this.entorno.cambiarFont("Courier New", 12, Color.WHITE, entorno.NEGRITA);
        this.entorno.escribirTexto(eliminados,680,433);
		
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
