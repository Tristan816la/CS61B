import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars(){
        OffByOne tester = new OffByOne();
        assertTrue(tester.equalChars('a','b'));
        assertTrue(!tester.equalChars('a','a'));
        assertTrue(tester.equalChars('m','n'));
        assertTrue(!tester.equalChars('a','c'));
        assertTrue(tester.equalChars('c','b'));
        assertTrue(tester.equalChars('l','k'));
        assertTrue(!tester.equalChars('i','b'));
        assertTrue(tester.equalChars('b','c'));
        assertTrue(tester.equalChars('l','m'));
    }
}



