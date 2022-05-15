package com.example.notes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter <NoteAdapter.NoteViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList<String>  idNote, titleNote, textNote;
    private int position;

    NoteAdapter(Activity activity, Context context, ArrayList idNote, ArrayList titleNote, ArrayList textNote) {
        this.activity = activity;
        this.context = context;
        this.titleNote = titleNote;
        this.textNote = textNote;
        this.idNote = idNote;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_note, parent, false);

        return new NoteViewHolder(view) ;
    }


    @Override
    public void onBindViewHolder(final NoteAdapter.NoteViewHolder holder, final int position) {
        this.position = position;
        holder.titleNote_txt.setText(String.valueOf(titleNote.get(position)));
        holder.textNote_txt.setText(String.valueOf(textNote.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateNoteActivity.class);
                intent.putExtra("id", String.valueOf(idNote.get(position)));
                intent.putExtra("title", String.valueOf(titleNote.get(position)));
                intent.putExtra("text", String.valueOf(textNote.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return titleNote.size();
    }

    public class NoteViewHolder extends  RecyclerView.ViewHolder {
        TextView textNote_txt, titleNote_txt;
        private LinearLayout mainLayout;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textNote_txt = itemView.findViewById(R.id.textNote);
            titleNote_txt = itemView.findViewById(R.id.titleNote);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }


}
