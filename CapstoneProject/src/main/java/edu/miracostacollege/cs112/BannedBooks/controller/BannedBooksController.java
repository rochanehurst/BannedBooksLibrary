package edu.miracostacollege.cs112.BannedBooks.controller;

import edu.miracostacollege.cs112.BannedBooks.model.BannedBooks;
import edu.miracostacollege.cs112.BannedBooks.model.Model;
import javafx.collections.ObservableList;



public class BannedBooksController {

    private static BannedBooksController theInstance;

    //private controller
    private BannedBooksController() {
    }

    //  public method
    public static BannedBooksController getInstance() {
        if (theInstance == null) {
            theInstance = new BannedBooksController();
            if (Model.binaryFileHasData())
                theInstance.bannedBooksList = Model.populateListFromBinaryFile();
            else
                theInstance.bannedBooksList = Model.populateListFromCSVFile();
        }
        return theInstance;
    }

    private ObservableList<BannedBooks> bannedBooksList;

    public ObservableList<BannedBooks> getAllBooks() {
        return bannedBooksList;
    }
    public void saveData() {
        Model.writeDataToBinaryFile(bannedBooksList);
    }
}