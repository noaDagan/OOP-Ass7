package hitevent;

import game.Ball;
import game.Block;
import game.GameLevel;

/**
 * The Counter.BallRemover class implements of Counter.HitListener.
 * a ball remover is in charge of removing balls from game,
 * as well as keeping count of the number of ball that remain.
 */
public class BallRemover implements HitListener {
    //Members
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor build the Counter.BallRemover by a game and Counter.
     *
     * @param gameLevel         a game type
     * @param removedBalls a Counter type to keeping count the number of ball that remain
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.remainingBalls = removedBalls;
        this.gameLevel = gameLevel;
    }

    /**
     * The method called whenever balls are hit on the block death.
     * When the block hit, he should be removed from the game and decrease the hitevent of remainingBalls
     *
     * @param beingHit a block whenever the beingHit object is hit
     * @param hitter   is the game.Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        // Update the count of ball to keeping the sum of remainingBalls
//        this.remainingBalls.decrease(1);
    }
}
