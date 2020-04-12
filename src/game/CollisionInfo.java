package game;

/**
 * The collisionInfo class.
 */
public class CollisionInfo {
    //Members
    private Point pointCollision;
    private Collidable collisionObject;

    /**
     * The conctructor build the game.CollisionInfo by the collision point and collision object.
     *
     * @param pointCollision  the intersection point in the line
     * @param collisionObject the intersection point between 2 object
     */
    public CollisionInfo(Point pointCollision, Collidable collisionObject) {
        this.pointCollision = pointCollision;
        this.collisionObject = collisionObject;
    }

    /**
     * @return point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.pointCollision;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * The function set pointCollision of class game.CollisionInfo.
     *
     * @param pointOfCollision the point of the collision
     */
    public void setCollisionPoint(Point pointOfCollision) {
        this.pointCollision = pointCollision;
    }

    /**
     * The function set collisionObject of class game.CollisionInfo.
     *
     * @param collisionOfObject the object of the collison
     */
    public void setCollisionObject(Collidable collisionOfObject) {
        this.collisionObject = collisionObject;
    }
}