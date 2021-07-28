
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import directoryList.DirectoryList;


public class App {
    public static void main(String[] args) throws Exception {

        //Directory Path needs to be changed accordingly
        Path directory = Paths.get("C:\\Users\\szalm\\Documents\\GitHub\\Summer-Assignment-2021\\Refactoring_tool\\test_files");

        List<Path> paths = DirectoryList.walk(directory);
        DirectoryList.listDirectoryContents(paths);
        
        
    }

}
