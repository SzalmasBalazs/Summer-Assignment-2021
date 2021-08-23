package com.szalmas.com.szalmas.maven.eclipse;

import java.io.IOException;


		import java.nio.file.Path;
		import java.nio.file.Paths;
		import java.util.List;

		import exceptions.RefactoringException;
		import refactoring.*;
	

	public class RefactoringToolExecutable {
		public static void main(String[] args) throws Exception {

        /*Relative directory pathing.
        First, the currentDirectory variable gets the path of the current directory,
        thanks to this, the directory path needs not to be manually changed.
        */
        Path rootDirectory = Paths.get("/src");
        RefactoringHelpTools.configureJavaParserForProject(rootDirectory);
        System.out.println(rootDirectory);
        /*The newPath variable contais the path to the folder
          with the test files for now. */ 
        Path newPath = Paths.get("/src/sample_files");

        /*relativize makes a path form the current directory to the new path.*/
        Path currentDirToNewPath = rootDirectory.relativize(newPath);
        
        List<Path> paths = DirectoryReader.walk(currentDirToNewPath.toAbsolutePath());
        DirectoryReader.listDirectoryContents(paths);
        
        for(Path ds : paths) {
        RefactoringHelpTools.callDotPrinter(ds);
        }
        
        for(Path ds : paths) {
        	callUserInput(ds,rootDirectory);
        }
        
        
        
    }
		
		/**
		 * Calls refactoring methods based on user input.
		 * 
		 * 
		 * @param path
		 * @param rootDirectory
		 * @throws IOException
		 * @throws RefactoringException
		 */
		public static void callUserInput(Path path, Path rootDirectory) throws IOException, RefactoringException {
			
			System.out.println("Please choose wich refactoring you would like to preform: "
					+ "\n 1. Rename a method name"
					+ "\n 2. Extract Method");
				int num = RefactoringHelpTools.readInt(1,2);
		switch(num) {
		
		case(1): {
			RenameRefactoring.callMethodRenamingInit(path);
			
			break;
		}case(2): {
			
			System.out.println("This feature is currently unavailable.");
			
			break;
		}
		
		}
		}
	}

