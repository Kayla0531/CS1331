import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Scanner;

public class Jordle extends Application implements EventHandler<KeyEvent> {
    public static void main(String[] args) {
        launch(args);
    }
    int inputColIndex = 0;
    int inputRowIndex = 0;
    String answer = getAnswer();
    String[] currLine = new String[5];
    GridPane board = new GridPane();
    Text lastInput = new Text();

    @Override
    public void start(Stage primaryStage) {
        startGame(primaryStage);
    }
    private void cleanup() {
        inputColIndex = 0;
        inputRowIndex = 0;
        //input = new Text();
        answer = getAnswer();
        currLine = new String[5];
    }
    /**
    class Cell extends StackPane {
        private Paint color;
        Cell() {
            super();
            this.color = Color.WHITE;
        }
        void add(Node node) {
            super.add(node);
        }
        void reFill(Paint newColor){
            this.color = newColor;
            this.add(new Rectangle(52, 52, newColor));
        }
    }
    */
    void startGame(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane);
        pane.setTop(new TextPane("JORDLE"));
        board = new GridPane();
        board.setAlignment(Pos.CENTER);
        board.setPadding(new Insets(20, 40, 10, 40)); // (TOP, RIGHT, BOTTOM, LEFT)
        board.setHgap(8);
        board.setVgap(5);
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                Rectangle box = new Rectangle(50, 50, Color.WHITE);
                Rectangle outer = new Rectangle(52, 52, Color.BLACK);
                board.add(outer, c, r);
                setObjectCenter(outer);
                board.add(box, c, r);
                setObjectCenter(box);
                scene.setOnKeyPressed(this);
                
            }
        }
        // overlapping
        //board.add(new Rectangle(52, 52, Color.LIGHTGREEN), 0, 1);
        pane.setCenter(board);
        GridPane bottom = new GridPane();
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10, 20, 20, 20));
        bottom.setHgap(5);
        Label bottomWords = new Label("Try Everything!");
        bottom.add(bottomWords, 0, 0);
        Button restartButton = new Button("RESTART!");
        restartButton.setFocusTraversable(false);
        bottom.add(restartButton, 1, 0);
        restartButton.setOnAction(
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    cleanup();
                    startGame(primaryStage);
                }
            }
        );
        Button instructButton = new Button("instructions");
        instructButton.setFocusTraversable(false);
        bottom.add(instructButton, 2, 0);
        pane.setBottom(bottom);
        

        

        
        primaryStage.setTitle("Jordle!"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    class Cell extends StackPane {
        private Paint bgdColor = Color.BLACK;
        private Rectangle bgdRect = new Rectangle(52, 52, bgdColor);
        private Text text = new Text();
        private Paint fillColor = Color.WHITE;
        private Rectangle fillRect = new Rectangle(50, 50, fillColor);
        Cell(String t) {
            text.setText(t);
            fillColor = name;
            this.add(bgdRect);
            this.add(fillRect);
            this.add(text);
        }
        Cell() {
            this(new Text());
        }
        void setBgdColor(Paint newColor) {
            bgdColor = newColor;
            this.bgdRect = new Rectangle(52, 52, bgdColor);
        }
        Paint getBgd() {
            return bgdColor;
        }
        void setFillColor(Paint newColor) {
            fillColor = newColor;
            this.fillRect = new Rectangle(50, 50, fillColor);
        }
        Paint getFill() {
            return fillColor;
        }
        void setText(String newS) {
            text.setText(newS);
        }
        String getText() {
            return text.toString();
        }
    }
    @Override
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        Text input = new Text();
        if (code.isLetterKey()) {
            while (currLine[inputColIndex] != null) {
                inputColIndex++;
            }
            if (inputColIndex < 5) {
                input.setText(code.toString());
                input.setFont(new Font("Times New Roman Bold", 30));
                board.add(input, inputColIndex, inputRowIndex);
                currLine[inputColIndex] = code.toString();
            }
        }
        if (code.equals(KeyCode.BACK_SPACE)) {
            while (currLine[inputColIndex] == null) {
                inputColIndex--;
            }
            if (inputColIndex >= 0) {
                Text toBeRemoved = new Text();
                toBeRemoved.setText(currLine[inputColIndex]);
                currLine[inputColIndex] = null;
                board.getChildren().remove(toBeRemoved);
            }
        }
        if (code.equals(KeyCode.ENTER)) {
            if (currLine[4] != null) {
                evaluateLine(currLine);
                currLine = new String[5];
                inputRowIndex++;
                inputColIndex = 0;
            } else {
                System.out.println("wait till you have put 5 chars");
                // an alert panel saying invalid input
            }
        }
    }
    private static void setObjectCenter(Shape object) {
        GridPane.setHalignment(object, HPos.CENTER);
        GridPane.setValignment(object, VPos.CENTER);
    }
    String getAnswer() {
        // preventing reemerging answers
        ArrayList<String> myList = Words.list;
        String a = myList.get((int)(Math.random()*myList.size()));
        myList.remove(a);
        // System.out.println(myList);
        a = a.toUpperCase();
        System.out.println(a);
        return a;
    }
    int[] charCounter(String word) {
        word = word.toUpperCase();
        int[] counter = new int[26]; 
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            counter[(int) c - 65]++;
        }
        return counter;
    }
    char[] evaluateLine(String[] line) {
        char[] res = new char[5];
        int[] charCount = charCounter(answer);
        // first loop is to find out all chars at right position
        for (int i = 0; i < 5; i++) {
            String inputWord = line[i].toUpperCase();
            char ansChar = answer.charAt(i);
            char inputChar = inputWord.charAt(0);
            if (ansChar == inputChar) {
                charCount[(int)inputChar - 65]--;
                res[i] = 'G'; // should be set to green using setFill
            }
        }
        // second loop looks for char that exist but not at right position
        for (int i = 0; i < 5; i++) {
            char inputChar = line[i].charAt(0);
            if (res[i] != '\u0000') {
                continue;
            } else if (charCount[(int) inputChar - 65] > 0) {
                res[i] = 'Y'; // assigned yellow
            } else {
                res[i] = 'N'; // not found, assigned gray                
            }
        }
        return res;
    }
    void checkChar(char c, int[] counts) {
        //if 
    }
}
class TextPane extends StackPane {
    public TextPane(String text) {
        Text t = new Text(text);
        t.setFont(new Font("Times New Roman Bold", 50));
        this.getChildren().add(t);
        this.setPadding(new Insets(20, 10, 0, 10));
    }
}
