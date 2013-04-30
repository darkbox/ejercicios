package entornoGrafico1.sistemaAscensor;
/**
 * Crea una clase testAscensor que simule el funcionamiento de un ascensor. Para ello, 
tendrás que crear una librería sistemaAscensor que contenga las clases Ascensor y 
puerta. Una salida típica puede ser esta:
Elige la altura del edificio (plantas en total): 
6
Estás en la planta 1. Elige un destino: 
5
Abriendo puerta
Cerrando puerta
Subiendo. Planta 2.
Subiendo. Planta 3.
Subiendo. Planta 4.
Subiendo. Planta 5.
Abriendo puerta
Estás en la planta 5. Destino conseguido
Cerrando puerta
Quieres dejar el ascensor ya? 
n
Estás en la planta 5. Elige un destino: 
1
Abriendo puerta
Cerrando puerta
Bajando. Planta 4.
Bajando. Planta 3.
Bajando. Planta 2.
Bajando. Planta 1.
Abriendo puerta
Estás en la planta 1. Destino conseguido
Cerrando puerta
Quieres dejar el ascensor ya? 
n
Estás en la planta 1. Elige un destino: 
6
Abriendo puerta
Cerrando puerta
Subiendo. Planta 2.
Subiendo. Planta 3.
Subiendo. Planta 4.
Subiendo. Planta 5.
Subiendo. Planta 6.
Abriendo puerta
Estás en la planta 6. Destino conseguido
Cerrando puerta
Quieres dejar el ascensor ya?
 * 
 * @author Rafael Garc�a Maliga
 * @version 2.0
 *
 */
public class Ascensor {
	private int floors;
	private int currentFloor;
	private int targetFloor;
	private Puerta door;
	//Constructor
	public Ascensor(int floors, Puerta door) {
		super();
		this.floors = floors;
		this.currentFloor = 0; //Planta por defecto
		this.targetFloor = 0;
		this.door = door;
	}
	//Métodos
	/**
	 * Método up() aumenta el número de la planta actual
	 */
	void up(){
		this.door.open();
		this.door.close();
		while(this.currentFloor<this.targetFloor && this.currentFloor<this.floors){
			this.currentFloor++;
			System.out.println("Subiendo. Planta "+this.currentFloor+".");
		}
		//Ha llegado al destino
		this.door.open();
		info();
		System.out.println(" Destino conseguido.");
		this.door.close();
	}
	/**
	 * Método up() decrementa el número de la planta actual
	 */
	void down(){
		this.door.open();
		this.door.close();
		while(this.currentFloor>this.targetFloor && this.currentFloor>0){
			this.currentFloor--;
			System.out.println("Bajando. Planta "+this.currentFloor+".");
		}
		//Ha llegado al destino
		this.door.open();
		info();
		System.out.println(" Destino conseguido.");
		this.door.close();
	}
	
	public void move(){
		if(this.getCurrentFloor() > this.getTargetFloor()){
			this.down();
		}else if (this.getCurrentFloor() < this.getTargetFloor()){
			this.up();
		}else{
			System.out.println("________________________________\n"
							 + "    Ya est� en la misma planta\n");
		}
		
	}
	/**
	 * Muestra la planta actual en la que se encuentra.
	 */
	void info(){
		System.out.print("Est�s en la planta "+this.currentFloor+".");
	}
	//Sets y Gets
	public void setTargetFloor(int targetFloor) {
		this.targetFloor = targetFloor;
	}
	public void setFloors(int floors){
		this.floors = floors;
	}
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	public int getCurrentFloor() {
		return currentFloor;
	}
	public int getTargetFloor() {
		return targetFloor;
	}
	public int getFloors() {
		return floors;
	}
}
