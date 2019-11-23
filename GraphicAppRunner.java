import java.util.Scanner;
/**
 * This will run the whole program and present the applet.
 *
 * @author Ugo Dos Reis
 */
public class GraphicAppRunner
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = in.nextLine();
        while (name.length() > 7) {
            System.out.println("Your name needs to be shorter than 7 characters... sorry! Please pick a new name: ");
            name = in.nextLine();
        }
        System.out.println("Thank you!");
        System.out.println("Game starting up....");
        System.out.println("Please stand by");
        Battle app = new Battle();
        int width = 1280;
        int height = 720;
        app.debug(width,height,name);
    }
}
