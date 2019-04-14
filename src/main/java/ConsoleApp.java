import java.nio.file.Path;
import java.util.ArrayList;

public class ConsoleApp {
    static SheetReader sheetReader;
    static GradeAssigner gradeAssigner;
    static path
    static Path[] paths;
    static TestEvaluation correctAnswers;
    static ArrayList<TestEvaluation> tests;

    public static void main(String[] args) {
        createPaths(args);
        readCorrectAnswersSheet();
        readAtLeastOneStudentSheet();
        printResults();
    }

    private static void createPaths(String[] args) {

    }

    private static void readCorrectAnswersSheet(){
        correctAnswers = sheetReader.readSheet(paths[0]);
        if (correctAnswers == null) throw new CantReadCorrectAnswers();
    }

    private static void readAtLeastOneStudentSheet(){

    }

    private static void printResults(){

    }


}


//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
//        System.out.println("mat = " + mat.dump());