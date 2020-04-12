package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The pause screen class.
 */
public class PauseScreen implements Animation {
    //Members
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * The constructor build PauseScreen by a KeyboardSensor.
     *
     * @param k a KeyboardSensor type
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * The do one frame methods crate the message on the screen.
     * if pressed space continue
     * @param dt a double type
     * @param d a DrawSurface type
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * The shouldStop boolean methods.
     *
     * @return false if need stop the loop, otherwise return true
     */
    public boolean shouldStop() {
        return this.stop;
    }
}