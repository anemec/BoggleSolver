import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Rectangle {

    private static final int TILE_SIZE = 100;

    Tile northTile = null;
    Tile eastTile = null;
    Tile southTile = null;
    Tile westTile = null;

    private String letter;
    private int x, y;

    public String getLetter() {
        return letter;
    }

    public int getTileX() {
        return x;
    }

    public int getTileY() {
        return y;
    }

    public Tile(String letter, int x, int y) {
        this.letter = letter;
        this.x = x;
        this.y = y;
        setHeight(TILE_SIZE);
        setWidth(TILE_SIZE);
        setStroke(Color.BLACK);
        setStrokeWidth(1);

        setFill(Color.GRAY);

    }

    public Tile getNorthTile() {
        return northTile;
    }

    public void setNorthTile(Tile northTile) {
        System.out.println();
        System.out.println("North tile set");
        System.out.println("Tile " + getLetter() + " North tile " + northTile.getLetter());
        this.northTile = northTile;
    }

    public Tile getEastTile() {
        return eastTile;
    }

    public void setEastTile(Tile eastTile) {
        this.eastTile = eastTile;
    }

    public Tile getSouthTile() {
        return southTile;
    }

    public void setSouthTile(Tile southTile) {
        this.southTile = southTile;
    }

    public Tile getWestTile() {
        return westTile;
    }

    public void setWestTile(Tile westTile) {
        System.out.println();
        System.out.println("West tile set");
        System.out.println("Tile " + getLetter() + " West tile " + westTile.getLetter());

        this.westTile = westTile;
    }

    public List<Tile> getNieghbors() {
        List<Tile> list = new ArrayList<>();
        if (northTile != null)
            list.add(northTile);
        if (eastTile != null)
            list.add(eastTile);
        if (southTile != null)
            list.add(southTile);
        if (westTile != null)
            list.add(westTile);
        return list;
    }
}
