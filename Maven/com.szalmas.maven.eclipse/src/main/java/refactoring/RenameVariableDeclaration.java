package refactoring;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

import exceptions.RefactoringException;

import com.github.javaparser.StaticJavaParser;


public class RenameVariableDeclaration {

	
	/**
     * Checks if the target variable name can be found within the Variable declarations, if yes renames all instances of the variable
     * 
     * @return
     * @param path
     * @param targetVariableName
     * @param newVariableName
     * @throws IOException
     * @thorws RefactoringException
     */
	
	public static void renameVariable(Path path, List<VariableDeclarator> variableDeclarations, String targetVariableName, String newVariableName) throws IOException, RefactoringException {

		String validatedTargetVariableName = lookForTargetVariableInDeclarations(variableDeclarations,targetVariableName);
		String out = performVariableRenaming(validatedTargetVariableName,newVariableName,path);
		System.out.println(out);
		
	}
	
	/**
     * Checks if the target variable name can be found within the Variable declarations, if yes it returns the variable name, if not it throws an exception.
     * 
     * @return
     * @param variableDeclarations
     * @param targetVariableName
     * @thorws RefactoringException
     */
	
	
	private static String lookForTargetVariableInDeclarations(List<VariableDeclarator> variableDeclarations,String targetVariableName)throws RefactoringException {
		
		
			VariableDeclarator targetVariable = null;
			for(VariableDeclarator V : variableDeclarations) {
				
				if(V.getNameAsString().equals(targetVariableName)) {
					
					targetVariable = V;
					
				}				
			}
			if(variableDeclarations.contains(targetVariable)) {
				
				return targetVariableName;
				
				} else {
				
					throw new RefactoringException("The chosen variable cannot be found.");
				
						}
	}
	/**
     * Renames all declarations of target variable to the new variable name.
     * 
     * @return
     * @param newVariableName
     * @param targetVariableName
     * @param path
     * @thorws IOException
     */
	
	
	private static String performVariableRenaming(String targetVariableName, String newVariableName,Path path) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		List<VariableDeclarator> variableDeclarators = cu.findAll(VariableDeclarator.class);
		List<MethodCallExpr> methodCalls = cu.findAll(MethodCallExpr.class);
		for(VariableDeclarator V: variableDeclarators) {
			
			if(V.getNameAsString().equals(targetVariableName)) {
			
				V.setName(newVariableName);
				
			}
			
		}
		for(MethodCallExpr methodCall : methodCalls) {
			
			methodCall.getArguments();
			List<Expression> arguments = methodCall.getArguments();
			for(Expression a : arguments) {
			
				if(a.isNameExpr() && a.asNameExpr().getNameAsString().equals(targetVariableName)) {
					
					a.asNameExpr().setName(newVariableName);
					
				}
				
			}
				
		}
		RefactoringHelpClass.writeOut(cu, path);
		return "Renamed all instances of "+targetVariableName+" to : "+newVariableName;
	}
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
