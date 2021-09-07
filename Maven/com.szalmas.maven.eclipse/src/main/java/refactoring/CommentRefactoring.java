package refactoring;

	import java.io.IOException;
	import java.nio.file.Path;

	import java.util.List;

	import com.github.javaparser.StaticJavaParser;
	import com.github.javaparser.ast.CompilationUnit;
	import com.github.javaparser.ast.body.MethodDeclaration;
	import com.github.javaparser.ast.comments.Comment;

	import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;



public class CommentRefactoring {
	
	public static String perfromCommentRemoval (Path path) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		
		List<Comment> commentsInFile = cu.getAllComments();
		
		for(Comment comment : commentsInFile) {
					
				if(comment.isLineComment()) {
					
					comment.remove();
				
				} else if (comment.isBlockComment()) {
					
					comment.remove();
					
				}
				
			}
			
		
		RefactoringHelpClass.writeOut(cu, path);
		
		
		return ("Removed commented out code.");
	}
	
	public static String addJavaDocCommentsForAllMethods(Path path) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		
		List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
		
		for(MethodDeclaration method : methods) {
			
			if(method.hasJavaDocComment() == false) {
							
				method.setJavadocComment("\r\n         * Auto-generated Javadoc for "+method.getNameAsString()+"\r\n         *\r\n         * @return\r\n         * @param "+method.getParameters()+"\r\n");

			}

		}
		RefactoringHelpClass.writeOut(cu, path);
		return ("Added JavaDoc comment for all methods.");
		
	}
	public static String removeJavaDocCommentsForAllMethods(Path path) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		
		List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
		
		for(MethodDeclaration method : methods) {
			
			if(method.hasJavaDocComment()) {
							
				method.removeJavaDocComment();
			
				
			}
			
			
			
		}
		RefactoringHelpClass.writeOut(cu, path);
		
		
		return ("Removed JavaDoc comment for all methods.");
	}
	

}