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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ListIterator;
import refactoring;

public class RefactoringMethods {

    /**
     * NOT FUNCTIONAL AS OF V 0.1.0, NEED TO ASK FOR ADVICE ON HOW TO MANIPULATE LIST ITEMS.
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

        return readString();

    }

    private static String varToRefactorTo(String varToRefactor) {
       
        System.out.println("Please instert what to rename the variable "+ varToRefactor +" to: ");

        return readString();

    }
    /**
     * Reads an integer within a range of a floor and a ceiling.
     * 
     * @return
     */
    public static int readInt(int floor, int ceiling){

        boolean ok = false; 
             int num = 0;
             InputStreamReader inputStream = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(inputStream);
 
                 do{

                     try {
                            num = Integer.valueOf(reader.readLine());
                             if(num <= ceiling && num >= floor){
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
}








































