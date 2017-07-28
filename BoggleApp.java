import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.*;

public class BoggleApp extends Application {

    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;

    private Group tileGroupBoard;
    private Group buttonGroup = new Group();


    private Parent initialCreate() throws FileNotFoundException {
        Pane root = new Pane();
        Board board = new Board();
        //Trie trie = new Trie();
        //trie.createTrie();

        //System.out.println(trie.findWord("aa"));

        root.setPrefSize(800, 750);
        //fillArray();
        //createGrid();
        tileGroupBoard = board.getTileGroup();

        //Text
        Text isWordFound = new Text(600, 150, " ");

        //ListView
        ListView<String> listView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        listView.setItems(items);
        listView.setPrefSize(100, 70);
        listView.relocate(600, 300);

        //Buttons
        Button solveButton = new Button("Solve");
        Button guessButton = new Button("Guess");
        Button resetButton = new Button("Reset Guess");
        solveButton.relocate(600, 200);
        guessButton.relocate(600, 100);
        resetButton.relocate(700, 100);

        solveButton.setOnMouseClicked(e -> {
            List<String> solverResults = Board.solverResults();
            Board.solveBoard();

            for (String s : solverResults) {
                items.add(s);
                listView.setItems(items);
            }

        });

        guessButton.setOnMouseClicked(e -> {
            String guessedWord = board.getGuessWord();
            boolean isFound = Trie.findWord(guessedWord);
            System.out.println("Word to guess is " + guessedWord);
            isWordFound.setText(String.valueOf(isFound));
            if (isFound) {
                items.add(guessedWord);
                listView.setItems(items);
            }

        });

        resetButton.setOnMouseClicked(e -> {
            board.reset();
        });








        buttonGroup.getChildren().addAll(solveButton, guessButton, resetButton,listView, isWordFound);


        //Text text = new Text(10, 20, "Hello");
        //root.getChildren().add(text);
        //root.getChildren().addAll(tileGroup);
        root.getChildren().addAll(tileGroupBoard, buttonGroup);

        return root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(initialCreate());
        primaryStage.setTitle("Boggle App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
