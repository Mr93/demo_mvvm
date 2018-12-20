package com.example.anhdn1.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import static com.example.anhdn1.myapplication.AddNoteActivity.EXTRA_DES;
import static com.example.anhdn1.myapplication.AddNoteActivity.EXTRA_PRIORITY;
import static com.example.anhdn1.myapplication.AddNoteActivity.EXTRA_TITLE;

public class MainActivity extends AppCompatActivity {

	public static final int ADD_NOTE_REQUEST = 1;
	public static final String TAG = MainActivity.class.getSimpleName();
	NoteViewModel noteViewModel;
	RecyclerView recyclerView;
	NoteAdapter noteAdapter;
	FloatingActionButton floatingActionButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		floatingActionButton = findViewById(R.id.button_add_note);
		floatingActionButton.setOnClickListener(v -> {
			Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
			startActivityForResult(intent, ADD_NOTE_REQUEST);
		});
		recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setHasFixedSize(true);
		noteAdapter = new NoteAdapter();
		recyclerView.setAdapter(noteAdapter);
		noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
		noteViewModel.getAllNotes().observe(this, notes -> {
			noteAdapter.setNotes(notes);
			Log.d(TAG, "onChanged: " + notes.size());
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
			noteViewModel.insert(new Note(data.getStringExtra(EXTRA_TITLE), data.getStringExtra(EXTRA_DES),
					data.getIntExtra(EXTRA_PRIORITY, 1)));
			Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
		}
	}
}
