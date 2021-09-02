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
			RefactoringHelpTools.callYalmPrinter(path);
			boolean ok = true;
			
		while(ok == true) {
			System.out.println("Please choose wich refactoring you would like to preform: "
					+ "\n 1. Rename a method name"
					+ "\n 2. Remove comments (excluding JavaDoc comments.)"
					+ "\n 3. Add JavaDoc comments for all methods."
					+ "\n 4. Remove JavaDoc comments from all methods."
					+ "\n 8. Exit");
			
				int num = RefactoringHelpTools.readInt(1,8);
		switch(num) {
		
		case(1): {
			RenameMethodDeclaration.callMethodRenamingInit(path);
			
			break;
				}
		case(2): {
			
			System.out.println(CommentRefactoring.PerfromCommentRemoval(path));
			
			break;
				}
		case(3): {
			
			System.out.println(CommentRefactoring.AddJavaDocCommentsForAllMethods(path));
			
			break;
		
				}
		case(4): {
			
			System.out.println(CommentRefactoring.RemoveJavaDocCommentsForAllMethods(path));
			
			break;
		}
		case(8): {
			
			ok = false;
			
			break;
		
		
					}
				}
			}
		}
	}
