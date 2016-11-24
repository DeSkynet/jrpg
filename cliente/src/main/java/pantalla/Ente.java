package pantalla;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import graficos.Animacion;
import graficos.Mapa;
import graficos.Sprite;
import herramientas.HerramientasGraficas;

public class Ente {
//	Juego juego;
//
//	// Tamaño de la entidad
//	private int ancho;
//	private int alto;
//
//	// Posiciones
//	private Point punto; 	// Posicion del personaje en el juego (x,y)
//	private float dx;		//
//	private float dy;		//
//	private float xInicial;	// Posicion de inicio del personaje en x
//	private float yInicial;	// Posicion de inicio del personaje en y
//	private float xFinal;	// Posicion final del personaje en x
//	private float yFinal;	// Psociion final del personaje en y
//	private int desplzamientoX;	// Desplazamiento de la camara en x
//	private int desplazamientoY;	// Desplazamiento de la camara en y
//	private int dibujarX;		// Dibujo en la coordenada x
//	private int dibujarY;		// Dibujo en la coordenada y
//	private Point posMouse;	// Posicion del mouse en (x,y)
//	private Point cuadro;	// Posicion en el cuadro (x,y)
//
//	// Calculo de movimiento
//	private float diferencialX;		// diferencial x para el calculo del movimiento en diagonal
//	private float diferencialY;		// diferencial y para el calculo del movimiento en diagonal
//	private float relacion;	// relacion entre el diferencial x e y
//
//	// Posicion final del movimiento del personaje en (x,y)
//	private Point posicionFinal;	
//
//	// Movimiento actual del personaje 
//	private boolean enMovimiento;	
//	private boolean horizontal;	
//	private boolean vertical;		
//	private boolean diagonalInferiorIzquierda;	
//	private boolean diagonalInferiorDerecha;
//	private boolean diagonalSuperiorIzquierda;
//	private boolean diagonalSuperiorDerecha;
//
//	// Animaciones del perosnaje
//	private LinkedList<BufferedImage[]> animaciones;
//	private final Animacion moverIzquierda;
//	private final Animacion moverArribaIzquierda;
//	private final Animacion moverArriba;
//	private final Animacion moverArribaDerecha;
//	private final Animacion moverDerecha;
//	private final Animacion moverAbajoDerecha;
//	private final Animacion moverAbajo;
//	private final Animacion moverAbajoIzquierda;
//
//	private Mapa mapa;
	Juego juego;

	// Tamaño de la entidad
	private int ancho;
	private int alto;

	// Posiciones
	private Point punto; 	// Posicion del personaje en el juego (x,y)
	private float dx;		//
	private float dy;		//
	private float xInicial;	// Posicion de inicio del personaje en x
	private float yInicial;	// Posicion de inicio del personaje en y
	private float xFinal;	// Posicion final del personaje en x.DONDE QUIERE LLEGAR
	private float yFinal;	// Psociion final del personaje en y
	private int desplzamientoX;	// Desplazamiento de la camara en x
	

	private int desplazamientoY;	// Desplazamiento de la camara en y
	private int dibujarX;		// Dibujo en la coordenada x
	private int dibujarY;		// Dibujo en la coordenada y
	private Point posMouse;	// Posicion del mouse en (x,y)
	private Point cuadro;	// Posicion en el cuadro (x,y)

	// Calculo de movimiento
	private float diferencialX;		// diferencial x para el calculo del movimiento en diagonal
	private float diferencialY;		// diferencial y para el calculo del movimiento en diagonal
	private float relacion;	// relacion entre el diferencial x e y

	// Posicion final del movimiento del personaje en (x,y)
//	private Point posicionFinal;	

	// Movimiento actual del personaje 
	private boolean enMovimiento;	
	private boolean horizontal;	
	private boolean vertical;		
	private boolean diagonalInferiorIzquierda;	
	private boolean diagonalInferiorDerecha;
	private boolean diagonalSuperiorIzquierda;
	private boolean diagonalSuperiorDerecha;

	// Animaciones del perosnaje
	private LinkedList<BufferedImage[]> animaciones;
	private final Animacion moverIzquierda;
	private final Animacion moverArribaIzquierda;
	private final Animacion moverArriba;
	private final Animacion moverArribaDerecha;
	private final Animacion moverDerecha;
	private final Animacion moverAbajoDerecha;
	private final Animacion moverAbajo;
	private final Animacion moverAbajoIzquierda;

	private Mapa mapa;
	
	
	public Ente(Juego juego, Mapa mapa, int ancho, int alto, int puntoX, int puntoY, LinkedList<BufferedImage[]> animaciones, int velAnimacion) {
		this.juego = juego;
		this.ancho = ancho;
		this.alto = alto;
		this.mapa = mapa;
		desplzamientoX = ancho / 2;
		desplazamientoY = alto / 2;
		punto = new Point(puntoX,puntoY);

		this.animaciones = animaciones;
		 
	    moverIzquierda = new Animacion(velAnimacion, this.animaciones.get(0));
	    moverArribaIzquierda = new Animacion(velAnimacion, this.animaciones.get(1));
	    moverArriba = new Animacion(velAnimacion, this.animaciones.get(2));
	    moverArribaDerecha = new Animacion(velAnimacion, this.animaciones.get(3));
	    moverDerecha = new Animacion(velAnimacion, this.animaciones.get(4));
	    moverAbajoDerecha = new Animacion(velAnimacion, this.animaciones.get(5));
	    moverAbajo = new Animacion(velAnimacion, this.animaciones.get(6));
	    moverAbajoIzquierda = new Animacion(velAnimacion, this.animaciones.get(7));
	}

