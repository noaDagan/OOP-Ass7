package invaders;

import biuoop.DrawSurface;
import game.Block;
import game.GameLevel;
import shapes.Rectangle;

import java.awt.Color;
import java.awt.Image;

/**
 * The AlienClass class create a alien as a extends of block.
 * The alien show a image above all block
 */
public class AlienClass extends Block {
    //Members
    private Image image;

    /**
     * The constructor build a alien by extend a block and show image.
     *
     * @param r     a Rectangle
     * @param c     a color
     * @param image a Image
     */
    public AlienClass(Rectangle r, Color c, Image image) {
        super(r, c);
        this.image = image;
    }

    /**
     * The drawOn method draw image of alien above a block.
     *
     * @param d DrawSurface type to make the rectangle
     */
    public void drawOn(DrawSurface d) {
        d.drawImage((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), this.image);
        super.drawOn(d);
    }

    /**
     * The setImage method.
     *
     * @param i type image
     */
    public void setImage(Image i) {
        this.image = i;
    }

    /**
     * The getImage.
     *
     * @return a image
     */
    public Image getImage() {
        return image;
    }

    @Override
    /**
     * The timePassed method.
     */
    public void timePassed(double dt) {

    }

    /**
     * The addToGame method add Alien class to the game.
     *
     * @param g add the block to the collidable and spirit list
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


}
