package com.hak.notizverwaltung;

import at.hakimst.apr.db.NoteDAO;

import java.util.List;

public class NoteManager {
    private NoteDAO noteDao;

    public NoteManager(NoteDAO noteDao) {
        this.noteDao = noteDao;
    }

    public Note getNoteById(int id) {
        return noteDao.findById(id);
    }

    public List<Note> getAllNotes() {
        return noteDao.findAll();
    }

    public void saveOrUpdateNote(Note note) {
        if (note.getId() == 0) {
            noteDao.save(note);
        } else {
            noteDao.update(note);
        }
    }

    public void deleteNote(Note note) {
        noteDao.delete(note);
    }
}
