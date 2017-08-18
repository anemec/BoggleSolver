import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 3/19/17.
 */
public class TrieElem {

    private Character letter = null;
    private boolean isEnd;
    private List<TrieElem> subtrie = new ArrayList<>();

    public TrieElem() {
        isEnd = false;
    }


    protected TrieElem(char c) {

        letter = c;
        isEnd = false;

    }

    char getLetter() {
        return letter;
    }

    void setLetter(char c) {
        letter = c;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd() {
        isEnd = true;
    }

    public void addToSubtrie(char c) {
        subtrie.add(new TrieElem(c));
    }

    public boolean find(char c, boolean isLast) {
        for (TrieElem trieElem : subtrie) {
            if (trieElem.getLetter() == c) {
                if (isLast)
                    return trieElem.isEnd;
                return true;
            }
        }
        return false;
    }

    //TODO Binary search or hash
    public TrieElem trieElemAt(char c) {
        for (TrieElem trieElem : subtrie) {
            if (trieElem.getLetter() == c) {
                return trieElem;
            }
        }
        return null;
    }

}
