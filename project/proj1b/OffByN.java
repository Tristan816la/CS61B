public class OffByN implements CharacterComparator{
    private int offEr;
    public OffByN(int o){
        offEr = o;
    }
    public boolean equalChars(char x, char y){
        return Math.abs(x-y) == offEr;
    }
}
