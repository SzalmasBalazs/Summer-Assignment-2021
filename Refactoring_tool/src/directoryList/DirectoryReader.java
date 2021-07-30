/*
 * DirectoryReader.java
 * 
 * Version 0.1.2
 *
 * 2021.07.30
 * 
 */

 package directoryList;

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
      * Check if the "paths" list is empty and notifies the use if so.
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
