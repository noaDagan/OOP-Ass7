package manu;

import animation.Animation;

/**
 * The interface Menu.
 * @param <T> a generic type
 */
public interface Menu<T> extends Animation {
    /**
     * addSelection method.
     * @param key a string type
     * @param message a string message
     * @param returnVal a t tyep.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * getStatus method.
     * @return the status
     */
    T getStatus();

    /**
     * The setStop method.
     * @param stop a boolean type, if true to stop o
     */
    void setStop(boolean stop);

    /**
     * addSubMenu method.
     * @param key a string
     * @param message a string
     * @param subMenu a T type
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}