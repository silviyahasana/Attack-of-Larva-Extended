import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Pakdenya here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Pakdenya extends Actor
{
    final int jSpeed = 27;
    private int ySpeed = 0, xSpeed = 0; 
    boolean aboutFace; 
    private boolean onGround;
    int imageOffset = 15;
    int numImagesLeft = 3;
    int numImagesRight = 3;
    int imageIndexLeft = 0;
    int imageIndexRight = 0;
    int l, r;
    private GreenfootImage[] imagesRight;
    private GreenfootImage[] imagesLeft;
    private World world = getWorld();
    private boolean hitWithWeapon = false;
    private int rotCount = 0;
    private boolean hitDirection;
    private int health = 6;
    private Random rand = new Random();
    
    //sound effects
   // private GreenfootSound brains = new GreenfootSound("rains0.wav");
    
    
    /** 
     * Checks for changes in direction and moves the main actor.
     */
    public Pakdenya(String image, int left, int right)
    {
        
        setImage(image);
        getImage().scale(100, 100);
        AnimateLoad();
        if (image.compareTo("PakdenyaWalkRight0.png") == 0)
            aboutFace = false;
        else
            aboutFace = true;
    }
    
    public void act()
    {
        World w = getWorld();
        List<Larva> list = w.getObjects(Larva.class);
        if (xSpeed > 0)
            aboutFace = false;
        else if (xSpeed < 0)
            aboutFace = true;
        
        if (!hitWithWeapon)
        {
            getDirection();
            move();
            hitDirection = getHit();
        }
        
        if (xSpeed == 0 && aboutFace) 
        {
            setImage("PakdenyaWalkLeft0.png");
        }
        else if (xSpeed == 0 && !aboutFace) 
        {
            setImage("PakdenyaWalkRight0.png");
        }
      
        if (hitWithWeapon)
            killZombie();
       
    }
    
    private void AnimateLoad() {
        int i = 0;
        int j = 0;
        int numImage = 0;
        imagesRight = new GreenfootImage[numImagesRight*imageOffset];
        imagesLeft = new GreenfootImage[numImagesLeft*imageOffset];
        while (numImage < numImagesRight) {
            GreenfootImage addImage = new GreenfootImage("PakdenyaWalkRight" + numImage + ".png");
            for (j = 0; j < imageOffset; j++) {
                imagesRight[i+j] = addImage;
            }
            i += j;
            numImage++;
        }    
        i = 0;
        numImage = 0;
        while (numImage < numImagesLeft) {
            GreenfootImage addImage = new GreenfootImage("PakdenyaWalkLeft" + numImage + ".png");
            for (j = 0; j < imageOffset; j++) {
                imagesLeft[i+j] = addImage;
            }
            i += j;
            numImage++;
        }
    }    
    
    private void AnimateRight() {
        if (imageIndexRight < numImagesRight*imageOffset)
            setImage(imagesRight[imageIndexRight]);
        else
            imageIndexRight = 0;
       imageIndexRight++;
    }
    
    private void AnimateLeft() {
        if (imageIndexLeft < numImagesLeft*imageOffset)
            setImage(imagesLeft[imageIndexLeft]);
        else
            imageIndexLeft = 0;
        imageIndexLeft++;
    }
        
    /**
     * Moves the actor with appropriate image.  Checks for obstacles and adjusts
     * the position of the actor accordingly.
     */
    private void move()
    {
        Actor obj;
        ySpeed++;
        setLocation(getX()+xSpeed/15, getY()+ySpeed/2);
         
        if((xSpeed>0 && aboutFace) || (xSpeed<0 && !aboutFace)) 
        {
            if (aboutFace)
                setImage("PakdenyaWalkLeft0.png");
            else 
                setImage("PakdenyaWalkRight0.png");
                
            aboutFace = !aboutFace;
        }
        
        onGround=false; 
        while(getOneObjectAtOffset(0, getImage().getHeight()/2+1, Platform.class)!=null)
        {
            onGround=true;
            setLocation(getX(), getY()-1); 
            ySpeed=0;
        }
        while((obj = getOneObjectAtOffset(0, -getImage().getHeight()/2-1, Platform.class))!=null) 
        {
            
            setLocation(getX(), getY()+1);
            ySpeed = 0;
            
        }
        while(getOneObjectAtOffset(getImage().getWidth()/2 - 25, -10, Platform.class)!=null || getOneObjectAtOffset(getImage().getWidth()/2 - 25, 10, Platform.class)!=null || getOneObjectAtOffset(getImage().getWidth()/2 - 25, 53, Platform.class)!=null || getOneObjectAtOffset(getImage().getWidth()/2 - 25, -44, Platform.class)!=null)
        {
            setLocation(getX()-1, getY());
            xSpeed = -15;
           
        }
        while(getOneObjectAtOffset(-getImage().getWidth()/2 + 25, -10, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 25, 10, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 25, 53, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 25, -44, Platform.class)!=null)
        {
            setLocation(getX()+1, getY());
            xSpeed = 15;
        } 
    }
    
    private boolean getHit()
    {
        List<Arm> arm = null;
        List<Poop> poop = null;     
        int count = -1;
        
        if (this != null)
        {
            world = getWorld();
            
            if (world != null)
            {
                arm = world.getObjects(Arm.class);
                poop = world.getObjects(Poop.class);
                
                for (int i = 0; i < poop.size(); i++)
                {
                    if (poop.get(i).isPosessed())
                    {
                        count = i;
                        break;
                    }
                }
            }
            
            if (arm != null && arm.size() > 0)
            {
                if(intersects(arm.get(0)))
                {
                    hitWithWeapon = true;
                    
                   
                    
                    if (arm.get(0).getX() > getX())
                    {
                        
                        return false;
                    }
                    else
                    {
                        
                        return true;
                    }
                }
                
                    
            }
            else if (poop != null && poop.size() > 0 && count > -1)
            {
                if(intersects(poop.get(count)))
                {
                    
                        
                    hitWithWeapon = true;
                    
                    if (poop.get(count).getX() > getX())
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }
        return false;
            
    }
    
    
    
    /**
     * Determines any changes in horizontal and vertical speeds for the actor.
     */
    private void getDirection()
    {
         Random rn = new Random();
         int i = rn.nextInt() % 100;
         
         if (i < 0)
            i = -i;
            
         
         if (i >= 99)
         {
            if (xSpeed == 0)
            {
                if (aboutFace == true)
                    xSpeed = -15;
                
                else
                    xSpeed = 15;
            }
                   
                xSpeed = -xSpeed;
        }
        
        else if (i <=1)
            xSpeed = 0;
        
        if (xSpeed > 0)
                AnimateRight();
        else
                AnimateLeft();
        
        
    }
    
    private void killZombie()
    {   
        
        if (rotCount < 90)
        {
            
            if (rotCount % 2 == 0)
                setLocation(getX(), getY() + 2);
            
            if (rotCount < 15)
            {
                if (!aboutFace)
                    setImage("PakdenyaHurtRight.png");
                else
                    setImage("PakdenyaHurtLeft.png");
            }
            
            else if (rotCount == 18)
            {
                if (!aboutFace)
                    setImage("PakdenyaWalkRight0.png");
                else
                    setImage("PakdenyaWalkLeft0.png");
            }
            rotCount+=3;
            if (!hitDirection)
            {
                setRotation(-rotCount);
            }
            else
                setRotation(rotCount);
        }
        
        if (rotCount == 90) {
           int x = getX();
           int y = getY();
           world.removeObject(this);
           Random rand = new Random();
           if (rand.nextInt() % 2 == 0)
           {
                world.addObject(new Poop(false), x, y);           
           }
        }
    }
    
    public boolean getAboutFace()
    {
        return aboutFace;
    }
    
    public void decrementHealthBLG_DZ(Kumbang bludg)
    {
        World world = getWorld();        
        if (health > 0)
        {
            health--;
            if (aboutFace)
                setImage("PakdenyaHurtLeft.png");
            else
                setImage("PakdenyaHurtRight.png");
        }
            
        if (health == 0)
        {
            if (bludg.getX() < getX())
            {
                hitDirection = true;
                
            }
            else if (bludg.getX() > getX())
            {
                
                hitDirection = false;
            }
                
            hitWithWeapon = true;
            
            killZombie();
        }
    }
}
    

