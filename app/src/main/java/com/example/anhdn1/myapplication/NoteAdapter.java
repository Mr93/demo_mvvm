package com.example.anhdn1.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

	private List<Note> notes = new ArrayList<>();

	@NonNull
	@Override
	public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
		return new NoteHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
		noteHolder.txtTitle.setText(notes.get(i).getTitle());
		noteHolder.txtPriority.setText(String.valueOf(notes.get(i).getPriority()));
		noteHolder.txtDes.setText(notes.get(i).getDescription());
	}

	@Override
	public int getItemCount() {
		return notes.size();
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
		notifyDataSetChanged();
	}

	class NoteHolder extends RecyclerView.ViewHolder {
		private TextView txtTitle;
		private TextView txtPriority;
		private TextView txtDes;

		public NoteHolder(@NonNull View itemView) {
			super(itemView);
			txtDes = itemView.findViewById(R.id.text_view_description);
			txtTitle = itemView.findViewById(R.id.text_view_title);
			txtPriority = itemView.findViewById(R.id.text_view_priority);
		}
	}
}
