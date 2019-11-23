
/**
 * MovesTester.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class MovesTester
{
    public static void main (String args [] )
    {
        Moves m1=new Moves("Scratch",2);
        Moves m2=new Moves("Growl",5);
        System.out.println(m1.getDmg());
        System.out.println(m2.getDmg());
        System.out.println(m1.getName());
        System.out.println(m2.getName());
        m1.setDmg(10);
        m2.setDmg(20);
        System.out.println(m1.getDmg());
        System.out.println(m2.getDmg());
        m1.setName("Scratchv2");
        m2.setName("Growlv2");
        System.out.println(m1.getName());
        System.out.println(m2.getName());
    }
}
