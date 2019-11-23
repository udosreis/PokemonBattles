
/**
 * PlayerTester.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class PlayerTester
{
    public static void main(String args [])
    {
        Player p1=new Player("Chris");
        p1.setPokemon(new Pokemon ("Turtwig",1,1,1,60,5,"Grass"));
        System.out.println(p1.getName());
        p1.changeName("Jasmine");
        System.out.println(p1.getName());
        System.out.println(p1.getPokemon());
        p1.changePokeDollars(1400);
        System.out.println(p1.getPokeDollars());
        p1.getPokemon().changeHealth(-30);
        System.out.println(p1.getPokemon().getHealth());
        p1.buyAPotion(2,2);
        System.out.println(p1.getnoOfSuperPotions());
        p1.useAPotion(2,1);
        System.out.println(p1.getnoOfSuperPotions());
        System.out.println(p1.getPokemon().getHealth());
        System.out.println(p1.getPokeDollars());
        p1.changePokeDollars(2000);
        System.out.println(p1.getPokeDollars());
        p1.getPokemon().changeHealth(-10);
        System.out.println(p1.getPokemon().getHealth());
        p1.buyAPotion(3,1);
        System.out.println(p1.getnoOfSuperPotions());
        p1.useAPotion(3,2);
        System.out.println(p1.getnoOfSuperPotions());
        System.out.println(p1.getPokemon().getHealth());
        System.out.println(p1.getPokeDollars());
    }
}

