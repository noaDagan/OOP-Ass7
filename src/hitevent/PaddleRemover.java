package hitevent;

import game.Ball;
import game.Block;
import game.GameLevel;

/**
 * PaddleRemover class.
 */
public class PaddleRemover implements HitListener {

    //Members
    private GameLevel gameLevel;
    private Counter removePaddle;
    private ScoreTrackingListener scoreTrackingListener;

    /**
     * The constructor build Counter.BlockRemover by a game and hitevent.Counter.
     *
     * @param gameLevel    a game type
     * @param removePaddle a Counter type to keeping count the number of block that remain
     */
    public PaddleRemover(GameLevel gameLevel, Counter removePaddle) {
        this.removePaddle = removePaddle;
        this.gameLevel = gameLevel;
    }

    /**
     * The method called whenever balls are hit on a specify block.
     * Blocks that are hit and reach 0 hit-points should be removed from the game.
     *
     * @param beingHit a block whenever the beingHit object is hit
     * @param hitter   is the game.Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
//        beingHit.removeFromGame(gameLevel);
        // Update the count of block to keeping the sum of remainingBlocks
        this.removePaddle.decrease(1);


    }

}
