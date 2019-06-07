import java.nio.file.Path;
import java.util.ArrayList;

public class TestEvaluation {
    Path sourceFile;
    String studentCode;
    ArrayList <ArrayList<Integer>> checkedAnswers;

    TestEvaluation(Path p,String sC,ArrayList<ArrayList<Integer>> cA){
        sourceFile=p;
        studentCode=sC;
        checkedAnswers=cA;
    }
    TestEvaluation(){

    }

}
