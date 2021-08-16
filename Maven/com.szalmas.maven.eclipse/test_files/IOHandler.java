/*
 * DirReaderExec.java
 * 
 * Version 0.1.0
 *
 * 2021.08.03
 * 
 * This class contains all the IO methods, used to interact with the user.
 */

package LibraryHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IOHandler {
    
    private static int ceiling = 3;
    private static int floor =0;


        /**
         * Reads an integer within a range of a floor and a ceiling value wich
         * represent the number of available refactorings.
         * @return
         */
        public static int readInt(){

            boolean ok = false; 
                 int num = 0;
                 InputStreamReader inputStream = new InputStreamReader(System.in);
                 BufferedReader reader = new BufferedReader(inputStream);
     
                     do{

                         try {
                                num = Integer.valueOf(reader.readLine());
                                 if(num <= getCeiling()  && num >= getFloor()){
                                      ok = true; 
                  
                                  }  else{
                                System.out.println("Invalid input. You must choose from the available options (1-3)");
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
     * Collection of setters and getters for this class. 
     * @return
     */

    public static int  getCeiling() {
        return ceiling;
    }

    public static void setCeiling(int ceiling) {
        IOHandler.ceiling = ceiling;
    }

    public static int getFloor() {
        return floor;
    }

    public static void setFloor(int floor) {
        IOHandler.floor = floor;
    }

}
