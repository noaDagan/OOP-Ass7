package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import hitevent.Counter;
import hitevent.PaddleRemover;
import hitevent.BlockRemover;
import hitevent.BallRemover;
import hitevent.NameIndicator;
import hitevent.ScoreTrackingListener;
import hitevent.ScoreIndicator;
import hitevent.LivesIndicator;
import invaders.AlienClass;
import invaders.AlienColl;
import invaders.AlienGroup;
import levels.LevelInformation;
import shapes.Rectangle;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * The class game.
 */
public class GameLevel implements Animation {
    //Members
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment;
    private Ball ball;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScores;
    private Counter countOfLives;
    private Counter counterForPaddle;
    private Counter counterAlien;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private PaddleRemover paddleRemover;
    private Paddle paddle;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private NameIndicator nameIndicator;
    private AlienGroup a;
    private AlienColl alienColl;
    private Block blocKlPaddle;
    private boolean isFirstLevel;
    // a final parameter
    private static final int PADDLE_X = 350;
    private static final int PADDLE_Y = 565;
    private double speedAlien;
    private double initSpeedAlien;
    private boolean isWin;

    /**
     * The constructor game.
     *
     * @param levelInformation     the level of the game
     * @param ks                   a keyboard
     * @param ar                   a animationRunner
     * @param countOfLivesReamain  counter live
     * @param counterOfScoreRemain counter block
     * @param speedAlien           double speed
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks,
                     AnimationRunner ar, Counter countOfLivesReamain, Counter counterOfScoreRemain, double speedAlien) {
        this.levelInformation = levelInformation;
        this.keyboard = ks;
        this.runner = ar;
        this.countOfLives = countOfLivesReamain;
        this.counterScores = counterOfScoreRemain;
        this.speedAlien = speedAlien;
        this.initSpeedAlien = 100;
        isWin = true;
    }

    /**
     * The method add collidable to the list of collidables.
     *
     * @param c collidable that add to list
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The method add sprite to the list of sprites.
     *
     * @param s sprite that add to the list
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The method initialize a new game.
     * the method create new game.Block, game.Ball, game.Paddle and add them to the game
     */
    public void initialize() {
        this.addSprite(this.levelInformation.getBackground());
        int x = 0;
        int y = 0;
        int sumOfPaddle = 1;
        Point upperRectanglePoint = new Point(x, y);
        environment = new GameEnvironment();
        this.counterForPaddle = new Counter(sumOfPaddle);
        this.counterBlocks = new Counter(this.levelInformation.createAlien(20).size());
        this.counterBalls = new Counter(this.levelInformation.numberOfBalls());
        this.counterAlien = new Counter(50);
        BallRemover ballRemover = new BallRemover(this, counterBalls);
        Rectangle rectangleScore = new Rectangle(upperRectanglePoint, 800, 20);
        Block blockScore = new Block(rectangleScore, Color.GRAY);
        blockScore.addHitListener(ballRemover);
        blockScore.addToGame(this);
        this.nameIndicator = new NameIndicator(this.levelInformation.levelName());
        this.addSprite(nameIndicator);
        this.scoreIndicator = new ScoreIndicator(counterScores);
        this.addSprite(scoreIndicator);
        this.livesIndicator = new LivesIndicator(countOfLives);
        this.addSprite(livesIndicator);
        this.createBlockDeathRegion(ballRemover);
        this.crateBlocksFrame();
        this.createBlocks();
        this.createPaddle();
        a = this.createAlienGroup();
        a.addToGame(this);
    }


    /**
     * The function crate the block remover.
     *
     * @param ballRemover a ball remover type
     */
    public void createBlockDeathRegion(BallRemover ballRemover) {
        Point upperRectanglePoint = new Point(10, 590);
        Rectangle rectangleDeathRegion = new Rectangle(upperRectanglePoint, 800, 10);
        Block blockDeathRegion = new Block(rectangleDeathRegion, Color.GRAY);
        blockDeathRegion.addToGame(this);
        blockDeathRegion.addHitListener(ballRemover);
    }


