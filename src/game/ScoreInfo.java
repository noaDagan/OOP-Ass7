package game;

import java.io.Serializable;

/**
 * The ScoreInfo method.
 */
public class ScoreInfo implements Serializable {
    //Member
    private String name;
    private int score;

    /**
     * The ScoreInfo method.
     * @param name a string
     * @param score a int
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getName method.
     * @return a string
     */
    public String getName() {
        return this.name;
    }

    /**
     * getScore method.
     * @return a int
     */
    public int getScore() {
        return this.score;
    }
}