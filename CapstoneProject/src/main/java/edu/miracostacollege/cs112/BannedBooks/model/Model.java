package edu.miracostacollege.cs112.BannedBooks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Model {
    public static final String CSV_FILE = "BookBans.csv";
    public static final String BINARY_FILE = "BannedBooks.dat";

    public static boolean binaryFileHasData() {
        File binaryFile = new File(BINARY_FILE);
        return (binaryFile.exists() && binaryFile.length() >= 5);
    }

    public static ObservableList<BannedBooks> populateListFromCSVFile()
    {
        ObservableList<BannedBooks> bannedBooksList = FXCollections.observableArrayList();
        try {
            Scanner csvFile = new Scanner(new File(CSV_FILE));
            //skip first line
            csvFile.nextLine();

            String line, name, author, typeOfBan, stateOfBan;
            String[] parts;

            while (csvFile.hasNextLine())
            {
                line = csvFile.nextLine();
                parts = line.split(",");
                name = parts[2];
                author = parts[1]  + parts [0];
                typeOfBan = parts[3];
                stateOfBan = parts[7];

                bannedBooksList.add(new BannedBooks(name, author, typeOfBan, stateOfBan));

            }
            csvFile.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return bannedBooksList;
    }

    public static ObservableList<BannedBooks> populateListFromBinaryFile() {
        ObservableList<BannedBooks> bannedBooksList = FXCollections.observableArrayList();
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));
                // read binary file into an array
                BannedBooks[] booksArray = (BannedBooks[]) fileReader.readObject();
                // put array into the list
                bannedBooksList.addAll(Arrays.asList(booksArray));

                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }

            return bannedBooksList;
        }
    }




    public static boolean writeDataToBinaryFile(ObservableList<BannedBooks> bannedBooksList)
    {
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));
            // make an array the same size as the list
            BannedBooks[] booksArray = new BannedBooks[bannedBooksList.size()];
            // convert list into array
            bannedBooksList.toArray(booksArray);
            // write the array to binary file
            fileWriter.writeObject(booksArray);

            fileWriter.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

}
