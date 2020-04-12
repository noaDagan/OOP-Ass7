package manu;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The MenuAnimation class.
 *
 * @param <T> a t type
 */
public class MenuAnimation<T> implements manu.Menu<T> {
    //Members
    private List selectList;
    private T status;
    private boolean stop;
    private KeyboardSensor keyS;
    private boolean isAlreadyPressed;
    private Selection selection;
    private String menu;
    private boolean isSubMenu;
    private AnimationRunner animation;
    private manu.Menu<T> isMenu;

    /**
     * The MenuAnimation build by KeyboardSensor, string, boolean and animation.
     *
     * @param keyS          a KeyboardSensor
     * @param menuString    a string
     * @param isSubMenuBool a boolean
     * @param animation     a AnimationRunner
     */
    public MenuAnimation(KeyboardSensor keyS, String menuString, boolean isSubMenuBool, AnimationRunner animation) {
        this.selectList = new ArrayList();
        this.status = null;
        this.stop = false;
        this.isAlreadyPressed = true;
        this.keyS = keyS;
        this.isSubMenu = isSubMenuBool;
        this.menu = menuString;
        this.animation = animation;
    }


    /**
     * @param key       a string type
     * @param message   a string message
     * @param returnVal a t tyep.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.selection = new Selection(key, message, returnVal);
        this.selectList.add(this.selection);
    }

    /**
     * The getStatus method.
     *
     * @return the status
     */
    public T getStatus() {
        return this.status;
    }

    /**
     * The setStatus method.
     *
     * @param st T type
     */
    public void setStatus(T st) {
        this.status = st;
    }

    @Override
    /**
     * The doOneFrame method.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.RED);
        d.drawText(30, 50, this.menu, 40);
        d.setColor(Color.CYAN);
        int y = 200;
        for (int i = 0; i < this.selectList.size(); i++) {
            String menuText = "(" + ((Selection) this.selectList.get(i)).getKey() + ")"
                    + ((Selection) this.selectList.get(i)).getMessage();
            d.drawText(10, y, menuText, 38);
            y = y + 100;
        }
        if (!shouldStop()) {
            for (int i = 0; i < this.selectList.size(); i++) {
                if (this.keyS.isPressed(((Selection) this.selectList.get(i)).getKey())) {
                    if (((Selection) this.selectList.get(i)).isSubMenu()) {
                        this.isMenu = ((Selection) this.selectList.get(i)).getSubMenu();
                        this.isSubMenu = true;
                        this.stop = true;
                        return;
                    } else {
                        Selection selection1 = (Selection) this.selectList.get(i);
                        setStatus((T) selection1.getReturnVal());
                        this.stop = true;
//                        this.isAlreadyPressed = true;
                        return;
                    }
                }
            }
        }
    }

    @Override
    /**
     * return true if need stop and otherwise false
     */
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    /**
     * The method setStop.
     */
    public void setStop(boolean s) {
        this.stop = s;
    }

    @Override
    /**
     * The method addSubMenu.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.selection = new Selection(key, message, subMenu);
        this.selectList.add(this.selection);
    }


    /**
     * @return true if it sub menu and otherwise false.
     */
    public Boolean isSubMenu() {
        return isSubMenu;
    }

    /**
     * The setSubMenu method.
     *
     * @param isSub boolean type
     */
    public void setSubMenu(Boolean isSub) {
        this.isSubMenu = isSub;
    }

}
