package levels;

import biuoop.DrawSurface;
import game.Block;
import game.GameLevel;
import game.Sprite;
import shapes.Shape;

import java.util.List;

/**
 * The levels.Background class.
 */
public class Background implements Sprite, Shape {
    //Members
    private Block background;
    private List<Shape> listOfShapes;
    private LevelInformation levelInformation;

    /**
     * The levels.Background constructor build by a background and list of geometry shapes.
     *
     * @param background   a shapes.Rectangle type of the background
     * @param listOfShapes a list of all the shape in the background.
     */
    public Background(Block background, List<Shape> listOfShapes) {
        this.background = background;
        this.listOfShapes = listOfShapes;
    }

    /**
     * The drawOn function create all the shape on background.
     *
     * @param d a DrawSurface type
     */
    public void drawOn(DrawSurface d) {
        this.background.drawOn(d);
        //check that list not empty
        if (listOfShapes.size() == 0) {
            return;
        } // draw all the shapes in the list of shape
        for (int i = 0; i < listOfShapes.size(); i++) {
            this.listOfShapes.get(i).drawOn(d);
        }

    }

    /**
     * The timePassed function.
     * @param dt a double
     */
    public void timePassed(double dt) {
        return;
    }

    @Override
    public void removeFromGame(GameLevel g) {
    }

    /**
     * The function addToListOfShapes.
     * @param listOfShape a list of all the shape in the background
     */
    @Override
    public void addToListOfShapes(List<Shape> listOfShape) {
        return;
    }
}
