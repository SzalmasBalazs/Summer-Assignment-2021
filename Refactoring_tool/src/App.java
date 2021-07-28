
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import directoryList.DirectoryList;


public class App {
    public static void main(String[] args) throws Exception {

        Path directory = Paths.get("D:\\Summer_Assigment\\Refactoring_tool\\test_files");
        List<Path> paths = DirectoryList.walk(directory);
        DirectoryList.listDirectoryContents(paths);

    }

}
