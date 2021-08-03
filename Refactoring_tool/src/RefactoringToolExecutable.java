/*
 * DirReaderExec.java
 * 
 * Version 0.2.5
 *
 * 2021.08.03
 * 
 */


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import LibraryHandler.*;



public class RefactoringToolExecutable {
    public static void main(String[] args) throws Exception {

        /*Relative directory pathing.
        First, the currentDirectory variable gets the path of the current directory,
        thanks to this, the directory path needs not to be manually changed.
        */
        Path currentDirectory = Paths.get("/src");

        /*The newPath variable contais the path to the folder
          with the test files for now. */ 
        Path newPath = Paths.get("/src/test_files");

        /*relativize makes a path form the current directory to the new path.*/
        Path currentDirToNewPath = currentDirectory.relativize(newPath);
      
        List<Path> paths = DirectoryReader.walk(currentDirToNewPath.toAbsolutePath());
        DirectoryReader.listDirectoryContents(paths);
        
        /*readFiles reads the files from the directory onto a String list,
          that we're going to manipulate. */
        List<String> fileArray = fileReader.readFiles(paths);
       // fileReader.peekFiles(fileArray);

       fileArray = userInput(fileArray);


    }
    /**
     * Calls an IO method, asking the user, what kind of refactoring 
     * they would like to do via numbers,
     * then calls the appropriate refactoring method (wich are still WIP).
     *
     * 
     * @param fileArray
     * @return 
     */

    private static List<String> userInput(List<String> fileArray) {

      int num = chooseRefactoringMethod();

      switch (num) {
        case 1:
          callRenameRefactoring(fileArray);
          
          break;

        case 0:
        System.out.println("No changes we're made.");
         return fileArray;
        
        default:
          break;
      }

      return fileArray;
    }

    /**
     * Extracted method from userInput.
     * @return
     */
    private static int chooseRefactoringMethod() {

        System.out.println("Please select from the available refactoring methods:");
        System.out.println("1. Rename Variable(s)");

          int num = IOHandler.readInt();

      return num;

    }

    /***
     * Extracted method from userInput for easier understanding of the code.
     * @param fileArray
     * @return
     */
    private static List<String> callRenameRefactoring(List<String> fileArray) {

      return fileArray = RefactoringMethods.renameRefactoring(fileArray);

    }
    
}
