import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Jordle. A java-related word guessing game.
 *
 * @author Jiayiw
 * @version 5.1
 */
public class Jordle extends Application implements EventHandler<KeyEvent> {
    /**
     * main method, launches the window.
     *
     * @param args  System input
     */
    public static void main(String[] args) {
        launch(args);
    }
    private int inputColIndex = 0;
    private ArrayList<String> myList = new ArrayList<String>(Words.list);
    private int inputRowIndex = 0;
    private String answer = getAnswer();
    private String[] currLine = new String[5];
    private Scene scene;
    private BorderPane pane;
    private GridPane board = new GridPane();
    private GridPane bottom = new GridPane();
    private boolean gameEnded = false;
    private Label bottomWords = new Label("Try Everything!");
    private ArrayList<Paint> defaultColorSet = new ArrayList<>(Arrays.asList(
        Color.web("EEECEB"), Color.GRAY, Color.WHITE, Color.LIGHTGREEN,
        Color.web("FFFF55"), Color.LIGHTGRAY, Color.BLACK));
        // Scene bgd, cell contour, cell fill, letter at right pos,
        // letter at wrong pos, irrelevant letter, text
    private String[] bgdColors = {"EEECEB", "252424"};
    private int bgdColorIndex = 0;
    private ArrayList<Paint> darkColorSet = new ArrayList<>(Arrays.asList(
        Color.web("252424"), Color.GRAY, Color.BLACK, Color.FORESTGREEN,
        Color.SADDLEBROWN, Color.LIGHTSLATEGRAY, Color.LIGHTGRAY));
    private ArrayList<Paint> colorSet = defaultColorSet;

