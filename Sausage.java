import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sausage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sausage extends Actor
{
    private Larva mc = null;
    private int ySpeed = 0, xSpeed = 0; 
    boolean aboutFace; 
    private boolean onGround; 
    /**
     * Act - do whatever the Sausage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {   
        move();
        if (foundMC()) {
            mc.increaseHealth();
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this);
        }
    }
    
    public boolean foundMC() {
        Actor a;
        if ((a = getOneObjectAtOffset(getImage().getWidth()/2 - 5, 0, Larva.class))!=null)
        {
            mc = (Larva)a;
            return true;
        }
     
        if ((a = getOneObjectAtOffset(-getImage().getWidth()/2 + 5, 0, Larva.class))!=null) 
        {
            mc = (Larva)a;
            return true;
        }
        
        return false;
    }
    
        private void move()
    {
        Actor obj;
        ySpeed++; 
        if (xSpeed != 0 && onGround) xSpeed+=aboutFace?10:-10; 
        setLocation(getX()+xSpeed/10, getY()+ySpeed/2);
        
      
        onGround=false; 
         
        while(getOneObjectAtOffset(0, getImage().getHeight()/2+1, Platform.class)!=null)
        {
            setLocation(getX(), getY()-1); 
            onGround=true;
            ySpeed=0;
            
        }
        
        while(getOneObjectAtOffset(0, getImage().getHeight()/2+1, Sausage.class)!=null)
        {
            setLocation(getX(), getY()-1); 
            onGround=true;
            ySpeed=0;
            
        }
    }
}
