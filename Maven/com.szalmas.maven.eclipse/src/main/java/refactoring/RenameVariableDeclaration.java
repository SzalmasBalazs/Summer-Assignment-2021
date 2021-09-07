package refactoring;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

import com.github.javaparser.StaticJavaParser;


public class RenameVariableDeclaration {

	public static void callVariableRenamingInit(Path path) throws IOException {

		System.out.println("Which variable would you like to rename?");
		List<VariableDeclarator> variableDeclarations = RefactoringHelpClass.findVariableDeclarationsFromFile(path);
		RefactoringHelpClass.listVariableDeclarations(variableDeclarations);
		
		String targetVariableName = lookForTargetVariableInDeclarations(variableDeclarations);
		
		
		System.out.println("What would you like to rename the variable to?");
		String newVariableName = RefactoringHelpClass.readString();
		
		String out = performVariableRenaming(targetVariableName,newVariableName,path);
		System.out.println(out);
		
	}

	private static String lookForTargetVariableInDeclarations(List<VariableDeclarator> variableDeclarations) {
		
		boolean ok = false;
		String returnString = "";
		
		do {
			
			returnString = RefactoringHelpClass.readString();
			VariableDeclarator targetVariable = null;
			for(VariableDeclarator V : variableDeclarations) {
				
				if(V.getNameAsString().equals(returnString)) {
					
					targetVariable = V;
					
				}				
			}
			if(variableDeclarations.contains(targetVariable)) {
				
				ok = true;
				
				} else {
				
					System.out.println("The chosen variable cannot be found, please try again.");
				
						}
			
			}while(!ok);
		
		return returnString;
	}

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
