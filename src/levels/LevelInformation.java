package levels;

import game.Block;
import game.Sprite;
import game.Velocity;
import invaders.AlienClass;

import java.util.List;

/**
 * The interface LevelInformation.
 */
public interface LevelInformation {

    /**
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * The methods initial velocity of each ball.
     *
     * @return the lost of velocity
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle Width.
     */
    int paddleWidth();

    /**
     * @return the level name.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return a list of all the blocks in this level.
     */
    List<Block> blocks();

    /**
     * @return number of blocks remain.
     */
    int numberOfBlocksToRemove();

    /**
     * The createAlien method.
     * @param index int type
     * @return a list of AlienClass
     */
    List<AlienClass> createAlien(int index);

    /**
     * The setNumOfLevel update the level number.
     * @param numOfLevel the index of the level
     */
    void setNumOfLevel(int numOfLevel);

}