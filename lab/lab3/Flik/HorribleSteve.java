import org.junit.Test;
import static org.junit.Assert.*;

public class HorribleSteve {
    @Test
    public void testisSameNumber() {
        int a = 12;
        int b = 15;
        int c = 20;
        int d = 22;
        assertTrue(!Flik.isSameNumber(a,b));
        assertTrue(!Flik.isSameNumber(c,d));
    }

    public static void main(String [] args) throws Exception {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            // Error: Integer is within the range (-128, 128)
            if (!Flik.isSameNumber(i, j)) {
                throw new Exception(
                        String.format("i:%d not same as j:%d ??", i, j));
            }
        }
        System.out.println("i is " + i);
    }
}
