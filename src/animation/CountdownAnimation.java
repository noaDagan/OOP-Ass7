package animation;

import biuoop.DrawSurface;
import game.SpriteCollection;

/**
 * The animation.CountdownAnimation class implement animation.animation.
 */
public class CountdownAnimation implements Animation {
    private boolean stop;
    private int countFrom;
    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private long startTime;
    private double numOfMiliSeconds;


    /**
     * The animation.CountdownAnimation class build by numOfSeconds,countFrom and gameScreen.
     *
     * @param numOfSeconds a double type
     * @param countFrom    a int type
     * @param gameScreen   a game.SpriteCollection type
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = true;
        this.startTime = System.currentTimeMillis();
        this.numOfMiliSeconds = this.numOfSeconds * 1000;
    }

    /**
     * The doOneFrame function.
     * @param dt a double type
     * @param d a DrawSurface type
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int currentCount = this.countFrom;
        this.gameScreen.drawAllOn(d);
        if ((System.currentTimeMillis() - startTime >= (numOfMiliSeconds / countFrom))
                && (System.currentTimeMillis() - startTime < (2 * numOfMiliSeconds / countFrom))) {
            currentCount = currentCount - 1;
        }
        if ((System.currentTimeMillis() - startTime >= (2 * numOfMiliSeconds / countFrom))
                && (System.currentTimeMillis() - startTime < (3 * numOfMiliSeconds / countFrom))) {
            currentCount = currentCount - 2;
        }
        if (System.currentTimeMillis() - startTime >= numOfMiliSeconds) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 7, "Go", 50);
            this.stop = false;
            return;
        }
        d.drawText(d.getWidth() / 2, d.getHeight() / 7, Integer.toString(currentCount) + "...", 50);
    }

    /**
     * @return false if need stop
     */
    public boolean shouldStop() {
        return !this.stop;
    }
}