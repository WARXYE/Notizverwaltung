package com.hak.notizverwaltung;

import at.hakimst.apr.db.NoteDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private NoteDAO noteDAO;
    private ObservableList<Note> noteList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        initializeNoteDAO();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setNoteDAO(noteDAO);
        controller.setNoteList(noteList);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeNoteDAO() {
        // Initialisieren Sie den NoteDAO und die ObservableList
        NoteDAO noteDAO = new NoteDAO();
    }
}
