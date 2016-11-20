package herramientas;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
*	Point es una clase muy simple, compuesta de 2 propiedades: X y Y. 
*	Se utiliza mucho para representar coordenadas en un plano bidimensional. 
*	Por ejemplo, si necesitas colocar un objeto en la posicion X=10 y Y=5 en un plano cartesiano,
*	puedes instanciar un objeto de tipo Point de esta manera: 
*	Point posicion = new Point(10,5);
*	ES UN SUSTITUTO PARA EVITAR USAR DOS VARIABLES ENTERAS O UN VECTOR DE DOS POSICIONES...
*	SI USTEDES CLICKEAN DENTRO DE LA VENTANA EN LA CONSOLA LES APARECERA LAS COORDENADAS.
*/

public class MousePoint implements MouseListener{
	Point punto;
	Point posicionActual;
	private boolean click;
	
	public MousePoint() {
		this.punto = new Point();
		this.posicionActual = new Point();
		this.click = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		setearPosicionMouse(e);
		
		click = true;
		System.out.println("X: " + punto.x + "--" + "Y: " + punto.y);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void setearPosicionMouse(MouseEvent e) {
		punto.x = e.getX();
		punto.y = e.getY();
	}
	
	public Point obtenerPosicionMouse() {
		return posicionActual;
	}
	
	public boolean obtenerClick(){
		return click; 
	}
	
	public void setClick(boolean click){
		this.click = click;
	}
	
	public void reiniciarClick(){
		click = false;
	}
	public void actualizar(){
		this.posicionActual.x = punto.x;
		this.posicionActual.y = punto.y;
	}
	

}
