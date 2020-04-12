package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The game.EndScreen class implements animation.animation.
 */
public class EndScreen implements Animation {
    private int scoreCounter;
    private KeyboardSensor keyboardSensor;
    private boolean stop;
    private String messageIs;

    /**
     * The end screen constructor build by a scoreCounter,  messageIs and keyboardSensor.
     *
     * @param scoreCounter   the value of score
     * @param messageIs      the message of winner or lose
     * @param keyboardSensor a KeyboardSensor type
     */
    public EndScreen(int scoreCounter, String messageIs, KeyboardSensor keyboardSensor) {
        this.scoreCounter = scoreCounter;
        this.messageIs = messageIs;
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
    }

    /**
     * The methods create the end screen and print a message if lose or win.
     * @param dt a double type
     * @param d a DrawSurface type
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        d.drawText(10, (d.getWidth() / 2) - 300, messageIs, 32);
        d.setColor(Color.BLUE);
        String messageScore = " your score is : ";
        String sumOfScore = Integer.toString(this.scoreCounter);
        d.drawText(10, (d.getWidth() / 2) - 200, messageScore + sumOfScore, 32);
        String msgContinue = "press space to continue";
        d.drawText(10, (d.getWidth() / 2) - 100, msgContinue, 32);
    }

    /**
     * @return fales if not need to stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
