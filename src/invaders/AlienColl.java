package invaders;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The AlienColl method.
 */
public class AlienColl implements Sprite {
    //Members
    private List<AlienClass> aliens = new ArrayList<>();

    /**
     * constructor build by a list of aliens.
     *
     * @param aliens a list
     */
    public AlienColl(List<AlienClass> aliens) {
        this.aliens = aliens;
    }

    /**
     * getCol method.
     *
     * @return y of aliens
     */
    public double getYCol() {
        for (int i = 0; i < this.aliens.size(); i++) {
            if (this.aliens.get(i).getCollisionRectangle().getUpperLeft().getY() > 0) {
                return this.aliens.get(i).getCollisionRectangle().getUpperLeft().getY();
            }
        }
        return 0;
    }

    /**
     * getAlienCol method.
     * @return list of AlienClass
     */
    public List<AlienClass> getAlienCol() {
        return this.aliens;
    }

    /**
     * The getXCol method.
     * @return x of aliens
     */
    public double getXCol() {
        for (int i = 0; i < this.aliens.size(); i++) {
            if (this.aliens.get(i).getCollisionRectangle().getUpperLeft().getX() > 0) {
                return this.aliens.get(i).getCollisionRectangle().getUpperLeft().getX();
            }
        }
        return 0;
    }


    @Override
    /**
     * drawOn method.
     */
    public void drawOn(DrawSurface d) {
    }

    @Override
    /**
     * drawOn method.
     */
    public void timePassed(double dt) {
    }

    /**
     * addToGame method.
     * @param g a GameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removeFromGame method.
     * @param g a game level type
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
