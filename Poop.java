import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Poop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Poop extends Weapon
{
    private int moveX = 9;
    private int grav = 1;
    private int moveY = 0;
    private Larva player;
    boolean direction; 
    World world;
    int imageOffset = 3;
    int numImages = 5;
    private GreenfootImage[] images;
    private GreenfootImage poop;
    int imageIndex = 0;
    boolean posess;
    Random rand = new Random();
    boolean explFlag = true;
    
    //sound effects
    GreenfootSound explosion0 = new GreenfootSound("explosion0.wav");
    
    public Poop(boolean p)
    {
        setImage("poop.png");
        posess = p;
    }
    
    public void act() 
    {
        if (posess)
        {
            movePoop();
            collisionCheck();
        }
        
        else
        {
            collectPoop();
        }
    }    
    
    private void collisionCheck()
    {
        //Explode
        if((isTouching(Platform.class) || isTouching(Pakdenya.class) || isTouching(Musuh.class)) && !isTouching(Larva.class))
        {
            moveY = 0;
            moveX = 0;
            
            if (explFlag)
            {
                if (rand.nextInt() % 2 == 0)
                    explosion0.play();
                else
                explFlag = false;
            }
            
            if(imageIndex >= imageOffset * numImages)
            {       
                world.removeObject(this);
            }
            else
            {
                runAnimation();
            }
        }
    }
    
    private void runAnimation()
    {
        setImage(images[imageIndex]);
        imageIndex++;
    }
    
    private void movePoop()
    {
        setLocation(getX() + moveX, getY() - moveY);
        moveY = moveY - grav;
    }
    
    public void startPoop()
    {
        List<Larva> mc;
        
        world = getWorld();
        mc = world.getObjects(Larva.class);
        player = mc.get(0);
        direction = player.getAboutFace();
        
        if(direction)
            moveX = -moveX;
            
        setImage("poop.png");
        setLocation(player.getX(), player.getY());
        moveY = 20;
        
        //load animation
        images = new GreenfootImage[numImages*imageOffset];
        for(int i = 0; i < numImages; i++)
        {
            GreenfootImage addImage = new GreenfootImage("Explosion" + i + ".png");
            for (int j = 0; j < imageOffset; j++)
            {
                images[i*imageOffset + j] = addImage;
            }
        }
    }
    
    private void collectPoop()
    {
        World world = getWorld();
        List<Larva> mc = world.getObjects(Larva.class);
        
        if(mc.size() > 0)
        {
            if (intersects(mc.get(0)))
            {
                world.removeObject(this);
                mc.get(0).PoopNambah();
                posess = true;
            }
        }
    }
    
    public boolean isPosessed()
    {
        return posess;
    }
}
