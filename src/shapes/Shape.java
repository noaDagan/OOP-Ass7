package shapes;

import biuoop.DrawSurface;

import java.util.List;

/**
 * The interface of geometry shapes.
 */
public interface Shape {

    /**
     * The drawOn methods create the shape.
     * @param d a DrawSurface type
     */
    void drawOn(DrawSurface d);

    /**
     * The methods add the shape to a list.
     * @param listOfShapes a list of the shapes.
     */
    void addToListOfShapes(List<Shape> listOfShapes);


}
