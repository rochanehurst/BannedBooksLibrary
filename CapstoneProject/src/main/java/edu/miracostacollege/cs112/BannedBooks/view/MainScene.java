package edu.miracostacollege.cs112.BannedBooks.view;

import edu.miracostacollege.cs112.BannedBooks.controller.BannedBooksController;
import edu.miracostacollege.cs112.BannedBooks.model.BannedBooks;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class MainScene extends Scene {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;



    private ImageView bannedBooksIV = new ImageView();
    private TextField nameTF = new TextField();
    private Label nameLabel = new Label("Book Name:");
    private Label nameErrLabel = new Label("Name is required.");

    private TextField authorTF = new TextField();
    private Label authorErrLabel = new Label("Author is required.");

    private TextField typeOfBanTF = new TextField();
    private Label typeOfBanErrLabel = new Label("Type of Ban is required.");

    private Label stateLabel = new Label("State:");
    private ComboBox<String> stateComboBox = new ComboBox<>();
    private Label stateOfBanErrLabel = new Label("State of Ban is required.");

    private ListView<BannedBooks> booksLV = new ListView<>();

    private Button removeButton = new Button("- Remove Book");
    private Button addButton = new Button("+ Add Book");

    private BannedBooksController controller = BannedBooksController.getInstance();
    private ObservableList<BannedBooks> booksList;
    private BannedBooks selectedBook;


    public MainScene() {
        super(new GridPane(), WIDTH, HEIGHT);
        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color: beige;");
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));


        bannedBooksIV.setImage(new Image("banned_books_image.png"));
        bannedBooksIV.setFitWidth(WIDTH);
        pane.add(bannedBooksIV, 0, 0, 3, 1);

        pane.add(new Label("Book Name:"), 0, 1);
        pane.add(nameTF, 1, 1);
        pane.add(nameErrLabel, 2, 1);
        nameErrLabel.setTextFill(Color.RED);
        nameErrLabel.setVisible(false);

        pane.add(new Label("Author:"), 0, 2);
        pane.add(authorTF, 1, 2);
        pane.add(authorErrLabel, 2, 2);
        authorErrLabel.setTextFill(Color.RED);
        authorErrLabel.setVisible(false);

        pane.add(new Label("Type of Ban:"), 0, 3);
        pane.add(typeOfBanTF, 1, 3);
        pane.add(typeOfBanErrLabel, 2, 3);
        typeOfBanErrLabel.setTextFill(Color.RED);
        typeOfBanErrLabel.setVisible(false);

        pane.add(stateLabel, 0, 4);
        // Add all 50 states to the ComboBox
        stateComboBox.getItems().addAll(
                "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
                "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky",
                "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
                "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
                "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia",
                "Washington", "West Virginia", "Wisconsin", "Wyoming"
        );
        stateComboBox.getSelectionModel().select(0);
        pane.add(stateComboBox, 1, 4);

        pane.add(stateOfBanErrLabel, 2, 4);
        stateOfBanErrLabel.setTextFill(Color.RED);
        stateOfBanErrLabel.setVisible(false);

        pane.add(addButton, 1, 5);
        booksLV.setPrefWidth(WIDTH);


        booksLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> selectBook());
        pane.add(booksLV, 0, 6, 3, 1);
        pane.add(removeButton, 0, 7);


        addButton.setOnAction(e -> addBook());
        removeButton.setOnAction(e -> removeBook());


        booksList = controller.getAllBooks();
        booksLV.setItems(booksList);

        removeButton.setDisable(true);

        this.setRoot(pane);
    }

    private void selectBook() {
        selectedBook = booksLV.getSelectionModel().getSelectedItem();

        removeButton.setDisable(selectedBook == null);
    }


    private void removeBook() {
        if (selectedBook == null)
            return;
        booksList.remove(selectedBook);
        selectedBook = null;
        booksLV.getSelectionModel().select(-1);
    }


    private void addBook() {

        String name, author, typeOfBan, stateOfBan;

        name = nameTF.getText();
        nameErrLabel.setVisible(name.isEmpty());

        author = authorTF.getText();
        authorErrLabel.setVisible(author.isEmpty());

        typeOfBan = typeOfBanTF.getText();
        typeOfBanErrLabel.setVisible(typeOfBan.isEmpty());

        stateOfBan = stateComboBox.getValue();
        stateOfBanErrLabel.setVisible(stateOfBan.isEmpty());


        if (nameErrLabel.isVisible() || authorErrLabel.isVisible() ||
                typeOfBanErrLabel.isVisible() || stateOfBanErrLabel.isVisible())
            return;


        booksList.add(0, new BannedBooks(name, author, typeOfBan, stateOfBan));


        nameTF.clear();
        authorTF.clear();
        typeOfBanTF.clear();
        stateComboBox.getSelectionModel().select(0);
    }
    private void updateDisplay()
    {
        booksLV.refresh();
    }
}
