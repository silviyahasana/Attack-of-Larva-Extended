import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Actor
{
    public StartButton() {
        GreenfootImage img = new GreenfootImage ("titlescreen.png");
        img.setColor(new Color(255, 255, 255));
        Font font = new Font("Calibri", Font.PLAIN, 28);
        img.setFont(font);
        img.drawString("Press Run to begin.", 180, 360);
        setImage(img);
    }
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {   
       //MyWorld world = new MyWorld();
       //Greenfoot.setWorld(world);
       //world.createLevel1();
    }    

}
