package game;

import biuoop.DrawSurface;
import hitevent.HitListener;
import hitevent.HitNotifier;
import shapes.Rectangle;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * The class game.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //Members
    private int wight;
    private int height;
    private Rectangle shape;
    private Color color;
    private int hitInBlock;
    private List<HitListener> hitListeners;
    private int x;
    private int y;
    private boolean isPaddle;
    private boolean paddleIntersection;

    /**
     * The Constructor method of game.Block build the block by rectangle and color list of hitListeners and fillDraw.
     *
     * @param shape type shapes.Rectangle the shape of the block
     * @param color make the color of the block
     */
    public Block(Rectangle shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.hitInBlock = 1;
        hitListeners = new ArrayList();
        this.isPaddle = false;
        this.paddleIntersection = false;
    }

    /**
     * Create block by x and y.
     *
     * @param x int
     * @param y int
     */
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        this.shape = new Rectangle(new Point(this.x, this.y),
                this.getCollisionRectangle().getWidth(), this.getCollisionRectangle().getHeight());
        this.hitInBlock = 1;
        hitListeners = new ArrayList();

    }

    /**
     * The method getCollisionRectangle.
     *
     * @return the shapes.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * methoThe d getHitInBlock.
     *
     * @return the number of hits of this block.
     */
    public int getHitInBlock() {
        return this.hitInBlock;
    }

    /**
     * The method setHitInBlock update the block num of hits.
     *
     * @param numOfHits a updated sum of number hitting the block.
     */
    public void setHitInBlock(int numOfHits) {
        this.hitInBlock = numOfHits;
    }

    /**
     * The getUpperLeft hit change the velocity after the collisionPoint.
     *
     * @param hitter          the ball that ding the hitting
     * @param collisionPoint  the intersect point
     * @param currentVelocity the velocity before the intersect
     * @return the new velocity in accordance to the update dx and dy
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Create  new velocity by dx and dy of the current velocity
        // Check if the ball hit on the blocks by y point value
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if ((collisionPoint.getY() == shape.getUpperLeft().getY() + shape.getHeight())
                || (collisionPoint.getY() == shape.getUpperLeft().getY())) {
            //Change the dy of the new velocity
            newVelocity.setDy(-currentVelocity.getDy());
        } // Check if the ball hit on the block by x point value
        if ((collisionPoint.getX() == shape.getUpperLeft().getX() + shape.getWidth())
                || (collisionPoint.getX() == shape.getUpperLeft().getX())) {
            //Change the dx of the new velocity
            newVelocity.setDx(-currentVelocity.getDx());
        } // if num of hit in block is not 0, remove the block
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * The method create the rectangle by color, UpperLeft point width and height.
     *
     * @param d DrawSurface type to make the rectangle
     */
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                    (int) getCollisionRectangle().getUpperLeft().getY(),
                    (int) getCollisionRectangle().getWidth(), (int) getCollisionRectangle().getHeight());
            d.setColor(this.color);
            d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                    (int) getCollisionRectangle().getUpperLeft().getY(),
                    (int) getCollisionRectangle().getWidth(), (int) getCollisionRectangle().getHeight());
            d.setColor(this.color.WHITE);
        }
    }

    /**
     * The timePassed methods.
     *
     * @param dt a double
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * The method add the block to the list by g.
     *
     * @param g add the block to the collidable and spirit list
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    /**
     * The method delete the block from the lists of collidable and spirt by g.
     *
     * @param game delete the block from collidable and spirit list
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * The method make a new hitevent.HitListener list and Notify all the listeners about a hit event.
     *
     * @param hitter is the ball that doing the hitting
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The method add a listener to a list hit events.
     *
     * @param hl as a listener add to hit events
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * The method remove a listener from a list hit events.
     *
     * @param hl as a listener delete from hit events
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The method setWight.
     *
     * @param wightB type int
     */
    public void setWight(int wightB) {
        this.wight = wightB;
        this.shape = new Rectangle(this.shape.getUpperLeft(), wightB, this.height);
    }

    /**
     * The method setHeight.nunOfBall.
     *
     * @param heightB type int
     */
    public void setHeight(int heightB) {
        this.height = heightB;
        this.shape = new Rectangle(this.shape.getUpperLeft(), this.wight, heightB);
    }

    /**
     * The setColor method.
     *
     * @param c type color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * The method getWight.
     *
     * @return wight block
     */
    public int getWight() {
        return this.wight;
    }

    /**
     * The method getHeight.
     *
     * @return height block
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * The method getColor.
     *
     * @return color block
     */
    public Color getColor() {
        return color;
    }

}
