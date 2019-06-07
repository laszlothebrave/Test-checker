import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrea on 14/04/2019.
 */
public class GradeAssignerImplementationTests {

    @Test
    public void CheckAnswersIfStudentHasNoAnswers() {
        //student zaznaczyl troche dobrych troche zlych
        ArrayList<Integer> good = new ArrayList<>();
        good.add(1);good.add(3);good.add(4);good.add(0);
        ArrayList<Integer> bad= new ArrayList<>();
        final int[] expected={0,4};
        final int[] actual= GradeAssignerImplementation.CheckAnswers(good,bad);
        Assert.assertArrayEquals(expected,actual);

    }

    @Test
    public void CheckAnswersIfStudentHasSomeGoodSomeBad() {
        //student zaznaczyl troche dobrych troche zlych
        ArrayList<Integer> good = new ArrayList<>();
        good.add(1);good.add(3);good.add(4);good.add(0);
        ArrayList<Integer> bad= new ArrayList<>();
        bad.add(1);bad.add(2);bad.add(3);
        final int[] expected={0,4};
        final int[] actual= GradeAssignerImplementation.CheckAnswers(good,bad);
        Assert.assertArrayEquals(expected,actual);

    }
    @Test
    public void CheckAnswersIfStudentHasEverythingGood() {
        //student zaznaczyl troche dobrych troche zlych
        ArrayList<Integer> good = new ArrayList<>();
        good.add(new Integer(1));good.add(3);good.add(4);good.add(0);
        ArrayList<Integer> bad= new ArrayList<>();
        bad.add(1);bad.add(4);bad.add(3);
        final int[] expected={3,4};
        final int[] actual= GradeAssignerImplementation.CheckAnswers(good,bad);
        Assert.assertArrayEquals(expected,actual);

    }

    @Test
    public void CheckAnswersIfStudentHasEverythingBad() {
        //student zaznaczyl troche dobrych troche zlych
        ArrayList<Integer> good = new ArrayList<>();
        good.add(1);
        ArrayList<Integer> bad= new ArrayList<>();
        bad.add(2);bad.add(4);bad.add(3);
        final int[] expected={0,1};
        final int[] actual= GradeAssignerImplementation.CheckAnswers(good,bad);
        Assert.assertArrayEquals(expected,actual);

    }

    @Test
    public void CheckAnswersIfStudentHasEveryThingSame() {
        //student zaznaczyl troche dobrych troche zlych
        ArrayList<Integer> good = new ArrayList<>();
        good.add(new Integer(1));good.add(3);good.add(4);good.add(0);
        ArrayList<Integer> bad= new ArrayList<>();
        bad.add(2);bad.add(4);bad.add(3);
        final int[] expected={0,1};
        final int[] actual= GradeAssignerImplementation.CheckAnswers(good,bad);
        Assert.assertArrayEquals(expected,actual);


    }
    @Test
    public void evaluateGradeEverythingGoodCase (){
        //student jest najlepsze i wszytkie dobrze zaznaczel
        Path path= Paths.get("C//Desktop");
        ArrayList<ArrayList<Integer>> myAll= new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<40;i++){
            ArrayList<Integer> tmp=new ArrayList<Integer>();
            tmp.add(1);
            tmp.add(2);
            myAll.add(tmp);
        }
        GradeAssignerImplementation test=new GradeAssignerImplementation();
        TestEvaluation good=new TestEvaluation(path,"123",myAll);
        test.correctAnswers=good;
        TestEvaluation commonStudent=new TestEvaluation(path,"123",myAll);
        float answer=test.evaluateGrade(commonStudent);
        Assert.assertEquals(100,answer,0.000001);
        /*DESCRIPTION bug detected
        ten test ukrywal ze w methodzie wartosc oczekiwana byla odejmowana w forze a nie po nim, co bylo zle*/

}

    @Test
    public void evaluateGradeEverythingBadCase (){
        //student nic nie trafil
        Path path= Paths.get("C//Desktop");
        ArrayList<ArrayList<Integer>> myAll= new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> myBad= new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<40;i++){
            ArrayList<Integer> tmp=new ArrayList<Integer>();
            ArrayList<Integer> tmp2=new ArrayList<Integer>();
            tmp.add(1);
            tmp.add(2);
            tmp2.add(1);
            tmp2.add(2);
            myAll.add(tmp);
            tmp2.add(0);
            myBad.add(tmp2);
        }
        GradeAssignerImplementation test=new GradeAssignerImplementation();
        TestEvaluation good=new TestEvaluation(path,"120",myAll);
        test.correctAnswers=good;
        TestEvaluation commonStudent=new TestEvaluation(path,"123",myBad);
        float answer=test.evaluateGrade(commonStudent);
        Assert.assertEquals(0,answer,0.000001);
        /*DESCRIPTION bug detected
        globalgoodanswers i numberofgoodanswers byly int co spowodowalo zle liczenie percentage
        w ramach poprawe zadeklarowano jako float*/

    }


    @Test
    public void evaluateGradeAverageCase (){
        //student ma pol dobre odpowiedzi i pol zle
        Path path= Paths.get("C//Desktop");
        ArrayList<ArrayList<Integer>> myAll= new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> myBad= new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<40;i++){
            ArrayList<Integer> tmp=new ArrayList<Integer>();
            ArrayList<Integer> tmp2=new ArrayList<Integer>();
            tmp.add(1);
            tmp2.add(i%2);
            myAll.add(tmp);
            myBad.add(tmp2);
        }
        GradeAssignerImplementation test=new GradeAssignerImplementation();
        TestEvaluation good=new TestEvaluation(path,"120",myAll);
        test.correctAnswers=good;
        TestEvaluation commonStudent=new TestEvaluation(path,"123",myBad);
        float answer=test.evaluateGrade(commonStudent);
        Assert.assertEquals(37.5,answer,0.000001);

        /*DESCRIPTION bug detected
        globalgoodanswers bylo inkrementowane w oparciu o ile ich bylo w test ktory dostalismy ale globalgoodanswers ma byc nie dotykane bo jest ustawione na 32 z default
        przez to ze jest jednokrotnego wyboru i mamy 40 dobrych odpowiedzi -8(wartosc oczykiwana)*/

    }

    @Test
    public void evaluateGradeExactlyPassedExam (){
        //student ma 24 dobre odpowiedzi
        Path path= Paths.get("C//Desktop");
        ArrayList<ArrayList<Integer>> myAll= new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> myBad= new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<40;i++){
            ArrayList<Integer> tmp=new ArrayList<Integer>();
            ArrayList<Integer> tmp2=new ArrayList<Integer>();
            tmp.add(1);
            if(i>30 && i%2!=1)
                tmp2.add(1);
            else tmp2.add(i%2);
            myAll.add(tmp);
            myBad.add(tmp2);
        }
        GradeAssignerImplementation test=new GradeAssignerImplementation();
        TestEvaluation good=new TestEvaluation(path,"120",myAll);
        test.correctAnswers=good;
        TestEvaluation commonStudent=new TestEvaluation(path,"123",myBad);
        float answer=test.evaluateGrade(commonStudent);
        Assert.assertEquals(50,answer,0.000001);



    }
}
