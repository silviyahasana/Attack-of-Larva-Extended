import greenfoot.*; 
import java.util.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kumbang here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kumbang extends Musuh
{
    /**
     * Act - do whatever the Kumbang wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    final int jSpeed = 27; 
    private int ySpeed = 0, xSpeed = 0; 
    boolean aboutFace;
    private boolean onGround; 
    int imageOffset = 9;
    int imageAttackOffset = 19;
    int numImagesLeft = 4;
    int numImagesRight = 4;
    int imageAttackIndexLeft = 0;
    int imageAttackIndexRight = 0;
    int imageIndexLeft = 0;
    int imageIndexRight = 0;
    int numImagesAttackRight = 2;
    int numImagesAttackLeft = 2;
    int l, r;
    private GreenfootImage[] imagesRight;
    private GreenfootImage[] imagesLeft;
    private GreenfootImage[] imagesAttackRight;
    private GreenfootImage[] imagesAttackLeft;
    private World world = getWorld();
    private boolean hitWithWeapon = false;
    private int rotCount = 0;
    int canJump = 0;
    boolean hitDirection;
    int count = 0;
    int attackCountDZ = 0;
    int attackCountMC = 0;
    Pakdenya dz;
    Larva mc;
    int right, left;
    private int health = 3;
    private Larva larva;
    
  
    GreenfootSound hit = new GreenfootSound("enemyhit0.wav");
    
    public Kumbang(String image, int l, int r)
    {
        super(image);
        AnimateLoad();
        AnimateLoadAttack();
        if (image.compareTo("KumbangJalanRight1.png") == 0)
            aboutFace = false;
        else
            aboutFace = true;
            
        left = l;
        right = r;
    }
    
    public void act()
    {
        count++;
        larva = getLarva();
        
        if (health <= 0) {
            FallHuman();
        }
        else
        {
            if (findAttackDZ())
            {
                if (aboutFace && dz.getX() < getX())
                    AnimateAttackLeft();
                else
                    AnimateAttackRight();
                if (attackCountDZ++ % 40 == 0)
                {
                    dz.decrementHealthBLG_DZ(this);
                }
            }
            else if (findAttackMC())
            {
                    if (aboutFace)
                    {
                        AnimateAttackLeft();
                    }
                    else
                    {
                        AnimateAttackRight();
                    }
                if (attackCountMC++ % 40 == 0)
                {   
                        
                    mc.decrementHealthBLG_MC(this);
                }
            }

            if ((canJump = super.findPlatform()) > 0)
            {
                if (canJump == 1 && aboutFace && onGround)
                {
                    jump();
                }
                if (canJump == 2 && !aboutFace && onGround)
                {
                    jump();
                    
                }
            }
                
            if (xSpeed > 0)
                aboutFace = false;
            else if (xSpeed < 0)
                aboutFace = true;
            
            
            if (health == 1)
                hitDirection = getHit();
            
            else
                getHit();
                
            if (health > 0)
            {
                getDirection();
                move();
            }
        }
    }
    
    private void AnimateLoad() {
        int i = 0;
        int j = 0;
        int numImage = 0;
        imagesRight = new GreenfootImage[numImagesRight*imageOffset];
        imagesLeft = new GreenfootImage[numImagesLeft*imageOffset];
        while (numImage < numImagesRight) {
            GreenfootImage addImage = new GreenfootImage("KumbangJalanRight" + numImage + ".png");
            for (j = 0; j < imageOffset; j++) {
                imagesRight[i+j] = addImage;
            }
            i += j;
            numImage++;
        }    

        i = 0;
        numImage = 0;
        while (numImage < numImagesLeft) {
            GreenfootImage addImage = new GreenfootImage("KumbangJalanLeft" + numImage + ".png");
            for (j = 0; j < imageOffset; j++) {
                imagesLeft[i+j] = addImage;
            }
            i += j;
            numImage++;
        }
    } 
    
    private void AnimateLoadAttack() {
        int i = 0;
        int j = 0;
        int numImage = 0;
        imagesAttackRight = new GreenfootImage[numImagesAttackRight*imageAttackOffset];
        imagesAttackLeft = new GreenfootImage[numImagesAttackLeft*imageAttackOffset];
        while (numImage < numImagesAttackRight) {
            GreenfootImage addImage = new GreenfootImage("KumbangAttackRight" + numImage + ".png");
            for (j = 0; j < imageAttackOffset; j++) {
                imagesAttackRight[i+j] = addImage;
            }
            i += j;
            numImage++;
        }    
        i = 0;

        numImage = 0;
        while (numImage < numImagesAttackLeft) {
            GreenfootImage addImage = new GreenfootImage("KumbangAttackLeft" + numImage + ".png");
            for (j = 0; j < imageAttackOffset; j++) {
                imagesAttackLeft[i+j] = addImage;
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
    
    private void AnimateAttackRight() {
        if (imageAttackIndexRight < numImagesAttackRight*imageAttackOffset)
            setImage(imagesAttackRight[imageAttackIndexRight]);
        else
            imageAttackIndexRight = 0;
       imageAttackIndexRight++;
    }
    
    private void AnimateAttackLeft() {
        if (imageAttackIndexLeft < numImagesAttackLeft*imageAttackOffset)
            setImage(imagesAttackLeft[imageAttackIndexLeft]);
        else
            imageAttackIndexLeft = 0;
       imageAttackIndexLeft++;
    }
        
    /**
     * Moves the actor with appropriate image.  Checks for obstacles and adjusts
     * the position of the actor accordingly.
     */
    private void move()
    {
        Actor obj;
        ySpeed++; 
        
        if ((count % 4 > 0))
            setLocation(getX()+xSpeed/10, getY()+ySpeed/2);
         
 
        if((xSpeed>0 && aboutFace) || (xSpeed<0 && !aboutFace)) 
        {

            if (aboutFace)
                setImage("KumbangJalanLeft1.png");
            else 
                setImage("KumbangJalanRight1.png");
                
            aboutFace = !aboutFace;
        }
        

        onGround=false; 
  
        while(getOneObjectAtOffset(0, getImage().getHeight()/2+5, Platform.class)!=null)
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
           
        }
        while(getOneObjectAtOffset(-getImage().getWidth()/2 + 25, -10, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 25, 10, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 25, 53, Platform.class)!=null || getOneObjectAtOffset(-getImage().getWidth()/2 + 25, -44, Platform.class)!=null)
        {
            setLocation(getX()+1, getY());
            
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
        
           }
    
    private boolean getHit()
    {
        List<Arm> arm;
        List<Poop> poop;
        world = getWorld();
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
                    return false;
                }
                else
                {
                    decrementHealth();
                    hitDirection = true;
                    return true;
                }
            }
        }
        else if (poop.size() > 0 && count > -1)
        {
            if(intersects(poop.get(count)))
            {
                if (poop.get(count).getX() > getX())
                {
                    health--;
                    return false;
                }
                else
                {
                    health--;
                    return true;
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
        World world = getWorld();
        List<Larva> larva;
        Larva z;
        larva = world.getObjects(Larva.class);
        z = larva.get(0);
        
        int y = Math.abs(z.getY() - getY());
        int x = Math.abs(z.getX() - getX());
        
        
        if (findAttackDZ())
            xSpeed = 0;
            
        else if ((z.getX() + (z.getImage().getWidth() - 10) < getX()) && y < 300 && x < 500)
        {
            xSpeed = -30;
            aboutFace = true;
            AnimateLeft();
        }
                
        else if ((z.getX() - (z.getImage().getWidth() - 10) > getX()) && y < 300 & x < 500)
        {
            xSpeed = 30;
            aboutFace = false;
            AnimateRight();
        }
        
        else if (y < 100)
        {
            xSpeed = 0;
            super.attack();
        }
        
        else
            xSpeed = 0;
            
        
        
        if (z.getX() + 10 > getX())
        {
            aboutFace = false;
        }
        
        if (z.getX() - 10 < getX())
        {
            aboutFace = true;
        }
        

        
    }
    
    private void dieHuman()
    {
        if (hitWithWeapon == false)
            getHit();
        
    }
    
    public boolean getAboutFace()
    {
        return aboutFace;
    }
    
    public int getHeight()
    {
        return getImage().getHeight();
    }
    
    private void jump()
    {
        ySpeed = -jSpeed;
    }
    
   public void FallHuman()
   {

        
        //System.out
        if (rotCount < 90)
        {
            if (rotCount % 2 == 0)
                setLocation(getX(), getY() + 2);
            
            if (rotCount < 15)
            {
                if (!aboutFace)
                {
                    setImage("KumbangHurtRight.png");
                }
                else
                {
                    setImage("KumbangHurtLeft.png");
                }
                
            }
            
            else if (rotCount == 18)
            {
                if (!aboutFace)
                    setImage("KumbangJalanRight1.png");
                else
                    setImage("KumbangJalanLeft1.png");
            }
            
            rotCount+=3;
            
            if (!hitDirection)
                setRotation(-rotCount);
            else
                setRotation(rotCount);
        }
        
        if (rotCount == 90) {
           world.addObject(new Sausage(), getX(), getY());
           world.removeObject(this);
        }
    }
    
    private boolean findAttackDZ()
    {
        Actor a;
        
        if ((a = getOneObjectAtOffset(getImage().getWidth()/2 - 10, 0, Pakdenya.class))!=null && !aboutFace)
        {
            dz = (Pakdenya)a;
            return true;
        }
      
        if ((a = getOneObjectAtOffset(-getImage().getWidth()/2 + 10, 0, Pakdenya.class))!=null && aboutFace) 
        {
            dz = (Pakdenya)a;
            return true;
        }
        
        return false;
    }
    
    public boolean findAttackMC()
    {
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
    
    private Larva getLarva()
    {
        World world = getWorld();
        List<Larva> mc = world.getObjects(Larva.class);
        
        return mc.get(0);
    }
    
    private void decrementHealth()
    {
        Random rand = new Random();
        if (rand.nextInt() % 2 == 0)
            hit.play();
            
        health--;
        if (aboutFace)
            setImage("KumbangHurtLeft.png");
        else
            setImage("KumbangHurtRight.png");
    }
        
}
    

   

