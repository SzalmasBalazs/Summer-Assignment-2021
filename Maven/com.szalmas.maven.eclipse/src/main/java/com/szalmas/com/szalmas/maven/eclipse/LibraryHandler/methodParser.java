package com.szalmas.com.szalmas.maven.eclipse.LibraryHandler;


	import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.StaticJavaParser;
	import com.github.javaparser.printer.DotPrinter;
	import com.github.javaparser.printer.YamlPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;


public class methodParser {

	
	@SuppressWarnings("unchecked")
	public static void MethodCallPrinter (Path path) throws IOException {
	
		CompilationUnit cu = StaticJavaParser.parse(path);
		
		/*YamlPrinter printer = new YamlPrinter(true);
		System.out.println(printer.output(cu));
		*/
		
		DotPrinter printer = new DotPrinter(true);
		try(FileWriter fileWriter = new FileWriter("ast.dot");
				   PrintWriter printWriter = new PrintWriter(fileWriter)) {
					    printWriter.print(printer.output(cu));
		}
		
		walkItLikeITalkIt(cu);
	//	new myMethodVisitor().visit(cu, null);
		
		
	}
	public static void walkItLikeITalkIt (CompilationUnit cu) {
		
		Node.BreadthFirstIterator iterator = new Node.BreadthFirstIterator(cu);
        while (iterator.hasNext()) {
            System.out.println("* " + iterator.next());
        }	
		
	}
}
