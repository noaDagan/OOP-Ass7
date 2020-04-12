package levels;

/**
 * The LevelSet class.
 */
public class LevelSet {
    //Members
    private String msg;
    private String k;
    private String level;

    /**
     * The level set.
     */
    public LevelSet() {
    }

    /**
     * getMsg method.
     * @return string
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * getK method.
     * @return string
     */
    public String getK() {
        return this.k;
    }

    /**
     * The getLevelPath.
     * @return return string
     */
    public String getLevelPath() {
        return level;
    }

    /**
     * The setMsg method.
     * @param m string type
     */
    public void setMsg(String m) {
        this.msg = m;
    }

    /**
     * The setK method.
     * @param key string
     */
    public void setK(String key) {
        this.k = key;
    }

    /**
     * setLevelPath method.
     * @param levelPath string
     */
    public void setLevelPath(String levelPath) {
        this.level = levelPath;
    }
}