    /**
     * The function build paddle.
     */
    public void createPaddle() {
        Point upperRectanglePoint;
        int widthOfPaddle = this.levelInformation.paddleWidth();
        int paddleSpeed = this.levelInformation.paddleSpeed();
        upperRectanglePoint = new Point(100, PADDLE_Y);
        Rectangle rectanglePaddle = new Rectangle(upperRectanglePoint, widthOfPaddle, 25); // create the paddle
        blocKlPaddle = new Block(rectanglePaddle, Color.YELLOW);
        paddle = new Paddle(blocKlPaddle, this.keyboard, widthOfPaddle, paddleSpeed, this, environment);
        paddleRemover = new PaddleRemover(this, this.counterForPaddle);
        blocKlPaddle.addHitListener(paddleRemover);
        paddle.addToGame(this);
    }

    /**
     * The createAliens method create a AlienColl with a five liens.
     *
     * @param index a index of the coll
     * @return a new AlienColl
     */
    public AlienColl createAliens(int index) {
        int numOfAlien = this.levelInformation.createAlien(index).size();
        List<AlienClass> listOfBlock = this.levelInformation.createAlien(index);
        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(counterScores);
        BallRemover ballRemover = new BallRemover(this, counterBlocks);
        for (int i = 0; i < numOfAlien; i++) {
            listOfBlock.get(i).addHitListener(ballRemover);
            listOfBlock.get(i).addHitListener(blockRemover);
            listOfBlock.get(i).addHitListener(scoreTrackingListener);
            listOfBlock.get(i).addToGame(this);
        }
        alienColl = new AlienColl(listOfBlock);
        alienColl.addToGame(this);
        return alienColl;
    }

    /**
     * The createAlienGroup method create a new AlienGroup with a 10 coll.
     *
     * @return a new AlienGroup
     */
    public AlienGroup createAlienGroup() {
        AlienGroup alienGroup = new AlienGroup(this.speedAlien, environment, this);
        for (int i = 0; i < 10; i++) {
            alienColl = createAliens(i);
            alienGroup.addAlienToGroup(alienColl);
        }
        return alienGroup;
    }


    /**
     * The method createBall create a new ball to shoot for the paddle.
     */
    public void createBall() {
        BallRemover ballRemover = new BallRemover(this, counterBalls);
        List<Velocity> balls = new ArrayList<>();
        balls = this.levelInformation.initialBallVelocities();
        counterBalls.increase(1);
        for (int i = 0; i < balls.size(); i++) {
            double x = paddle.getCollisionRectangle().getUpperLeft().getX()
                    + paddle.getCollisionRectangle().getWidth() / 2;
            double y = paddle.getCollisionRectangle().getUpperLeft().getY() - 20;
            Point point = new Point(x, y);
            int radius = 6;
            Ball b = new Ball(point, radius, Color.WHITE);
            b.setVelocity(this.levelInformation.initialBallVelocities().get(i).getDx(),
                    this.levelInformation.initialBallVelocities().get(i).getDy());
            b.addToGame(this);
            b.setGameEnvironment(environment);
        }
    }

    /**
     * The resetGame the game return the screen to the first step.
     * The method delete all the ball from the screen
     */
    public void resetGame() {
        blocKlPaddle.addHitListener(paddleRemover);
        this.counterForPaddle.increase(1);
        this.a.setSpeedAlien(this.initSpeedAlien);
        List<Sprite> ballsToRemove = new ArrayList<>();
        //copy the list of the ball to delete them
        for (Sprite sprite : this.sprites.getListOfSpriteCollection()) {
            if (sprite instanceof Ball) {
                ballsToRemove.add(sprite);
            }
        }
        for (Sprite ballToRemove : ballsToRemove) {
            ballToRemove.removeFromGame(this);
        }
        this.getA().setAllGroup();
    }

