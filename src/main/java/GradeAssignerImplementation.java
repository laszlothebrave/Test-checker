import java.util.ArrayList;

public class GradeAssignerImplementation implements GradeAssigner{

    TestEvaluation correctAnswers;
    @Override
    public void setAnswerKey(TestEvaluation correctAnswers) {
        this.correctAnswers=correctAnswers;
    }

    @Override
    public GradeAndStudentCode assignGrade(TestEvaluation studentAnswers) {
        GradeAndStudentCode actualStudent=new GradeAndStudentCode(studentAnswers.studentCode);

        return null;
    }

    public static int[] CheckAnswers(ArrayList<Integer> Good, ArrayList<Integer> maybeBad){
        int[] tmp=new int[2];
        tmp[1]=0;
        int numberOfGoodAnswers=0;
        for(Integer good: Good){
            tmp[1]++;//liczymy ile w ogole bylo dobrych
            if(maybeBad.contains(good))
                numberOfGoodAnswers++;//liczymy ile studentrobak robil dobrze
        }
        tmp[0]=numberOfGoodAnswers;
        return tmp;
    }

    private float evaluateGrade (TestEvaluation correctAnswers, TestEvaluation studentAnswers){
        float percentage=0;
        int numberOfGoodAnswers=0;
        int globalGoodAnswers=0;
        int[] goodForStudentAndGoodGlobal=new int[2];
        for(int i=0;i<correctAnswers.checkedAnswers.size();i++){
            goodForStudentAndGoodGlobal=CheckAnswers(correctAnswers.checkedAnswers.get(i),studentAnswers.checkedAnswers.get(i));
            numberOfGoodAnswers+=goodForStudentAndGoodGlobal[0];
            globalGoodAnswers+=goodForStudentAndGoodGlobal[1];
        }
        percentage=numberOfGoodAnswers/globalGoodAnswers*100;

        return percentage;
    }
}
