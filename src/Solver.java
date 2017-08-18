import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solver {

    List<String> results = new ArrayList<>();
    Stack<Tile> tileStack = new Stack<>();

    public Solver() {
        search();
    }

    public void search() {
        for (int i = 0; i < Board.getSize(); i ++) {
            for (int j = 0; j < Board.getSize(); j++) {
                searchTile(Board.tileBoard[i][j].getTile());
            }
        }
    }

    public void searchTile(Tile tile) {
        tileStack.clear();
        searchTileHelper(tile, tile.getLetter());
    }

    private void searchTileHelper(Tile tile, String string) {
        //System.out.println("String so far " + string);
        List<Tile> neighbors = tile.getNieghbors();
        tileStack.push(tile);

        if (Trie.findPartialWord(string)) {
            if (Trie.findWord(string))
                results.add(string);


            for (Tile n : neighbors) {
                if (tileStack.search(n) == -1) {
                    String copyString = string;
                    searchTileHelper(n, (copyString += n.getLetter()));
                }
            }

        }
        tileStack.pop();

    }

    public void printResultsToCLI() {
        for (String s : results)
            System.out.println("Result: " + s);
    }

    public List<String> getResults() {
        return results;
    }
}
