import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;
/**
 * Battle.java  
 *
 * @author: Ugo Dos Reis
 * 
 * This program will create the game and draw everything. The player will be able to pick a starting pokemon and start battles
 * against the computer which will have random pokemons with a level around the one of the player's pokemon. 
 * 
 *
 */
public class Battle extends Applet implements ActionListener,MouseListener
{
    //Variables and Objects
    private Player p;
    private Opponent o = new Opponent();

    private int stage = 0;
    private int xMouse;
    private int yMouse;

    private int pokemonLogoX;
    private double logoCF= 0.25;

    private int wait = 0;
    private int battleCount = 300;

    private int bckgNum;
    private int moveNum;

    private int atckNum;
    private int target;

    private double atckX;
    private double atckY;
    private int atckXS;
    private int atckYS;

    //Images
    private BufferedImage pokemonLogo;
    private BufferedImage pokeball;
    private BufferedImage backgroundIMG;
    private BufferedImage prof;
    private BufferedImage textBox;
    private BufferedImage standingloc;
    private BufferedImage battlebckg;
    private BufferedImage battleTextBox;
    private BufferedImage healthBar;
    private BufferedImage enemyHealthBar;
    private BufferedImage attackBox;
    private BufferedImage opponentIMG;
    private BufferedImage attack;
    //Pokemon
    private BufferedImage piplup;
    private BufferedImage prinplup;
    private BufferedImage magikarp;
    private BufferedImage gyrados;
    private BufferedImage carracosta;
    private BufferedImage turtwig;
    private BufferedImage chikarita;
    private BufferedImage grotle;
    private BufferedImage sceptile;
    private BufferedImage treecko;
    private BufferedImage chimchar;
    private BufferedImage ponyta;
    private BufferedImage monferno;
    private BufferedImage blaziken;
    private BufferedImage victini;
    private BufferedImage pPokemon;

    //Music
    private Clip clip;
    private Clip beep;

    //Private Timer
    private Timer timer;

    /****************************************************
     * The following code should NOT be changed for any
     * reason. Doing so will prevent the app from running
     ****************************************************/
    public boolean debugging;
    public void debug(int width, int height, String pName) {
        p = new Player(pName);;
        Applet applet = this;
        debugging = true;
        String windowTitle = applet.getClass().getName();
        //System.out.println(windowTitle + " created " + new Date());
        JFrame frame = new JFrame(windowTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height + 20);
        applet.setSize(width, height);
        frame.add(applet);
        applet.init();      // simulate browser call(1)
        applet.start();      // simulate browser call(2)
        frame.setVisible(true);
    }  

    public boolean debugging() {
        return debugging;
    }

