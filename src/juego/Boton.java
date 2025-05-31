package juego;

import java.awt.Color;

import entorno.Entorno;

public class Boton {
	double x;
	double y;
	int ancho;
	int alto;
	Color color;
	String texto;

	// constructor
	public Boton(double x, double y, int ancho, int alto, String texto, Color color) {

		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.texto = texto;
		this.color = color;

	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
		e.cambiarFont("Trebuchet", 18, new Color(242, 242, 233), e.NEGRITA);
		e.escribirTexto(texto, x - 25, y + 5);
	}

	public boolean cursorSobreBoton(Entorno e) {
		double mouseX = e.mouseX();
		double mouseY = e.mouseY();
		return (mouseX >= this.getBordeIzq() && mouseX <= this.getBordeDer())
				&& (mouseY >= this.getBordeSup() && mouseY <= this.getBordeInf());
	}

	public void setColor(Color c) {
		this.color = c;
		// hayHechizoSeleccionado = true;
	}

	// getters de los límites del objeto
	public double getBordeDer() {
		return this.x + (this.ancho / 2);
	}

	public double getBordeIzq() {
		return this.x - (this.ancho / 2);
	}

	public double getBordeSup() {
		return this.y - (this.alto / 2);
	}

	public double getBordeInf() {
		return this.y + (this.alto / 2);
	}

}
