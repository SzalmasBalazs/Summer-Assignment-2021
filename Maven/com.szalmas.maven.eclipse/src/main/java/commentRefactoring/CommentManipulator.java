package commentRefactoring;

	import java.io.IOException;
	import java.nio.file.Path;

	import java.util.List;

	import com.github.javaparser.StaticJavaParser;
	import com.github.javaparser.ast.CompilationUnit;
	import com.github.javaparser.ast.body.MethodDeclaration;
	import com.github.javaparser.ast.comments.Comment;

	import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

import refactoring.RefactoringHelper;



public class CommentManipulator {
	
	 /**
     *	Removes all non-JavaDoc comments from a file.
     * 
     * @return
     * @param path
     * @throws IOException
     */
	public static String removeAllComments (Path path) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		
		List<Comment> commentsInFile = cu.getAllComments();
		
		for(Comment comment : commentsInFile) {
					
				if(comment.isLineComment()) {
					
					comment.remove();
				
				} else if (comment.isBlockComment()) {
					
					comment.remove();
					
				}
				
			}
			
		
		RefactoringHelper.writeOut(cu, path);
		
		
		return ("Removed commented out code.");
	}
	
	 /**
     * Adds auto-generated JavaDoc comments to all methods that do not have one already.
     * 
     * @return
     * @param path
     * @throws IOException
     */
	
	
	public static String addJavaDocCommentsForAllMethods(Path path) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		
		List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
		
		for(MethodDeclaration method : methods) {
			
			if(method.hasJavaDocComment() == false) {
							
				method.setJavadocComment("\r\n         * Auto-generated Javadoc for "+method.getNameAsString()+"\r\n         *\r\n         * @return\r\n         * @param "+method.getParameters()+"\r\n");

			}

		}
		RefactoringHelper.writeOut(cu, path);
		return ("Added JavaDoc comment for all methods.");
		
	}
	
	 /**
     * Removes all JavaDoc comments from a file
     * 
     * @return
     * @param path
     * @throws IOException
     */
	
	public static String removeJavaDocCommentsForAllMethods(Path path) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		
		List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
		
		for(MethodDeclaration method : methods) {
			
			if(method.hasJavaDocComment()) {
							
				method.removeJavaDocComment();
			
				
			}
			
			
			
		}
		RefactoringHelper.writeOut(cu, path);
		
		
		return ("Removed JavaDoc comment for all methods.");
	}
	

}