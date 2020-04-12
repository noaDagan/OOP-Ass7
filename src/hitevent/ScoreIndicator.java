package hitevent;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;

import java.awt.Color;

/**
 * The ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    //Members
    private Counter scoreCounter;

    /**
     * The constructor build by Counter.
     *
     * @param scoreCounterFromGame a counter type
     */
    public ScoreIndicator(Counter scoreCounterFromGame) {
        this.scoreCounter = scoreCounterFromGame;
    }

    /**
     * The drawOn methods.
     *
     * @param d a DrawSurface type
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(365, 15, "Score: " + Integer.toString(this.scoreCounter.getValue()), 15);
    }

    /**
     * The function notify the sprite that time has passed.
     *
     * @param dt type double
     */
    public void timePassed(double dt) {
        return;

    }

    @Override
    public void removeFromGame(GameLevel g) {
    }
}
