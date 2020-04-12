package game;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * the class spirit collection save the spirit collection.
 */
public class SpriteCollection {
    //Members
    private List<Sprite> listOfSpriteCollection;

    /**
     * Constructor make a new ArrayList to spirits.
     */
    public SpriteCollection() {
        this.listOfSpriteCollection = new ArrayList();
    }

    /**
     * Function add the spirit to the list.
     *
     * @param s a spirit add to the list
     */
    public void addSprite(Sprite s) {
        listOfSpriteCollection.add(s);
    }


    /**
     * Function remove a sprite from the list.
     *
     * @param s a spirit to remove from the list
     */
    public void removeSprite(Sprite s) {
        listOfSpriteCollection.remove(s);
    }

    /**
     * The function call timePassed() on all sprites.
     *
     * @param dt type type
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < listOfSpriteCollection.size(); i++) {
            ((Sprite) this.listOfSpriteCollection.get(i)).timePassed(dt);
        }
    }

    /**
     * The function call drawOn(d) on all sprites.
     *
     * @param d a DrawSurface to drawOn with spirit from the list
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < listOfSpriteCollection.size(); i++) {
            ((Sprite) listOfSpriteCollection.get(i)).drawOn(d);
        }
    }

    /**
     * The getListOfSpriteCollection method.
     * @return a list of sprite
     */
    public List<Sprite> getListOfSpriteCollection() {
        return listOfSpriteCollection;
    }
}