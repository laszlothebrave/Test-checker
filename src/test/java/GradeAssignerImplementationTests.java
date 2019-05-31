import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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
        good.add(1);good.add(3);good.add(4);good.add(0);
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
}
