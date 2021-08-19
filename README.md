# Summer-Assignment-2021
Repository for my summer assignment.

Project title: Simple refactoring tool

Concept: Making a Java compatible tool that is capable of doing
simple refactorings automatically.

How to Use:

-Add a .java file to the sample_files folder.
-Run the RefactoringToolExecutable.java as a Java application.
-The app also outputs a .dot file with the AST of the file(s) that are being refactored, this can be (as of 08.19.21)
 found in the root directory of the app. With the help of GraphViz you can turn this file into a graph.
-GraphViz command: dot -Tpng ast.dot > ast.png
  
