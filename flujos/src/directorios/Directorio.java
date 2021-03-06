package directorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Clase Directorio
 * @author Rafael Garc�a Maliga
 * @since 16/04/2013
 * @version 1.0
 * 
 * Entrega la clase Directorios que permita el manejo de directorios de un sistema de ficheros.
 * Para ello utiliza los siguientes enlaces:
 * Java I/O Tutorial
 * Ficheros (p�gina 101 de Java, Jorge S�nchez)
 * API de Java 6 SE
 * Para ello has de utilizar la clase File.
 * 
 */
public class Directorio {
	
	/**
	 * Optiene la direcci�n del directorio de trabajo actual
	 * @return String direcci�n del directorio
	 */
	public static String getWorkingDirectory(){
		return System.getProperty("user.dir");
	}
	
	/**
	 * Obtiene el directorio padre de un fichero
	 * @param file fichero
	 * @return String directorio padre
	 */
	public static String getParent(File file){
		File file2 = new File(file.getAbsolutePath());
		return file2.getParent();
	}
	
	/**
	 * Obtiene la direcci�n absoluta
	 * @param file fichero
	 * @return String direcci�n absoluta
	 */
	public static String getAbsolutePath(File file){
		return file.getAbsolutePath();
	}
	
	/**
	 * Comprueba si un fichero o directorio existe
	 * @param file fichero
	 * @return true si existe
	 */
	public static boolean exists(File file){
		return file.exists();
	}
	
	/**
	 * Comprueba si es un directorio
	 * @param dir fichero
	 * @return int 1 no est� vac�o; 0 est� vac�o; -1 no es un directorio 
	 */
	public static int checkEmptyDir(File dir){
		if(dir.isDirectory()){
			if(dir.list().length>0){
				System.out.println("Directory is not empty!");
				return 1;
			}else{
				System.out.println("Directory is empty!");
				return 0;
			}
		}else{
			System.out.println("This is not a directory");
			return -1;
		}
	}
	
	/**
	 * Comprueba si es un directorio o no.
	 * @param dir fichero
	 * @return true si es directorio
	 */
	public static boolean isDir(File dir){
		if(dir.isDirectory()){
			return true;
		}
		return false;
	}
	
	/**
	 * Renombra un directorio o fichero
	 * @param old fichero antiguo
	 * @param dest fichero destino
	 * @return true si ha tenido �xito
	 */
	public static boolean renameDirAndFile(File old, File dest){
		return old.renameTo(dest);
	}
	
	/**
	 * Muestra y lista un directorio y sus archivos
	 * @param node fichero nodo
	 */
	public static void displayDirAndFile(File node){
		System.out.println(node.getAbsoluteFile());
		 
		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				displayDirAndFile(new File(node, filename));
			}
		}
	}
	
	/**
	 * Crea un directorio
	 * "C:\\Directory1"
	 * @param dir fichero a crear
	 */
	public static void mkdir(File dir){
		if(!dir.exists()){
			if(dir.mkdir()){
				System.out.println("Directory is created!");
			}else{
				System.out.println("Failed to create directory!");
			}
		}
	}
	
	/**
	 * Crea varios directorios
	 * "C:\\Directory2\\Sub2\\Sub-Sub2"
	 * @param dirs ficheros a crear
	 */
	public static void mkdirs(File dirs){
		if (!dirs.exists()) {
			if (dirs.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
	}
	
	/**
	 * Borra un directorio
	 * @param dir fichero a borrar
	 */
	public static void rmdir(File dir){
		if(!dir.exists()){
			System.err.println("Directory does not exist.");
		}else{
			try{
				delete(dir);
				System.out.println("Done");
			}catch(IOException e){
				System.err.println("Error");
			}
		}
	}
	
	/**
	 * Borra un directorio si est� vac�o
	 * @param dir fichero
	 */
	public static void rmdirIfEmpty(File dir){
		if(!dir.exists()){
			System.err.println("Directory does not exist.");
		}else if(checkEmptyDir(dir) == 0){
			try{
				delete(dir);
				System.out.println("Done");
			}catch(IOException e){
				System.err.println("Error");
			}
		}else{
			System.err.println("Directory is not empty. Remove action aborted.");
		}
	}
	
	
	/**
	 * Borra un archivo
	 * @param file fichero a borrar
	 * @throws IOException
	 */
	private static void delete(File file)
	    	throws IOException{
	 
	    	if(file.isDirectory()){
	 
	    		//directory is empty, then delete it
	    		if(file.list().length==0){
	 
	    		   file.delete();
	    		   System.out.println("Directory is deleted : " 
	                                                 + file.getAbsolutePath());
	 
	    		}else{
	 
	    		   //list all the directory contents
	        	   String files[] = file.list();
	 
	        	   for (String temp : files) {
	        	      //construct the file structure
	        	      File fileDelete = new File(file, temp);
	 
	        	      //recursive delete
	        	     delete(fileDelete);
	        	   }
	 
	        	   //check the directory again, if empty then delete it
	        	   if(file.list().length==0){
	           	     file.delete();
	        	     System.out.println("Directory is deleted : " 
	                                                  + file.getAbsolutePath());
	        	   }
	    		}
	 
	    	}else{
	    		//if file, then delete it
	    		file.delete();
	    		System.out.println("File is deleted : " + file.getAbsolutePath());
	    	}
	    }
	
	/**
	 * Copia un directorio
	 * @param srcFolder fichero fuente
	 * @param destFolder fichero destino
	 */
	public static void cpdir(File srcFolder, File destFolder){
		//make sure source exists
		if(!srcFolder.exists()){

			System.out.println("Directory does not exist.");
			//just exit
			System.exit(0);

		}else{

			try{
				copyFolder(srcFolder,destFolder);
			}catch(IOException e){
				e.printStackTrace();
				//error, just exit
				System.exit(0);
			}
		}

		System.out.println("Done");
	}
	
	/**
	 * Copia un fichero
	 * @param src fichero funete
	 * @param dest fichero destino
	 * @throws IOException
	 */
	private static void copyFolder(File src, File dest)
	    	throws IOException{
	 
	    	if(src.isDirectory()){
	 
	    		//if directory not exists, create it
	    		if(!dest.exists()){
	    		   dest.mkdir();
	    		   System.out.println("Directory copied from " 
	                              + src + "  to " + dest);
	    		}
	 
	    		//list all the directory contents
	    		String files[] = src.list();
	 
	    		for (String file : files) {
	    		   //construct the src and dest file structure
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	    		   //recursive copy
	    		   copyFolder(srcFile,destFile);
	    		}
	 
	    	}else{
	    		//if file, then copy it
	    		//Use bytes stream to support all file types
	    		InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(dest); 
	 
	    	        byte[] buffer = new byte[1024];
	 
	    	        int length;
	    	        //copy the file content in bytes 
	    	        while ((length = in.read(buffer)) > 0){
	    	    	   out.write(buffer, 0, length);
	    	        }
	 
	    	        in.close();
	    	        out.close();
	    	        System.out.println("File copied from " + src + " to " + dest);
	    	}
	    }
	
}
