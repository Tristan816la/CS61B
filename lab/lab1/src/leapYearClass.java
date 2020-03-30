import java.util.Scanner;
public class leapYearClass {

    public static void main(String args[]){
        System.out.print("Enter the year: ");
        Scanner reader = new Scanner(System.in);
        int year = reader.nextInt();
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            System.out.println("This is a leap year");
        } else{
            System.out.println("This is not a leap year");
        }
    }
}
