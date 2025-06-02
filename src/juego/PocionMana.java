package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class PocionMana {
	double x, y;
	double ancho, alto;
	Image imagen;
	double escala = 0.2;

	public PocionMana(double x, double y) {
		this.x = x;
		this.y = y;
		this.imagen = Herramientas.cargarImagen("pocionMana.jpg");
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(imagen, x, y, 0, escala);
	}

	public boolean colisionaCon(Gondolf g) {
		if (g == null)
			return false;
		return this.getBordeDer() > g.getBordeIzq() && this.getBordeIzq() < g.getBordeDer()
				&& this.getBordeInf() > g.getBordeSup() && this.getBordeSup() < g.getBordeInf();
	}

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