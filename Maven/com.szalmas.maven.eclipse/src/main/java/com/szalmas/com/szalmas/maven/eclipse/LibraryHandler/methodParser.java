package com.szalmas.com.szalmas.maven.eclipse.LibraryHandler;


	import com.github.javaparser.ast.CompilationUnit;
	import com.github.javaparser.ast.body.MethodDeclaration;
	import com.github.javaparser.StaticJavaParser;
	import com.github.javaparser.printer.DotPrinter;
	import com.github.javaparser.printer.YamlPrinter;
	 
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.nio.file.Path;


public class methodParser {

	
	/**
     *	Uses JavaParser to generate AST(s) from the files on the designated path.
     * @param path
     */
	public static void MethodCallPrinter (Path path) throws IOException {
	
		CompilationUnit cu = StaticJavaParser.parse(path);
		
		callYalmPrinter(cu);
		callDotPrinter(cu);
		walkTree(cu);
		
	//	new myMethodVisitor().visit(cu, null);
		
	}
	
	/**
     *	DotPrinter lets put out a dot file that contains the nodes on an AST, after this using GraphViz
     *	we are able to make a very nice graph of our AST(s).
     * @param cu
     */
	private static void callDotPrinter(CompilationUnit cu) throws IOException {
		
		DotPrinter printer = new DotPrinter(true);
		try(FileWriter fileWriter = new FileWriter("ast.dot");
				   PrintWriter printWriter = new PrintWriter(fileWriter)) {
					    printWriter.print(printer.output(cu));
		}
		
	}

	/**
     *	JavaParser's printer library, YalmPrinter lets you print out the AST in your console.
     * @param cu
     */
	private static void callYalmPrinter(CompilationUnit cu) {
		YamlPrinter printer = new YamlPrinter(true);
		System.out.println(printer.output(cu));
	}

	/**
     * walks the tree, listing all the nodes with method declarations.
     * @param cu
     */
	public static void walkTree (CompilationUnit cu) {
		
		cu.findAll(MethodDeclaration.class).forEach(node -> System.out.println("* " + node));
		
        }	
		
	}

