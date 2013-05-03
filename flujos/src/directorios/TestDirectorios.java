package directorios;

import java.io.File;

/**
 * 
 * Clase TestDirectorios
 * 
 * Comprueba el uso de la clase Directorio
 * 
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 * 
 */
public class TestDirectorios {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		TecladoScanner t = TecladoScanner.getInstance();
		Menu menu =  new Menu("Directorios", true);
		menu.add("Crear un directorio");
		menu.add("Borrar");
		menu.add("Renombrar");
		menu.add("Copiar directorio");
		menu.add("Listar");
		menu.add("Directorio actual");
		menu.add("Crear varios directorios");
		menu.add("Mostrar ruta absoluta");
		menu.add("Borrar si está vacio");
		menu.add("Mostrar padre");
		menu.add("Es directorio?");
		
		while(true){
			menu.print();
			switch (menu.select()) {
			case 1:
				Directorio.mkdir(new File(t.leerCadena("Escribe el nombre del directorio:")));
				break;
			case 2:
				Directorio.rmdir(new File(t.leerCadena("Escribe el nombre del directorio:")));
				break;
			case 3:
				Directorio.renameDirAndFile(new File(t.leerCadena("Escribe el nombre del directorio:")), new File(t.leerCadena("Escribe el nombre del directorio:")));
				break;
			case 4:
				Directorio.cpdir(new File(t.leerCadena("Escribe el antiguo nombre:")), new File(t.leerCadena("Escribe el nuevo nombre:")));
				break;
			case 5:
				Directorio.displayDirAndFile(new File(t.leerCadena("Escribe el nombre del directorio a listar:")));
				break;
			case 6:
				System.out.println(Directorio.getWorkingDirectory());
				break;
			case 7:
				Directorio.mkdirs(new File(t.leerCadena("Escribe el nombre de los directorios:")));
				break;
			case 8:
				Directorio.getAbsolutePath(new File(t.leerCadena("Escribe el nombre del directorio:")));
				break;
			case 9:
				Directorio.rmdirIfEmpty(new File(t.leerCadena("Escribe el nombre del directorio:")));
				break;
			case 10:
				Directorio.getParent(new File(t.leerCadena("Escribe el nombre del directorio:")));
				break;
			case 11:
				if(Directorio.isDir(new File(t.leerCadena("Escribe el nombre del directorio:"))))
					System.out.println("Sí es un directorio.");
				else
					System.out.println("No es un directorio.");
				break;
			}
		}

	}

}
