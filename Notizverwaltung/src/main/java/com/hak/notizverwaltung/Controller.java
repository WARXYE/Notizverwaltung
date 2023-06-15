package com.hak.notizverwaltung;

import at.hakimst.apr.db.NoteDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Controller {
    private NoteDAO noteDAO;
    private ObservableList<Note> noteList;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea contentTextArea;

    @FXML
    private Button addButton;

    public void setNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    public void setNoteList(ObservableList<Note> noteList) {
        this.noteList = noteList;
    }

    @FXML
    private Button alln; // Button mit der ID "alln" im FXML-Code

    @FXML
    private Button newn; // Button mit der ID "newn" im FXML-Code

    @FXML
    private Button exitn; // Button mit der ID "alln1" im FXML-Code

    @FXML
    private void handleNewNoteButtonClick() throws IOException {
        openNewNoteWindow();
    }

    // Methode, die aufgerufen wird, wenn der "Alle Notizen" Button geklickt wird
    @FXML
    private void handleAllNotesButtonClick() throws IOException {
        openAllNotesWindow();
    }


    // Methode, die aufgerufen wird, wenn der "Exit" Button geklickt wird
    @FXML
    private void handleExitButtonClick() {
        Stage stage = (Stage) exitn.getScene().getWindow();

        // Zeige eine Bestätigungsmeldung, bevor das Fenster geschlossen wird
        boolean confirmed = showConfirmationAlert("Bestätigung", "Möchten Sie die Anwendung wirklich beenden?");
        if (confirmed) {
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        }
    }

    private void openNewNoteWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("new_note.fxml"));
            AnchorPane newNotePane = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Neue Notiz");
            stage.setScene(new Scene(newNotePane));
            stage.show();
        } catch (IOException e) {
            System.err.println("Fehler beim Öffnen des neuen Notizfensters: " + e.getMessage());
        }
    }

    private void openAllNotesWindow() throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AllNotes.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Alle Notizen");
            stage.setScene(new Scene(root));

            AllNotesController controller = loader.getController();
            controller.initialize();
            controller.setColumnSettings();


            stage.show();

    }

    private boolean showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
