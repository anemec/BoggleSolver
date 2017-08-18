import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class DiceTile extends StackPane {

    Tile tile;

    public DiceTile(String letter, int x, int y) {
        tile = new Tile(letter, x, y);
        Text text = new Text(letter);

        relocate(x * 100, y * 100);

        getChildren().addAll(tile, text);

        setOnMousePressed(e -> {

            if (isTileSelectable(tile)) {
                tile.setFill(tile.getFill() == Color.LIGHTBLUE ? Color.GRAY : Color.LIGHTBLUE);
                if (tile.getFill() == Color.LIGHTBLUE) {
                    Board.addGuessStack(tile);
                    Board.setGuessWord(tile.getLetter());
                }
                else {
                    Board.getGuessStack().pop();
                    Board.removeLetter(tile.getLetter());
                }
            }

            System.out.println(tile.getLetter());
        });
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    private boolean isTileSelectable(Tile t) {
        Tile topStack = null;

        if (!Board.getGuessStack().empty())
            topStack = Board.getGuessStack().peek();

        if (Board.getGuessStack().empty())
            return true;

        if (t == topStack)
            return true;

        if (Board.getGuessStack().contains(t))
            return false;

        if (t.getNieghbors().contains(topStack))
            return true;

        if (t == tile && t.getNieghbors().contains(topStack))
            return true;
        return false;

    }


}
