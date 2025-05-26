package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;



public class Gondolf {

	double x;
	double y;
	double ancho;
	double alto;
	double angulo;
	Image imgIzquierda;
	Image imgDerecha;
	Image imgFrente;
	Image imgEspalda;
	int bordePantallaDer;
	int bordePantallaIzq;
	int bordePantallaSup;
	int bordePantallaInf;
	private char direccionActual; // 'a', 'd', 'w', 's'
	int vidas;
	Roca piedra1;
	

	Entorno e;
	double velocidad;
	double escala;
	
	public Gondolf(int x, int y) {
		this.x = x;
		this.y = y;		
		this.velocidad= 3.0;
		this.escala =  0.1;
		imgIzquierda = Herramientas.cargarImagen("izquierdaMG.jpg");
		imgDerecha = Herramientas.cargarImagen("derecha.jpg");
		imgFrente = Herramientas.cargarImagen("frente.jpg");
		imgEspalda = Herramientas.cargarImagen("espaldaMG.jpg");
		bordePantallaDer = 640;
		bordePantallaIzq = 10;
		bordePantallaSup = 30;
		bordePantallaInf = 565;
		vidas = 3;
		
		direccionActual = 'w';
	}

	
	public void dibujar(Entorno e){
		if(this.direccionActual == 'd') {
			e.dibujarImagen(imgDerecha, x, y, 0,escala);
		}		
		if(this.direccionActual == 'a'){
			e.dibujarImagen(imgIzquierda, x, y, 0,escala);
		}
		
		if(this.direccionActual == 'w') {
			e.dibujarImagen(imgEspalda, x, y, 0,escala);
		}
		if(this.direccionActual == 's') {
			e.dibujarImagen(imgFrente, x, y, 0,escala);
		}	
	}
		
	
		public void moverDerecha () {
			if (this.x + this.velocidad < this.bordePantallaDer) {
				this.x += this.velocidad;
				direccionActual = 'd';
			}
		}
		public void moverIzquieda () {
			if (this.x - this.velocidad > this.bordePantallaIzq) {
				this.x -= this.velocidad;
				this.direccionActual = 'a';
			}
		}
		public void moverArriba() {
			if (this.y - this.velocidad > this.bordePantallaSup) {
				this.y -= this.velocidad;
				direccionActual = 'w';
			}
		}
		
		public void moverAbajo() {
			if (this.y + this.velocidad <this.bordePantallaInf) {
				this.y += this.velocidad;				
				direccionActual = 's';
			}
		}
		/*public void moverAbajo(Roca[] rocas) {
		    boolean hayColision = false;

		    for (Roca roca : rocas) {
		        if (this.colisionaCon(roca)) {
		            hayColision = true;
		            break;
		        }
		    }

		    if (!hayColision && this.y + this.velocidad < this.bordePantallaInf) {
		        this.y += this.velocidad;				
		        direccionActual = 's';
		    }
		}*/
		
		
		
		public boolean colisionaCon( Roca r) {
			
		    return this.getBordeDer() > r.getBordeIzq() &&
		            this.getBordeIzq() < r.getBordeDer() &&
		            this.getBordeInf() > r.getBordeSup() &&
		            this.getBordeSup() < r.getBordeInf();
		}
		
		public void pierdeVida() {
			this.vidas --;
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
