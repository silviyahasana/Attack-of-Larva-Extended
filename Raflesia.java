import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Raflesia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Raflesia extends Musuh
{
    /**
     * Act - do whatever the Raflesia wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private boolean aboutFace;
    private Larva z;
    private int ySpeed = 0, xSpeed = 0;
    private boolean onGround = false;
    private boolean shoot = false;
    private boolean hitDirection;
    private int health = 2;
    private int rotCounter = 0;
    private boolean fall = false;
    private int numImagesRight;
    private int numImagesLeft;
    private Bullet bullet;
    private boolean shot = false;
    private Random rand = new Random();
    private int counter = rand.nextInt();
    
    GreenfootSound hit = new GreenfootSound("enemyhit0.wav");
    GreenfootSound shootGun = new GreenfootSound("gunsound0.wav");
    GreenfootSound huah = new GreenfootSound("ang.mp3");
    
    public Raflesia(String image)
    {
        super(image);
        
        if (image.compareTo("RaflesiaStandRight.png") == 0)
            aboutFace = false;
        else
            aboutFace = true;
           
    }
    
    public void act() 
    {
        move();
        z = getMC();
        
        if (z != null)
        {
            setGunImage(z);
            
            if (z.getX() + 50 < getX())
                aboutFace = true;
            else if (z.getX() - 50 > getX())
                aboutFace = false;
            getShoot(z);
            if (shoot)
            {
                if (counter++ % 75 == 0)
                {
                    attack(z);
                }
            }
            getHit();
        }
        if (fall)
                fallRanged();
    }
    
    
    public void move()
    {
        ySpeed++; 
        if (xSpeed != 0 && onGround) xSpeed+=aboutFace?10:-10; 
        setLocation(getX(), getY()+ySpeed/2);
        
        while(getOneObjectAtOffset(0, getImage().getHeight()/2+1, Platform.class)!=null)
        {
            setLocation(getX(), getY()-1); 
            onGround=true;
            ySpeed=0;
            
        }
        
    }

    private Larva getMC()
    {
        List<Larva> mc = getObjectsInRange(600, Larva.class);
        
        if (mc.size() > 0)
            return mc.get(0);
        else
            return null;
    }
    
    private void getShoot(Larva larva)
    {
        if (larva == null)
            shoot = false;
        else
            shoot = true;
    }
    
    private void shoot(Larva larva)
    {
    }
    
    public void getHit()
    {
        World world = getWorld();
        List<Arm> arm;
        List<Poop> poop;
        boolean p = false;
        int count = -1;
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
        
        if (arm.size() > 0)
        {
            if(intersects(arm.get(0)))
            {
                if (arm.get(0).getX() > getX())
                {
                    decrementHealth();
                    hitDirection = false;
                }
                else
                {
                    decrementHealth();
                    hitDirection = true;
                }
            }
            
        }
        
        if (poop.size() > 0 && count > -1)
        {
            if (intersects(poop.get(count)))
            {
                if (poop.get(count).getX() > getX())
                {
                    decrementHealth();
                    hitDirection = false;
                }
                
                else
                {
                    decrementHealth();
                    hitDirection = true;
                }
            }
        }
    }
    
    private void fallRanged()
    {
        World world = getWorld();
        if (rotCounter < 90)
        {
            if (rotCounter % 2 == 0)
                setLocation(getX(), getY() + 2);
            
            if (rotCounter < 15)
            {
                if (!aboutFace)
                {
                    setImage("RaflesiaHurtRight.png");
                }
                else
                {
                    setImage("RaflesiaHurtLeft.png");
                }
                
            }
            
            else if (rotCounter == 18)
            {
                if (!aboutFace)
                    setImage("RaflesiaStandRight.png");
                else
                    setImage("RaflesiaStandLeft.png");
            }
            
            rotCounter += 3;
            
            if (!hitDirection)
                setRotation(-rotCounter);
            else
                setRotation(rotCounter);
        }
        
        if (rotCounter == 90) {
           int soundCount = rand.nextInt();
        
           world.addObject(new Sausage(), getX(), getY());
           world.removeObject(this);
        }
    }
    
    private void decrementHealth()
    {
        Random rand = new Random();
        
        if (rand.nextInt() % 2 == 0)
        {
            hit.play();
        }
            
        if (health > 0)
        {
            health--;
            if (aboutFace)
                setImage("RaflesiaHurtLeft.png");
            else
                setImage("RaflesiaHurtRight.png");
        }
        
        else
            fall = true;
    }
    
    private void attack(Larva larva)
    {
        World world = getWorld();
        bullet = new Bullet();
        
        if (!shot && larva != null)
        {
            shootGun.play();
            if (aboutFace)
            {
                if (Math.abs(larva.getY() - getY()) < 100)
                    world.addObject(bullet, getX() - 70, getY());
                else if (larva.getY() - getY() > 100)
                    world.addObject(bullet, getX() - 60, getY() + 30);
                else
                {
                    
                    world.addObject(bullet, getX() - 60, getY() - 30);
                }
            }
            
            else
            {
                
                if (Math.abs(larva.getY() - getY()) < 100)
                    world.addObject(bullet, getX() + 70, getY());
                else if (larva.getY() - getY() > 100)
                    world.addObject(bullet, getX() + 60, getY() + 30);
                else
                    world.addObject(bullet, getX() + 60, getY() - 30);
            }
            
            bullet.startBullet(larva, this, bullet.getX(), bullet.getY());
        }
    }
    
    public void setShot(boolean b)
    {
        shot = b;
    }
    
    private void setGunImage(Larva larva)
    {
        if (Math.abs(larva.getY() - getY()) < 100)
        {
            if (aboutFace)
                setImage("RaflesiaAttackLeft0.png");
            else
                setImage("RaflesiaAttackRight0.png");
        }
        
        else if (larva.getY() - getY() > 100)
        {
            if (aboutFace)
                setImage("RaflesiaStandLeft.png");
            else
                setImage("RaflesiaStandRight.png");
        }
        
        else 
        {
            if (aboutFace)
                setImage("RaflesiaAttackLeft1.png");
            else
                setImage("RaflesiaAttackRight1.png");
              
        }
    }
    
}
