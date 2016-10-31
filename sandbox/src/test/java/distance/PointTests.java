package distance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance(){
        Point a = new Point(6, 2);
        Point b = new Point(6, -1);

        Assert.assertEquals(a.calcDistance(b), 3.0);
    }
}
