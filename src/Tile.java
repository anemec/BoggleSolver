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
    Tile nwTile = null;
    Tile neTile = null;
    Tile seTile = null;
    Tile swTile = null;

    private String letter;
    private int x, y;
    private boolean isCurrentTile = false;

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

    public void setIsCurrent(boolean b) {
        isCurrentTile = b;
    }

    public boolean isCurrent() {
        return isCurrentTile;
    }

    public Tile getNorthTile() {
        return northTile;
    }

    public void setNorthTile(Tile northTile) {
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
        this.westTile = westTile;
    }

    public Tile getNwTile() {
        return nwTile;
    }

    public void setNwTile(Tile nwTile) {
        this.nwTile = nwTile;
    }

    public Tile getNeTile() {
        return neTile;
    }

    public void setNeTile(Tile neTile) {
        this.neTile = neTile;
    }

    public Tile getSeTile() {
        return seTile;
    }

    public void setSeTile(Tile seTile) {
        this.seTile = seTile;
    }

    public Tile getSwTile() {
        return swTile;
    }

    public void setSwTile(Tile swTile) {
        this.swTile = swTile;
    }

    public List<Tile> getNieghbors() {
        List<Tile> list = new ArrayList<>();
        if (northTile != null)
            list.add(northTile);
        if (neTile != null)
            list.add(neTile);
        if (eastTile != null)
            list.add(eastTile);
        if (seTile != null)
            list.add(seTile);
        if (southTile != null)
            list.add(southTile);
        if (swTile != null)
            list.add(swTile);
        if (westTile != null)
            list.add(westTile);
        if (nwTile != null)
            list.add(nwTile);

        return list;
    }
}
