import greenfoot.*;
import java.util.*;

/**
 * A user-controlled actor that walks and jumps, and is pulled down by gravity.
 * <l><li>Left arrow moves actor left (toward left scroll limit)</li>
 * <li>Right arrow moves actor right (toward right scroll limit)</li>
 * <li>Up arrow makes the actor jump</li><l>
 */
public class Larva extends Actor
{
    final int jSpeed = 27; 
    private int ySpeed = 0, xSpeed = 0; 
    private boolean aboutFace; 
    private boolean onGround;
    int imageOffset = 5;
    int imageIdleOffset = 15;
    int numImagesLeft = 4;
    int numImagesRight = 4;
    int numImagesIdle = 4;
    int imageIndexLeft = 0;
    int imageIndexRight = 0;
    int imageIndexIdle = 0;
    int idleCheck = 0;
    boolean isTitle = false;
    int levelSekarang = 1;
    
    Arm arm = null;
    Poop poop = null;
    int poopCount = 0;
    int fireCounter = 23;
    World world;
    List<Arm> arms;
    List<Poop> poops;
    private GreenfootImage[] imagesRight;
    private GreenfootImage[] imagesLeft;
    private GreenfootImage[] imagesNoArmsRight;
    private GreenfootImage[] imagesNoArmsLeft;
    private GreenfootImage[] imagesIdleLeft;
    private GreenfootImage[] imagesIdleRight;
    private int health = 8;
    private int numBrains = 0;
    private int rotCount = 0;
    private Random rand = new Random();
    

    GreenfootSound jump;
    GreenfootSound throwArm;
    GreenfootSound kill[];

    GreenfootSound throwB = new GreenfootSound("throw.mp3");
    private GreenfootSound zombieHit = new GreenfootSound("aww.mp3");

    int attackSoundCounter = 0;
    
    
    public Larva() {
        AnimateLoad();
        jump = new GreenfootSound("ang.mp3");
        throwArm = new GreenfootSound("huah.wav");
    }
    
    public Larva(boolean isTitleScreen) {
        AnimateLoad();
        isTitle = isTitleScreen;
        idleCheck = 1001;
    }
    
    public void act()
    {   
        checkHealth();
        if (!isTitle) {
            world = getWorld();
            arms = world.getObjects(Arm.class);
            poops = world.getObjects(Poop.class);
            if (idle()) {
                if (aboutFace) {
                    AnimateIdleLeft();
                }
                else if (!aboutFace) {
                    AnimateIdleRight();
                }
            }
            getDirection();
            move();
            attack();
            GambarDiem();
        }
        else {
            if (idle()) {
                if (!aboutFace) {
                    AnimateIdleLeft();
                }
            }
        }
    }
    
