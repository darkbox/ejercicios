package entornoGrafico1.sistemaAscensor;

/**
 * Clase que contiene el funcionamiento básico de la
 * puerta de un ascensor.
 * 
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public class Puerta {
	boolean status;
	//Constructor
	public Puerta() {
		super();
		this.status = false;
	}
	//Métodos
	void open(){
		System.out.println("Abriendo puerta");
		this.status=true;
	}
	void close(){
		System.out.println("Cerrando puerta");
		this.status=false;
	}
}
