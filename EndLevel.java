import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class EndLevel1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndLevel extends AreaSensor
{
    MyWorld world;
    Larva mc;
    
    public EndLevel()
    {
        GreenfootImage image = new GreenfootImage("invisRoad.png");
        image.scale(50, 1000);
        setImage(image);
    }
    public void act() 
    {
        if(isTouching(Larva.class))
        {
            GreenfootImage bg;
            List<Larva> mcs;
            world = (MyWorld) getWorld();
            world.stopSound();
            mcs = world.getObjects(Larva.class);
            mc = mcs.get(0);
            
            if(mc.LevelSekarang() < 2)
            {
                bg = new GreenfootImage("got-1.png");
            }
            else if(mc.LevelSekarang() < 4)
                bg = new GreenfootImage("got-2.png");
            else
                bg = new GreenfootImage("got-3.png");
            mc.LevelNaik();
            
            if(mc.LevelSekarang() > 6)
            {
               MyWorld myworld = (MyWorld) getWorld(); 
               myworld.stopped();
               getWorld().addObject(new Menang(),600,300);
               Greenfoot.playSound("win.mp3");
               Greenfoot.stop();
            }
            else
            {
                world = new MyWorld(mc, bg);
                Greenfoot.setWorld(world);
                
                for(int i = 0; i < 9; i++)
                {
                    if(mc.LevelSekarang() == i)
                        world.createLevel(i);
                }
            }
        }
    }    
}
