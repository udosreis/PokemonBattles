
/**
 * Player.java  
 *
 * @author: Chris Yang
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class Player
{
    private Pokemon myPokemon;
    private int pokeDollars;
    private int noOfPotions;
    private int noOfSuperPotions;
    private int noOfHyperPotions;
    private String name;

    /**
     * Constructor Name: Player()
     * Description: creates a player object that the user
     * will use to communicate with the game
     * 
     * @precondition: none
     * @param: String n
     * @return: none
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public Player(String n)
    {
        name=n;
    }
    /**
     * Constructor Name: Player()
     * Description: creates a player object that the user
     * will use to communicate with the game
     * 
     * @precondition: none
     * @param: String n, Pokemon p
     * @return: none
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public Player(String n,Pokemon p)
    {
        name=n;
        myPokemon = p;
    }
    /**
     * Method Name: setPokemon()
     * Description: sets an inputted pokemon to
     * the pokemon that the player owns
     * 
     * @precondition: none
     * @param: Pokemon p
     * @return: none
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public void setPokemon(Pokemon p)
    {
        myPokemon=p;
    }
    /**
     * Method Name: changeName()
     * Description: changes the name of the player
     * to the name that is inputted
     * 
     * @precondition: none
     * @param: String n
     * @return: none
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public void changeName(String n)
    {
        name=n;
    }
    /**
     * Method Name: getName()
     * Description: returns the value stored as name
     * 
     * @precondition: none
     * @param: none
     * @return: String name
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public String getName()
    {
        return name;
    }
    /**
     * Method Name: changePokeDollars()
     * Description: changes the amount of pokedollars
     * the player has by adding the amount of dollars 
     * inputted in. Can be used to lose money by inputting negative numbers.
     * 
     * @precondition: none
     * @param: int d
     * @return: none
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public void changePokeDollars(int d)
    {
        pokeDollars+=d;
    }
    /**
     * Method Name: getPokeDollars()
     * Description: returns the amount of 
     * pokedollars the player has currently
     * 
     * @precondition: none
     * @param: none
     * @return: int pokeDollars
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public int getPokeDollars()
    {
        return pokeDollars;
    }
    /**
     * Method Name: getPokemon()
     * Description: returns the pokemon that
     * the player has
     * 
     * @precondition: none
     * @param: none
     * @return: Pokemon myPokemon
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public Pokemon getPokemon()
    {
        return myPokemon;
    }
    /**
     * Method Name: getnoOfPotions()
     * Description: returns the number of
     * potions that the player has
     * 
     * @precondition: none
     * @param: none
     * @return: int noOfPotions
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public int getnoOfPotions()
    {
        return noOfPotions;
    }
    /**
     * Method Name: getnoOfSuperPotions()
     * Description: returns the number of super potions
     * the player has
     * 
     * @precondition: none
     * @param: none
     * @return: int noOfSuperPotions
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public int getnoOfSuperPotions()
    {
        return noOfSuperPotions;
    }
    /**
     * Method Name: getnoOfHyperPotions()
     * Description: returns the number of hyper potions
     * the player has
     * 
     * @precondition: none
     * @param: none
     * @return: int noOfHyperPotions
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public int getnoOfHyperPotions()
    {
        return noOfHyperPotions;
    }
    /**
     * Method Name: useAPotion()
     * Description: uses recursion to heal pokemon through
     * the use of potions. Every time a potion is used, the pokemon is healed
     * a certain amount of hp, and the method calls itself so that the player
     * can use multiple potions in a row if they choose
     * to do so.
     * 
     * @precondition: none
     * @param: int p, int n
     * @return: none
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public void useAPotion(int p,int n)
    {
        if(n>0)
        {
            if(p==1 && noOfPotions>0 && getPokemon().getHealth()!=getPokemon().getMaxHP())
            {
                if(myPokemon.getHealth()+20>myPokemon.getMaxHP())
                {
                    System.out.println("Healed "+(myPokemon.getMaxHP()-myPokemon.getHealth())+" HP");
                    myPokemon.changeHealth(myPokemon.getMaxHP()-myPokemon.getHealth());
                    noOfPotions--;

                }
                else{
                    myPokemon.changeHealth(20);
                    noOfPotions--;
                    System.out.println("Healed 20 HP");
                }

                
            }
            else if(p==2 && noOfSuperPotions>0 && getPokemon().getHealth()!=getPokemon().getMaxHP())
            {
                if(myPokemon.getHealth()+50>myPokemon.getMaxHP())
                {
                    System.out.println("Healed "+(myPokemon.getMaxHP()-myPokemon.getHealth())+" HP");
                    myPokemon.changeHealth(myPokemon.getMaxHP()-myPokemon.getHealth());
                    noOfSuperPotions--;

                }
                else{
                    myPokemon.changeHealth(50);
                    noOfSuperPotions--;
                    System.out.println("Healed 50 HP");
                }

                
            }
            else if(p==3 && noOfHyperPotions>0)
            {
                if(myPokemon.getHealth()+200>myPokemon.getMaxHP())
                {
                    System.out.println("Healed "+(myPokemon.getMaxHP()-myPokemon.getHealth())+" HP");
                    myPokemon.changeHealth(myPokemon.getMaxHP()-myPokemon.getHealth());
                    noOfHyperPotions--;

                }
                else{
                    myPokemon.changeHealth(200);
                    noOfHyperPotions--;
                    System.out.println("Healed 200 HP");
                }

                
            }
            else 
            {
                System.out.println("Nothing happened");
                n--;
            }
            useAPotion(p,n-1);
        }
    }
    /**
     * Method Name: buyAPotion()
     * Description: uses recursion to buy potions. the method calls itself
     * until the player does not want to buy any more potions or when they
     * don't have enough money to do so. takes in int p to determine the kind
     * of potion to buy. Changes the variables noOfPotions, noOfSuperPotions, 
     * noOfHyperPotions, and pokeDollars accordingly.
     * 
     * @precondition: none
     * @param: int p, int num
     * @return: none
     * 
     * @author: Chris Yang
     * @version May 2018
     */
    public void buyAPotion(int p, int num)
    {
        if(num>0)
        {
            if(p==1 && pokeDollars>=300)
            {
                noOfPotions++;
                pokeDollars-=300;
                System.out.println("You got 1 Potion!");

                
            }
            else if(p==2 && pokeDollars>=700)
            {
                noOfSuperPotions++;
                pokeDollars-=700;
                System.out.println("You got 1 Super Potion!");

                
            }
            else if(p==3 && pokeDollars>=1200)
            {
                noOfHyperPotions++;
                pokeDollars-=1200;
                System.out.println("You got 1 Hyper Potion!");

                
            }
            else 
            {
                System.out.println("Nothing Happened");
                num--;
            }
            buyAPotion(p,num-1);
        }
    }
}