package com.hak.notizverwaltung;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Note {
    private int id;
    private StringProperty title;
    private StringProperty content;

    public Note() {
        this.title = new SimpleStringProperty();
        this.content = new SimpleStringProperty();
    }

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public StringProperty contentProperty() {
        return content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
