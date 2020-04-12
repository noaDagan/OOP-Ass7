package invaders;

import biuoop.DrawSurface;
import game.Sprite;
import game.GameLevel;
import game.GameEnvironment;
import game.Point;
import game.Ball;
import game.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AlienGroup method.
 */
public class AlienGroup implements Sprite {
    //Members
    private List<AlienColl> liseOfAlienGroup = new ArrayList<>();
    private double speedAlien;
    private GameLevel game;
    private double timeForShoot;
    private GameEnvironment environments;
    private static final int RIGHT = 790;
    private static final int LEFT = 10;
    private static final int WIDTH_ALIEN = 40;


    /**
     * AlienGroup build by double, GameEnvironment and GameLevel.
     *
     * @param speedAlien   a double
     * @param environments a GameEnvironment
     * @param g            a GameLevel
     */
    public AlienGroup(double speedAlien, GameEnvironment environments, GameLevel g) {
        this.liseOfAlienGroup = liseOfAlienGroup;
        this.speedAlien = speedAlien;
        this.environments = environments;
        this.game = g;
        this.timeForShoot = 0.5;
        this.speedAlien = 100;
    }

    /**
     * getAlienGroup method.
     *
     * @return list of AlienColl
     */
    public List<AlienColl> getAlienGroup() {
        return liseOfAlienGroup;
    }

    /**
     * The moveAlienX method.
     *
     * @param dt type double
     */
    public void moveAlienX(double dt) {
        for (int i = 0; i < this.liseOfAlienGroup.size(); i++) {
            for (int j = 0; j < this.liseOfAlienGroup.get(i).getAlienCol().size(); j++) {
                double changeX =
                        this.liseOfAlienGroup.get(i).getAlienCol().get(j).getCollisionRectangle().getUpperLeft().getX();
                this.liseOfAlienGroup.get(i).getAlienCol().get(j).getCollisionRectangle().getUpperLeft().setX(
                        changeX + this.speedAlien * dt);
            }
        }
    }

    /**
     * The moveAlienY method.
     *
     * @param dt type double
     */
    public void moveAlienY(double dt) {
        double speedByY = this.speedAlien * 10;
        for (int i = 0; i < this.liseOfAlienGroup.size(); i++) {
            for (int j = 0; j < this.liseOfAlienGroup.get(i).getAlienCol().size(); j++) {
                if (this.liseOfAlienGroup.get(i).getAlienCol().get(j).getCollisionRectangle()
                        .getUpperLeft().getY() > 0) {
                    double changeY =
                            this.liseOfAlienGroup.get(i).getAlienCol().get(j).getCollisionRectangle()
                                    .getUpperLeft().getY();
                    if (speedByY < 0) {
                        speedByY = speedByY * -1;
                    }
                    this.liseOfAlienGroup.get(i).getAlienCol().get(j).getCollisionRectangle().getUpperLeft().setY(
                            changeY + speedByY * dt);
                }
            }
        }
        this.speedAlien = this.speedAlien * 1.1;
    }

    /**
     * The setAllGroup return all the aliens for her first point.
     */
    public void setAllGroup() {
        int startX = 120;
        int startY = 100;
        for (int t = 0; t < this.liseOfAlienGroup.size(); t++) {
            for (int r = 0; r < this.liseOfAlienGroup.get(t).getAlienCol().size(); r++) {
                int x = startX + t * 50;
                int y = startY + 50 * r;
                if (this.liseOfAlienGroup.get(t).getAlienCol().get(r) instanceof AlienClass) {
                    this.liseOfAlienGroup.get(t).getAlienCol().get(r).getCollisionRectangle().getUpperLeft().setX(x);
                    this.liseOfAlienGroup.get(t).getAlienCol().get(r).getCollisionRectangle().getUpperLeft().setY(y);
                } else {
                    x = startX + t * 50;
                }
            }
        }
    }

    /**
     * The addAlienToGroup method, add a alienColl the group.
     *
     * @param alienColl a AlienColl type
     */
    public void addAlienToGroup(AlienColl alienColl) {
        this.liseOfAlienGroup.add(alienColl);
    }

    @Override
    /**
     * drawOn method.
     */
    public void drawOn(DrawSurface d) {
    }

    @Override
    /**
     * timePassed method.
     */
    public void timePassed(double dt) {
        boolean is = true;
        for (int i = 0; i < this.liseOfAlienGroup.size(); i++) {
            for (int j = 0; j < this.liseOfAlienGroup.get(i).getAlienCol().size(); j++) {
                double x =
                        this.liseOfAlienGroup.get(i).getAlienCol().get(j).getCollisionRectangle().getUpperLeft().getX();
                if ((x <= LEFT) || (x + WIDTH_ALIEN) >= RIGHT) {
                    this.speedAlien = (this.speedAlien * -1);
                    moveAlienY(dt);
                    is = false;
                    break;
                }
            }
            if (!is) {
                break;
            }
        }
        this.moveAlienX(dt);
        if (timeForShoot < 0) {
            this.shoot();
            timeForShoot = 0.5;
        } else {
            timeForShoot = timeForShoot - dt;
        }
    }

    /**
     * addToGame method.
     * add the group as a sprite
     *
     * @param gameL GameLevel type
     */
    public void addToGame(GameLevel gameL) {
        gameL.addSprite(this);
    }

    @Override
    /**
     * The removeFromGame method.
     */
    public void removeFromGame(GameLevel g) {

    }

    /**
     * The removeAlien method delete a specify alienClass from the group.
     *
     * @param g          a GameLevel type.
     * @param alienClass a AlienClass type to delete.
     */
    public void removeAlien(GameLevel g, AlienClass alienClass) {
        for (int i = 0; i < this.liseOfAlienGroup.size(); i++) {
            for (int j = 0; j < this.liseOfAlienGroup.get(i).getAlienCol().size(); j++) {
                if (this.liseOfAlienGroup.get(j).getAlienCol().contains(alienClass)) {
                    g.removeSprite(alienClass);
                    g.removeCollidable(alienClass);
                    this.liseOfAlienGroup.get(j).getAlienCol().remove(alienClass);
                    if (this.getAlienGroup().get(j).getAlienCol().size() == 0) {
                        this.liseOfAlienGroup.remove(this.getAlienGroup().get(j));
                    }
                    break;
                }
            }
        }
    }


    /**
     * The shoot method create the shoot of aliens in a random point.
     */
    public void shoot() {
        Random r = new Random();
        double x = this.liseOfAlienGroup.get(r.nextInt(this.liseOfAlienGroup.size())).getXCol();
        double y = this.liseOfAlienGroup.get(0).getYCol() + 250;
        int radius = 3;
        Color color = Color.RED;
        Ball ball = new Ball(new Point(x, y), radius, color);
        ball.setVelocity(Velocity.fromAngleAndSpeed(180, 95));
        ball.setGameEnvironment(this.environments);
        ball.addToGame(this.game);
    }

    /**
     * setSpeedAlien method.
     *
     * @param speed a double type
     */

    public void setSpeedAlien(double speed) {
        this.speedAlien = speed;
    }

    /**
     * getUnderPoint method.
     *
     * @return the under point of the group
     */
    public double getUnderPoint() {
        double underPoint = this.liseOfAlienGroup.get(0).getYCol() + 300;
        return underPoint;
    }
}