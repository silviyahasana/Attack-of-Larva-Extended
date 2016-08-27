import greenfoot.*;

/**
 * Class MyWorld: sample world to show how to make use of my world super-class SWorld
 */
public class MyWorld extends SWorld
{
    GreenfootSound backgroundMusic = new GreenfootSound("backsound.mp3");

    private static final int GROUND_HEIGHT = 740;
    private static final int GROUND_ACTOR = 600;
    private static final int DEFAULT_STAIR_HEIGHT = GROUND_HEIGHT - 45;
    private Larva mc;
    private Score score;
    private int numEnemies = 15;
    /**
     * Creates a scrolling world using a main actor, a background, some obstacles, and a non-scrolling score.
     */
    public MyWorld()
    {    
        super(1024, 720, 1, 2937, 1024);
        
        backgroundMusic.setVolume(30);
        backgroundMusic.playLoop();
        mc = new Larva();
        setMainActor(mc, 150, 500); 
        mainActor.setLocation(-800, GROUND_HEIGHT - 85);
        GreenfootImage bg = new GreenfootImage("got-1.png");
        setScrollingBackground(bg); 
        addObject(new HealthBar(mc), 130, 690, false);
        addObject(new TextBox("poop.png"), 270, 690, false);
        score = new Score(mc.getPoopCount());
        addObject(score, 320, 690, false);
        addObject(new Ground(null), 200, GROUND_HEIGHT);
        prepare();
    }
    
    public MyWorld(Larva z, GreenfootImage image)
    {    
        super(1024, 720, 1, 2937, 1024);
        mc = z;
        setMainActor(mc, 150, 500); 
        mainActor.setLocation(-800, GROUND_HEIGHT - 85);
        GreenfootImage bg = image;
        setScrollingBackground(bg);
        addObject(new HealthBar(mc), 130, 690, false);
        addObject(new TextBox("poop.png"), 270, 690, false);
        score = new Score(mc.getPoopCount());
        addObject(score, 320, 690, false);
        addObject(new Ground(null), 200, GROUND_HEIGHT);
        //prepare();
    }
    
    public void stopSound() {
       // backgroundMusic.stop();
    }
    
    public void poopCounter(int poops) {
        score.setScore(poops);
    }
    
    public void createLevel(int level)
    {
        switch (level)
        {
            case 1:
                createLevel1();
                break;
            case 2:
                createLevel2();
                break;
            case 3:
                createLevel3();
                break;
            case 4:
                createLevel4();
                break;
            case 5:
                createLevel5();
                break;
            case 6:
                createLevel6();
                break;
            default:
                break;
        }
    }
    