    private void checkHealth() {
        if (health <= 0) {
            MyWorld world = (MyWorld) getWorld();
            world.addObject(new GameOver("youdied.png"), 520, 350);
            Greenfoot.playSound("gameover.mp3");
            Greenfoot.stop();
        }
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getPoopCount() {
        return poopCount;
    }
    
    private void GambarDiem()
    {
        if(arms.size() > 0)
        {
            if (xSpeed == 0 && aboutFace && !idle()) {
                setImage("LarvaJalanLeft1.png");
            }
            else if (xSpeed == 0 && !aboutFace && !idle()) {
                setImage("LarvaJalanRight1.png");
            }
        }
        else
        {
            if (xSpeed == 0 && aboutFace && !idle()) {
                setImage("LarvaJalanLeft1.png");
            }
            else if (xSpeed == 0 && !aboutFace && !idle()) {
                setImage("LarvaJalanRight1.png");
            }
        }
    }
    
    private void attack()
    {    
        attackSoundCounter++;
        if(Greenfoot.isKeyDown("x") && arms.size() == 0)
        {
            throwB.play();
            
            arm = new Arm();
            world.addObject(arm, 100, 100);
            arm.startArm();
        }
        
        else if(Greenfoot.isKeyDown("z") && poopCount > 0)
        {
            if (!NgambilPoop())
            {
                poop = new Poop(true);
                world.addObject(poop, 100, 100);
                poop.startPoop();
                PoopKurang();
            }
        }
    
    }
    
    private boolean NgambilPoop()
    {
        List<Poop> poops;
        Iterator<Poop> it;
        boolean answer = false;
        
        poops = world.getObjects(Poop.class);
        it = poops.iterator();
        while(it.hasNext())
        {
            Poop e = it.next();
            if(e.isPosessed())
                answer = true;
        }
        return answer;
    }
    
    private void AnimateLoad() {
        int i = 0;
        int j = 0;
        int numImage = 0;
        imagesRight = new GreenfootImage[numImagesRight*imageOffset];
        imagesLeft = new GreenfootImage[numImagesLeft*imageOffset];
        imagesNoArmsRight = new GreenfootImage[numImagesRight*imageOffset];
        imagesNoArmsLeft = new GreenfootImage[numImagesLeft*imageOffset];
        imagesIdleLeft = new GreenfootImage[numImagesIdle*imageIdleOffset];
        imagesIdleRight = new GreenfootImage[numImagesIdle*imageIdleOffset];
        
        while (numImage < numImagesRight) {
            GreenfootImage addImage = new GreenfootImage("LarvaJalanRight" + numImage + ".png");
            for (j = 0; j < imageOffset; j++) {
                imagesRight[i+j] = addImage;
            }
            i += j;
            numImage++;
        }    
        i = 0;
        numImage = 0;
        while (numImage < numImagesLeft) {
            GreenfootImage addImage = new GreenfootImage("LarvaJalanLeft" + numImage + ".png");
            for (j = 0; j < imageOffset; j++) {
                imagesLeft[i+j] = addImage;
            }
            i += j;
            numImage++;
        }
        i = 0;
        numImage = 0;
        
        while (numImage < numImagesRight) {
            GreenfootImage addImage = new GreenfootImage("LarvaJalanRight" + numImage + ".png");
            for (j = 0; j < imageOffset; j++) {
                imagesNoArmsRight[i+j] = addImage;
            }
            i += j;
            numImage++;
        }    
        i = 0;
        numImage = 0;
        while (numImage < numImagesLeft) {
            GreenfootImage addImage = new GreenfootImage("LarvaJalanLeft" + numImage + ".png");
            for (j = 0; j < imageOffset; j++) {
                imagesNoArmsLeft[i+j] = addImage;
            }
            i += j;
            numImage++;
        }
        i = 0;
        numImage = 0;
        
        while (numImage < numImagesIdle) {
            GreenfootImage addImage = new GreenfootImage("LarvaSomplakLeft" + numImage + ".png");
            for (j = 0; j < imageIdleOffset; j++) {
                imagesIdleLeft[i+j] = addImage;
            }
            i += j;
            numImage++;
        }
        i = 0;
        numImage = 0;
        while (numImage < numImagesIdle) {
            GreenfootImage addImage = new GreenfootImage("LarvaSomplakRight" + numImage + ".png");
            for (j = 0; j < imageIdleOffset; j++) {
                imagesIdleRight[i+j] = addImage;
            }
            i += j;
            numImage++;
        }
    }    
    
    private void AnimasiKanan() {
        if(arms.size() > 0)
        {
            if (imageIndexRight < numImagesRight*imageOffset)
                setImage(imagesNoArmsRight[imageIndexRight]);
            else
                imageIndexRight = 0;
        }
        else
        {
            if (imageIndexRight < numImagesRight*imageOffset)
                setImage(imagesRight[imageIndexRight]);
            else
                imageIndexRight = 0;
        }
       imageIndexRight++;
    }
    
    private void AnimasiKiri() {
        if(arms.size() > 0)
        {
            if (imageIndexLeft < numImagesLeft*imageOffset)
                setImage(imagesNoArmsLeft[imageIndexLeft]);
            else
                imageIndexLeft = 0;
            imageIndexLeft++;
        }
        else
        {
            if (imageIndexLeft < numImagesLeft*imageOffset)
                setImage(imagesLeft[imageIndexLeft]);
            else
                imageIndexLeft = 0;
            imageIndexLeft++;
        }
    }
        
    private void AnimateIdleLeft() {
        if (imageIndexIdle < numImagesIdle*imageIdleOffset)
            setImage(imagesIdleLeft[imageIndexIdle]);
        else
            imageIndexIdle = 0;
        imageIndexIdle++;
    }
    
    private void AnimateIdleRight() {
        if (imageIndexIdle < numImagesIdle*imageIdleOffset)
            setImage(imagesIdleRight[imageIndexIdle]);
        else
            imageIndexIdle = 0;
        imageIndexIdle++;
    }
    
    private boolean idle() {
        if (idleCheck > 500) { 
            return true;
        }
        else {
            idleCheck++;
            return false;
        }    
    }    
    
    /**
     * Moves the actor with appropriate image.  Checks for obstacles and adjusts
     * the position of the actor accordingly.
     */
    private void move()
    {
        Actor obj;
        ySpeed++; 
        if (xSpeed != 0 && onGround) xSpeed+=aboutFace?10:-10; 
        setLocation(getX()+xSpeed/10, getY()+ySpeed/2);
     
        if((xSpeed>0 && aboutFace) || (xSpeed<0 && !aboutFace)) 
        {
            
            arms = world.getObjects(Arm.class);
            
            if(arms.size() > 0)
            {
               
                if (!aboutFace)
                    setImage("LarvaJalanLeft1.png");
                else 
                    setImage("LarvaJalanRight1.png");
                    
                aboutFace = !aboutFace;
            }
            else
            {
                if (!aboutFace)
                    setImage("LarvaJalanLeft1.png");
                else 
                    setImage("LarvaJalanRight1.png");
                    
                aboutFace = !aboutFace;
            }
        }

        onGround=false; 
        
        while(getOneObjectAtOffset(0, getImage().getHeight()/2+1, Platform.class)!=null)
        {
            setLocation(getX(), getY()-1); 
            onGround=true;
            ySpeed=0;
            
        }
   
        while((obj = getOneObjectAtOffset(0, -getImage().getHeight()/2-1, Platform.class))!=null) 
        {
            
            setLocation(getX(), getY()+1);
            ySpeed = 0;
            
        }
        

        while(getOneObjectAtOffset(getImage().getWidth()/2 - 25, -10, Platform.class)!=null || getOneObjectAtOffset(getImage().getWidth()/2 - 25, 10, Platform.class)!=null || getOneObjectAtOffset(getImage().getWidth()/2 - 25, 53, Platform.class)!=null || getOneObjectAtOffset(getImage().getWidth()/2 - 35, -52, Platform.class)!=null)
        {
            setLocation(getX()-1, getY());
            xSpeed = 0;
        }
   
        while(getOneObjectAtOffset(-getImage().getWidth()/2 + 25, -10, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 25, 10, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 25, 53, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 35, -52, Platform.class)!=null)
        {
            setLocation(getX()+1, getY());
            xSpeed = 0;
        } 
        
        
        while(getOneObjectAtOffset(0, getImage().getHeight()/2-25, Pakdenya.class)!=null)
        {
            setLocation(getX(), getY()-1); 
            onGround=true;
            ySpeed=0;
            
        }
     
        while((obj = getOneObjectAtOffset(0, -getImage().getHeight()/2-50, Pakdenya.class))!=null) 
        {
            
            setLocation(getX(), getY()+1);
            ySpeed = 0;
            
        }
        
     
        while(getOneObjectAtOffset(getImage().getWidth()/2 - 42, -10, Pakdenya.class)!=null || getOneObjectAtOffset(getImage().getWidth()/2 - 42, 10, Pakdenya.class)!=null || getOneObjectAtOffset(getImage().getWidth()/2 - 42, -44, Pakdenya.class)!=null)
        {
            setLocation(getX()-1, getY());
            xSpeed = 0;
        }
   
        while(getOneObjectAtOffset(-getImage().getWidth()/2 + 42, -10, Pakdenya.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 42, 10, Pakdenya.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 42, -44, Pakdenya.class)!=null)
        {
            setLocation(getX()+1, getY());
            xSpeed = 0;
        } 
   
        if(getOneObjectAtOffset(0, getImage().getHeight()/2+4, MovingPlatform2.class)!=null)
        {
            Actor p;
            p = getOneObjectAtOffset(0, getImage().getHeight()/2+4, MovingPlatform2.class);
            
            if(((MovingPlatform2)p).getVertical())
                setLocation(getX(), getY() + ((MovingPlatform2)p).getSpeed());
            else
                setLocation(getX() + ((MovingPlatform2)p).getSpeed(), getY());
            
        }
        
     
        if(getOneObjectAtOffset(0, getImage().getHeight()/2-5, Fire.class)!=null)
        {
            Actor p;
            p = getOneObjectAtOffset(0, getImage().getHeight()/2-5, Fire.class);
            fireCounter++;
            if (fireCounter % 10 == 0)
                decrementHealthFire();
        }
    }
    
    /**
     * Determines any changes in horizontal and vertical speeds for the actor.
     */
    private void getDirection()
    {

        if (Greenfoot.isKeyDown("left")) 
        {
            AnimasiKiri();
            idleCheck = 0;
            if (onGround)
            {
                xSpeed = -50;
               
            }
            else
                xSpeed = -30;
                
            
        }
        if (Greenfoot.isKeyDown("right")) 
        {
            AnimasiKanan();
            idleCheck = 0;
            if (onGround)
                xSpeed = 50; 
            else
                xSpeed = 30;
        }
        if ((Greenfoot.isKeyDown("up") && onGround)) 
        {
            ySpeed -= jSpeed; 
            jump.play();
        }
        else if (Greenfoot.isKeyDown("space") && onGround)
        {
            ySpeed -= jSpeed;
            jump.play();
        }
    }
    
    public boolean getAboutFace()
    {
        return aboutFace;
    }
    
    public int getHeight()
    {
        return getImage().getHeight();
    }
    
    public void decrementHealthBLG_MC(Kumbang blg)
    {
        zombieHit.play();
        
        if (health > 0)
        {
            health--;
            if (aboutFace)
                setImage("LarvaBloodyLeft.png");
            else
                setImage("LarvaBloodyRight.png");
        }
        
        else
        {
            if (blg.getX() > getX())
                KenaAttack(false);
            else
                KenaAttack(true);
        }
    }
    
    public void decrementHealthFire()
    {
        zombieHit.play();
        if (aboutFace)
            setImage("LarvaBloodyLeft.png");
        else
            setImage("LarvaBloodyRight.png");
        if (health > 0)
            health--;
        else
            KenaAttack(true);
    }
    
    public void increaseHealth() {
        if (health < 8) {
            health++;
        }
    }
    
    public void KenaAttack(boolean hitDirection)
   {
        
       while (rotCount <= 90)
       {
           if (rotCount % 2 == 0)
                setLocation(getX(), getY() + 2);
            
            if (rotCount < 15)
            {
                if (!aboutFace)
                {
                    setImage("LarvaHurtRight.png");
                }
                else
                {
                    setImage("LarvaHurtLeft.png");
                }
                
            }
            
            else if (rotCount == 18)
            {
                if (!aboutFace)
                    setImage("LarvaStandRight.png");
                else
                    setImage("LarvaStandLeft.png");
            }
            
            rotCount+=3;
            
            if (!hitDirection)
                setRotation(-rotCount);
            else
                setRotation(rotCount);
            
            
            if (rotCount == 90)
               world.removeObject(this);
        }
    }
    
    public int LevelSekarang()
    {
        return levelSekarang;
    }
    
    public void LevelNaik()
    {
        levelSekarang++;
    }
    
    public void PoopNambah()
    {
        poopCount++;
        MyWorld w = (MyWorld) getWorld();
        w.poopCounter(poopCount);  
    }
    
    public void PoopKurang()
    {
        poopCount--;
        MyWorld w = (MyWorld) getWorld();
        w.poopCounter(poopCount);
    }
}
