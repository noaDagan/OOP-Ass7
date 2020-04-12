package shapes;

import biuoop.DrawSurface;
import game.Point;

import java.awt.Color;
import java.util.List;

/**
 * The circle a implements of shape.
 */
public class Circle implements Shape {
    //Member
    private Point point;
    private int radius;
    private Color color;

    /**
     * The circle shape constructor build by a point and radius.
     * @param point the center point of the circle
     * @param radius the radius of circle
     */
    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    /**
     * The drawOn function.
     * @param drawSurface a DrawSurface type
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.color);
        drawSurface.drawCircle((int) this.point.getX(), (int) this.point.getY(), this.radius);
    }

    @Override
    /**
     * The function addToListOfShapes add a circle to the list of shapes.
     */
    public void addToListOfShapes(List<Shape> listOfShapes) {
        listOfShapes.add(this);
    }

    /**
     * The set Color.
     * @param colorForCircle the color for the circle
     */
    public void setColor(Color colorForCircle) {
        this.color = colorForCircle;
    }
}
