
/**
 * OpponentTester.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class OpponentTester
{
    public static void main (String[]args)
    {
        Player p1=new Player("Jasmine", new Pokemon("Turtwig", 1, 0, 55, 68,64,"Grass"));
        Opponent o1=new Opponent();      
        System.out.println(o1.getOpponentPokemon());
        o1.setOpponentPokemon(p1.getPokemon().getLvl());
        System.out.println(o1.getOpponentPokemon());
    }
}
