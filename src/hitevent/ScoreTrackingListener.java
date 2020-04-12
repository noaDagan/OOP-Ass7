package hitevent;

import game.Ball;
import game.Block;

/**
 * The .ScoreTrackingListener implement of .HitListener.
 */
public class ScoreTrackingListener implements HitListener {
    //Members
    private Counter currentScore;

    /**
     * The hitevent.ScoreTrackingListener method.
     *
     * @param scoreCounter a Counter type to keeping sum of score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * The hitEvent method .
     *
     * @param beingHit a block whenever the beingHit object is hit
     * @param hitter   is the game.Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitInBlock() == 0) {
            this.currentScore.increase(100);
        } else if (beingHit.getHitInBlock() == 1) {
            this.currentScore.increase(5);
        }

    }
}