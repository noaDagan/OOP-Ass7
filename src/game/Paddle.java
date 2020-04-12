package game;

import biuoop.DrawSurface;
import hitevent.HitListener;
import hitevent.HitNotifier;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class paddle.
 */
public class Paddle implements Sprite, Collidable, HitNotifier {
    //Members
    private biuoop.KeyboardSensor keyboard;
    private Block blockPaddle;
    private Velocity velocity;
    private int paddleWidth;
    private int paddleSpeed;
    private static final int NUM_OF_PADDLE_REGIONS = 5;
    private List<HitListener> listeners;
    private GameLevel g;
    private GameEnvironment environment;
    private double speed;
    private boolean isPaddle;

    /**
     * Constructor the paddle with a block ,KeyboardSensor and velocity.
     *
     * @param blockShape  the block of paddle
     * @param keyboard    that help move the paddle from right to left
     * @param paddleWidth The width of the paddle in the level
     * @param paddleSpeed The speed of the paddle in the level
     * @param g           a GameLevel
     * @param e           a GameEnvironment
     */
    public Paddle(Block blockShape, biuoop.KeyboardSensor keyboard,
                  int paddleWidth, int paddleSpeed, GameLevel g, GameEnvironment e) {
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
        //initialize the velocity
        this.velocity = new Velocity(6, 6);
        this.velocity.fromAngleAndSpeed(this.velocity.getDx(), this.paddleSpeed);
        this.blockPaddle = blockShape;
        this.paddleWidth = paddleWidth;
        this.g = g;
        this.environment = e;
        this.speed = 0.35;
        this.isPaddle = true;
        this.listeners = new ArrayList<>();
    }

    /**
     * The function move the paddle to left in the screen.
     *
     * @param dt a double
     */
    public void moveLeft(double dt) {
        int newSpeed = (int) ((double) this.paddleSpeed * dt);
        if (this.getCollisionRectangle().getUpperLeft().getX() - newSpeed <= 10) {
            this.getCollisionRectangle().getUpperLeft().setX(
                    this.getCollisionRectangle().getUpperLeft().getX());
        } else {
            this.getCollisionRectangle().getUpperLeft().setX(
                    this.getCollisionRectangle().getUpperLeft().getX() - newSpeed);
        }
    }

    /**
     * The function move the paddle to right in the screen.
     *
     * @param dt a double
     */
    public void moveRight(double dt) {
        int newSpeed = (int) ((double) this.paddleSpeed * dt);
        if (this.getCollisionRectangle().getUpperLeft().getX() + this.paddleWidth + newSpeed >= 790) {
            this.getCollisionRectangle().getUpperLeft().setX(
                    this.getCollisionRectangle().getUpperLeft().getX());
        } else {
            this.getCollisionRectangle().getUpperLeft().setX(
                    this.getCollisionRectangle().getUpperLeft().getX() + newSpeed);
        }
    }

    /**
     * The gun method call to createBall method in the game level and create the gum of the paddle by crate new ball.
     *
     * @param dt a double
     */
    public void gun(double dt) {
        this.g.createBall();

    }

    /**
     * The function check what the user push in the keyboard.
     * if the user push left key - moveLeft function ,otherwise the user push right key - moveRight function
     *
     * @param dt double
     */
    public void timePassed(double dt) {
        //Check if choose left or right
        if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            moveLeft(dt);
        } else if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            moveRight(dt);
        } else if (keyboard.isPressed(this.keyboard.SPACE_KEY)) {
            if (this.speed < 0) {
                gun(dt);
                this.speed = 0.35;
            } else {
                this.speed = this.speed - dt;
            }
        }
    }

    /**
     * The function draw the paddle on the screen.
     *
     * @param d a DrawSurface type
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) this.blockPaddle.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.blockPaddle.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.blockPaddle.getCollisionRectangle().getWidth(),
                (int) this.blockPaddle.getCollisionRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.blockPaddle.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.blockPaddle.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.blockPaddle.getCollisionRectangle().getWidth(),
                (int) this.blockPaddle.getCollisionRectangle().getHeight());
    }

    /**
     * The getCollisionRectangle methods.
     *
     * @return the the collision rectangle of paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.blockPaddle.getCollisionRectangle();
    }

    /**
     * The function change the velocity of the ball after he collision in the paddle.
     * the ball define by 5 region that if the ball bounce in the point in trthe region,change in angle according
     *
     * @param ball            a ball
     * @param collisionPoint  the intersect point
     * @param currentVelocity the velocity before the intersect
     * @return the new velocity after the collision
     */
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        //define a 5 region for paddle
        double upperLeft = this.blockPaddle.getCollisionRectangle().getUpperLeft().getX();
        double endRegion1 = upperLeft + (this.getCollisionRectangle().getWidth() / NUM_OF_PADDLE_REGIONS);
        double endRegion2 = upperLeft + (2 * (this.getCollisionRectangle().getWidth() / NUM_OF_PADDLE_REGIONS));
        double endRegion3 = upperLeft + (3 * (this.getCollisionRectangle().getWidth() / NUM_OF_PADDLE_REGIONS));
        double endRegion4 = upperLeft + (4 * (this.getCollisionRectangle().getWidth() / NUM_OF_PADDLE_REGIONS));
        double endRegion5 = upperLeft + this.getCollisionRectangle().getWidth();
        //initialize the new velocity,dx.dy and speed
        Velocity newVelocity = currentVelocity;
        double velocityDx = currentVelocity.getDx();
        double velocityDy = currentVelocity.getDy();
        double ourSpeed = Math.sqrt((velocityDx * velocityDx) + (velocityDy * velocityDy));
        if ((upperLeft <= collisionPoint.getX()) && (collisionPoint.getX() < endRegion1)) { // check the region
            newVelocity = currentVelocity.fromAngleAndSpeed(330, ourSpeed);
        } else if ((endRegion1 <= collisionPoint.getX()) && (collisionPoint.getX() < endRegion2)) {
            newVelocity = currentVelocity.fromAngleAndSpeed(300, ourSpeed);
        } else if ((endRegion2 <= collisionPoint.getX()) && (collisionPoint.getX() < endRegion3)) {
            newVelocity.setDy(-currentVelocity.getDy());
        } else if ((endRegion3 <= collisionPoint.getX()) && (collisionPoint.getX() < endRegion4)) {
            newVelocity = currentVelocity.fromAngleAndSpeed(60, ourSpeed);
        } else if ((endRegion4 <= collisionPoint.getX()) && (collisionPoint.getX() <= endRegion5)) {
            newVelocity = currentVelocity.fromAngleAndSpeed(30, ourSpeed);
        }
        // return the new velocity
        return newVelocity;
    }

    /**
     * The function Add this paddle to the game.
     *
     * @param gl type of game
     */
    public void addToGame(GameLevel gl) {
        gl.addSprite(this);
        gl.addCollidable(this.blockPaddle);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.listeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.listeners.remove(hl);
    }

    /**
     * removeFromGame method.
     *
     * @param gameL a game level
     */
    public void removeFromGame(GameLevel gameL) {
        gameL.removeCollidable(this);
        gameL.removeSprite(this);
    }


}
