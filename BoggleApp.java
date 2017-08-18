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

import java.util.List;


public class BoggleApp extends Application {

    private Group tileGroupBoard;
    private Group buttonGroup = new Group();
    private Group textGroup = new Group();
    private Group listViewGroup = new Group();

    private Integer score = 0;


    private Parent initialCreate() throws FileNotFoundException {
        Pane root = new Pane();
        Board board = new Board();

        root.setPrefSize(800, 750);
        tileGroupBoard = board.getTileGroup();

        //Text
        Text isWordFound = new Text(550, 100, " ");
        Text wordsFoundSoFar = new Text(550, 150, "Words found so far");
        Text playerOneScore = new Text(100, 550, "Player score:     " + score.toString());
        textGroup.getChildren().addAll(isWordFound, wordsFoundSoFar, playerOneScore);

        //ListView
        ListView<String> listView = new ListView<>();
        ListView<String> solvedListView = new ListView<>();

        ObservableList<String> items = FXCollections.observableArrayList();
        ObservableList<String> solveItems = FXCollections.observableArrayList();

        listView.setItems(items);
        listView.setPrefSize(150, 100);
        listView.relocate(550, 165);

        solvedListView.setItems(solveItems);
        solvedListView.setPrefSize(150, 100);
        solvedListView.relocate(550, 400);

        listViewGroup.getChildren().addAll(listView, solvedListView);

        //Buttons
        Button solveButton = new Button("Solve");
        Button guessButton = new Button("Guess");
        Button resetButton = new Button("Reset Guess");
        solveButton.relocate(550, 350);
        guessButton.relocate(550, 50);
        resetButton.relocate(625, 50);

        solveButton.setOnMouseClicked(e -> {
            List<String> solverResults = board.solverResults();
            // List<String> solverResults = Board.solverResults();
            board.solveBoard();
            //Board.solveBoard();

            for (String s : solverResults) {
                solveItems.add(s.toLowerCase());
                solvedListView.setItems(solveItems);
            }

        });

        guessButton.setOnMouseClicked(e -> {
            String guessedWord = board.getGuessWord();
            boolean isFound = Trie.findWord(guessedWord);
            System.out.println("Word to guess is " + guessedWord);
            if (guessedWord.length() == 0) {
                isWordFound.setText("Please use the board to enter a word");
            } else if (!items.contains(guessedWord.toLowerCase())) {
                isWordFound.setText("Word " + guessedWord + " found: " + String.valueOf(isFound));
                if (isFound) {
                    items.add(guessedWord.toLowerCase());
                    listView.setItems(items);
                    calculateScore(guessedWord);
                    playerOneScore.setText("Player score:     " + score.toString());
                }
            } else {
                isWordFound.setText(guessedWord + " already found");
            }

        });

        resetButton.setOnMouseClicked(e -> {
            board.reset();
            isWordFound.setText(" ");
        });

        buttonGroup.getChildren().addAll(solveButton, guessButton, resetButton);

        root.getChildren().addAll(tileGroupBoard, buttonGroup, textGroup, listViewGroup);

        return root;
    }

    private void calculateScore(String word) {
        if (word.length() == 3 || word.length() == 4)
            score += 1;
        if (word.length() == 5)
            score += 2;
        if (word.length() == 6)
            score += 3;
        if (word.length() == 7)
            score += 5;
        if (word.length() >= 8)
            score += 11;
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
