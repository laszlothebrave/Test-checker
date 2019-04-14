import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;

public class ConsoleApp {
    static SheetReader sheetReader = new SheetReaderImplementation();
    static GradeAssigner gradeAssigner;
    static File correctAnswersFile;
    static File[] testsPaths;
    static TestEvaluation correctAnswers;
    static ArrayList<TestEvaluation> tests;

    public static void main(String[] args) {
        try {
            createPaths(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            readCorrectAnswersSheet();
        } catch (CantReadCorrectAnswers e) {
            e.printStackTrace();
        }
        try {
            readAtLeastOneStudentSheet();
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            printResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createPaths(String[] args) {
        correctAnswersFile = new File(args[0]);
        testsPaths = new File(args[1]).listFiles();
    }

    private static void readCorrectAnswersSheet() throws CantReadCorrectAnswers{
        correctAnswers = sheetReader.readSheet(correctAnswersFile);
        if (correctAnswers == null) throw new CantReadCorrectAnswers(correctAnswersFile);
    }

    private static void readAtLeastOneStudentSheet(){

    }

    private static void assignGrades () {
        
    }
    private static void printResults() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();
    }
}


//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
//        System.out.println("mat = " + mat.dump());