
/**
 * PokemonTester.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class PokemonTester
{
    public static void main(String args[])
    {
        Pokemon p1 = new Pokemon("Turtwig", 1,1, 0, 100, 10, "Grass");
        System.out.println(p1);
        System.out.println(p1.getName());
        System.out.println(p1.getType());
        System.out.println(p1.getLvl());
        System.out.println(p1.getHealth());
        p1.lvlUP(20000);
        System.out.println(p1);
        System.out.println(p1.getStatus());
        p1.changeHealth(-201);
        p1.setStatus();
        System.out.println(p1.getStatus());
        p1.setName("Leafy");
        System.out.println(p1);
        Pokemon p2 = new Pokemon("Chimchar", 25,1, 0, 100, 10, "Grass");
        System.out.println(p2);
        p2.evolve();
        System.out.println(p2);
    }
}