    //Code
    /**
     * Method name: init()
     * Description: gets called when the applet is created. It sets all the pokemon
     * to their corresponding image and gets the first song started
     * 
     * @precondition: none
     * @param: none
     * @return: none
     * 
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    public void init()
    { 
        //Listeners
        addMouseListener(this);
        //Timer
        timer=new Timer(10,this);
        timer.start();
        try {
            //Images
            pokemonLogo = ImageIO.read(new File("Images/pokemonLogo.png"));
            pokeball = ImageIO.read(new File("Images/pokeBall.png"));
            backgroundIMG = ImageIO.read(new File("Images/Background.jpg"));
            prof = ImageIO.read(new File("Images/Prof.png"));
            textBox = ImageIO.read(new File("Images/textBox1.png"));
            battleTextBox = ImageIO.read(new File("Images/textBoxBattle.png"));
            healthBar = ImageIO.read(new File("Images/healthBar.png"));
            enemyHealthBar = ImageIO.read(new File("Images/EnemyHP.png"));
            //Pokemon
            piplup = ImageIO.read(new File("Images/Water/Piplup.gif"));
            turtwig = ImageIO.read(new File("Images/Grass/Turtwig.gif"));
            chimchar = ImageIO.read(new File("Images/Fire/Chimchar.gif"));

            chikarita = ImageIO.read(new File("Images/Grass/Chikarita.gif"));
            sceptile = ImageIO.read(new File("Images/Grass/Sceptile.gif"));
            treecko = ImageIO.read(new File("Images/Grass/Treecko.gif"));
            grotle = ImageIO.read(new File("Images/Grass/Grotle.gif"));

            carracosta = ImageIO.read(new File("Images/Water/Carracosta.gif"));
            magikarp = ImageIO.read(new File("Images/Water/Magikarp.gif"));
            prinplup = ImageIO.read(new File("Images/Water/Prinplup.gif"));
            gyrados = ImageIO.read(new File("Images/Water/Gyrados.gif"));

            blaziken = ImageIO.read(new File("Images/Fire/Blaziken.gif"));
            monferno = ImageIO.read(new File("Images/Fire/Monferno.gif"));
            ponyta = ImageIO.read(new File("Images/Fire/Ponyta.gif"));
            victini = ImageIO.read(new File("Images/Fire/Victini.gif"));

            //Music
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Audio/pokemonTheme.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch(Exception e) {
            System.out.println("Error loading content...");
        }
        try {
            AudioInputStream beepInputStream = AudioSystem.getAudioInputStream(new File("Audio/beep.wav"));
            beep = AudioSystem.getClip();
            beep.open(beepInputStream);
        }
        catch(Exception e) {
            System.out.println("Failed at starting intro music");
        } 
        //Variables
        pokemonLogoX = 290;
    }

    public void actionPerformed(ActionEvent ae){ //Repaints
        repaint();
    }

    public void mouseExited(MouseEvent me){ //draws when leave the screen

    }

    public void mouseEntered(MouseEvent me){ //draws when enter the screen

    }

    public void mouseReleased(MouseEvent me){ //draws when mouse released

    }

    public void mousePressed(MouseEvent me){ //draws when moused pressed

    }

    public void mouseClicked(MouseEvent me){ //draws when pressed and released at same spot
        xMouse=me.getX();
        yMouse=me.getY();
    }

    /**
     * Method Name: paint()
     * Description: draws and controls the whole game. It also accesses all the classes
     * to be able to get all the methods needed and create the needed objects
     * 
     * @precondition: none
     * @param: Graphics g
     * @return: none
     * 
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    public void paint(Graphics g)
    {
        if (stage == 0) { //Homescreen
            g.drawImage(backgroundIMG,0,0,1280,720,this);
            Color nameColor = new Color(33,47,136);
            g.setColor(nameColor);
            g.setFont(new Font("Arial",Font.BOLD,18));
            g.drawString("Name: " + p.getName(),5,17);

            g.drawImage(pokemonLogo,pokemonLogoX,25,701,263,this);
            g.drawImage(pokeball,565,350,150,150,this);
            //g.drawImage(pikachuGIF,756,450,144,125,this);
            if (pokemonLogoX >= 350 && logoCF > 0) {
                logoCF*=-1;
            }
            if (pokemonLogoX <= 230 && logoCF < 0) {
                logoCF*=-1;
            }
            pokemonLogoX += logoCF;
            if (xMouse >= 565 && xMouse <= 715 && yMouse >= 350 && yMouse <= 500) {
                stage = 1;
                clip.stop();
                playBeep();
                playIntro();
                wait = 1;
                xMouse = -100;
                yMouse = -100;
            }
        }
        else if (stage == 1) { //Makes the player choose his pokemon
            g.drawImage(piplup,0,315,400,400,this);
            g.drawImage(pokeball,163,610,50,50,this);
            g.drawImage(turtwig,440,300,400,400,this);
            g.drawImage(pokeball,603,610,50,50,this);
            g.drawImage(chimchar,880,300,400,400,this);
            g.drawImage(pokeball,1043,610,50,50,this);

            g.drawImage(prof,264,50,176,264,this);

            Color barColor = new Color(33,47,136);
            g.setColor(barColor);
            g.fillRect(50,350,1180,5);
            g.drawImage(textBox,500,50,697,169,this);
            g.setColor(Color.BLACK);

            g.setFont(new Font("Arial",Font.BOLD,26));
            g.drawString("Hi, " + p.getName() + "! Welcome to the world of Pokemon!",535,115);
            g.drawString("I am Professor Oak and I want you to complete",535,150);
            g.drawString("the pokedex! Please pick a pokemon!",535,185);
            if (wait == 1) {
                try        
                {
                    Thread.sleep(250);
                } 
                catch(InterruptedException ex) 
                {
                    System.out.println("Failed to pause for one second");
                }   
                wait = 0;
                xMouse = -100;
                yMouse = -100;
            }
            else {
                if (xMouse >= 0 && xMouse <= 400 && yMouse >= 350 && yMouse <= 750) {
                    stage = 2;
                    p.setPokemon(new Pokemon("Piplup",1,0,53,51,63,"Water"));
                    p.getPokemon().addMoves(new Moves("Hydro Pump", 80));
                    p.getPokemon().addMoves(new Moves("Water Pulse", 60));
                    p.getPokemon().addMoves(new Moves("Waterfall", 70));
                    p.getPokemon().addMoves(new Moves("Bubble",50));
                    p.getPokemon().lvlUP(630);
                    try {
                        pPokemon = ImageIO.read(new File("Images/Water/Piplup.gif"));
                    }
                    catch(Exception e) {
                        System.out.println("Error loading content...");
                    }
                    playBeep();
                    wait = 1;
                    xMouse = -100;
                    yMouse = -100;
                }
                if (xMouse >= 440 && xMouse <= 840 && yMouse >= 350 && yMouse <= 750) {
                    stage = 2;
                    p.setPokemon(new Pokemon("Turtwig",1,0,55,68,64,"Grass"));
                    p.getPokemon().addMoves(new Moves("Razor Leaf", 50));
                    p.getPokemon().addMoves(new Moves("XScizzor", 60));
                    p.getPokemon().addMoves(new Moves("Energy Ball", 70));
                    p.getPokemon().addMoves(new Moves("Leaf Storm",80));
                    p.getPokemon().lvlUP(630);//630
                    try {
                        pPokemon = ImageIO.read(new File("Images/Grass/Turtwig.gif"));
                    }
                    catch(Exception e) {
                        System.out.println("Error loading content...");
                    }
                    playBeep();
                    wait = 1;
                    xMouse = -100;
                    yMouse = -100;
                }
                if (xMouse >= 880 && xMouse <= 1280 && yMouse >= 350 && yMouse <= 750) {
                    stage = 2;
                    p.setPokemon(new Pokemon("Chimchar",1,0,44,58,62,"Fire"));
                    p.getPokemon().addMoves(new Moves("Burn", 50));
                    p.getPokemon().addMoves(new Moves("Fire Punch", 70));
                    p.getPokemon().addMoves(new Moves("Dragon Claw", 60));
                    p.getPokemon().addMoves(new Moves("Fire Blast",80));
                    p.getPokemon().lvlUP(630);
                    try {
                        pPokemon = ImageIO.read(new File("Images/Fire/Chimchar.gif"));
                    }
                    catch(Exception e) {
                        System.out.println("Error loading content...");
                    }
                    playBeep();
                    wait = 1;
                    xMouse = -100;
                    yMouse = -100;
                }
            }
        }
        else if (stage == 2) { //Says good choice
            g.drawImage(prof,264,50,176,264,this);
            Color barColor = new Color(33,47,136);
            g.setColor(barColor);
            g.fillRect(50,350,1180,5);
            g.drawImage(textBox,500,50,697,169,this);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial",Font.BOLD,26));
            g.drawString(p.getPokemon().getName() + "! Great choice! You are on your way to",535,115);
            g.drawString("be a pokemon trainer! You and your new friend",535,150);
            g.drawString("will have lots of fun on your journey!",535,185);
            if (wait == 1) {
                try        
                {
                    Thread.sleep(250);
                } 
                catch(InterruptedException ex) 
                {
                    System.out.println("Failed to pause for one second");
                }   
                wait = 0;
                xMouse = -100;
                yMouse = -100;
            }
            g.drawImage(pPokemon,340,315,400,400,this);
            g.drawImage(pokeball,700,490,75,75,this);
            if (xMouse >= 700 && xMouse <= 775 && yMouse >= 490 && yMouse <= 565) {
                stage = 3;
                wait = 5;
                clip.stop();
                playBeep();
                playBattle();
                o.setOpponentPokemon(p.getPokemon().getLvl());
                if (o.getName().equals("Aaron")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Aaron.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                else if (o.getName().equals("Byron")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Byron.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                else if (o.getName().equals("Cynthia")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Cynthia.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                else if (o.getName().equals("Cyrus")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Cyrus.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                else if (o.getName().equals("Mars")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Mars.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                bckgNum = (int)(Math.random()*4);
                if (bckgNum == 0) {
                    try {
                        standingloc = ImageIO.read(new File("Images/grassP.png"));
                        battlebckg = ImageIO.read(new File("Images/grass.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading bckg");
                    } 
                }
                else if (bckgNum == 1) {
                    try {
                        standingloc = ImageIO.read(new File("Images/waterP.png"));
                        battlebckg = ImageIO.read(new File("Images/water.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading bckg");
                    } 
                }
                else if (bckgNum == 2) {
                    try {
                        standingloc = ImageIO.read(new File("Images/caveP.png"));
                        battlebckg = ImageIO.read(new File("Images/cave.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading bckg");
                    } 
                }
                else {
                    try {
                        standingloc = ImageIO.read(new File("Images/sandP.png"));
                        battlebckg = ImageIO.read(new File("Images/sand.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading bckg");
                    } 
                }
                if (p.getPokemon().getType().equals("Water")) {
                    try {
                        attackBox = ImageIO.read(new File("Images/WaterAttackBox.png"));
                    }
                    catch (Exception e) {
                        System.out.println("Error loading attackbox");
                    }
                }
                else if (p.getPokemon().getType().equals("Fire")) {
                    try {
                        attackBox = ImageIO.read(new File("Images/FireAttackBox.png"));
                    }
                    catch (Exception e) {
                        System.out.println("Error loading attackbox");
                    }
                }
                else {
                    try {
                        attackBox = ImageIO.read(new File("Images/GrassAttackBox.png"));
                    }
                    catch (Exception e) {
                        System.out.println("Error loading attackbox");
                    }
                }
                xMouse = -100;
                yMouse = -100;
            }
        }
        else if (stage == 3) { //Battle stage
            g.drawImage(battlebckg,0,0,1024,576,this);
            if (wait == 2 && target == 1 && atckNum == 8) {
                try {
                    g.drawImage(ImageIO.read(new File("Images/Attacks/FireB/bckg.png")),0,0,1024,576,this);
                }
                catch (Exception e) {
                    System.out.println("Error loading attackbox");
                }
            }
            if ((wait == 2 || wait == 1)  && atckNum == 2) {
                try {
                    g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/bckg.png")),0,0,1024,576,this);
                }
                catch (Exception e) {
                    System.out.println("Error loading attackbox");
                }
            }
            if ((wait == 2 || wait == 1)  && atckNum == 11) {
                try {
                    g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/bckg.png")),0,0,1024,576,this);
                }
                catch (Exception e) {
                    System.out.println("Error loading attackbox");
                }
            }
            if ((wait == 2 || wait == 1)  && atckNum == 12) {
                try {
                    g.drawImage(ImageIO.read(new File("Images/Attacks/HydroP/bckg.png")),0,0,1024,576,this);
                }
                catch (Exception e) {
                    System.out.println("Error loading attackbox");
                }
            }
            g.drawImage(standingloc,0,288,1024,288,this);

            //Draw Players pokemon
            if (p.getPokemon().getHealth() > 0) {
                g.drawImage(pPokemon,400,345,-400,400,this);
            }

            //Draw enemy pokemon
            if (o.getOpponentPokemon().getHealth() > 0 && wait != -3) {
                if (o.getOpponentPokemon().getName().equals("Piplup")) {
                    g.drawImage(piplup,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Carracosta")) {
                    g.drawImage(carracosta,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Gyrados")) {
                    g.drawImage(gyrados,575,25,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Magikarp")) {
                    g.drawImage(magikarp,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Prinplup")) {
                    g.drawImage(prinplup,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Chikorita")) {
                    g.drawImage(chikarita,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Grotle")) {
                    g.drawImage(grotle,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Treecko")) {
                    g.drawImage(treecko,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Sceptile")) {
                    g.drawImage(sceptile,575,25,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Turtwig")) {
                    g.drawImage(turtwig,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Blaziken")) {
                    g.drawImage(blaziken,575,25,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Chimchar")) {
                    g.drawImage(chimchar,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Monferno")) {
                    g.drawImage(monferno,575,50,400,400,this);
                }
                else if (o.getOpponentPokemon().getName().equals("Ponyta")) {
                    g.drawImage(ponyta,575,50,400,400,this);
                }
                else {
                    g.drawImage(victini,575,50,400,400,this);
                }
            }

            //Attack Graphics
            if (wait == 1 || wait == 2) {
                if (atckNum==12) {
                    if (battleCount > 230 && battleCount < 250) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/1.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 210 && battleCount < 230) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/2.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 190 && battleCount < 210) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/3.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 170 && battleCount < 190) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/4.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 150 && battleCount < 170) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/5.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 130 && battleCount < 150) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/6.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 110 && battleCount < 130) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/7.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 90 && battleCount < 110) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/8.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 70 && battleCount < 90) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/9.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 50 && battleCount < 70) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/10.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 30 && battleCount < 50) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/11.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 10 && battleCount < 30) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/HydroP/12.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    if (target==1) {
                        g.drawImage(attack,200,150,775,610,this);
                    }
                    else if (target==0) {
                        g.drawImage(attack,975,760,-775,-610,this);
                    }
                }
                if (atckNum==11) {
                    if (target==1) {
                        if (battleCount > 290) {
                            atckX = 775;
                            atckY = -320;
                            atckXS = 30;
                            atckYS = 30;
                        }
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/Waterfall/1.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                        if (atckY < 225) {
                            atckY += 4;
                        }
                        if (atckY > 200) {
                            try {
                                g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/3.png")),(int)(atckX)+10,(int)(atckY)-20,60,60,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/2.png")),(int)(atckX)+30,(int)(atckY)+20,50,50,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/2.png")),(int)(atckX)-25,(int)(atckY)+45,40,40,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/3.png")),(int)(atckX)+45,(int)(atckY)-45,30,30,this);
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                        }
                        else {
                            g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)+35,(int)(atckY)-35,atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)-30,(int)(atckY)+15,atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                        }
                    }
                    else if (target==0) {
                        if (battleCount > 290) {
                            atckX = 200;
                            atckY = 0;
                            atckXS = 30;
                            atckYS = 30;
                        }
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/Waterfall/1.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                        if (atckY < 545) {
                            atckY += 4;
                        }
                        if (atckY > 500) {
                            try {
                                g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/3.png")),(int)(atckX)+10,(int)(atckY)-20,60,60,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/2.png")),(int)(atckX)+30,(int)(atckY)+20,50,50,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/2.png")),(int)(atckX)-25,(int)(atckY)+45,40,40,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/Waterfall/3.png")),(int)(atckX)+45,(int)(atckY)-45,30,30,this);
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                        }
                        else {
                            g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)+35,(int)(atckY)-35,atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)-30,(int)(atckY)+15,atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                        }
                    }
                }
                if (atckNum==1) {
                    try {
                        attack = ImageIO.read(new File("Images/Attacks/XScizzor.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Error loading attack...");
                    }
                    if (target==1) {
                        if (battleCount < 250) {
                            g.drawImage(attack,625,62,300,300,this);
                        }
                        if (battleCount < 150) {
                            g.drawImage(attack,950,62,-300,300,this);
                        }
                    }
                    else if (target==0) {
                        if (battleCount < 250) {
                            g.drawImage(attack,50,370,300,300,this);
                        }
                        if (battleCount < 150) {
                            g.drawImage(attack,350,370,-300,300,this);
                        }
                    }
                }
                if (atckNum==2) {
                    if (target==1) {
                        if (battleCount > 290) {
                            atckX = 225;
                            atckY = 545;
                            atckXS = 18;
                            atckYS = 16;
                        }
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/LeafS/Leaf.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                        if (atckX < 775) {
                            atckX += 7;
                        }
                        if (atckY > 225) {
                            atckY -= 4;
                        }
                        if (atckY < 270 && atckX > 730) {
                            try {
                                g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/2.png")),(int)(atckX)+10,(int)(atckY)-20,60,60,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/1.png")),(int)(atckX)+30,(int)(atckY)+20,50,50,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/2.png")),(int)(atckX)-25,(int)(atckY)+45,40,40,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/1.png")),(int)(atckX)+45,(int)(atckY)-45,30,30,this);
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                        }
                        else {
                            g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)+35,(int)(atckY)-35,atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)-30,(int)(atckY)+15,atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                        }
                    }
                    else if (target==0) {
                        if (battleCount > 290) {
                            atckX = 775;
                            atckY = 225;
                            atckXS = 18;
                            atckYS = 16;
                        }
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/LeafS/LeafO.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                        if (atckX > 200) {
                            atckX -= 7;
                        }
                        if (atckY < 545) {
                            atckY += 4;
                        }
                        if (atckY > 525 && atckX < 245) {
                            try {
                                g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/2.png")),(int)(atckX)+10,(int)(atckY)-20,60,60,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/1.png")),(int)(atckX)+30,(int)(atckY)+20,50,50,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/2.png")),(int)(atckX)-25,(int)(atckY)+45,40,40,this);
                                g.drawImage(ImageIO.read(new File("Images/Attacks/LeafS/1.png")),(int)(atckX)+45,(int)(atckY)-45,30,30,this);
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                        }
                        else {
                            g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)+35,(int)(atckY)-35,atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)-30,(int)(atckY)+15,atckXS,atckYS,this);
                            g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                        }
                    }
                }
                if (atckNum==6) {
                    if (target==1) {
                        if (battleCount > 290) {
                            atckX = 225;
                            atckY = 545;
                            atckXS = 20;
                            atckYS = 19;
                        }
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/Bubble/Bubble.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                        if (atckX < 775) {
                            atckX += 7;
                        }
                        if (atckY > 225) {
                            atckY -= 4;
                        }
                        g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+35,(int)(atckY)-35,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)-30,(int)(atckY)+15,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)-20,(int)(atckY)+20,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                    }
                    else if (target==0) {
                        if (battleCount > 290) {
                            atckX = 775;
                            atckY = 225;
                            atckXS = 18;
                            atckYS = 16;
                        }
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/Bubble/Bubble.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                        if (atckX > 200) {
                            atckX -= 7;
                        }
                        if (atckY < 545) {
                            atckY += 4;
                        }
                        g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+35,(int)(atckY)-35,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)-30,(int)(atckY)+15,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)-20,(int)(atckY)+20,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                    }
                }
                if (atckNum==9) {
                    if (target==1) {
                        if (battleCount > 290) {
                            atckX = 225;
                            atckY = 545;
                            atckXS = 18;
                            atckYS = 16;
                        }
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/RazorL/Leaf.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                        if (atckX < 775) {
                            atckX += 7;
                        }
                        if (atckY > 225) {
                            atckY -= 4;
                        }
                        g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+35,(int)(atckY)-35,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)-30,(int)(atckY)+15,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                    }
                    else if (target==0) {
                        if (battleCount > 290) {
                            atckX = 775;
                            atckY = 225;
                            atckXS = 18;
                            atckYS = 16;
                        }
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/RazorL/LeafO.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                        if (atckX > 200) {
                            atckX -= 7;
                        }
                        if (atckY < 545) {
                            atckY += 4;
                        }
                        g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+35,(int)(atckY)-35,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)-30,(int)(atckY)+15,atckXS,atckYS,this);
                        g.drawImage(attack,(int)(atckX)+30,(int)(atckY)+50,atckXS,atckYS,this);
                    }
                }
                if (atckNum==3) {
                    if (battleCount > 220 && battleCount < 250) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/DragonC/1.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 190 && battleCount < 220) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/DragonC/2.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 160 && battleCount < 190) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/DragonC/3.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 130 && battleCount < 160) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/DragonC/4.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 100 && battleCount < 130) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/DragonC/5.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 70 && battleCount < 100) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/DragonC/6.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 40 && battleCount < 70) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/DragonC/7.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 10 && battleCount < 40) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/DragonC/8.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    if (target==1) {
                        g.drawImage(attack,671,194,208,174,this);
                    }
                    else if (target==0) {
                        g.drawImage(attack,96,479,208,174,this);
                    }
                }
                if (atckNum==4) {
                    if (battleCount > 200 && battleCount < 250) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/FireP/1.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 150 && battleCount < 200) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/FireP/2.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 100 && battleCount < 150) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/FireP/3.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 50 && battleCount < 100) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/FireP/4.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    else if (battleCount > 10 && battleCount < 50) {
                        try {
                            attack = ImageIO.read(new File("Images/Attacks/FireP/5.png"));
                        }
                        catch(Exception e) {
                            System.out.println("Error loading attack...");
                        }
                    }
                    if (target==1) {
                        g.drawImage(attack,709,200,133,133,this);
                        try {
                            g.drawImage(ImageIO.read(new File("Images/Attacks/FireP/Fist.png")),729,150,92,74,this);
                        }
                        catch (Exception e) {
                            System.out.println("Error loading attackbox");
                        }
                    }
                    else if (target==0) {
                        g.drawImage(attack,134,475,133,133,this);
                        try {
                            g.drawImage(ImageIO.read(new File("Images/Attacks/FireP/Fist.png")),154,475,92,74,this);
                        }
                        catch (Exception e) {
                            System.out.println("Error loading attackbox");
                        }
                    }
                }
                if (atckNum==5) {
                    if (target==1) {
                        if (battleCount > 200 && battleCount < 250) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn1.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,728,208,95,185,this);
                        }
                        else if (battleCount > 150 && battleCount < 200) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn2.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,738,225,75,150,this);
                        }
                        else if (battleCount > 100 && battleCount < 150) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn3.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,743,239,65,125,this);
                        }
                        else if (battleCount > 50 && battleCount < 100) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn4.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,750,250,50,100,this);
                        }
                        else if (battleCount > 10 && battleCount < 50) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn5.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,755,263,40,75,this);
                        }
                    }
                    else if (target==0) {
                        if (battleCount > 200 && battleCount < 250) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn1.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,203,420,95,185,this);
                        }
                        else if (battleCount > 150 && battleCount < 200) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn2.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,213,437,75,150,this);
                        }
                        else if (battleCount > 100 && battleCount < 150) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn3.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,218,451,65,125,this);
                        }
                        else if (battleCount > 50 && battleCount < 100) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn4.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,225,462,50,100,this);
                        }
                        else if (battleCount > 10 && battleCount < 50) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Burn/Burn5.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,230,475,40,75,this);
                        }
                    }
                }
                if (atckNum==7) {
                    if (target==1) {
                        if (battleCount > 290) {
                            atckX = 225;
                            atckY = 545;
                            atckXS = 11;
                            atckYS = 11;
                        }
                        if (battleCount > 200 && battleCount < 250) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water1.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 11;
                            atckYS = 11;
                        }
                        if (battleCount > 150 && battleCount < 200) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water2.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 10;
                            atckYS = 11;
                        }
                        if (battleCount > 100 && battleCount < 150) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water3.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 11;
                            atckYS = 10;
                        }
                        if (battleCount > 60 && battleCount < 100) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water4.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 11;
                            atckYS = 10;
                        }
                        if (battleCount > 10 && battleCount < 60) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water5.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS+=3;
                            atckYS+=3;
                            atckX -= 3;
                            atckY -= 3;
                        }
                        if (atckX < 775) {
                            atckX += 3.5;
                        }
                        if (atckY > 225) {
                            atckY -= 2;
                        }
                        g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                    }
                    else if (target==0) {
                        if (battleCount > 290) {
                            atckX = 775;
                            atckY = 225;
                            atckXS = 11;
                            atckYS = 11;
                        }
                        if (battleCount > 200 && battleCount < 250) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water1.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 11;
                            atckYS = 11;
                        }
                        if (battleCount > 150 && battleCount < 200) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water2.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 10;
                            atckYS = 11;
                        }
                        if (battleCount > 100 && battleCount < 150) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water3.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 11;
                            atckYS = 10;
                        }
                        if (battleCount > 60 && battleCount < 100) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water4.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 11;
                            atckYS = 10;
                        }
                        if (battleCount > 10 && battleCount < 60) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/WaterPulse/Water5.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS+=3;
                            atckYS+=3;
                            atckX -= 3;
                            atckY -= 3;
                        }
                        if (atckX > 200) {
                            atckX -= 3.5;
                        }
                        if (atckY < 545) {
                            atckY += 2;
                        }
                        g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                    }
                }
                if (atckNum==10) {
                    if (target==1) {
                        if (battleCount > 290) {
                            atckX = 225;
                            atckY = 545;
                            atckXS = 11;
                            atckYS = 11;
                        }
                        if (battleCount > 200 && battleCount < 250) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/1.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 9;
                            atckYS = 5;
                        }
                        if (battleCount > 150 && battleCount < 200) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/2.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 17;
                            atckYS = 16;
                        }
                        if (battleCount > 100 && battleCount < 150) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/3.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 16;
                            atckYS = 18;
                        }
                        if (battleCount > 60 && battleCount < 100) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/4.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 27;
                            atckYS = 24;
                        }
                        if (battleCount > 10 && battleCount < 60) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/5.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 24;
                            atckYS = 26;
                        }
                        if (atckX < 775) {
                            atckX += 3.5;
                        }
                        if (atckY > 225) {
                            atckY -= 2;
                        }
                        g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                    }
                    else if (target==0) {
                        if (battleCount > 290) {
                            atckX = 775;
                            atckY = 225;
                            atckXS = 11;
                            atckYS = 11;
                        }
                        if (battleCount > 200 && battleCount < 250) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/1.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 9;
                            atckYS = 5;
                        }
                        if (battleCount > 150 && battleCount < 200) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/2.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 17;
                            atckYS = 16;
                        }
                        if (battleCount > 100 && battleCount < 150) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/3.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 16;
                            atckYS = 18;
                        }
                        if (battleCount > 60 && battleCount < 100) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/4.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 27;
                            atckYS = 24;
                        }
                        if (battleCount > 10 && battleCount < 60) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/Eball/5.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            atckXS = 24;
                            atckYS = 26;
                        }
                        if (atckX > 200) {
                            atckX -= 3.5;
                        }
                        if (atckY < 545) {
                            atckY += 2;
                        }
                        g.drawImage(attack,(int)(atckX),(int)(atckY),atckXS,atckYS,this);
                    }
                }
                if (atckNum==8) {
                    if (target==1) {
                        if (battleCount > 210 && battleCount < 250) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/1.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,731,206,88,88,this);
                        }
                        else if (battleCount > 170 && battleCount < 210) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/2.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,703,178,145,145,this);
                        }
                        else if (battleCount > 130 && battleCount < 170) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/3.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,703,178,145,145,this);
                        }
                        else if (battleCount > 90 && battleCount < 130) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/4.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,703,178,145,145,this);
                        }
                        else if (battleCount > 50 && battleCount < 90) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/5.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,703,178,145,145,this);
                        }
                        else if (battleCount > 10 && battleCount < 50) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/6.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,703,178,145,145,this);
                        }
                    }
                    else if (target==0) {
                        if (battleCount > 210 && battleCount < 250) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/1.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,186,501,88,88,this);
                        }
                        else if (battleCount > 170 && battleCount < 210) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/2.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,158,473,145,145,this);
                        }
                        else if (battleCount > 130 && battleCount < 170) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/3.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,158,473,145,145,this);
                        }
                        else if (battleCount > 90 && battleCount < 130) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/4.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,158,473,145,145,this);
                        }
                        else if (battleCount > 50 && battleCount < 90) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/5.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,158,473,145,145,this);
                        }
                        else if (battleCount > 10 && battleCount < 50) {
                            try {
                                attack = ImageIO.read(new File("Images/Attacks/FireB/6.png"));
                            }
                            catch(Exception e) {
                                System.out.println("Error loading attack...");
                            }
                            g.drawImage(attack,158,473,145,145,this);
                        }
                    }
                }
            }
            //Draw healthboxes
            g.drawImage(healthBar,664,450,360,114,this);
            g.drawImage(enemyHealthBar,0,50,360,120,this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial",Font.BOLD,30));
            g.drawString(p.getPokemon().getName(),720,493);
            g.drawString(o.getOpponentPokemon().getName(),20,106);

            g.drawString(""+p.getPokemon().getLvl(),950,493);
            g.drawString(""+o.getOpponentPokemon().getLvl(),250,106);

            g.setFont(new Font("Arial",Font.BOLD,25));
            g.drawString(""+p.getPokemon().getMaxHP(),948,551);
            g.drawString(""+p.getPokemon().getHealth(),893,551);

            //Draw attack boxes
            g.drawImage(attackBox,1029,0,251,111,this);
            g.drawImage(attackBox,1029,111,251,111,this);
            g.drawImage(attackBox,1029,222,251,111,this);
            g.drawImage(attackBox,1029,333,251,111,this);

            g.drawString(p.getPokemon().getSpecMove(0).getName(),1050,50);
            g.drawString(p.getPokemon().getSpecMove(1).getName(),1050,160);
            g.drawString(p.getPokemon().getSpecMove(2).getName(),1050,270);
            g.drawString(p.getPokemon().getSpecMove(3).getName(),1050,380);

            //Draw chatbox
            Color barColor = new Color(33,47,136);
            g.setColor(barColor);
            g.fillRect(0,576,1024,5);
            g.fillRect(1024,0,5,581);
            g.drawImage(battleTextBox,0,581,1029,139,this);
            g.drawString("Opponent: ",1040,470);

            //Control Chatbox
            if (wait == 5) {
                if (battleCount > 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString("Go! " + p.getPokemon().getName() + "!",30,630);
                    battleCount--;
                }
                else {
                    wait = 4;
                    battleCount = 300;
                }
            }
            else if (wait == 4) {
                if (battleCount > 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString(o.getOpponent() + " sent out " + o.getOpponentPokemon().getName() + "!",30,630);
                    battleCount--;
                }
                else {
                    wait = 3;
                    battleCount = 300;
                }
            }
            else if (wait == 3) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial",Font.BOLD,26));
                g.drawString(p.getName() + "'s turn! Pick an attack!",30,630);
                if (xMouse >= 1029 && yMouse >= 0 && yMouse <= 111) {
                    wait = 2;
                    battleCount = 300;
                    moveNum = 0;
                    xMouse = -100;
                    yMouse = -100;
                    playBeep();
                    target = 1;
                }
                else if (xMouse >= 1029 && yMouse >= 111 && yMouse <= 222) {
                    wait = 2;
                    battleCount = 300;
                    moveNum = 1;
                    xMouse = -100;
                    yMouse = -100;
                    playBeep();
                    target = 1;
                }
                else if (xMouse >= 1029 && yMouse >= 222 && yMouse <= 333) {
                    wait = 2;
                    battleCount = 300;
                    moveNum = 2;
                    xMouse = -100;
                    yMouse = -100;
                    playBeep();
                    target = 1;
                }
                else if (xMouse >= 1029 && yMouse >= 333 && yMouse <= 444) {
                    wait = 2;
                    battleCount = 300;
                    moveNum = 3;
                    xMouse = -100;
                    yMouse = -100;
                    playBeep();
                    target = 1;
                }
            }
            else if (wait == 2) {
                if (battleCount > 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString(p.getPokemon().getName() + " uses " + p.getPokemon().getSpecMove(moveNum).getName() + "!",30,630);
                    setAttackNum(p.getPokemon().getSpecMove(moveNum).getName());
                    battleCount--;
                }
                else {
                    wait = 1;
                    battleCount = 300;
                    int crit = 1;
                    if  ((int)(Math.random()*25) == 23) {
                        crit = 2;
                    }
                    double type = 1;
                    if  (o.getOpponentPokemon().getType().equals(p.getPokemon().getType())) {
                        type = 1.5;
                    }
                    double typeMul = 1;
                    if  (o.getOpponentPokemon().getType().equals("Fire") && p.getPokemon().getType().equals("Water")) {
                        typeMul = 1.25;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Water") && p.getPokemon().getType().equals("Fire")) {
                        typeMul = 1;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Grass") && p.getPokemon().getType().equals("Water")) {
                        typeMul = 1;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Water") && p.getPokemon().getType().equals("Grass")) {
                        typeMul = 1.25;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Fire") && p.getPokemon().getType().equals("Grass")) {
                        typeMul = 1;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Grass") && p.getPokemon().getType().equals("Fire")) {
                        typeMul = 1.25;
                    }
                    System.out.println(typeMul);
                    int DMG = (int)((double)((((p.getPokemon().getLvl()*2/5)+2)*p.getPokemon().getSpecMove(moveNum).getDmg()/50+2)*crit*(double)(100-(int)(Math.random()*15+1))/100)*type*typeMul);
                    o.getOpponentPokemon().changeHealth(DMG);
                    System.out.println(DMG);
                    if (o.getOpponentPokemon().getHealth() <= 0) {
                        wait = -1;
                        battleCount = 500;
                    }
                    moveNum = (int)(Math.random()*4);
                    target = 0;
                }
            }
            else if (wait == 1) {
                if (battleCount > 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString(o.getOpponentPokemon().getName() + " uses " + o.getOpponentPokemon().getSpecMove(moveNum).getName() + "!",30,630);
                    setAttackNum(o.getOpponentPokemon().getSpecMove(moveNum).getName());
                    battleCount--;
                }
                else {
                    int crit = 1;
                    if  ((int)(Math.random()*25) == 23) {
                        crit = 2;
                    }
                    double type = 1;
                    if  (o.getOpponentPokemon().getType().equals(p.getPokemon().getType())) {
                        type = 1.5;
                    }
                    double typeMul = 1;
                    if  (o.getOpponentPokemon().getType().equals("Fire") && p.getPokemon().getType().equals("Water")) {
                        typeMul = 1;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Water") && p.getPokemon().getType().equals("Fire")) {
                        typeMul = 1.25;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Grass") && p.getPokemon().getType().equals("Water")) {
                        typeMul = 1.25;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Water") && p.getPokemon().getType().equals("Grass")) {
                        typeMul = 1;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Fire") && p.getPokemon().getType().equals("Grass")) {
                        typeMul = 1.25;
                    }
                    if  (o.getOpponentPokemon().getType().equals("Grass") && p.getPokemon().getType().equals("Fire")) {
                        typeMul = 1;
                    }
                    int DMG = (int)((double)((((p.getPokemon().getLvl()*2/5)+2)*o.getOpponentPokemon().getSpecMove(moveNum).getDmg()/50+2)*crit*(double)(100-(int)(Math.random()*15+1))/100)*type*typeMul);
                    p.getPokemon().changeHealth(DMG);
                    System.out.println(DMG);
                    if (p.getPokemon().getHealth() <= 0) {
                        wait = -2;
                        battleCount = 500;
                    }
                    else {
                        wait = 3;
                    }
                }
            }
            else if (wait == -1) {
                if (battleCount > 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString(o.getOpponentPokemon().getName() + " has fainted!",30,630);
                    battleCount--;
                }
                else {
                    wait = -4;
                    battleCount = 500;
                }
            }
            else if (wait == -2) {
                if (battleCount > 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString(p.getPokemon().getName() + " has fainted!",30,630);
                    battleCount--;
                }
                else {
                    wait = -3;
                    battleCount = 500;
                }
            }
            else if (wait == -3) {
                if (battleCount > 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString("This was " + o.getOpponent() + "! Until next time!",30,630);
                    battleCount--;
                }
                else {
                    clip.stop();
                    playIntro();
                    stage = 4;
                }
            }
            else if (wait == -4) {
                int xp = (int)((double)(o.getOpponentPokemon().getXPStat()) * (double)(o.getOpponentPokemon().getLvl()) * 1.5 / 7.00);
                if (battleCount > 0) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString(p.getPokemon().getName() + " earned " + xp + " experience points!",30,630);
                    battleCount--;
                }
                else {
                    p.getPokemon().lvlUP(xp);
                    wait = -3;
                    battleCount = 500;
                }
            }

            //Draw Health Bar
            g.setColor(new Color(247,247,247));
            g.fillRect(847,510,150,12);
            g.fillRect(147,130,150,16);
            g.setColor(new Color(93,245,95));
            int wp = (int)(150.0 * ((double)(p.getPokemon().getHealth())/(double)(p.getPokemon().getMaxHP())));
            int wo = (int)(150.0 * ((double)(o.getOpponentPokemon().getHealth())/(double)(o.getOpponentPokemon().getMaxHP())));
            g.fillRect(847,510,wp,12);
            g.fillRect(147,130,wo,16);

            //Opponent
            if (wait == -3) {
                if (o.getName().equals("Byron") || o.getName().equals("Cynthia") || o.getName().equals("Mars")) {
                    g.drawImage(opponentIMG,670,50,160,316,this);
                }
                else if (o.getName().equals("Aaron")) {
                    g.drawImage(opponentIMG,638,50,224,316,this);
                }
                else if (o.getName().equals("Cyrus")) {
                    g.drawImage(opponentIMG,654,50,192,316,this);
                }
            }

            if (o.getName().equals("Byron") || o.getName().equals("Cynthia") || o.getName().equals("Mars")) {
                g.drawImage(opponentIMG,1100,480,160,316,this);
            }
            else if (o.getName().equals("Aaron")) {
                g.drawImage(opponentIMG,1060,480,224,316,this);
            }
            else if (o.getName().equals("Cyrus")) {
                g.drawImage(opponentIMG,1060,480,192,316,this);
            }
        }
        else if (stage == 4) { //Post-Battle screen, button to go to next round
            if (p.getPokemon().getType().equals("Water")) {
                if (p.getPokemon().getName().equals("Piplup") && p.getPokemon().getLvl() == 16) {
                    p.getPokemon().evolve();
                    try {
                        pPokemon = ImageIO.read(new File("Images/Water/Prinplup.gif"));
                    }
                    catch(Exception e) {
                        System.out.println("Error loading content...");
                    }
                    g.drawImage(textBox,500,50,697,169,this);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString("Your Piplup has evolved into a " + p.getPokemon().getName() + "!",535,115);
                    g.drawString("Congratulations!",535,150);
                }
                else {
                    g.drawImage(textBox,500,50,697,169,this);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString("Good Battle " + p.getName() + "! Your " + p.getPokemon().getName() + " is level " + p.getPokemon().getLvl() + "!",535,115);
                }
            }
            else if (p.getPokemon().getType().equals("Grass")) {
                if (p.getPokemon().getName().equals("Turtwig") && p.getPokemon().getLvl() == 18) {
                    p.getPokemon().evolve();
                    try {
                        pPokemon = ImageIO.read(new File("Images/Grass/Grotle.gif"));
                    }
                    catch(Exception e) {
                        System.out.println("Error loading content...");
                    }
                    g.drawImage(textBox,500,50,697,169,this);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString("Your Turtwig has evolved into a " + p.getPokemon().getName() + "!",535,115);
                    g.drawString("Congratulations!",535,150);
                }
                else {
                    g.drawImage(textBox,500,50,697,169,this);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString("Good Battle " + p.getName() + "! Your " + p.getPokemon().getName() + " is level " + p.getPokemon().getLvl() + "!",535,115);
                }
            }
            else if (p.getPokemon().getType().equals("Fire")) {
                if (p.getPokemon().getName().equals("Chimchar") && p.getPokemon().getLvl() == 14) {
                    p.getPokemon().evolve();
                    try {
                        pPokemon = ImageIO.read(new File("Images/Fire/Monferno.gif"));
                    }
                    catch(Exception e) {
                        System.out.println("Error loading content...");
                    }
                    g.drawImage(textBox,500,50,697,169,this);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString("Your Chimchar has evolved into a " + p.getPokemon().getName() + "!",535,115);
                    g.drawString("Congratulations!",535,150);
                }
                else {
                    g.drawImage(textBox,500,50,697,169,this);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.BOLD,26));
                    g.drawString("Good Battle " + p.getName() + "! Your " + p.getPokemon().getName() + " is level " + p.getPokemon().getLvl() + "!",535,115);
                }
            }

            g.drawImage(pPokemon,200,0,400,400,this);
            Color barColor = new Color(33,47,136);
            g.setColor(barColor);
            g.fillRect(50,350,1180,5);
            g.drawImage(pokeball,565,450,150,150,this);
            if (xMouse >= 565 && xMouse <= 715 && yMouse >= 500 && yMouse <= 650) {
                stage = 3;
                wait = 5;
                clip.stop();
                playBeep();
                playBattle();
                p.getPokemon().heal();
                o.setOpponentPokemon(p.getPokemon().getLvl());
                o.reset();
                if (o.getName().equals("Aaron")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Aaron.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                else if (o.getName().equals("Byron")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Byron.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                else if (o.getName().equals("Cynthia")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Cynthia.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                else if (o.getName().equals("Cyrus")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Cyrus.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                else if (o.getName().equals("Mars")) {
                    try {
                        opponentIMG = ImageIO.read(new File("Images/Characters/Mars.png"));
                    }
                    catch(Exception e) {
                        System.out.println("Failed at loading");
                    } 
                }
                bckgNum = (int)(Math.random()*4);
                xMouse = -100;
                yMouse = -100;
            }
        }
    }

    /**
     * Method Name: playIntro()
     * Description: plays the intro song when the player is picking his pokemon
     * 
     * @precondition: none
     * @param: none
     * @return: none
     * 
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    private void playIntro() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Audio/intro.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch(Exception e) {
            System.out.println("Failed at starting intro music");
        }   
    }

    /**
     * Method Name: playBattle()
     * Description: plays the songs during the battles based on the tier of the opponent
     * 
     * @precondition: none
     * @param: none
     * @return: none
     * 
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    private void playBattle() {
        try {
            if (o.getRank().equals("Trainer")) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Audio/trainer.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
            else if (o.getRank().equals("Gym Leader")) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Audio/GymLeader.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
            else {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Audio/eFour.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
        }
        catch(Exception e) {
            System.out.println("Failed at starting intro music");
        }   
    }

    /**
     * Method Name: playBeep()
     * Description: plays the beep sound effect
     * 
     * @precondition: none
     * @param: none
     * @return: none
     * 
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    private void playBeep() {
        beep.stop();
        beep.setMicrosecondPosition(0);
        beep.start();
    }

    /**
     * Method Name: setAttackNum()
     * Description: sets the move number to allow the paint() to draw
     * the attack graphics
     * 
     * @precondition: a is a valid attack
     * @param: String a
     * s is the name of the move
     * @return: none
     * 
     * @author: Ugo Dos Reis
     * @version May 2018
     */
    public void setAttackNum(String a) {
        if (a.equals("XScizzor")) {
            atckNum = 1;
        }
        if (a.equals("Leaf Storm")) {
            atckNum = 2;
        }
        if (a.equals("Razor Leaf")) {
            atckNum = 9;
        }
        if (a.equals("Dragon Claw")) {
            atckNum = 3;
        }
        if (a.equals("Fire Punch")) {
            atckNum = 4;
        }
        if (a.equals("Burn")) {
            atckNum = 5;
        }
        if (a.equals("Bubble")) {
            atckNum = 6;
        }
        if (a.equals("Water Pulse")) {
            atckNum = 7;
        }
        if (a.equals("Fire Blast")) {
            atckNum = 8;
        }
        if (a.equals("Energy Ball")) {
            atckNum = 10;
        }
        if (a.equals("Waterfall")) {
            atckNum = 11;
        }
        if (a.equals("Hydro Pump")) {
            atckNum = 12;
        }
    }
}