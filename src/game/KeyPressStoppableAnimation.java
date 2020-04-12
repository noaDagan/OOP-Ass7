package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    //Members
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * The KeyPressStoppableAnimation methods.
     * @param sensor a KeyboardSensor type
     * @param key a string type
     * @param animation a Animation type
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    /**
     * The doOneFrame method.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
                isAlreadyPressed = false;
        }
    }

    @Override
    /**
     * The shouldStop method.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
