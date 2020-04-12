package game;
import shapes.Rectangle;

/**
 * The interface collidable.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the intersect point
     * @param currentVelocity the velocity before the intersect
     * @param ball the ball make the hitter
     * @return the new velocity expected after the hit (based on he force the object inflicted on us)
     */
    Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity);


}