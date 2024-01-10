package edu.miracostacollege.cs112.BannedBooks.view;

import edu.miracostacollege.cs112.BannedBooks.controller.BannedBooksController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/*
Rochane Hurst
CS112
Professor Mike Paulding
12/14/2023


References:
https://docs.google.com/spreadsheets/d/1hTs_PB7KuTMBtNMESFEGuK-0abzhNxVv4tgpI5-iKe8/edit#gid=1171606318

https://studentwork.prattsi.org/infovis/projects/banned-books-data-visualization/

https://docs.google.com/spreadsheets/d/1a6v7R7pidO7TIwRZTIh9T6c0--QNNVufcUUrDcz2GJM/edit#gid=982757372


 */
public class View extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewNavigator.setStage(primaryStage);
		primaryStage.getIcons().add(new Image("banned_books_icon1.png"));
		//"https://www.flaticon.com/free-icons/study" title="study icons">Study icons created by Iconic Panda - Flaticon</a>
		ViewNavigator.loadScene("Banned Books", new MainScene());
	}

	@Override
	public void stop() throws Exception {
		BannedBooksController.getInstance().saveData();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