    public void createLevel6()
    {
        makePlatform("bata.jpg", 300, GROUND_HEIGHT - 45, 1, 16);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 240, GROUND_HEIGHT - 185);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 240, GROUND_HEIGHT - 335);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 240, GROUND_HEIGHT - 485);
        makePlatform("bata.jpg", 220, GROUND_HEIGHT - 445, 2, 1);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 240, GROUND_HEIGHT - 635);
        makePlatform("bata.jpg", 220, GROUND_HEIGHT - 595, 2, 1);
        
        makePlatform("bata.jpg", 0, GROUND_HEIGHT - 145, 2, 1);
        makeMovingPlatform("bata.jpg", -275, GROUND_HEIGHT - 245, 125, 2, false, 2);
        makeMovingPlatform("bata.jpg", -500, GROUND_HEIGHT - 245, 100, 2, false, 2);
        
        makePlatform("bata.jpg", -600, GROUND_HEIGHT - 395, 2, 1);
        makeMovingPlatform("bata.jpg", -430, GROUND_HEIGHT - 645, 130, 1, true, 2);
        makeMovingPlatform("bata.jpg", -320, GROUND_HEIGHT - 695, 150, 2, false, 2);
        makeMovingPlatform("bata.jpg", -100, GROUND_HEIGHT - 615, 150, 1, false, 2);
        
        makeFire(-300, GROUND_HEIGHT - 565, 10, 0);
        makeFire(-300, GROUND_HEIGHT - 525, 1, 0);
        makeFire(60, GROUND_HEIGHT - 525, 1, 0);
        makeFire(-300, GROUND_HEIGHT - 485, 10, 0);
        
        makePlatform("bata.jpg", 700, GROUND_HEIGHT - 135, 32, 1);
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 740, GROUND_HEIGHT - 245);
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 1020, GROUND_HEIGHT - 245);
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 1300, GROUND_HEIGHT - 245);
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 1580, GROUND_HEIGHT - 245);
        
        makePlatform("bata.jpg", 860, GROUND_HEIGHT - 285, 3, 1);
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 900, GROUND_HEIGHT - 395);
        makePlatform("bata.jpg", 1140, GROUND_HEIGHT - 285, 3, 1);
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 1180, GROUND_HEIGHT - 395);
        makePlatform("bata.jpg", 1420, GROUND_HEIGHT - 285, 3, 1);
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 1460, GROUND_HEIGHT - 395);
        
        addObject(new Sausage(), 1800, GROUND_HEIGHT - 190);
        addObject(new Sausage(), 1850, GROUND_HEIGHT - 190);
        addObject(new Sausage(), 1900, GROUND_HEIGHT - 190);
        addObject(new Sausage(), 1750, GROUND_HEIGHT - 190);
        
        addObject(new Sausage(), 1775, GROUND_HEIGHT - 240);
        addObject(new Sausage(), 1825, GROUND_HEIGHT - 240);
        addObject(new Sausage(), 1875, GROUND_HEIGHT - 240);
        
        addObject(new Sausage(), 1800, GROUND_HEIGHT - 290);
        addObject(new Sausage(), 1850, GROUND_HEIGHT - 290);
        
        addObject(new Sausage(), 1825, GROUND_HEIGHT - 340);
        
        addObject(new EndLevel(), 1950, 250);
    }

    public void createLevel5()
    {
        makeStairs("bata.jpg", -400, GROUND_HEIGHT - 45, 5, false);
        makePlatform("bata.jpg", -200, GROUND_HEIGHT - 205, 2, 1);
        
        makeFire(-200, GROUND_HEIGHT - 45, 1, 0);
        makeFire(-40, GROUND_HEIGHT - 45, 1, 0);
        makeFire(120, GROUND_HEIGHT - 45, 1, 0);
        makeFire(280, GROUND_HEIGHT - 45, 1, 0);
        makeFire(440, GROUND_HEIGHT - 45, 1, 0);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 0, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), -50, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 50, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 150, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 250, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 350, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 450, GROUND_HEIGHT - 85);
        
        makePlatform("bata.jpg", 600, GROUND_HEIGHT - 45, 2, 4);
        
        makeFire(680, GROUND_HEIGHT - 45, 1, 0);
        makeFire(760, GROUND_HEIGHT - 45, 1, 0);
        makeFire(840, GROUND_HEIGHT - 45, 1, 0);
        makeFire(920, GROUND_HEIGHT - 45, 1, 0);
        makeFire(1000, GROUND_HEIGHT - 45, 1, 0);
        makeFire(1080, GROUND_HEIGHT - 45, 1, 0);
        makeFire(1160, GROUND_HEIGHT - 45, 1, 0);
        makeFire(1240, GROUND_HEIGHT - 45, 1, 0);
        makeFire(1320, GROUND_HEIGHT - 45, 1, 0);
        makeFire(1400, GROUND_HEIGHT - 45, 1, 0);
        
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 750, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 850, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 950, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1050, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1150, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1250, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1350, GROUND_HEIGHT - 85);
        
        makePlatform("bata.jpg", 1440, GROUND_HEIGHT - 45, 2, 4);
        makePlatform("bata.jpg", 1520, GROUND_HEIGHT - 45, 1, 16);
        addObject(new Sausage(), 1515, GROUND_HEIGHT - 845);
        makePlatform("bata.jpg", 1480, GROUND_HEIGHT - 285, 1, 1);
        
        makeMovingPlatform("bata.jpg", 1100, GROUND_HEIGHT - 400, 200, 1, false, 3);
        makeMovingPlatform("bata.jpg", 930, GROUND_HEIGHT - 560, 100, 2, false, 3);
        
        makePlatform("bata.jpg", 1180, GROUND_HEIGHT - 710, 2, 1);
        
        makeMovingPlatform("bata.jpg", 1400, GROUND_HEIGHT - 700, 100, 1, true, 2);
        
        addObject(new EndLevel(), 1950, 250);
    }
    
    
    public void createLevel4()
    {
        addObject(new Kumbang("KumbangAttackLeft0.png", 500, 600), -400, GROUND_HEIGHT - 85);
        
        makePlatform("bata.jpg", -200, GROUND_HEIGHT - 125, 2, 1);
        makeFire(-120, GROUND_HEIGHT - 125, 2, 0);
        makePlatform("bata.jpg", -120, GROUND_HEIGHT - 85, 2, 1);
        
        makePlatform("bata.jpg", 30, GROUND_HEIGHT - 45, 1, 13);
        
        makePlatform("bata.jpg", -370, GROUND_HEIGHT - 275, 2, 1);
        makeFire(-490, GROUND_HEIGHT - 275, 3, 0);
        makePlatform("bata.jpg", -490, GROUND_HEIGHT - 225, 3, 1);
        makePlatform("bata.jpg", -570, GROUND_HEIGHT - 225, 2, 4);
        makeFire(-770, GROUND_HEIGHT - 275, 5, 0);
        makePlatform("bata.jpg", -770, GROUND_HEIGHT - 225, 5, 1);
        
        makePlatform("bata.jpg", -670, GROUND_HEIGHT - 505, 1, 1);
        
        makePlatform("bata.jpg", -610, GROUND_HEIGHT - 665, 2, 1);
        makePlatform("bata.jpg", -450, GROUND_HEIGHT - 665, 1, 1);
        
        makePlatform("bata.jpg", -330, GROUND_HEIGHT - 525, 1, 6);
        makePlatform("bata.jpg", -370, GROUND_HEIGHT - 525, 1, 1);
        addObject(new Sausage(), -380, GROUND_HEIGHT - 575);
        makePlatform("bata.jpg", -330, GROUND_HEIGHT - 725, 14, 1);
        
        makePlatform("bata.jpg", 30, GROUND_HEIGHT - 525, 10, 1);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 100, GROUND_HEIGHT - 645);
        
        makePlatform("bata.jpg", 550, GROUND_HEIGHT - 525, 10, 1);
        makePlatform("bata.jpg", 860, GROUND_HEIGHT - 685, 2, 1);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 880, GROUND_HEIGHT - 765);
        addObject(new Sausage(), 700, GROUND_HEIGHT - 585);
        addObject(new Sausage(), 750, GROUND_HEIGHT - 585);
        addObject(new Sausage(), 725, GROUND_HEIGHT - 635);
        
        //Lower
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 500, GROUND_HEIGHT - 85);
        addObject(new Kumbang("KumbangAttackLeft0.png", 750, 600), 800, GROUND_HEIGHT - 85);
        
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 1480, GROUND_HEIGHT - 245);
        makePlatform("bata.jpg", 1400, GROUND_HEIGHT - 165, 10, 1);
        addObject(new Kumbang("KumbangAttackLeft0.png", 600, 600), 1480, GROUND_HEIGHT - 85);
        
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 245);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 245);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        
        
        addObject(new EndLevel(), 1950, 250);
    }
    
    public void createLevel3()
    {
        makeStairs("bata.jpg", -400, GROUND_HEIGHT - 45, 5, false);
        
        makePlatform("bata.jpg", -200, GROUND_HEIGHT - 45, 3, 5);
        
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 0, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 40, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 80, GROUND_HEIGHT - 85);
        
        addObject(new Kumbang("KumbangAttackLeft0.png", 500, 600), 140, GROUND_HEIGHT - 85);
        
        makePlatform("bata.jpg", 180, GROUND_HEIGHT - 205, 2, 1);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 195, GROUND_HEIGHT - 330);
        
        makePlatform("bata.jpg", 600, GROUND_HEIGHT - 285, 2, 1);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 615, GROUND_HEIGHT - 330);
        makePlatform("bata.jpg", 1120, GROUND_HEIGHT - 285, 2, 1);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 1135, GROUND_HEIGHT - 330);
        
        makePlatform("bata.jpg", 800, GROUND_HEIGHT - 145, 2, 1);
        makePlatform("bata.jpg", 870, GROUND_HEIGHT - 285, 2, 1);
        makePlatform("bata.jpg", 940, GROUND_HEIGHT - 145, 2, 1);
        
        makePlatform("bata.jpg", 750, GROUND_HEIGHT - 445, 2, 1);
        addObject(new Sausage(), 770, GROUND_HEIGHT - 495);
        makePlatform("bata.jpg", 990, GROUND_HEIGHT - 445, 2, 1);
        addObject(new Sausage(), 1010, GROUND_HEIGHT - 495);
        makePlatform("bata.jpg", 1200, GROUND_HEIGHT - 445, 2, 1);
        
        makeFire(1080, GROUND_HEIGHT - 45, 8, 0);
        
        addObject(new EndLevel(), 1950, 250);
    }
    
   
    
    public void createLevel1()
    {
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 100, GROUND_HEIGHT-35);
        
        makeStairs("bata.jpg", 400, GROUND_HEIGHT - 45, 2, false);
        makeStairs("bata.jpg", 480, GROUND_HEIGHT - 45, 2, true);
        
        addObject(new Kumbang("KumbangAttackLeft0.png", 500, 600), 600, GROUND_HEIGHT - 85);
        
        addObject(new Poop(false), 700, GROUND_HEIGHT - 45);
        addObject(new Poop(false), 720, GROUND_HEIGHT - 45);
        addObject(new Poop(false), 740, GROUND_HEIGHT - 45);
        
        makePlatform("bata.jpg", 900, GROUND_HEIGHT - 100, 3, 1);
        makePlatform("bata.jpg", 1100, GROUND_HEIGHT - 180, 3, 1);
        addObject(new Raflesia("RaflesiaStandLeft.png"), 1150, GROUND_HEIGHT - 260);
        
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1400, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1450, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1400, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1450, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1500, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1550, GROUND_HEIGHT - 85);
        addObject(new Kumbang("KumbangAttackLeft0.png", 500, 600), 1650, GROUND_HEIGHT - 85);

        addObject(new EndLevel(), 1950, 250);
    }
    
    public void createLevel2()
    {
        makeStairs("bata.jpg", -500, GROUND_HEIGHT - 45, 6, false);
        makePlatform("bata.jpg", -260, GROUND_HEIGHT - 45, 1, 6);
        makePlatform("bata.jpg", -220, GROUND_HEIGHT - 165, 1, 1);
        
        makePlatform("bata.jpg", -80, GROUND_HEIGHT - 205, 3, 2);
        makePlatform("bata.jpg", 40, GROUND_HEIGHT - 205, 2, 1);
        makeFire(40, GROUND_HEIGHT - 245, 2, 0);
        makePlatform("bata.jpg", 120, GROUND_HEIGHT - 205, 3, 2);
        
        addObject(new Sausage(), 300, GROUND_HEIGHT - 45);
        
        makePlatform("bata.jpg", 360, GROUND_HEIGHT - 205, 3, 2);
        makePlatform("bata.jpg", 600, GROUND_HEIGHT - 205, 3, 2);
        
        makePlatform("bata.jpg", 850, GROUND_HEIGHT - 205, 3, 2);
        makePlatform("bata.jpg", 970, GROUND_HEIGHT - 205, 3, 1);
        makeFire(970, GROUND_HEIGHT - 245, 3, 0);
        makePlatform("bata.jpg", 1090, GROUND_HEIGHT - 205, 3, 2);
        
        addObject(new Sausage(), 1280, GROUND_HEIGHT - 45);
        
        addObject(new Kumbang("KumbangAttackLeft0.png", 500, 600), 1000, GROUND_HEIGHT - 85);
        
        addObject(new Kumbang("KumbangAttackLeft0.png", 500, 600), 1300, GROUND_HEIGHT - 85);
        makePlatform("bata.jpg", 1330, GROUND_HEIGHT - 45, 2, 5);
        makePlatform("bata.jpg", 1410, GROUND_HEIGHT - 45, 2, 2);
        makePlatform("bata.jpg", 1490, GROUND_HEIGHT - 45, 2, 3);
        
        
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        addObject(new Pakdenya("PakdenyaWalkLeft0.png", 0, 0), 1600, GROUND_HEIGHT - 85);
        
        addObject(new EndLevel(), 1950, 250);
    }
    
    private void makeFlatPlatform(String image, int startX, int startY, int length)
    {
        for (int i = 0; i < length; i++)
        {
            Platform p = new Platform(image);
            addObject(p, startX + i * p.getImage().getWidth(), startY, true);
        }
    }
    
    private void makeVerticalPlatform(String image, int startX, int startY, int length)
    {
        for (int i = 0; i < length; i++)
        {
            Platform p = new Platform(image);
            addObject(p, startX, startY - i * p.getImage().getHeight(), true);
        }
    }
    
    private void makePlatform(String image, int startX, int startY, int width, int height)
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                Platform p = new Platform(image);
                addObject(p, startX + i * p.getImage().getHeight(), startY - j * p.getImage().getHeight(), true);
            }
        }
    }
    
    
    private void makeStairs(String image, int startX, int startY, int heightUnits, boolean b) //left is true, right is false
    {
        if (!b)
        {
            for (int i = 0; i < heightUnits; i++)
            {
                Platform p = new Platform(image);
                addObject(p, startX + i * p.getImage().getWidth(), startY - i * p.getImage().getHeight(), true);
                //System.out.println(startX + i * p.getImage().getWidth()/2);
            }
            
            for (int i = 0; i < heightUnits; i++)
            {
                for (int j = i + 1; j < heightUnits; j++)
                {
                    Platform p = new Platform(image);
                    addObject(p, startX + j * p.getImage().getWidth(), startY - i * p.getImage().getHeight(), true);
                }
            }
        }
        
        else
        {
            for (int i = 0; i < heightUnits; i++)
            {
                Platform plat = new Platform(image);
                addObject(plat, startX - i * plat.getImage().getWidth(), startY - i * plat.getImage().getHeight(), true);
            }
            
            for (int i = 0; i <  heightUnits; i++)
            {
                for (int j = i + 1; j < heightUnits; j++)
                {
                    Platform plat = new Platform(image);
                    addObject(plat, startX - j * plat.getImage().getWidth(), startY - i * plat.getImage().getHeight(), true);
                }
            }
        
        }
                
                
    }
  
    private void makePlatform(String image, int startX, int startY, int units)
    {
        for (int i = 0; i < units;i++)
        {
            Platform p = new Platform(image);
            addObject(p, startX + i * p.getImage().getWidth(), startY, true);
        }
    }
    
    private void makeMovingPlatform(String image, int startX, int startY, int distance, int speed, boolean vertical, int units)
    {
        for (int i = 0; i < units;i++)
        {
            MovingPlatform2 p = new MovingPlatform2(image, distance, speed, vertical);
            addObject(p, startX + i * p.getImage().getWidth(), startY, true);
        }
    }
    
     public void started()
    {
       removeObjects(getObjects(StartButton.class));        
       createLevel1();
    }

    private void prepare()
    {
        StartButton StartButton = new StartButton();
        addObject(new StartButton(), 579, 251); 
    }
    
    
    private void makeFire(int startX, int startY, int width, int height)
    {
            
        for (int i = 0; i < width; i++)
        {
            Fire f = new Fire();
            if (height > 0)
            {
                f.getImage().scale(f.getWidth(), f.getHeight() + height);
            }
            addObject(f, startX + i * (f.getImage().getWidth()) , startY - height/2, true);
        }
    }
}
