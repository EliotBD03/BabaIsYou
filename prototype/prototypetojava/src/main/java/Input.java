import java.util.Scanner;

public class Input
{
    public String get_Input()
    {
        Scanner object = new Scanner(System.in);
        System.out.println("Please enter => \nz : up s : down : d : right q : left\ne : escape ");
        String input = object.nextLine();
        return input;
    }
}
