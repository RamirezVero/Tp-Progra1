package juego;
import java.awt.Color;

import entorno.Entorno;

public class Hud {
    // Posiciones y dimensiones base
    private int barraX = 725;
    private int barraYVida = 350;
    private int barraYMana = 390;
    private int barraAnchoMaximo = 80;
    private int barraAlto = 15;

    // Estado del jugador
    private int vidaActual = 100;
    private int vidaMaxima = 100;
    private int manaActual = 100;
    private int manaMaxima = 100;

    // Enemigos eliminados
    private int enemigosEliminados = 0;

    // Mostrar u ocultar Hud
    private boolean visible = true;

    // Actualizar valores
    public void actualizarEstado(int vidaActual, int vidaMaxima, int manaActual, int manaMaxima, int enemigosEliminados) {
        this.vidaActual = vidaActual;
        this.vidaMaxima = vidaMaxima;
        this.manaActual = manaActual;
        this.manaMaxima = manaMaxima;
        this.enemigosEliminados = enemigosEliminados;
    }

    // Ocultar HUD si el jugador muere
    public void setVisibleSegúnEstadoDelJugador(boolean estaVivo) {
        this.visible = estaVivo;
    }

    // Dibujar HUD
    public void dibujar(Entorno entorno) {
        if (!visible) return;

        double proporcionVida = 1.0 * vidaActual / vidaMaxima;
        double proporcionMana = 1.0 * manaActual / manaMaxima;

        entorno.cambiarFont("Courier New", 14, Color.WHITE, entorno.NEGRITA);

        // Vida
        entorno.dibujarRectangulo(barraX, barraYVida, barraAnchoMaximo, barraAlto, 0, Color.WHITE);
        entorno.dibujarRectangulo(barraX, barraYVida, barraAnchoMaximo * proporcionVida, barraAlto, 0, Color.RED);
        entorno.escribirTexto(vidaActual + "/" + vidaMaxima, barraX - 20, barraYVida + 5);

        // Maná
        entorno.dibujarRectangulo(barraX, barraYMana, barraAnchoMaximo, barraAlto, 0, Color.WHITE);
        entorno.dibujarRectangulo(barraX, barraYMana, barraAnchoMaximo * proporcionMana, barraAlto, 0, Color.BLUE);
        entorno.escribirTexto(manaActual + "/" + manaMaxima, barraX - 20, barraYMana + 5);

        // Enemigos eliminados
        String eliminados = "ELIMINADOS: " + enemigosEliminados;
        entorno.escribirTexto(eliminados, 620, 530);
    }
}
