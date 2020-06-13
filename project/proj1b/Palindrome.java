public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> result = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            result.addLast(word.charAt(i));
        }
        return result;
    }
    public boolean isPalindrome(String word){
        Deque words = wordToDeque(word);
        while(words.size() > 1){
            if(words.removeFirst() != words.removeLast())
                return false;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque words = wordToDeque(word);
        while(words.size() > 1) {
            if (!cc.equalChars((char) words.removeFirst(), (char) words.removeLast()))
                return false;
        }
            return true;
    }
}
