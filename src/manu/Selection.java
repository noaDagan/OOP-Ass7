package manu;


/**
 * The Selection class.
 *
 * @param <T> a generic
 */
public class Selection<T> {
    //Members
    private String key;
    private String message;
    private T returnVal;
    private boolean isSubMenuBool;
    private Menu<T> isSubMenu;

    /**
     * The constructor by the main menu.
     *
     * @param key       type string
     * @param m   type string
     * @param returnVal type T
     */
    public Selection(String key, String m, T returnVal) {
        this.key = key;
        this.message = m;
        this.returnVal = returnVal;
        this.isSubMenuBool = false;

    }

    /**
     * The Selection by the subMenu.
     *
     * @param key       type string
     * @param m       type string
     * @param isSubMenu type Menu
     */
    public Selection(String key, String m, Menu<T> isSubMenu) {
        this.key = key;
        this.message = m;
        this.isSubMenu = isSubMenu;
        this.isSubMenuBool = true;
    }

    /**
     * getKey method.
     *
     * @return a string
     */
    public String getKey() {
        return this.key;
    }

    /**
     * getMessage method.
     *
     * @return a strind
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * getReturnVal method.
     *
     * @return type T
     */
    public T getReturnVal() {
        return this.returnVal;
    }

    /**
     * isSubMenu method.
     *
     * @return type bool
     */
    public boolean isSubMenu() {
        return this.isSubMenuBool;
    }

    /**
     * The getSubMenu method.
     *
     * @return type menu
     */
    public Menu<T> getSubMenu() {
        return this.isSubMenu;
    }


}
