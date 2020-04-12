package animation;

import biuoop.DrawSurface;

/**
 * The animation interface.
 */
public interface Animation {
    /**
     * The function do one frame.
     * @param d a DrawSurface type
     * @param dt a double type
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * The boolean shouldStop function.
     * @return true to continue, and false if stop
     */
    boolean shouldStop();
}