package hitevent;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;

import java.awt.Color;

/**
 * The ScoreIndicator class.
 */
public class NameIndicator implements Sprite {
    //Members
    private String nameLevel;

    /**
     * The constructor build by string.
     *
     * @param nameLevel a string type
     */
    public NameIndicator(String nameLevel) {
        this.nameLevel = nameLevel;
    }

    /**
     * The drawOn methods.
     *
     * @param d a DrawSurface type
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(580, 15, "Level Name: " + nameLevel, 15);
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

