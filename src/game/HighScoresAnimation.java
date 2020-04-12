package game;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The HighScoresAnimation class.
 */
public class HighScoresAnimation implements Animation {
    //Members
    private HighScoresTable scores;
    private KeyboardSensor k;
    private boolean stop;

    /**
     * The HighScoresAnimation method.
     * @param scores a HighScoresTable type
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
        this.k = k;
        this.stop = false;
    }

    /**
     * The do one frame.
     * @param d a DrawSurface type
     * @param dt a double type
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int y = d.getHeight() / 3;
        d.setColor(Color.BLUE);
        d.drawText(d.getWidth() / 3 - 50, 100, "Scores table : ", 40);
        d.setColor(Color.RED);
        d.drawText(180, 140, "Score: ", 40);
        d.drawText(400, 140, "Name: ", 40);
        d.setColor(Color.BLACK);
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            y = y + 35;
            d.drawText(200, y, this.scores.getHighScores().get(i).getName(), 32);
            d.drawText(400, y, Integer.toString(this.scores.getHighScores().get(i).getScore()), 32);
        }
    }

    /**
     * The boolean methods of shouldStop.
     * @return true if need to stop
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
