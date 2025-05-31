package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.multi.MultiPopupMenuUI;

public class Juego extends InterfaceJuego {
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
	private String mensajeManá = "";
	private int timerMensaje = 0;

	// private int murcielagosGenerados = 0;
	private final int totalMurcielagos = 50;

	private boolean perdiste = false;
	Gondolf gondolf;
	Random random = new Random();
	Hechizos hFuego;
	Hechizos hAgua;

	private String hechizoSeleccionado = "";
	ArrayList<Hechizos> listaHechizos = new ArrayList<>();
	private PocionMana[] pociones = new PocionMana[50];
	private int contEnemigosEliminados = 0;

	int menuX = 648;
	int menuAncho = 610;
	int menuAlto = 600;
	private Image areaFuego;
	private Image areaAgua;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El camino de Gondolf", 800, 600);
		int altoPantalla = entorno.alto(); // 600
		// Inicializar lo que haga falta para el juego
		this.fondo = Herramientas.cargarImagen("fondo.jpg");
		this.gameOver = Herramientas.cargarImagen("perdiste-salir.jpg");
		this.ganador = Herramientas.cargarImagen("ganaste.jpg");
		this.entorno.dibujarImagen(fondo, 400, 100, 0);

		this.miGris = new Color(122, 135, 150);
		this.miAzul = new Color(17, 97, 158);
		this.miRojo = new Color(145, 29, 6);
		this.botonAgua = new Boton(725, 150, 80, 60, "Agua", miAzul);
		this.botonFuego = new Boton(725, 250, 80, 60, "Fuego", miRojo);
		this.rocas = new Roca[5];
		this.rocas[0] = new Roca(100, 300, 0.3, entorno);
		this.rocas[1] = new Roca(550, 150, 0.4, entorno);
		this.rocas[2] = new Roca(350, 200, 0.5, entorno);
		this.rocas[3] = new Roca(250, 450, 0.6, entorno);
		this.rocas[4] = new Roca(500, 350, 0.7, entorno);
		this.hFuego = new Hechizos(entorno.mouseX(), entorno.mouseY(), "Fuego", entorno);
		this.hAgua = new Hechizos(entorno.mouseX(), entorno.mouseY(), "Agua", entorno);
		// En constructor:
		this.areaFuego = Herramientas.cargarImagen("hechizoFuego.gif"); // o areafuego.png
		this.areaAgua = Herramientas.cargarImagen("hechizoAgua.gif"); // o areaAgua.png
		Herramientas.cargarSonido("agua.wav");
		Herramientas.cargarSonido("fuego.wav");
		murcielagos = new Murcielago[cantMurcielagos];
		this.gondolf = new Gondolf(400, 300);
		for (int i = 0; i < cantMurcielagos; i++) {
			int xRandom = random.nextInt(menuX); // sólo entre 0 y 647 para no entrar al menú
			int yRandom = random.nextInt(altoPantalla); // entre 0 y 599, todo el alto
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
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		this.entorno.dibujarImagen(fondo, 400, 300, 0);

		// botones
		botonAgua.dibujar(entorno);
		botonFuego.dibujar(entorno);
		for (PocionMana p : pociones) {
			if (p != null) {
				p.dibujar(entorno);
			}
		}
		// Piedras
		for (int i = 0; i < rocas.length; i++) {
			rocas[i].dibujar(entorno);
		}

		// Murcielago.mover();
		for (int i = 0; i < murcielagos.length; i++) {
			Murcielago m = murcielagos[i];

			if (m != null) {
				m.mover();
				m.cambiarAngulo(gondolf.x, gondolf.y);
				m.dibujarse(entorno);

				// Colisión con Gondolf
				if (gondolf.colisionaCon(m)) {
					gondolf.pierdeVida();
					// NO se suma al contador de enemigos eliminados
					if (gondolf.getVidaActual() > 0) {
						murcielagos[i] = generarMurcielagoAleatorioFueraDelMenu();
					} else {
						murcielagos[i] = null; // seguí generando aunque hayas alcanzado el total
					}
				}
			}
		}
		// Condición de perder
		if (gondolf.getVidaActual() <= 0) {
			perdiste = true;
		}

		// Verifica si no queda ningún murciélago en pantalla
		boolean quedanMurcielagosVivos = false;
		for (Murcielago m : murcielagos) {
			if (m != null) {
				quedanMurcielagosVivos = true;
				break;
			}
		}

		// Si ya se generaron todos los murciélagos, no queda ninguno en pantalla
		// y no mate los 50 -> perdiste
		if (!quedanMurcielagosVivos && contEnemigosEliminados < totalMurcielagos) {
			perdiste = true;
		}

		// Mostrar pantalla de perder
		if (perdiste) {
			this.entorno.dibujarImagen(gameOver, 400, 300, 0, 0.8);
			if (entorno.sePresiono('f')) {
				System.exit(0);
			}
			return;
		}

		// Mostrar pantalla de victoria si se mataron todos los murciélagos (50)
		if (contEnemigosEliminados >= totalMurcielagos) {
			this.entorno.dibujarImagen(ganador, 400, 300, 0);
			if (entorno.sePresiono('f')) {
				System.exit(0);
			}
			return;
		}
		gondolf.dibujar(entorno);

		// Movimiento del mago a la izquierda
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

			boolean clickFueraMenu = !(mouseX >= menuX && mouseX <= menuX + menuAncho && mouseY >= 0
					&& mouseY <= menuAlto);

			if (clickFueraMenu) {
				botonAgua.setColor(miAzul);
				botonFuego.setColor(miRojo);
				hechizoSeleccionado = "";
			}
		}
		// ArrayList<PocionMana> pocionesAEliminar = new ArrayList<>();

