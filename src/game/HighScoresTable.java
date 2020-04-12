package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * The game.HighScoresTable class.
 */
public class HighScoresTable implements Serializable {
    //Members
    private int size;
    private List<ScoreInfo> table;
    private HighScoresTable highScoresTable;

    /**
     * The constructor build game.HighScoresTable by create an empty high-scores table
     * with the specified size and a list of ScoreInfo.
     *
     * @param size a int means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.table = new ArrayList<>();
    }

    /**
     * The methods Add a high-score to the list.
     * If the list is empty add in the first place.
     *
     * @param score a scoreInfo type
     */
    public void add(ScoreInfo score) {
        if (table.size() == 0) {
            this.table.add(score);
        } else {
            for (int i = 0; i < table.size(); i++) {
                if (this.table.get(i).getScore() < score.getScore()) {
                    this.table.add(i, score);
                    return;
                }
            }
            this.table.add(table.size(), score);
        }
    }

    /**
     * @return the table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * The list is sorted such that the highest scores come first.
     *
     * @return the current high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.table;
    }

    /**
     * The method return the rank of the table.
     * @param score int
     * @return the rank
     */
    public int getRank(int score) {
        int rate = 1;
        for (int i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getScore() < score) {
                return rate;
            } else {
                rate++;
            }
        }
        return rate;
    }

    /**
     * The methods Clears the table.
     */
    public void clear() {
        for (int i = 0; i < table.size(); i++) {
            this.table.remove(i);
        }
    }

    /**
     * The methods Load table data from file.
     * Current table data is cleared.
     *
     * @param filename a file type
     * @throws IOException a exception
     */
    public void load(File filename) throws IOException {
        this.highScoresTable = loadFromFile(filename);
        this.table = this.highScoresTable.getHighScores();
        this.size = this.highScoresTable.size;
    }

    /**
     * The methods Save table data to the specified file.
     *
     * @param filename a file type
     * @throws IOException a exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * The methods Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename a file type
     * @return if throw a exception return null, otherwise return highScoresTable.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable highScoresTable = (HighScoresTable) objectInputStream.readObject();
            return highScoresTable;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return null;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            return null;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return null;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
}
