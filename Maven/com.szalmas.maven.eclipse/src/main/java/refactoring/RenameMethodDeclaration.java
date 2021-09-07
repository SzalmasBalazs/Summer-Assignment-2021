package refactoring;


	import com.github.javaparser.StaticJavaParser;
	import com.github.javaparser.ast.CompilationUnit;
	import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
	import com.github.javaparser.ast.body.MethodDeclaration;
	import com.github.javaparser.ast.expr.MethodCallExpr;
	import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;



	import java.io.IOException;
	import java.nio.file.Path;
	import java.util.ArrayList;
	import java.util.List;
	
	import exceptions.RefactoringException;
	
/**
 * Automatic Method renaming.
 * 
 * Takes the users choice of method, then renames it and all 
 * method calls to the chosen name.
 * 
 * 
 * @author szalm
 *
 */
	
	

public class RenameMethodDeclaration {
	
	 
	
	private static List<MethodDeclaration> refactoringRelevantMethods = new ArrayList<>();
	
	/**
	 * Asks the user which method they would like to rename and what to rename it to,
	 * initializing the refactoring.
	 * 
	 * 
	 * @param path
	 * @throws IOException
	 * @throws RefactoringException
	 */
	
	public static void callMethodRenamingInit(Path path) throws IOException, RefactoringException {
		
		System.out.println("Which method would you like to rename?");
		
		List<ClassOrInterfaceDeclaration> classAndOrInterfaces = RefactoringHelpClass.FindClassesOrInterfaces(path);	
		RefactoringHelpClass.listMethods(classAndOrInterfaces);
		
			String targetMethodName = RefactoringHelpClass.readString();
				
			System.out.println("What would you like to rename the method to?");
			String newMethodName = RefactoringHelpClass.readString();
				
			MethodDeclaration targetMethod = findAndValidateMethod(classAndOrInterfaces,targetMethodName,newMethodName,path);
			for(MethodDeclaration d: refactoringRelevantMethods) {	
				System.out.println(d.getNameAsString() +" , "+d.getDeclarationAsString());
			}
			System.out.println("\n");
		String out = performRefactoring(targetMethod,targetMethodName,classAndOrInterfaces,path,newMethodName);
		System.out.println(out);
				
	}
	
	/**
	 * Performs the method declaration and call renaming.
	 * 
	 * @param targetMethod
	 * @param targetMethodName
	 * @param classAndOrInterfaces
	 * @param path
	 * @param newMethodName
	 * @return
	 * @throws IOException
	 */
	private static  String performRefactoring(MethodDeclaration targetMethod, String targetMethodName, List<ClassOrInterfaceDeclaration> 
					classAndOrInterfaces,Path path, String newMethodName) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		List<MethodDeclaration> MethodDeclarations = cu.findAll(MethodDeclaration.class);
		List<MethodCallExpr> MethodCalls = cu.findAll(MethodCallExpr.class);
	
		for(MethodCallExpr methodCallExpr : MethodCalls) {
			if(methodCallExpr.getNameAsString().equals(targetMethodName)) {
				
				methodCallExpr.setName(newMethodName);
				
			
			}
		}
		
		for(MethodDeclaration methodDeclaration : MethodDeclarations) {
			if(refactoringRelevantMethods.contains(methodDeclaration)) {

				methodDeclaration.setName(newMethodName);
			}
			
		}
		
		
		RefactoringHelpClass.writeOut(cu, path);
		return "Renamed Method: "+targetMethodName +" to : "+ newMethodName;
	}


	/**
	 * finds the method declaration of the target method.
	 * 
	 * @param classAndOrInterfaces
	 * @param targetMethodName
	 * @param newMethodName
	 * @param path
	 * @return 
	 * @throws RefactoringException
	 */
	
	private static MethodDeclaration findAndValidateMethod(List<ClassOrInterfaceDeclaration> classAndOrInterfaces, 
			String targetMethodName, String newMethodName,Path path) throws RefactoringException {
		
		MethodDeclaration validTargetMethod = null;
			for(ClassOrInterfaceDeclaration classOrInterface : classAndOrInterfaces) {
				for(MethodDeclaration currentMethod :  classOrInterface.getMethods()) {
					
					if(compareMethodNames(currentMethod,targetMethodName)) {
						
						validTargetMethod = currentMethod;
						refactoringRelevantMethods.add(currentMethod);
						break;
					}
						if(validTargetMethod!= null) {
							break;
						}
					}
				
			}
			
			/*
			 * Checks if the new method name given by the user matches 
			 * the current methods name, if so throws an exception.
			 * 
			 * */
			
		checkOldAndNewMethodName(validTargetMethod,newMethodName);
			
		return validTargetMethod;
	}
	
	
	/**Check if the validated methods name is the same as the new method name.
	 * Throws an Refactoring Exception if true.
	 * 
	 * @param validTargetMethod
	 * @param newMethodName
	 * @throws RefactoringExcapetion
	 */
	private static void checkOldAndNewMethodName(MethodDeclaration validTargetMethod, String newMethodName) 
												throws RefactoringException {
		
		if(compareMethodNames(validTargetMethod,newMethodName)) {
			
			throw new RefactoringException("New Method name cannot be the old one!");
			
		}
		
	}

	/**
	 * Compares the names of two method declarations.
	 * 
	 * Returns true if they match.
	 * @param currentMethod
	 * @param targetMethodName
	 * @return
	 */
	private static boolean compareMethodNames(MethodDeclaration currentMethod,
			String targetMethodName) {
		
				if(currentMethod.getNameAsString().contentEquals(targetMethodName)) {
					return true;
				}
				
		return false;
	}
}