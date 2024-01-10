package edu.miracostacollege.cs112.BannedBooks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

// BannedBook class representing a banned book
public class BannedBooks implements Serializable, Comparable<BannedBooks> {
	private String name;
	private String author;
	private String typeOfBan;
	private String stateOfBan;



	// Constructors, getters, setters, toString, and compareTo methods

	public BannedBooks(String name, String author, String typeOfBan, String stateOfBan) {
		this.name = name;
		this.author = author;
		this.typeOfBan = typeOfBan;
		this.stateOfBan = stateOfBan;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BannedBooks that = (BannedBooks) o;
		return Objects.equals(name, that.name) && Objects.equals(author, that.author) && Objects.equals(typeOfBan, that.typeOfBan) && Objects.equals(stateOfBan, that.stateOfBan);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, author, typeOfBan, stateOfBan);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTypeOfBan() {
		return typeOfBan;
	}

	public void setTypeOfBan(String typeOfBan) {
		this.typeOfBan = typeOfBan;
	}

	public String getStateOfBan() {
		return stateOfBan;
	}

	public void setStateOfBan(String stateOfBan) {
		this.stateOfBan = stateOfBan;
	}

	@Override
	public String toString() {
		return "BannedBooks [" +
				"Name of Book: " + name + ", " +
				"Author: " + author + ", " +
				"Type of Ban: " + typeOfBan + ", " +
				"State of Ban: " + stateOfBan + ".]";
	}



	@Override
	public int compareTo(BannedBooks o) {
		int nameComp = this.name.compareTo(o.name);
		if (nameComp != 0) return nameComp;

		int authorComp = this.author.compareTo(o.author);
		if (authorComp != 0) return authorComp;

		int typeOfBanComp = this.typeOfBan.compareTo(o.typeOfBan);
		if (typeOfBanComp != 0) return typeOfBanComp;

		return this.stateOfBan.compareTo(o.stateOfBan);

	}
}


