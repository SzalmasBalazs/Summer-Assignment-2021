/*
 * DirReaderExec.java
 * 
 * Version 0.1.0
 *
 * 2021.08.03
 * 
 * This class will handle all 3 of the refactorings and a renaming refactoring.
 */

package LibraryHandler;

import java.util.List;
import java.util.ListIterator;

public class RefactoringMethods {

    /**
     * NOT FUNCTIONAL AS OF V 0.1.0, NEED TO ASK FOR ADVICE ON HOW TO MANIPULATE LISTS.
     * @param fileArray
     * @return
     */
    public static List<String> renameRefactoring(List<String> fileArray) {

        String varToRefactor = selectVarToRename();
        String newVar = varToRefactorTo(varToRefactor);

        ListIterator<String> iterator = fileArray.listIterator();
        while(iterator.hasNext()){

            if(iterator.next().equalsIgnoreCase(varToRefactor)){
                iterator.set(newVar);
            }
      
    }
        while(iterator.hasNext()){

            System.out.println(iterator.next());;
  
        }
        return fileArray;
    }

    private static String selectVarToRename() {

        System.out.println("Please name the variable you would like to rename: ");

        return IOHandler.readString();

    }

    private static String varToRefactorTo(String varToRefactor) {
       
        System.out.println("Please instert what to rename the variable "+ varToRefactor +" to: ");

        return IOHandler.readString();

    }
}
