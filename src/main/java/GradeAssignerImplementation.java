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
        float percentage=evaluateGrade(studentAnswers);
        Grade grade =fromPercentageTograde(percentage);
        actualStudent.grade=grade;
        return actualStudent;

    }
    private Grade fromPercentageTograde(float percentage){
        if(percentage<50)
            return Grade.F;
        if(percentage>50 && percentage<60)
            return Grade.E;
        if(percentage>60 && percentage<70)
                return Grade.D;
        if(percentage>70 && percentage<80)
            return Grade.C;
        if(percentage>80 && percentage<90)
            return Grade.B;
        return Grade.A;
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
        if(maybeBad.size()>numberOfGoodAnswers)
            tmp[0]=0;
        else tmp[0]=numberOfGoodAnswers;
        return tmp;
    }

    public float evaluateGrade ( TestEvaluation studentAnswers){
        float percentage=0;
        float numberOfGoodAnswers=0;
        //float globalGoodAnswers=0;
        float globalGoodAnswers=32;
        int[] goodForStudentAndGoodGlobal = new int[2];
        for(int i=0;i<correctAnswers.checkedAnswers.size();i++){
            goodForStudentAndGoodGlobal=CheckAnswers(correctAnswers.checkedAnswers.get(i),studentAnswers.checkedAnswers.get(i));
            numberOfGoodAnswers+=goodForStudentAndGoodGlobal[0];
            //globalGoodAnswers+=goodForStudentAndGoodGlobal[1];

        }
        numberOfGoodAnswers-=8;
        //globalGoodAnswers-=8;
        if(numberOfGoodAnswers<0)
            numberOfGoodAnswers=0;//jezeli mial mniej niz 8 dobrych odpowiedzi to i tak oddajemy 0 bo nie moze miec negatywne
        percentage=numberOfGoodAnswers/globalGoodAnswers*100;

        return percentage;
    }
}
