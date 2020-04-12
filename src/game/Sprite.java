package game;
import biuoop.DrawSurface;

/**
 * The interface game.Sprite.
 */
public interface  Sprite {

    /**
     * The function draw the sprite to the screen.
     * @param d a DrawSurface type
     */
    void drawOn(DrawSurface d);

    /**
     * The function notify the sprite that time has passed.
     * @param dt type double
     */
    void timePassed(double dt);

    /**
     * The removeFromGame method.
     * @param g a game level type
     */
    void removeFromGame(GameLevel g);

}