    /**
     * The method create a new balls, set the velocity and game environment.
     * The method update the hit event of num of ball in accordance
     * The method add to spirit list by addToGame function
     */
    public void createBalls() {
        // create a new 2 ball with velocity and add to the spirit list by addToGame function
        int numOfBalls = this.levelInformation.numberOfBalls();
        int radius = 4;
        int y = 540;
        int x = 390;
        for (int i = 0; i < numOfBalls; i++) {
            if (this.levelInformation.levelName().equals("Direct Hit")) {
                ball = new Ball(x, y, radius, Color.WHITE);
            } else {
                ball = new Ball(x, y, radius, Color.BLACK);
            }
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i).getDx(),
                    this.levelInformation.initialBallVelocities().get(i).getDy());
            ball.addToGame(this);
            ball.setGameEnvironment(environment);
        }
    }

    /**
     * The method create the block of the game.
     * Build the blocks by loop that run over all list of blocks from the level and create the block by them
     * add the blocks to collidable and sprite list by addToGame function and  addHitListener
     */
    public void createBlocks() {
        int numOfBlock = this.levelInformation.blocks().size();
        List<Block> listOfBlock = this.levelInformation.blocks();
        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(counterScores);
        BallRemover ballRemover = new BallRemover(this, counterBlocks);
        for (int i = 0; i < numOfBlock; i++) {
            listOfBlock.get(i).addHitListener(ballRemover);
            listOfBlock.get(i).addHitListener(blockRemover);
            listOfBlock.get(i).addHitListener(scoreTrackingListener);
            listOfBlock.get(i).addToGame(this);
        }
    }

    /**
     * The method create the blocks frame to initialize function.
     */
    public void crateBlocksFrame() {
        int x = 0;
        int y = 0;
        BallRemover ballRemover = new BallRemover(this, counterBalls);
        Point upperRectanglePoint = new Point(x, y);
        Rectangle rectangleEdge1 = new Rectangle(upperRectanglePoint, 10, 600);
        Block block1 = new Block(rectangleEdge1, Color.GRAY);
        upperRectanglePoint.setX(790);
        upperRectanglePoint.setY(0);
        Rectangle rectangleEdge3 = new Rectangle(upperRectanglePoint, 10, 600);
        Block block3 = new Block(rectangleEdge3, Color.GRAY);
        block1.addHitListener(ballRemover);
        block3.addHitListener(ballRemover);
        block1.addToGame(this);
        block3.addToGame(this);
    }

    /**
     * The function run the game and start the animation loop.
     */
    public void playOneTurn() {
        //this.createAlienGroup();
        if (isFirstLevel) {
            this.resetGame();
        }
        this.isFirstLevel = true;
        this.setPaddle();
        //    this.createBalls();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * The function remove a collidable from the list.
     *
     * @param c a game.Collidable to delete
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * The function remove a sprite from the list.
     *
     * @param s a sprite to delete
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The setPaddle function.
     */
    public void setPaddle() {
        this.paddle.getCollisionRectangle().getUpperLeft().setX(this.levelInformation.paddleWidth() / 2 + 235);
        this.paddle.getCollisionRectangle().getUpperLeft().setY(PADDLE_Y);
    }

    /**
     * The doOneFrame function.
     *
     * @param dt a double type
     * @param d  a DrawSurface type
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        d.setColor(Color.BLACK);


        if ((this.getA().getAlienGroup().size() == 0)) {
            this.running = false;
            isWin = true;
        }

        if (this.a.getUnderPoint() >= 500) {
            this.resetGame();
            this.running = false;
            isWin = false;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        if (this.counterForPaddle.getValue() == 0) {
            this.resetGame();
            this.running = false;
            isWin = false;
        }
        if (this.getCounterAlien().getValue() == 0) {
            this.running = false;
            isWin = true;

        }
    }

    /**
     * The shouldStop boolean function.
     *
     * @return if it need to stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * The get counter block.
     *
     * @return the counterBlocks
     */
    public Counter getCounterBlocks() {
        return this.counterBlocks;
    }

    /**
     * The getA method.
     *
     * @return AlienGroup
     */
    public AlienGroup getA() {
        return a;
    }

    /**
     * A bollen method return if winner or loose.
     *
     * @return true is winner, otherwise false
     */
    public boolean isWinner() {
        return this.isWin;
    }

    /**
     * The getCounterAlien method.
     *
     * @return a counter of alien
     */
    public Counter getCounterAlien() {
        return counterAlien;
    }
}
