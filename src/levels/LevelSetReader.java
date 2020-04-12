package levels;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The LevelSetReader class.
 */
public class LevelSetReader {
    //Members
    private List<LevelSet> levelSetList;

    /**
     * The LevelSetReader method build a new list of LevelSet.
     */
    public LevelSetReader() {
        this.levelSetList = new ArrayList<>();
    }

    /**
     * The fromReader methods parsing the text and create a new level set.
     *
     * @param reader a reader type
     * @throws Exception if string not valid
     */
    public void fromReader(java.io.Reader reader) throws Exception {
        LevelSet levelSet = new LevelSet();
        LineNumberReader readLine = new LineNumberReader(reader);
        String line = null;
        try {
            while ((line = readLine.readLine()) != null) {
                line = line.trim();
                int getLine = readLine.getLineNumber();
                if ((getLine % 2) == 1) {
                    String[] parsing = line.trim().split(":");
                    if (parsing.length != 2) {
                        throw new Exception("string line not valid");
                    }
                    String descriptionKey = parsing[0];
                    String descriptionMsg = parsing[1];
                    levelSet.setK(descriptionKey);
                    levelSet.setMsg(descriptionMsg);
                } else {
                    levelSet.setLevelPath(line);
                    this.levelSetList.add(levelSet);
                    levelSet = new LevelSet();
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The getLevelSetList method.
     * @return a list of levelSetList
     */
    public List<LevelSet> getLevelSetList() {
        return this.levelSetList;
    }

}
