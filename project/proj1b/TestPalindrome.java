import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIspalindrome(){
        assertTrue(palindrome.isPalindrome("abcba"));
        assertTrue(palindrome.isPalindrome("cbabc"));
        assertFalse(palindrome.isPalindrome("cidis"));
        assertFalse(palindrome.isPalindrome("okokok"));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("madam"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("dog"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("AAAaAAA"));
        assertTrue(palindrome.isPalindrome("ABBA"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("ACDC"));
        assertFalse(palindrome.isPalindrome("yang"));
    }
}