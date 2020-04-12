package game;

import shapes.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * The class game.GameEnvironment make list of collidable and add them to the list.
 */
public class GameEnvironment {
    //Members
    private List listOfCollidable;

    /**
     * Constructor - make the new ArrayList.
     */
    public GameEnvironment() {
        this.listOfCollidable = new ArrayList();
    }

    /**
     * The function add the given collidable to the environment.
     *
     * @param c from type game.Collidable to add
     */
    public void addCollidable(Collidable c) {
        listOfCollidable.add(c);
    }

    /**
     * The function remive a collidable from the environment.
     *
     * @param c from type game.Collidable to remive
     */
    public void removeCollidable(Collidable c) {
        listOfCollidable.remove(c);
    }


    /**
     * The function add the get collidable to the environment.
     *
     * @param index type int save the index place of the game.Collidable
     * @return the specific collidable in the list
     */
    public Collidable getCollidable(int index) {
        return (Collidable) this.listOfCollidable.get(index);
    }

    /**
     * The function find the closest intersection point in the list of point and return the index place.
     *
     * @param start       is the first point int the line.
     * @param listOfPoint is list of all the intersection point
     * @return the index of the closest intersection point to the start pooint
     */
    public int findClosestInsterctionPoint(Point start, List listOfPoint) {
        double minDistance = start.distance((Point) listOfPoint.get(0));
        double distanceOfTwoPoint;
        int minIndex = 0;
        for (int i = 1; i < listOfPoint.size(); i++) {
            distanceOfTwoPoint = start.distance((Point) listOfPoint.get(i));
            if (distanceOfTwoPoint < minDistance) {
                minDistance = distanceOfTwoPoint;
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * The boolean function check if the point exist in the list of point or not.
     *
     * @param pointIntersection               a one point intersection to check if equals to another point
     * @param listOfIntersectionAllRectangles a list of points
     * @return the function return true if the point is exist in the list, and false if not
     */
    public static boolean isPointExists(Point pointIntersection, List listOfIntersectionAllRectangles) {
        for (int i = 0; i < listOfIntersectionAllRectangles.size(); i++) {
            if (pointIntersection.equals((Point) listOfIntersectionAllRectangles.get(i))) {
                return true;
            }
        }
        return false;
    }


    /**
     * The function Assume an object moving from line.start() to line.end().
     * the function check the closest intersection point in the line
     *
     * @param trajectory the line of the intersection
     * @return if this object will not collide with any of the collidables in the collection return null,
     * else return the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int minIndex;
        CollisionInfo collisionInfo;
        Point pointIntersection;
        List listOfIntersectionAllRectangles = new ArrayList();
        List collidableObjectList = new ArrayList();
        for (int i = 0; i < listOfCollidable.size(); i++) {
            pointIntersection = trajectory.closestIntersectionToStartOfLine(
                    ((Collidable) listOfCollidable.get(i)).getCollisionRectangle());
            // Intersection occurdecrease the nums hit on dhe blocks
            if ((pointIntersection != null)
                    && (!isPointExists(pointIntersection, listOfIntersectionAllRectangles))) {
                // Not a paddle
                if (i != (listOfCollidable.size() - 1)) {
                    if (((Block) listOfCollidable.get(i)).getHitInBlock() != 0) {
                        ((Block) listOfCollidable.get(i)).setHitInBlock(
                                ((Block) listOfCollidable.get(i)).getHitInBlock() - 1);
                    }
                }
                listOfIntersectionAllRectangles.add(pointIntersection);
                collidableObjectList.add(listOfCollidable.get(i));
            }
        }
        if (listOfIntersectionAllRectangles.isEmpty()) {
            return null;
        }
        //find the closet point from the list
        minIndex = this.findClosestInsterctionPoint(trajectory.start(), listOfIntersectionAllRectangles);
        //create a new collisionInfo
        collisionInfo = new CollisionInfo((Point) listOfIntersectionAllRectangles.get(minIndex),
                (Collidable) collidableObjectList.get(minIndex));
        return collisionInfo;
    }
}
