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
    
    ArrayList<Cell> setCurrCells() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        for (int i = 0; i < 5; i++) {
            list.add(new Cell());
        }
        return list;
    }
    ArrayList<Cell> currCells = setCurrCells();

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
                /**
                Rectangle box = new Rectangle(50, 50, Color.WHITE);
                Rectangle outer = new Rectangle(52, 52, Color.BLACK);
                board.add(outer, c, r);
                setObjectCenter(outer);
                board.add(box, c, r);
                setObjectCenter(box);
                scene.setOnKeyPressed(this);
                */
                Cell cell = new Cell();
                board.add(cell, c, r);
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
        private Paint bgdColor = Color.LIGHTGRAY;
        private Rectangle bgdRect = new Rectangle(52, 52, bgdColor);
        private Text text = new Text();
        private Paint fillColor = Color.WHITE;
        private Rectangle fillRect = new Rectangle(50, 50, fillColor);
        Cell(String t, Paint bgd, Paint fill) {
            text.setText(t);
            text.setFont(new Font("Times New Roman Bold", 30));
            bgdRect = new Rectangle(52, 52, bgd);
            fillRect = new Rectangle(50, 50, fill);
            this.getChildren().addAll(bgdRect, fillRect, text);
            setObjectCenter(bgdRect);
            setObjectCenter(fillRect);
            setObjectCenter(text);
        }
        Cell(String t) {
            this(t, Color.LIGHTGRAY, Color.WHITE);
        }
        Cell() {
            this("");
        }
        Cell(Cell ref) {
            this(ref.getText(), ref.getBgd(), ref.getFill());
        }
        void setBgdColor(Paint newColor) {
            bgdColor = newColor;
            this.getChildren().remove(bgdRect);
            this.bgdRect = new Rectangle(52, 52, bgdColor);
            this.getChildren().add(bgdRect);
            setObjectCenter(bgdRect);
        }
        Paint getBgd() {
            return bgdColor;
        }
        void setFillColor(Paint newColor) {
            fillColor = newColor;
            this.getChildren().remove(fillRect);
            this.fillRect = new Rectangle(50, 50, fillColor);
            this.getChildren().add(fillRect);
            setObjectCenter(fillRect);
        }
        Paint getFill() {
            return fillColor;
        }
        void setText(String newS) {
            this.getChildren().remove(text);
            text.setText(newS);
            this.getChildren().add(text);
            text.setFont(new Font("Times New Roman Bold", 30));
            setObjectCenter(text);
        }
        String getText() {
            return text.getText();
        }
    }
    @Override
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code.isLetterKey()) {
            String input = code.toString();
            if (inputColIndex < 5) {
                board.getChildren().remove(currCells.get(inputColIndex));
                Cell newCell = new Cell(input);
                currCells.set(inputColIndex, newCell);
                board.add(newCell, inputColIndex, inputRowIndex);
                inputColIndex++;
            }
        }
        if (code.equals(KeyCode.BACK_SPACE)) {
            if (inputColIndex > 0) {
                inputColIndex--;
                board.getChildren().remove(currCells.get(inputColIndex));
                
                Cell newCell = new Cell();
                currCells.set(inputColIndex, newCell);
                board.add(newCell, inputColIndex, inputRowIndex);
            }
        }
        if (code.equals(KeyCode.ENTER)) {
            if (inputColIndex >= 5) {
                for (int i = 0; i < 5; i++) {
                    currLine[i] = currCells.get(i).getText();
                }
                Paint[] newColors = evaluateLine(currLine);
                for (int i = 0; i < 5; i++) {
                    Cell newCell = new Cell(currCells.get(i));
                    newCell.setBgdColor(newColors[i]);
                    newCell.setText(currCells.get(i).getText());
                    board.getChildren().remove(currCells.get(i));
                    currCells.set(i, newCell);
                    board.add(newCell, i, inputRowIndex);
                }
                inputRowIndex++;
                currCells = setCurrCells();
                inputColIndex = 0;
            }
            if (inputRowIndex == 6) {
                endGame();
            }
        }
    }
    private void endGame(){
        // an alert windows shows up
        System.out.println("Game Ended!");
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
    Paint[] evaluateLine(String[] line) {
        Paint[] res = new Paint[5];
        int[] charCount = charCounter(answer);
        answer = answer.toUpperCase();
        // first loop is to find out all chars at right position
        for (int i = 0; i < 5; i++) {
            String inputStr = line[i].toUpperCase();
            char ansChar = answer.charAt(i);
            char inputChar = inputStr.charAt(0);
            if ((int) inputChar > 90 || (int) inputChar < 65) {
                res[i] = Color.LIGHTGRAY;
                continue;
            }
            if (ansChar == inputChar) {
                charCount[(int)inputChar - 65]--;
                res[i] = Color.LIGHTGREEN; // should be set to green using setFill            
            }
        }
        for (int j = 0; j < 5; j++) {
            String inputStr = line[j].toUpperCase();
            char ansChar = answer.charAt(j);
            char inputChar = inputStr.charAt(0);
            if (res[j] != null) {
                continue;
            }
            if (charCount[(int) inputChar - 65] > 0) {
                res[j] = Color.LIGHTYELLOW;
                charCount[(int) inputChar - 65]--;
            } else {
                res[j] = Color.LIGHTGRAY;
            }
        }
        return res;
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
