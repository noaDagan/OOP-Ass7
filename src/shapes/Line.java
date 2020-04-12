package shapes;

import biuoop.DrawSurface;
import game.Point;

import java.awt.Color;
import java.util.List;

/**
 * /**
 * The line class make a line between two point start and end.
 * also check if the line have intersection point,if the line are equals and calculate the middle point
 */
public class Line implements Shape {
    //Members
    private Point start;
    private Point end;
    private Color color;

    /**
     * constructors build the start and end point of the line.
     *
     * @param x1 double x Value first point
     * @param y1 double y Value first point
     * @param x2 double x Value second point
     * @param y2 double y value second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        // The function check which point in the start or end
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.end = new Point(x1, y1);
            this.start = new Point(x2, y2);
        }
    }

    /**
     * @param start the first value point of the line
     * @param end   the last value point of the line
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * @return The function return the distance of the line between start and end point
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * The function cslculate the middle point between start and end.
     *
     * @return The middle point of the line
     */
    public Point middle() {
        // Members
        double middleX;
        double middleY;
        Point middlePoint;
        // Calculate the middle point with x and y
        middleX = (start.getX() + end.getX()) / 2;
        middleY = (start.getY() + end.getY()) / 2;
        // make a new middle point
        middlePoint = new Point(middleX, middleY);
        return middlePoint;
    }

    /**
     * point start.
     *
     * @return Returns the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * point end.
     *
     * @return Returns the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function check the intersection point with other line.
     *
     * @param other parameter type line
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // Check if the intersectionWith function find a intersect point
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * The function check if the point in the range of the others.
     *
     * @param a the value of the point
     * @param b the value of the point
     * @param c the value of the point that we check
     * @return true if c is between a and b , else return false
     */
    public boolean isBetween(double a, double b, double c) {
        double epsilon = 0.01;
        if (c >= a && c <= b) {
            return true;
        } else if (c >= b && c <= a) {
            return true;
        } //check if two point is equals
        if (a == b) {
            if (Math.abs(a - c) <= epsilon) {
                return true;
            }
        }
        return false;
    }

    /**
     * The function find the intersection point between the lines.
     *
     * @param other line parameter
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        // Members
        double b1 = 0;
        double b2 = 0;
        double m1 = 0;
        double m2 = 0;
        double intersectX = 0;
        double intersectY = 0;
        Point intersect;
        // Calculate the function by the point - y = mx + b
        // Calculate the lncline and a point in the function.
        // check that x in the lines different
        if (other.end.getX() - other.start.getX() != 0) {
            m1 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
            b1 = other.start.getY() - m1 * other.start.getX();
        }
        if (this.end.getX() - this.start.getX() != 0) {
            m2 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            b2 = this.start.getY() - m2 * this.start.getX();
        }
        //The line equals or parallel
        if ((other.end.getX() - other.start.getX() != 0) && (this.end.getX() - this.start.getX() != 0)) {
            if (m1 == m2) {
                return null;
            }
            // calculate the intersect point
            intersectX = (b2 - b1) / (m1 - m2);
            intersectY = (m1 * intersectX) + b1;
        } else {
            if ((other.end.getX() - other.start.getX() == 0) && (this.end.getX() - this.start.getX() == 0)) {
                if (other.start.getX() == this.start.getX()) {
                    intersectX = this.start.getX();
                } else {
                    return null;
                }
            }
            if ((other.end.getX() - other.start.getX() == 0) && (this.end.getX() - this.start.getX() != 0)) {
                intersectX = other.end.getX();
                intersectY = (m2 * intersectX) + b2;
            }
            if ((this.end.getX() - this.start.getX() == 0) && (other.end.getX() - other.start.getX() != 0)) {
                intersectX = this.end.getX();
                intersectY = (m1 * intersectX) + b1;
            }
        }
        // check if the point in the range
        if ((isBetween(other.start.getY(), other.end.getY(), intersectY)
                && isBetween(this.start.getY(), this.end.getY(), intersectY))
                && (isBetween(other.start.getX(), other.end.getX(), intersectX)
                && isBetween(this.start.getX(), this.end.getX(), intersectX))) {
            // round the intersection point x and y
            intersectX = Math.round(intersectX);
            intersectY = Math.round(intersectY);
            intersect = new Point(intersectX, intersectY);
            return intersect;
        }
        //no intersection point
        return null;
    }

    /**
     * The function check if the line are equals.
     *
     * @param other parameter line
     * @return True is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * The function check the closest intersection point to the start of this line.
     *
     * @param rect a shapes.Rectangle to check the closest intersection point
     * @return null line does not intersect with the rectangle
     * Otherwise, return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List listOfPoints;
        //make new closestIntersection point
        Point closestIntersection = new Point(0, 0);
        Point startPoint;
        Point intersectionPoint;
        double minDistance;
        double distance;
        // make list of intersection point by rect
        listOfPoints = rect.intersectionPoints(this);
        //check if have intersection point
        if (listOfPoints.isEmpty()) {
            return null;
        } else {
            startPoint = new Point(this.start.getX(), this.start.getY());
            intersectionPoint = (Point) listOfPoints.get(0);
            closestIntersection = intersectionPoint;
            minDistance = startPoint.distance(intersectionPoint);
            // the loop run over all the list and check the smallest distance between the point and the rectangle
            //calculate the distance between the start point to intersection point by the distance function
            for (int i = 1; i < listOfPoints.size(); i++) {
                intersectionPoint = (Point) listOfPoints.get(i);
                distance = startPoint.distance(intersectionPoint);
                //check the smallest distance
                if (distance < minDistance) {
                    closestIntersection = (Point) listOfPoints.get(i);
                    minDistance = distance;
                }
            }
            return closestIntersection;
        }
    }

    /**
     * The drawOn methods create a line shape.
     * @param d a DrawSurface type
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    @Override
    public void addToListOfShapes(List<Shape> listOfShapes) {
        listOfShapes.add(this);
    }

    /**
     * The setColor fot line methods.
     * @param colorLine a color type
     */
    public void setColor(Color colorLine) {
        this.color = colorLine;
    }
}
