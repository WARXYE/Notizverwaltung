package com.hak.notizverwaltung;

import at.hakimst.apr.db.NoteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AllNotesController {

    private ObservableList<Note> noteList;

    @FXML
    private TableView<Note> notesTableView;

    @FXML
    Button löschen;

    @FXML
    private TableColumn<Note, String> titleColumn;

    @FXML
    private TableColumn<Note, String> noteColumn;

    public void initialize() {
        noteList = FXCollections.observableArrayList();
        NoteDAO noteDAO = new NoteDAO();

        List<Note> notesList = noteDAO.getAllNotes();

        for (Note note : notesList) {
            noteList.add(note);
        }

        notesTableView.setItems(noteList);
        loadNotesFromDatabase(noteDAO);
    }

    private void loadNotesFromDatabase(NoteDAO noteDAO) {
        noteList.setAll(noteDAO.getAllNotes());
        System.out.println("Notizen aus der Datenbank geladen.");
    }

    public void setColumnSettings() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
    }

    public void editNote() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit_Note.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Alle Notizen");
        stage.setScene(new Scene(root));

        EditNoteController controller = loader.getController();
        controller.getNote(notesTableView.getSelectionModel().getSelectedItem());


        stage.show();
    }

    public void deleteNote() {
        Note note = notesTableView.getSelectionModel().getSelectedItem();

        NoteDAO noteDAO = new NoteDAO();
        noteDAO.delete(note);

        noteList.remove(note);

        notesTableView.refresh();
    }

    @FXML
    private void handleEditButtonAction() {
        Note selectedNote = notesTableView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
        } else {
            showAlert(AlertType.WARNING, "Hinweis", "Bitte wählen Sie eine Notiz zum Bearbeiten aus.");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
