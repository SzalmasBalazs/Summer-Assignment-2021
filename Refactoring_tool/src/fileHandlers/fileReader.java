/*
 * DirectoryReader.java
 * 
 * Version 0.1.0
 *
 * 2021.07.31
 * 
 */
package fileHandlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class fileReader {
    
public static List<String> returnArray = new ArrayList<>();

/**
 * Reads the file(s) from the designated folder onto an array.
 * 
 * @param paths
 * @return 
 * @throws IOException
 */

public static List<String> readFiles(List<Path> paths) throws IOException {

    for(Path ds : paths){
    returnArray = Files.readAllLines(ds);
    }

    return returnArray;
}

/**
 * peekFiles allows the user to see if the file array
 * has the data from the directory files.
 * (Temporary method, will probably be removed when done.)
 * 
 * @param fileArray
 */

public static void peekFiles(List<String> fileArray) {

    for(String temp : fileArray){
        System.out.println(temp);
      }
}

}
