import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.Collections;

public class Board {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private Group tileGroup = new Group();
    private static StringBuilder guessWord = new StringBuilder();
    private static Solver solver;
    private static Stack<Tile> guessStack = new Stack<>();

    private String[][] dice = {{ "N", "S", "C", "T", "E", "C" },
            { "A", "E", "A", "E", "E", "E" },
            { "H", "H", "L", "R", "O", "D" },
            { "O", "R", "W", "V", "G", "R" },
            { "S", "P", "R", "I", "Y", "Y" },
            { "S", "U", "E", "N", "S", "S" },
            { "M", "E", "A", "U", "E", "G" },
            { "S", "E", "P", "T", "I", "C" },
            { "D", "H", "H", "O", "W", "N" },
            { "L", "E", "P", "T", "I", "S" },
            { "S", "T", "L", "I", "E", "I" },
            { "A", "R", "S", "I", "Y", "F" },
            { "T", "E", "T", "I", "I", "I" },
            { "O", "T", "T", "T", "M", "E" },
            { "N", "M", "N", "E", "G", "A" },
            { "N", "N", "E", "N", "A", "D" },
            { "O", "U", "O", "T", "T", "O" },
            { "B", "Z", "J", "B", "X", "K" },
            { "A", "A", "F", "A", "S", "R" },
            { "T", "O", "O", "U", "W", "N" },
            { "O", "T", "H", "D", "D", "N" },
            { "R", "A", "A", "S", "F", "I" },
            { "H", "O", "D", "R", "L", "N" },
            { "E", "E", "E", "E", "A", "M" },
            { "HE", "QU", "TH", "IN", "ER", "AN" }};

    private Integer[] randNumArr = new Integer[25];
    public static String[][] boardArr = new String[HEIGHT][HEIGHT];
    public static DiceTile[][] tileBoard = new DiceTile[HEIGHT][HEIGHT];

    public Board() {
        fillArray();
        createGrid();
        setNeighbors();
        solver = new Solver();

    }

    public void solveBoard() {
        solver.printResultsToCLI();
    }

    public List<String> solverResults() {
        return solver.getResults();
    }

    private void createGrid() {
        int i = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Random rand = new Random();
                int randNumi = randNumArr[i];
                int randNumj = rand.nextInt(6);

                String letter = dice[randNumi][randNumj];
                boardArr[y][x] = letter;
                DiceTile tile = new DiceTile(letter, x, y);
                tileBoard[y][x] = tile;
                tileGroup.getChildren().add(tile);
                i++;
            }
        }
    }

    private void fillArray() {

        for (int i = 0; i < 25; i++) {
            randNumArr[i] = i;
        }
        Collections.shuffle(Arrays.asList(randNumArr));

        System.out.println(Arrays.toString(randNumArr));

    }

    public Group getTileGroup() {
        return tileGroup;
    }

    public void printBoardToCommandLine() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                System.out.print(boardArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static Stack<Tile> getGuessStack() {
        return guessStack;
    }

    public static void addGuessStack(Tile t) {
        guessStack.push(t);
    }

    public static void setGuessWord(String letter) {
        guessWord.append(letter);
    }

    public static void removeLetter(String letter) {
        guessWord.delete(guessWord.length() - letter.length(), guessWord.length());
    }

    public String getGuessWord() {
        return guessWord.toString();
    }

    public static void reset() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tileBoard[i][j].tile.setFill(Color.GRAY);
            }
        }
        guessWord.delete(0, guessWord.length());
        //guessWord = null;
        guessStack.clear();
    }

    private void setNeighbors() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i > 0)
                    tileBoard[i][j].getTile().setNorthTile(tileBoard[i - 1][j].getTile());
                if (i > 0 && j < HEIGHT - 1)
                    tileBoard[i][j].getTile().setNeTile(tileBoard[i - 1][j + 1].getTile());
                if (i > 0 && j > 0)
                    tileBoard[i][j].getTile().setNwTile(tileBoard[i - 1][j - 1].getTile());
                if (i < HEIGHT - 1)
                    tileBoard[i][j].getTile().setSouthTile(tileBoard[i + 1][j].getTile());
                if (i < HEIGHT - 1 && j < HEIGHT - 1)
                    tileBoard[i][j].getTile().setSeTile(tileBoard[i + 1][j + 1].getTile());
                if (i < HEIGHT - 1 && j > 0)
                    tileBoard[i][j].getTile().setSwTile(tileBoard[i + 1][j - 1].getTile());
                if (j > 0)
                    tileBoard[i][j].getTile().setWestTile(tileBoard[i][j - 1].getTile());
                if (j < HEIGHT - 1)
                    tileBoard[i][j].getTile().setEastTile(tileBoard[i][j + 1].getTile());
            }
        }
    }

    public static int getSize() {
        return HEIGHT;
    }


}
