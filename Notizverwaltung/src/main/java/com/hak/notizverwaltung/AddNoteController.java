package com.hak.notizverwaltung;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddNoteController {
    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea noteTextArea;

    @FXML
    private Button finishButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        // Initialisierungslogik, falls erforderlich
    }

    @FXML
    private void handleFinishButtonAction() {
        String title = titleTextField.getText();
        String note = noteTextArea.getText();

        if (validateInput(title, note)) {
            saveNoteToDatabase(title, note);
        }
    }
    private boolean validateInput(String title, String note) {
        if (title.trim().isEmpty() || note.trim().isEmpty()) {
            showAlert(AlertType.ERROR, "Fehler", "Titel und Notiz dürfen nicht leer sein.");
            return false;
        }
        return true;
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void saveNoteToDatabase(String title, String note) {
        String url = "jdbc:mysql://localhost:3306/notes";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO notes (title, content) VALUES (?, ?)")) {

            statement.setString(1, title);
            statement.setString(2, note);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Fehler beim Laden der Notiz in die Datenbank: " + e.getMessage());
            showAlert(AlertType.ERROR, "Fehler", "Fehler beim Speichern der Notiz in die Datenbank.");
        }
    }
}
