package graficos;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import herramientas.Constantes;
import pantalla.Ente;
import pantalla.Juego;

public class OtrosPersonajes extends Ente{
		private String usuario;
		private String raza;
		int coordX;
		int coordY;
		int nivel;
		boolean aliado;
		
		public OtrosPersonajes(String usuario, String raza, int coordX, int coordY, int nivel,Juego juego,Mapa mapa) {
			super(juego, mapa, Constantes.ANCHO_ENTE, Constantes.ALTO_ENTE, coordX, coordY,Sprite.cargarOtroPersonaje(raza), Constantes.VELOCIDAD_ANIMACION);
			
			this.usuario = usuario;
			this.raza = raza;
			this.coordX = coordX;
			this.coordY = coordY;
			this.nivel = nivel;
		}

		public void actualizar() {
			super.moverIzquierda.actualizar();
			super.moverArribaIzquierda.actualizar();
			super.moverArriba.actualizar();
			super.moverArribaDerecha.actualizar();
			super.moverDerecha.actualizar();
			super.moverAbajoDerecha.actualizar();
			super.moverAbajo.actualizar();
			super.moverAbajoIzquierda.actualizar();
			//getEntrada();
			mover();
		}
		
//		public void getEntrada() {
//
//			posMouse = new Point(juego.getMouse().obtenerPosicionMouse());
//			System.out.println(punto.x+" "+punto.y);
//			if (xFinal!= punto.x && yFinal != punto.y) {
//				super.ultimo=true;
//				diagonalInferiorIzquierda = false;
//				diagonalInferiorDerecha = false;
//				diagonalSuperiorIzquierda = false;
//				diagonalSuperiorDerecha = false;
//				vertical = false;
//				horizontal = false;
//				enMovimiento = false;
//
//				xInicial = punto.x;
//				yInicial = punto.y;
//							
//				xFinal = Math.round(posMouse.x + juego.getCamaraPersonaje().getMovimientoX() - desplzamientoX);
//				yFinal = Math.round(posMouse.y + juego.getCamaraPersonaje().getMovimientoY() - desplazamientoY);
//							
//				diferencialX = Math.abs(xFinal - xInicial);
//				diferencialY = Math.abs(yFinal - yInicial);
//				relacion = diferencialX / diferencialY;
//
//				if (diferencialX == 0 || diferencialY == 0) {
//					relacion = 1;
//				}
//
//				if (diferencialX < ancho && yInicial != yFinal) {
//					vertical = true;
//					horizontal = true;
//				}
//				if (diferencialY < alto && xInicial != xFinal) {
//					horizontal = true;
//					vertical = true;
//				}
//
//				if (!vertical && !horizontal) {
//					if (xFinal > xInicial && yFinal > yInicial) {
//						diagonalInferiorDerecha = true;
//					} else if (xFinal < xInicial && yFinal > yInicial) {
//						diagonalInferiorIzquierda = true;
//					} else if (xFinal > xInicial && yFinal < yInicial) {
//						diagonalSuperiorDerecha = true;
//					} else if (xFinal < xInicial && yFinal < yInicial) {
//						diagonalSuperiorIzquierda = true;
//					}
//				}
//
//				enMovimiento = true;
//			}
//		}
		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getRaza() {
			return raza;
		}

		public void setRaza(String raza) {
			this.raza = raza;
		}

		public int getCoordX() {
			return coordX;
		}

		public void setCoordX(int coordX) {
			this.coordX = coordX;
		}

		public int getCoordY() {
			return coordY;
		}

		public void setCoordY(int coordY) {
			this.coordY = coordY;
		}

		public int getNivel() {
			return nivel;
		}

		public void setNivel(int nivel) {
			this.nivel = nivel;
		}

		public boolean isAliado() {
			return aliado;
		}

		public void setAliado(boolean aliado) {
			this.aliado = aliado;
		}
		

	
}
