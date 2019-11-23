import java.util.ArrayList;
public class Pokemon
{
    private boolean status;
    private int evolution;
    private int hp;
    private int atk;
    private int lvl;
    private int maxHP;
    private int baseHP;
    private int expStored;
    private int xpStat;
    private String name; 
    private String type; 
    private int area;
    private ArrayList<Moves> currentMoves=new ArrayList<Moves>();
    /**
     * Constructor Name: Pokemon()
     * Description: creates a pokemon object
     * 
     * @precondition: level>0, evolution>0, hp>0, attack>0
     * @param: String n, int l, int e, int h, int a, int x, String t - 
     * n is name, l is level, e is evolution status, h is hitpoints (hp), a is attack, x is xpstat, t is type
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public Pokemon(String n, int l, int e, int h, int a, int x, String t)
    {
        //Starter Pokemon
        type=t;
        lvl=l;
        name=n;
        baseHP=h;
        atk=a;
        evolution=0;
        expStored=0;
        xpStat=x;
        maxHP = 2*baseHP*lvl/100+lvl+10;
        hp = maxHP;
        setStatus();
    }

    /**
     * Method Name: getName()
     * Description: Returns the name of the pokemon
     * 
     * @precondition: pokemon has been instantiated as an object
     * @param: none
     * @return: String name
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public String getName() 
    { 
        return name;
    }

    /**
     * Method Name: getType()
     * Description: returns the type of the pokemon
     * 
     * @precondition: pokemon has been instantiated as an object
     * @param:none
     * @return:String type
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public String getType()
    {
        return type;
    }

    /**
     * Method Name: getLvl()
     * Description: returns the level of the pokemon
     * 
     * @precondition: pokemon object has been instantiated
     * @param:none
     * @return:int lvl
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public int getLvl()
    {
        return lvl;
    }

    /**
     * Method Name: getHealth()
     * Description: returns the current health of the pokemon
     * 
     * @precondition: pokemon object has been instantiated
     * @param:none
     * @return:int hp
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public int getHealth()
    {
        return hp;
    }

    /**
     * Method Name: getStatus()
     * Description: returns if the pokemon is fainted or still able to battle
     * 
     * @precondition: pokemon object has been instantiated
     * @param: none
     * @return: boolean status
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public boolean getStatus()
    {
        return status;
    }

    /**
     * Method Name: getMaxHP()
     * Description: returns the max amount of hp the pokemon can have
     * (based on level and type), used for healing pokemon
     * 
     * @precondition: pokemon object has been instantiated
     * @param: none
     * @return: int maxHP
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public int getMaxHP()
    {
        return maxHP;
    }

    /**
     * Method Name: getATCK()
     * Description: returns the attack value of the pokemon pokemon
     * (based on level and type)
     * 
     * @precondition: pokemon object has been instantiated
     * @param: none
     * @return: int atk
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public int getATCK()
    {
        return atk;
    }

    /**
     * Method Name: getXPStat()
     * Description: gets the pokemon's XPStat
     * 
     * @precondition: none
     * @param: none
     * @return: xpStat
     * 
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    public int getXPStat()
    {
        return xpStat;
    }

    /**
     * Method Name: setLvl()
     * Description: sets the pokemon's level to the inputted value
     * 
     * @precondition: pokemon object has been instantiated and l>0
     * @param: int l
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public void setLvl(int l)
    {
        lvl=l;
    }

    /**
     * Method Name: heal()
     * Description: sets the pokemon's health to the max
     * 
     * @precondition: none
     * @param: none
     * @return: none
     * 
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    public void heal()
    {
        hp = maxHP;
    }

    /**
     * Method Name: lvlUP()
     * Description: controls how a pokemon levels up, and how the stats of the 
     * pokemon change after levelling up (health, attack). the input is the
     * additional amount of experience the pokemon earns after a battle.
     * 
     * @precondition: pokemon has battled another pokemon
     * @param: int exp
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public void lvlUP(int exp)
    {
        expStored+=exp;
        int expRequired = lvl*45;
        if(expStored-expRequired>=0)
        {
            expStored-=expRequired;
            lvl++;
            maxHP = 2*baseHP*lvl/100+lvl+10;
            hp = maxHP;
            expRequired = lvl*45;
            if(expStored-expRequired>=0)
            {
                lvlUP(0);
            }
        }
    }
    
    /**
     * Method Name: changeHealth()
     * Description: sets the current health by adding the input to the current health,
     * used for healing and attacking (negative numbers used to subtract from health)
     * 
     * @precondition: hp>0
     * @param: int change
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public void changeHealth(int change)
    {
        hp-=change;
        if (hp < 0) {
            hp = 0;
        }
    }

    /**
     * Method Name: changeAtk()
     * Description: changes the atk by adding the change to the 
     * atk variable
     * 
     * @precondition: atk>0
     * @param: int change
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public void changeAtk(int change)
    {
        atk+=change;
    }

    /**
     * Method Name: toString()
     * Description: prints out the level, name, and current hp of the pokemon
     * 
     * @precondition: pokemon object has been instantiated
     * @param: none
     * @return: String 
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public String toString()
    {
        return name+"\tLevel: "+lvl+"\nHP: "+hp;
    }

    /**
     * Method Name: setStatus()
     * Description: determines whether the pokemon has fainted or can still battle
     * 
     * @precondition: pokemon object has been instantiated
     * @param: none
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public void setStatus()
    {
        if(hp>0)
        {
            status=true;
        }
        else
        {
            status=false;
        }
    }

    /**
     * Method Name: setName()
     * Description: used for changing the name of a pokemon for a nickname or for evolutions.
     * takes the inputted the name and changes it
     * 
     * @precondition: pokemon object has been instantiated and if ready to evolve
     * @param: String s
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public void setName(String s)
    {
        name=s;
    }

    /**
     * Method Name: evolve()
     * Description: changes the name of the pokemon if it is evolving into the next evolution's name
     * 
     * @precondition: pokemon object has been instantiated
     * @param: none
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public void evolve()
    {
        if(evolution==0)
        {
            if(name.equals("Turtwig"))
            {
                name="Grotle";
            }
            else if(name.equals("Chimchar"))
            {
                name="Monferno";
            }
            else if(name.equals("Piplup"))
            {
                name="Prinplup";
            }
        }
        else if(evolution==1)
        {
            if(name.equals("Grotle"))
            {
                name="Torterra";
            }
            else if(name.equals("Monferno"))
            {
                name="Infernape";
            }
            else if(name.equals("Prinplup"))
            {
                name="Empoleon";
            }
        }
    }

    /**
     * Method Name: getSpecMove()
     * Description: accesses a specific move at the position of the input and returns
     * the move that is in the position of that arraylist
     * 
     * @precondition: pokemon object has been instantiated, has moves in the arraylist of currentMoves
     * @param: int x
     * @return: Moves currentMoves.get(x)
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public Moves getSpecMove(int x)
    {
        return currentMoves.get(x);
    }

    /**
     * Method Name: addMoves()
     * Description: adds an inputted move to a pokemon's arraylist of currentMoves
     * 
     * @precondition: pokemons has less than 4 moves
     * @param: Moves m
     * @return: none
     * 
     * @author: Kevin Yan
     * @version May 2018
     */
    public void addMoves(Moves m)
    {
        currentMoves.add(m);
    }
}