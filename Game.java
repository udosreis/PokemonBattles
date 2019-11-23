import java.util.Scanner;
/**
 * Game.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class Game
{
    public static void main(String args[])
    {
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Welcome to Pokemon");
        System.out.println("Please enter your name: ");
        String name=scan1.nextLine();
        boolean efComplete = false;
        int battleCount=0;
        int gymBadges = 0;
        Player p1=new Player(name);
        Opponent o1=new Opponent();
        System.out.println("Hello there "+name+"! Welcome to the world of POKEMON! My name is OAK! People call me the POKEMON PROF!");
        System.out.println("Oh... I see you do not have any pokemon... WEll, your in luck. I actually have three Pokemon on me RIGHT NOW!");
        System.out.println("Please select the one you would like to keep. Take your time as this Pokemon you choose will be your ONLY Pokemon!");
        System.out.println("\nTurtwig\t\tChimchar\tPiplup");
        System.out.println("Select 1\tSelect 2\tSelect 3");
        Scanner scan2 = new Scanner(System.in);
        System.out.println("Enter your selection");
        int selection = scan2.nextInt();
        while(selection!=1 && selection!=2 && selection!=3)
        {
            System.out.println("Please select a Pokemon");
            selection = scan2.nextInt();
        }
        if(selection==1)
        {
            p1.setPokemon(new Pokemon("Turtwig", 15, 0, 55, 68, 64,"Grass"));
            Moves xScissor=new Moves("XScizzor", 60);
            Moves razorLeaf=new Moves("Razor Leaf", 50);
            Moves leafStorm=new Moves("Leaf Storm",80);
            Moves eball=new Moves("Energy Ball", 70);
            p1.getPokemon().addMoves(razorLeaf);
            p1.getPokemon().addMoves(eball);
            p1.getPokemon().addMoves(leafStorm);
            p1.getPokemon().addMoves(xScissor);
        }
        else if(selection ==2)
        {
            p1.setPokemon(new Pokemon("Chimchar", 15, 0, 45, 58, 62,"Fire"));
            Moves burn=new Moves("Burn",50);
            Moves dclaw=new Moves("Dragon Claw", 60);
            Moves fireB=new Moves("Fire Blast",80);
            Moves firePunch=new Moves("Fire Punch", 70);
            p1.getPokemon().addMoves(burn);
            p1.getPokemon().addMoves(fireB);
            p1.getPokemon().addMoves(firePunch);
            p1.getPokemon().addMoves(firePunch);
        }
        else
        {
            p1.setPokemon(new Pokemon("Piplup", 15, 0, 53, 51, 63, "Water"));
            Moves bubble=new Moves("Bubble", 50);
            Moves waterp=new Moves("Water Pulse", 60);
            Moves hydroPump=new Moves("Hydro Pump", 80);
            Moves waterfall=new Moves("Waterfall", 70);
            p1.getPokemon().addMoves(bubble);
            p1.getPokemon().addMoves(waterp);
            p1.getPokemon().addMoves(hydroPump);
            p1.getPokemon().addMoves(waterfall);
        }
        System.out.println("Oh... I see... So you have selected "+p1.getPokemon().getName()+"! That is a fine choice!");
        System.out.println("Alright, now your Pokemon adventure has begun. Be warned, your journey will be the struggle of a lifetime!");
        System.out.println("Let's GOOOOOOOO!\n");
        for(int x=0; x<51; x++)
        {
            System.out.print("* ");
        }
        while(!efComplete)
        {
            System.out.println("\n");
            System.out.println("Pokemon Party");
            System.out.println(p1.getPokemon());
            System.out.println("Battles completed: "+battleCount);
            System.out.println("What would you like to do? (Battle (B) Heal (H) Gym (G) Elite Four (EF))");
            Scanner scanOption = new Scanner(System.in);
            String option = scanOption.nextLine();
            while(!option.equals("B")&&!option.equals("H")&&!option.equals("G")&&!option.equals("EF"))
            {
                System.out.println("Invalid option. Please select again");
                option = scanOption.nextLine();
            }
            if(option.equals("B"))
            {
                o1.setOpponentPokemon(p1.getPokemon().getLvl());
                while(p1.getPokemon().getStatus() && o1.getOpponentPokemon().getStatus())
                {
                    System.out.println(p1.getPokemon());
                    System.out.println("\n"+o1.getOpponentPokemon());
                    System.out.println("\nWhat would you like to do? Attack(A) Heal(H)");
                    Scanner scanOption1 = new Scanner(System.in);
                    String optionBattle = scanOption1.nextLine();
                    while(!optionBattle.equals("A")&&!optionBattle.equals("H"))
                    {
                        System.out.println("Invalid option. Please select again");
                        optionBattle = scanOption.nextLine();
                    }
                    if(optionBattle.equals("A"))
                    {
                        Scanner scanOption2 = new Scanner(System.in);
                        System.out.println("Which move would you like to use?\n"+p1.getPokemon().getSpecMove(0).getName()+" (1)\n"+p1.getPokemon().getSpecMove(1).getName()+" (2)\n"+p1.getPokemon().getSpecMove(2).getName()+" (3)\n"+p1.getPokemon().getSpecMove(3).getName()+" (4)\n");
                        int optionMoves = scanOption.nextInt();
                        while(optionMoves!=1&&optionMoves!=2&&optionMoves!=3&&optionMoves!=4)
                        {
                            System.out.println("Invalid option. Please select again");
                            optionMoves = scanOption.nextInt();
                        }
                        System.out.println(p1.getPokemon().getName()+" used "+p1.getPokemon().getSpecMove(optionMoves-1).getName());
                        int critPlayer=1;
                        int chance1 = (int)(Math.random()*10);
                        if(chance1==5)
                        {
                            critPlayer=2;
                        }
                        int dmgPlayer = (int)((double)((((p1.getPokemon().getLvl()*2/5)+2)*p1.getPokemon().getSpecMove(optionMoves-1).getDmg()/50+2)*critPlayer*(double)(100-(int)(Math.random()*15+1))/100));
                        o1.getOpponentPokemon().changeHealth(dmgPlayer);
                        System.out.println(o1.getOpponentPokemon().getName()+" took "+dmgPlayer+" damage.");
                        o1.getOpponentPokemon().setStatus();
                        if(o1.getOpponentPokemon().getStatus())
                        {
                            int rand1 = (int)(Math.random()*4);
                            int critOpp=1;
                            int chance2 = (int)(Math.random()*10);
                            if(chance2==5)
                            {
                                critOpp=2;
                            }
                            int dmgOpp = (int)((double)((((o1.getOpponentPokemon().getLvl()*2/5)+2)*o1.getOpponentPokemon().getSpecMove(rand1).getDmg()/50+2)*critOpp*(double)(100-(int)(Math.random()*15+1))/100));
                            System.out.println(o1.getOpponentPokemon().getName()+" used "+o1.getOpponentPokemon().getSpecMove(rand1).getName());
                            p1.getPokemon().changeHealth(dmgOpp);
                            System.out.println(p1.getPokemon().getName()+" took "+dmgOpp+" damage.");
                            p1.getPokemon().setStatus();
                        }
                    }
                    if(optionBattle.equals("H"))
                    {
                        if(p1.getPokemon().getHealth()<p1.getPokemon().getMaxHP())
                        {
                            int maxHeal = p1.getPokemon().getMaxHP()-p1.getPokemon().getHealth();
                            int heal = (int)(Math.random()*p1.getPokemon().getMaxHP());
                            if(heal>maxHeal)
                            {
                                heal=maxHeal;
                            }
                            p1.getPokemon().changeHealth(-1*heal);
                            System.out.println(p1.getPokemon().getName()+" healed for "+heal+" points of health!");
                        }
                        else
                        {
                            System.out.println("You are already at Max HP");
                        }
                    }
                    if(p1.getPokemon().getStatus()&&!o1.getOpponentPokemon().getStatus())
                    {
                        int xp = (int)((double)(o1.getOpponentPokemon().getXPStat()) * (double)(o1.getOpponentPokemon().getLvl()) * 1.5 / 7.00);
                        p1.getPokemon().lvlUP(xp);
                        System.out.println("You win! Nice job");
                        System.out.println("\n*******************************************\n");
                        battleCount++;
                    }
                    else if(!p1.getPokemon().getStatus())
                    {
                        System.out.println("You have been defeated!!");
                    }
                }
            }
            else if(option.equals("H"))
            {
                System.out.println("Healing your Pokemon");
                p1.getPokemon().changeHealth((-1)*(p1.getPokemon().getMaxHP()-p1.getPokemon().getHealth()));
                p1.getPokemon().setStatus();
            }
            else if(option.equals("G"))
            {
                if(battleCount>=10)
                {
                    System.out.println("You have been challenged by Gym Leader "+(gymBadges+1));
                    o1.setOpponentPokemon(p1.getPokemon().getLvl());
                    while(p1.getPokemon().getStatus() && o1.getOpponentPokemon().getStatus())
                    {
                        System.out.println(p1.getPokemon());
                        System.out.println("\n"+o1.getOpponentPokemon());
                        System.out.println("\nWhat would you like to do? Attack(A) Heal(H)");
                        Scanner scanOption1 = new Scanner(System.in);
                        String optionBattle = scanOption.nextLine();
                        while(!optionBattle.equals("A")&&!optionBattle.equals("H"))
                        {
                            System.out.println("Invalid option. Please select again");
                            optionBattle = scanOption.nextLine();
                        }
                        if(optionBattle.equals("A"))
                        {
                            Scanner scanOption2 = new Scanner(System.in);
                            System.out.println("Which move would you like to use?\n"+p1.getPokemon().getSpecMove(0).getName()+" (1)\n"+p1.getPokemon().getSpecMove(1).getName()+" (2)\n"+p1.getPokemon().getSpecMove(2).getName()+" (3)\n"+p1.getPokemon().getSpecMove(3).getName()+" (4)\n");
                            int optionMoves = scanOption.nextInt();
                            while(optionMoves!=1&&optionMoves!=2&&optionMoves!=3&&optionMoves!=4)
                            {
                                System.out.println("Invalid option. Please select again");
                                optionMoves = scanOption.nextInt();
                            }
                            System.out.println(p1.getPokemon().getName()+" used "+p1.getPokemon().getSpecMove(optionMoves-1).getName());
                            int critPlayer=1;
                            int chance1 = (int)(Math.random()*10);
                            if(chance1==5)
                            {
                                critPlayer=2;
                            }
                            int dmgPlayer = (int)((double)((((p1.getPokemon().getLvl()*2/5)+2)*p1.getPokemon().getSpecMove(optionMoves-1).getDmg()/50+2)*critPlayer*(double)(100-(int)(Math.random()*15+1))/100));
                            o1.getOpponentPokemon().changeHealth(dmgPlayer);
                            System.out.println(o1.getOpponentPokemon().getName()+" took "+dmgPlayer+" damage.");
                            o1.getOpponentPokemon().setStatus();
                            if(o1.getOpponentPokemon().getStatus())
                            {
                                int rand1 = (int)(Math.random()*4);
                                int critOpp=1;
                                int chance2 = (int)(Math.random()*10);
                                if(chance2==5)
                                {
                                    critOpp=2;
                                }
                                int dmgOpp = (int)((double)((((o1.getOpponentPokemon().getLvl()*2/5)+2)*o1.getOpponentPokemon().getSpecMove(rand1).getDmg()/50+2)*critOpp*(double)(100-(int)(Math.random()*15+1))/100));
                                System.out.println(o1.getOpponentPokemon().getName()+" used "+o1.getOpponentPokemon().getSpecMove(rand1).getName());
                                p1.getPokemon().changeHealth(dmgOpp);
                                System.out.println(p1.getPokemon().getName()+" took "+dmgOpp+" damage.");
                                p1.getPokemon().setStatus();
                            }
                        }
                        if(optionBattle.equals("H"))
                        {
                            if(p1.getPokemon().getHealth()<p1.getPokemon().getMaxHP())
                            {
                                int maxHeal = p1.getPokemon().getMaxHP()-p1.getPokemon().getHealth();
                                int heal = (int)(Math.random()*p1.getPokemon().getMaxHP());
                                if(heal>maxHeal)
                                {
                                    heal=maxHeal;
                                }
                                p1.getPokemon().changeHealth(-1*heal);
                                System.out.println(p1.getPokemon().getName()+" healed for "+heal+" points of health!");
                            }
                            else
                            {
                                System.out.println("You are already at Max HP");
                            }
                        }
                    }
                    if(p1.getPokemon().getStatus())
                    {
                        int xp = (int)((double)(o1.getOpponentPokemon().getXPStat()) * (double)(o1.getOpponentPokemon().getLvl()) * 1.5 / 7.00);
                        p1.getPokemon().lvlUP(xp);
                        System.out.println("You have beaten Gym Leader "+(gymBadges+1)+" Nice job");
                        System.out.println("\n*******************************************\n");
                        gymBadges++;
                        battleCount=0;
                    }
                    else
                    {
                        System.out.println("You have been defeated!!");
                    }
                }
                else if(gymBadges >=8)
                {
                    System.out.println("You have already completed all the gyms. You can compete in the Elite Four now.");
                }
                else 
                {
                    System.out.println("You need to complete "+(10-battleCount)+ " more battles in order fight a gym leader!");
                }
            }
            else if(option.equals("EF"))
            {
                if(gymBadges==8)
                {
                    System.out.println("You have been challenged by Elite Four Champion LANCE");
                    o1.setOpponentPokemon(p1.getPokemon().getLvl());
                    while(p1.getPokemon().getStatus() && o1.getOpponentPokemon().getStatus())
                    {
                        System.out.println(p1.getPokemon());
                        System.out.println("\n"+o1.getOpponentPokemon());
                        System.out.println("\nWhat would you like to do? Attack(A) Heal(H)");
                        Scanner scanOption1 = new Scanner(System.in);
                        String optionBattle = scanOption.nextLine();
                        while(!optionBattle.equals("A")&&!optionBattle.equals("H"))
                        {
                            System.out.println("Invalid option. Please select again");
                            optionBattle = scanOption.nextLine();
                        }
                        if(optionBattle.equals("A"))
                        {
                            Scanner scanOption2 = new Scanner(System.in);
                            System.out.println("Which move would you like to use?\n"+p1.getPokemon().getSpecMove(0).getName()+" (1)\n"+p1.getPokemon().getSpecMove(1).getName()+" (2)\n"+p1.getPokemon().getSpecMove(2).getName()+" (3)\n"+p1.getPokemon().getSpecMove(3).getName()+" (4)\n");
                            int optionMoves = scanOption.nextInt();
                            while(optionMoves!=1&&optionMoves!=2&&optionMoves!=3&&optionMoves!=4)
                            {
                                System.out.println("Invalid option. Please select again");
                                optionMoves = scanOption.nextInt();
                            }
                            System.out.println(p1.getPokemon().getName()+" used "+p1.getPokemon().getSpecMove(optionMoves-1).getName());
                            int critPlayer=1;
                            int chance1 = (int)(Math.random()*10);
                            if(chance1==5)
                            {
                                critPlayer=2;
                            }
                            int dmgPlayer = (int)((double)((((p1.getPokemon().getLvl()*2/5)+2)*p1.getPokemon().getSpecMove(optionMoves-1).getDmg()/50+2)*critPlayer*(double)(100-(int)(Math.random()*15+1))/100));
                            o1.getOpponentPokemon().changeHealth(dmgPlayer);
                            System.out.println(o1.getOpponentPokemon().getName()+" took "+dmgPlayer+" damage.");
                            o1.getOpponentPokemon().setStatus();
                            if(o1.getOpponentPokemon().getStatus())
                            {
                                int rand1 = (int)(Math.random()*4);
                                int critOpp=1;
                                int chance2 = (int)(Math.random()*10);
                                if(chance2==5)
                                {
                                    critOpp=2;
                                }
                                int dmgOpp = (int)((double)((((o1.getOpponentPokemon().getLvl()*2/5)+2)*o1.getOpponentPokemon().getSpecMove(rand1).getDmg()/50+2)*critOpp*(double)(100-(int)(Math.random()*15+1))/100));
                                System.out.println(o1.getOpponentPokemon().getName()+" used "+o1.getOpponentPokemon().getSpecMove(rand1).getName());
                                p1.getPokemon().changeHealth(dmgOpp);
                                System.out.println(p1.getPokemon().getName()+" took "+dmgOpp+" damage.");
                                p1.getPokemon().setStatus();
                            }
                        }
                        if(optionBattle.equals("H"))
                        {
                            if(p1.getPokemon().getHealth()<p1.getPokemon().getMaxHP())
                            {
                                int maxHeal = p1.getPokemon().getMaxHP()-p1.getPokemon().getHealth();
                                int heal = (int)(Math.random()*p1.getPokemon().getMaxHP());
                                if(heal>maxHeal)
                                {
                                    heal=maxHeal;
                                }
                                p1.getPokemon().changeHealth(-1*heal);
                                System.out.println(p1.getPokemon().getName()+" healed for "+heal+" points of health!");
                            }
                            else
                            {
                                System.out.println("You are already at Max HP");
                            }
                        }
                    }
                    if(p1.getPokemon().getStatus())
                    {
                        int xp = (int)((double)(o1.getOpponentPokemon().getXPStat()) * (double)(o1.getOpponentPokemon().getLvl()) * 1.5 / 7.00);
                        p1.getPokemon().lvlUP(xp);
                        System.out.println("You have beaten the Pokemon Champion LANCE! You are now the new POKEMON CHAMPION!!");
                        efComplete=true;
                    }
                    else
                    {
                        System.out.println("You have been defeated!!");
                    }
                }
                else
                {
                    System.out.println("You need to beat all gym leaders!");
                }
            }
        }
        System.out.println("Thank you for playing!");
    }
}

