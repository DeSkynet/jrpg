package pantalla;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import graficos.Animacion;
import graficos.Mapa;
import graficos.OtrosPersonajes;
import herramientas.HerramientasGraficas;
import mensajes.MensajeColision;

public class Ente {
	protected Juego juego;

	// Tamaño de la entidad
	protected int ancho;
	protected int alto;

	// Posiciones
	protected Point punto; 	// Posicion del personaje en el juego (x,y)
	protected float dx;		//
	protected float dy;		//
	protected float xInicial;	// Posicion de inicio del personaje en x
	protected float yInicial;	// Posicion de inicio del personaje en y
	protected float xFinal;	// Posicion final del personaje en x.DONDE QUIERE LLEGAR
	protected float yFinal;	// Psociion final del personaje en y
	protected int desplzamientoX;	// Desplazamiento de la camara en x
	

	protected int desplazamientoY;	// Desplazamiento de la camara en y
	protected int dibujarX;		// Dibujo en la coordenada x
	protected int dibujarY;		// Dibujo en la coordenada y
	protected Point posMouse;	// Posicion del mouse en (x,y)
	protected Point cuadro;	// Posicion en el cuadro (x,y)
	protected Point ulti=new Point(0, 0);
	protected boolean ultimo=false;

	// Calculo de movimiento
	protected float diferencialX;		// diferencial x para el calculo del movimiento en diagonal
	protected float diferencialY;		// diferencial y para el calculo del movimiento en diagonal
	protected float relacion;	// relacion entre el diferencial x e y

	// Posicion final del movimiento del personaje en (x,y)
//	private Point posicionFinal;	

	// Movimiento actual del personaje 
	protected boolean enMovimiento;	
	protected boolean horizontal;	
	protected boolean vertical;		
	protected boolean diagonalInferiorIzquierda;	
	protected boolean diagonalInferiorDerecha;
	protected boolean diagonalSuperiorIzquierda;
	protected boolean diagonalSuperiorDerecha;

	// Animaciones del perosnaje
	protected LinkedList<BufferedImage[]> animaciones;
	protected final Animacion moverIzquierda;
	protected final Animacion moverArribaIzquierda;
	protected final Animacion moverArriba;
	protected final Animacion moverArribaDerecha;
	protected final Animacion moverDerecha;
	protected final Animacion moverAbajoDerecha;
	protected final Animacion moverAbajo;
	protected final Animacion moverAbajoIzquierda;
	protected boolean eleccion;

	protected Mapa mapa;
	
	
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
		if(( punto.x!=xFinal || punto.y!=yFinal || ulti.x !=punto.x || ulti.y!=punto.y) && ultimo==true){
			ulti=new Point(punto.x, punto.y);
			if(punto.x== xFinal && punto.y==yFinal)
				ultimo=false;
			juego.cliente.posicionDelPersonaje(punto);
			
		}
		juego.getCamaraPersonaje().Centrar(this);
	}

	public void getEntrada() {

		posMouse = new Point(juego.getMouse().obtenerPosicionMouse());
		System.out.println(punto.x+" "+punto.y);
		if (juego.getMouse().obtenerClick()) {
			ultimo=true;
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
			OtrosPersonajes per=this.ChequeaColicion(punto.x,punto.y);
			if(per!=null){
				if(eleccion==false){
				eleccion=true;
				enviaColicionAEnemigo(per.getUsuario());
				JOptionPane.showConfirmDialog(null, "HAY COLICION con: "+per.getUsuario());
				}
			}else{
				eleccion=false;
			}
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
				dx += relacion;
				dy++;
				cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx+15 ,punto.y+(int)dy+15));
			} else if (diagonalInferiorIzquierda) {
				dx -= relacion;
				dy++;
				cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx-15,punto.y+(int)dy+15));
			} else if (diagonalSuperiorDerecha) {
				dx += relacion;
				dy--;
				cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx+15,punto.y+(int)dy-15));
			} else if (diagonalSuperiorIzquierda) {
				dx -= relacion;
				dy--;
				cuadro =   HerramientasGraficas.clickACuadro(new Point(punto.x+(int)dx-15 , punto.y+(int)dy-15));
				
			}

			//System.out.println(xFinal+" "+ yFinal+" "+xInicial+" "+punto.x+" "+punto.y);
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
	
			if (Math.round(punto.x) == xFinal && Math.round(punto.y) == yFinal) {
				diagonalInferiorIzquierda = false;
				diagonalInferiorDerecha = false;
				diagonalSuperiorIzquierda = false;
				diagonalSuperiorDerecha = false;
				enMovimiento = false;
			}

		}
	}

	private void enviaColicionAEnemigo(String usuario) {
		juego.cliente.enviarMensaje("MensajeColicion", new MensajeColision(juego.cliente.getUsuario(),usuario));
		
	}

	public void graficar(Graphics g) {
		dibujarX = (int) (punto.x - juego.getCamaraPersonaje().getMovimientoX());
		dibujarY = (int) (punto.y - juego.getCamaraPersonaje().getMovimientoY());
		g.drawImage(this.getFrameAnimacionActual(), dibujarX, dibujarY, ancho, alto, null);
	}

	protected BufferedImage getFrameAnimacionActual() {
		if (horizontal && punto.x > xFinal) {
			return this.moverIzquierda.getFrameActual();
		} else if (horizontal && punto.x < xFinal) {
			return this.moverDerecha.getFrameActual();
		} else if (vertical && punto.y > yFinal) {
			return this.moverArriba.getFrameActual();
		} else if (vertical && punto.y < yFinal) {
			return this.moverAbajo.getFrameActual();
		} else if (diagonalInferiorIzquierda) {
			return this.moverAbajoIzquierda.getFrameActual();
		} else if (diagonalInferiorDerecha) {
			return this.moverAbajoDerecha.getFrameActual();
		} else if (diagonalSuperiorIzquierda) {
			return this.moverArribaIzquierda.getFrameActual();
		} else if (diagonalSuperiorDerecha) {
			return this.moverArribaDerecha.getFrameActual();
		}
		return this.animaciones.get(6)[0];
		
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

	public boolean isEnMovimiento() {
		return enMovimiento;
	}

	public void setEnMovimiento(boolean enMovimiento) {
		this.enMovimiento = enMovimiento;
	}
	public Juego getJuego(){
		return this.juego;
	}
	public void setXYFinal(int x,int y){
		xFinal=x;
		yFinal=y;
	}
	public OtrosPersonajes ChequeaColicion(int xActual,int yActual){
		Point point = HerramientasGraficas.clickACuadro(new Point(xActual, yActual));
			Iterator<OtrosPersonajes> it =this.juego.getEstadoJuego().getOtroPersonajes().values().iterator();
			while(it.hasNext()){
				OtrosPersonajes per=it.next();
			Point poinOtro = HerramientasGraficas.clickACuadro(new Point(per.getPunto().x, per.getPunto().y));
				if(point.x==poinOtro.x ){
					if(point.y==poinOtro.y ){
						return per;
					}
				}
			
		}
	
	return null;
}
}
