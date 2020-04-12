package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * The animation.AnimationRunner function.
 */
public class AnimationRunner {
    //Members
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * The animation.AnimationRunner constructor build buy a gui and create new sleeper.
     *
     * @param gui a Gui type
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 100;
        this.sleeper = new biuoop.Sleeper();
        this.gui = gui;
    }

    /**
     * The run function play the animation on the gui.
     *
     * @param animation a animation.animation to play
     */
    public void run(Animation animation) {
        double dt = 1.0 / this.framesPerSecond;
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, dt);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

