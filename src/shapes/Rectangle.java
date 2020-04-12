package shapes;

import biuoop.DrawSurface;
import game.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * crate a new shapes.Rectangle class with specified line.
 */
public class Rectangle implements Shape {
    //Members
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;

    /**
     * The function constructor a new rectangle with location, width and height.
     *
     * @param upperLeft point
     * @param width     size of the rectangle
     * @param height    size of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * @param line specified.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List intersectionPoints(Line line) {
        List listOfPoints = new ArrayList();
        Line rectangleSide1 = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        Line rectangleSide2 = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        Line rectangleSide3 = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        Line rectangleSide4 = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        Point intersect1 = rectangleSide1.intersectionWith(line);
        if (intersect1 != null) {
            listOfPoints.add(intersect1);
        }
        Point intersect2 = rectangleSide2.intersectionWith(line);
        if (intersect2 != null) {
            listOfPoints.add(intersect2);
        }
        Point intersect3 = rectangleSide3.intersectionWith(line);
        if (intersect3 != null) {
            listOfPoints.add(intersect3);
        }
        Point intersect4 = rectangleSide4.intersectionWith(line);
        if (intersect4 != null) {
            listOfPoints.add(intersect4);
        }
        return listOfPoints;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @param d ff
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(), (int) this.getWidth(), (int) this.getHeight());
    }

    /**
     * The setColor for rectangle methods.
     *
     * @param colorRectangle a color type
     */

    public void setColor(java.awt.Color colorRectangle) {
        this.color = colorRectangle;
    }

    /**
     * The setWight method.
     * @param wightB double type
     */
    public void setWight(double wightB) {
        this.width = wightB;
    }

    /**
     * The setHeight method.
     * @param heighB double type
     */
    public void setHeight(double heighB) {
        this.height = heighB;
    }

    @Override
    /**
     * The method addToListOfShapes.
     */
    public void addToListOfShapes(List<Shape> listOfShapes) {
        listOfShapes.add(this);
    }
}