		for (int i = 0; i < pociones.length; i++) {
			PocionMana p = pociones[i];
			if (p != null && p.colisionaCon(gondolf)) {
				gondolf.recuperarMana(100);
				pociones[i] = null; // la poción desaparece al ser recogida
			}
		}

		/*
		 * for (PocionMana p : pocionesAEliminar) { pociones.remove(p); }
		 */

		// Coordenadas de la barra
		int barraX = 725;
		int barraYVida = 350;
		int barraYMana = 390;
		int barraYEliminados = 430;
		int anchoMaximo = 100;
		int alto = 20;

		// Cálculo de proporciones usando multiplicación con 1.0
		// double proporcionVida = 1.0 * vidaActual / vidaMaxima;
		double proporcionMana = 1.0 * gondolf.getManaActual() / gondolf.getManaMaxima();
		// Barra de vida
		double proporcionVida = 1.0 * gondolf.getVidaActual() / gondolf.getVidaMaxima();

		entorno.dibujarRectangulo(barraX, barraYVida, anchoMaximo, alto, 0, Color.WHITE); // fondo
		entorno.dibujarRectangulo(barraX, barraYVida, (int) (anchoMaximo * proporcionVida), alto, 0, Color.RED); // relleno
		entorno.cambiarFont("Courier New", 14, Color.WHITE, entorno.NEGRITA);
		entorno.escribirTexto(gondolf.getVidaActual() + "/" + gondolf.getVidaMaxima(), barraX - 20, barraYVida + 5);
		// Barra de maná
		entorno.dibujarRectangulo(barraX, barraYMana, anchoMaximo, alto, 0, Color.WHITE); // fondo
		entorno.dibujarRectangulo(barraX, barraYMana, anchoMaximo * proporcionMana, alto, 0, Color.BLUE); // relleno
		entorno.cambiarFont("Courier New", 14, Color.WHITE, entorno.NEGRITA);
		entorno.escribirTexto(gondolf.getManaActual() + "/" + gondolf.getManaMaxima(), barraX - 20, barraYMana + 5);

		String eliminados = String.format("Eliminados:" + contEnemigosEliminados);
		entorno.dibujarRectangulo(barraX, barraYEliminados, anchoMaximo, alto, 0, Color.BLACK); // fondo
		this.entorno.cambiarFont("Courier New", 12, Color.WHITE, entorno.NEGRITA);
		this.entorno.escribirTexto(eliminados, 680, 433);
		if (timerMensaje > 0) {
			entorno.cambiarFont("Arial", 30, Color.RED, entorno.NEGRITA);
			entorno.escribirTexto(mensajeManá, 350, 550); //
			timerMensaje--;
		}
		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && botonFuego.cursorSobreBoton(entorno)) {
			botonFuego.setColor(miGris);

		}

		if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			if (botonFuego.cursorSobreBoton(entorno)) {
				hechizoSeleccionado = "Fuego";
				botonFuego.setColor(miGris); // gris = seleccionado
				botonAgua.setColor(miAzul); // azul = no seleccionado
			} else if (botonAgua.cursorSobreBoton(entorno)) {
				hechizoSeleccionado = "Agua";
				botonAgua.setColor(miGris); // gris = seleccionado
				botonFuego.setColor(miRojo); // rojo = no seleccionado
			}
		}

		if (entorno.sePresionoBoton(entorno.BOTON_DERECHO)) {
			double mouseX = entorno.mouseX();
			double mouseY = entorno.mouseY();

			boolean clickFueraMenu = !(mouseX >= menuX && mouseX <= menuX + menuAncho && mouseY >= 0
					&& mouseY <= menuAlto);

			if (hechizoSeleccionado != null && clickFueraMenu) {
				if (hechizoSeleccionado.equals("Fuego")) {
					if (gondolf.getManaActual() >= 25) {
						gondolf.pierdeMana(25);
						listaHechizos.add(new Hechizos(mouseX, mouseY, hechizoSeleccionado, entorno));
						Herramientas.play("fuego.wav");
					} else {
						mensajeManá = "¡¡¡No queda maná!!!";
						timerMensaje = 120; // 2 segundos
					}
				} else if (hechizoSeleccionado.equals("Agua")) {
					listaHechizos.add(new Hechizos(mouseX, mouseY, hechizoSeleccionado, entorno));
					Herramientas.play("agua.wav");
				}
				hechizoSeleccionado = null;
				botonFuego.setColor(miRojo);
				botonAgua.setColor(miAzul);
			}
		}

		for (int i = 0; i < listaHechizos.size(); i++) {
			Hechizos h = listaHechizos.get(i);
			h.dibujar(entorno); // este método ya descuenta duracionFrames y desactiva si llega a 0

			if (h.activa) {
				for (int j = 0; j < murcielagos.length; j++) {
					Murcielago m = murcielagos[j];
					if (m != null) {
						double distancia = Math.hypot(h.x - m.x, h.y - m.y);
						if (distancia <= h.radio) {
							contEnemigosEliminados++;

							// Intentar generar una poción con 10% de probabilidad
							if (Math.random() < 0.1) {
								for (int k = 0; i < pociones.length; i++) {
									if (pociones[k] == null) {
										pociones[k] = new PocionMana(m.x, m.y);
										break;
									}
								}
							}

							if (contEnemigosEliminados < totalMurcielagos) {
								murcielagos[j] = generarMurcielagoAleatorioFueraDelMenu();
							} else {
								murcielagos[j] = null;
							}
						}
					}
				}
			}
			// Eliminar hechizo solo si ya terminó su duración
			if (!h.activa) {
				listaHechizos.remove(i);
				i--;
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		new Juego();
	}

}
