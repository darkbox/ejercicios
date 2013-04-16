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
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Directorio.getWorkingDirecctory());
		// Creamos un directorio
		Directorio.mkdir(new File("dir_01"));
		Directorio.mkdir(new File("dir_02"));
		System.out.println();
		// Creamos varios directorios
		
		Directorio.mkdir(new File("dir_01\\subDir_01\\lol"));
		System.out.println();
		
		// Comprobamos si está vacío un directorio
		Directorio.checkEmptyDir(new File("dir_02"));
		System.out.println();

		// listamos los directorios
		Directorio.displayDirAndFile(new File("dir_01"));
		System.out.println();

		// Copiamos un directorio
		Directorio.cpdir(new File("dir_01"), new File("dir_02/CopiaDir_01"));
		System.out.println();

		// listamos los directorios
		Directorio.displayDirAndFile(new File("dir_02"));
		System.out.println();

		// borramos todo
		Directorio.rmdir(new File("dir_01"));
		Directorio.rmdir(new File("dir_02"));


		
	}

}
