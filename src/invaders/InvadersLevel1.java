package invaders;

import game.Velocity;
import game.GameLevel;
import game.Block;
import game.Point;
import game.Sprite;
import game.ImageParser;
import levels.Background;
import levels.LevelInformation;
import shapes.Rectangle;
import shapes.Shape;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The InvadersLevel1 class.
 */
public class InvadersLevel1 implements LevelInformation {
    //members
    private static final int START_X = 150;
    private static final int START_Y = 500;
    private GameLevel g;
    private double speedAlien;
    private int numOfLevel;

    /**
     * The InvadersLevel1 constructor by GameLevel and double.
     * @param g a GameLevel type
     * @param speedAlien a double type
     */
    public InvadersLevel1(GameLevel g, double speedAlien) {
        this.g = g;
        this.speedAlien = speedAlien;
    }

    /**
     * createAlien method create all the AlienClass by a list.
     *
     * @param index type int
     * @return a list of AlienClass
     */
    public List<AlienClass> createAlien(int index) {
        int startX = 120;
        int startY = 100;
        List<AlienClass> alienList = new ArrayList<AlienClass>();
        try {
            String strForImage = ".\\resources\\block_images\\enemy.png";
            Image image = new ImageParser().imageFromString(strForImage);
                for (int r = 0; r < 5; r++) {
                    int x = startX + index * 50;
                    int y = startY + 50 * r;
                    Point point = new Point(x, y);
                    AlienClass alienClass = new AlienClass(new Rectangle(point, 40, 30), null, image);
                    alienClass.setHitInBlock(1);
                    alienList.add(alienClass);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return alienList;
    }

    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocity = new ArrayList();
        double speed = 250;
        listOfVelocity.add(Velocity.fromAngleAndSpeed(0, speed));
        return listOfVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 600;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Battle No" + numOfLevel;
    }


    @Override
    public Sprite getBackground() {
        Rectangle background = new Rectangle(new Point(0, 0), 800, 600);
        background.setColor(Color.black);
        List<shapes.Shape> shapeList = new ArrayList<Shape>();
        Background b = new Background(new Block(background, Color.BLACK), shapeList);
        return b;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlock = new ArrayList();
        int k = 0;
        int sumOfLine = 4;
        int x = START_X;
        int y = START_Y;
        while (k != 3) {
            for (int j = 0; j < sumOfLine; j++) {
                x = START_X + START_X * k;
                for (int i = 0; i <= 20; i++) {
                    Point upperPoint = new Point(x, y);
                    int widthB = 5;
                    int heightB = 5;
                    Rectangle r = new Rectangle(upperPoint, widthB, heightB);
                    Block block = new Block(r, Color.lightGray);
                    listOfBlock.add(block);
                    x = x + 5;
                }
                y = y + 5;
            }
            y = START_Y;
            k++;
        }
        return listOfBlock;
    }

    @Override
    /**
     * numberOfBlocksToRemove method.
     */
    public int numberOfBlocksToRemove() {
        return this.initialBallVelocities().size();
    }

    /**
     * setNumOfLevel method update the num of level.
     * @param numLevel a int type
     */
    public void setNumOfLevel(int numLevel) {
        this.numOfLevel = numLevel;
    }
}
