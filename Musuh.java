import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Musuh here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Musuh extends Actor
{
    /**
     * Act - do whatever the Musuh wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int xSpeed = 0;
    private boolean aboutFace;
    
    private GreenfootImage Musuh;
    
    public Musuh(String image)
    {
        Musuh = new GreenfootImage(image);
        Musuh.drawImage(Musuh, 0, 0);
        setImage(image);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    
    
    public int findPlatform()
    {
        if (getOneObjectAtOffset(-getImage().getWidth()/2 - 10, 0, Pakdenya.class) == null)
        {
            //System.out.println("BUTT");
            if ((getOneObjectAtOffset(-getImage().getWidth()/2 - 10, 0, Platform.class) != null) || (getOneObjectAtOffset(-getImage().getWidth()/2 - 10, 30, Platform.class) != null) 
                || (getOneObjectAtOffset(-getImage().getWidth()/2 - 10, -20, Platform.class) != null))
            {
                //System.out.println("1");
                return 1;
            }
        }
        
        if (getOneObjectAtOffset(getImage().getWidth()/2 + 10, 0, Pakdenya.class) == null) 
        {
            
            //System.out.println(getOneObjectAtOffset(getImage().getWidth()/2 + 10, 30, Platform.class));
            if ((getOneObjectAtOffset(getImage().getWidth()/2 + 10, 0, Platform.class)!=null) || (getOneObjectAtOffset(getImage().getWidth()/2 + 10, 30, Platform.class) != null)
                 || (getOneObjectAtOffset(getImage().getWidth()/2 + 10, -20, Platform.class) != null)) 
            {
                //System.out.println("2");
                return 2;
            }
                
        }
        
        return 0;
    }
        
        
    
    public void attack()
    {
        //System.out.println();
    }
}
