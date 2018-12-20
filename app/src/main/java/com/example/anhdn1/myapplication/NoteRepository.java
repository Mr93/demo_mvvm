package com.example.anhdn1.myapplication;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

	private NoteDao noteDao;

	private LiveData<List<Note>> allNotes;

	public NoteRepository(Application application) {
		NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
		noteDao = noteDatabase.noteDao();
		allNotes = noteDao.getAllNotes();
	}

	public void insert(Note note) {
		new BackgroundDbTask(noteDao, TypeAction.INSERT).execute(note);
	}

	public void update(Note note) {
		new BackgroundDbTask(noteDao, TypeAction.UPDATE).execute(note);
	}

	public void delete(Note note) {
		new BackgroundDbTask(noteDao, TypeAction.DELETE).execute(note);
	}

	public void deleteAllNotes() {
		new BackgroundDbTask(noteDao, TypeAction.DELETE_ALL).execute();
	}

	public LiveData<List<Note>> getAllNotes() {
		return allNotes;
	}

	public enum TypeAction {
		INSERT,
		UPDATE,
		DELETE,
		DELETE_ALL
	}

	private static class BackgroundDbTask extends AsyncTask<Note, Void, Void> {

		private NoteDao noteDao;

		private TypeAction typeAction;

		BackgroundDbTask(NoteDao noteDao, TypeAction typeAction) {
			this.noteDao = noteDao;
			this.typeAction = typeAction;
		}

		@Override
		protected Void doInBackground(Note... notes) {
			if (typeAction == TypeAction.INSERT) {
				noteDao.insert(notes[0]);
			} else if (typeAction == TypeAction.UPDATE) {
				noteDao.update(notes[0]);
			} else if (typeAction == TypeAction.DELETE) {
				noteDao.delete(notes[0]);
			} else {
				noteDao.deleteAllNotes();
			}
			return null;
		}
	}

}
