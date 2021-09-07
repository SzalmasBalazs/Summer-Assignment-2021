/*
 * DirReaderExec.java
 * 
 * Version 0.1.0
 *
 * 2021.08.03
 * 
 * This class contains all the IO methods, used to interact with the user.
 */

package refactoring;

	import java.io.BufferedReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.nio.file.Path;
	import java.util.List;

	import com.github.javaparser.StaticJavaParser;
	import com.github.javaparser.ast.CompilationUnit;
	import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
	import com.github.javaparser.ast.body.MethodDeclaration;
	import com.github.javaparser.ast.body.VariableDeclarator;
	import com.github.javaparser.ast.expr.MethodCallExpr;

	import com.github.javaparser.printer.DotPrinter;
	import com.github.javaparser.printer.YamlPrinter;
	import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
	import com.github.javaparser.symbolsolver.JavaSymbolSolver;
	import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
	import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
	import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

	
/**
 * A set of methods that I would have to call often enough that I 
 * justified making a class of them.
 * 
 * @author Szalmás Balázs
 *
 */
	
public class RefactoringHelpClass {
	
        /**
         * Reads an integer from the user within a range of a minValue. and a maxValue.
         * 
         * @return
         */
        public static int readInt(int minValue, int maxValue){

            boolean ok = false; 
                 int num = 0;
                 InputStreamReader inputStream = new InputStreamReader(System.in);
                 BufferedReader reader = new BufferedReader(inputStream);
     
                     do{

                         try {
                                num = Integer.valueOf(reader.readLine());
                                 if(num <= maxValue && num >= minValue){
                                      ok = true; 
                  
                                  }  else{
                                System.out.println("Invalid input. You must choose from the available options (1 or 2)");
                                  }
                             
                             } catch (Exception e) {
                                e.getMessage();
                             }

                         }while(!ok);

            return num;

         }
    
         /**
          * Reads a string from the user.
          * @return
          */
        public static String readString(){

        	boolean ok = false; 
        		String returnString = "";
        		InputStreamReader inputStream = new InputStreamReader(System.in);
        		BufferedReader reader = new BufferedReader(inputStream);
     
        		do{

        			try {
                    
                        returnString = reader.readLine();
                        ok = true;

        				} catch (Exception e) {
        				e.printStackTrace();
        				}

        			}while(!ok);


        return returnString;
    }
      /**
       * Gets all class and interface declarations from all files on the path.
       * 
       * 
       * @param path
       * @return
       * @throws IOException
       */
      public static List<ClassOrInterfaceDeclaration> FindClassesOrInterfaces(Path path) throws IOException {
    	  
    	  CompilationUnit cu = StaticJavaParser.parse(path);
    	  
		return cu.findAll(ClassOrInterfaceDeclaration.class);
   	    	  
      }
      /**
       * Lists all class and interface declarations within a list.
       * 
       * 
       * @param list
       */
      
      public static void listClassOrInterfaceDeclarations(List<ClassOrInterfaceDeclaration> list) {
    	  for(ClassOrInterfaceDeclaration n : list) {
  			
  			System.out.println(n.getNameAsString());
  			
  		}
      }
      
      /**
       * Lists all methods from a list of classes and interfaces.
       * 
       * @param list
       */
      public static void listMethods(List<ClassOrInterfaceDeclaration> list) {
    	 
    	  for(ClassOrInterfaceDeclaration classOrInterface : list) {
    		  for(MethodDeclaration method : classOrInterface.getMethods()) {
    		  
    			  System.out.println(method.getNameAsString());
    			  
    		  }
    	  }
    	  
    	  
      }
      /**
       * Gets all method declarations from a file.
       * 
       * @param path
       * @return
       * @throws IOException
       */
      public static List<MethodDeclaration> findMethodDeclarations(Path path) throws IOException {
    	  
    	  CompilationUnit cu = StaticJavaParser.parse(path);
    	  
		return cu.findAll(MethodDeclaration.class);
   	    	  
      }
     
 

	/**
      * Gets all method call expressions from a file
      * 
      * @param path
      * @return
      * @throws IOException
      */
  	public static List<MethodCallExpr> findMethodCallExpr(Path path) throws IOException {
	  
	  CompilationUnit cu = StaticJavaParser.parse(path);
	  
	return cu.findAll(MethodCallExpr.class);
	    	  
  }
  	
  	
  	public static void listVariableDeclarations(List<VariableDeclarator> list){
  		
  		for(VariableDeclarator V : list) {
  			
  			System.out.println(V.getNameAsString());
  			
  		}
  		
  		

  	}
  	/**
  	 * Configures JavaParsers Type solver for the project.
  	 * 
  	 * @param path
  	 */
  	
  	public static void configureJavaParserForProject(Path path) {
		CombinedTypeSolver typeSolver = new CombinedTypeSolver();
		for (Path javaRoot : (path.getRoot())) {
			typeSolver.add(new JavaParserTypeSolver(javaRoot));
		}
		typeSolver.add(new ReflectionTypeSolver());
		JavaSymbolSolver javaSymbolSolver = new JavaSymbolSolver(typeSolver);
		StaticJavaParser.getConfiguration().setSymbolResolver(javaSymbolSolver);
	}
  	

	/**
     *	DotPrinter lets put out a dot file that contains the nodes on an AST, after this using GraphViz
     *	we are able to make a very nice graph of our AST(s).
     * @param cu
     * @throws IOException
     */
  	
  	
	@SuppressWarnings("unused")
	public static void writeTreeToDotFile(Path path) throws IOException {
		CompilationUnit cu = StaticJavaParser.parse(path);
		DotPrinter printer = new DotPrinter(true);
		try(FileWriter fileWriter = new FileWriter("ast.dot");
				   PrintWriter printWriter = new PrintWriter(fileWriter)) {
					    printWriter.print(printer.output(cu));
		}
		
	}

	/**
     *	JavaParser's printer library, YalmPrinter lets you print out the AST in your console.
     * @param cu
	 * @throws IOException 
     */
	
	@SuppressWarnings("unused")
	public static void printASTofFile(Path path) throws IOException {
		CompilationUnit cu = StaticJavaParser.parse(path);
		YamlPrinter printer = new YamlPrinter(true);
		System.out.println(printer.output(cu));
	}
	
	/**
	 * Writes the CompilationUnit onto the disk, into the sample file folder
	 * overriding the old file.
	 * 
	 * @param cu
	 * @param path
	 * @throws IOException
	 */
	
	public static void writeOut(CompilationUnit cu, Path path) throws IOException {
		
		try {
		PrintWriter out = new PrintWriter(path.toString());
		out.println(LexicalPreservingPrinter.print(cu));
		out.close();
		}catch(Exception e ) {
			e.getMessage();
		}
		
	}

	public static List<VariableDeclarator> findVariableDeclarationsFromFile(Path path) throws IOException {
		
		CompilationUnit cu = LexicalPreservingPrinter.setup(StaticJavaParser.parse(path));
		
		return cu.findAll(VariableDeclarator.class);
	}
}
