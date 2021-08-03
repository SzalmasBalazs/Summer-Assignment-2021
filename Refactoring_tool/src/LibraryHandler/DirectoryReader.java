/*
 * DirectoryReader.java
 * 
 * Version 0.2.1
 *
 * 2021.08.03
 * 
 */


 package LibraryHandler;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class DirectoryReader {

    /*This list will contain the file names and where they are*/
    private static List<Path> paths = new ArrayList<>();

    /*The file extension(s) we're looking for */
    private static String extensionToInclude = ".java";

   /**
    *   The method walk, "walks" recursively along the 
        path its given, searching for files with the included extension(s).
    * @param path
    * @return
    * @throws IOException
    */

    public static List<Path> walk(Path path)throws IOException { 

    System.out.println("Reading directory for files with the extension: "+extensionToInclude );

        try(DirectoryStream<Path> ds = Files.newDirectoryStream(path)){

         for(Path file : ds){
             if (Files.isDirectory(file)) {

             walk(file);   
         }
         if(accept(file))
            {
            paths.add(file);
            }
          }

          if(checkIfPathsEmpty(paths)){
          }
          
          return paths;
        }
    }
    /**
     * Lists the files with the included extension(s)
     * @param path
     */

    public static void listDirectoryContents(List<Path> path){

        System.out.println("The following files have been found: ");
        System.out.println();

        for(Path temp : path){
            System.out.println(temp);
        }
    }

    /**
     * Checks if the file has the appropriate extension
     * @param path
     * @return
     */

    public static boolean accept(Path path){
      
        if(path.getFileName().toString().endsWith(extensionToInclude)){
            return true;
        }
        return false;

    }

     /**
      * Checks if "paths" is empty and notifies the user if so.
      * @param path
      * @return
      */
    public static boolean checkIfPathsEmpty(List<Path> path){

       if(paths.size() == 0){
        System.out.println("There are no files with the searched extension");
       }
       return false; 
    }

}