    ArrayList<Cell> setCurrCells() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        for (int i = 0; i < 5; i++) {
            list.add(new Cell());
        }
        return list;
    }
    private ArrayList<Cell> currCells = setCurrCells();

    @Override
    public void start(Stage primaryStage) {
        startGame(primaryStage);
    }
    private void cleanup() {
        inputColIndex = 0;
        inputRowIndex = 0;
        answer = getAnswer();
        currLine = new String[5];
        colorSet = defaultColorSet;
        bgdColorIndex = 0;
        pane = new BorderPane();
        board = new GridPane();
        bottom = new GridPane();
        gameEnded = false;
        bottomWords = new Label("Try Everything!");
        pane.setStyle("-fx-background-color: #" + bgdColors[bgdColorIndex] + ";");
    }

    GridPane buildBoard(GridPane gp) {
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(20, 40, 0, 40)); // (TOP, RIGHT, BOTTOM, LEFT)
        gp.setHgap(8);
        gp.setVgap(5);
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                Cell cell = new Cell();
                gp.add(cell, c, r);
                scene.setOnKeyPressed(this);
            }
        }
        return gp;
    }
    private void setColorMode(ArrayList<Paint> newSet) {
        colorSet = newSet;
        if (newSet == defaultColorSet) {
            bgdColorIndex = 0;
        } else {
            bgdColorIndex = 1;
        }
        pane.setTop(new TextPane("JORDLE"));
        pane.setStyle("-fx-background-color: #" + bgdColors[bgdColorIndex] + ";");
        for (Node c : board.getChildren()) {
            if (c instanceof Cell) {
                Cell cell = (Cell) c;
                cell.setColorMode();
            }
        }
    }
    private void setColorMode() {
        if (colorSet == defaultColorSet) {
            this.setColorMode(darkColorSet);
        } else {
            this.setColorMode(defaultColorSet);
        }
    }
    void setButtonColorMode(Button btn) {
        btn.setStyle("-fx-background-color: #" + bgdColors[bgdColorIndex] + ";");
        int otherColorIndex = 1 - bgdColorIndex;
        btn.setStyle("-fx-text-fill: #" + bgdColors[otherColorIndex] + ";");

    }
    void startGame(Stage primaryStage) {
        pane = new BorderPane();
        pane.setStyle("-fx-background-color: #" + bgdColors[bgdColorIndex] + ";");
        scene = new Scene(pane);
        pane.setTop(new TextPane("JORDLE"));
        board = buildBoard(board);
        pane.setCenter(board);
        bottom = new GridPane();
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(15, 20, 20, 20));
        bottom.setHgap(5);
        bottom.add(bottomWords, 0, 0);
        bottomWords.setFont(new Font("Times New Roman Bold", 14));
        final Button restartButton = new Button("RESTART!");
        restartButton.setFocusTraversable(false);
        bottom.add(restartButton, 1, 0);
        restartButton.setFont(new Font("Times New Roman Bold", 14));
        restartButton.setOnAction(
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    cleanup();
                    startGame(primaryStage);
                }
            }
        );
        final Button instructButton = new Button("instructions");
        instructButton.setFocusTraversable(false);
        bottom.add(instructButton, 2, 0);
        instructButton.setFont(new Font("Times New Roman Bold", 14));
        Alert instruction = new Alert(Alert.AlertType.INFORMATION,
            "Try to guess a 5-digit word!\n"
            + "GREEN is for letters at their right spot\n"
            + "YELLOW/BROWN ones should be on another spot\n"
            + "and GRAY letters are not in the answer\n"
            + "GOOD LUCK ;)");
        instruction.setHeaderText("Instruction");
        instructButton.setOnAction((ActionEvent e) -> {
            instruction.showAndWait();
        });
        final Button colorModeButton = new Button("Color mode");
        colorModeButton.setFocusTraversable(false);
        bottom.add(colorModeButton, 3, 0);
        colorModeButton.setFont(new Font("Times New Roman Bold", 14));

        colorModeButton.setOnAction((ActionEvent e) -> {
            setColorMode();
            bottomWords.setStyle("-fx-text-fill: #" + bgdColors[1 - bgdColorIndex] + ";");
            setButtonColorMode(restartButton);
            setButtonColorMode(instructButton);
            setButtonColorMode(colorModeButton);
        });
        pane.setBottom(bottom);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(530);
        primaryStage.setMaxWidth(500);
        primaryStage.setMaxHeight(530);
        primaryStage.setTitle("Jordle."); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    class Cell extends StackPane {
        private Paint bgdColor = colorSet.get(1);
        private Rectangle bgdRect = new Rectangle(52, 52, bgdColor);
        private Text text = new Text();
        private Paint fillColor = colorSet.get(2);
        private Rectangle fillRect = new Rectangle(50, 50, fillColor);
        Cell(String t, Paint bgd, Paint fill) {
            text.setText(t);
            text.setFont(new Font("Times New Roman Bold", 30));
            text.setFill(colorSet.get(6));
            bgdRect = new Rectangle(52, 52, bgd);
            fillRect = new Rectangle(50, 50, fill);
            this.getChildren().addAll(bgdRect, fillRect, text);
            setObjectCenter(bgdRect);
            setObjectCenter(fillRect);
            setObjectCenter(text);
        }
        Cell(String t) {
            this(t, colorSet.get(1), colorSet.get(2));
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
            text.setFill(colorSet.get(6));
            setObjectCenter(text);
        }
        String getText() {
            return text.getText();
        }
        void setColorMode() {
            ArrayList<Paint> otherColorSet = (colorSet == defaultColorSet) ? darkColorSet : defaultColorSet;
            this.setBgdColor(colorSet.get(otherColorSet.indexOf(this.getBgd())));
            this.setFillColor(colorSet.get(otherColorSet.indexOf(this.getFill())));
            this.setText(getText());
        }
    }
    @Override
    public void handle(KeyEvent event) {
        if (gameEnded) {
            return;
        }
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
                boolean allGreen = true;
                for (int i = 0; i < 5; i++) {
                    Cell newCell = new Cell(currCells.get(i));
                    if (newColors[i] != colorSet.get(3)) {
                        allGreen = false;
                    }
                    newCell.setFillColor(newColors[i]);
                    newCell.setText(currCells.get(i).getText());
                    board.getChildren().remove(currCells.get(i));
                    currCells.set(i, newCell);
                    board.add(newCell, i, inputRowIndex);
                }
                inputRowIndex++;
                currCells = setCurrCells();
                inputColIndex = 0;
                if (allGreen) {
                    endGame(true);
                }
            } else {
                Alert inputUnderFive = new Alert(Alert.AlertType.ERROR, "We need 5 digits!");
                inputUnderFive.setHeaderText("Not Enough Letters!");
                inputUnderFive.showAndWait();
            }
            if (inputRowIndex == 6 & !gameEnded) {
                endGame(false);
            }
        }
    }
    private void endGame(boolean won) {
        // an alert windows shows up
        gameEnded = true;
        bottom.getChildren().remove(bottomWords);
        if (won) {
            bottomWords = new Label("GOOD JOB! ^v^");
        } else {
            bottomWords = new Label("Oops.\nIt was " + answer + ".");
        }
        bottomWords.setFont(new Font("Times New Roman Bold", 14));
        bottomWords.setTextFill(colorSet.get(6));
        bottom.add(bottomWords, 0, 0);
    }
    private static void setObjectCenter(Shape object) {
        GridPane.setHalignment(object, HPos.CENTER);
        GridPane.setValignment(object, VPos.CENTER);
    }
    String getAnswer() {
        // preventing reemerging answers
        if (myList.size() <= 0) {
            myList = new ArrayList<String>(Words.list);
        }
        String a = myList.get((int) (Math.random() * myList.size()));
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
                res[i] = colorSet.get(5);
                continue;
            }
            if (ansChar == inputChar) {
                charCount[(int) inputChar - 65]--;
                res[i] = colorSet.get(3); // should be set to green using setFill
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
                res[j] = colorSet.get(4);
                charCount[(int) inputChar - 65]--;
            } else {
                res[j] = colorSet.get(5);
            }
        }
        return res;
    }
    class TextPane extends StackPane {
        TextPane(String text) {
            Text t = new Text(text);
            t.setFont(new Font("Times New Roman Bold", 50));
            t.setFill(colorSet.get(6));
            this.getChildren().add(t);
            this.setPadding(new Insets(20, 10, 0, 10));
        }
    }
}
