package hitevent;

import game.Ball;
import game.Block;

/**
 * The HitListener interface method.
 */
public interface HitListener {

    /**
     * The hitEvent method is called whenever a object is hit.
     *
     * @param beingHit a block whenever the beingHit object is hit
     * @param hitter   is the game.Ball that's doing the hitting
     */
     void hitEvent(Block beingHit, Ball hitter);
}