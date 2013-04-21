package directorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Clase Directorio
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 * 
 * Entrega la clase Directorios que permita el manejo de directorios de un sistema de ficheros.
 * Para ello utiliza los siguientes enlaces:
 * Java I/O Tutorial
 * Ficheros (página 101 de Java, Jorge Sánchez)
 * API de Java 6 SE
 * Para ello has de utilizar la clase File.
 * 
 */
public class Directorio {
	
	/**
	 * Optiene la dirección del directorio de trabajo actual
	 * @return String
	 */
	public static String getWorkingDirecctory(){
		return System.getProperty("user.dir");
	}
	
	/**
	 * Comprueba si es un directorio
	 * @param dir
	 * @return int 1 no está vacío; 0 está vacío; -1 no es un directorio 
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
	 * Muestra y lista un directorio y sus archivos
	 * @param node
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
	 * @param folder
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
	 * @param folders
	 */
	public static void mkdirs(File dirs){
		if (dirs.exists()) {
			if (dirs.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
	}
	
	/**
	 * Borra un directorio
	 * @param dir
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
	 * Borra un directorio si está vacío
	 * @param dir
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
	 * @param file
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
	 * @param srcFolder
	 * @param destFolder
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
	 * Conpia un fichero
	 * @param src
	 * @param dest
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
