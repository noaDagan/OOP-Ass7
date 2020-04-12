package hitevent;

import game.Ball;
import game.Block;
import game.GameLevel;
import invaders.AlienClass;

/**
 * The Counter.BlockRemover methods implements Counter.HitListener.
 * a Counter.BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    //Members
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    private ScoreTrackingListener scoreTrackingListener;

    /**
     * The constructor build Counter.BlockRemover by a game and hitevent.Counter.
     *
     * @param gameLevel     a game type
     * @param removedBlocks a Counter type to keeping count the number of block that remain
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
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
        beingHit.removeFromGame(gameLevel);
        // Update the count of block to keeping the sum of remainingBlocks
        this.remainingBlocks.decrease(1);
        if (beingHit instanceof AlienClass) {
            gameLevel.getA().removeAlien(gameLevel, (AlienClass) beingHit);
            gameLevel.getCounterAlien().decrease(1);

        }


    }
}