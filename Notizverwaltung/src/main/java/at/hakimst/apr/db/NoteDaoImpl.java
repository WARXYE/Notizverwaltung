package at.hakimst.apr.db;

import com.hak.notizverwaltung.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface NoteDaoImpl<N> {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/notizverwaltung";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";

    private Note mapResultSetToNoteObject(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        note.setId(resultSet.getInt("id"));
        note.setTitle(resultSet.getString("title"));
        note.setContent(resultSet.getString("content"));
        return note;
    }

    public N findById(int id);


    public List<N> findAll();

    public void save(N o);


    public void update(N o);


    public void delete(N o);
}
