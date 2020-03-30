public class hw1 {
    public static void drawTriangle(int row){
        int col = 1;
        for(int i = 0; i < row; i++){
            for (int j = 0; j < col; j++)
                System.out.print('*');
            System.out.println();
            col++;
        }
    }
    public static int max(int[] m){
        int max = m[0];
        for (int i = 0; i < m.length; i++){
            if (m[i] > max)
                max = m[i];
        }
        return max;
    }

    public static void windowPosSum(int [] a, int n){
        for (int i = 0; i < a.length; i++){
            if (a[i] > 0){
                int count = 0;
                for (int j = i + 1; j < a.length && count < n; j++) {
                    a[i] += a[j];
                    count++;
                }
            }
        }
    }
    public static void main (String args[]){
        drawTriangle(10);
        int [] numbers = new int[]{9,2,15,2,22,10,6};
        int [] a = new int[]{1,2,-3,4,5,4};
        int [] b = new int[] {1, -1, -1, 10, 5, -1};
        windowPosSum(numbers, 3);
        windowPosSum(a, 3);
        windowPosSum(b, 2);
        for (int i : a)
            System.out.print(i + " ");
        System.out.println();
        for (int i : b)
            System.out.print(i + " ");
    }
}
