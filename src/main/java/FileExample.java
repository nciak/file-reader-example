import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Norbert on 2017-09-02.
 */
public class FileExample {

    public static void main(String[] args) {

        //Java6

        String pathString = "D:\\temp\\fileexamples";
        File dir = new File(pathString);
        File fileToFile = new File(pathString + "\\test.txt");

        Path homeDir = Paths.get("D:", "temp", "fileexamples"); //or just add ,test.txt
        Path pathToFile = homeDir.resolve("test.txt");


        Path pathToNewFile = homeDir.resolve("newfile.txt");
        try(PrintWriter writer = new PrintWriter(new FileWriter(pathToNewFile.toFile(),true))) {
            writer.println("addddddd");
        } catch (IOException e) {
            e.printStackTrace();
        }


//        readAndParseNumbersFile(homeDir);
//        readFileJava7(pathToFile);
//        readFileReader(fileToFile)
//        readFileJava8();
    }

    private static void readAndParseNumbersFile(Path homeDir) {
        Path pathToNumberFile = homeDir.resolve("numbers.txt");

        try {
            List<String> numberStrList = Files.readAllLines(pathToNumberFile);
            int sum = numberStrList.stream()
                    .mapToInt(Integer::parseInt)
                    .sum();
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("Unreadable file");
            e.printStackTrace();
        }
    }

    private static void readFileJava7(Path pathToFile) {
        try (BufferedReader reader = Files.newBufferedReader(pathToFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFileReader(File fileToFile) {
        Reader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(fileToFile);
            reader = new BufferedReader(fileReader);

            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (reader != null) {
                    reader.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void readFileJava8() {
        Path homeDir = Paths.get("D:", "temp", "fileexamples"); //or just add ,test.txt
        Path pathToFile = homeDir.resolve("test.txt");


        try {
            List<String> fileLines = Files.readAllLines(pathToFile);
            fileLines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Reading file exception: ");
            e.printStackTrace();
        }
    }

}