	public void actualizar() {
		moverIzquierda.actualizar();
		moverArribaIzquierda.actualizar();
		moverArriba.actualizar();
		moverArribaDerecha.actualizar();
		moverDerecha.actualizar();
		moverAbajoDerecha.actualizar();
		moverAbajo.actualizar();
		moverAbajoIzquierda.actualizar();
		getEntrada();
		mover();
		juego.getCamaraPersonaje().Centrar(this);
	}

	public void getEntrada() {

		posMouse = new Point(juego.getMouse().obtenerPosicionMouse());

		if (juego.getMouse().obtenerClick()) {
			diagonalInferiorIzquierda = false;
			diagonalInferiorDerecha = false;
			diagonalSuperiorIzquierda = false;
			diagonalSuperiorDerecha = false;
			vertical = false;
			horizontal = false;
			enMovimiento = false;

			xInicial = punto.x;
			yInicial = punto.y;
						
			xFinal = Math.round(posMouse.x + juego.getCamaraPersonaje().getMovimientoX() - desplzamientoX);
			yFinal = Math.round(posMouse.y + juego.getCamaraPersonaje().getMovimientoY() - desplazamientoY);
						
			diferencialX = Math.abs(xFinal - xInicial);
			diferencialY = Math.abs(yFinal - yInicial);
			relacion = diferencialX / diferencialY;

			if (diferencialX == 0 || diferencialY == 0) {
				relacion = 1;
			}

			if (diferencialX < ancho && yInicial != yFinal) {
				vertical = true;
				horizontal = true;
			}
			if (diferencialY < alto && xInicial != xFinal) {
				horizontal = true;
				vertical = true;
			}

			if (!vertical && !horizontal) {
				if (xFinal > xInicial && yFinal > yInicial) {
					diagonalInferiorDerecha = true;
				} else if (xFinal < xInicial && yFinal > yInicial) {
					diagonalInferiorIzquierda = true;
				} else if (xFinal > xInicial && yFinal < yInicial) {
					diagonalSuperiorDerecha = true;
				} else if (xFinal < xInicial && yFinal < yInicial) {
					diagonalSuperiorIzquierda = true;
				}
			}

			juego.getMouse().setClick(false);
			enMovimiento = true;
		}
	}

	public void mover() {

		dx = 0;
		dy = 0;

		if (enMovimiento && (punto.x != xFinal || punto.y != yFinal)) {

			if (vertical) {
				if (yFinal > punto.y) {
					dy++;
					cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+15,punto.y+(int)dy+16));
//					mapa.moverYAbajo();
				} else {
					dy--;
					cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx+15,punto.y+(int)dy+14));
//					mapa.moverYArriba();
				}
			}

			if (horizontal) {
				if (xFinal > punto.x) {
					dx++;
					cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx+15,punto.y+15));
//					mapa.moverXIzquierda();
				} else {
					dx--;
					cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx-15,punto.y+15));
//					mapa.moverXDerecha();
				}
			}

			if (diagonalInferiorDerecha) {
				dx += 1;
				dy++;
				cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx+15 ,punto.y+(int)dy+15));
			} else if (diagonalInferiorIzquierda) {
				dx -= 1;
				dy++;
				cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx-15,punto.y+(int)dy+15));
			} else if (diagonalSuperiorDerecha) {
				dx += 1;
				dy--;
				cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx+15,punto.y+(int)dy-15));
			} else if (diagonalSuperiorIzquierda) {
				dx -= 1;
				dy--;
				cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx-15 , punto.y+(int)dy-15));
				
			}
//			ut.println(mapa.getCuadro(cuadro).noEsAtravesable());
			if (mapa.getCuadro(cuadro).noEsAtravesable()) {
				xFinal = punto.x;
				yFinal = punto.y;

			} else {
				punto.x += dx;
				punto.y += dy;

			}

			if (horizontal || vertical) {
				if (punto.x == xFinal) {
					horizontal = false;
				}

				if (punto.y == yFinal) {
					vertical = false;
				}
			}
	
			if (punto.x == xFinal && punto.y == yFinal) {
				diagonalInferiorIzquierda = false;
				diagonalInferiorDerecha = false;
				diagonalSuperiorIzquierda = false;
				diagonalSuperiorDerecha = false;
				enMovimiento = false;
			}
		}
	}

	public void graficar(Graphics g) {
		dibujarX = (int) (punto.x - juego.getCamaraPersonaje().getMovimientoX());
		dibujarY = (int) (punto.y - juego.getCamaraPersonaje().getMovimientoY());
		g.drawImage(getFrameAnimacionActual(), dibujarX, dibujarY, ancho, alto, null);
	}

	private BufferedImage getFrameAnimacionActual() {
		if (horizontal && punto.x > xFinal) {
			return moverIzquierda.getFrameActual();
		} else if (horizontal && punto.x < xFinal) {
			return moverDerecha.getFrameActual();
		} else if (vertical && punto.y > yFinal) {
			return moverArriba.getFrameActual();
		} else if (vertical && punto.y < yFinal) {
			return moverAbajo.getFrameActual();
		} else if (diagonalInferiorIzquierda) {
			return moverAbajoIzquierda.getFrameActual();
		} else if (diagonalInferiorDerecha) {
			return moverAbajoDerecha.getFrameActual();
		} else if (diagonalSuperiorIzquierda) {
			return moverArribaIzquierda.getFrameActual();
		} else if (diagonalSuperiorDerecha) {
			return moverArribaDerecha.getFrameActual();
		}
		return Sprite.getSprite().get(6)[0];
		
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public Point getPunto() {
		return punto;
	}

	public void setPunto(Point punto) {
		this.punto = punto;
	}
	public int getDesplzamientoX() {
		return desplzamientoX;
	}

	public int getDesplazamientoY() {
		return desplazamientoY;
	}
}
