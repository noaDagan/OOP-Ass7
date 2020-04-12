package game;

import biuoop.DrawSurface;
import shapes.Line;

/**
 * The class ball crate a ball.
 */
public class Ball implements Sprite {
    //Members
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * constructor game.Ball build a ball with center value,radius and color.
     *
     * @param center value of the center circle
     * @param r      the radius od the circle
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        // initialize the velocity with (0,0)
        this.velocity = new Velocity(0, 0);
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
    }

    /**
     * constructor game.Ball build a  ball with (x,y) point ,radius and color.
     *
     * @param x     value of point
     * @param y     value of point
     * @param r     the value radius of point
     * @param color the color value of point
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        // initialize the velocity with (0,0)
        this.velocity = new Velocity(0, 0);
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * accessors to return the center value.
     *
     * @return this integer center of circle
     */
    public int getX() {
        return (int) this.center.getX();

    }

    /**
     * accessors to return the center value.
     *
     * @return this integer center of circle
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessors to return the radius.
     *
     * @return this radius of the circle
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessors to return the color.
     *
     * @return this color of circle
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The function change ball velocity.
     *
     * @param v a velocity parameter
     */
    public void setVelocity(Velocity v) {
        this.velocity.setDx(v.getDx());
        this.velocity.setDy(v.getDy());
    }

    /**
     * The function change ball velocity by speed and distance.
     *
     * @param dx type double
     * @param dy type double
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The function Set gameEnvironment.
     *
     * @param gameEnvironmentFromGame a game enviroment type
     */
    public void setGameEnvironment(GameEnvironment gameEnvironmentFromGame) {
        this.gameEnvironment = gameEnvironmentFromGame;
    }

    /**
     * The function return the next step of the ball.
     * if the ball hits in the border of the screen change the angle direction
     * change the dx and dy according to his direction and velocity
     *
     * @param dt a type double
     */
    public void moveOneStep(double dt) {
        CollisionInfo collisionInfo;
        Point newCenter;
        Velocity newVelocity;
        double newDx = this.velocity.getDx() * dt;
        double newDy = this.velocity.getDy() * dt;
        Velocity velocity1 = new Velocity(newDx, newDy);

//        newCenter = this.getVelocity().applyToPoint(this.center);
        //create new line

        newCenter = velocity1.applyToPoint(this.center);
        Line ballTrajectory = new Line(this.center.getX(), this.center.getY(), newCenter.getX(), newCenter.getY());
        collisionInfo = this.gameEnvironment.getClosestCollision(ballTrajectory);
        // No Intersection
        if (collisionInfo == null) {
            this.center = newCenter;
        } else {
            newVelocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.getVelocity());

            //check the angle the ball come to the collision point and change the dx or dy.
            // if the dx is positive or negative

            if (newVelocity.getDx() > 0) {
                newCenter.setX(newCenter.getX() - 1);
            } else {
                newCenter.setX(newCenter.getX() + 1);
            }
            if (newVelocity.getDy() > 0) {
                newCenter.setY(newCenter.getY() - 1);
            } else {
                newCenter.setY(newCenter.getY() + 1);
            }
            //new center
            this.velocity = newVelocity;
        }
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface the type of the screen
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        // crate the ball by x , y and radius
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * the function call to moveOneStep function.
     *
     * @param dt a type double
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * The function add this spirit to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    /**
     * The function delete this spirit from the game.
     *
     * @param g type game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }


}




