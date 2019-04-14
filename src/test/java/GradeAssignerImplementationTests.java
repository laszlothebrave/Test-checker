import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Andrea on 14/04/2019.
 */
public class GradeAssignerImplementationTests {
    @Test
    public void CheckAnswers() {
        ArrayList<Integer> good = new ArrayList<>();
        good.add(1);good.add(2);good.add(3);good.add(4);good.add(0);
        ArrayList<Integer> bad= new ArrayList<>();
        bad.add(1);bad.add(2);bad.add(3);
        final int[] expected={3,5};
        final int[] actual= GradeAssignerImplementation.CheckAnswers(good,bad);
        Assert.assertArrayEquals(expected,actual);

    }
}
