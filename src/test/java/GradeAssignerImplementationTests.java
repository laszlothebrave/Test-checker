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
    public void evaluateGradeCommonCase (){
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

}
}
