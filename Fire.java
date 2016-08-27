import greenfoot.*; 
import java.util.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fire extends Actor
{
    /**
     * Act - do whatever the Fire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage fire;
    private World world;
    
    private int fireCounter = 0;
    private int imageCounter = 0;
    
    public Fire()
    {
        fire = new GreenfootImage("fire.png");
        fire.scale(40, 35);
        setImage(fire);
        fire.drawImage(fire, 0, 0);
        
    }
    
    public void act() 
    {
        fireCounter++;
        
        //MCInFire(fireCounter);
        
        getImage().mirrorHorizontally();
        // Add your action code here.
    }
    
    public int getWidth()
    {
        return getImage().getWidth();
    }
    
    public int getHeight()
    {
        return getImage().getHeight();
    }
 
        
}
