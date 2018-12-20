package com.example.anhdn1.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Iterator;

public class AddNoteActivity extends AppCompatActivity {

	public static final String EXTRA_TITLE = "EXTRA_TITLE";
	public static final String EXTRA_DES = "EXTRA_DES";
	public static final String EXTRA_PRIORITY = "EXTRA_PRIORITY";

	private EditText editTextTitle;
	private EditText editTextDes;
	private NumberPicker numberPickerPriority;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		editTextDes = findViewById(R.id.edit_text_description);
		editTextTitle = findViewById(R.id.edit_text_title);
		numberPickerPriority = findViewById(R.id.number_picker_priority);
		numberPickerPriority.setMinValue(1);
		numberPickerPriority.setMaxValue(10);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
		setTitle("Add Note");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.add_note_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.save_note:
				saveNote();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void saveNote() {
		String title = editTextTitle.getText().toString();
		String des = editTextDes.getText().toString();
		int priority = numberPickerPriority.getValue();
		if (title.trim().isEmpty() || des.trim().isEmpty()) {
			Toast.makeText(this, "Please insert a title and des", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent data = new Intent();
		data.putExtra(EXTRA_TITLE, title);
		data.putExtra(EXTRA_DES, des);
		data.putExtra(EXTRA_PRIORITY, priority);
		setResult(RESULT_OK, data);
		finish();
	}
}
