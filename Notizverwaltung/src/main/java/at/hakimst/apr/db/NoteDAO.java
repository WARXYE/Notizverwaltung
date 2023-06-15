package at.hakimst.apr.db;

import com.hak.notizverwaltung.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO implements NoteDaoImpl<Note>{


    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try {
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM notes");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");

                Note note = new Note(id, title, content);
                notes.add(note);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }


@Override
    public Note findById(int id) {
        Note note = null;
    Connection connection = DBConnection.getConnection();
        try{
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM notes WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int noteId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");

                note = new Note(noteId, title, content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;
    }
@Override
    public List<Note> findAll() {
        List<Note> noteList = new ArrayList<>();

    Connection connection = DBConnection.getConnection();
        try {
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM notes");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");

                Note note = new Note(id, title, content);
                noteList.add(note);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return noteList;
    }
@Override
    public void save(Note note) {
    Connection connection = DBConnection.getConnection();
        try {
             PreparedStatement statement = connection.prepareStatement("INSERT INTO notes (title, content) VALUES (?, ?)");
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
@Override
    public void update(Note note) {
    Connection connection = DBConnection.getConnection();
        try {
             PreparedStatement statement = connection.prepareStatement("UPDATE notes SET title = ?, content = ? WHERE id = ?");
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setInt(3, note.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
@Override
    public void delete(Note note) {
    Connection connection = DBConnection.getConnection();
        try {
             PreparedStatement statement = connection.prepareStatement("DELETE FROM notes WHERE id = ?");
            statement.setInt(1, note.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Note mapResultSetToNoteObject(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        note.setId(resultSet.getInt("id"));
        note.setTitle(resultSet.getString("title"));
        note.setContent(resultSet.getString("content"));
        return note;
    }
}
