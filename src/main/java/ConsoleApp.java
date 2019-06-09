import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleApp {
    static SheetReader sheetReader = new SheetReaderImplementation();
    static GradeAssigner gradeAssigner = new GradeAssignerImplementation();
    static File correctAnswersFile;
    static File[] testsPaths;
    static TestEvaluation correctAnswers;
    static ArrayList<TestEvaluation> tests = new ArrayList<>();
    static ArrayList<GradeAndStudentCode> grades = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(" ________    _____    _____   ________                ____   __    __    _____     ____    __   ___    _____   ______    \n" +
                "(___  ___)  / ___/   / ____\\ (___  ___)              / ___) (  \\  /  )  / ___/    / ___)  () ) / __)  / ___/  (   __ \\   \n" +
                "    ) )    ( (__    ( (___       ) )     ________   / /      \\ (__) /  ( (__     / /      ( (_/ /    ( (__     ) (__) )  \n" +
                "   ( (      ) __)    \\___ \\     ( (     (________) ( (        ) __ (    ) __)   ( (       ()   (      ) __)   (    __/   \n" +
                "    ) )    ( (           ) )     ) )               ( (       ( (  ) )  ( (      ( (       () /\\ \\    ( (       ) \\ \\  _  \n" +
                "   ( (      \\ \\___   ___/ /     ( (                 \\ \\___    ) )( (    \\ \\___   \\ \\___   ( (  \\ \\    \\ \\___  ( ( \\ \\_)) \n" +
                "   /__\\      \\____\\ /____/      /__\\                 \\____)  /_/  \\_\\    \\____\\   \\____)  ()_)  \\_\\    \\____\\  )_) \\__/  \n" +
                "                                                                                                                          \n ");
        System.out.println("dP                   d8888b. d88  d8888b. d88888P                   .8888b   dP                                         \n" +
                "88                       `88  88      `88     d8'                   88   \"   88                                         \n" +
                "88d888b. dP    dP    .aaadP'  88   aaad8'    d8'  .d8888b. .d8888b. 88aaa  d8888P dP  dP  dP .d8888b. 88d888b. .d8888b. \n" +
                "88'  `88 88    88    88'      88      `88   d8'   Y8ooooo. 88'  `88 88       88   88  88  88 88'  `88 88'  `88 88ooood8 \n" +
                "88.  .88 88.  .88    88.      88      .88  d8'          88 88.  .88 88       88   88.88b.88' 88.  .88 88       88.  ... \n" +
                "88Y8888' `8888P88    Y88888P d88P d88888P d8'     `88888P' `88888P' dP       dP   8888P Y8P  `88888P8 dP       `88888P' \n" +
                "              .88                                                                                                       \n" +
                "          d8888P                                                                                                        ");
        System.out.println("\n\n\n");
        System.out.println("Umiesc plik o nazwie klucz_odpowiedzi.jpg w folderze klucz odpowiedzi i nacisnij enter");
        promptEnterKey();
        try {
            createPaths(args);
        } catch (Exception e) {
            System.out.println("Nie mozna uzyskac dostepu do plikow");
            return;
        }
        System.out.println("Odczyt klucza odpowiedzi w trakcie");
        try {
            readCorrectAnswersSheet();
        } catch (Exception e) {
            System.out.println("Klucz odpowiedzi nie moze byc zdjeciem konia");
            System.out.println("Skopiuj klucz odpowiedzi z folderu arkusze_do_oceny");
            return;
        }
        System.out.println("Poprawnie odczytano klucz odpowiedzi");
        System.out.println("Umiesc pliki z arkuszami do oceny w formacie jpg w folderze arkusze_do_oceny i nacisnij enter");
        promptEnterKey();
        try {
            readAtLeastOneStudentSheet();
        } catch (Exception e){
            System.out.println("Blad przy odczycie arkuszy");
            return;
        }
        System.out.println("Poprawnie odczytano wszystkie arkusze");
        gradeAssigner.setAnswerKey(correctAnswers);
        assignGrades();
        try {
            printResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Plik z wynikami znajduje sie w folderze wyniki");
        System.out.println("Dziekujemy!");
    }

    public static void promptEnterKey(){
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static void createPaths(String[] args) {
        final String dir = System.getProperty("user.dir");
        correctAnswersFile = new File(dir + "/" + args[0]);
        testsPaths = new File(dir + "/" + args[1]).listFiles();
    }

    private static void readCorrectAnswersSheet() throws CantReadCorrectAnswers{
        correctAnswers = sheetReader.readSheet(correctAnswersFile);
        if (correctAnswers == null) throw new CantReadCorrectAnswers(correctAnswersFile);
    }

    private static void readAtLeastOneStudentSheet(){
        System.out.println("");
        for (File iter : testsPaths) {
            System.out.print(". ");
            tests.add(sheetReader.readSheet(iter));
        }
        System.out.println("");
    }

    private static void assignGrades () {
        for (TestEvaluation iter : tests) {
            grades.add(gradeAssigner.assignGrade(iter));
        }
    }
    private static void printResults() throws FileNotFoundException, UnsupportedEncodingException {
        String path=correctAnswersFile.getAbsolutePath();
        PrintWriter writer = new PrintWriter("wyniki/wyniki.txt", "UTF-8");
        writer.println("Results: ");
        for (GradeAndStudentCode iter : grades) {
            writer.println(iter.toString());
        }
        writer.close();
    }
}


//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
//        System.out.println("mat = " + mat.dump());