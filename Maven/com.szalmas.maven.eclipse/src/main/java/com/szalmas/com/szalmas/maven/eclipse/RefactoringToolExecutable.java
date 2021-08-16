package com.szalmas.com.szalmas.maven.eclipse;

/*
 * DirReaderExec.java
 * 
 * Version 0.2.5
 *
 * 2021.08.03
 * 
 */

	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.util.List;

	import com.szalmas.com.szalmas.maven.eclipse.LibraryHandler.*;
	

	public class RefactoringToolExecutable {
		public static void main(String[] args) throws Exception {

        /*Relative directory pathing.
        First, the currentDirectory variable gets the path of the current directory,
        thanks to this, the directory path needs not to be manually changed.
        */
        Path currentDirectory = Paths.get("/src");
        System.out.println(currentDirectory);
        /*The newPath variable contais the path to the folder
          with the test files for now. */ 
        Path newPath = Paths.get("/src/test_files");

        /*relativize makes a path form the current directory to the new path.*/
        Path currentDirToNewPath = currentDirectory.relativize(newPath);
      
        List<Path> paths = DirectoryReader.walk(currentDirToNewPath.toAbsolutePath());
        DirectoryReader.listDirectoryContents(paths);
        
        for(Path ds : paths) {
        	methodParser.MethodCallPrinter(ds);
        }
        
        /*readFiles reads the files from the directory onto a String list,
          that we're going to manipulate. */
       // List<String> fileArray = fileReader.readFiles(paths);
       // fileReader.peekFiles(fileArray);
        
        
        
    }
    
}

