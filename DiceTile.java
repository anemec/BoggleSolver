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
        //System.out.println("Added");

        getChildren().addAll(tile, text);


        setOnMousePressed(e -> {
            tile.setFill(tile.getFill() == Color.LIGHTBLUE ? Color.GRAY : Color.LIGHTBLUE);
            if (tile.getFill() == Color.LIGHTBLUE)
                Board.setGuessWord(tile.getLetter());
            else
                Board.removeLetter(tile.getLetter());
            System.out.println(tile.getLetter());
        });
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }


}
