package directoryList;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class DirectoryList {

    private static List<Path> paths = new ArrayList<>();
    private static String extensionToInclude = ".java";

    public static List<Path> walk(Path path)throws IOException { 

        try(DirectoryStream<Path> ds = Files.newDirectoryStream(path)){

         for(Path file: ds){
             if (Files.isDirectory(file)) {

             walk(file);   
         }
         if(accept(file))
            {
            paths.add(file);
            }
          }
          if(checkIfPathsEmpty(paths)){
            System.out.println("There are no files with the searched extension");
          }
          return paths;
        }
    }

    public static void listDirectoryContents(List<Path> path){
        for(Path temp : path){
            System.out.println(temp);
        }
    }

    public static boolean accept(Path path){
      

        if(path.getFileName().toString().endsWith(extensionToInclude)){
            return true;
        }
        return false;

    }

    public static boolean checkIfPathsEmpty(List<Path> path){

       if(paths.size() == 0){
        return true;
       }
       return false; 
    }

}
