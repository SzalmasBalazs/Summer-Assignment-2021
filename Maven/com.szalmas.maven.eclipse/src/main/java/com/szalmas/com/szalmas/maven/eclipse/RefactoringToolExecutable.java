package com.szalmas.com.szalmas.maven.eclipse;

import java.io.IOException;


		import java.nio.file.Path;
		import java.nio.file.Paths;
		import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import commentRefactoring.CommentManipulator;
import exceptions.RefactoringException;
		import refactoring.*;
import renameRefactoring.MethodDeclarationRenamer;
import renameRefactoring.VariableDeclarationRenamer;
	

	public class RefactoringToolExecutable {
		public static void main(String[] args) throws Exception {

        /*Relative directory pathing.
        First, the currentDirectory variable gets the path of the current directory,
        thanks to this, the directory path needs not to be manually changed.
        */
        Path rootDirectory = Paths.get("/src");
        RefactoringHelper.configureJavaParserForProject(rootDirectory);
        System.out.println(rootDirectory);
        /*The newPath variable contais the path to the folder
          with the test files for now. */ 
        Path newPath = Paths.get("/src/sample_files");

        /*relativize makes a path form the current directory to the new path.*/
        Path currentDirToNewPath = rootDirectory.relativize(newPath);
        
        List<Path> paths = DirectoryReader.walk(currentDirToNewPath.toAbsolutePath());
        DirectoryReader.listDirectoryContents(paths);
        
        for(Path ds : paths) {
        RefactoringHelper.writeTreeToDotFile(ds);
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
		//	RefactoringHelpTools.callYalmPrinter(path);
			boolean ok = true;
			
		while(ok == true) {
			System.out.println("Please choose wich refactoring you would like to preform: "
					+ "\n 1. Rename a method name"
					+ "\n 2. Remove comments (excluding JavaDoc comments.)"
					+ "\n 3. Add JavaDoc comments for all methods."
					+ "\n 4. Remove JavaDoc comments from all methods."
					+ "\n 5. Rename variable."
					+ "\n 8. Exit");
			
				int num = RefactoringHelper.readInt(1,8);
		switch(num) {
		
		case(1): {
			
			System.out.println("Which method would you like to rename?");
			
			List<ClassOrInterfaceDeclaration> classAndOrInterfaces = RefactoringHelper.FindClassesOrInterfaces(path);	
			RefactoringHelper.listMethods(classAndOrInterfaces);
			
				String targetMethodName = RefactoringHelper.readString();
					
				System.out.println("What would you like to rename the method to?");
				String newMethodName = RefactoringHelper.readString();
				MethodDeclarationRenamer.RenameMethod(path,targetMethodName,newMethodName);
			break;
				}
		case(2): {
			
			System.out.println(CommentManipulator.removeAllComments(path));
			
			break;
				}
		case(3): {
			
			System.out.println(CommentManipulator.addJavaDocCommentsForAllMethods(path));
			
			break;
		
				}
		case(4): {
			
			System.out.println(CommentManipulator.removeJavaDocCommentsForAllMethods(path));
			
			break;
		}
		case(5): {
			
			System.out.println("Which variable would you like to rename?");
			List<VariableDeclarator> variableDeclarations = RefactoringHelper.findVariableDeclarationsFromFile(path);
			RefactoringHelper.listVariableDeclarations(variableDeclarations);
			String targetVariableName = RefactoringHelper.readString();
			
			System.out.println("What would you like to rename the variable to?");
			String newVariableName = RefactoringHelper.readString();
			
			VariableDeclarationRenamer.renameVariable(path,variableDeclarations,targetVariableName,newVariableName);
			
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
