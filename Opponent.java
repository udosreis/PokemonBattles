import java.util.ArrayList;
/**
 * Opponent.java  
 *
 * @author: Ugo Dos Reis
 * 
 * Brief Program Description: This is who the player will go against. This class
 * has a name which is the name of the trainer and a pokemon.
 * 
 *
 */
public class Opponent
{ 
    private String name;
    private String rank;
    private Pokemon opponentPokemon;
    private String []names = {"Aaron","Cynthia","Byron","Cyrus","Mars"};
    private String []ranks = {"Trainer","Gym Leader","Elite Four"};
    private ArrayList<Pokemon> possiblePokemon=new ArrayList<Pokemon>();
    /**
     * Constructor Name: Opponent()
     * Description: Creates an Opponent with name and rank as well as add all possible pokemon for the opponent
     * 
     * @precondition: none 
     * @param: none
     * 
     * @return: none
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public Opponent() 
    {
        //ArrayList<Pokemon> possiblePokemon;
        name = names[(int)(Math.random()*names.length)];
        rank = ranks[(int)(Math.random()*ranks.length)];    

        addpossiblePokemon("Turtwig", 1, 0, 55, 68, 64,"Grass");
        addpossiblePokemon("Chikorita",1,0,45,49,64,"Grass");
        addpossiblePokemon("Grotle",18,1,75,89,142,"Grass");
        addpossiblePokemon("Sceptile",36,2,70,85,239,"Grass");
        addpossiblePokemon("Treecko",1,0,40,45,62,"Grass");
        addpossiblePokemon("Piplup", 1, 0, 53, 51,63,"Water");
        addpossiblePokemon("Prinplup",16,1,64,66,142,"Water");
        addpossiblePokemon("Magikarp",1,0,20,10,40,"Water");
        addpossiblePokemon("Gyrados",20,1,95,125,189,"Water");
        addpossiblePokemon("Carracosta",37,0,74,108,173,"Water");
        addpossiblePokemon("Chimchar",1, 0, 45, 58,62,"Fire");
        addpossiblePokemon("Ponyta",1,0,50,85,82,"Fire");
        addpossiblePokemon("Monferno",14,1,64,78,142,"Fire");
        addpossiblePokemon("Blaziken",36,2,80,120,239,"Fire");
        addpossiblePokemon("Victini",1,0,100,100,270,"Fire");
    }
    /**
     * Method Name: setOpponentPokemon(int lvl)
     * Description: Creates a random pokemon for the opponent and bases the level on the player's pokemon level
     * Adds moves for the pokemon based on type (Water, Fire, Grass) and resets the opponents life
     * 
     * @precondition: level>0
     * @param: int lvl
     * 
     * return: none
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    public void setOpponentPokemon(int lvl) {
        int r = (int)(Math.random()*9-4);
        int r2 = (int)(Math.random()*possiblePokemon.size());
        while (possiblePokemon.get(r2).getLvl() > r+lvl) {
            r2 = (int)(Math.random()*possiblePokemon.size());
        }
        opponentPokemon=possiblePokemon.get(r2);
        while (opponentPokemon.getLvl() < r+lvl) {
            opponentPokemon.lvlUP(45);
        }
        opponentPokemon.heal();
        if(opponentPokemon.getType().equals("Water"))
        {
            Moves bubble=new Moves("Bubble", 50);
            Moves waterp=new Moves("Water Pulse", 60);
            Moves hydroPump=new Moves("Hydro Pump", 80);
            Moves waterfall=new Moves("Waterfall", 70);
            opponentPokemon.addMoves(bubble);
            opponentPokemon.addMoves(waterp);
            opponentPokemon.addMoves(hydroPump);
            opponentPokemon.addMoves(waterfall);
        }
        if(opponentPokemon.getType().equals("Grass"))
        {
            Moves xScissor=new Moves("XScizzor", 60);
            Moves razorLeaf=new Moves("Razor Leaf", 50);
            Moves leafStorm=new Moves("Leaf Storm",80);
            Moves eball=new Moves("Energy Ball", 70);
            opponentPokemon.addMoves(razorLeaf);
            opponentPokemon.addMoves(eball);
            opponentPokemon.addMoves(leafStorm);
            opponentPokemon.addMoves(xScissor);
        }
        if(opponentPokemon.getType().equals("Fire"))
        {
            Moves burn=new Moves("Burn",50);
            Moves dclaw=new Moves("Dragon Claw", 60);
            Moves fireB=new Moves("Fire Blast",80);
            Moves firePunch=new Moves("Fire Punch", 70);
            opponentPokemon.addMoves(burn);
            opponentPokemon.addMoves(fireB);
            opponentPokemon.addMoves(firePunch);
            opponentPokemon.addMoves(firePunch);
        }
    }
    /**
     * Method Name: getOpponent()
     * Description: toString to display the opponent's rank and name
     * 
     * @precondition: none
     * @param: none
     * 
     * @return: none
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public String getOpponent() 
    {
        return rank + " " + name;
    }
    /**
     * Method Name: addpossiblePokemon()
     * Description: Adds a pokemon to the list of random pokemon the opponent may select
     * 
     * @precondition: level>0, evolution>0, hp>0, attack>0
     * @param: String n, int lvl, int e, int hp, int atk, int x, String t - 
     * n is name, lvl is level, e is evolution status, h is hitpoints (hp), a is attack, x is xp, t is type
     * @return: none
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public void addpossiblePokemon(String n, int lvl, int e, int hp, int atk, int x, String t)
    {           
        possiblePokemon.add(new Pokemon(n, lvl, e, hp, atk, x, t));
    }
    /**
     * Constructor Name: getOpponentPokemon()
     * Description: returns the opponent's pokemon
     * 
     * @precondition: none
     * @param: none
     * 
     * @return: opponentPokemon
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public Pokemon getOpponentPokemon(){
        return opponentPokemon;
    }
    /**
     * Method Name: getName()
     * Description: returns name of the opponent
     * 
     * @precondition: none
     * @param: none
     * 
     * @return: name
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public String getName(){
        return name;
    }
    /**
     * Method Name: getRank()
     * Description: returns rank of the opponent
     * 
     * @precondition: none
     * @param: none
     * 
     * @return: rank
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public String getRank(){
        return rank;
    }
    /**
     * Method Name: reset()
     * Description: resets the name and rank of the opponent
     * 
     * @precondition: none
     * @param: none
     * 
     * @return: none
     * 
     * @author: Jasmine Wang
     * @version May 2018
     */
    public void reset(){
        name = names[(int)(Math.random()*names.length)];
        rank = ranks[(int)(Math.random()*ranks.length)];    
    }
}
