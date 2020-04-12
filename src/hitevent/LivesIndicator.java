package hitevent;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;
import java.awt.Color;

/**
 * The LivesIndicator class.
 */
public class LivesIndicator implements Sprite {
    //Members
    private Counter livesCount;

    /**
     * The constructor build the LivesIndicator by a Counter.
     * @param livesCountFromGame a Counter of sum of lives og the player
     */
    public LivesIndicator(Counter livesCountFromGame) {
        this.livesCount = livesCountFromGame;
    }

    /**
     * The drawOn function draw the score on the screen.
     * @param d a DrawSurface type
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(200, 15, "Lives: " + Integer.toString(this.livesCount.getValue()), 15);
    }

    /**
     * The timePassed function.
     * @param dt type double
     */
    public void timePassed(double dt) {
        return;
    }

    @Override
    public void removeFromGame(GameLevel g) {
    }
}
