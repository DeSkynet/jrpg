package herramientas;

import java.awt.Toolkit;

public class Constantes {
	public static final int ANCHO_PANTALLA = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int ALTO_PANTALLA = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static final String NOMBRE_JUEGO = "The Alliance";
	public static final String PATH_ICONO = "Assets/icono/icono.png";
	public static final String PATH_MAPA_CAMPO = "Assets/mapas/mapaUno.txt";
	public static final String PATH_MAPA_PLAYA = "Assets/mapas/mapaDos.txt";
	public static final String PATH_MAPA_DESIERTO = "Assets/mapas/mapaTres.txt";
	
	public static final String PATH_AGUA = "Assets/sprites/agua.png";
	public static final String PATH_ROCA = "Assets/sprites/roca.png";
	public static final String PATH_CESPED = "Assets/sprites/cesped.png";
	public static final String PATH_PIEDRAN = "Assets/sprites/montaña.png";
	public static final String PATH_TIERRA = "Assets/sprites/tierra.png";

	public static final String SPRITE_ALIEN = "Assets/sprites/goblin.png";
	public static final String SPRITE_ROBOT = "Assets/sprites/skeleton2.png";
	public static final String SPRITE_HUMANO = "Assets/sprites/male_light.png";
	public static final String SPRITE_SUPER = "Assets/sprites/werewolf.png";
	
	public static final int ANCHO_CUADRO = 256;
	public static final int ALTO_CUADRO = 128;
	
	public static final int ANCHO_ENTE = 64;
	public static final int ALTO_ENTE = 64;
	
	public static final int NANOSEGUNDOS_POR_SEGUNDO = 1000000000;
	public static final int ACTUALIZACIONES_POR_SEGUNDO = 60;
	public static final double NANOSEGUNDOS_POR_ACTUALIZACION = NANOSEGUNDOS_POR_SEGUNDO / ACTUALIZACIONES_POR_SEGUNDO;
	public static final int VELOCIDAD_ANIMACION=200;
}
