/**
 * Moves.java  
 *
 * @author: Jasmine Wang
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class Moves
{
    private String name;
    private int dmg;
    /**
     * Method Name: Moves()
     * Description: creates a move object. Moves are attacks
     * that pokemon use when battling, so each move has a name
     * and damage
     * 
     * @precondition: pokemon has >4 moves
     * @param: String s, int d-
     * s is the name of the move, d is the amount of damage it does
     * @return: none
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public Moves(String s, int d)
    {
        name=s;
        dmg=d;
    }
    /**
     * Method Name: setDmg()
     * Description: takes an input and sets it
     * equal to the damage of the move
     * 
     * @precondition: d>0
     * @param: int d
     * @return: none
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public void setDmg(int d)
    {
        dmg=d;
    }
    /**
     * Method Name: setName()
     * Description: changes the name of the move into
     * the inputted string
     * 
     * @precondition: none
     * @param: String s
     * @return: none
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public void setName(String s)
    {
        name=s;
    }
    /**
     * Method Name: getDmg()
     * Description: returns the amount of dmg that the move has
     * 
     * @precondition: none
     * @param: none
     * @return: int dmg
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public int getDmg()
    {
        return dmg;
    }
    /**
     * Method Name: getName()
     * Description: returns the name of the move
     * 
     * @precondition: none
     * @param: none
     * @return: String name
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public String getName()
    {
        return name;
    }
}