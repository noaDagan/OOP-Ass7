package game;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import hitevent.Counter;
import invaders.InvadersLevel1;
import levels.LevelInformation;
import levels.LevelSetReader;
import manu.Menu;
import manu.MenuAnimation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The gameFlow class.
 */
public class GameFlow implements Task {
    private KeyboardSensor keyS;
    private AnimationRunner animationRunner;
    private int numberOfLives;
    private int sumOfScore;
    private String messageIs;
    private EndScreen endScreen;
    private int sizeOfTable;
    private HighScoresAnimation scoresScreen;
    private HighScoresTable scoresTable;
    private GUI gui;
    private File fileScores;
    private GameLevel gameLevel;
    private double speedAlien;
    private double initSpeedAlien;
    private Counter countOfLives;
    private int levelNumber;
    private InvadersLevel1 invadersLevel1;

    /**
     * The constructor build by a AnimationRunner,KeyboardSensor, numberOfLives and width and height screen.
     *
     * @param gui           a Gui type
     * @param runner        a AnimationRunner type
     * @param keyS          a KeyboardSensor type
     * @param numberOfLives a int type.
     * @param speedAlien    a int type.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyS, int numberOfLives, GUI gui, double speedAlien) {
        this.numberOfLives = numberOfLives;
        this.keyS = keyS;
        this.animationRunner = runner;
        this.sumOfScore = 0;
        this.sizeOfTable = 10;
        this.gui = gui;
        this.scoresTable = new HighScoresTable(sizeOfTable);
        this.gameLevel = gameLevel;
        this.initSpeedAlien = 100;
        this.speedAlien = speedAlien;
        this.levelNumber = 1;
        fileScores = new File("filescore");

    }

    /**
     * The runLevels method run the level.
     * if the counter of live is 0 , end the game.
     *
     * @param levels the first level
     * @throws IOException if throw exception
     */
    public void runLevels(LevelInformation levels) throws IOException {
        Counter counterOfScore = new Counter(sumOfScore);
        if (fileScores.exists()) {
            this.scoresTable.load(fileScores);
        } else {
            this.scoresTable.save(fileScores);
        }
        gameLevel = new GameLevel(
                levels, this.keyS, this.animationRunner, countOfLives, counterOfScore, this.speedAlien);
        gameLevel.initialize();
        while (countOfLives.getValue() >= 0) {
            gameLevel.playOneTurn();
            if (!gameLevel.isWinner()) {
                this.countOfLives.decrease(1);
            }
            if (gameLevel.getCounterAlien().getValue() == 0) {
                gameLevel.playOneTurn();
                this.levelNumber = this.levelNumber + 1;
                levels.setNumOfLevel(this.levelNumber);
                invadersLevel1 = new InvadersLevel1(gameLevel, this.speedAlien);
                invadersLevel1.setNumOfLevel(this.levelNumber);
                this.speedAlien = speedAlien * 1.1;
                gameLevel = new GameLevel(
                        invadersLevel1, this.keyS, this.animationRunner, countOfLives, counterOfScore, this.speedAlien);
                gameLevel.initialize();
                System.out.println(this.levelNumber);
                this.levelNumber = this.levelNumber + 1;
                invadersLevel1.setNumOfLevel(this.levelNumber);
                gameLevel.playOneTurn();
            }
        }
        if (countOfLives.getValue() == 0) {
            messageIs = "Game Over.";
            // create the EndScreen with a specific message
            scoresScreen = new HighScoresAnimation(this.scoresTable);
            Animation scoresScreenAni = new KeyPressStoppableAnimation(this.keyS, this.keyS.SPACE_KEY, scoresScreen);
            endScreen = new EndScreen(counterOfScore.getValue(), messageIs, this.keyS);
            Animation endScreenAni = new KeyPressStoppableAnimation(this.keyS, this.keyS.SPACE_KEY, endScreen);
            DialogManager dialog = gui.getDialogManager();
            String newName = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreInfo = new ScoreInfo(newName, counterOfScore.getValue());
            if (this.scoresTable.getRank(scoreInfo.getScore()) <= this.sizeOfTable) {
                this.scoresTable.add(scoreInfo);
            }
            this.animationRunner.run(endScreenAni);
            this.animationRunner.run(scoresScreenAni);
        }
    }


    /**
     * The method runTheMenu create the menu.
     *
     * @param levelSetReader a levelSetReader
     * @throws IOException if something not wrong
     */
    public void runTheMenu(LevelSetReader levelSetReader) throws IOException {
        countOfLives = new Counter(numberOfLives);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(this.keyS, "game : ", false, this.animationRunner);
        menu.addSelection("s", "start game", new Task<Void>() {
            @Override
            public Void run() {
                try {
                    List<LevelInformation> level = new ArrayList<>();
                    invadersLevel1 = new InvadersLevel1(gameLevel, speedAlien);
                    invadersLevel1.setNumOfLevel(levelNumber);
                    level.add(invadersLevel1);
                    runLevels(invadersLevel1);

                } catch (IOException e) {
                    System.out.println(e);
                }
                return null;
            }
        });
        menu.addSelection("h", "High Scores", new Task<Void>() {
            @Override
            public Void run() {
                animationRunner.run(new KeyPressStoppableAnimation(keyS, keyS.SPACE_KEY,
                        new HighScoresAnimation(scoresTable)));
                return null;
            }
        });
        menu.addSelection("e", "Exit", new Task<Void>() {
            @Override
            public Void run() {
                gui.close();
                return null;
            }
        });

        while (true) {
            Task<Void> task = null;
            this.speedAlien = speedAlien + 100;
            this.animationRunner.run(menu);
            task = menu.getStatus();
            task.run();
            (menu).setStop(false);
        }
    }

    @Override
    public Object run() {
        return null;
    }
}